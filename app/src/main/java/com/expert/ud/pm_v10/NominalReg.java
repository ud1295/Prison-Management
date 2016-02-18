package com.expert.ud.pm_v10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;

import android.R.drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NominalReg extends Activity {

	public static EditText name, address, city, state, country, crime, crimelocation, charges, judge, advocate, article,articlesno, ids;
	ImageButton center, reset, ok;
	Uri fileUri;
	Bitmap thumbnail;
	String picturePath;
	static String id=null;
	DatabaseHelper helper=new DatabaseHelper(NominalReg.this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominal_reg);
        getsetid();
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        city=(EditText) findViewById(R.id.city);
        state =(EditText) findViewById(R.id.state);
        country=(EditText) findViewById(R.id.Country);
        crime=(EditText) findViewById(R.id.crime);
        crimelocation =(EditText) findViewById(R.id.crimelocation);
        charges =(EditText) findViewById(R.id.crimecharges);
        judge =(EditText) findViewById(R.id.judge);
        advocate =(EditText) findViewById(R.id.advocate);
        article=(EditText) findViewById(R.id.articles);
        articlesno=(EditText) findViewById(R.id.articlesno);
        center=(ImageButton) findViewById(R.id.center);
        reset=(ImageButton) findViewById(R.id.reset);
        ok=(ImageButton) findViewById(R.id.ok);
        
        
        
        center.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				center.setImageBitmap(null);
				selectImage();
			}
		});
        
        reset.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View arg0) 
			{
				name.setText(null);
				address.setText(null);
				city.setText(null);
				state.setText(null);
				country.setText(null);
				crime.setText(null);
				crimelocation.setText(null);
				charges.setText(null);
				judge.setText(null);
				advocate.setText(null);
				article.setText(null);
				articlesno.setText(null);
				
				center.setImageBitmap(null);
				
			}
		});
        
        
        ok.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				{
					if (name.getText().toString()!=null && address.getText().toString()!=null && city.getText().toString()!=null && state.getText().toString()!=null && country.getText().toString()!=null && crime.getText().toString()!=null && crimelocation.getText().toString()!=null && charges.getText().toString()!=null && advocate.getText().toString()!=null && article.getText().toString()!=null && articlesno.getText().toString()!=null)
					{
						helper.InsertData(name.getText().toString(), address.getText().toString(), city.getText().toString(), state.getText().toString(), country.getText().toString(), crime.getText().toString(), crimelocation.getText().toString(), charges.getText().toString(), advocate.getText().toString(), article.getText().toString(), judge.getText().toString(), articlesno.getText().toString());		
						Toast.makeText(NominalReg.this, "Added successfully", Toast.LENGTH_SHORT).show();
						name.setText(null);
						address.setText(null);
						city.setText(null);
						state.setText(null);
						country.setText(null);
						crime.setText(null);
						crimelocation.setText(null);
						charges.setText(null);
						judge.setText(null);
						advocate.setText(null);
						article.setText(null);
						articlesno.setText(null);
						getsetid();
						
					}
					else
					{
						Toast.makeText(NominalReg.this, "Enter all feilds", Toast.LENGTH_SHORT).show();
					}
				}
			}
        });
   }

    private void selectImage()
    {	 
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(NominalReg.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item) 
            {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
					fileUri = getOutputMediaFileUri();
        			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on scren orientation
		// changes
		outState.putParcelable("file_uri", fileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		fileUri = savedInstanceState.getParcelable("file_uri");
	}
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) 
        {
            if (requestCode == 1) 
            {
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize=8;
				thumbnail = BitmapFactory.decodeFile(fileUri.getPath(),bitmapOptions);	
				center.setImageBitmap(thumbnail);
            }
            else if (requestCode==2)
            {
            	Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                thumbnail = (BitmapFactory.decodeFile(picturePath));
			center.setImageBitmap(thumbnail);
				try
				{
					Date date=new Date();
					@SuppressWarnings("deprecation")
					String dire=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+"prisonmgmt"+File.separator+id+name.getText().toString()+date.getDate()+date.getMonth()+date.getYear();
					File dir=new File(dire);
					File file = new File(dir.getPath()+ File.separator+ "IMG_" + name.getText().toString() + crime.getText().toString() + ".jpg");
					if (!dir.exists()) 
					{
						dir.mkdirs();
					}
					if(file.exists())
					file.delete();
					file.createNewFile();   
					File sd =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
					if (sd.canWrite()) 
					{
						File source= new File(picturePath);
					    if (source.exists()) 
					    {
					    	@SuppressWarnings("resource")
							FileChannel src = new FileInputStream(source).getChannel();
					        @SuppressWarnings("resource")
							FileChannel dst = new FileOutputStream(file).getChannel();
					        dst.transferFrom(src, 0, src.size());
					        src.close();
					        dst.close();
					    }
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
            }
        }
    }   
    
    public Uri getOutputMediaFileUri() 
	{
    	return Uri.fromFile(getOutputMediaFile());
    }
	
	
	private static File getOutputMediaFile() 
	{
		Date date=new Date();
		@SuppressWarnings("deprecation")
		String namefolder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+"prisonmgmt"+File.separator+id+name.getText().toString()+date.getDate()+date.getMonth()+date.getYear();
		File mediaStorageDir = new File(namefolder);
		if (!mediaStorageDir.exists()) 
		{
			if (!mediaStorageDir.mkdirs()) 
			{
				return null;
			}
		}
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_"+name.getText().toString()+crime.getText().toString()+date.getHours()+date.getMinutes()+date.getSeconds()+".jpg");
		if (mediaFile.exists())
		{
			mediaFile.delete();
			try {
				mediaFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mediaFile;
	}
	
	public void getsetid()
	{
		id=helper.getId();
        ids=(EditText) findViewById(R.id.ids);
        ids.setText(id);
	}
}
