package com.example.elbrus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button authBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        authBtn = (Button) findViewById(R.id.auth_button);
        authBtn.setOnClickListener(this);
    }

    public void authClick(View view) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auth_button:
                Intent intent = new Intent(this, Category.class);
            startActivity(intent);
                break;
        }
    }
}
