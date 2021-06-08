package com.uniapp.r2scalendar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.Feedback;

import java.util.List;

public class FeedbackSpinerAdapter extends ArrayAdapter<Feedback> {

    public FeedbackSpinerAdapter(@NonNull Context context, int resource, @NonNull List<Feedback> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(this.getItem(position).getTitle());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(this.getItem(position).getTitle());
        return label;
    }
}
