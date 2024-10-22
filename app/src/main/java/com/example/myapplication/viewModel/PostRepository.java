package com.example.myapplication.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.Post;
import com.example.myapplication.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private ApiService apiService;

    public PostRepository() {
        apiService = RetrofitClient.getRetrofit().create(ApiService.class);
    }

    public MutableLiveData<List<Post>> getPosts() {
        MutableLiveData<List<Post>> postData = new MutableLiveData<>();
        apiService.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postData.setValue(null);
            }
        });
        return postData;
    }
}
