package edu.neu.numad22sp_yongxi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class PopupDialog extends AppCompatDialogFragment {
    private EditText editName;
    private EditText editURL;
    private DialogListener listener;
    private RecyclerView recyclerView;
    LinkControllerPage context;
    Bundle savedInstanceState;
    private View mainView;

    public PopupDialog(RecyclerView recyclerView, LinkControllerPage context, Bundle savedInstanceState) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view  = inflater.inflate(R.layout.popup, null);

        builder.setView(view)
                .setTitle("Save URL")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editName.getText().toString();
                        String link = editURL.getText().toString();
                        if (name.equals("") || link.equals("") ) {
                            Snackbar snackbar = Snackbar.make(recyclerView,"name or link should not be empty",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            return;
                        }
                        if (!link.startsWith("https://")) {
                            Snackbar snackbar = Snackbar.make(recyclerView,"valid URL link should start with https://",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            return;
                        }
                        listener.applyTexts(name, link);
                        Snackbar snackbar = Snackbar.make(recyclerView,"Add an URL link to the list",Snackbar.LENGTH_SHORT);
                        snackbar.show();

                    }
                });

        editName = view.findViewById(R.id.et_name);
        editURL = view.findViewById(R.id.et_URL);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }

    }



}
