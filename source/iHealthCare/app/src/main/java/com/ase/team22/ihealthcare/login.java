package com.ase.team22.ihealthcare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private TextView msg;
    TextView txtStatus;
    LoginButton fin;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        msg = (TextView) findViewById(R.id.tv_msg);
        msg.setVisibility(View.INVISIBLE);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        Button signInButton = (Button) findViewById(R.id.gsin);
        signInButton.setOnClickListener(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initializeControls();
        loginWithFB();
    }

    private void initializeControls() {
        txtStatus = (TextView) findViewById(R.id.txtstatus);
        fin = (LoginButton) findViewById(R.id.fin);
        callbackManager = CallbackManager.Factory.create();
    }
    private void loginWithFB() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtStatus.setText("login success/n"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText("login cancelled.");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("login error: "+error.getMessage());
            }
        });
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        login callbackManager = null;

        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Log.i(this.getClass().getName(),data.toString()+" : "+result.isSuccess()+" resultcode : "+ resultCode);
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    /*Log.i(this.getClass().getName(),data.toString()+" : "+account.getAccount().toString()
                            +"\n"+account.getId()+"\n"+account.getIdToken()+"\n"+account.zzqF());*/
                    msg.setVisibility(View.VISIBLE);
                    msg.setText("Hello "+account.getDisplayName()+" ! \nThanks for logging into iHealthCare");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(login.this,Home.class);
                            startActivity(i);
                        }
                    }, 1500);

                }
        }

    }

}
