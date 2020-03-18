package com.gollachut.bd;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import com.gollachut.bd.ui.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.core.content.res.ResourcesCompat.getDrawable;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    //All inputs
    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    // Buttons
    private ImageButton closeBtn;
    private Button signUpBtn;
    // progress Bar
    private ProgressBar progressbar;

    // freebase Auth
    private FirebaseAuth firebaseAuth;

    // firebase store

    private FirebaseFirestore firebaseFirestore;

    // check valid email
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public static boolean disableCloseBtn=false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);

        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        alreadyHaveAnAccount = view.findViewById(R.id.alreadyHaveAccountTextView);

        // assign all input using id
        email = view.findViewById(R.id.SignUpEmailIdEditText);
        fullName = view.findViewById(R.id.signUpFullNameEditText);
        password =view.findViewById(R.id.SignUpPassword);
        confirmPassword = view.findViewById(R.id.SignUpConfirmPass);
        // assign Btn and progress bar
        closeBtn= view.findViewById(R.id.sign_up_close_button);
        signUpBtn = view.findViewById(R.id.SignUpButton);
        progressbar = view.findViewById(R.id.signUpProgressBar2);
        // arsing firebaseauth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        if(disableCloseBtn){
            closeBtn.setVisibility(View.GONE);
        }else{
            closeBtn.setVisibility(View.VISIBLE);
        }



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        // check all field  empty or not
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    checkInputes();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputes();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputes();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputes();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // on signUp button clicked
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailAndPassword();
            }
        });

        // on close Button clicked

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGotoMainActivity();
            }
        });
    }

    /* All Methods  */
    //go to login fragment
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();


    }
   //check all inputs if blanks
    private void checkInputes(){

        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(fullName.getText())){
                if(!TextUtils.isEmpty(password.getText())&& password.length() >= 8){
                    if(!TextUtils.isEmpty(confirmPassword.getText())){
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb( 255,255,255));

                    }else{
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50, 255,255,255));

                    }
                }else{
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50, 255,255,255));

                }

            }else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50, 255,255,255));

            }

        }else{
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50, 255,255,255));

        }

    }
    // on signUp button clicked check valid email and password match
    private void checkEmailAndPassword(){

        // change the error icon
        Drawable customErrorIcon = getDrawable(getResources(), R.mipmap.error, null);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());

//
        if(email.getText().toString().matches(emailPattern)){
            if(password.getText().toString().equals(confirmPassword.getText().toString())){
                // visible progressBar if all set
                progressbar.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50, 255,255,255));

                // send data to firebase
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Map<Object,String > userdata = new HashMap<>();
                                    userdata.put("fullname",fullName.getText().toString());
                                    firebaseFirestore.collection("USERS").document(firebaseAuth.getUid())
                                            .set(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Map<Object,Long > listSize = new HashMap<>();
                                                        listSize.put("fullname",(long) 0);
                                                        firebaseFirestore.collection("USERS").document(firebaseAuth.getUid()).collection("USER_DATA").document("MY_WISHLIST")
                                                                .set(listSize).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    onGotoMainActivity();

                                                                }else{
                                                                    progressbar.setVisibility(View.INVISIBLE);
                                                                    signUpBtn.setEnabled(true);
                                                                    signUpBtn.setTextColor(Color.rgb( 255,255,255));
                                                                    String error = task.getException().getMessage();
                                                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                                                                }
                                                            }
                                                        });

                                                    }else{

                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });


                                }else{
                                    progressbar.setVisibility(View.INVISIBLE);
                                    signUpBtn.setEnabled(true);
                                    signUpBtn.setTextColor(Color.rgb( 255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                confirmPassword.setError("Password doesn't match!", customErrorIcon);
            }
        }else{
            email.setError("Invalid Email", customErrorIcon);

        }

    }

    private void onGotoMainActivity(){
        if(disableCloseBtn){
            disableCloseBtn =false;
        }else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);

        }
        getActivity().finish();
    }
}
