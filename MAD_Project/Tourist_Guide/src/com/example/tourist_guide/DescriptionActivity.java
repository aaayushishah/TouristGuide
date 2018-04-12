package com.example.tourist_guide;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DescriptionActivity extends Activity implements OnClickListener{
	ImageButton fav;
	SQLiteDatabase db;
	//EditText  et_uname
	String str;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		
		Intent i = getIntent();
		str = i.getExtras().getString("key1");
		
		fav = (ImageButton) findViewById(R.id.imageButton1);
		fav.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final Dialog d = new Dialog(DescriptionActivity.this);
		d.setContentView(R.layout.loginactivity);
		d.setTitle("Login Form");
		d.show();
		final EditText et_uname = (EditText) d.findViewById(R.id.editText1);
		final EditText et_pass = (EditText) d.findViewById(R.id.editText2);
		Button btnLogin = (Button) d.findViewById(R.id.btnsignup);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String a = et_uname.getText().toString();
				String b = et_pass.getText().toString();
				
				db = openOrCreateDatabase("Tourist", MODE_PRIVATE, null);
				Cursor c = db.rawQuery("select * from tbl_login where uname='"+a+"' and pass ='"+b+"'", null);
				c.moveToFirst();
				String str = c.getString(0);
				if(str.equals(null))
				{					
					Toast.makeText(getApplicationContext(), "Wrong Username and Password..!!",Toast.LENGTH_SHORT ).show();
				}else
				{
					c.moveToFirst();
					String username = c.getString(0);
					String password = c.getString(1);
					Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_SHORT ).show();
					
					//HERE ISERTION OF FAVORITE ID IS LEFT
					d.dismiss();
				}
				
				db.close();
			}
		});
		
		Button btnSignUp = (Button) d.findViewById(R.id.button2);
		btnSignUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog d1 = new Dialog(DescriptionActivity.this);
				d1.setContentView(R.layout.signupactivity);
				d1.setTitle("Login Form");
				d1.show();
				final EditText et_uname = (EditText) d1.findViewById(R.id.editText1);
				final EditText et_pass = (EditText) d1.findViewById(R.id.editText2); 
				
				Button btnSignUp = (Button) d1.findViewById(R.id.btnsignup);
				btnSignUp.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String user = et_uname.getText().toString();
						String pass = et_pass.getText().toString();
						
						db = openOrCreateDatabase("Tourist", MODE_PRIVATE, null);
						db.execSQL("Insert into tbl_login values('" + user + "','" + pass + "',"+Integer.parseInt(str)+");");
				        db.close();
					}
				});
			}
		});
	}

	
}
