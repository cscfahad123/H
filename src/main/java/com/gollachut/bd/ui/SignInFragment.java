package com.gollachut.bd.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gollachut.bd.MainActivity;
import com.gollachut.bd.R;
import com.gollachut.bd.ResrePasswordFragment;
import com.gollachut.bd.SignUpFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.gollachut.bd.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    // Edit Text

    private EditText email;
    private EditText password;
    private ImageButton closeButton;
    private Button signInButton;

    private  TextView forgotPassword;

    // firebase
    private FirebaseAuth firebaseAuth;
    // email Pattern check
    private String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public static boolean disableCloseBtn=false;

    // progressBar

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);

        dontHaveAnAccount = view.findViewById(R.id.dontHaveanAccount);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        // assign all view
        email = view.findViewById(R.id.sign_In_EmailIdEditText);
        password = view.findViewById(R.id.sign_In_PasswordEditText);
        closeButton = view.findViewById(R.id.sign_in_close_button);
        signInButton = view.findViewById(R.id.SignInButton);
        // assign firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // assign progressBar

        progressBar = view.findViewById(R.id.signInProgressBar);

        // assign forgot password textView

        forgotPassword = view.findViewById(R.id.ForGotPassTextView);
        if(disableCloseBtn){
            closeButton.setVisibility(View.GONE);
        }else{
            closeButton.setVisibility(View.VISIBLE);
        }




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());
            }
        });
        // email empty check
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // password Empty check
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // on signUp Button Clicked
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
        // on close button clicked
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnGoToMainIntent();

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetPasswordFragment=true;
                setFragment(new ResrePasswordFragment());
            }
        });

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();


    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(password.getText())){
                signInButton.setEnabled(true);
                signInButton.setTextColor(Color.rgb(255,255,255));

            }else{
                signInButton.setEnabled(false);
                signInButton.setTextColor(Color.argb(50,255,255,255));
            }

        }else{
            signInButton.setEnabled(false);
            signInButton.setTextColor(Color.argb(50,255,255,255));

        }
    }

    private void checkEmailAndPassword(){
        if(email.getText().toString().matches(emailPattern)){
            if(password.length() >= 8){

                progressBar.setVisibility(View.VISIBLE);
                signInButton.setEnabled(false);
                signInButton.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    OnGoToMainIntent();

                                }else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signInButton.setEnabled(true);
                                    signInButton.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }else{
                Toast.makeText(getActivity(),"Incorrect email or password",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"Incorrect email or password",Toast.LENGTH_SHORT).show();
        }

    }


    private void OnGoToMainIntent(){
        if(disableCloseBtn){
            disableCloseBtn =false;
        }else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);

        }
        getActivity().finish();
    }
}
