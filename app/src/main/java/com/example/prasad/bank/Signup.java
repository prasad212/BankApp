package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;
import com.example.prasad.bank.Data.User;
import com.example.prasad.bank.Data.UserDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.xml.transform.Result;

public class Signup extends AppCompatActivity {
    public User user;
    public UserDao userDao;
    EditText nametext, emailtext, passwordEdit;
    AppDatabase db;
    FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       nametext = findViewById(R.id.Name_id);
        emailtext = findViewById(R.id.Email_id);
        passwordEdit = findViewById(R.id.password);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();
        auth = FirebaseAuth.getInstance();

    }

    void insert(View view) {

        try {

            User user = new User();
            String email = emailtext.getText().toString();
            String name = nametext.getText().toString();
            String password = passwordEdit.getText().toString();

            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);

            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String inserted = "Account Created in Firebase";
                                int time = Toast.LENGTH_SHORT;
                                Context c = getApplicationContext();
                                Toast t = Toast.makeText(c, inserted, time);
                                t.show();
                            }else
                            {
                                String inserted = "Firebase Error";
                                int time = Toast.LENGTH_SHORT;
                                Context c = getApplicationContext();
                                Toast t = Toast.makeText(c, inserted, time);
                                t.show();
                            }
                        }
                    });
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

            db.userDao().insertAll(user);
            mDatabase.push().setValue(user);
            int time = Toast.LENGTH_SHORT;
            String inserted = "Account Created";
            Context c = getApplicationContext();
            Toast t = Toast.makeText(c, inserted, time);
            t.show();
        } catch (SQLiteConstraintException e) {
            int time = Toast.LENGTH_SHORT;
            String inserted = "Email Already Exist";
            Context c = getApplicationContext();
            Toast t = Toast.makeText(c, inserted, time);
            t.show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
