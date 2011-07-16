package com.Smart_task1;


import java.io.InputStream;
import java.util.Calendar;
import com.Smart_task1.DatabaseHelper;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public final class ProxyAlert extends BroadcastReceiver
{	

	Button yes,no;
	DatabaseHelper db;

	InputStream is = null;
	
	@Override
    
    public void onReceive(Context context, Intent intent) 
	{
		
			Intent myIntent = new Intent( context, TestProxy.class );
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(myIntent); 
			int SECS = 900;
		    int MINS = 60 * SECS;
		    Calendar cal = Calendar.getInstance();
		    Intent in = new Intent(context, Sync.class);
		    PendingIntent pi = PendingIntent.getService(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
		    AlarmManager alarms = (AlarmManager)context.getSystemService(
		    Context.ALARM_SERVICE);
			alarms.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 
		            10 * MINS, pi);
		    System.out.println("After 10 Min");

		    Toast.makeText(context, "Auto Sync Activated For Every 15 minutes..", Toast.LENGTH_SHORT).show();
    }


		
	}

	

