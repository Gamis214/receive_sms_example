package com.example.gamis214.gatdatafromsms_example;

import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static TextView txtSMS;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS
    };

    private static final int PERMISSIONS_ALL = 1;

    private Button btnActivateBroadcast;
    private int broad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSMS = (TextView) findViewById(R.id.textToPrint);
        btnActivateBroadcast = (Button) findViewById(R.id.btnActivateBroadcast);

        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, PERMISSIONS_ALL);

        btnActivateBroadcast.setOnClickListener(this);

        disableBradcast();

    }

    private void disableBradcast() {
        ComponentName receiver = new ComponentName(this, BroadCast.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void enableBroadcast(){
        ComponentName receiver = new ComponentName(this, BroadCast.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    static void setTextSMS(String sms){
        txtSMS.setText("msj: " + sms);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnActivateBroadcast:
                if(broad == 0){
                    btnActivateBroadcast.setText("Enable");
                    enableBroadcast();
                    broad = 1;
                }else if(broad != 0){
                    btnActivateBroadcast.setText("Disable");
                    disableBradcast();
                    broad = 0;
                }
                break;
        }
    }
}
