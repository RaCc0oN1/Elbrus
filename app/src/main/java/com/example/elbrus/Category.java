package com.example.elbrus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity implements View.OnClickListener {

    Button shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        shop = (Button) findViewById(R.id.shopBtn);
        shop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopBtn:
                Intent intent = new Intent(this, Shop.class);
                startActivity(intent);
                break;
        }
    }
}
