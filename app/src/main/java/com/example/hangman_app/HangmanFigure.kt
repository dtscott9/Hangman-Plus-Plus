package com.example.hangman_app

import android.widget.ImageView

class HangmanFigure {


    fun checkPlayerLives(playerLives:Int, hangMan:ImageView)
    {
        when (playerLives) {
            6 -> {
                hangMan.setImageResource(R.drawable.hangman1)
            }
            5 -> {
                hangMan.setImageResource(R.drawable.hangman2)
            }
            4 -> {
                hangMan.setImageResource(R.drawable.hangman3)
            }
            3 -> {
                hangMan.setImageResource(R.drawable.hangman4)
            }
            2 -> {
                hangMan.setImageResource(R.drawable.hangman5)
            }
            1 -> {
                hangMan.setImageResource(R.drawable.hangman6)
            }
        }

    }
}