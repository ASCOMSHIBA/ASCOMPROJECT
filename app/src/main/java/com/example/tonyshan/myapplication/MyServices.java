package com.example.tonyshan.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tonyshan on 15/07/18.
 */
public class MyServices extends Service{
    static final String TAG="LocalService";
    private final IBinder mBinder = new MyServiceLocalBinder();

    @Override
    public void onCreate() {

        Log.i(TAG, "Services: onCreate");
        Toast.makeText(this, "MyService#onCreate", Toast.LENGTH_SHORT).show();

        HomeButtonReceive mHomeButtonReceive = new HomeButtonReceive();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        this.registerReceiver(mHomeButtonReceive, iFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand Received start id " + startId + ": " + intent);
        Toast.makeText(this, "MyService#onStartCommand", Toast.LENGTH_SHORT).show();
        //明示的にサービスの起動、停止が決められる場合の返り値
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyService#onBind"+ ": " + intent, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onBind" + ": " + intent);
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        Toast.makeText(this, "MyService#onDestroy", Toast.LENGTH_SHORT).show();
    }

    //サービスに接続するためのBinder
    public class MyServiceLocalBinder extends Binder {
        //サービスの取得
        MyServices getService() {
            return MyServices.this;
        }
    }

    public class HomeButtonReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Toast.makeText(getApplicationContext(),"HomeButton pushed!",Toast.LENGTH_SHORT).show();

        }
    }

}
