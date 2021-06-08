package com.uniapp.r2scalendar.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Build;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.MainActivity;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.ViewHolder.AssignmentViewHolder;
import com.uniapp.r2scalendar.ui.AddAssignmentFragment;
import com.uniapp.r2scalendar.ui.GlobalFragment;

import java.util.List;

public class AssignmentAdapter  extends RecyclerView.Adapter<AssignmentViewHolder> implements IAssignmentAdapter{
    View view;
    List<AssignmentResponse> assignmentResponseList;
    public RecyclerView recyclerView;

    Button btOk, btCancel;
    Dialog dialog;
    ImageView imgIconDialog;
    TextView tvContent3,tvContent;
    ProgressDialog progressDialog;
    AssignmentResponse choose_assignment;
    IAssignmentController iAssignmentController;
    FragmentActivity fragmentActivity;

    public AssignmentAdapter(View view, List<AssignmentResponse> assignmentResponseList, RecyclerView recyclerView, IAssignmentController iAssignmentController, FragmentActivity fragmentActivity) {
        this.view = view;
        this.assignmentResponseList = assignmentResponseList;
        this.recyclerView = recyclerView;
        this.iAssignmentController=iAssignmentController;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_assigment, parent, false);

        return new AssignmentViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
            holder.txtNo.setText("No: " +String.valueOf(position+1));
            holder.txtModuleName.setText("Module Name: "+assignmentResponseList.get(position).getModuleName());
            holder.txtClassName.setText("Class Name: "+assignmentResponseList.get(position).getClassName());
            holder.txtTrainerName.setText("Trainer Name: "+assignmentResponseList.get(position).getTrainerName());
            holder.txtRegistrationCode.setText("Registration Code: "+assignmentResponseList.get(position).getRegistrationCode());

            if(GlobalUser.getInstance().get("role").equals("Trainer"))
            {
                holder.btEdit.setVisibility(View.INVISIBLE);
                holder.btDelete.setVisibility(View.INVISIBLE);
            }

            holder.setItemClickListener((view,position1,isDelete) ->{
                if(isDelete)
                {
                    //btDelete OnClick

                    dialog = new Dialog(view.getContext());
                    dialog.setContentView(R.layout.dialog_2_button);
                    dialog.setCancelable(false);
                    imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
                    tvContent = dialog.findViewById(R.id.tvContent);
                    tvContent3 = dialog.findViewById(R.id.tvContent3);
                    btOk = dialog.findViewById(R.id.btOK);
                    btCancel = dialog.findViewById(R.id.btCancel);

                    imgIconDialog.setImageResource(R.drawable.ic_baseline_error_outline_24);
                    tvContent.setText(R.string.are_you_sure);
                    tvContent3.setText(R.string.do_you_delete);
                    btOk.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.sky_blue));
                    btOk.setText("Yes");
                    btCancel.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.red));
                    btCancel.setText("Cancel");

                    btOk.setOnClickListener(v -> {
                        dialog.dismiss();
                        progressDialog = new ProgressDialog(view.getContext());
                        progressDialog.setTitle("Please wait...");
                        progressDialog.setMessage("Loading data.......");
                        progressDialog.show();
                        choose_assignment = assignmentResponseList.get(position);
                        iAssignmentController.deleteAssignment(true, choose_assignment);
                    });
                    btCancel.setOnClickListener(v-> {
                        dialog.dismiss();
                    });
                    dialog.show();
                }
                else
                {
                    //btEdit onClick
                    GlobalFragment.replaceFragment(new AddAssignmentFragment(assignmentResponseList.get(position)), fragmentActivity);
                }
            });

    }

    @Override
    public int getItemCount() {
        return assignmentResponseList.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void configDialogConstraint(boolean isViolateConstraint)
    {
        progressDialog.dismiss();
        if(isViolateConstraint)
        {
            //btDelete OnClick
            dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.dialog_2_button);
            dialog.setCancelable(false);
            imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
            tvContent = dialog.findViewById(R.id.tvContent);
            tvContent3 = dialog.findViewById(R.id.tvContent3);
            btOk = dialog.findViewById(R.id.btOK);
            btCancel = dialog.findViewById(R.id.btCancel);

            imgIconDialog.setImageResource(R.drawable.ic_baseline_error_outline_24);
            tvContent.setText(R.string.are_you_sure);
            tvContent3.setText(R.string.assignment_violate_constraint);
            btOk.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.sky_blue));
            btOk.setText("Yes");
            btCancel.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.red));
            btCancel.setText("Cancel");

            btOk.setOnClickListener(v -> {
                dialog.dismiss();
                progressDialog.show();
                iAssignmentController.deleteAssignment(false,choose_assignment);
            });
            btCancel.setOnClickListener(v-> {
                dialog.dismiss();
            });
            dialog.show();
        }
        else
        {
            iAssignmentController.getAll();

            //btDelete OnClick
            dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.dialog_1_button);
            dialog.setCancelable(false);

            imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
            tvContent = dialog.findViewById(R.id.tvContent);
            btOk = dialog.findViewById(R.id.btOK);
            btCancel = dialog.findViewById(R.id.btCancel);
            imgIconDialog.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
            tvContent.setText(R.string.success);
            btOk.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.sky_blue));

            btOk.setOnClickListener( v -> {
                //back screen
                dialog.dismiss();
            });
            progressDialog.dismiss();
            dialog.show();
        }
    }
}
