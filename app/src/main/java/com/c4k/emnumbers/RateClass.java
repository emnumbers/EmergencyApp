package com.c4k.emnumbers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

public final class RateClass {

    public static void RateApp(Context context)
    {
        String appPackageName =context.getPackageName();
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse("market://details?id=" + appPackageName));
            // we need to add this, because the activity is in a new context.
            // Otherwise the runtime will block the execution and throw an exception
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
        catch (ActivityNotFoundException ex)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
            // we need to add this, because the activity is in a new context.
            // Otherwise the runtime will block the execution and throw an exception
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }
}
