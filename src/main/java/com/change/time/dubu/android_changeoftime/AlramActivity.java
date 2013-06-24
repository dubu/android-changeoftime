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

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService(new Intent("com.change.time.dubu.android_changeoftime"));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
