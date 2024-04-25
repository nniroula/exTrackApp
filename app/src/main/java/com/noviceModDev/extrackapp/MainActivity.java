package com.noviceModDev.extrackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

//import edu.ucdenver.salimlakhani.phonebook.databinding.ActivityMainBinding;
//import com.noviceModDev.extrackapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //private ActivityMainBinding binding;
    private ArrayList<HouseHoldItem> listOfHouseHoldItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //  binding = ActivityMainBinding.inflate(getLayoutInflater());
        //  setContentView(binding.getRoot());
        //  setSupportActionBar(binding.toolbar);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // click on a category item under menu bar
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_add) {
        if (id == R.id.action_household) {
            AddHouseHoldItemDialog addContactDialog = new AddHouseHoldItemDialog();
            addContactDialog.show(getSupportFragmentManager(), "");
        }
        return true;
    }

    public void addHouseHoldItem (HouseHoldItem householdItem) {
        listOfHouseHoldItems.add(householdItem);
      //  HouseHoldAdapter.notifyDataSetChanged();
        //Log.i ("info", "Number of Contacts" + list.size());
    }

    // on item click
    public void onItemClick(int position) {
        if (position != RecyclerView.NO_POSITION) {
            HouseHoldItem houseHoldItem = listOfHouseHoldItems.get(position);
//            ContactViewDialog cvDialog = new ContactViewDialog(contact, position);
//            cvDialog.show(getSupportFragmentManager(), "");
            HouseHoldItemDisplayDialog houseHoldItemDisplayDialog = new HouseHoldItemDisplayDialog(houseHoldItem, position);
            houseHoldItemDisplayDialog.show(getSupportFragmentManager(), "");

        }
    }

    // remove item
    public void removeHouseHoldItem(int index){
        //list.remove(index);
        HouseHoldItem currentHouseHoldItem = listOfHouseHoldItems.get(index);
        listOfHouseHoldItems.remove(currentHouseHoldItem);
      //  HouseHoldAdapter.notifyDataSetChanged();
    }
}