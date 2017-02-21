package nyc.c4q.jordansmith.finefree.network.new_york_311;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import nyc.c4q.jordansmith.finefree.ActivityMain;
import nyc.c4q.jordansmith.finefree.R;
import nyc.c4q.jordansmith.finefree.model.model311.Item_;
import nyc.c4q.jordansmith.finefree.model.model311.Response311;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jordansmith on 2/20/17.
 */

public class AlternateSideParkingNotifierService extends IntentService {
    int NOTIFICATION_ID = 555;
    private static final String SERVICE_NAME = "notification-service";


    public AlternateSideParkingNotifierService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        intent = new Intent(this, ActivityMain.class);
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);


        NewYork311Client.getInstance()
                .get311Response("a42dde63", "002d0f87fab612ca6c4d1326b50172b0")
                .enqueue(new Callback<Response311>() {
                    @Override
                    public void onResponse(Call<Response311> call, Response<Response311> response) {
                        Response311 response311 = response.body();
                        Item_ parkingItem = response311.getItems().get(0).getItems().get(0);
                        if(parkingItem.getStatus().equals("SUSPENDED")){
                            makeNotification(true, pendingIntent, parkingItem.getDetails());
                        }
                        else {
                            makeNotification(false, pendingIntent, parkingItem.getDetails());
                        }
                    }

                    @Override
                    public void onFailure(Call<Response311> call, Throwable t) {

                    }
                });

    }

    private void makeNotification(Boolean isParkingSuspended, PendingIntent pendingIntent,String details){
        String parkingMessage;
        String parkingTitle;
        if(isParkingSuspended){
            parkingMessage = details;
            parkingTitle = "Rest Easy";
        }
        else{
            parkingMessage = "Alternate Side of the Street Parking rules are in effect!";
            parkingTitle = "Park Carefully";
        }


        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_car_black_36dp)
                .setContentTitle(parkingTitle)
                .setContentText(parkingMessage)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
