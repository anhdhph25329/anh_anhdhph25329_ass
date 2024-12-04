package fpt.anhdhph.anh_anhdhph25329_ass.config;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class AddNotifyConfig extends Application {

    public static final String CHANEL_ID = "ABC123456";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    void config() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Đặt tên cho chanel
            CharSequence name = "Kenh notify demo";
            // mo ta cho chanel
            String mota = "Noi dung mo ta cho notify";


            int do_uu_tien = NotificationManager.IMPORTANCE_DEFAULT;


            // am thanh
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                    .build();
            // dang ky notify
            NotificationChannel channel = new NotificationChannel(CHANEL_ID, name, do_uu_tien);
            channel.setDescription(mota);
            channel.setSound(uri, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }

    }
}
