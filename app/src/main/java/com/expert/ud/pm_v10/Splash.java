package com.expert.ud.pm_v10;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity implements AnimationListener
{

	Animation animRotate;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.layout.rotate);
		animRotate.setAnimationListener(this);
		final ImageView image=(ImageView) findViewById(R.id.imageView1);
		image.startAnimation(animRotate);

        Thread th=new Thread()
        {
        	public void run() 
        	{
        		try 
        		{
					Thread.sleep(4000);
					
        		}
        		catch (InterruptedException e) 
        		{
					e.printStackTrace();
				}    
        		finally 
        		{
	    			
	 					Intent s = new Intent(getApplicationContext(), Main.class);
	 					startActivity(s);
	 					finish();
	        			
        		}
        	};
        };
        th.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

}
