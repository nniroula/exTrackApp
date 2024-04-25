package com.noviceModDev.extrackapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import com.noviceModDev.extrackapp.databinding.DialogAddHouseholdItemsBinding;

public class AddHouseHoldItemDialog extends DialogFragment{
    // Use DialogFragment as container for the dialog

    private DialogAddHouseholdItemsBinding binding;

    public Dialog onCreateDialog (Bundle savedInstanceState) { // this method creates alert dialog

        binding = DialogAddHouseholdItemsBinding.inflate(LayoutInflater.from(getContext()));
        // use AlertDialog subclass to instantiate the Dialog class
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
//        binding.addHouseHoldItem.inflateMenu(R.menu.menu_add_contact);
        binding.addHouseHoldItem.inflateMenu(R.menu.menu_add_household_item);

        binding.addHouseHoldItem.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_save) {
//                            String name = binding.textInputName.getText().toString();
//                            String phone = binding.textInputPhone.getText().toString();
//                            String email = binding.textInputEmail.getText().toString();
                            String itemInputDescription = binding.textInputHouseholdItemDescription.getText().toString();
                            String itemInputDate = binding.textInputHouseholdDate.getText().toString();
                            String itemInputCost = binding.textInputHouseHoldAMount.getText().toString();

                            HouseHoldItem houseHoldItem = new HouseHoldItem (itemInputDescription, itemInputDate, itemInputCost);

                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.addHouseHoldItem (houseHoldItem);

                            dismiss();

                        }
                        else if (id == R.id.action_clear) {
                            binding.textInputHouseholdItemDescription.setText("");
                            binding.textInputHouseholdDate.setText("");
                            binding.textInputHouseHoldAMount.setText("");

                        }
                        else if (id == R.id.action_exit) {
                            dismiss();
                        }

                        return false;
                    }
                }
        );

        binding.buttonMainMenu.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        dismiss();
                    }
                }
        );

        binding.buttonClear.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        binding.textInputHouseholdItemDescription.setText("");
                        binding.textInputHouseholdDate.setText("");
                        binding.textInputHouseHoldAMount.setText("");

                    }
                }
        );

        binding.buttonSave.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {

                        String inputDescription = binding.textInputHouseholdItemDescription.getText().toString();
                        String inputDate = binding.textInputHouseholdDate.getText().toString();
                        String inputAmount = binding.textInputHouseHoldAMount.getText().toString();

                        HouseHoldItem houseHoldItem = new HouseHoldItem (inputDescription, inputDate, inputAmount);

                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.addHouseHoldItem (houseHoldItem);

                        dismiss();
                    }
                }
        );

        return builder.create();
    }
}

