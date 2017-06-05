package com.murach.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ReminderActivity extends Activity implements OnClickListener {

    private Button startServiceButton;
    private Button stopServiceButton;
    private Intent notificationIntent;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        notificationIntent = new Intent(this, ReminderService.class);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);		
	}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.startServiceButton:
        	    startService(notificationIntent);
                Toast.makeText(this, "Notification service starting", Toast.LENGTH_SHORT).show();
        		break;
        	case R.id.stopServiceButton:
                stopService(notificationIntent);
                Toast.makeText(this, "Notification service stopping", Toast.LENGTH_SHORT).show();
        		break;
        }
    }
}