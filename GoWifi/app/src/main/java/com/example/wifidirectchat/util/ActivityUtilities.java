package com.example.wifidirectchat.util;

import android.app.Activity;
import android.os.Build;
import android.widget.TextView;
import com.example.wifidirectchat.R;;

public class ActivityUtilities {

	public static void customiseActionBar(Activity activity)
    {
        int titleId = 0;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            titleId = activity.getResources().getIdentifier("action_bar_title", "id", "android");
        else
            titleId = R.id.chatName;

        if(titleId>0){
            TextView titleView = (TextView) activity.findViewById(titleId);
            titleView.setTextSize(22);
        }
    }
	
}
