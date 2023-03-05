package com.example.taskoneandtwo.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.taskoneandtwo.R;
import com.example.taskoneandtwo.databinding.FragmentLoginBinding;
import com.example.taskoneandtwo.utils.RuntimePermission;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();

        clickEvent();

        return binding.getRoot();
    }

    private void clickEvent() {
        binding.appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        binding.butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = binding.email.getText().toString();
                String Password = binding.pass.getText().toString();

                if (validationRegForm(Email, Password)) {
                    if (RuntimePermission.getInstance(requireContext()).isInternetConnected()) {
                        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "User Login Successful", Toast.LENGTH_SHORT).show();
                                    binding.email.setText("");
                                    binding.pass.setText("");
                                    Navigation.findNavController(view).navigate(R.id.loginFragment_to_imageFragment);
                                } else {
                                    Toast.makeText(getActivity(), "User Login Failure", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validationRegForm(String Email, String Password) {

        if (Email.isEmpty()) {
            showError("Email is required", binding.email);
            return false;
        }
        if (Password.isEmpty()) {
            showError("Password is required", binding.pass);
            return false;
        }
        return true;

    }

    private void showError(String Massage, EditText error) {
        error.setError(Massage);
        error.requestFocus();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.butSinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.loginFragment_to_registrationFragment);
            }
        });

    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment_to_imageFragment);
        }
    }

*/
}