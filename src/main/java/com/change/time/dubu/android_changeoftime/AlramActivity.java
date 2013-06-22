package com.change.time.dubu.android_changeoftime;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dubu.android_changeoftime.R;

import java.util.*;

/**
 * User: kingkingdubu
 * Date: 13. 6. 20
 * Time: 오전 12:21
 */
public class AlramActivity extends Activity {

    private Handler mHandler;
    private Runnable mRunnable;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                runTime();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000);

    }

    private void runTime() {
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        int dayHour =  cal.get(Calendar.HOUR_OF_DAY);

        mHandler.postDelayed(mRunnable, 5000);
        if(dayHour%2 == 1 &&cal.get(Calendar.MINUTE) == 0  ){
            //vibrationFire(2);
            if(dayHour == 23 ||dayHour == 5 ||dayHour == 11 ||dayHour == 17) vibrationFire(0);
            if(dayHour == 1 ||dayHour == 7  ||dayHour == 13 ||dayHour == 19) vibrationFire(1);
            if(dayHour == 3 ||dayHour == 9  ||dayHour == 15 ||dayHour == 21) vibrationFire(2);
        }
        if(cal.get(Calendar.MINUTE) != 0  ){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    private void vibrationFire(int i) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        List<long[]> pats = new ArrayList<long[]>();
        pats.add(new long[]{1000L, 1000L,1000L, 1000L,1000L,1000L});
        pats.add(new long[]{500L, 500L,500L, 500L,500L, 500L});
        pats.add(new long[]{250L, 250L,250L, 250L,250L,250L, 250L});
        vibe.vibrate(pats.get(i), -1);
        unlockScreen();       // screen on!!
    }

    private void unlockScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.release();
    }
}
