package com.example.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.api.Post;
import com.example.myapplication.api.PostApi;
import com.example.myapplication.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private MutableLiveData<List<Post>>posts;
    private PostApi postApi;

    public PostViewModel (){
        posts = new MutableLiveData<>();
        postApi = RetrofitClient.getRetrofit().create(PostApi.class);
        loadPost();

    }

    public LiveData<List<Post>>getPost(){
        return posts;
    }

    private void loadPost() {
        postApi.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    posts.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                posts.setValue(null);

            }
        });
    }

}
