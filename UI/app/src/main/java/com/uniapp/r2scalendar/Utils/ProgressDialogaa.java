package com.uniapp.r2scalendar.Utils;

import android.view.View;

public class ProgressDialogaa {
    private static ProgressDialogaa instance;
    private static android.app.ProgressDialog progressDialog;

    public static ProgressDialogaa getProgressDialog() {
        if (instance == null) {
            instance = new ProgressDialogaa();
        }
        return instance;
    }

    public void displayProgressDialog(View view,String title, String message) {
        if (progressDialog == null) {
            progressDialog = new android.app.ProgressDialog(view.getContext());
        }
        progressDialog.dismiss();
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }
}
