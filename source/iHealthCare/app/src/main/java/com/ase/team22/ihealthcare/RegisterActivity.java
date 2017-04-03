package com.ase.team22.ihealthcare;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ase.team22.ihealthcare.othermodel.UserRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class RegisterActivity extends AppCompatActivity implements FragmentPersonalDetails.OnFragmentInteractionListener{

    private static final String TAG = "";
    private FirebaseAuth mAuth;

    private String email;
    private String password;
    private String userId;

    private UserRegistration userProfile = new UserRegistration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSignupOne fragmentSignupOne = new FragmentSignupOne();

        //Passing user instance
        fragmentSignupOne.setUserInstance(userProfile);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
        fragmentTransaction.add(R.id.activity_register,fragmentSignupOne );
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction() {

        try{


            email = userProfile.getEmail();
            password = userProfile.getPassword();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            //User is successfully registered and logged in
                            if (task.isSuccessful()) {
                                FirebaseUser user = task.getResult().getUser();

                                Log.d(TAG, "onComplete: uid=" + user.getUid());

                                userId = user.getUid();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("users");
                                myRef.child(userId).setValue(userProfile);

                                Intent intent = new Intent(RegisterActivity.this,Home.class);
                                startActivity(intent);

                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }catch (Exception e){
            //error
            Log.e("MYAPP", "exception: " + e.getMessage());
            Log.e("MYAPP", "exception: " + e.toString());
        }


    }
}
