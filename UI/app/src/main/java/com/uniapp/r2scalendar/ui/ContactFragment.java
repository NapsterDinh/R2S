package com.uniapp.r2scalendar.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uniapp.r2scalendar.Controller.AssignmentController;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.GlobalUser;

public class ContactFragment extends Fragment {
    ImageButton imgBtIns, imgBtTwit, imgBtFB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onViewCreated(view, savedInstanceState);
        imgBtIns = view.findViewById(R.id.imgBtIns);
        imgBtTwit = view.findViewById(R.id.imgBtTwit);
        imgBtFB = view.findViewById(R.id.imgBtFB);

        imgBtFB.setOnClickListener(v -> {
            goToFB(v);
        });

        imgBtTwit.setOnClickListener(v -> {
            goToTwit(v);
        });

        imgBtIns.setOnClickListener(v -> {
            goToIns(v);
        });
    }

    public void goToIns (View view) {
        goToUrl ( "https://www.instagram.com/jking_812/");
    }

    public void goToTwit (View view) {
        goToUrl ( "https://twitter.com/VNeseRiven");
    }

    public void goToFB (View view) {
        goToUrl ( "https://www.facebook.com/kingj812/");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
