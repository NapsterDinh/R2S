package com.uniapp.r2scalendar.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.IModuleController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.View.IModuleView;
import com.uniapp.r2scalendar.ViewHolder.ClassViewHolder;
import com.uniapp.r2scalendar.ViewHolder.ModuleViewHolder;
import com.uniapp.r2scalendar.ui.AddClassFragment;
import com.uniapp.r2scalendar.ui.AddModuleFragment;
import com.uniapp.r2scalendar.ui.GlobalFragment;
import com.uniapp.r2scalendar.ui.ModuleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder> {
    List<ModuleResponse> moduleResponseList;
    IModuleController moduleController;
    View view;
    FragmentActivity fragmentActivity;
    IModuleView moduleView;

    public ModuleAdapter(List<ModuleResponse> moduleResponseList, View view, IModuleController moduleController, FragmentActivity activity, ModuleFragment moduleFragment) {
        this.moduleResponseList = moduleResponseList;
        this.view = view;
        this.moduleController = moduleController;
        this.fragmentActivity = fragmentActivity;
        this.moduleView = moduleView;
    }
    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        holder.ModuleID.setText("Module ID: " + moduleResponseList.get(position).getModuleId());
        holder.ModuleName.setText("Module Name: " + moduleResponseList.get(position).getModuleName());
        holder.AdminID.setText("Admin ID: " + moduleResponseList.get(position).getAdminId());
        holder.StartTime.setText("Start Date: " + moduleResponseList.get(position).getStartTime());
        holder.EndTime.setText("End Date: " + moduleResponseList.get(position).getEndTime());
        holder.FeedbackTitle.setText("Feedback Title: " + moduleResponseList.get(position).getFeedbackId());
        holder.FeedbackStartTime.setText("Feedback StartTime: " + moduleResponseList.get(position).getFeedbackStartTime());
        holder.FeedEndTime.setText("Feedback EndTime: " + moduleResponseList.get(position).getFeedbackEndTime());

        holder.btnDelete.setOnClickListener(v -> {
            String today = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-"
                    + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
                    + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String message = "Do you want to delete this item?";

            try {
                if (moduleResponseList.get(position).getEndTime() != null
                        && moduleResponseList.get(position).getEndTime() != "null"
                        && new SimpleDateFormat("yyyy-MM-dd").parse(moduleResponseList.get(position).getEndTime())
                        .after(new SimpleDateFormat("yyyy-MM-dd").parse(today))) {
                    message = "Module is operating. Do you want to delete this class?";
                }

                confirmPopup("Are you sure?",message, view, position);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        holder.btnEdit.setOnClickListener(t -> {
            ModuleResponse moduleResponse = new ModuleResponse(moduleResponseList.get(position).getModuleId(), moduleResponseList.get(position).getModuleName(),
                    moduleResponseList.get(position).getAdminId(),
                    moduleResponseList.get(position).getFeedbackTitle(),
                    moduleResponseList.get(position).getStartTime(),
                    moduleResponseList.get(position).getEndTime(),
                    moduleResponseList.get(position).getFeedbackStartTime(),
                    moduleResponseList.get(position).getFeedbackEndTime(),
                    0);
            GlobalFragment.replaceFragment(new AddModuleFragment(moduleController, moduleResponse),fragmentActivity);
        });
    }
    @Override
    public int getItemCount() {
        return moduleResponseList.size();
    }
    public void confirmPopup(String title, String message, View view, int position) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_2_button);
        dialog.setCancelable(false);

        ((TextView) dialog.findViewById(R.id.tvContent)).setText(title);
        ((TextView) dialog.findViewById(R.id.btCancel)).setText("Cancel");
        ((TextView) dialog.findViewById(R.id.tvContent3)).setText(message);
        dialog.findViewById(R.id.btOK).setOnClickListener(t -> {
            dialog.dismiss();
            moduleController.handleDeleteModuleItem(String.valueOf(moduleResponseList.get(position).getModuleId()), moduleView, view);
        });

        dialog.findViewById(R.id.btCancel).setOnClickListener(t -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}

