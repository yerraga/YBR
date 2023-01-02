package com.example.yourbuddyreviews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.yourbuddyreviews.utilities.FirebaseUtilities;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.emailEditText)
    TextInputEditText emailEditText;
    @BindView(R.id.passwordEditText)
    TextInputEditText passwordEditText;
    PasswordValidator passwordValidator = new PasswordValidator();
    FirebaseUtilities firebaseUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        firebaseUtilities = new FirebaseUtilities(this);
    }
   @OnClick(R.id.register)
    void register(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (email.length()==0 || !email.contains("@")){
            emailEditText.setError("Enter valid mailaddress");
            return;
        }


       if (!passwordValidator.validate(password)) {
           passwordEditText.setError("Please enter valid password," +
                   " \n with 8 characters length, " +
                   "\n with at least one small letter and one capital letter," +
                   "\n with at least one special characters like *, #, @" +
                   "\n with at least one number ");
           return ;
       }

       firebaseUtilities.register(email, password);

   }
    @OnClick(R.id.login)
    void goToLoginactivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}