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
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Menu extends AppCompatActivity implements PokemonAdapter.OnListItemClickListener{

    RecyclerView mPokemonList;
    PokemonAdapter mPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mPokemonList = findViewById(R.id.recview);
        mPokemonList.hasFixedSize();
        mPokemonList.setLayoutManager(new LinearLayoutManager(this));



        final ArrayList<Pokemon> pokemons = new ArrayList<>();

        //-----------------Firestore read start---------------------------------------------------------------

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final PokemonAdapter.OnListItemClickListener listener = this;

        db.collection("/Restaurants/Restaurant1/Menu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)  {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Pokemon pokemon = new Pokemon(null, 0, 0, 0);

                        Map<String, Object> map = document.getData();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(entry.getKey() + "/" + entry.getValue());
                            if(entry.getKey().equals("Id")){

                                pokemon.setId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("PicId")){

                                pokemon.setIconId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("name")){

                                pokemon.setName(entry.getValue().toString());
                            }
                            if(entry.getKey().equals("Price")){

                                pokemon.setPrice(Integer.parseInt(entry.getValue().toString()));
                            }
                        }

                        pokemons.add(pokemon);

                        System.out.println(document.getId() + " => " + document.getData());
                    }
                } else {
                    System.out.println("Error getting documents." + task.getException());
                }

                /*
                int resid = R.drawable.p1;
                System.out.println(resid);
                // finding out the id of the given pic
                */

                mPokemonAdapter = new PokemonAdapter(pokemons, listener);
                mPokemonList.setAdapter(mPokemonAdapter);
            }
        });
        //---------------Firestore read end-----------------------------------------------------------------
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Pokemon selectedPokemon = mPokemonAdapter.getItem(clickedItemIndex);

        Intent intent = new Intent(getBaseContext(), InredientsSelector.class);
        intent.putExtra("id", selectedPokemon.getId());
        intent.putExtra("name", selectedPokemon.getName());
        startActivity(intent);

    }
}
