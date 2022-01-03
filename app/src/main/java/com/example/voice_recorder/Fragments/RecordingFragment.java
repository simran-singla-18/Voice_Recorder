package com.example.voice_recorder.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.voice_recorder.Adapters.ItemAdapter;
import com.example.voice_recorder.OnSelectListener;
import com.example.voice_recorder.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecordingFragment extends Fragment implements OnSelectListener {
  View view;
  RecyclerView recyclerView;
  List<File>fileList;
  ItemAdapter adapter;

    File path=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Recorder");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_recording_fragment,container,false);

        displayFiles();
        return view;
    }

    private void displayFiles() {
        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        fileList=new ArrayList<>();
        fileList.addAll(findFile(path));
        adapter=new ItemAdapter(getContext(),fileList,this);
        recyclerView.setAdapter(adapter);
    }
    public ArrayList<File> findFile(File file)
    {
       ArrayList<File> arrayList=new ArrayList<>();
       File[] files=file.listFiles();
       for (File singlefiles : files)
       {
           if(singlefiles.getName().toLowerCase().endsWith(".amr"))
               arrayList.add(singlefiles);
       }
        return arrayList;
    }

    @Override
    public void OnSelected(File file) {
        Uri uri= FileProvider.getUriForFile(getContext(),getContext().getApplicationContext().getPackageName() +".provider",file);
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"audio/x-wav");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getContext().startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            displayFiles();
        }
    }
}