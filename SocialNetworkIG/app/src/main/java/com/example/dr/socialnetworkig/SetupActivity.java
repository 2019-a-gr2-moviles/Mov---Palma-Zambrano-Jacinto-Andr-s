 package com.example.dr.socialnetworkig;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.HashMap;
import java.util.Set;

 public class SetupActivity extends AppCompatActivity {

    private EditText UserName, FullName, CountryName;
    private Button SaveInformationButton;
    private CircleImageView ProfileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
     private ProgressDialog loadingBar;

     String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        UserName = (EditText) findViewById(R.id.setup_username);
        FullName = (EditText) findViewById(R.id.setup_full_name);
        CountryName = (EditText) findViewById(R.id.setup_country_name);
        SaveInformationButton = (Button) findViewById(R.id.setup_information_button);
        ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);
        loadingBar = new ProgressDialog(this);

        SaveInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAccountSetupInformation();
            }
        });

    }

     private void SaveAccountSetupInformation() {
        String username = UserName.getText().toString();
         String fullname = FullName.getText().toString();
         String country = CountryName.getText().toString();

         if(TextUtils.isEmpty(username)){
             Toast.makeText(this,"Please write your Username...",Toast.LENGTH_SHORT);
         }if(TextUtils.isEmpty(fullname)){
             Toast.makeText(this,"Please write your Full Name...",Toast.LENGTH_SHORT);
         }if(TextUtils.isEmpty(country)){
             Toast.makeText(this,"Please write your country...",Toast.LENGTH_SHORT);
         }else{

             loadingBar.setTitle("Saving information");
             loadingBar.setMessage("Please wait, whie we are registering your data..");
             loadingBar.show();
             loadingBar.setCanceledOnTouchOutside(true);

             HashMap userMap = new HashMap();
             userMap.put("username",username);
             userMap.put("fullname",fullname);
             userMap.put("country",country);
             userMap.put("status","hey there");
             userMap.put("gender","none");
             userMap.put("dob","none");
             userMap.put("relationshipStatus","none");
             UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                 @Override
                 public void onComplete(@NonNull Task task) {
                     if(task.isSuccessful()){
                         SendUserToMainActivity();
                         Toast.makeText(SetupActivity.this,"Your account has been created successfully",Toast.LENGTH_LONG);
                         loadingBar.dismiss();

                     }else{
                         String message = task.getException().getMessage();
                         Toast.makeText(SetupActivity.this,"Error occurred; "+ message,Toast.LENGTH_SHORT);
                         loadingBar.dismiss();
                     }
                 }
             });

         }
     }

     private void SendUserToMainActivity() {
         Intent mainIntent = new Intent(SetupActivity.this,Main2.class);
         mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
         startActivity(mainIntent);
         finish();
     }
 }
