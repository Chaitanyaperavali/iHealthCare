package com.ase.team22.ihealthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.messages.internal.Update;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private TextView Name;
    private TextView Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.gsin);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);


    }

    public void userLogin(View v) {
        if (v.getId() == R.id.login) {
            Intent i = new Intent(login.this, Home.class);
            startActivity(i);
        }

    }

    public void facebookSignin(View v) {
        if (v.getId() == R.id.fin) {
            /*Intent i = new Intent( login.this,Home.class);
            startActivity(i);*/
        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.gsin:
                signin();
                break;
        }

    }

    private void signin() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(i, REQ_CODE);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (resultCode == 200) {
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    assert account != null;
                    String name = account.getDisplayName();
                    String email = account.getEmail();
                    Name.setText(name);
                    Email.setText(email);
                    Intent i = new Intent(login.this,Home.class);
                    startActivity(i);
                }
            }
        }
    }

}
