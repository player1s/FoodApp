package com.app.foodapp.Logic.IngredientsSelector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.foodapp.Logic.OrderConfirmed.OrderConfirmed;
import com.app.foodapp.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class InredientsSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inredients_selector);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        TextView tv2 = findViewById(R.id.nameshow);
        tv2.setText(name);

        final Button button = findViewById(R.id.btnIngredientsSelectorOrder);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderConfirmed.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }
}