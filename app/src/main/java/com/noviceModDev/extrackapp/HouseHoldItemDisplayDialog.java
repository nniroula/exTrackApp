package com.noviceModDev.extrackapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;

import com.noviceModDev.extrackapp.databinding.DialogDisplayHouseholdItemsBinding;

public class HouseHoldItemDisplayDialog extends DialogFragment {
//}

//public class ContactViewDialog extends DialogFragment {
    //private DialogViewContactBinding binding;
    private DialogDisplayHouseholdItemsBinding binding;
//    private Contact contact;
    private HouseHoldItem houseHoldItem;
    private int itemIndexInRecyclerView; // to hold contact view holder position
    private TextView textViewItemDescription;
    private TextView textViewItemPurchaseDate;
    private TextView textViewItemCost;


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

        // set the values for fields
        textViewItemDescription.setText(itemName);
        textViewItemPurchaseDate.setText(itemDate);
        textViewItemCost.setText(itemAmount);

        //binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            // implement delete functionality
            @Override
            public void onClick(View view) {
                MainActivity mainActivityFragment = (MainActivity) getActivity();
                assert mainActivityFragment != null;
                mainActivityFragment.removeHouseHoldItem(itemIndexInRecyclerView);
                dismiss();
            }
        });
        return builder.create();
    }
}

