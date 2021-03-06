package com.Smart_task1;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class DDetails extends Activity
{	
	TextView tname,tddate,tdesp,ttime,tcat,tpriority;
	DatabaseHelper db;
	int id,tc;
	Button dedit,ddelete,back;
	 public void onCreate(Bundle savedInstanceState)
	 {
		  super.onCreate(savedInstanceState);
		  requestWindowFeature(Window.FEATURE_NO_TITLE);  
		     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);   
		  db=new DatabaseHelper(this);
	        setContentView(R.layout.ddetails);
	        Bundle bun=getIntent().getExtras();
	        id =bun.getInt("tid");
	        tname=(TextView)findViewById(R.id.tname);
	        tdesp=(TextView)findViewById(R.id.tdesp);
	        tcat=(TextView)findViewById(R.id.tcat);
	        tpriority=(TextView)findViewById(R.id.tpriority);
	        ttime=(TextView)findViewById(R.id.time);
	        tddate=(TextView)findViewById(R.id.due);
	        dedit=(Button)findViewById(R.id.dedit);
	        back=(Button)findViewById(R.id.back);
	        ddelete=(Button)findViewById(R.id.ddelete);
	        System.out.println("task is...dd"+id);
	      
	        	Cursor c=db.getDetails(id);
	        	while(c.moveToNext())
	        	{
				   tname.setText(c.getString(0));
				   tdesp.setText(c.getString(1));
				   tc=c.getInt(2);
				   tpriority.setText(c.getString(3));
				   String pr1=c.getString(3);
				   System.out.println("priority...."+c.getString(3));
				   tddate.setText(c.getString(4));
				   ttime.setText(c.getString(5));
			   }
	        	//category
	        	if(tc==0)
	        	{
	        		tcat.setText("Personal");
	        	}else if(tc==1)
	        	{
	        		tcat.setText("Work");
	        	}
	        	else if(tc==2)
	        	{
	        		tcat.setText("Home");
	        	}
	        	dedit.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{	
	    				edit();
	    				
	    			}
	    			});
	        	ddelete.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{
	    				db.deletetask(id);
	    				Toast t=Toast.makeText(getBaseContext(),"Task Deleted",Toast.LENGTH_LONG);
	    				t.show();
	    				show();
	    			}
	    			});
	        	back.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{
	    				show();
	    			}
	    			});
	        	
	 }
	 protected void show() {
		 Intent i1= new Intent(this,Smart_task1.class);
		 startActivity(i1);
		
	}
	 //calls activity for editing
	protected void edit() 
	{
		Intent i= new Intent(this,Dedit.class);
		 Bundle bun=new Bundle();
		 bun.putInt("tid",id);
		 i.putExtras(bun);
		 startActivity(i);
		
	}
	
	
}

