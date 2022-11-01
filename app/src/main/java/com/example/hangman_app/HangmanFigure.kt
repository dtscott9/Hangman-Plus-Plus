package com.example.hangman_app

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import android.widget.TextView

class HangmanFigure {



    fun checkPlayerLives(playerLives:Int, hangMan:ImageView, animation:AnimationDrawable, health:TextView)
    {
        when (playerLives) {
            6 -> {
                hangMan.setBackgroundResource(R.drawable.hero_animation_idle)

            }
            5 -> {
                health.text = "${playerLives}/6"
            }
            4 -> {
                health.text = "${playerLives}/6"
            }
            3 -> {
                health.text = "${playerLives}/6"
            }
            2 -> {
                health.text = "${playerLives}/6"
            }
            1 -> {
                health.text = "${playerLives}/6"
            }
        }

    }
}