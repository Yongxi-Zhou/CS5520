package edu.neu.numad22sp_yongxi;

import java.util.ArrayList;
import java.util.List;

public class MyApplication {
    private static ArrayList<ItemURL> itemList = new ArrayList<>();

    public static ArrayList<ItemURL> getItemList() {
        return itemList;
    }

    public static void setItemList(ArrayList<ItemURL> itemList) {
        MyApplication.itemList = itemList;
    }
}
