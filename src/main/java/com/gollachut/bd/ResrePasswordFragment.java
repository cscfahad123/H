package com.gollachut.bd;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gollachut.bd.ui.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResrePasswordFragment extends Fragment {


    public ResrePasswordFragment() {
        // Required empty public constructor
    }

    // create variable for parent frame layout
    private FrameLayout parentFarmalayout;
    // create all variable
    private EditText registeredEmail;
    private Button resetPassBtn;
    private TextView goBack;
    // create firebase auth variable
    private FirebaseAuth firebaseAuth;

    // define hidden items

    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emailIconText;
    private ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_resre_password, container, false);
        // assign the variables
        registeredEmail = view.findViewById(R.id.resetPasswordEmailEditText);
        resetPassBtn = view.findViewById(R.id.resetPasswordButton);
        goBack = view.findViewById(R.id.resetPasswordGoBackTextView);
        //assign parent activity
        parentFarmalayout = getActivity().findViewById(R.id.register_frame_layout);
        // asssign firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // assign hidden value

        emailIconContainer= view.findViewById(R.id.ForgotPasswordEmailIconLinearLayout);
        emailIcon = view.findViewById(R.id.forgot_password_email_icon);
        emailIconText = view.findViewById(R.id.forgot_pass_email_icon_text);
        progressBar = view.findViewById(R.id.forgot_pass_progressBar);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // add text change listener to email field
        registeredEmail.addTextChangedListener(new TextWatcher() {
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
        // click listener to on go back text
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        // add click listener to button

        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIconText.setVisibility(View.GONE);


                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                //make button disable
                resetPassBtn.setEnabled(false);
                resetPassBtn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.sendPasswordResetEmail(registeredEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    // animation for successfully send reset link
                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,emailIconText.getWidth()/2,emailIconText.getHeight()/2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);

                                    scaleAnimation.setRepeatCount(1);
                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            TransitionManager.beginDelayedTransition(emailIconContainer);
                                            String successText = "Recovery email sent successfully ! check your inbox";
                                            emailIconText.setText(successText);
                                            emailIconText.setTextColor(getResources().getColor(R.color.successGreen));

                                            emailIcon.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                            emailIcon.setImageResource(R.mipmap.green_email);

                                        }
                                    });

                                    emailIconText.startAnimation(scaleAnimation);

                                }else {
                                    String error = task.getException().getMessage();

                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    // set Error Text
                                    emailIconText.setText(error);
                                    // Change the text color
                                    emailIconText.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    emailIconText.setVisibility(View.VISIBLE);

                                    // button enable
                                    resetPassBtn.setEnabled(true);
                                    resetPassBtn.setTextColor(Color.rgb(255,255,255));

                                }
                                // if resetEmail not send hide progressbar
                                progressBar.setVisibility(View.GONE);




                            }
                        });

            }
        });
    }


    // check input is input or not
    private void checkInputs(){
        if(TextUtils.isEmpty(registeredEmail.getText())){
            resetPassBtn.setEnabled(false);
            resetPassBtn.setTextColor(Color.argb(50,255,255,255));

        }else {
            resetPassBtn.setEnabled(true);
            resetPassBtn.setTextColor(Color.rgb(255,255,255));

        }
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFarmalayout.getId(),fragment);
        fragmentTransaction.commit();


    }
}
