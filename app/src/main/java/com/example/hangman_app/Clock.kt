package com.example.hangman_app

import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach

class Clock {

    fun create (timer:ImageView, time:TextView) {

        timer.visibility = View.GONE
        time.visibility = View.GONE
    }

    fun TimeUp() {


    }

    fun TimeAttack (timer:ImageView, time:TextView, playerLives:Int, replay:TextView, guessWord:TextView, letters:ConstraintLayout) {
        timer.visibility = View.VISIBLE
        time.visibility = View.VISIBLE
        var seconds : Long = 5000
        var interval : Long = 1000
        var countDownTimer = object : CountDownTimer(seconds, interval) {
            override fun onTick(remaining: Long) {
                var seconds: Int = remaining.toInt() / 1000
                time.text = seconds.toString()
            }

            override fun onFinish() {
                time.text = "0"
                var calculateScore : Int = playerLives * 100
                replay.visibility = View.GONE
                guessWord.text = "Time is Up. Final score is ${calculateScore}"
                letters.forEach { letter ->
                    if (letter is Button)
                    {
                        letter.visibility = View.GONE
                    }
                }


            }
        }.start()

    }
}