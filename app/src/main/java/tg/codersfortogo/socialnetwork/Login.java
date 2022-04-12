package tg.codersfortogo.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button LoginButton;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAccountLink;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

        NeedNewAccountLink = (TextView) findViewById(R.id.register_account_link);
        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText)  findViewById(R.id.login_password);
        LoginButton = (Button) findViewById(R.id.login_button);

        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SenUserToRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowingUserToLogin();
            }

        });
    }

    private void AllowingUserToLogin(){
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password..", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Process");
            loadingBar.setMessage("Please wait, while we are allowing you to login into your Account...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                         if (task.isSuccessful()){
                             SenUserToMainActivity();
                             Toast.makeText(Login.this, "you are logged In successfully", Toast.LENGTH_SHORT).show();
                         }
                         else {
                             String message = task.getException().getMessage();
                             Toast.makeText(Login.this, "Error occurred:"+ message, Toast.LENGTH_SHORT).show();
                         }
                        loadingBar.dismiss();
                    });

        }
    }

    private void SenUserToMainActivity() {
        Intent mainIntent = new Intent(Login.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SenUserToRegisterActivity()
    {
        Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}