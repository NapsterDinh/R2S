package com.uniapp.r2scalendar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.uniapp.r2scalendar.Controller.IAccountController;
import com.uniapp.r2scalendar.Controller.AccountController;

import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.ui.AssignmentFragment;
import com.uniapp.r2scalendar.ui.ClassFragment;
import com.uniapp.r2scalendar.ui.ContactFragment;
import com.uniapp.r2scalendar.ui.EnrollmentFragment;
import com.uniapp.r2scalendar.ui.FeedbackFragment;
import com.uniapp.r2scalendar.ui.GlobalFragment;
import com.uniapp.r2scalendar.ui.HomeFragment;
import com.uniapp.r2scalendar.ui.JoinFragment;
import com.uniapp.r2scalendar.ui.LogOutFragment;
import com.uniapp.r2scalendar.ui.ModuleFragment;
import com.uniapp.r2scalendar.ui.QuestionFragment;
import com.uniapp.r2scalendar.ui.ResultFragment;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.top_header);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        //set global user
        //GlobalUser.getInstance().put("idUser","tantudinh812");
        //GlobalUser.getInstance().put("role","Admin");

        //configure menu
        //configMenu(GlobalUser.getInstance().get("role"));
        configMenu(GlobalUser.getInstance().get("role"));


        GlobalFragment.addFragment(new HomeFragment(),MainActivity.this);

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    void configMenu(String role)
    {
        switch (role)
        {
            case "Admin":
                        navigationView.getMenu().findItem(R.id.nav_join).setVisible(false);
                break;
            case "Trainer":
                        navigationView.getMenu().findItem(R.id.nav_enrollment).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_feedback).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_question).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_join).setVisible(false);
                break;
            case "Trainee":
                        navigationView.getMenu().findItem(R.id.nav_assignment).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_enrollment).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_feedback).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_result).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_question).setVisible(false);
                break;

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        //GlobalFragment.getFragmentManager(null);
        switch (item.getItemId())
        {
            case R.id.nav_home:
                GlobalFragment.replaceFragment(new HomeFragment(),MainActivity.this);
                break;
            case R.id.nav_assignment:
                GlobalFragment.replaceFragment(new AssignmentFragment(),MainActivity.this);
                break;
            case R.id.nav_class:
                GlobalFragment.replaceFragment(new ClassFragment(),MainActivity.this);
                break;
            case R.id.nav_module:
                GlobalFragment.replaceFragment(new ModuleFragment(),MainActivity.this);
                break;
            case R.id.nav_enrollment:
                GlobalFragment.replaceFragment(new EnrollmentFragment(),MainActivity.this);
                break;
            case R.id.nav_feedback:
                GlobalFragment.replaceFragment(new FeedbackFragment(MainActivity.this),MainActivity.this);
                break;
            case R.id.nav_result:
                GlobalFragment.replaceFragment(new ResultFragment(),MainActivity.this);
                break;
            case R.id.nav_question:
                GlobalFragment.replaceFragment(new QuestionFragment(),MainActivity.this);
                break;
            case R.id.nav_contact:
                GlobalFragment.replaceFragment(new ContactFragment(),MainActivity.this);
                break;
            case R.id.nav_join:
                GlobalFragment.replaceFragment(new JoinFragment(),MainActivity.this);
                break;
            case R.id.nav_logout:
                GlobalFragment.replaceFragment(new LogOutFragment(),MainActivity.this);
                break;
        }
        return true;
    }



}