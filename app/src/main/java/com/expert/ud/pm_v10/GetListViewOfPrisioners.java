package com.expert.ud.pm_v10;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GetListViewOfPrisioners extends Activity {

	
	ArrayAdapter<String> adapter;
	String[] arr;
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();allMechanism();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		allMechanism();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofprisoners);
        allMechanism();
    }


    public void allMechanism()
    {
    	ListView list=(ListView) findViewById(R.id.Lv1);
        final DatabaseHelper databaseHelper=new DatabaseHelper(this.getApplicationContext());
        Cursor cursor=databaseHelper.getData();
        
        if (cursor!= null)
        {
        	arr = new String[cursor.getCount()];
        	cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) 
            {
            	arr[i]=cursor.getString(1);
            	cursor.moveToNext();
            }
            adapter=new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, arr);
            list.setAdapter(adapter);
        }
        else
        {
        	Toast.makeText(this, "Empty List", Toast.LENGTH_SHORT).show();
        }
        list.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
			{
				String ename=null, fromdate=null, todate=null, police=null;
				Intent intent=new Intent(GetListViewOfPrisioners.this, PayrollView.class);
				String name=((TextView)arg1).getText().toString();
				Cursor cursor=databaseHelper.getDataFromName(name);
				System.out.println(cursor.getColumnCount()+"+++++++++++++++++++++++++++++++++++++"+cursor.getCount());
				ename=cursor.getString(1);
				fromdate=cursor.getString(2);
				todate=cursor.getString(3);
				police=cursor.getString(5);	
				intent.putExtra("name", ename);
				intent.putExtra("fromdate", fromdate);
				intent.putExtra("todate", todate);
				intent.putExtra("police", police);
				startActivity(intent);			
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payroll_view, menu);
        return true;
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) 
    	{
			case R.id.newparole:
				Intent intent=new Intent(getApplicationContext(), PayrollEntry.class);
				startActivity(intent);
			break;
		}
    	return false;
    }
    
}
