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

                        if (id == R.id.action_save) {
                            String itemInputDescription = binding.textInputHouseholdItemDescription.getText().toString();
                            String itemInputDate = binding.textInputHouseholdDate.getText().toString();
                            String itemInputCost = binding.textInputHouseHoldAMount.getText().toString();

                            // database
                            Context context;
                            context = getContext();
                            assert context != null;
                            context.getApplicationContext();
                            db = new DBHandler(context);
                            db.insertDataIntoHouseHoldTable(itemInputDescription, itemInputDate, itemInputCost);
                            //String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            HouseHoldItem houseHoldItem = new HouseHoldItem (itemInputDescription, itemInputDate, itemInputCost);

                            MainActivity mainActivity = (MainActivity) getActivity();
                            assert mainActivity != null;
                            mainActivity.addHouseHoldItem (houseHoldItem);

                            // WOrks here
                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            binding.textViewDisplayHouseHoldTotalAmount.setText((String.valueOf(mainActivity.getHouseHoldTotalCost())));
                            //Log.d("info:", "MSG: action_save total " + mainActivity.getHouseHoldTotalCost());
                            //check this NO change impact
                            //String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            //Log.d("info:", "MSG: onAction_Save AddHouseHold "+ totalAmt);
                            //binding.textViewDisplayHouseHoldTotalAmount.setText("CODING FUN");

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
                            assert mainActivity != null;
                            totalHouseHoldCost = mainActivity.getHouseHoldTotalCost();
                           // DecimalFormat df = new DecimalFormat("#, ##0.00");
                           // formatedTotal = df.format(totalHouseHoldCost);
                           // binding.textViewDisplayHouseHoldTotalAmount.setText(formatedTotal);
                            // binding.textViewDisplayHouseHoldTotalAmount.setText(formatedTotal);
                            //Log.d("info:", "formatted num is ......" + df.format(sum));

                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();
                            //Log.d("info:", "MSG: in action_household "+ totalAmt);
                           // textViewDisplayHouseHoldTotalAmount;
                            TextView textViewDisplayHouseHoldTotalAmount;
                          //  textViewDisplayHouseHoldTotalAmount = binding.textViewDisplayHouseHoldTotalAmount;
                          //  textViewDisplayHouseHoldTotalAmount.setText(("FFFFFFFFF"));  // check this
                           // Log.d("info:", "MSG: display total __________________ " + totalHouseHoldCost);

                            dismiss();
                        }
                        //
                        else if(id == R.id.action_add){
                            String totalAmt = binding.textViewDisplayHouseHoldTotalAmount.getText().toString();

                           // Log.d("info: ", "MSG: action_add " + totalAmt);
                            binding.textViewDisplayHouseHoldTotalAmount.setText("FUUUUUN OF CODING");
                            MainActivity mainActivity = (MainActivity) getActivity();
                            assert mainActivity != null;
                            double sum = mainActivity.getHouseHoldTotalCost();
                            //Log.i("info", "MSG: action_add sum " + sum);
                            //binding.textViewDisplayHouseHoldTotalAmount.setText(sum);
                           // DecimalFormat df = new DecimalFormat("#, ##0.00");
                            // Log.d("info:", "formatted num is ......" + df.format(sum));
                            // binding.textViewDisplayHouseHoldTotalAmount.setText(df.format(sum));
                            //   binding.textViewDisplayHouseHoldTotalAmount.postInvalidate();
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

                        dismiss();
                    }
                }
        );
        return builder.create();
    }
}

