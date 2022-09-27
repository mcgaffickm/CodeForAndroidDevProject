package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

//Pretty much the center of the program that controls 90% of everything else
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Declares the widget intents, variables, objects, list, etc
    DrawerLayout drawer;
    NavigationView nav;

    Intent ending;
    Intent music;

    SelectedOptions options;

    SmsManager sms;

    boolean foundNewspaper, kingRoomBest;

    int currentChange, lives, progress, bestCount, count;

    Boolean locations[];
    //0 secret
    //1 final
    //2 newspaper
    Boolean acquireTools[];
    //0 = hammer
    //1 = lighter
    //2 = decoder
    Boolean acquiredOrbs[];
    //0 = green
    //1 = blue
    //2 = red
    Boolean [] kingPuzzleCorrect;
    Button[] orbs;

    //Location declaration
    AlchemyLab alchemyLab;
    Armory armory;
    Cells cell;
    FinalTrial finalTrial;
    Inventory inventory;
    KingRoom kingRoom;
    Library library;
    MusicRoom musicRoom;
    SecretChamber secretChamber;
    Tower tower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Finds the drawer and sets it up
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navView);
        nav.setItemIconTintList(null);
        nav.setNavigationItemSelectedListener(this);

        //Turns on button in top left corner
        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle myToggle = new ActionBarDrawerToggle(this, drawer,0,0);
        drawer.addDrawerListener(myToggle);
        myToggle.syncState();
        /////

        //Sets default values for variables
        kingRoomBest = false;
        foundNewspaper = false;
        count = 0;
        bestCount = 0;
        lives = 3;
        currentChange = 0;
        progress = 5;
        ////

        //Declares the list
        kingPuzzleCorrect = new Boolean[4];
        acquireTools = new Boolean[3];
        acquiredOrbs = new Boolean[3];
        locations = new Boolean[3];
        ////

        //Assigns some objects
        sms = SmsManager.getDefault();
        options = (SelectedOptions) getIntent().getSerializableExtra("Options");
        ////

        //Intents and music declaration
        ending = new Intent(MainActivity.this, Ending.class);
        music = new Intent(MainActivity.this, MusicService.class);
        MusicService.chooseSong(0);
        startService(music);
        ////


        //Assigns buttons to view buttons
        orbs = new Button[3];
        orbs[0] = findViewById(R.id.btnGreen);
        orbs[1] = findViewById(R.id.btnBlue);
        orbs[2] = findViewById(R.id.btnRed);

        //Declares and gives this activity to the fragment locations
        otherAssignments();

        //Makes the arrays have default values
        cleanArrays();
    }

    //Cleans arrays by making them have default values
    private void cleanArrays() {
        for(int i = 0; i < kingPuzzleCorrect.length; i++)
            kingPuzzleCorrect[i] = false;
        for(int i = 0; i < acquireTools.length; i++)
            acquireTools[i] = false;
        for(int i = 0; i < acquiredOrbs.length; i++)
            acquiredOrbs[i] = false;
        for(int i = 0; i < orbs.length; i++)
            orbs[i].setVisibility(View.INVISIBLE);
        for(int i = 0; i < locations.length; i++)
            locations[i] = false;
    }

    //Assign values to the locations and puts in this activity
    private void otherAssignments() {
        alchemyLab = new AlchemyLab();
        armory = new Armory();
        armory.insertActivity(this);
        cell = new Cells();
        cell.insertActivity(this);
        finalTrial = new FinalTrial();
        finalTrial.insertActivity(this);
        inventory = new Inventory();
        inventory.insertActivity(this);
        kingRoom = new KingRoom();
        kingRoom.insertActivity(this);
        library = new Library();
        library.insertActivity(this);
        musicRoom = new MusicRoom();
        musicRoom.insertActivity(this);
        secretChamber = new SecretChamber();
        secretChamber.insertActivity(this);
        tower = new Tower();
        tower.insertActivity(this);
    }

    //Called whenever you miss an answer
    void subtractLives()
    {
        //Infinite lives protects you from losing
        if(options.isInfiniteLives())
            return;

        //Subtracts a life and calls inventory to remove heart
        lives--;
        if(lives == 2)
        {
            inventory.changeVisible(-3);
        }
        else if (lives == 1)
        {
            inventory.changeVisible(-2);
        }
        else if(lives == 0)
        {
            //Game over with no lives
            inventory.changeVisible(-1);
            EndingChanger.changeEnding(2);
            startActivity(ending);
        }
    }

    //This is the worst ending so only the final trial calls it
    //Instant game over
    public void gameOver()
    {
        EndingChanger.changeEnding(1);
        startActivity(ending);
    }

    //Successfully completed the game
    void finishGame()
    {
        //Best ending check
        if(foundNewspaper && options.isBestEnding())
            EndingChanger.changeEnding(4);
        else
            EndingChanger.changeEnding(3);

        startActivity(ending);
    }

    //Closes drawer with button on top left or opens
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(drawer.isDrawerOpen(nav))
            drawer.closeDrawers();
        else
            drawer.openDrawer(nav);

        return super.onOptionsItemSelected(item);
    }

    //The navigation method
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Declares needed stuff
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        FrameLayout frgContain = findViewById(R.id.frgContainer);
        LinearLayout lin = findViewById(R.id.layMain);

        //Message for phone
        String message = "";

        //Close the drawers
       drawer.closeDrawers();

       //Summary: This gets you item id to determine location selected.  IT assigns values to the phone message for your location
        //Main door causes container to disappear and scroll view to appear.  Sends message in its code and returns
        //The rest function normally except for a few locations like hint, secret, final, newspaper that require specific conditions
        //To be met before you can access them.
        switch (item.getItemId()) {
            case R.id.itemDoor:
                findViewById(R.id.frgContainer).setVisibility(View.GONE);
                lin.setVisibility(View.VISIBLE);
                message = "The main gate: A place with great evil emanating from the doorway.  With the three orbs, you could probably get inside.";
                if (options.isPhone())
                    sms.sendTextMessage(options.getPhoneText(), null, message, null, null);
                return false;
            case R.id.itemArmory:
                message = "Armory: You see racks of various tools lining the walls.  On a table, you find a variety spread out before you.";
                transaction.replace(R.id.frgContainer, armory);
                break;
            case R.id.itemBedroom:
                message = "King's Bedroom: You stand in the bedroom of the last king.  On the wall you see a bunch of crest with small symbols and buttons.  The right order might reveal a hidden secret.";
                transaction.replace(R.id.frgContainer, kingRoom);
                break;
            case R.id.itemCells:
                message = "Dungeon: You notice cave drawings across the walls.  Something here might be useful if you examine everything closely.";
                transaction.replace(R.id.frgContainer, cell);
                break;
            case R.id.itemFinal:
                if(!locations[1])
                {
                    Toast.makeText(this, "The door is still sealed shut.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                message = "You enter the final chamber and notice an orb of great evil.  Lighting the torches in the correct order seems to be your only hope.";
                transaction.replace(R.id.frgContainer, finalTrial);
                break;
            case R.id.itemHints:
                if(!options.isHints())
                {
                    Toast.makeText(this, "Hints are not available for this playthrough.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                transaction.replace(R.id.frgContainer, new Hints());
                break;
            case R.id.itemInventory:
                message = "Inventory: You open your backpack and look at everything you collected.";
                transaction.replace(R.id.frgContainer, inventory);
                break;
            case R.id.itemLab:
                message = "Alchemy Lab: You notice four potions sitting in a row.  You begin to get curious about the effects of these potions.";
                transaction.replace(R.id.frgContainer, alchemyLab);
                break;
            case R.id.itemLibrary:
                message = "Library: You enter a room lined with books.  You notice a few on a lone shelf and begin to look closely at them.";
                transaction.replace(R.id.frgContainer, library);
                break;
            case R.id.itemMusicRoom:
                message = "Music Room: You enter the room to find a phonograph and a few records scattered beside it.";
                transaction.replace(R.id.frgContainer, musicRoom);
                break;
            case R.id.itemNewspaper:
                if(!locations[2])
                {
                    Toast.makeText(this, "You need to find this item first.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                message = "Newspaper: You take the newspaper from your bag and begin to read it.";
                transaction.replace(R.id.frgContainer, new Newspaper());
                break;
            case R.id.itemSecret:
                if(!locations[0])
                {
                    Toast.makeText(this, "You need to find this area first.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!acquireTools[1]) {
                    Toast.makeText(this, "You need something to illuminate the area before you can continue.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                message = "Secret: You make your way to the dungeons and climb through the hole in the wall, revealing a secret chamber.";
                transaction.replace(R.id.frgContainer, secretChamber);
                break;
            case R.id.itemTower:
                message = "Tower: You climb up the stairs and head to an area that seems to have a weird control panel for seemingly no reason.";
                transaction.replace(R.id.frgContainer, tower);
                break;
        }

        //Message for occuring and sends phone message if the player selected option
        Toast.makeText(this, "Travel Occurring", Toast.LENGTH_SHORT).show();
        if (options.isPhone())
            sms.sendTextMessage(options.getPhoneText(), null, message, null, null);

        //Makes main disappear and fragment appear
        lin.setVisibility(View.GONE);
        frgContain.setVisibility(View.VISIBLE);

        //Opens the fraction as needed
        transaction.commit();
        return false;
    }

    //This is for best path
    public void checkCount() {
        //Checks to see that you meant all three conditions to get newspaper
        bestCount++;
        if (bestCount == 3)
            openLocation(3);
    }

    //This adds a tool when you find it
    public void setAcquireTools(int i)
    {
        //Determines which tool to add (hammer, lighter, decoder)  Order at top
        //Sets stuff in inventory too
        switch (i)
        {
            case 1:
                acquireTools[0] = true;
                inventory.changeVisible(0);
                setMyProgress(5);
                break;
            case 2:
                acquireTools[1] = true;
                inventory.changeVisible(1);
                setMyProgress(5);
                break;
            case 3:
                acquireTools[2] = true;
                inventory.changeVisible(2);
                setMyProgress(5);
                break;
        }
    }

    //Adds acquired orbs
    public void addOrbs(int i)
    {
        //Determines if red, blue, green is added (Order at top of class)
        //Sets stuff in inventory
        switch (i)
        {
            case 1:
                acquiredOrbs[0] = true;
                inventory.changeVisible(3);
                orbs[0].setVisibility(View.VISIBLE);
                setMyProgress(20);
                break;
            case 2:
                acquiredOrbs[1] = true;
                inventory.changeVisible(4);
                orbs[1].setVisibility(View.VISIBLE);
                setMyProgress(20);
                break;
            case 3:
                acquiredOrbs[2] = true;
                inventory.changeVisible(5);
                orbs[2].setVisibility(View.VISIBLE);
                setMyProgress(20);
                break;
        }
    }

    //Used by king adapter for king room puzzle, just a transfer method for easy use| Could probably be exported to fragment class
    public void kingPuzzlesChange(int i, boolean b)
    {
        kingPuzzleCorrect[i] = b;
    }

    //Opens specified location
    public void openLocation(int i)
    {
        //Opens secret, newspaper, final (order at top)
        switch (i)
        {
            case 1:
                locations[0] = true; //secret
                setMyProgress(5);
                break;
            case 2:
                locations[1] = true; //final
                Toast.makeText(this, "The final trial awaits!", Toast.LENGTH_SHORT).show();
                setMyProgress(5);
                break;
            case 3:
                locations[2] = true; //newspaper
                foundNewspaper = true;
                Toast.makeText(this, "You head down the hallway and notice a strange newspaper that wasn't there before!", Toast.LENGTH_SHORT).show();
                setMyProgress(5);
                break;
        }
    }

    //Gets the options for the fragments
    public SelectedOptions getOptions()
    {
        return options;
    }

    //The button click method
    public void useOrbs(View v)
    {
        //LEts you use orbs, but they must be used in right order.
        //This is puzzle of the main activity
        switch (v.getId())
        {
            case R.id.btnRed:
                if(count ==1)
                {
                    count++;
                    orbs[2].setVisibility(View.INVISIBLE);

                }
                else
                {
                    subtractLives();
                    Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBlue:
                if(count ==2)
                {
                    count++;
                    Toast.makeText(this, "Correct: The door is now open!", Toast.LENGTH_SHORT).show();
                    openLocation(2);
                    orbs[1].setVisibility(View.INVISIBLE);
                }
                else
                {
                    subtractLives();
                    Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGreen:
                count++;
                orbs[0].setVisibility(View.INVISIBLE);
                break;
        }
    }

    //Sets the progress bar
    public void setMyProgress(int i)
    {
        inventory.setProgress(i);
    }

    //Checks the options for if this is best path
    public boolean isBest()
    {
        return options.isBestPath();
    }

    //Lets the music room change the song
    public void changeSong(int cmusic)
    {
        stopService(music);
        MusicService.chooseSong(cmusic);
        startService(music);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Time travel cannot be used here!", Toast.LENGTH_SHORT).show();
    }
}
