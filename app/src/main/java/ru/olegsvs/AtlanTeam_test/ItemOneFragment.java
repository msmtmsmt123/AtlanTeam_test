package ru.olegsvs.AtlanTeam_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.olegsvs.AtlanTeam_test.adapter.Model;
import ru.olegsvs.AtlanTeam_test.adapter.MultiViewTypeAdapter;
import ru.olegsvs.AtlanTeam_test.model.Comment;
import ru.olegsvs.AtlanTeam_test.model.Photo;
import ru.olegsvs.AtlanTeam_test.model.Post;
import ru.olegsvs.AtlanTeam_test.model.Todo;
import ru.olegsvs.AtlanTeam_test.model.User;
import ru.olegsvs.AtlanTeam_test.utils.Logging;

/**
 * Created by oleg.svs on 12.10.2017.
 */

public class ItemOneFragment extends Fragment implements View.OnClickListener {
    ArrayList<Model> list;
    RecyclerView mRecyclerView;
    MultiViewTypeAdapter adapter;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    private void loadData() {
        loadPost();
        loadComment();
        loadUser();
        loadPhoto(3);
        loadTodo();
    }

    private void initAdapter() {
        list= new ArrayList<>();

        adapter = new MultiViewTypeAdapter(list,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_item_one, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        initAdapter();
        return v;
    }

    private void loadPost() {
        App.getApi().getPost(1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Logging.log("onResponse: " + response.body().getTitle() + "\n" + response.body().getBody());
                list.add(new Model(Model.POST, response.body().getTitle(), response.body().getBody(),0));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Logging.log("onFailure: " + t.toString());
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadComment() {
        App.getApi().getComment(1).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Logging.log("onResponse: " + response.body().getName() + "\n" + response.body().getBody());
                list.add(new Model(Model.COMMENT, response.body().getName(), response.body().getBody(), 0));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Logging.log("onFailure: " + t.toString());
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUser() {

        App.getApi().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = new ArrayList<>();
                users.addAll(response.body());
                list.add(new Model(Model.USER, users));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Logging.log("onFailure: " + t.toString());
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPhoto(int id) {
        App.getApi().getPhoto(id).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                Logging.log("onResponse: " + response.body().getUrl());
                list.add(new Model(Model.PHOTO, response.body().getTitle(), response.body().getUrl()));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Logging.log("onFailure: " + t.toString());
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTodo() {
        Random rnd = new Random();
        App.getApi().getTodo(rnd.nextInt(200)).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Logging.log("onResponse: " + response.body().getTitle());
                list.add(new Model(Model.TODO, response.body().getTitle(), "Completed: " + response.body().getCompleted().toString(), 0));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Logging.log("onFailure: " + t.toString());
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.i("DribbbleApp", "onClick: 1");
    }
}
