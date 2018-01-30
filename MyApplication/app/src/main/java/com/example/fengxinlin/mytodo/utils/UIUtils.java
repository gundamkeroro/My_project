package com.example.fengxinlin.mytodo.utils;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by fengxinlin on 1/28/18.
 */
public class UIUtils {

    public  static void setTextViewStrikeThrough(@NonNull TextView tv, boolean strikeThrough) {
        if (strikeThrough) {
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
