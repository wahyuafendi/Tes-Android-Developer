package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.adapter.PostAdapter;
import com.example.myapplication.api.Post;
import com.example.myapplication.viewModel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostViewModel postViewModel;
    PostAdapter postAdapter;
    List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}