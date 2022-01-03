package com.example.voice_recorder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voice_recorder.ItemViewholder;
import com.example.voice_recorder.OnSelectListener;
import com.example.voice_recorder.R;

import java.io.File;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewholder> {
    Context context;
    List<File>fileList;
    OnSelectListener listener;

    public ItemAdapter(Context context, List<File> fileList, OnSelectListener listener) {
        this.context = context;
        this.fileList = fileList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_item,parent,false);
        return new ItemViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewholder holder, int position) {
          holder.txtName.setText(fileList.get(position).getName());
          holder.txtName.setSelected(true);
          holder.container.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  listener.OnSelected(fileList.get(position));
              }
          });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }
}
