package com.google.jwitteveen.project4_mathquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.jwitteveen.project4_mathquiz.R;

public class PickOperationActivity extends ActionBarActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_operation);
        name = getIntent().getExtras().getString("name");

        TextView tv = (TextView)findViewById(R.id.textViewPickOperation);
        tv.setText("Hello, " + name + "! Please pick an operation.");
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
        TextView tv = (TextView)findViewById(R.id.textViewPickOperation);
        tv.setText("Hello, " + name + "! Please pick an operation.");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_operation, menu);
        return true;
    }



    public void buttonOnClick(View view) {
        EditText et = (EditText)findViewById(R.id.editTextName);
        Intent quizIntent = new Intent(this, QuizActivity.class);
        switch (view.getId()) {
            case R.id.buttonAddition:
                quizIntent.putExtra("operation", "addition");
                quizIntent.putExtra("name", name);
                startActivity(quizIntent);
                break;
            case R.id.buttonSubtraction:
                quizIntent.putExtra("operation", "subtraction");
                quizIntent.putExtra("name", name);
                startActivity(quizIntent);
                break;
            case R.id.buttonMultiplication:
                quizIntent.putExtra("operation", "multiplication");
                quizIntent.putExtra("name", name);
                startActivity(quizIntent);
                break;
            case R.id.buttonDivision:
                quizIntent.putExtra("operation", "division");
                quizIntent.putExtra("name", name);
                startActivity(quizIntent);
                break;
        }
    }
}
