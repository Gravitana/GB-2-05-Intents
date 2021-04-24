package com.example.gb_2_05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Constants {

    private EditText txtName;
    private Account account;

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = new Account();
        initView();
    }

    private void initView() {
        Button btnGreetings = findViewById(R.id.btnGreetings);
        final TextView txtGreetings = findViewById(R.id.textHello);
        txtName = findViewById(R.id.textName);
        btnGreetings.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            String sayHello = getString(R.string.say_hello) + name;
            txtGreetings.setText(sayHello);
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Чтобы стартовать активити, надо подготовить интент
                // В данном случае это будет явный интент, поскольку здесь передаётся класс активити
                Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
                populateAccount();
                // Передача данных через интент
                runSettings.putExtra(YOUR_ACCOUNT, account);
                // Метод стартует активити, указанную в интенте
                startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);

            }
        });
    }

    private void populateAccount() {
        account.setName(txtName.getText().toString());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
            Чтобы понять, какая активити возвращает результат, передаётся код запроса в первом параметре.
            Надо проверить этот параметр и обрабатывать результат, если только код совпадает.
            Если вам потребуется обрабатывать результаты сразу нескольких активити,
            то вы всегда можете ориентироваться на этот параметр.
            Потому что может быть такое, что вам нужно открывать разные активити из одного экрана
            и от каждой получать какой-то результат обратно.
         */
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        /*
            Также можно проверить код возврата. Он может быть RESULT_OK, RESULT_CANCELED
            или может быть произвольным и базироваться на значении RESULT_FIRST_USER — результат,
            определённый пользователем, отсюда и такое название.
            Это значение можно модифицировать, например, простым увеличением на единицу.
         */
        if (resultCode == RESULT_OK){
            account = data.getParcelableExtra(YOUR_ACCOUNT);
            populateView();
        }
    }

    private void populateView(){
        txtName.setText(account.getName());
    }

}