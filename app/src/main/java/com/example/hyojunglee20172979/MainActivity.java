package com.example.hyojunglee20172979;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hyojunglee20172979.databinding.ActivityMainBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String filename;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        TedPermission.create().setPermissionListener(permissionlistener)
                .setDeniedMessage("Permissions are Denied.")
                .setPermissions(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

        SurfaceView surfaceView = new SurfaceView(this);

        surfaceHolder = surfaceView.getHolder();

        binding.container.addView(surfaceView);

        filename = this.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath()+"/recorded.mp4";
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }

    };


    public void startRecording(View view){

        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }

        if(mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();

        }

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        mediaRecorder.setOutputFile(filename);
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

        try{
            mediaRecorder.prepare();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    public void stopRecording(View view){

        if(mediaRecorder == null){
            return ;
        }

        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
        mediaRecorder = null;

    }

    public void startPlay(View view){
        if(mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
        }

        try{
            mediaPlayer.setDataSource(filename);
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void stopPlay(View view){

        if(mediaPlayer !=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}