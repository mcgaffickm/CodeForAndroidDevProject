package com.example.secretsofthecursedcastle;

public class EndingChanger  {

    //Declaration of variables
    static String title, description;
    static int background, endImage;

    //Lets you change the sample layout to the ending that you earned
    static void changeEnding(int type)
    {
        switch(type)
        {
            //Worst - bad - good - best
            case 1:
                title = "Worst Ending";
                description = "The door seals behind you, trapping you in the room with the dark orb.  The torches fall to pieces as you are left, trapped in the chamber for all eternity.";
                background = R.drawable.worst;
                endImage = R.drawable.worstending;
                break;
            case 2:
                title = "Bad Ending";
                description = "The curse grips the last ounce of your life and transforms  you to stone.  You remain as a statue within the castle, waiting for the next victim to join you.";
                background = R.drawable.bad;
                endImage = R.drawable.badending;
                break;
            case 3:
                title = "Good Ending";
                description = "The orb shatters before you, but the course of history cannot be changed that easily.  You walk outside to find that you have been sealed outside of reality with the village of Wolfheim.  You now have become exiled along with the village.";
                background = R.drawable.good;
                endImage = R.drawable.goodending;
                break;
            case 4:
                title = "Best Ending";
                description = "The dark orb shatters before you, history has changed course causing a throne to be revealed.  You sit down on the throne and a crown appears on your head.  You have now been crowned as the king of Wolfheim as it prepares to enter the real world after hundreds of years of exile.";
                background = R.drawable.best;
                endImage = R.drawable.bestending;
                break;
        }

    }

}
