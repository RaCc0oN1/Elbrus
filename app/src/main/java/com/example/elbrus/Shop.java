package com.example.elbrus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Shop extends AppCompatActivity {
    int scoreX = 0;
    Button plus, minus, product1;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        score = (TextView) findViewById(R.id.score);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        product1 = (Button) findViewById(R.id.product1);

        product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreX != 0 && scoreX >= 5) {
                    scoreX -= 5;
                    score.setText("" + scoreX);
                } else{
                    score.setText("" + scoreX);

                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreX += 10;
                score.setText("" + scoreX);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreX != 0) {
                    scoreX -= 10;
                    score.setText("" + scoreX);
                } else{
                    score.setText("" + scoreX);

                }
            }
        });
    }
}
