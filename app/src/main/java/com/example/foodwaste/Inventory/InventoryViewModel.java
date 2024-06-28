package com.example.foodwaste.Inventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class InventoryViewModel extends ViewModel {

    private final MutableLiveData<List<Inventory_item>> inventoryLiveData;
    private final FirebaseFirestore firestore;
    private final FirebaseAuth firebaseAuth;

    public InventoryViewModel() {
        inventoryLiveData = new MutableLiveData<>(new ArrayList<>());
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            loadUserData();
        }
    }

    public LiveData<List<Inventory_item>> getInventoryLiveData() {
        return inventoryLiveData;
    }

    public void addInventoryItem(Inventory_item item) {
        List<Inventory_item> currentList = inventoryLiveData.getValue();

        // Check if item already exists in the list to avoid duplicates
        if (!currentList.contains(item)) {
            currentList.add(item);
            inventoryLiveData.setValue(currentList);
            saveItemToFirestore(item);
        }
    }

    private void loadUserData() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        firestore.collection("Users").document(userId).collection("Inventory")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Inventory_item> itemList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Inventory_item item = document.toObject(Inventory_item.class);
                            itemList.add(item);
                        }
                        inventoryLiveData.setValue(itemList);
                    } else {
                        // Handle error
                    }
                });
    }

    private void saveItemToFirestore(Inventory_item item) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        firestore.collection("Users").document(userId).collection("Inventory")
                .add(item)
                .addOnSuccessListener(documentReference -> {
                    // Optionally, handle success
                })
                .addOnFailureListener(e -> {
                    // Optionally, handle failure
                });
    }
}