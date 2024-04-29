package com.noviceModDev.extrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userLoginUsername;
    private EditText userLoginPassword;
    Button btnLogin;
    Button btnCancel;
    String newUser;
    String newPass;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLoginUsername = (EditText)findViewById(R.id.loginUserNameInput);
        userLoginPassword = findViewById(R.id.loginPasswordInput);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        // set login button onClick Listener
        btnLogin.setOnClickListener(this);

        // receive bundle from signup activity, data carier is intent here
        Bundle bundle = getIntent().getBundleExtra("signUpData");
        assert bundle != null;
        newUser = bundle.getString("user"); // key from sign up activity
        newPass = bundle.getString("pass");

        // set cancelbutton onClick listener
        Intent intent = new Intent(this, MainActivity.class);
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        //startActivity(intent);
                        startActivity(intent);
                    }
                }
        );

    }

    @Override
    public void onClick(View view) {
        // get uer input for username and password
        String userNameLoginInput = userLoginUsername.getText().toString();
        String passwordLoginInput = userLoginPassword.getText().toString();

        if(newUser.equals(userNameLoginInput) && newPass.equals(passwordLoginInput)){
            Toast toast = Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_LONG);
            toast.setMargin(4, 4);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //Menu
            TextView m = findViewById(R.id.action_login);
            //m.setEnabled(false);
            //m.setTitle("Logout").toString();

        }else{
            Toast toast = Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_LONG);
            // Toast toast = Toast.makeText(this, "Amount must be a decimal number.", Toast.LENGTH_LONG);
            toast.setMargin(4, 4);
            toast.show();

            count++;
            if(count == 3){
                btnLogin.setEnabled(false);
            }
            //Toast.makeText(this, count + " Failed attempts", Toast.LENGTH_LONG).show();
            Toast toastFailed = Toast.makeText(getBaseContext(), count + " Failed attempt!", Toast.LENGTH_LONG);
            // Toast toast = Toast.makeText(this, "Amount must be a decimal number.", Toast.LENGTH_LONG);
            toastFailed.setMargin(4, 4);
            toastFailed.show();
        }

    }
}