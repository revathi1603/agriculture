package com.example.agriculture;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private TextView mtext;
    private Button login;
    private EditText email,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mtext = findViewById(R.id.register);
        email= findViewById(R.id.input_email);
        login=findViewById(R.id.button);
        password=findViewById(R.id.input_password);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    private void Login(){
        String em_ail=email.getText().toString();
        String pass_word=password.getText().toString();
        if(TextUtils.isEmpty(em_ail))
        {
            email.setError("Enter your email");
            return;
        }

        else if(TextUtils.isEmpty(pass_word))
        {
            password.setError("Enter your Password");
            return;
        }


        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(em_ail,pass_word).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(login.this,"Login Successfull...",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(login.this,dashboad.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(login.this,"Login failed....",Toast.LENGTH_LONG).show();


                }
                progressDialog.dismiss();
            }
        });

    }

}
