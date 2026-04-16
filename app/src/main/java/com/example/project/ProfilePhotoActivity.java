package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePhotoActivity extends AppCompatActivity {

    private ImageView imageViewPreview;
    private Button buttonCamera, buttonGallery, buttonSkip, buttonContinue;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_photo);

        imageViewPreview = findViewById(R.id.imageViewProfilePreview);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonGallery = findViewById(R.id.buttonGallery);
        buttonSkip = findViewById(R.id.buttonSkip);
        buttonContinue = findViewById(R.id.buttonContinue);

        // Launcher for Gallery
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        imageViewPreview.setImageURI(imageUri);
                        buttonContinue.setVisibility(View.VISIBLE);
                    }
                }
        );

        // Launcher for Camera (Thumbnail)
        // Fixed: TakePicturePreview takes Void as input and returns Bitmap in the callback.
        // Therefore, the launcher type should be ActivityResultLauncher<Void>.
        ActivityResultLauncher<Void> cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicturePreview(),
                result -> {
                    if (result != null) {
                        imageViewPreview.setImageBitmap(result);
                        // Convert bitmap to URI for consistency (Simplified for practical)
                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), result, "ProfilePic", null);
                        if (path != null) {
                            imageUri = Uri.parse(path);
                        }
                        buttonContinue.setVisibility(View.VISIBLE);
                    }
                }
        );

        buttonCamera.setOnClickListener(v -> cameraLauncher.launch(null));

        buttonGallery.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        buttonSkip.setOnClickListener(v -> startMainActivity());

        buttonContinue.setOnClickListener(v -> {
            if (imageUri != null) {
                UserSession.setProfilePicUri(imageUri);
            }
            startMainActivity();
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(ProfilePhotoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
