package com.yinghuizou.vocabulary_journey;

/**
 * Created by yinghuizou on 7/30/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;
import com.yinghuizou.vocabulary_journey.UserAreaActivity;
public class AlertReceiver extends BroadcastReceiver {
    private final int _id = (int) System.currentTimeMillis();

    Intent intentc ;
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Time is up!", Toast.LENGTH_SHORT).show();

        NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent newintent = new Intent(context,UserAreaActivity.class);
        PendingIntent pendingIntents =PendingIntent.getBroadcast(context,_id, newintent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifications = new NotificationCompat.Builder(context);
        notifications.setContentIntent(pendingIntents);
        notifications.setSmallIcon(R.drawable.vj);
        notifications.setTicker("Vocabulary Journey Remainder");
        notifications.setContentTitle("Are you ready to review!!!");
        notifications.setContentText("Time to Review the Vocab");
        notifications.setAutoCancel(true);
        nm.notify(_id,notifications.build());

    }
}




