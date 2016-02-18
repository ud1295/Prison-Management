package com.expert.ud.pm_v10;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayrollEntry extends Activity {

	EditText name, fromdate, todate, policestation, ctr, why;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addprisoner);
		name=(EditText) findViewById(R.id.namepayroll);
		why=(EditText) findViewById(R.id.why);
		fromdate=(EditText) findViewById(R.id.frompayroll);
		todate=(EditText) findViewById(R.id.topayroll);
		policestation=(EditText) findViewById(R.id.policestationpayroll);
		ctr=(EditText) findViewById(R.id.counter);
		button=(Button) findViewById(R.id.closeparole);
		
		final DatabaseHelper helper=new DatabaseHelper(PayrollEntry.this);
		
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				helper.insertForPayroll(name.getText().toString(), fromdate.getText().toString(), why.getText().toString(), todate.getText().toString(), ctr.getText().toString(), policestation.getText().toString());		
				name.setText(null);
				why.setText(null);
				fromdate.setText(null);
				todate.setText(null);
				policestation.setText(null);
				ctr.setText(null);
				Toast.makeText(PayrollEntry.this, "Added successfully", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payroll_entry, menu);
		return true;
	}

}
