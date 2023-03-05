package com.example.taskoneandtwo.view.ui.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.taskoneandtwo.R;
import com.example.taskoneandtwo.databinding.FragmentImageBinding;
import com.example.taskoneandtwo.utils.RuntimePermission;

public class ImageFragment extends Fragment {
    private FragmentImageBinding binding;
    private static final int SELECT_PICTURE = 3999;
    private Bitmap bitmap;
    private int width;
    private int height;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 111;
    private boolean mLocationPermissionGranted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(getLayoutInflater());

clickEvent();
        return binding.getRoot();
    }

    private void clickEvent() {
        binding.appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.imageFragment_to_loginFragment);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.imageFragment_to_postsFragment);

            }
        });

        binding.imageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (RuntimePermission.getInstance(requireContext()).checkRuntimePermission(getActivity())){
                   Log.e("permission accepted", "permission accepted");
                   Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                   photoPickerIntent.setType("image/*");
                   startActivityForResult(photoPickerIntent, SELECT_PICTURE);
               }else {
                  permission();
               }



            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }

            }

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("activityResultcalled", "called");
        if (resultCode == RESULT_OK) {
            Log.e("resultCode", "RESULT_OK");
            if (requestCode == 709) {
                Log.e("requestCode", "709");
                if (data != null) {
                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");

                    binding.image.setImageBitmap(bitmap);

                }
            } else if (requestCode == SELECT_PICTURE) {

                Uri selectedImage = data.getData();

                String imageAbsolutePath = getRealPathFromUri(requireContext(), selectedImage);
                bitmap = BitmapFactory.decodeFile(imageAbsolutePath);
                binding.image.setImageBitmap(bitmap);

            }
        }
    }


    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Bitmap rotateBitmap(Bitmap original, float degrees) {
        int x = original.getWidth();
        int y = original.getHeight();
        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);
        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        return rotatedBitmap;
    }



    private void permission() {

        if (RuntimePermission.getInstance(requireContext()).checkRuntimePermission(getActivity())) {
        } else {
            RuntimePermission.getInstance(requireContext()).checkRuntimePermission(getActivity());
        }

    }
}