package com.example.tonyshan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import java.util.ServiceConfigurationError;

/**
 * メイン画面
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    static final String TAG="MainActivity";
    /**
     * 画面を録画するボタン
     */
    private Button BUTTON_RECORD;
    /**
     * 画面を再生するボタン
     */
    private Button BUTTON_RECORDPLAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // メイン画面のレイアウトを設定
        setContentView(R.layout.activity_main);

    BUTTON_RECORD = (Button)findViewById(R.id.button1);

    BUTTON_RECORDPLAY = (Button)findViewById(R.id.button2);
    // ボタンをクリックのイベント
    BUTTON_RECORD.setOnClickListener(this);
    BUTTON_RECORDPLAY.setOnClickListener(this);

        Log.i(TAG,"onCreate");
    }

    /**
     * クリックイベント
     * @param v View
     */
    @Override
    public void onClick(View v) {
        if ( v == BUTTON_RECORD) {
            startService(new Intent(MainActivity.this,MyServices.class));
            finish();
            //Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
        } else if ( v== BUTTON_RECORDPLAY) {
            // Services停止
            stopService(new Intent(MainActivity.this,MyServices.class));
            // 画面を遷移
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,SubActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Unknown Button",Toast.LENGTH_SHORT).show();
        }

        HomeButtonReceive mHomeButtonReceive = new HomeButtonReceive();

        IntentFilter iFilter = new IntentFilter();

        iFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        this.registerReceiver(mHomeButtonReceive, iFilter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class HomeButtonReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Toast.makeText(getApplicationContext(),"HomeButton pushed!",Toast.LENGTH_SHORT).show();

        }
    }
}
