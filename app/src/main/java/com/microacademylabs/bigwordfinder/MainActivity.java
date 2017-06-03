package com.microacademylabs.bigwordfinder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  //private TextView hintText;
  private GridView letterGrid;
  private TextView guessWord;
  private Button okButton;
  private Button hintButton;
  private Button scoreButton;
  private ListView wordList;

  private String word;
  private String currentGuess;
  private ArrayList<String> guesses;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    guessWord=(TextView) findViewById(R.id.editGuess);
    guesses = new ArrayList<String>();
    currentGuess = "";
    word = Words.getWord();
    char[] hint = getHint(word).toCharArray();

    letterGrid = (GridView) findViewById(R.id.hintGrid);
    letterGrid.setAdapter(new LetterAdapter(this, hint));
    letterGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentGuess+=parent.getItemAtPosition(position).toString();
        guessWord.setText(currentGuess);
      }
    });

    wordList=(ListView) findViewById(R.id.wordList);

    okButton=(Button) findViewById(R.id.okButton);
    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        guesses.add(guessWord.getText().toString());
        currentGuess="";
        guessWord.setText(currentGuess);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, guesses);
        wordList.setAdapter(adapter);
        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.oxforddictionaries.com/definition/" + guesses.get(position)));
            startActivity(webIntent);
          }
        });
      }
    });

    hintButton=(Button)findViewById(R.id.btnHint);
    hintButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this, word, Toast.LENGTH_SHORT).show();
      }
    });

    scoreButton=(Button)findViewById(R.id.btnScore);
    scoreButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Total Score: " + getScore(guesses), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The word was: *" + word + "*", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });
  }

  private String getHint(String word) {
    List<String> chars = Arrays.asList(word.trim().split(""));
    Collections.shuffle(chars);
    return TextUtils.join("", chars);
  }

  private String getScore(ArrayList<String> guesses) {
    int score= 0;
    for(int i=0; i<guesses.size(); i++) {
      String thisGuess = guesses.get(i);
      switch(thisGuess.length()){
        case 3:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 1", Toast.LENGTH_SHORT).show();
          score+=1;
          break;
        case 4:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 2", Toast.LENGTH_SHORT).show();
          score+=2;
          break;
        case 5:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 3", Toast.LENGTH_SHORT).show();
          score+=3;
          break;
        case 6:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 5", Toast.LENGTH_SHORT).show();
          score+=5;
          break;
        case 7:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 8", Toast.LENGTH_SHORT).show();
          score+=8;
          break;
        case 8:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 13", Toast.LENGTH_SHORT).show();
          score+=13;
          break;
        case 9:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 21", Toast.LENGTH_SHORT).show();
          score+=21;
          break;
        case 10:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 34", Toast.LENGTH_SHORT).show();
          score+=34;
          break;
        case 11:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 55", Toast.LENGTH_SHORT).show();
          score+=55;
          break;
        case 12:
          Toast.makeText(MainActivity.this, " Score: " + thisGuess + "=" + score + " + 89", Toast.LENGTH_SHORT).show();
          score+=89;
          break;
        default:
          break;
      }
    }
    return String.valueOf(score);
  }
}
