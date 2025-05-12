package com.example.prlsv6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private GifImageView gifImageView;
    private TextView textLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        gifImageView = findViewById(R.id.gifImageView);
        textLabel = findViewById(R.id.textLabel);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString().trim().toLowerCase(Locale.ROOT);
                showGif(searchText);
            }
        });
    }

    private void showGif(String keyword) {
        // Set all to invisible first
        gifImageView.setVisibility(View.GONE);
        textLabel.setVisibility(View.GONE);

        // Normalize the keyword (remove special characters)
        String normalizedKeyword = normalizeString(keyword);

        // Search for a matching GIF in the drawable folder
        int gifId = getGifResourceId(normalizedKeyword);
        if (gifId != 0) {
            gifImageView.setImageResource(gifId);
            textLabel.setText(capitalize(keyword));
            gifImageView.setVisibility(View.VISIBLE);
            textLabel.setVisibility(View.VISIBLE);
        } else {
            textLabel.setText("No se encontró su palabra");
            textLabel.setVisibility(View.VISIBLE);
        }
    }

    private int getGifResourceId(String keyword) {
        // Directly search for the drawable with the normalized name
        int resId = getResources().getIdentifier(keyword, "drawable", getPackageName());
        return resId;
    }

    private String normalizeString(String str) {
        if (str == null) return "";

        // Replace special characters with their standard versions
        str = str
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replace("ü", "u")
                .replaceAll("[^a-z0-9_]", ""); // Remove any other special characters

        return str.toLowerCase(Locale.ROOT);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return capitalized.toString().trim();
    }
}
