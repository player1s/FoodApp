package com.app.foodapp;

<<<<<<< HEAD
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> parent of 73498c5... pressing buttons takes to a new activity using intents

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
<<<<<<< HEAD

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Button button = findViewById(R.id.btnMenuSelectFood);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InredientsSelector.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

                db.collection("Restaurants")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        System.out.println(document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    System.out.println("Error getting documents." + task.getException());
                                }
                            }
                        });

            }
        });
=======
        FirebaseFirestore db = FirebaseFirestore.getInstance();
>>>>>>> parent of 73498c5... pressing buttons takes to a new activity using intents
    }
}
