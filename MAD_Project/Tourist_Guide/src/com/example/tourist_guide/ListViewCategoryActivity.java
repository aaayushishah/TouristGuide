package com.example.tourist_guide;

import com.example.tourist_guide.R.string;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewCategoryActivity extends Activity implements OnItemClickListener{
	ListView lv;
	Cursor c,c1;
	String str1;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_category);
		
		db = openOrCreateDatabase("Tourist", MODE_PRIVATE, null);
		
		
		Intent i = getIntent();
		//String str1 = i.getExtras().getString("spin");
		str1 = i.getExtras().getString("spin1");
		//Toast.makeText(ListViewCategoryActivity.this, str1, Toast.LENGTH_SHORT).show();
		Toast.makeText(ListViewCategoryActivity.this, str1, Toast.LENGTH_SHORT).show();
		
		lv = (ListView) findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
		c = db.rawQuery("select dname as _id from tbl_desc where subcatname='"+str1+"'", null);
		ListAdapter adp = new SimpleCursorAdapter(ListViewCategoryActivity.this,R.layout.listviewdemo, c, new String[]{"_id"}, new int[]{R.id.textViewlist});
        lv.setAdapter(adp);
        db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	//	String oo = (String) ((TextView)view).getText();
//		TextView v= (TextView) view;
//		String oo= v.getText().toString();
		String oo=null;
		c.moveToFirst();
		Toast.makeText(ListViewCategoryActivity.this, Integer.toString(c.getCount()), Toast.LENGTH_LONG).show();
		if(c.getCount()>0)
		{
		c.move(position);
		oo=c.getString(0);
		//Toast.makeText(ListViewCategoryActivity.this, oo, Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(ListViewCategoryActivity.this,DescriptionActivity.class);
		db = openOrCreateDatabase("Tourist", MODE_PRIVATE, null);
		Cursor c2 = db.rawQuery("SELECT descid FROM tbl_desc where subcatname='"+str1+"' and dname='"+oo+"'", null);
		c2.moveToFirst();
		i.putExtra("key1", c2.getString(0));
		startActivity(i);
		db.close();
		
		}
		
		
		
		
		
	}
}

