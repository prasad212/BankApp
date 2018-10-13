package com.example.prasad.bank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    String email, password;

    EditText emailtext, passwordtext;
    MyApplication application;
    TextView textView;
    Button loginbutton;
    FirebaseAuth auth;

    ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailtext = findViewById(R.id.login_id_email);
        passwordtext = findViewById(R.id.loginpassword);
        loginbutton = (Button) findViewById(R.id.login_id);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(v);
            }
        });


        textView = findViewById(R.id.signup_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
        auth = FirebaseAuth.getInstance();

    }


    void login(View view) {

        if (getdata()) {
            progressBar = new ProgressDialog(this);
            progressBar.setTitle("Loging");
            progressBar.setMessage("Loging into Account");
            progressBar.show();

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        boolean isnewuser = task.getResult().getAdditionalUserInfo().isNewUser();
                        if (isnewuser) {
                            Intent intent = new Intent(MainActivity.this, Userinfo.class);
                            startActivity(intent);
                            finish();
                            progressBar.dismiss();
                        } else {


                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        progressBar.dismiss();
                        int time = Toast.LENGTH_SHORT;
                        String msg = "invalid in Firebase";
                        Toast t = Toast.makeText(MainActivity.this, msg, time);
                        t.show();
                    }
                }
            });
        }else {

        }

    }

    boolean getdata() {
        boolean valid = false;
        email = emailtext.getText().toString();
        password = passwordtext.getText().toString();
        if (email.isEmpty()) {
            emailtext.setError("Enter Email");
            valid = false;
        } else {
            emailtext.setError(null);
            valid = true;
        }
        if (password.isEmpty()) {
            passwordtext.setError("Enter Password");
            valid = false;

        } else {
            passwordtext.setError(null);
            valid  = true;
        }
        return valid;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( progressBar!=null && progressBar.isShowing() ){
            progressBar.cancel();
        }
    }
}
