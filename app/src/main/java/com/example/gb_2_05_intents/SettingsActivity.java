package com.example.gb_2_05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private EditText editName;
    private EditText editSurname;
    private EditText editAge;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

        // получить данные из Intent
        Account account = getIntent().getExtras().getParcelable(YOUR_ACCOUNT);
        // Сохранить их в поле на экране
        populateView(account);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            intentResult.putExtra(YOUR_ACCOUNT, createAccount());
            setResult(RESULT_OK, intentResult);
            // Метод finish() завершает активити
            finish();
        });
    }

    private void initView() {
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
    }

    private void populateView(Account account) {
        editName.setText(account.getName());
        editSurname.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(), "%d", account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private Account createAccount() {
        return new Account(
                editName.getText().toString(),
                editSurname.getText().toString(),
                Integer.parseInt(editAge.getText().toString()),
                editEmail.getText().toString());
    }

}