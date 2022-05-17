package com.example.agriculture;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class register extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText name,email_id,phone_number,password,confirm_password;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        firebaseAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name_txt);
        email_id=findViewById(R.id.email_txt);
        phone_number=findViewById(R.id.phonenumber_txt);
        password=findViewById(R.id.password_txt);
        confirm_password=findViewById(R.id.confirmpassword_txt);
        sign_up=findViewById(R.id.signup_btn);
        progressDialog=new ProgressDialog(this);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });



    }
    private void Register(){
        String na_me=name.getText().toString();
        String email=email_id.getText().toString();
        String ph_no=phone_number.getText().toString();
        String pass_word=password.getText().toString();
        String conf_pass=confirm_password.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            email_id.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(na_me))
        {
            name.setError("Enter your name");
            return;
        }
        else if(TextUtils.isEmpty(ph_no))
        {
            phone_number.setError("Enter your Phone number");
            return;
        }
        else if(TextUtils.isEmpty(pass_word))
        {
            password.setError("Enter your Password");
            return;
        }
        else if(TextUtils.isEmpty(conf_pass))
        {
            confirm_password.setError("Enter your Confirm Password");
            return;
        }
        else if(!pass_word.equals(conf_pass))
        {
            confirm_password.setError("Password not matched");
            return;
        }
        else if(!isVaildEmail(email)){
            email_id.setError("Invalid email");
            return;
        }
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,pass_word).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(register.this,"Successfully registered...",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(register.this,dashboad.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(register.this,"Registration failed....",Toast.LENGTH_LONG).show();


                }
                progressDialog.dismiss();
            }
        });

    }

    private Boolean isVaildEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }

}
