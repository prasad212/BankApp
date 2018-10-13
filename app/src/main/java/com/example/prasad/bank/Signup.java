package com.example.prasad.bank;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prasad.bank.Data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText confirmPasswordtext, emailtext, passwordEdit;

    FirebaseAuth auth;

    FirebaseDatabase database;
    Context signupcontext;
    String email, name, password, conpass;
    FirebaseUser fireuser;
    boolean isfireuseradded;
    Button createaccount;
    User user;

    ProgressDialog progressDialog;
    MyApplication myApplication;
    private DatabaseReference mDatabase;
   protected AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        confirmPasswordtext = findViewById(R.id.confirmpass_id);
        emailtext = findViewById(R.id.Email_id);
        passwordEdit = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        createaccount = (Button) findViewById(R.id.createaccount_id);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
    }


    void insert(View view) {


        if (getdata()) {
            if (passwordcheck(password, conpass)) {

                authfirebase();

                }
            }
        }




    boolean authfirebase() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Creating Your Account Please Wait");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email, conpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            fireuser = auth.getCurrentUser();

                            signupcontext = getApplicationContext();

                            progressDialog.dismiss();
                            login();

                        } else {
                            String inserted = "Account Already Exists";
                            int time = Toast.LENGTH_SHORT;
                            Context c = getApplicationContext();
                            Toast t = Toast.makeText(c, inserted, time);
                            t.show();

                            progressDialog.dismiss();
                        }
                    }
                });

        return false;
    }

    boolean passwordcheck(String password, String conpass) {
        if (password.equals(conpass)) {
            return true;
        } else {
          confirmPasswordtext.setError("Please Check Your Password");
            return false;
        }
    }

   private boolean getdata() {
       boolean valid = false;
       email = emailtext.getText().toString();
       conpass = confirmPasswordtext.getText().toString();
       password = passwordEdit.getText().toString();

       if (email.isEmpty()) {
           emailtext.setError("Email Required");
           valid = false;
       } else {
           emailtext.setError(null);
           valid = true;
       }

       if (password.isEmpty()) {
           passwordEdit.setError("Password Required");
           valid = false;
       } else {
           int length = password.length();
           if (length >= 6) {
               passwordEdit.setError(null);
               valid = true;
           } else {
               passwordEdit.setError("Password Has to greater than 5 character");
               valid = false;
           }
       }

           if (conpass.isEmpty()) {
               confirmPasswordtext.setError("Confirm password");
               valid = false;
           } else {

               confirmPasswordtext.setError(null);
               valid = true;
           }



       return valid;
   }
    void login()
    {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent = new Intent(Signup.this,Getuserinfo.class);
                    startActivity(intent);
                }
                else {


                }
            }
        });

    }
}
