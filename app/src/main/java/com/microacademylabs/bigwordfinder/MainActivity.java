package com.microacademylabs.bigwordfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  //private TextView hintText;
  private GridView letterGrid;
  private EditText guessWord;
  private Button okButton;
  private Button scoreButton;
  private ListView wordList;
  private ArrayList<String> guesses;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    letterGrid = (GridView) findViewById(R.id.hintGrid);
    String[] hint = getHint(Words.getWord());
    letterGrid.setAdapter(new LetterAdapter(this, hint));
    //Toast.makeText(this, hint.toString(), Toast.LENGTH_SHORT).show();

    guessWord=(EditText) findViewById(R.id.editGuess);
    guesses = new ArrayList<String>();

    wordList=(ListView) findViewById(R.id.wordList);


    okButton=(Button) findViewById(R.id.okButton);
    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        guesses.add(guessWord.getText().toString());
        guessWord.setText("");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, guesses);
        wordList.setAdapter(adapter);
      }
    });

    scoreButton=(Button)findViewById(R.id.btnScore);
    scoreButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Show Score Here - or show score activity?", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });
  }

  private String[] getHint(String word) {
// String url = "https://en.oxforddictionaries.com/definition/" + word for definition

    String[] hint = Arrays.asList(word.split("")).toArray(new String[0]);;
    // Collections.shuffle(hint);
    return hint;
  }
}
