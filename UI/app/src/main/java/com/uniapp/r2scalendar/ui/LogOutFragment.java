package com.uniapp.r2scalendar.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.uniapp.r2scalendar.LoginActivity;
import com.uniapp.r2scalendar.MainActivity;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.GlobalUser;

public class LogOutFragment extends Fragment {
    private Dialog dialog;
    private ImageView imgIconDialog;
    private TextView tvContent, tvContent3;
    private Button btOk, btCancel;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_2_button);
        dialog.setCancelable(false);
        imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
        tvContent = dialog.findViewById(R.id.tvContent);
        tvContent3 = dialog.findViewById(R.id.tvContent3);
        btOk = dialog.findViewById(R.id.btOK);
        btCancel = dialog.findViewById(R.id.btCancel);

        imgIconDialog.setImageResource(R.drawable.ic_baseline_error_outline_24);
        tvContent.setText("Are you sure?");
        tvContent3.setText("Do you want to Log Out?");
        btOk.setBackgroundTintList(this.getResources().getColorStateList(R.color.sky_blue));
        btOk.setText("Yes");
        btCancel.setBackgroundTintList(this.getResources().getColorStateList(R.color.red));
        btCancel.setText("Cancel");

        dialog.show();
        logout();
    }


    protected void logout()
    {
        btOk.setOnClickListener(v->{
            GlobalUser.getInstance().put("role","");
            dialog.dismiss();
            getActivity().finish();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });
        btCancel.setOnClickListener(v->{
            dialog.dismiss();
        });
    }
}
