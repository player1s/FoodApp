package com.app.foodapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class InredientsSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inredients_selector);
<<<<<<< HEAD

        final Button button = findViewById(R.id.btnIngredientsSelectorOrder);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderConfirmed.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });


        

       /* Toolbar toolbar = findViewById(R.id.toolbar);
=======
        Toolbar toolbar = findViewById(R.id.toolbar);
>>>>>>> parent of 73498c5... pressing buttons takes to a new activity using intents
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
