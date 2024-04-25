package com.noviceModDev.extrackapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HouseHoldAdapter extends RecyclerView.Adapter<HouseHoldAdapter.ListItemHolder>{
//}

/*
    Adapter class
    ContactAdapter.ListItemHolder means ContactAdapter is the class, ListItemHolder is an inner class
    It has to display all the contacts, use modal class
*/
//public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ListItemHolder>  {
    private MainActivity mainActivity;
    private ArrayList<HouseHoldItem> listOfItems; // bring in conctact modal as arraylist

    // Constructor
    public HouseHoldAdapter (MainActivity mainActivity, ArrayList<HouseHoldItem> listOfItems) {
        this.mainActivity = mainActivity;
        this.listOfItems = listOfItems;
    }

    /* return the layout of the list */
    public HouseHoldAdapter.ListItemHolder onCreateViewHolder (ViewGroup parent, int viewType ){
        View listItem = LayoutInflater.from (parent.getContext())
                .inflate(R.layout.household_item_display_list_layout, parent, false);

        return new ListItemHolder(listItem);
    }

    /* bind the contact list item layout with our modal class */
    public void onBindViewHolder (HouseHoldAdapter.ListItemHolder holder, int position) {
        HouseHoldItem houseHoldItem = listOfItems.get(position); // Contact is modal class

        holder.textViewName.setText(houseHoldItem.getItemDescription()); // get name from modal class

        //phone
        holder.textViewPhone.setText(houseHoldItem.getCostAmount());
    }

    /* recycler view wants to know how many items you want to display */
    public int getItemCount () {

        return  listOfItems.size();
    }

    /* Inner class, works like onCreate method */
    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewPhone;

        // Constructor
        public ListItemHolder(View itemView) {
            super(itemView);
            //name
            textViewName = itemView.findViewById(R.id.textViewHouseHoldItemDisplayDescription);  // retrieve nameView by Id
            textViewName.setClickable(true);
            textViewName.setOnClickListener(this);

            //phone
//            textViewPhone = itemView.findViewById(R.id.textViewPhone);  // retrieve phoneView by Id
            textViewPhone = itemView.findViewById(R.id.textViewHouseHoldDisplayAmount);  // retrieve phoneView by Id
            textViewPhone.setClickable(true);
            textViewPhone.setOnClickListener(this);
        }

        public void onClick(View view) {
//            int pos = getAdapterPosition(); getAbsoluteAdapterPosition();
            int pos = getAbsoluteAdapterPosition();
            //Log.d("info", "MSG: adatper position is " + pos);
            if (pos != RecyclerView.NO_POSITION) {
                mainActivity.onItemClick(pos);
            }
        }
    }

} // end class

