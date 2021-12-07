package com.example.exercise646d.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise646d.R;
import com.example.exercise646d.adapter.UserListAdapter;
import com.example.exercise646d.api.APIClient;
import com.example.exercise646d.model.Group;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {
    private List<Group> groups;
    private UserListAdapter userListAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private AppCompatTextView noContent;
    private SearchView filterBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mRecyclerView = findViewById(R.id.rv_user_list);
        mProgressBar = findViewById(R.id.progress_bar);
        noContent = findViewById(R.id.empty_content);
        filterBy = findViewById(R.id.filter_by_city);

        filterBy.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String filterText) {
                final List<Group> filteredGroupList = filterUserByCity(groups, filterText);
                userListAdapter.setFilter(filteredGroupList);
                return true;
            }
        });

        getGroups();
    }

    public List<Group> filterUserByCity(List<Group> groups, String filterText) {
        filterText = filterText.toLowerCase();
        final List<Group> filteredGroupList = new ArrayList<>();
        for (Group group : groups) {
            final String city = group.getCity().toLowerCase();
            if (city.contains(filterText)) {
                filteredGroupList.add(group);
            }
        }
        return filteredGroupList;
    }


    private void populateUserList(List<Group> userList) {
        groups = userList;
        userListAdapter = new UserListAdapter(userList, getApplicationContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(userListAdapter);

    }

    private void getGroups() {
        Call<List<Group>> groupResponse = APIClient.getAPIService().groupList();
        groupResponse.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(@NonNull Call<List<Group>> call, @NonNull Response<List<Group>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    groups = new ArrayList<>();
                    populateUserList(response.body());
                    noContent.setVisibility(View.GONE);
                } else {
                    noContent.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Group>> call, @NonNull Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}