package com.noviceModDev.extrackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.noviceModDev.extrackapp.databinding.ActivityMainBinding;
import com.noviceModDev.extrackapp.databinding.DialogAddHouseholdItemsBinding;
import com.noviceModDev.extrackapp.databinding.DialogShowHouseholdTotalBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<HouseHoldItem> listOfHouseHoldItems;
    // need household adapter
    private HouseHoldAdapter houseHoldAdapter;

    private DialogAddHouseholdItemsBinding bindingAddHouseHoldDialog;
    private DialogShowHouseholdTotalBinding bindingShowHouseHoldTotal;

    private TextView textViewDisplayHouseHoldTotalAmount;

    // database
    //SimpleDataBaseHandler db;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
        //   Toolbar myToolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(myToolbar);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

          // set the values with Recyclerview, using professor's code
        listOfHouseHoldItems = new ArrayList<HouseHoldItem>();  // HouseHoldItem modal
        houseHoldAdapter = new HouseHoldAdapter(this, listOfHouseHoldItems);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        /*
        Get and display household content from household content xml file on main activity screen. Put that
        file under include tag in activity_main.xml file
        */
        binding.hosueHoldContentXMLfile.recyclerView.setLayoutManager(layoutManager);
        binding.hosueHoldContentXMLfile.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.hosueHoldContentXMLfile.recyclerView.setAdapter(houseHoldAdapter);



        // textView to display total
        textViewDisplayHouseHoldTotalAmount = findViewById(R.id.textViewDisplayHouseHoldTotalAmount);

        // Database
        db = new DBHandler(this);
       // db.addNewItem("jj", "12", "kk", "402");
       // db.insertData("NK");
        Toast.makeText(this, "data saved in DB", Toast.LENGTH_LONG).show();

        } // End onCreate method


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        //
        double total = getHouseHoldTotalCost();
        Log.d("info:", "MSG: total is Main Activity HERE ******" + String.valueOf(total));
        //

        return true;
    }

    // click on a category item under menu bar
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_household) {

            //
            TextView textViewDisplayHouseHoldTotalAmount = findViewById(R.id.textViewDisplayHouseHoldTotalAmount);
           // textViewDisplayHouseHoldTotalAmount.setText((int) getHouseHoldTotalCost());
            //textViewDisplayHouseHoldTotalAmount.setText("HAHHAAAH");
//            textViewDisplayHouseHoldTotalAmount.postInvalidate();

//             for displaying add household item dialog box, works fine WORKS GREATE
            AddHouseHoldItemDialog addHouseHoldItemDialog = new AddHouseHoldItemDialog();
            addHouseHoldItemDialog.show(getSupportFragmentManager(), "");

//            double total = getHouseHoldTotalCost();
//            Log.d("info:", "MSG: total is Main Activity MENU ******" + String.valueOf(total));


//            // NEW concept, works fine as well
//            Log.d("info:", "MSG: onOptionsItemSelected Main activity file");
//            Intent intent = new Intent(this, HouseHoldShowTotalActivity.class);
//            intent.putExtra("SUM", getHouseHoldTotalCost());
//            startActivity(intent);


//            ShowHouseHoldTotal showHHTotal = new ShowHouseHoldTotal();
//            showHHTotal.show(getSupportFragmentManager(), "");

        }
        // signup clicked
        if(id == R.id.action_signup){
            Intent intent = new Intent(this, SignUpActivity.class);
            //intent.putExtra("SUM", getHouseHoldTotalCost());
            //startActivity(intent);
            startActivity(intent);
        }

        return true;
    }

    public void addHouseHoldItem (HouseHoldItem householdItem) {
        listOfHouseHoldItems.add(householdItem);
        houseHoldAdapter.notifyDataSetChanged();

        // **********************
//        listOfHouseHoldItems.get(0).getCostAmount();
//        Log.d("info", "MSG: item cost is " + listOfHouseHoldItems.get(0).getCostAmount());

        getHouseHoldTotalCost();
        // *******************************************
//        // add display total amount
//        TextView textViewDisplayHouseHoldTotalAmount  = bindingAddHouseHoldDialog.textViewDisplayHouseHoldTotalAmount;
////        textViewDisplayHouseHoldTotalAmount.setText((int) mainActivityFragment.getHouseHoldTotalCost());
//        textViewDisplayHouseHoldTotalAmount.setText((int) getHouseHoldTotalCost());


    }

    // on item click
    public void onItemClick(int position) {
        if (position != RecyclerView.NO_POSITION) {
            HouseHoldItem houseHoldItem = listOfHouseHoldItems.get(position);
            HouseHoldItemDisplayDialog houseHoldItemDisplayDialog = new HouseHoldItemDisplayDialog(houseHoldItem, position);
            houseHoldItemDisplayDialog.show(getSupportFragmentManager(), "");
        }
    }

    // remove item
    public void removeHouseHoldItem(int index){
        HouseHoldItem currentHouseHoldItem = listOfHouseHoldItems.get(index);
        listOfHouseHoldItems.remove(currentHouseHoldItem);
        houseHoldAdapter.notifyDataSetChanged();
    }

    // show house hold total
    public void showHouseHoldItem (HouseHoldItem householdItem) {
//        listOfHouseHoldItems.add(householdItem);
//        houseHoldAdapter.notifyDataSetChanged();
        listOfHouseHoldItems.add(householdItem);
        houseHoldAdapter.notifyDataSetChanged();
    }

    // get total cost
//    public double getHouseHoldTotalCost () {
    public double getHouseHoldTotalCost () {
//        listOfHouseHoldItems.add(householdItem);
//        houseHoldAdapter.notifyDataSetChanged();
        double sum = 0.0;
        for(int i = 0; i< listOfHouseHoldItems.size(); i++){
            sum = sum + Double.parseDouble(listOfHouseHoldItems.get(i).getCostAmount());
        }


        Log.d("info", "MSG: HH item cost in Main Activity getHouseHoldTotalCost method sum is " + sum);
        // Decimal formatter
//        DecimalFormat df = new DecimalFormat("#, ##0.00");
//        Log.d("info:", "formatted num is ......" + df.format(sum));
        //String sumValue = df.format(sum);


//       TextView textViewDisplayHouseHoldTotalAmount = findViewById(R.id.textViewDisplayHouseHoldTotalAmount);
//
//        textViewDisplayHouseHoldTotalAmount.setText(df.format(sum));

        return sum;
    }

    // database handler
//    public void handleDB(String itemDescription, String date, String cost, String total){
//        DBHandler dbHandler = new DBHandler(MainActivity.this);
//        dbHandler.addNewItem(itemDescription, date, cost, total);
//    }

}