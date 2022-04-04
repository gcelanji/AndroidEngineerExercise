package com.example.androidengineerexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");



    Button returnBtn, nextBtn;
    EditText email, createPassword, retypePassword;
    TextView passwordErrorMessage, emailErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        returnBtn.setOnClickListener(
                (__)->{
            finish();
        });

        nextBtn.setOnClickListener(
                (__)->{
                    if(validateInputs()){
                        Toast.makeText(this, "email: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "password: " + createPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private boolean validateInputs() {
        String email = this.email.getText().toString();
        String createPassword = this.createPassword.getText().toString();
        String retypePassword = this.retypePassword.getText().toString();

        boolean emailValidation = false;
        boolean passwordValidation = false;

        if(!validateEmail(email)){
            //this.email.setError("Invalid email");
            this.email.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            this.email.setBackgroundResource(R.drawable.et_error);

            //this.retypePassword.setError("One of the passwords you entered wasn't valid");
            this.emailErrorMessage.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.cross, 0, 0, 0);
            this.emailErrorMessage.setBackgroundResource(R.drawable.error_message);
            this.emailErrorMessage.setText(R.string.email_error_message);

        }
        else{
            // clean error message
            this.emailErrorMessage.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            this.emailErrorMessage.setBackgroundResource(0);
            this.emailErrorMessage.setText("");

            // set tick icons
            this.email.setBackgroundResource(R.drawable.et_success);
            this.email.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.tick, 0);

            // helper variable
            emailValidation = true;
        }

        if(!createPassword.equals(retypePassword)){
            this.createPassword.setBackgroundResource(R.drawable.et_error);
            this.retypePassword.setBackgroundResource(R.drawable.et_error);
            this.createPassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            this.retypePassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            //this.retypePassword.setError("Your passwords don't match");
            this.passwordErrorMessage.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.cross, 0, 0, 0);
            this.passwordErrorMessage.setBackgroundResource(R.drawable.error_message);
            this.passwordErrorMessage.setText(R.string.password_error_message_1);
        }
        else if (!validatePassword(createPassword) || !validatePassword(retypePassword)){
            this.createPassword.setBackgroundResource(R.drawable.et_error);
            this.retypePassword.setBackgroundResource(R.drawable.et_error);

            this.createPassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            this.retypePassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);

            //this.retypePassword.setError("One of the passwords you entered wasn't valid");
            this.passwordErrorMessage.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.cross, 0, 0, 0);
            this.passwordErrorMessage.setBackgroundResource(R.drawable.error_message);
            this.passwordErrorMessage.setText(R.string.password_error_message_2);
        }
        else{
            // Clean error message
            this.passwordErrorMessage.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0);
            this.passwordErrorMessage.setBackgroundResource(0);
            this.passwordErrorMessage.setText("");

            // set tick icons
            this.createPassword.setBackgroundResource(R.drawable.et_success);
            this.retypePassword.setBackgroundResource(R.drawable.et_success);
            this.createPassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.tick, 0);
            this.retypePassword.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.tick, 0);

            //helper variable
            passwordValidation = true;
        }

        if(emailValidation && passwordValidation){
            return true;
        }
        else{
            return false;
        }
    }

    private void initViews() {
        returnBtn = findViewById(R.id.return_button);
        nextBtn = findViewById(R.id.button_next);
        email = findViewById(R.id.et_email);
        createPassword = findViewById(R.id.et_create_password);
        retypePassword = findViewById(R.id.et_retype_password);
        passwordErrorMessage = findViewById(R.id.text_password_error_message);
        emailErrorMessage = findViewById(R.id.text_email_error_message);
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String emailStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(emailStr);
        return matcher.find();
    }
}