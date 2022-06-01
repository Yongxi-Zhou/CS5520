package edu.neu.numad22sp_yongxi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name;
    TextView tv_URL;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_URL = itemView.findViewById(R.id.tv_URL);
    }

    public void bindThisData(ItemURL thePersonToBind, Context context) {
        // sets the name of the ItemURL to the name textview of the viewholder.
        tv_name.setText(thePersonToBind.getLinkName());
        // sets the URL of the ItemURL to the URL textview of the viewholder.
        tv_URL.setText(thePersonToBind.getLinkURL());
        tv_URL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String URL = tv_URL.getText().toString();
                intent.setData(Uri.parse(URL));
                context.startActivity(intent);
            }
        });
    }
}
