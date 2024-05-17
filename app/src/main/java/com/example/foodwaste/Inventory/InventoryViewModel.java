package com.example.foodwaste.Inventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class InventoryViewModel extends ViewModel {

    private MutableLiveData<List<Inventory_item>> inventoryLiveData;

    public LiveData<List<Inventory_item>> getInventoryLiveData(){
        if (inventoryLiveData == null) {
            inventoryLiveData = new MutableLiveData<>();

            inventoryLiveData.setValue(new ArrayList<>());
        }
        return inventoryLiveData;
    }

    public void addInventoryItem(Inventory_item item) {
        List<Inventory_item> currentList = inventoryLiveData.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(item);
        inventoryLiveData.setValue(currentList);
    }
}
