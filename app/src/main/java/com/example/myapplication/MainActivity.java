package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button openActivity;
    private TextView textView;
    private Button openGalery;
    private Button openGmail;
    private ImageView imageView;
    private Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        openActivity = findViewById(R.id.btn_next);
        textView = findViewById(R.id.tv_next);
        openGalery = findViewById(R.id.btn_galary);
        openGmail = findViewById(R.id.btn_gmail);
        imageView = findViewById(R.id.img_galery);

        openActivity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("text", textView.getText().toString() );
            startActivity(intent);
        });

        openGalery.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
        openGmail.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "что хотите написать");
            intent.putExtra(Intent.EXTRA_TEXT, "кому хотите написать");
            startActivity(Intent.createChooser(intent, ""));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            image = data.getData();
            imageView.setImageURI(image);
        }
    }
}