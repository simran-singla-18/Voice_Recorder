package com.example.voice_recorder.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voice_recorder.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecorderFragment extends Fragment {

    View view;
    ImageButton btnRec;
    ImageView imageView;
    TextView textStatus;
    Chronometer timeRec;
    boolean isRecording;

    private static  String fileName;
    private MediaRecorder recorder;

    File path=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Recorder");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.activity_recorder_fragment,container,false);
       btnRec=view.findViewById(R.id.play);
       imageView=view.findViewById(R.id.image);
       textStatus=view.findViewById(R.id.text);
       timeRec=view.findViewById(R.id.time);

       isRecording=false;
       askruntimepermission();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String date=format.format(new Date());

        fileName=path+"/recording_"+date+".amr";
        if(!path.exists())
        {
            path.mkdirs();
        }

        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRecording)
                {
                    try
                        {
                            startRecording();
                            timeRec.setBase(SystemClock.elapsedRealtime());
                            timeRec.start();
                            textStatus.setText("Recording...");
                            btnRec.setImageResource(R.drawable.ic_stop);
                            isRecording=true;
                      }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        //Toast.makeText(getContext(),"Couldn't Record",Toast.LENGTH_LONG).show();
                    }

                }
                else if(isRecording)
                {
                    stopRecording();
                    timeRec.setBase(SystemClock.elapsedRealtime());
                    timeRec.stop();
                    textStatus.setText("");
                    btnRec.setImageResource(R.drawable.ic_radio);
                    isRecording=false;
                }
            }
        });
       return view;
    }

    private void startRecording()  {
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
           recorder.start();

    }
    private void stopRecording()
    {
        recorder.stop();
        recorder.release();
        recorder=null;
    }


    private void askruntimepermission() {
        Dexter.withContext(getContext()).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        Toast.makeText(getContext(),"Granted",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

}