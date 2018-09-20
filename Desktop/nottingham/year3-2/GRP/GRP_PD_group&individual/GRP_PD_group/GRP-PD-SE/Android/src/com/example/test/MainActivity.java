package com.example.test;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Set up click listeners
				View newGameButton = this.findViewById(R.id.new_game_label);
				newGameButton.setOnClickListener(this);
				View helpButton = this.findViewById(R.id.help_label);
				helpButton.setOnClickListener(this);
				View exitButton = this.findViewById(R.id.exit_label);
				exitButton.setOnClickListener(this);

	}
	public void onClick(View v){
		switch (v.getId()){
			case R.id.help_label:
				Intent help = new Intent(this, Help.class);
				startActivity(help);
				break;
				
			case R.id.new_game_label:
				Intent new_game = new Intent(this, NewGame.class);
				startActivity(new_game);
				break;
				
			case R.id.exit_label:
				finish();
				break;

		}
		

	

	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
