package ru.olegsvs.AtlanTeam_test.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.olegsvs.AtlanTeam_test.App;
import ru.olegsvs.AtlanTeam_test.R;
import ru.olegsvs.AtlanTeam_test.model.Comment;
import ru.olegsvs.AtlanTeam_test.model.Post;
import ru.olegsvs.AtlanTeam_test.utils.Logging;


/**
 * Created by oleg.svs on 11.10.2017.
 */
public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;

    public static class PostTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView body;
        CardView cardView;
        Button postButton;
        EditText edPost;

        public PostTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.body = (TextView) itemView.findViewById(R.id.body);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.edPost = (EditText) itemView.findViewById(R.id.postEditText);
            this.postButton = (Button) itemView.findViewById(R.id.postButton);
            this.postButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Logging.log("onGetPostClick: " + this.edPost.getText().toString());
            int id = Integer.parseInt(edPost.getText().toString() );
            if (id <= 100) {
                App.getApi().getPost(id).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Logging.log("onResponse: " + response.body().getTitle());
                        PostTypeViewHolder.this.title.setText(response.body().getTitle());
                        PostTypeViewHolder.this.body.setText(response.body().getBody());
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Logging.log("onFailure: " + t.toString());
                    }
                });
            } else Toast.makeText(itemView.getContext(),"Please, set value <= 100", Toast.LENGTH_LONG).show();
        }
    }

    public static class CommentTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView body;
        CardView cardView;
        Button commentButton;
        EditText edComment;

        public CommentTypeViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.body = (TextView) itemView.findViewById(R.id.body);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.edComment = (EditText) itemView.findViewById(R.id.commentEditText);
            this.commentButton = (Button) itemView.findViewById(R.id.commentButton);
            this.commentButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Logging.log("onGetCommentClick: " + this.edComment.getText().toString());
            int id = Integer.parseInt(edComment.getText().toString() );
            if(id <= 500) {
                App.getApi().getComment(id).enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        Logging.log("onResponse: " + response.body().getName());
                        CommentTypeViewHolder.this.name.setText(response.body().getName());
                        CommentTypeViewHolder.this.body.setText(response.body().getBody());
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Logging.log("onFailure: " + t.toString());
                    }
                });
            } else Toast.makeText(itemView.getContext(),"Please, set value <= 500", Toast.LENGTH_LONG).show();
        }
    }

    public static class UserTypeViewHolder extends RecyclerView.ViewHolder {

        TextView name1;
        TextView name2;
        TextView name3;
        TextView name4;
        TextView name5;
        CardView cardView;

        public UserTypeViewHolder(View itemView) {
            super(itemView);

            this.name1 = (TextView) itemView.findViewById(R.id.name1);
            this.name2 = (TextView) itemView.findViewById(R.id.name2);
            this.name3 = (TextView) itemView.findViewById(R.id.name3);
            this.name4 = (TextView) itemView.findViewById(R.id.name4);
            this.name5 = (TextView) itemView.findViewById(R.id.name5);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class TodoTypeViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView completed;
        CardView cardView;

        public TodoTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.completed = (TextView) itemView.findViewById(R.id.completed);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.POST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_type, parent, false);
                return new PostTypeViewHolder(view);
            case Model.COMMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_type, parent, false);
                return new CommentTypeViewHolder(view);
            case Model.USER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_type, parent, false);
                return new UserTypeViewHolder(view);
            case Model.TODO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_type, parent, false);
                return new TodoTypeViewHolder(view);
            case Model.PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.POST;
            case 1:
                return Model.COMMENT;
            case 2:
                return Model.USER;
            case 3:
                return Model.PHOTO;
            case 4:
                return Model.TODO;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Model.POST:
                    ((PostTypeViewHolder) holder).title.setText(object.title);
                    ((PostTypeViewHolder) holder).body.setText(object.desc);
                    break;
                case Model.COMMENT:
                    ((CommentTypeViewHolder) holder).name.setText(object.title);
                    ((CommentTypeViewHolder) holder).body.setText(object.desc);
                    break;
                case Model.USER:
                    ((UserTypeViewHolder) holder).name1.setText(object.name1);
                    ((UserTypeViewHolder) holder).name2.setText(object.name2);
                    ((UserTypeViewHolder) holder).name3.setText(object.name3);
                    ((UserTypeViewHolder) holder).name4.setText(object.name4);
                    ((UserTypeViewHolder) holder).name5.setText(object.name5);
                    break;
                case Model.PHOTO:
                    ((ImageTypeViewHolder) holder).title.setText(object.title);
                    Picasso.with(mContext)
                            .load(object.url)
                            .into(((ImageTypeViewHolder) holder).image);
                    break;
                case Model.TODO:
                    ((TodoTypeViewHolder) holder).title.setText(object.title);
                    ((TodoTypeViewHolder) holder).completed.setText(object.desc);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
