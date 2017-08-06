package org.osoro.project10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
/**
 * Created by User on 2017-07-25.
 */

public class FloatingView extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;



    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();

        final WindowManager.LayoutParams params= new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity= Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.view_floating_head,null);
        mFloatingView.setOnTouchListener(new View.OnTouchListener(){
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        initialX= params.x;
                        initialY= params.y;

                        initialTouchX=event.getRawX();
                        initialTouchY=event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        params.x=initialX+(int)(event.getRawX()-initialTouchX);
                        params.y=initialY+(int)(event.getRawY()-initialTouchY);

                        mWindowManager.updateViewLayout(mFloatingView,params);
                        break;
                }
                return  false;
            }
        });

        mFloatingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FloatingView.this, FloatingKeypadService.class));
            }
        });

        mWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView,params);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if(mFloatingView!=null) mWindowManager.removeView(mFloatingView);
    }

}
