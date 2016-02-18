package com.expert.ud.pm_v10;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Main extends Activity {

    ImageButton nominal, casereg, diary, payroll;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        nominal=(ImageButton) findViewById(R.id.NominalRegistration);
        casereg=(ImageButton) findViewById(R.id.CaseRegister);
        diary=(ImageButton) findViewById(R.id.Diary);
        payroll=(ImageButton) findViewById(R.id.Payroll);

        nominal.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                Intent intent=new Intent(Main.this, NominalReg.class);
                startActivity(intent);
            }
        });

        casereg.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

            }
        });

        diary.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

            }
        });

        payroll.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                Intent intent=new Intent(Main.this, GetListViewOfPrisioners.class);
                startActivity(intent);
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
