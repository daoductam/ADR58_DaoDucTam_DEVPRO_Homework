package com.rsui.rs_network;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsui.rs_network.adapter.UserAdapter;
import com.rsui.rs_network.model.User;
import com.rsui.rs_network.network.ApiCallback;
import com.rsui.rs_network.network.ApiRepository;
import com.rsui.rs_network.storage.PreferencesRepository;
import com.rsui.rs_network.storage.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.onItemClickListener {

    RecyclerView recyclerView;
    List<User> userList = new ArrayList<>();
    UserAdapter adapter;
    ApiRepository apiRepository;
    PreferencesRepository preferencesRepository;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        database = AppDatabase.getDatabase(this);

        apiRepository = new ApiRepository();
        setupViews();
        getUsers();
        saveUserInfo();
    }


    private void saveUserInfo() {
        MyApplication application = (MyApplication) getApplication();
        preferencesRepository = new PreferencesRepository(application.getDataStore());
        preferencesRepository.setUsername("John Doe");
        preferencesRepository.setUserAge(25);
        preferencesRepository.setLoggedIn(true);
    }

    private void getUsers() {
        apiRepository.getUsers(new ApiCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                adapter.notifyDataSetChanged();
                new Handler().postDelayed(() -> {
                    com.rsui.rs_network.storage.database.entities.User[] uarr =
                            users.stream().map(u -> u.toEntity())
                                    .toArray(com.rsui.rs_network.storage.database.entities.User[]::new);
                    database.databaseWriteExecutor.execute(() -> {
                        database.userDao().insertAll(uarr);
                    });
                }, 500);
            }

            @Override
            public void onError(String error) {
                // Xử lý lỗi
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Kết thúc activity hiện tại và quay lại activity trước
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(userList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(User user) {
        Log.i("data", "id: "+user.getId());
        Log.i("data", "Name: "+user.getName());
        Log.i("data", "Email: "+user.getEmail());

//        Intent intent = new Intent(this, FullscreenActivity.class);
//        intent.putExtra("avatar", user.getAvatarUrl());
//        intent.putExtra("name", user.getName());
//        intent.putExtra("email", user.getEmail());
//        startActivity(intent);
    }
}