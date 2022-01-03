package com.example.voice_recorder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voice_recorder.R;

import java.text.BreakIterator;

public class ItemViewholder extends RecyclerView.ViewHolder {

    public  TextView txtName;
    public LinearLayout container;
    public ItemViewholder(@NonNull View itemView) {
        super(itemView);
        txtName=itemView.findViewById(R.id.txtName);
        container=itemView.findViewById(R.id.container);
    }
}
