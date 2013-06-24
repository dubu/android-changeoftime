package com.change.time.dubu.android_changeoftime;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * User: kingkingdubu
 * Date: 13. 6. 25
 * Time: 오전 12:46
 */
public class AlramService extends Service {

    private Handler mHandler;
    private Runnable mRunnable;
    private Runnable mRunnableUnlock;
    private Runnable mRunnableClose;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        mRunnable = new Runnable() {
            @Override
            public void run() {
                runTime();
            }
        };

        mRunnableUnlock= new Runnable() {
            @Override
            public void run() {
                runTimeUnlock();
            }
        };

        mRunnableClose= new Runnable() {
            @Override
            public void run() {
                runTimeClose();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 0);
        mHandler.postDelayed(mRunnableUnlock, 5000);
        mHandler.postDelayed(mRunnableClose, 1000 * 30 );
    }

    @Override
    public void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        mHandler.removeCallbacks(mRunnableUnlock);
        super.onDestroy();
    }

    private void runTimeUnlock() {
        mHandler.postDelayed(mRunnableUnlock, 100);
//        unlockScreen();
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        if(cal.get(Calendar.MINUTE) == 0  ){
            unlockScreen();
        }
    }

    private void runTimeClose() {
        stopService(new Intent("com.change.time.dubu.android_changeoftime"));
    }

    private void runTime() {
        mHandler.postDelayed(mRunnable, 10000);
        //vibrationFire(2);
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        int dayHour =  cal.get(Calendar.HOUR_OF_DAY);
        if(dayHour%2 == 1 &&cal.get(Calendar.MINUTE) == 0  ){
            if(dayHour == 23 ||dayHour == 5 ||dayHour == 11 ||dayHour == 17) vibrationFire(0);
            if(dayHour == 1 ||dayHour == 7  ||dayHour == 13 ||dayHour == 19) vibrationFire(1);
            if(dayHour == 3 ||dayHour == 9  ||dayHour == 15 ||dayHour == 21) vibrationFire(2);
        }
    }
    private void vibrationFire(int i) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        List<long[]> pats = new ArrayList<long[]>();
        pats.add(new long[]{1000L, 1000L,1000L, 1000L,1000L,1000L});
        pats.add(new long[]{1000, 200, 1000, 2000, 1200});
        pats.add(new long[]{250L, 250L,250L, 250L,250L,250L, 250L});
        vibe.vibrate(pats.get(i), -1);
        // screen on!!
    }

    private void unlockScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.release();
    }
}