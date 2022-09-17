package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    EditText txtPhone;
    CheckBox chInfinite;
    CheckBox chHints;
    RadioButton rbSensor;
    SelectedOptions changeOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        chHints = findViewById(R.id.cbHints);
        chInfinite = findViewById(R.id.cbInfiniteLives);
        rbSensor = findViewById(R.id.rbLight);
        txtPhone = findViewById(R.id.txtPhone);
        changeOptions = (SelectedOptions) getIntent().getSerializableExtra("Options");
    }

    public void saveChanges(View v)
    {

        SmsManager sms = SmsManager.getDefault();
        if(!txtPhone.getText().toString().equals("XXXXXXXXXX"))
        {
            if(txtPhone.getText().toString().equals("8496581239a")) {
                changeOptions.setBestPath(true);
            }
            else
            {
                changeOptions.setBestPath(false);
                try {
                    sms.sendTextMessage(txtPhone.getText().toString(), null, "Secret of Cursed Castle: Sample Test Message", null, null);
                    changeOptions.setPhoneText(txtPhone.getText().toString());
                    changeOptions.setPhone(true);
                } catch (SecurityException e) {
                    Toast.makeText(Options.this, "Please check your permissions to use the call features", Toast.LENGTH_SHORT).show();
                    changeOptions.setPhoneText("");
                    changeOptions.setPhone(false);
                } catch (Exception e) {
                    Toast.makeText(Options.this, "Please check your number to use the call features", Toast.LENGTH_SHORT).show();
                    changeOptions.setPhoneText("");
                    changeOptions.setPhone(false);
                }
            }
        }
        else
            changeOptions.setBestPath(false);

        changeOptions.setBestEnding(true);

        changeOptions.setHints(chHints.isSelected());
        changeOptions.setInfiniteLives(chInfinite.isSelected());

        if(changeOptions.isInfiniteLives() || changeOptions.isHints())
            changeOptions.setBestEnding(false);


        changeOptions.setLightSensor(rbSensor.isSelected());

        if(!changeOptions.isBestEnding())
            changeOptions.setBestPath(false);

        Toast.makeText(this, "Successfully saved.", Toast.LENGTH_SHORT).show();
    }

    public void goToTitle(View view)
    {
        Intent title = new Intent(this, TitleScreen.class);
        title.putExtra("Options", changeOptions);
        startActivity(title);
    }
}
