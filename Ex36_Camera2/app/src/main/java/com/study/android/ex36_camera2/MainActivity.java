package com.study.android.ex36_camera2;
//하드웨어(카메라 기기에)를 직접.
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  SurfaceHolder.Callback{

    private static final String TAG ="lecture";

    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private Camera camera;
    boolean previewing =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //권한 체크 후 사용자에 의해 취소시 재요청

        if( ContextCompat.checkSelfPermission
                (this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
        }
    }
    public void onBtn1Clicked(View v){
        if(!previewing){
            camera = Camera.open();
            if(camera != null){
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.setDisplayOrientation(90);
                    camera.startPreview();
                }catch(IOException e){
                }
            }
        }
    }
    public void onBtn2Clicked(View v){
        if(camera != null && previewing){
            camera.stopPreview();
            camera.release();
            camera = null;
            previewing =false;

        }
    }
    public void onBtn3clicked(View v){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    public void surfaceCreated(SurfaceHolder holder){}
    public void surfaceChanged(SurfaceHolder holder ,int format, int width, int height){}
    public void surfaceDestroyed(SurfaceHolder holder){}
}
