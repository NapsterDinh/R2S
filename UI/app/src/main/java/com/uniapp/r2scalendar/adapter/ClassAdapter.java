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

import com.uniapp.r2scalendar.Controller.IClassController;
import com.uniapp.r2scalendar.Controller.IModuleController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.View.IAddClassView;
import com.uniapp.r2scalendar.View.IClassView;
import com.uniapp.r2scalendar.ViewHolder.AssignmentViewHolder;
import com.uniapp.r2scalendar.ViewHolder.ClassViewHolder;
import com.uniapp.r2scalendar.ui.AddClassFragment;
import com.uniapp.r2scalendar.ui.GlobalFragment;
import com.uniapp.r2scalendar.ui.ModuleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassViewHolder> {
    List<ClassResponse> classResponseList;
    IClassController classController;
    View view;
    FragmentActivity fragmentActivity;
    IClassView classView;

    public ClassAdapter(List<ClassResponse> classResponseList, View view, IClassController classController, FragmentActivity fragmentActivity, IClassView classView) {
        this.classResponseList = classResponseList;
        this.view = view;
        this.classController = classController;
        this.fragmentActivity = fragmentActivity;
        this.classView = classView;
    }

    public ClassAdapter(List<ClassResponse> classResponseList, View v, IModuleController moduleController, FragmentActivity activity, ModuleFragment moduleFragment) {
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_class, parent, false);
        return new ClassViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        holder.classID.setText("Class ID: " + classResponseList.get(position).getClassID());
        holder.className.setText("Class Name: " + classResponseList.get(position).getClassName());
        holder.capacity.setText("Capacity: " + classResponseList.get(position).getCapacity());
        holder.startTime.setText("Start Date: " + classResponseList.get(position).getStartTime());
        holder.endTime.setText("End Date: " + classResponseList.get(position).getEndTime());

        holder.btnDelete.setOnClickListener(v -> {
            String today = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-"
                    + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
                    + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String message = "Do you want to delete this item?";

            try {
                if (classResponseList.get(position).getEndTime() != null
                        && classResponseList.get(position).getEndTime() != "null"
                        && new SimpleDateFormat("yyyy-MM-dd").parse(classResponseList.get(position).getEndTime())
                        .after(new SimpleDateFormat("yyyy-MM-dd").parse(today))) {
                        message = "Class is operating. Do you want to delete this class?";
                }

                confirmPopup("Are you sure?",message, view, position);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        holder.btnEdit.setOnClickListener(t -> {
            ClassResponse classResponse = new ClassResponse(classResponseList.get(position).getClassID(), classResponseList.get(position).getClassName(),
                    classResponseList.get(position).getCapacity(), classResponseList.get(position).getStartTime(),
                    classResponseList.get(position).getEndTime(), 0);
            GlobalFragment.replaceFragment(new AddClassFragment(classController, classResponse),fragmentActivity);
        });
    }

    @Override
    public int getItemCount() {
        return classResponseList.size();
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
            classController.handleDeleteClassItem(String.valueOf(classResponseList.get(position).getClassID()), classView, view);
        });

        dialog.findViewById(R.id.btCancel).setOnClickListener(t -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
