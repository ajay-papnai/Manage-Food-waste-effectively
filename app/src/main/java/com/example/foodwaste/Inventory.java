package com.example.foodwaste;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends Fragment {
    RecyclerView recyclerView ;
    FloatingActionButton floatingActionButton;

    List<Inventory_item> data ;

    public in_adapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventory, container, false);


        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        data = new ArrayList<>();

        data.add(new Inventory_item("Apple", "5", "2023-05-01", "2023-05-10"));
        data.add(new Inventory_item("Banana", "3", "2023-05-03", "2023-05-08"));

        data.add(new Inventory_item("Mango", "5", "2023-05-01", "2023-05-10"));
        data.add(new Inventory_item("Bread", "3", "2023-05-03", "2023-05-08"));

        data.add(new Inventory_item("Butter", "5", "2023-05-01", "2023-05-10"));
        data.add(new Inventory_item("Milk", "3", "2023-05-03", "2023-05-08"));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter =new in_adapter(getActivity() , data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}