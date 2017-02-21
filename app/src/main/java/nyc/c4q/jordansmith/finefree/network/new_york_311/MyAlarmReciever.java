package nyc.c4q.jordansmith.finefree.network.new_york_311;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jordansmith on 2/20/17.
 */

public class MyAlarmReciever extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "nyc.c4q.notificationdemo.alarm";

    // Triggered by the Alarm periodically (starts the notification service)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlternateSideParkingNotifierService.class);
        context.startService(i);
    }
}