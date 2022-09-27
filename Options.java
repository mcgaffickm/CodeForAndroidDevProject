package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    //Widgets and variables
    EditText txtPhone;
    CheckBox chInfinite;
    CheckBox chHints;
    RadioGroup rg;
    SelectedOptions changeOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //Finds everything, brings current options in
        chHints = findViewById(R.id.cbHints);
        chInfinite = findViewById(R.id.cbInfiniteLives);
        rg = findViewById(R.id.rbSecretGroup);
        txtPhone = findViewById(R.id.txtPhone);
        changeOptions = (SelectedOptions) getIntent().getSerializableExtra("Options");
    }

    //Click the button to go here
    public void saveChanges(View v)
    {
        //Determines your sms setting
        SmsManager sms = SmsManager.getDefault();
        if(!txtPhone.getText().toString().equals("XXXXXXXXXX"))
        {
            //Best path option activates with this code
            if(txtPhone.getText().toString().equals("84965812397")) {
                changeOptions.setBestPath(true);
            }
            else
            {
                //Default functionality of entering a number
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
        //No phone number given
        else
            changeOptions.setBestPath(false);

        //Sets the default condition
        changeOptions.setBestEnding(true);

        //Adds hints
        if(chHints.isChecked())
            changeOptions.setHints(true);
        else
            changeOptions.setHints(false);

        //Adds infinite lives
        if(chInfinite.isChecked())
            changeOptions.setInfiniteLives(true);
        else
            changeOptions.setInfiniteLives(false);

        //Cancels best ending if you have either of above set
        if(changeOptions.isInfiniteLives() || changeOptions.isHints())
            changeOptions.setBestEnding(false);


        //Light sensor or gesture version of secret chamber
        if(rg.getCheckedRadioButtonId() == R.id.rbLight)
            changeOptions.setLightSensor(true);
        else
            changeOptions.setLightSensor(false);

        //Determines if best path should be set because you can't use the best path if the best ending can't be achieved
        if(!changeOptions.isBestEnding())
            changeOptions.setBestPath(false);

        //Save message
        Toast.makeText(this, "Successfully saved.", Toast.LENGTH_SHORT).show();
    }

    //Return to title
    public void goToTitle(View view)
    {
        //Puts options back, returns to title, etc
        Intent title = new Intent(this, TitleScreen.class);
        title.putExtra("Options", changeOptions);
        startActivity(title);
    }
}
