package com.google.jwitteveen.project4_mathquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;


public class QuizActivity extends ActionBarActivity {
    String name = "";
    String operation = "";
    TextView tvQuizName, tvProblem, tvQuizScore;

    int lhs, rhs;

    Vector<Integer> possibleAnswers = new Vector<Integer>();
    Integer correctAnswer;

    int myScore = 0;
    int correct = 0;
    int incorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        name = getIntent().getExtras().getString("name");
        operation = getIntent().getExtras().getString("operation");

        tvQuizName = (TextView) findViewById(R.id.textViewQuizName);
        tvQuizScore = (TextView) findViewById(R.id.textViewQuizScore);
        tvProblem = (TextView) findViewById(R.id.textViewProblem);

        newQuestion();
    }

    public String getOperatorSymbol(String op){
        String sym = "";
        if(op.contains("addition"))
            sym = "\u002B";
        else if(op.contains("subtraction"))
            sym = "\u2212";
        else if (op.contains("multiplication"))
            sym = "\u00D7";
        else if (op.contains("division"))
            sym = "\u00F7";

        return sym;
    }

    public Integer solveEquation(int l, int r, String op){
        if(op.contains("addition"))
            return (l + r);
        else if(op.contains("subtraction"))
            return (l - r);
        else if (op.contains("multiplication"))
            return (l * r);
        else if (op.contains("division"))
            return (l / r);
        else
            return 0;
    }

    public void populateButtons(Vector<Integer> v){
        // Populate buttons with shuffled vector
        Button btn = (Button)findViewById(R.id.buttonAnswer1);
        btn.setText(v.elementAt(0).toString());
        btn = (Button)findViewById(R.id.buttonAnswer2);
        btn.setText(v.elementAt(1).toString());
        btn = (Button)findViewById(R.id.buttonAnswer3);
        btn.setText(v.elementAt(2).toString());
        btn = (Button)findViewById(R.id.buttonAnswer4);
        btn.setText(v.elementAt(3).toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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

    public void newQuestion(){
        possibleAnswers.clear();
        // setup first question
        Random rand = new Random();
        lhs = rand.nextInt(12 + 1) + 0;
        rhs = rand.nextInt(12 + 1) + 0;

        if(lhs < rhs){
            int temp = lhs;
            lhs = rhs;
            rhs = temp;
        }
        // Don't allow divide by zero
        if(lhs == 0) {
            ++lhs;
        }
        if(rhs == 0) {
            ++rhs;
        }
        // Set textView to display equation
        String displayEquation = lhs + " " + getOperatorSymbol(operation) + " " + rhs;
        tvProblem.setText(displayEquation);

        // Get correct answer
        correctAnswer = solveEquation(lhs, rhs, operation);

        // Fill vector with right answer and 3 other ones
        possibleAnswers.add(correctAnswer);
        possibleAnswers.add(rand.nextInt((correctAnswer + 10) + 1) + 0);
        possibleAnswers.add(rand.nextInt((correctAnswer + 10) + 1) + 0);
        possibleAnswers.add(rand.nextInt((correctAnswer + 10) + 1) + 0);

        Collections.shuffle(possibleAnswers);

        populateButtons(possibleAnswers);
    }

    public void answerButtonClick(View view) {

        switch (view.getId()) {
            case R.id.buttonAnswer1:
                Button btn1 = (Button)findViewById(R.id.buttonAnswer1);
                if(Integer.parseInt((btn1.getText().toString())) == correctAnswer){
                    Toast.makeText(getBaseContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                    ++myScore;
                    ++correct;
                    tvQuizScore.setText("" + myScore);
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
                    ++incorrect;
                }
                newQuestion();
                break;
            case R.id.buttonAnswer2:
                Button btn2 = (Button)findViewById(R.id.buttonAnswer2);
                if(Integer.parseInt((btn2.getText().toString())) == correctAnswer){
                    Toast.makeText(getBaseContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                    ++myScore;
                    ++correct;
                    tvQuizScore.setText("" + myScore);

                }
                else {
                    Toast.makeText(getBaseContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
                    ++incorrect;
                }
                newQuestion();
                break;
            case R.id.buttonAnswer3:
                Button btn3 = (Button)findViewById(R.id.buttonAnswer3);
                if(Integer.parseInt((btn3.getText().toString())) == correctAnswer){
                    ++myScore;
                    ++correct;
                    tvQuizScore.setText("" + myScore);
                    Toast.makeText(getBaseContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
                    ++incorrect;
                }
                newQuestion();
                break;
            case R.id.buttonAnswer4:
                Button btn4 = (Button)findViewById(R.id.buttonAnswer4);
                if(Integer.parseInt((btn4.getText().toString())) == correctAnswer){
                    ++myScore;
                    ++correct;
                    tvQuizScore.setText("" + myScore);
                    Toast.makeText(getBaseContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
                    ++incorrect;
                }
                newQuestion();
                break;
            case R.id.buttonNewPlayer:
                Intent mainIntent = new Intent(this, MainActivity.class);
                myScore = 0;
                correct = 0;
                incorrect = 0;
                name = "";
                startActivity(mainIntent);
                break;

            case R.id.buttonScore:
                Intent scoreIntent = new Intent(this, ScoreActivity.class);
                String strScore = "" + myScore;
                String strCorrect = "" + correct;
                String strIncorrect = "" + incorrect;
                int questions = correct + incorrect;
                String strQuest = "" + questions;
                scoreIntent.putExtra("score", strScore);
                scoreIntent.putExtra("correct", strCorrect);
                scoreIntent.putExtra("incorrect", strIncorrect);
                scoreIntent.putExtra("operation", operation);
                scoreIntent.putExtra("name", name);
                scoreIntent.putExtra("questions", strQuest);
                startActivityForResult(scoreIntent, 0);
                break;
        }
    }

}
