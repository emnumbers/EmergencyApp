package com.c4k.emnumbers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class CallPhoneClass {
    private Context context;
     private  final int REQUEST_PHONE_CALL = 9867;
     private  final String CALL_PERMISSION = Manifest.permission.CALL_PHONE;
    CallPhoneClass(Context context) {
        this.context = context;
    }

    public void MakePhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, CALL_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{CALL_PERMISSION}, REQUEST_PHONE_CALL);
                return;
            }
        }
        context.startActivity(callIntent);
    }
}