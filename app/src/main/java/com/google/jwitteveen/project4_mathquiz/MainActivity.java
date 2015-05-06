package com.google.jwitteveen.project4_mathquiz;

/* ***
    Author: Jenna Witteveen
     Date: April 17th, 2015
  Purpose: Kid's math quiz game. User can enter name and pick an
           operand to be tested on.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences("state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", name);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = getSharedPreferences("state", Context.MODE_PRIVATE);
        name = settings.getString("name", "");
        EditText et = (EditText) findViewById(R.id.editTextName);
        et.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View view) {
        EditText et = (EditText)findViewById(R.id.editTextName);
        Intent IPickOperation = new Intent(this, PickOperationActivity.class);
        switch (view.getId()) {
            case R.id.buttonEnterName:
                name = et.getText().toString();
                IPickOperation.putExtra("name", name);
                startActivity(IPickOperation);
                break;
        }
    }


}
