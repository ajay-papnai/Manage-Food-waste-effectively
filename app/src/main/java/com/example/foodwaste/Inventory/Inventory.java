package com.example.foodwaste.Inventory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodwaste.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends Fragment {
    RecyclerView recyclerView ;
    FloatingActionButton floatingActionButton;

    List<Inventory_item> data ;

    public in_adapter adapter ;
    private InventoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventory, container, false);


        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        viewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);

        data = new ArrayList<>();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getInventoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<Inventory_item>>() {
            @Override
            public void onChanged(List<Inventory_item> inventoryItems) {
                adapter.setData(inventoryItems);
                adapter.notifyDataSetChanged();
            }
        });

        adapter =new in_adapter(getActivity() , data);
        recyclerView.setAdapter(adapter);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IN_bottom bottomsheet = new IN_bottom();
                bottomsheet.show(getActivity().getSupportFragmentManager(), "bottomsheet");
            }
        });



        return view;
    }
}