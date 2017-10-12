package ru.olegsvs.AtlanTeam_test.api;

import ru.olegsvs.AtlanTeam_test.model.Comment;
import ru.olegsvs.AtlanTeam_test.model.Photo;
import ru.olegsvs.AtlanTeam_test.model.Post;
import ru.olegsvs.AtlanTeam_test.model.Todo;
import ru.olegsvs.AtlanTeam_test.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by oleg.svs on 11.10.2017.
 */

public interface TypicodeApi {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @GET("comments/{id}")
    Call<Comment> getComment(@Path("id") int id);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") int id);

    @GET("todos/{id}")
    Call<Todo> getTodo(@Path("id") int id);
}
