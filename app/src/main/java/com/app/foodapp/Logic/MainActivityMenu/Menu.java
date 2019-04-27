package com.app.foodapp.Logic.MainActivityMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.app.foodapp.Logic.IngredientsSelector.InredientsSelector;
import com.app.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Menu extends AppCompatActivity implements PokemonAdapter.OnListItemClickListener{

    RecyclerView mPokemonList;
    RecyclerView.Adapter mPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mPokemonList = findViewById(R.id.recview);
        mPokemonList.hasFixedSize();
        mPokemonList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", R.drawable.p1, 1, 12));
        pokemons.add(new Pokemon("Ivysaur", R.drawable.p2, 2, 43));



        mPokemonAdapter = new PokemonAdapter(pokemons, this);
        mPokemonList.setAdapter(mPokemonAdapter);

                //-----------------Firestore read start---------------------------------------------------------------

                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                //---------------Firestore read end-----------------------------------------------------------------

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent intent = new Intent(getBaseContext(), InredientsSelector.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        /*
        int pokemonNumber = clickedItemIndex + 1;
        Toast.makeText(this, "Pokemon Number: " + pokemonNumber, Toast.LENGTH_SHORT).show();
        */
    }
}
