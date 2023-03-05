package com.example.taskoneandtwo.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.taskoneandtwo.databinding.FragmentPostsBinding;
import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.view.adapter.PostsAdapter;
import com.example.taskoneandtwo.viewmodel.PostsViewModel;

import java.util.List;

public class PostsFragment extends Fragment {
    private FragmentPostsBinding binding;
    private PostsViewModel viewModel = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(getActivity()).get(PostsViewModel.class);

        try {
            viewModel.getPostsViewModel().observe(getActivity(), new Observer<List<PostsModel>>() {
                @Override
                public void onChanged(List<PostsModel> postsModels) {
                    PostsAdapter adapter = new PostsAdapter(postsModels, getActivity());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                    binding.recy.setLayoutManager(gridLayoutManager);
                    binding.recy.setAdapter(adapter);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Exception : " + e, Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }
}