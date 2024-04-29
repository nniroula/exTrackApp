package com.noviceModDev.extrackapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import com.noviceModDev.extrackapp.databinding.DialogAddHouseholdItemsBinding;

import java.text.DecimalFormat;

public class AddHouseHoldItemDialog extends DialogFragment{
    // Use DialogFragment as container for the dialog

    private DialogAddHouseholdItemsBinding binding;
    private double totalHouseHoldCost;
    private String formatedTotal;

    // database handler
    DBHandler db;

    public Dialog onCreateDialog (Bundle savedInstanceState) { // this method creates alert dialog

        binding = DialogAddHouseholdItemsBinding.inflate(LayoutInflater.from(getContext()));
        // use AlertDialog subclass to instantiate the Dialog class
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.addHouseHoldItem.inflateMenu(R.menu.menu_add_household_item);


        binding.addHouseHoldItem.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        // after you click household under menu, this checks if id belongs to save, clear or exit button
                        Log.d("info", "MSG: menu item id is " + id );

                        if (id == R.id.action_save) {
//                            String name = binding.textInputName.getText().toString();
//                            String phone = binding.textInputPhone.getText().toString();
//                            String email = binding.textInputEmail.getText().toString();
                            String itemInputDescription = binding.textInputHouseholdItemDescription.getText().toString();
                            String itemInputDate = binding.textInputHouseholdDate.getText().toString();
                            String itemInputCost = binding.textInputHouseHoldAMount.getText().toString();

                            Log.d("info:", "MSG: SAVING >>>>>>>>>>" + itemInputDescription);
                            Log.d("info:", "MSG: SAVING >>>>>>>>>>" + itemInputDate);
                            Log.d("info:", "MSG: SAVING >>>>>>>>>>" + itemInputCost);

                            // database
                            Context context;
                            context = getContext();
                            context.getApplicationContext();
                            db = new DBHandler(context);
                            // db.addNewItem("jj", "12", "kk", "402");
                           // db.insertData(itemInputDescription);
                            db.insertDataIntoHouseHoldTable(itemInputDescription, itemInputDate, itemInputCost);
                            //String q = "SELECT name from first_table where id = 1";
                            //db.retrieveData();
                            //String getVal = "select * from household";
                            //Toast.makeText(this, "data saved in DB", Toast.LENGTH_LONG).show();


                            // text total input binding
                            //String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();

                            HouseHoldItem houseHoldItem = new HouseHoldItem (itemInputDescription, itemInputDate, itemInputCost);

                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.addHouseHoldItem (houseHoldItem);

                            // save to database
//                            mainActivity.handleDB(itemInputDescription, itemInputDate, itemInputCost);
                           // mainActivity.handleDB("NK", "2/2/24", "10", "45");

                            // CHECK IF IT WORKS

                            // WOrks here
                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            binding.textViewDisplayHouseHoldTotalAmount.setText((String.valueOf(mainActivity.getHouseHoldTotalCost())));
                            Log.d("info:", "MSG: action_save total " + (String.valueOf(mainActivity.getHouseHoldTotalCost())));

                            //check this NO change impact
                            //String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            Log.d("info:", "MSG: onAction_Save AddHouseHold "+ totalAmt);
                            binding.textViewDisplayHouseHoldTotalAmount.setText("CODING FUN");

                            dismiss();

                        }
                        else if (id == R.id.action_clear) {
                            binding.textInputHouseholdItemDescription.setText("");
                            binding.textInputHouseholdDate.setText("");
                            binding.textInputHouseHoldAMount.setText("");

                        }
                        //Check this one as well NOT SURE
                        else if(id == R.id.action_household){
                                MainActivity mainActivity = (MainActivity) getActivity();
                                //mainActivity.addHouseHoldItem (houseHoldItem);
                                totalHouseHoldCost = mainActivity.getHouseHoldTotalCost();
                            DecimalFormat df = new DecimalFormat("#, ##0.00");
                            formatedTotal = df.format(totalHouseHoldCost);
                            binding.textViewDisplayHouseHoldTotalAmount.setText(formatedTotal);
                            //Log.d("info:", "formatted num is ......" + df.format(sum));

                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            Log.d("info:", "MSG: onMenu AddHouseHold "+ totalAmt);


                           // textViewDisplayHouseHoldTotalAmount;
                            TextView textViewDisplayHouseHoldTotalAmount;
//                            textViewDisplayHouseHoldTotalAmount = binding.textViewDisplayHouseHoldTotalAmount;
//                            textViewDisplayHouseHoldTotalAmount.setText(("FFFFFFFFF"));  // check this
                            Log.d("info:", "MSG: display total __________________ " + totalHouseHoldCost);

                                dismiss();

                        }
                        //
                        else if(id == R.id.action_add){
                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();

                            Log.d("info: ", "MSG: action_add " + totalAmt);
                            binding.textViewDisplayHouseHoldTotalAmount.setText("GOOOOOOOOOD");
//                            MainActivity mainActivity = (MainActivity) getActivity();
//                            assert mainActivity != null;
//                            double sum = mainActivity.getHouseHoldTotalCost();
//                            DecimalFormat df = new DecimalFormat("#, ##0.00");
//                          Log.d("info:", "formatted num is ......" + df.format(sum));
//                            binding.textViewDisplayHouseHoldTotalAmount.setText(formatedTotal);
//                            binding.textViewDisplayHouseHoldTotalAmount.postInvalidate();

                        }
                        //
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

                        // CHECK IF IT WORKS??
                        // when clicked saved NEW ADDITION   CHECK IT
//                        String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
//                        Log.d("info:", "MSG: onClick AddHouseHold "+ totalAmt);
//                        binding.textViewDisplayHouseHoldTotalAmount.setText((String.valueOf(mainActivity.getHouseHoldTotalCost())));

                        dismiss();
                    }
                }
        );

        return builder.create();
    }
}

