package com.example.gb_2_05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText editName = findViewById(R.id.editName);

        // получить данные из Intent
        String text = getIntent().getExtras().getString(YOUR_NAME);
        // Сохранить их в поле на экране
        editName.setText(text);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            // Метод finish() завершает активити
            finish();
        });
    }
}