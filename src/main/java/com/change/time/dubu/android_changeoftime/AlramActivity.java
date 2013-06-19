package com.change.time.dubu.android_changeoftime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.*;

/**
 * User: kingkingdubu
 * Date: 13. 6. 20
 * Time: 오전 12:21
 */
public class AlramActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar cal = Calendar.getInstance(Locale.KOREA);
        int dayHour =  cal.get(Calendar.HOUR_OF_DAY);

        String timeStr =cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 "
                + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 ";

        Toast toast = Toast.makeText(getApplicationContext(),timeStr, Toast.LENGTH_SHORT);
        toast.show();

        if(dayHour%2 == 1 &&cal.get(Calendar.MINUTE) == 0  ){
            if(dayHour == 23 ||dayHour == 5 ||dayHour == 11 ||dayHour == 17) vibrationFire(0);
            if(dayHour == 1 ||dayHour == 7  ||dayHour == 13 ||dayHour == 19) vibrationFire(1);
            if(dayHour == 3 ||dayHour == 9  ||dayHour == 15 ||dayHour == 21) vibrationFire(2);
        }

        if(dayHour%2 == 1 && cal.get(Calendar.MINUTE) == 0  ){
            unlockScreen();
        }
    }

    private void vibrationFire(int i) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        List<long[]> pats = new ArrayList<long[]>();
        pats.add(new long[]{1000L, 1000L,1000L, 1000L,1000L,1000L});
        pats.add(new long[]{500L, 500L,500L, 500L,500L, 500L});
        pats.add(new long[]{250L, 250L,250L, 250L,250L,250L, 250L});
        vibe.vibrate(pats.get(i), -1);
    }

    private void unlockScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.release();
    }
}
