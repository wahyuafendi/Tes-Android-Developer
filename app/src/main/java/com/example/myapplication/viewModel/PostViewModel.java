package com.example.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.api.Post;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private MutableLiveData<List<Post>> posts;
    private ApiService postApi;
    private List<Post> allPosts;

    public PostViewModel() {
        posts = new MutableLiveData<>();
        postApi = RetrofitClient.getRetrofit().create(ApiService.class);
        loadPosts();  // Load all posts initially
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    private void loadPosts() {
        postApi.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    allPosts = response.body();
                    posts.setValue(allPosts);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                posts.setValue(null);
            }
        });
    }

    public void searchPosts(String query) {
        if (query == null || query.isEmpty()) {
            posts.setValue(allPosts);  // Show all posts if query is empty
        } else {
            List<Post> filteredPosts = new ArrayList<>();
            for (Post post : allPosts) {
                if (post.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredPosts.add(post);
                }
            }
            posts.setValue(filteredPosts);
        }
    }

}
