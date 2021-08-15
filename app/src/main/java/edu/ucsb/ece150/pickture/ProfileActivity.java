package edu.ucsb.ece150.pickture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

/*
 * This is the main activity of Pickture. It will should display the user's profile picture
 * and the user's first/last name. An example ImageView and example picture is given.
 *
 * Remember to read through all available documentation (there are so many Android development
 * guides that can be found) and read through your error logs.
 */
public class ProfileActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;
    private int newProfilePictureId = -1;


    public void onClick(View view) {
        Intent intent = new Intent(ProfileActivity.this, GalleryActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView displayPictureView = findViewById(R.id.exampleImageView);
        Intent intent = getIntent();
        imageView = findViewById(R.id.exampleImageView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newProfilePictureId = bundle.getInt("image",-1);
            share(newProfilePictureId);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    @Override
    protected void onPause() {
        super.onPause();




        //ImageView profilePicture = (ImageView) this.findViewById(R.id.exampleImageView);
        int id = getResources().getIdentifier("receivedImage", "drawable", getPackageName());


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        ImageView displayPictureView = findViewById(R.id.exampleImageView);
        if (containsImage()) {
            if (getImage() == -1) {
                displayPictureView.setImageResource(R.drawable.i1);
            } else {
                displayPictureView.setImageResource(getImage());
            }
        } else {
            displayPictureView.setImageResource(R.drawable.i1);
        }

    }

    private void share(final int imageId) {
        SharedPreferences sharedPreferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("drawableId", imageId).apply();
    }

    private boolean containsImage() {
        SharedPreferences sharedPreferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.contains("drawableId");
    }

    private int getImage() {
        SharedPreferences sharedPreferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getInt("drawableId", -1);
    }




}
