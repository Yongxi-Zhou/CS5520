package edu.neu.numad22sp_yongxi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkControllerPage extends AppCompatActivity implements DialogListener {
    FloatingActionButton btn_add;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemURL> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_controller_page);
        itemList = MyApplication.getItemList();
        Log.d(TAG, "onCreate: " + itemList.toString());
        Toast.makeText(this, "URLs count = " + itemList.size(), Toast.LENGTH_LONG).show();
        btn_add = findViewById(R.id.btn_addFloating);

        // bind recycleView id - Widget
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // render the dataSet
        myAdapter = new RecycleViewAdapter(itemList, this);
        recyclerView.setAdapter(myAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(savedInstanceState);
            }
        });
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private void openDialog(Bundle savedInstanceState) {
        PopupDialog popupDialog = new PopupDialog(this.getRecyclerView(), this, savedInstanceState);
        popupDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String name, String link) {
        ItemURL curItem = new ItemURL(name, link);
        itemList.add(curItem);
        // hot refresh
        recyclerView.setAdapter(new RecycleViewAdapter(itemList, this));
    }

}