package com.microacademylabs.bigwordfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private TextView hintText;
  private EditText guessWord;
  private Button okButton;
  private ListView wordList;
  private ArrayList<String> guesses;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    hintText=(TextView)findViewById(R.id.hintText);
    hintText.setText(Words.getWord());

    guessWord=(EditText) findViewById(R.id.editGuess);
    guesses = new ArrayList<String>();

    wordList=(ListView) findViewById(R.id.wordList);


    okButton=(Button) findViewById(R.id.okButton);
    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add word guess to array-list view
        guesses.add(guessWord.getText().toString());

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, guesses);
        wordList.setAdapter(adapter);

      }
    });
  }
}
