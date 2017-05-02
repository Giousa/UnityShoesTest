package com.zmm.unityshoestest;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mInstance;
    private PermanentUnityPlayer mUnityPlayer;
    private float x = 10;
    private float y = -2;
    private float z = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        initView();
    }

    private void initView() {
        FrameLayout unityFrameLayout = (FrameLayout) findViewById(R.id.unity_framelayout);
        Button left = (Button) findViewById(R.id.btn_left);
        Button right = (Button) findViewById(R.id.btn_right);
        mUnityPlayer = new PermanentUnityPlayer(this);
        unityFrameLayout.addView(mUnityPlayer);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUnityPlayer != null){
                    GetAngleXYZ getAngleXYZ = new GetAngleXYZ(x--, y--, z--);
                    String s = JSON.toJSONString(getAngleXYZ);
                    Log.d("TAG","json = "+s);
                    mUnityPlayer.UnitySendMessage("scripts", "rotateLeft", s);
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUnityPlayer != null){
                    GetAngleXYZ getAngleXYZ = new GetAngleXYZ(x++, y++, z++);
                    String s = JSON.toJSONString(getAngleXYZ);
                    Log.d("TAG","json = "+s);
                    mUnityPlayer.UnitySendMessage("scripts", "rotateRight", s);
                }
            }
        });


    }

    private void unityGameStart(){
        Log.d("TAG","unity game start");
    }

    private void unityLeftRotate(){
        Log.d("TAG","unity game left");
    }

    private void unityRightRotate(){
        Log.d("TAG","unity game right");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnityPlayer != null) {
            this.mUnityPlayer.quit();
            this.mUnityPlayer = null;
        }

        if(mInstance != null){
            mInstance = null;
        }
    }

    public void onConfigurationChanged(Configuration var1) {
        super.onConfigurationChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.configurationChanged(var1);
    }

    public void onWindowFocusChanged(boolean var1) {
        super.onWindowFocusChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.windowFocusChanged(var1);
    }

    public boolean dispatchKeyEvent(KeyEvent var1) {
        return var1.getAction() == 2 ? this.mUnityPlayer.injectEvent(var1) : super.dispatchKeyEvent(var1);
    }

    public boolean onKeyUp(int var1, KeyEvent var2) {

        return this.mUnityPlayer.injectEvent(var2);
    }


    public boolean onKeyDown(int var1, KeyEvent var2) {
        return this.mUnityPlayer.injectEvent(var2);
    }

    public boolean onTouchEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }

    public boolean onGenericMotionEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }
}
