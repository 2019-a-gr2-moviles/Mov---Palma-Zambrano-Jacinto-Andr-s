package com.example.dr.socialnetworkig;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;


public class Main2 extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ImageButton AddNewPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexión con Firebase para la autenticación
        mAuth = FirebaseAuth.getInstance();

        //Referencia del ID del usuario dentro de la base de datos
        userRef = (FirebaseDatabase.getInstance().getReference().child("Users"));


        //Toolbar que permite ver el titulo en la pantalla
        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        AddNewPostButton = (ImageButton)findViewById(R.id.add_new_post_button);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(Main2.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                UserMenuSelector(menuItem);

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Revisa que el usuario esté registrado para poder acceder
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            //usuario no autenticado
          SendUserToLoginActivity();
        }else{
            CheckUserExistence();
        }

    }

    private void CheckUserExistence() {
        final String current_user_id = mAuth.getCurrentUser().getUid();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_user_id)){
                    SendUserToSetupActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AddNewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendUserToPostActivity();

            }
        });

    }

    private void SendUserToPostActivity() {
        Intent addNewPostIntent = new Intent(Main2.this, PostActivity.class);
        //addNewPostIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(addNewPostIntent);
        finish();
    }

    private void SendUserToSetupActivity() {
        Intent setupIntent = new Intent(Main2.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(Main2.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_post:
                SendUserToPostActivity();
                break;
            case R.id.nav_profile:
                Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_home:
                Toast.makeText(this,"Home",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_friends:
                Toast.makeText(this,"Friend List",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_find_friends:
                Toast.makeText(this,"Find Friends",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_messages:
                Toast.makeText(this,"Messages",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_logout:
                //Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
                mAuth.signOut();
                SendUserToLoginActivity();
                break;
        }
    }
}
