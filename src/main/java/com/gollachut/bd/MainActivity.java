package com.gollachut.bd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.gollachut.bd.ui.MyCartFragment;
import com.gollachut.bd.ui.SignInFragment;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


import static com.gollachut.bd.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity{


    private AppBarConfiguration mAppBarConfiguration;

    private static final int HOME_FRAGMENT=0;
    private static final  int CART_FRAGMENT =1;
    public static boolean showCart=false;
    public static DrawerLayout drawer;



    private static int currentFragment;
    private FrameLayout frameLayout;
    private NavController navController;
    private NavigationView navigationView;
    private Dialog signInDialog;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // hide the action bar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

         drawer = findViewById(R.id.drawer_layout);

         navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_mall,
                R.id.nav_my_orders,
                R.id.nav_my_rewards,
                R.id.nav_my_cart,
                R.id.nav_my_wishlist,
                R.id.nav_my_account)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        // add clickListener

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.nav_my_mall){

//                    Toast.makeText(,"Clicked On LogOut", Toast.LENGTH_SHORT).show();

                }else if(destination.getId() == R.id.nav_my_orders){


                }else if(destination.getId()==R.id.nav_my_rewards){
                    

                }else if (destination.getId() == R.id.nav_my_cart){

                }else if (destination.getId() == R.id.nav_my_wishlist){

                }else if (destination.getId() == R.id.nav_my_account){

                }
                else if (destination.getId() == R.id.nav_log_out){
                    Log.i("faltuerror","error");
                   FirebaseAuth.getInstance().signOut();
                   Intent registerIntent =new Intent(MainActivity.this,RegisterActivity.class);
                   startActivity(registerIntent);
                   finish();
                }


            }
        });



        frameLayout = findViewById(R.id.main_frame_layout);
        signInDialog = new Dialog(MainActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        // set height and weight of the dialog layout
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        // assign dialog layout buttons
        Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
        Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);

        final Intent RegisterIntent = new Intent(MainActivity.this,RegisterActivity.class);
        // set click listener om signin and sigup button
        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn =true;
                SignUpFragment.disableCloseBtn =true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(RegisterIntent);

            }
        });
        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment.disableCloseBtn =true;
                SignInFragment.disableCloseBtn =true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(RegisterIntent);


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser =FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(false);
        }else{
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(true);
        }

    }
    //    @Override
//    protected void onStart() {
//        super.onStart();
//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(currentUser==null){
//            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(false);
//        }else{
//            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(true);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment == HOME_FRAGMENT){
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    // right site menu icons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();



        if(id== R.id.main_search_icon){
            // todo: search
            return true;
        }else if(id == R.id.main_notification_icon){
           //todo:notification
            return true;

        }else if(id== R.id.main_cart_icon){

            // create Dialog
            if(currentUser==null){
                signInDialog.show();

            }else{

            }







            //todo:cart
//            myCart();
//            AppBarConfiguration appBarConfiguration =
//                    new AppBarConfiguration.Builder(R.id.nav_my_mall, R.id.nav_my_cart).build();
//           NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//            NavigationUI.setupWithNavController(navigationView, navController);
            return true;

        }

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.onNavDestinationSelected(item, navController)
//                || super.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }

    private void myCart(){
//        navigationView.getMenu().getItem(3).setChecked(true);

      invalidateOptionsMenu();
        setFragment(new MyCartFragment(),CART_FRAGMENT);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

   private void setFragment(Fragment fragment, int fragmentNumber){

        if(fragmentNumber != currentFragment){
            currentFragment = fragmentNumber;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }



    }
    public void onClickDone(View view) {
        Log.i("Error","Error is occured");
        //Code that runs when the FAB is clicked
        Intent h=new Intent(MainActivity.this,LiveChat.class);

        startActivity(h);
    }


}
