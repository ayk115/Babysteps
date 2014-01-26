package com.example.galaxy_note;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.babysteps.MESSAGE";
	private TextView selectedDate;
	//private DatePicker dpResult;
 
	private int year;
	private int month;
	private int day;
	
	
 
	static final int DATE_DIALOG_ID = 999;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setCurrentDateOnView();
		addListenerOnButton();
	}

	// display current date
		public void setCurrentDateOnView() {
	 
			selectedDate = (TextView) findViewById(R.id.editText1);
			selectedDate.setGravity(Gravity.CENTER);
			//dpResult = (DatePicker) findViewById(R.id.dpResult);
	 
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into textview
			selectedDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));
	 
			// set current date into datepicker
			//dpResult.init(year, month, day, null);
	 
		}
	 
		public void addListenerOnButton() {
	 
			selectedDate = (EditText) findViewById(R.id.editText1);
	 
			selectedDate.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View v) {
	 
					showDialog(DATE_DIALOG_ID);
	 
				}
	 
			});
	 
		}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, 
                         year, month,day);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener datePickerListener 
                = new DatePickerDialog.OnDateSetListener() {
 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			String dateOfBirth= new String();
			dateOfBirth=(new StringBuilder().append(month + 1)
					   .append("-").append(day).append("-").append(year)
					   .append(" ")).toString();
 
			
			
			
			// set selected date into textview
			selectedDate.setText(dateOfBirth);

			
			
 
			// set selected date into datepicker also
			//dpResult.init(year, month, day, null);
 
			Intent intent = new Intent(MainActivity.this,DisplaySchedule.class);
			EditText editText = (EditText) findViewById(R.id.editText1);
			String message = editText.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
		}
	};
}