package com.app.foodapp.Logic.MainActivityMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.app.foodapp.Logic.IngredientsSelector.InredientsSelector;
import com.app.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Menu extends AppCompatActivity implements MenuItemAdapter.OnListItemClickListener{

    RecyclerView mPokemonList;
    MenuItemAdapter mMenuItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mPokemonList = findViewById(R.id.recview);
        mPokemonList.hasFixedSize();
        mPokemonList.setLayoutManager(new LinearLayoutManager(this));



        final ArrayList<MenuItem> menuItems = new ArrayList<>();

        //-----------------Firestore read start---------------------------------------------------------------

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final MenuItemAdapter.OnListItemClickListener listener = this;

        db.collection("/Restaurants/Restaurant1/Menu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)  {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        MenuItem menuItem = new MenuItem(null, 0, 0, 0);

                        Map<String, Object> map = document.getData();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(entry.getKey() + "/" + entry.getValue());
                            if(entry.getKey().equals("Id")){

                                menuItem.setId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("PicId")){

                                menuItem.setIconId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("name")){

                                menuItem.setName(entry.getValue().toString());
                            }
                            if(entry.getKey().equals("Price")){

                                menuItem.setPrice(Integer.parseInt(entry.getValue().toString()));
                            }
                        }

                        menuItems.add(menuItem);

                        System.out.println(document.getId() + " => " + document.getData());
                    }
                } else {
                    System.out.println("Error getting documents." + task.getException());
                }


                int resid = R.drawable.respizza;
                System.out.println(resid);
                int resid2 = R.drawable.resgoulash;
                System.out.println(resid2);
                int resid3 = R.drawable.reslasagna;
                System.out.println(resid3);
                int resid4 = R.drawable.resccc;
                System.out.println(resid4);
                // finding out the id of the given pic


                mMenuItemAdapter = new MenuItemAdapter(menuItems, listener);
                mPokemonList.setAdapter(mMenuItemAdapter);
            }
        });
        //---------------Firestore read end-----------------------------------------------------------------
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        MenuItem selectedMenuItem = mMenuItemAdapter.getItem(clickedItemIndex);

        Intent intent = new Intent(getBaseContext(), InredientsSelector.class);
        intent.putExtra("id", selectedMenuItem.getId());
        intent.putExtra("name", selectedMenuItem.getName());
        startActivity(intent);

    }
}
