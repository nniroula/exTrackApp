package com.noviceModDev.extrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpUserNameInput;
    private EditText signUpPasswordInput;
    private Button btnSignUp;
    private Button btnCancel;

    // use regular expression for pattern matchin of password
//    private String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#$@!&])[A-Za-z\\d#@!&].{4,}$";
    private String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{3,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpUserNameInput = findViewById(R.id.signupUserNameInput);
        signUpPasswordInput = findViewById(R.id.signupPasswordInput);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // get uer input for username and password
        String userNameInput = signUpUserNameInput.getText().toString();
        String passwordInput = signUpPasswordInput.getText().toString();
        // validate the password - validatePassword() - define this method
        if(validPassword(passwordInput)){
            Toast.makeText(getBaseContext(), "Valid password", Toast.LENGTH_LONG).show();

            // bundle username and password together and send it to login activity
            Bundle bundleObj = new Bundle();
            // pair up username and password
            bundleObj.putString("user", userNameInput);
            bundleObj.putString("pass", passwordInput);

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("signUpData", bundleObj);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getBaseContext(), "Min lenght = 3. Must have at least one each of a lower case, upper case and digit.", Toast.LENGTH_LONG);
           // Toast toast = Toast.makeText(this, "Amount must be a decimal number.", Toast.LENGTH_LONG);
            toast.setMargin(4, 4);
            toast.show();
        }

    }
    //
    private boolean validPassword(String pass){
        Pattern patternObj = Pattern.compile(passwordPattern);
        Matcher matcherObj = patternObj.matcher(pass);

        return matcherObj.matches();
    }
}