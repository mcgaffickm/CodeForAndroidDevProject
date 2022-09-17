package com.example.secretsofthecursedcastle;

public class EndingChanger  {
    static String title, description;
    static int background, endImage;

    static void changeEnding(int type)
    {
        switch(type)
        {
            //Worst - bad - good - best
            case 1:
                title = "Worst Ending";
                description = "";
                background = R.drawable.worst;
                endImage = R.drawable.worstending;
                break;
            case 2:
                title = "Bad Ending";
                description = "";
                background = R.drawable.bad;
                endImage = R.drawable.badending;
                break;
            case 3:
                title = "Good Ending";
                description = "";
                background = R.drawable.good;
                endImage = R.drawable.goodending;
                break;
            case 4:
                title = "Best Ending";
                description = "";
                background = R.drawable.best;
                endImage = R.drawable.bestending;
                break;
        }

    }

}
