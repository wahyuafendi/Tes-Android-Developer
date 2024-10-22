package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.Post;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_POST = "extra_post";

    private TextView  idTextView, titleTextView;

    public static void start(Context context, Post post) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POST, post);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idTextView = findViewById(R.id.id);
        titleTextView = findViewById(R.id.title);

        // Mengambil data dari intent
        Post post = getIntent().getParcelableExtra(EXTRA_POST);
        if (post != null) {
            idTextView.setText(String.valueOf(post.getId()));
            titleTextView.setText(post.getTitle());
        }
    }
}