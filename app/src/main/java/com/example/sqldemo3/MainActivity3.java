package com.example.sqldemo3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity3 extends AppCompatActivity {
    private EditText edtUsername, edtPassword, edtEmail;
    private Button btnSubmit;
    private TextView txtLoginInfo;
    Button btn_Home;
    Button btn_Browse;
    Button btn_Logout;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnSubmit = findViewById(R.id.btnSubmit);
        btn_Browse = findViewById(R.id.btn_TakeToWeb);
        btn_Home = findViewById(R.id.btn_TakeToHome);
        btn_Logout = findViewById(R.id.btn_Logout);

        txtLoginInfo = findViewById(R.id.txtLoginInfo);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtPassword.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty()){
                    if(isSigningUp && edtUsername.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity3.this, "Empty Fields Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(isSigningUp){
                    managingSigningUp();
                }else {
                    managingLoggingIN();
                }
            }
        });

        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSigningUp){
                    isSigningUp = false;
                    edtUsername.setVisibility(View.GONE);
                    btnSubmit.setText("Login");
                    txtLoginInfo.setText("No account: Sign Up");

                } else {
                    isSigningUp = true;
                    edtUsername.setVisibility(View.VISIBLE);
                    btnSubmit.setText("Sign Up");
                    txtLoginInfo.setText("Existing Account: Login");
                }
            }
        });

        btn_Browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Taking you from main activity to mainactivity 2
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Taking you from main activity to mainactivity 3
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });

    }
    private void managingSigningUp(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity3.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity3.this, MainActivity.class));

                } else{
                    Toast.makeText(MainActivity3.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void managingLoggingIN(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity3.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity3.this, MainActivity.class));
                } else{
                    Toast.makeText(MainActivity3.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
