package com.expert.ud.pm_v10;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper 
{
	private static final String DATABASENAME="prisoner";
	private static final int DATABASEVERSION=1;
	private static final String TABLENAME="nominalentry";
	private static final String NAME="name";
	private static final String ADDRESS="address";
	private static final String CITY="city";
	private static final String STATE="state";
	private static final String COUNTRY="country";
	private static final String CRIME="crime";
	private static final String CRIMELOCATION="crimelocation";
	private static final String CHARGES="charges";
	private static final String ADVOCATE="advocate";
	private static final String JUDGE="judge";
	private static final String ARTICLESNO="articleno";
	private static final String ARTICLES="articles";
	
	private static final String TABLENAME1="payroll";
	private static final String FROM="datefrom";
	private static final String TO="dateto";
	private static final String WHY="why";
	private static final String POLICESTATION="station";
	private static final String COUNTER="counter";
	
	
	private static final String CREATETABLE="create table "+TABLENAME+ " (caseno INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME + " text, " + ADDRESS + " text, " + CITY +" text, "+ STATE + " text, " + COUNTRY + " text, " + CRIME + " text, "+CRIMELOCATION +" text, "+ CHARGES +" text, "+ADVOCATE +" text, "+ JUDGE +" text, "+ ARTICLESNO + " text, "+ ARTICLES +" text );";
	private static final String CREATETABLE2="create table "+TABLENAME1+ " (Pno INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME +" text, "+ FROM + " date, " + TO + " date, " + WHY + " text, " + POLICESTATION + " text, " + COUNTER + " int );";
	
	
	
	Helper help;
	SQLiteDatabase db;
	
	public DatabaseHelper(Context context)
	{
		help=new Helper(context, DATABASENAME, null, DATABASEVERSION);
	}
	
	class Helper extends SQLiteOpenHelper
	{

		public Helper(Context context, String name, CursorFactory factory,int version) 
		{
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void onCreate(SQLiteDatabase arg0)
		{
			// TODO Auto-generated method stub
			arg0.execSQL(CREATETABLE);
			arg0.execSQL(CREATETABLE2);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
		{
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
			arg0.execSQL("DROP TABLE IF EXISTS "+TABLENAME1);
			onCreate(arg0);	
		}
		
	}
	
	public void InsertData(String name, String address, String city, String state, String country, String crime, String crimelocation, String charges, String advocate, String article,String judge, String articleno)
	{
		db=help.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(NAME, name);
		values.put(ADDRESS, address);
		values.put(CITY, city);
		values.put(STATE, state);
		values.put(COUNTRY, country);
		values.put(CRIME, crime);
		values.put(CRIMELOCATION, crimelocation);
		values.put(CHARGES, charges);
		values.put(ADVOCATE, advocate);
		values.put(ARTICLESNO, articleno);
		values.put(ARTICLES, article);
		values.put(JUDGE, judge);
		
		db.insert(TABLENAME, null, values);
		db.close();
	}
	
	public String getId()
	{
		db=help.getWritableDatabase();
		String sno = null;
		Cursor cursor=db.rawQuery("select (max(caseno))+1 from "+TABLENAME, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
			sno=cursor.getString(0);
			if (sno==null)
			{
				sno="1";
			}
		}
		
		db.close();
		return sno;
	}

	public Cursor getData()
	{
		db=help.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from "+TABLENAME1, null);
		cursor.moveToFirst();
		db.close();
		return cursor;
	}
	
	public Cursor getDataFromName(String name)
	{
		db=help.getReadableDatabase();
		Cursor cursor=db.query(TABLENAME1, null, NAME+"='"+ name +"'", null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		db.close();
		return cursor;
	}
	
	public void insertForPayroll(String name, String fromdate, String why, String todate, String ctr, String police)
	{
		db=help.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(NAME, name);
		values.put(FROM, fromdate);
		values.put(WHY, why);
		values.put(TO, todate);
		values.put(COUNTER, ctr);
		values.put(POLICESTATION, police);
		db.insert(TABLENAME1, null, values);
		db.close();
	}
}
