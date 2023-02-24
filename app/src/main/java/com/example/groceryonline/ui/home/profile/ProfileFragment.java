package com.example.groceryonline.ui.home.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView profileImage;
    EditText name, mail, phone, address;
    Button update;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;


    ActivityResultLauncher<String>  profile_Img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_profile, container, false);

//        profile_Img = registerForActivityResult(
//                new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri profileUri) {
//                        profileImage.setImageURI(profileUri);
//
//                    }
//                });

        profile_Img = registerForActivityResult(
                new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri profileUri) {
                        profileImage.setImageURI(profileUri);
                        final StorageReference reference = storage.getReference().child("Profile Image").child(FirebaseAuth.getInstance().getUid());
                        reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getContext(), "Profile Picture Added", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        name = root.findViewById(R.id.name_pro);
        mail = root.findViewById(R.id.email_pro);
        address = root.findViewById(R.id.address_pro);
        phone = root.findViewById(R.id.phoneNumber_pro);

        //Profile Image
        profileImage = root.findViewById(R.id.profile_img);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_Img.launch("image/*");
            }
        });


        //Update Button
        update = root.findViewById(R.id.UpdateButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });




        return root;
    }

    private void updateUserProfile() {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData()!= null){
            Uri profileUri = data.getData();

        }
    }
}