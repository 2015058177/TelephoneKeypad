package org.osoro.project10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by User on 2017-07-31.
 */

public class TelephonyState extends Activity{
    TelephonyManager manager;
    int a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            manager.listen(new PhoneStateListener() {
                public void onCallStateChanged(int state, String incomingNumber) {
                    switch (state) {
                        case TelephonyManager.CALL_STATE_IDLE:
                            a = 0;
                            break;
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            a = 2;
                            break;
                        default:
                            break;
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

}
