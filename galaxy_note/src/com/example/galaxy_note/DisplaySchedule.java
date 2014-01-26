package com.example.galaxy_note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplaySchedule extends Activity {

	private String message=new String();
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_schedule);
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
     // Get the message from the intent
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the activity layout
        setContentView(textView);
        
        
		// new
		Calendar dob = Calendar.getInstance();  
		try {
			dob.setTime(getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		Calendar today = Calendar.getInstance();
		
		long mdob=dob.getTimeInMillis();
		long mtoday=today.getTimeInMillis();
		//Calendar end=Calendar.getInstance();
		int elapsed=0,date=0,month=0,year=0;
		Calendar clone=(Calendar)today.clone();
		if(mdob>=mtoday)
		{
			date=elapsed(clone,dob,Calendar.DATE);
			month=elapsed(clone,dob,Calendar.MONTH);
			year=elapsed(clone,dob,Calendar.YEAR);
			//int age1 = today.get(Calendar.SECOND) - dob.get(Calendar.SECOND);
		}
		else
		{
			date=-elapsed(dob,clone,Calendar.DATE);
			month=-elapsed(dob,clone,Calendar.MONTH);
			year=-elapsed(dob,clone,Calendar.YEAR);
		}
	//	String text=getVaccine(date,month,year);
	/*	if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age1--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age1--;  
		}*/
		//String text=getVaccine();
    	textView.setText(""+date+" "+month+" "+year);
		
	}
	/*public static String[][] (int date,int month,int year){
		String[][] vaccines=  new String[100][100];
		String disease,vaccine;
		if (date==0 && month==0 && year==0)
		{
			vaccines[0][0]=" HEPATITIS B";
			vaccines[1][0]=" HEP B VACCINE -I";
			vaccines[0][1]="POLIO";
			vaccines[1][1]=" ORAL PV 0 DOSE";		
		}
		return vaccines;
	}*/
	public static int elapsed(Calendar before, Calendar after, int field) {
	    Calendar clone = (Calendar) before.clone(); // Otherwise changes are been reflected.
	    int elapsed = -1;
	    while (!clone.after(after)) {
	        clone.add(field, 1);
	        elapsed++;
	    }
	    if(elapsed==-1)
	    {
	    	clone=(Calendar)after.clone();
	    	while(!clone.before(before)){
	    		clone.add(field,1);
	    		elapsed--;
	    	}
	    }
	    return elapsed;
	}
	private Date getBirthDate() throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date birthDateObject = dateFormat.parse(message);
		
		return birthDateObject;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_schedule, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}