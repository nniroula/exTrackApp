package com.noviceModDev.extrackapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HouseHoldAdapter extends RecyclerView.Adapter<HouseHoldAdapter.ListItemHolder>{
    /* Adapter class
        ContactAdapter.ListItemHolder means ContactAdapter is the class, ListItemHolder is an inner class
        It has to display all the contacts, use modal class */
    private MainActivity mainActivity;
    private ArrayList<HouseHoldItem> listOfItems; // bring in modal data as arraylist

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

    /* bind the household list item layout with our modal class */
    public void onBindViewHolder (HouseHoldAdapter.ListItemHolder holder, int position) {
        HouseHoldItem houseHoldItem = listOfItems.get(position); // modal class

        holder.textViewItemDescriptionDisplay.setText(houseHoldItem.getItemDescription()); // get from modal class
        holder.textViewItemCostDisplay.setText(houseHoldItem.getCostAmount());
    }

    /* recycler view wants to know how many items you want to display */
    public int getItemCount () {

        return  listOfItems.size();
    }

    /* Inner class, works like onCreate method */
    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewItemDescriptionDisplay;
        private TextView textViewItemCostDisplay;

        // Constructor
        public ListItemHolder(View itemView) {
            super(itemView);
            textViewItemDescriptionDisplay = itemView.findViewById(R.id.textViewHouseHoldItemDisplayDescription);
            textViewItemDescriptionDisplay.setClickable(true);
            textViewItemDescriptionDisplay.setOnClickListener(this);

            textViewItemCostDisplay = itemView.findViewById(R.id.textViewHouseHoldDisplayAmount);
            textViewItemCostDisplay.setClickable(true);
            textViewItemCostDisplay.setOnClickListener(this);
        }

        public void onClick(View view) {
            int pos = getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                mainActivity.onItemClick(pos);
            }
        }
    }
} // end class

