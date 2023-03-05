package com.example.taskoneandtwo.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.taskoneandtwo.R;
import com.example.taskoneandtwo.model.PostsModel;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<PostsModel> listData;
    private Context context;

    public PostsAdapter(List<PostsModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(listData.get(position).getTitle());

        int Id = listData.get(position).getId();
        String Title=listData.get(position).getTitle();
        String Details=listData.get(position).getBody();

        holder.title.setText(String.valueOf(listData.get(position).getTitle()));
        holder.details.setText(String.valueOf(listData.get(position).getBody()));


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",Id);
                bundle.putString("title", Title);
                bundle.putString("details", Details);
                Navigation.findNavController(view).navigate(R.id.postsFragment_to_postDetailsFragment,bundle);

            }

        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title;
        ReadMoreTextView details;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postTitle);
            details = itemView.findViewById(R.id.postdetails);
            layout = itemView.findViewById(R.id.item);
        }

    }
}
