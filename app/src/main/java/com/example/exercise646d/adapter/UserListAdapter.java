package com.example.exercise646d.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise646d.R;
import com.example.exercise646d.model.Group;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<Group> groupList;
    private Context mainContext;


    public UserListAdapter(List<Group> groupList, Context mainContext) {
        this.groupList = groupList;
        this.mainContext = mainContext;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }


    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        final Group group = groupList.get(position);

        holder.fullname.setText(String.format(" %s %s", group.getName(), group.getSurname()));
        holder.age.setText(group.getAge() + " Years");
        holder.city.setText(group.getCity());
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(List<Group> filteredUserByCity) {
        groupList = new ArrayList<>();
        groupList.addAll(filteredUserByCity);
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView fullname, age, city;

        public ViewHolder(View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.lbl_name_value);
            age = itemView.findViewById(R.id.lbl_age_value);
            city = itemView.findViewById(R.id.lbl_city_value);
        }
    }
}