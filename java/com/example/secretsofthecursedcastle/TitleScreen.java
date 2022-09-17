package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TitleScreen extends AppCompatActivity {

    Intent newGame;
    Intent option;
    SelectedOptions changedOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_title_screen);
        newGame = new Intent(TitleScreen.this, History.class);
        option = new Intent(TitleScreen.this, Options.class);

    }

    public void newGame(View v)
    {
        changedOptions = (SelectedOptions) getIntent().getSerializableExtra("Options");
        newGame.putExtra("Options", changedOptions);
        startActivity(newGame);
    }

    public void options(View v){startActivity(option);}


}
