package edu.neu.numad22sp_yongxi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LinkControllerPage extends AppCompatActivity implements PopupDialog.DialogListener {
    FloatingActionButton btn_add;
    Dialog mDialog;
    private TextView tv_user;
    private TextView tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_controller_page);

        tv_user = findViewById(R.id.tv_user);
        tv_password = findViewById(R.id.tv_password);
        btn_add = findViewById(R.id.btn_addFloating);
        mDialog = new Dialog(this);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        PopupDialog popupDialog = new PopupDialog();
        popupDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String user, String password) {
        tv_user.setText(user);
        tv_password.setText(password);
    }

}