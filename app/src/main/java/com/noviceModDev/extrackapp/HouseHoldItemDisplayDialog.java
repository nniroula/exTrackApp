package com.noviceModDev.extrackapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.noviceModDev.extrackapp.databinding.DialogDisplayHouseholdItemsBinding;

public class HouseHoldItemDisplayDialog extends DialogFragment {
    private DialogDisplayHouseholdItemsBinding binding;
    //private DialogAddHouseholdItemsBinding bindingAddHouseHoldDialog;
    private HouseHoldItem houseHoldItem;
    private int itemIndexInRecyclerView;
    private TextView textViewItemDescription;
    private TextView textViewItemPurchaseDate;
    private TextView textViewItemCost;

    // display total cost amount
   // private TextView textViewDisplayHouseHoldTotalAmount;

    public HouseHoldItemDisplayDialog( HouseHoldItem itemInfo, int itemIndexInArrayList) {
        this.houseHoldItem = itemInfo;
        this.itemIndexInRecyclerView = itemIndexInArrayList;
    }
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        binding = DialogDisplayHouseholdItemsBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        // grab the fields
        textViewItemDescription = binding.textViewHouseHoldItemDisplayDescription;
        textViewItemPurchaseDate = binding.textViewHouseHoldDisplayDate;
        textViewItemCost = binding.textViewHouseHoldDisplayAmount;

        // get the data values for the fields
        String itemName = houseHoldItem.getItemDescription();
        String itemDate = houseHoldItem.getPurchaseDate();
        String itemAmount = houseHoldItem.getCostAmount();

//        MainActivity mainActivityFragment = (MainActivity) getActivity();
//        mainActivityFragment.getHouseHoldTotalCost();
        //textViewDisplayHouseHoldTotalAmount = binding.textViewDisplayHouseHoldTotalAmount;
//        textViewDisplayHouseHoldTotalAmount  = bindingAddHouseHoldDialog.textViewDisplayHouseHoldTotalAmount;
//        textViewDisplayHouseHoldTotalAmount.setText((int) mainActivityFragment.getHouseHoldTotalCost());

        // set the values for fields
        textViewItemDescription.setText(itemName);
        textViewItemPurchaseDate.setText(itemDate);
        textViewItemCost.setText(itemAmount);

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            // implement delete functionality
            @Override
            public void onClick(View view) {
                MainActivity mainActivityFragment = (MainActivity) getActivity();
                assert mainActivityFragment != null;
//                mainActivityFragment.removeHouseHoldItem(itemIndexInRecyclerView);

                // remove from database
                DBHandler databaseHandler = new DBHandler(getContext());
                Log.d("info", "MSG: name is " + itemName);
                databaseHandler.deleteItemFromDataBase(itemName);
                mainActivityFragment.removeHouseHoldItem(itemIndexInRecyclerView);

                dismiss();
            }
        });
        return builder.create();
    }
}

