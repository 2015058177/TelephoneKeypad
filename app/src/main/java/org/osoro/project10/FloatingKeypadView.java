package org.osoro.project10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 2017-07-25.
 */

public class FloatingKeypadView extends Service {

    private static final String TAG = FloatingKeypadView.class.getSimpleName();

    private WindowManager mWindowManager;
    private View mFloatingView;
    private TextView textView;
    private ImageButton keypad0;
    private ImageButton keypad1;
    private ImageButton keypad2;
    private ImageButton keypad3;
    private ImageButton keypad4;
    private ImageButton keypad5;
    private ImageButton keypad6;
    private ImageButton keypad7;
    private ImageButton keypad8;
    private ImageButton keypad9;
    private ImageButton keypadstar;
    private ImageButton keypadsharp;
    String number="";
    ImageView temp;
    ConstraintLayout keypadLayout;
    TelephonyManager manager;

    private boolean active_flag = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent2){
        return null;
    }


    @Override
    public void onCreate(){
        super.onCreate();
        manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            manager.listen(new PhoneStateListener() {
                public void onCallStateChanged(int state, String incomingNumber) {
                    switch (state) {
                        case TelephonyManager.CALL_STATE_IDLE:
                            if (active_flag)
                                stopSelf();
                            Log.d(TAG, "call state idle");
                            break;
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            active_flag = true;
                            Log.d(TAG, "call state offhook");
                            break;
                        case TelephonyManager.CALL_STATE_RINGING:
                            Log.d(TAG, "call state ringing");
                        default:
                            break;
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }

        Log.d("debugging_", "onCreate is called");


        final WindowManager.LayoutParams params= new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity= Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.temp, null);
        temp = (ImageView) mFloatingView.findViewById(R.id.image);
        keypadLayout = (ConstraintLayout) mFloatingView.findViewById(R.id.keypad_layout);
        keypad0=(ImageButton)mFloatingView.findViewById(R.id.keypad0);
        keypad1=(ImageButton)mFloatingView.findViewById(R.id.keypad1);
        keypad2=(ImageButton)mFloatingView.findViewById(R.id.keypad2);
        keypad3=(ImageButton)mFloatingView.findViewById(R.id.keypad3);
        keypad4=(ImageButton)mFloatingView.findViewById(R.id.keypad4);
        keypad5=(ImageButton)mFloatingView.findViewById(R.id.keypad5);
        keypad6=(ImageButton)mFloatingView.findViewById(R.id.keypad6);
        keypad7=(ImageButton)mFloatingView.findViewById(R.id.keypad7);
        keypad8=(ImageButton)mFloatingView.findViewById(R.id.keypad8);
        keypad9=(ImageButton)mFloatingView.findViewById(R.id.keypad9);
        keypadstar=(ImageButton)mFloatingView.findViewById(R.id.keypadstar);
        keypadsharp=(ImageButton)mFloatingView.findViewById(R.id.keypadsharp);
        textView=(TextView)mFloatingView.findViewById(R.id.textView);

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keypadLayout.getVisibility() == View.VISIBLE)
                    keypadLayout.setVisibility(View.INVISIBLE);
                else
                    keypadLayout.setVisibility(View.VISIBLE);
            }
        });
        keypad0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="0";
                textView.setText(number);
            }
        });
        keypad1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="1";
                textView.setText(number);
            }
        });
        keypad2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="2";
                textView.setText(number);
            }
        });
        keypad3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="3";
                textView.setText(number);
            }
        });
        keypad4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="4";
                textView.setText(number);
            }
        });
        keypad5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="5";
                textView.setText(number);
            }
        });
        keypad6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="6";
                textView.setText(number);
            }
        });
        keypad7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="7";
                textView.setText(number);
            }
        });
        keypad8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="8";
                textView.setText(number);
            }
        });
        keypad9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="9";
                textView.setText(number);
            }
        });
        keypadsharp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="#";
                textView.setText(number);
            }
        });
        keypadstar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number+="*";
                textView.setText(number);
            }
        });

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


        mWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView,params);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mFloatingView!=null) mWindowManager.removeView(mFloatingView);
    }
}
