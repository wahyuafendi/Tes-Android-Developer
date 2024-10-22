package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myapplication.activity.DetailActivity;
import com.example.myapplication.adapter.PostAdapter;
import com.example.myapplication.api.Post;
import com.example.myapplication.viewModel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PostViewModel postViewModel;
    private PostAdapter postAdapter;
    private EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchEditText = findViewById(R.id.etSearch);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        postAdapter = new PostAdapter(new ArrayList<>(), post -> {
            // Handle item click, show detail view
            DetailActivity.start(MainActivity.this, post);
        });
        recyclerView.setAdapter(postAdapter);

        postViewModel.getPosts().observe(this, posts -> {  // Change 'getPost()' to 'getPosts()'
            if (posts != null) {
                postAdapter.setPosts(posts);
            }
        });


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postViewModel.searchPosts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}