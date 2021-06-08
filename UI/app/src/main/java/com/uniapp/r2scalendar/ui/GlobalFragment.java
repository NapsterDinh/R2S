package com.uniapp.r2scalendar.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uniapp.r2scalendar.R;

public class GlobalFragment {
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static Fragment pre_fragment;

    public static void getFragmentManager(FragmentActivity fragmentActivity)
    {
        if(fragmentManager==null)
        {
            fragmentManager = fragmentActivity.getSupportFragmentManager();
        }
    }

    public static void replaceFragment(Fragment fragment, FragmentActivity fragmentActivity)
    {
        pre_fragment = fragmentManager.findFragmentById(R.id.nav_host_fragment);
        getFragmentManager(fragmentActivity);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public static void addFragment(Fragment fragment,FragmentActivity fragmentActivity)
    {
        getFragmentManager(fragmentActivity);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment,fragment);
        fragmentTransaction.commit();
    }
    public static void backFragment(FragmentActivity fragmentActivity)
    {
        getFragmentManager(fragmentActivity);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,pre_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
