package com.expert.ud.pm_v10;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PayrollView extends Activity {

	TextView ename, dates, policestation;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_payroll);
		String name=getIntent().getStringExtra("name");
		String fromdate=getIntent().getStringExtra("fromdate");
		String todate=getIntent().getStringExtra("todate");
		String police=getIntent().getStringExtra("police");
		
		ename=(TextView) findViewById(R.id.namepayrolls);
		dates=(TextView) findViewById(R.id.duration);
		policestation=(TextView) findViewById(R.id.policestation);
		
		ename.setText("Name: " + name);
		String duration="On parole \n from: "+fromdate+"\n to: "+todate;
		dates.setText(duration);
		policestation.setText("At police Station "+police);
		
		button=(Button) findViewById(R.id.close);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
