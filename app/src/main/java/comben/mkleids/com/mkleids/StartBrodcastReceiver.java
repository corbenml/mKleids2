package comben.mkleids.com.mkleids;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartBrodcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context,getContactService.class);
        context.startService(serviceIntent);
        Log.d("BrodcaseReceiver","Auto start service from BroadcastReceived!");
    }
}
