package com.example.yourbuddyreviews.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.yourbuddyreviews.LoginActivity;
import com.example.yourbuddyreviews.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseUtilities {
    Context context;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    public FirebaseUtilities(Context context){
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(context);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
    }
    public void register(String email, String passsword){
        progressDialog.setMessage("Creating account...");
        firebaseAuth.createUserWithEmailAndPassword(email, passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Account created successfully :)", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Failed due to: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void login(String email, String passsword){
        progressDialog.setMessage("Loggging to  account...");
        firebaseAuth.signInWithEmailAndPassword(email, passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Logged in successfully :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Failed due to: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void logout(){
        firebaseAuth.signOut();
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
