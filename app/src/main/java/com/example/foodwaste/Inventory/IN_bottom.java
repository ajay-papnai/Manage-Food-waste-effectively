package com.example.foodwaste.Inventory;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.foodwaste.Inventory.Inventory_item;
import com.example.foodwaste.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class IN_bottom extends BottomSheetDialogFragment {
    EditText item, qty, pdate, edate;
    Button add;
    Calendar calendar;
    Calendar purchaseDateCalendar;
    private InventoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_i_n_bottom, container, false);

        item = view.findViewById(R.id.b_item);
        qty = view.findViewById(R.id.b_qty);
        pdate = view.findViewById(R.id.p_date);
        edate = view.findViewById(R.id.e_date);
        add = view.findViewById(R.id.add);

        calendar = Calendar.getInstance();
        purchaseDateCalendar = Calendar.getInstance();
        edate.setVisibility(View.INVISIBLE);
        edate.setEnabled(false);  // Disable expiration date initially

        DatePickerDialog.OnDateSetListener purchaseDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                purchaseDateCalendar.set(Calendar.YEAR, year);
                purchaseDateCalendar.set(Calendar.MONTH, month);
                purchaseDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalendar(pdate, purchaseDateCalendar);
                edate.setVisibility(View.VISIBLE);
                edate.setEnabled(true);  // Enable expiration date after purchase date is set
            }
        };

        pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), purchaseDateListener, purchaseDateCalendar.get(Calendar.YEAR),
                        purchaseDateCalendar.get(Calendar.MONTH), purchaseDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener expirationDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (selectedDate.before(purchaseDateCalendar)) {
                    Toast.makeText(getActivity(), "Expiration date must be after purchase date", Toast.LENGTH_SHORT).show();
                } else {
                    updateCalendar(edate, selectedDate);
                }
            }
        };

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), expirationDateListener, purchaseDateCalendar.get(Calendar.YEAR),
                        purchaseDateCalendar.get(Calendar.MONTH), purchaseDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        viewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Item = item.getText().toString().trim();
                String QTY = qty.getText().toString().trim();
                String Pdate = pdate.getText().toString().trim();
                String Edate = edate.getText().toString().trim();

                if(!Item.isEmpty()  && !QTY.isEmpty()  && !Pdate.isEmpty()  & !Edate.isEmpty()){

                    Inventory_item inventoryItem = new Inventory_item(Item, QTY, Pdate, Edate);
                    viewModel.addInventoryItem(inventoryItem);
                    dismiss();
                }
                else{
                    Toast.makeText(getActivity(), "All fields are compulsary", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateCalendar(EditText editText, Calendar calendar) {
        String format = "dd-MM-yyyy";  // Correct date format
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        editText.setText(sdf.format(calendar.getTime()));
    }
}