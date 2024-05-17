package com.example.foodwaste.Cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodwaste.Cart.sh_adapter;
import com.example.foodwaste.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Shopping extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    List<String> data;

    public sh_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        data = new ArrayList<>();
        data.add("milk");
        data.add("rice");
        data.add("wheat");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new sh_adapter(getActivity(), data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}