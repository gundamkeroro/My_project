package com.example.fengxinlin.mytodo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by fengxinlin on 1/28/18.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "alarm on!", Toast.LENGTH_LONG).show();
    }
}
