package com.example.dr.socialnetworkig;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText UserEmail, UserPassword, UserConfirmPassword;
    private Button CreateAccountButton;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserEmail = (EditText) findViewById(R.id.register_email);
        UserPassword = (EditText) findViewById(R.id.register_password);
        UserConfirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        CreateAccountButton = (Button) findViewById(R.id.register_create_account);
        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CreateNewAccount();
            }
        });

    }

    private void CreateNewAccount() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String confirmPassword = UserConfirmPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email....", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password....", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "Please confirm your password....", Toast.LENGTH_SHORT).show();
        }else if(password!=confirmPassword){
            Toast.makeText(this, "Your password does not match", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(RegisterActivity.this,"you are authenticated successfully...",Toast.LENGTH_SHORT).show();
                           }else{
                               String message = task.getException().getMessage();
                               Toast.makeText(RegisterActivity.this,"Error occurred: "+ message,Toast.LENGTH_SHORT).show();
                           }
                        }
                    });

        }
    }
}
