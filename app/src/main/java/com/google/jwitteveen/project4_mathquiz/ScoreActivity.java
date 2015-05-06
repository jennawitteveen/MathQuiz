package com.google.jwitteveen.project4_mathquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScoreActivity extends ActionBarActivity {
    TextView tvScoreField;
    String name, operation;
    String questions, correct, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        name = getIntent().getExtras().getString("name");
        operation = getIntent().getExtras().getString("operation");
        correct = getIntent().getExtras().getString("correct");
        incorrect = getIntent().getExtras().getString("incorrect");
        questions = getIntent().getExtras().getString("questions");

        String greeting = "Thanks for playing, " +  name + "!";

        TextView tvGreeting = (TextView)findViewById(R.id.textViewScoreGreeting);
        tvGreeting.setText(greeting);

        tvScoreField = (TextView)findViewById(R.id.textViewScoreField);
        String myScore = getIntent().getExtras().getString("score");
        tvScoreField.setText(myScore);
        TextView tvQuestions = (TextView)findViewById(R.id.textViewQuestionNum);
        tvQuestions.setText(questions);
        TextView tvCorrect = (TextView)findViewById(R.id.textViewCorrectScore);
        tvCorrect.setText(correct);
        TextView tvIncorrect = (TextView)findViewById(R.id.textViewIncorrectScore);
        tvIncorrect.setText(incorrect);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
