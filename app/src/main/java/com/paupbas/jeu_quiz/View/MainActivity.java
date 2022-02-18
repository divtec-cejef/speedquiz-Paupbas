package com.paupbas.jeu_quiz.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputLayout;
import com.paupbas.jeu_quiz.R;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout settings;
    private RelativeLayout questions;
    private Button BT_addPlayer;
    private Button BT_play;
    private TextInputLayout TIL_playerLayout1;
    private EditText ET_Player1;
    private TextInputLayout TIL_playerLayout2;
    private EditText ET_Player2;
    private Button BT_closeSettings;
    private Button BT_closeQuestions;
    private Switch SW_nightMode;

    private String joueur1;
    private String joueur2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Reprend le constructeur de son parent
        super.onCreate(savedInstanceState);

        // Relit le java au xml
        setContentView(R.layout.activity_main);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        settings = findViewById(R.id.main_settings);
        questions = findViewById(R.id.main_questions);

        BT_addPlayer = findViewById(R.id.main_button_addPlayer);
        BT_play = findViewById(R.id.main_button_play);
        TIL_playerLayout1 = findViewById(R.id.main_textInputLayout1);
        ET_Player1 = findViewById(R.id.main_textInput_player1);
        TIL_playerLayout2 = findViewById(R.id.main_textInputLayout2);
        ET_Player2 = findViewById(R.id.main_textInput_player2);
        SW_nightMode = findViewById(R.id.settings_switch_night);
        BT_closeSettings = findViewById(R.id.settings_close);
        BT_closeQuestions = findViewById(R.id.questions_close);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BT_addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TIL_playerLayout1.setVisibility(View.VISIBLE);
            }
        });

        ET_Player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ET_Player1.getText().toString().equals(""))
                    TIL_playerLayout2.setVisibility(View.VISIBLE);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        ET_Player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                BT_addPlayer.setVisibility(View.INVISIBLE);
                BT_play.setVisibility(View.VISIBLE);
            }
        });

        BT_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("player1", ET_Player1.getText());
                intent.putExtra("player2", ET_Player2.getText());
                startActivity(intent);
            }
        });

        SW_nightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    setTheme(R.style.Theme_jeu_night);
                } else {
                    setTheme(R.style.Theme_jeu_light);
                }

                setContentView(R.layout.activity_main);
            }
        });

        BT_closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setVisibility(View.INVISIBLE);
            }
        });

        BT_closeQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.setVisibility(View.INVISIBLE);
            }
        });
    }
        public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                settings.setVisibility(View.VISIBLE);
                break;
            case R.id.action_questions:
                questions.setVisibility(View.VISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}