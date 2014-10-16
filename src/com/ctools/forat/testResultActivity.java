package com.ctools.forat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class testResultActivity extends Activity{
	ListView listview;
	SQLiteDatabase database;
	String DATABASE_FILENAME = "TestResult.sqlite";
//	String DATABASE_PATH = "/storage/emulated/legacy/sql";
	String DATABASE_PATH = android.os.Environment.getExternalStorageDirectory().getPath()+"/sql";
	String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
	private static final String DATABASE_TABLE = "Result";
	private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " ( TestID, Test_Result, Remark);";
	public Cursor cursor;
	int rowCount;



	
//	String[] chars = new String[]{
//			"test_01_01","Pass","reason"
//	};
	
	//打开数据库
	public SQLiteDatabase openDatabase(){

		try{
			File destDir = new File(DATABASE_PATH);
			if (!destDir.exists()){destDir.mkdir();}
			database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			database.execSQL(DATABASE_CREATE);
		}catch(Exception e){
//			database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
		}
		
		return null;
	}	
	@Override
	public void onCreate(Bundle savedInstanceState){
		openDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_result_index);
		listview = (ListView)findViewById(R.id.listview);
		String sql = "select * from Result";
		cursor = database.rawQuery(sql, null);
		rowCount = cursor.getCount();
		ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String, Object>>();
		cursor.moveToFirst();
		for(int i =0;i<rowCount;i++){
			HashMap<String, Object> item = new HashMap<String,Object>();
			int id =cursor.getColumnIndex("TestID");
			int test_result = cursor.getColumnIndex("Test_Result");
			int remark = cursor.getColumnIndex("Remark");
			item.put("id",cursor.getString(id));
			item.put("result", cursor.getString(test_result));
			item.put("remark", cursor.getString(remark));
			data.add(item);
			if(cursor.moveToNext()){
				continue;
			}else{
				break;
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item,
				new String[]{"id","result","remark"},new int[]{R.id.test_id,R.id.test_result,R.id.test_remark});
		
		listview.setAdapter(adapter);
	
	}
}
