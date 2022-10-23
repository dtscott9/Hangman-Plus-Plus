package com.example.hangman_app

import android.widget.ImageView

class HangmanFigure {

    fun checkPlayerLives(playerLives:Int, hangMan:ImageView)
    {
        if(playerLives == 6)
        {
            hangMan.setImageResource(R.drawable.hangman1)
        }
        else if(playerLives == 5)
        {
            hangMan.setImageResource(R.drawable.hangman2)
        }
        else if(playerLives == 4)
        {
            hangMan.setImageResource(R.drawable.hangman3)
        }
        else if(playerLives == 3)
        {
            hangMan.setImageResource(R.drawable.hangman4)
        }
        else if(playerLives == 2)
        {
            hangMan.setImageResource(R.drawable.hangman5)
        }
        else if(playerLives == 1)
        {
            hangMan.setImageResource(R.drawable.hangman6)
        }

    }
}