package com.app.foodapp.Logic.MainActivityMenu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.app.foodapp.Logic.IngredientsSelector.InredientsSelector;
import com.app.foodapp.Logic.Settings.Settings;
import com.app.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Menu extends AppCompatActivity implements MenuItemAdapter.OnListItemClickListener{

    RecyclerView mItemList;
    MenuItemAdapter mMenuItemAdapter;
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTopToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        mItemList = findViewById(R.id.recview);
        mItemList.hasFixedSize();
        mItemList.setLayoutManager(new LinearLayoutManager(this));

        fireStoreRead("/Restaurants/Restaurant1/Breakfast");

        //---------------Bottom navbar navigation start-----------------------------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {

                System.out.println("it thinks itemid: " + item.getItemId());

                //these numbers are the integer ids of the resources, ids, all that
                //here im using that to determine which button was tapped
                //this is for the bottom menu bar

                switch (item.getItemId()) {
                    case 2131230760:
                        fireStoreRead("/Restaurants/Restaurant1/Breakfast");
                        break;
                    case 2131230842:
                        fireStoreRead("/Restaurants/Restaurant1/Lunch");
                        break;
                    case 2131230798:
                        fireStoreRead("/Restaurants/Restaurant1/Dinner");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getBaseContext(), Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        MenuItem selectedMenuItem = mMenuItemAdapter.getItem(clickedItemIndex);

        //passing on ID of the selected menu item and name for later use

        Intent intent = new Intent(getBaseContext(), InredientsSelector.class);
        intent.putExtra("id", selectedMenuItem.getId());
        intent.putExtra("name", selectedMenuItem.getName());
        startActivity(intent);

    }
    //---------------Bottom navbar navigation end-----------------------------------------------------

    public void fireStoreRead(String collectionPath)
    {
        //-----------------Firestore read start---------------------------------------------------------------

        final ArrayList<MenuItem> menuItems = new ArrayList<>();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final MenuItemAdapter.OnListItemClickListener listener = this;

        db.collection(collectionPath).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)  {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        MenuItem menuItem = new MenuItem(null, 0, 0, 0);
                        //------------------define the type of the read data start --------------
                        Map<String, Object> map = document.getData();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(entry.getKey() + "/" + entry.getValue());
                            if(entry.getKey().equals("id")){
                                menuItem.setId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("PicId")){
                                menuItem.setIconId(Integer.parseInt(entry.getValue().toString()));
                            }
                            if(entry.getKey().equals("name")){
                                menuItem.setName(entry.getValue().toString());
                            }
                            if(entry.getKey().equals("price")){
                                menuItem.setPrice(Integer.parseInt(entry.getValue().toString()));
                            }
                        }
                        //------------------define the type of the read data end --------------
                        menuItems.add(menuItem);

                        System.out.println(document.getId() + " => " + document.getData());
                    }
                } else {
                    System.out.println("Error getting documents." + task.getException());
                }

/*
                int resid = R.drawable.respizza;
                System.out.println(resid);
                int resid2 = R.drawable.ressandwich;
                System.out.println(resid2);

                int resid3 = R.drawable.reslasagna;
                System.out.println(resid3);
                int resid4 = R.drawable.resccc;
                System.out.println(resid4);
                // finding out the id of the given pic
*/

                mMenuItemAdapter = new MenuItemAdapter(menuItems, listener);
                mItemList.setAdapter(mMenuItemAdapter);
            }
        });
        //---------------Firestore read end-----------------------------------------------------------------

    }


}
