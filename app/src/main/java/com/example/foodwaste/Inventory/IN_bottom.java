package com.example.foodwaste.Inventory;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodwaste.Inventory.Inventory_item;
import com.example.foodwaste.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class IN_bottom extends BottomSheetDialogFragment {

    EditText item , qty , pdate , edate ;
    Button add;
    List<Inventory_item> data ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_i_n_bottom,container,false);

        item = view.findViewById(R.id.b_item);
        qty = view.findViewById(R.id.b_qty);
        pdate = view.findViewById(R.id.p_date);
        edate = view.findViewById(R.id.e_date);
        add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Item  = item.getText().toString().trim();
                String QTY  = qty.getText().toString().trim();
                String Pdate  = pdate.getText().toString().trim();
                String Edate  = edate.getText().toString().trim();

                data =  new ArrayList<>();
                data.add(new Inventory_item(Item , QTY , Pdate , Edate));

                dismiss();
            }
        });




        return view;
    }

    private void add(){

    }
}