package edu.neu.numad22sp_yongxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkControllerPage extends AppCompatActivity implements PopupDialog.DialogListener {
    FloatingActionButton btn_add;
    Dialog mDialog;
    private TextView tv_user;
    private TextView tv_password;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemURL> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_controller_page);

        itemList = new ArrayList<>();
        tv_user = findViewById(R.id.tv_user);
        tv_password = findViewById(R.id.tv_password);
        btn_add = findViewById(R.id.btn_addFloating);
        mDialog = new Dialog(this);

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
                openDialog();
            }
        });
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private void openDialog() {
        PopupDialog popupDialog = new PopupDialog(this.getRecyclerView());
        popupDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String user, String password) {
        if (user.equals("") || password.equals("")) {
            return;
        }
        ItemURL curItem = new ItemURL(user, password);
        itemList.add(curItem);
        tv_user.setText(user);
        tv_password.setText(password);
    }

}