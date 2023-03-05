package com.example.taskoneandtwo.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.taskoneandtwo.R;
import com.example.taskoneandtwo.databinding.FragmentPostDetailsBinding;
import com.example.taskoneandtwo.model.CommentsModel;
import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.view.adapter.CommentAdapter;
import com.example.taskoneandtwo.viewmodel.CommentsViewModel;
import com.example.taskoneandtwo.viewmodel.CreateCommentViewModel;
import com.example.taskoneandtwo.viewmodel.PostsDetailsViewModel;

import java.util.List;

public class PostDetailsFragment extends Fragment {
    private FragmentPostDetailsBinding binding;
    private String Title, Details;
    private int Id;
    private PostsDetailsViewModel viewModel = null;
    private CommentsViewModel cViewModel = null;
    private CreateCommentViewModel createCommentViewModel = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostDetailsBinding.inflate(getLayoutInflater());
        if (getArguments() != null) {
            Id = getArguments().getInt("id");
            Title = getArguments().getString("title");
            Details = getArguments().getString("details");
        }

        viewModel = new ViewModelProvider(getActivity()).get(PostsDetailsViewModel.class);
        cViewModel = new ViewModelProvider(getActivity()).get(CommentsViewModel.class);
        createCommentViewModel = new ViewModelProvider(getActivity()).get(CreateCommentViewModel.class);

        try {
            viewModel.getPostDetailsViewModel(Id).observe(getActivity(), new Observer<PostsModel>() {
                @Override
                public void onChanged(PostsModel postsModel) {
                    binding.title.setText(postsModel.getTitle());
                    binding.tvTitle.setText(postsModel.getBody());
                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Exception : " + e, Toast.LENGTH_SHORT).show();
        }

        try {
            cViewModel.getCommentsViewModel(Id).observe(getActivity(), new Observer<List<CommentsModel>>() {
                @Override
                public void onChanged(List<CommentsModel> commentsModels) {

                    CommentAdapter adapter = new CommentAdapter(commentsModels, getActivity());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                    binding.commentsRecy.setLayoutManager(gridLayoutManager);
                    binding.commentsRecy.setAdapter(adapter);

                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Exception : " + e, Toast.LENGTH_SHORT).show();
        }


        clickEvent();

        return binding.getRoot();
    }

    private void clickEvent() {

        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Exception : " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.postDetailsFragment_to_postsFragment);
            }
        });
    }
}