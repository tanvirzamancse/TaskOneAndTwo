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
import com.example.taskoneandtwo.databinding.FragmentRegistrationBinding;
import com.example.taskoneandtwo.model.RegistrationModel;
import com.example.taskoneandtwo.utils.RuntimePermission;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationFragment extends Fragment {
    private FragmentRegistrationBinding binding;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("User");


        clickEvent();
        return binding.getRoot();
    }

    private void clickEvent() {
        binding.butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = binding.name.getText().toString();
                String Email = binding.email.getText().toString();
                String Password = binding.pass.getText().toString();
                String CPassword = binding.cPass.getText().toString();

                if (validationRegForm(Name, Email, Password, CPassword)) {
                    RegistrationModel model = new RegistrationModel(Name, Email, Password, CPassword);

                    if (RuntimePermission.getInstance(requireContext()).isInternetConnected()) {

                        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "User Registration Successful", Toast.LENGTH_SHORT).show();

                                    binding.email.setText("");
                                    binding.pass.setText("");
                                    binding.name.setText("");
                                    binding.cPass.setText("");


                                    myRef.child(Name).setValue(model);
                                    Navigation.findNavController(view).navigate(R.id.registrationFragment_to_loginFragment);
                                }else {
                                    Toast.makeText(getActivity(), "User Registration Error", Toast.LENGTH_SHORT).show();

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

    private boolean validationRegForm(String Name, String Email, String Password, String CPassword) {

        if (Name.isEmpty()) {
            showError("Name is required", binding.name);
            return false;
        }
        if (Email.isEmpty()) {
            showError("Email is required", binding.email);
            return false;
        }
        if (Password.isEmpty()) {
            showError("Password is required", binding.pass);
            return false;
        }
        if (CPassword.isEmpty()) {
            showError("CPassword is required", binding.cPass);
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

        binding.butSinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registrationFragment_to_loginFragment);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registrationFragment_to_loginFragment);
            }
        });

    }
}