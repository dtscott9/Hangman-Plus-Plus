package com.example.hangman_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach

class MainActivity : AppCompatActivity() {
    // View Variables
    private lateinit var guessWord: TextView
    private lateinit var hangmanImage: ImageView
    private lateinit var letters: ConstraintLayout
    private lateinit var replay: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Variables linked to Kotlin
        guessWord = findViewById(R.id.wordToGuess)
        hangmanImage = findViewById(R.id.hangmanFigure)
        letters = findViewById(R.id.letters)
        replay = findViewById(R.id.replay)
        // Game Variables
        var playerLives: Int = 6
        var word = Word()
        word.GenRandomWord()
        var wordToGuess = word._wordToGuess
        var terminal = Terminal()
        terminal.createWordToGuess(wordToGuess)
        var hangman = HangmanFigure()
        var gameStatus: Boolean = true

        fun ReplayGame() {
            replay.visibility = View.VISIBLE
            replay.setOnClickListener{
                word.GenRandomWord()
                wordToGuess = word._wordToGuess
                terminal.createWordToGuess(wordToGuess)
                letters.forEach { letter ->
                    if (letter is Button)
                    {
                        letter.visibility = View.VISIBLE
                        guessWord.text = terminal.dashedWord.concatToString()
                        playerLives = 6
                        hangmanImage.setImageResource(R.drawable.hangman1)
                    }
                }
                replay.visibility = View.GONE
            }
        }

        //Displays Game Over screen
        fun GameLost()
        {
          if (playerLives == 0){
              hangmanImage.setImageResource(R.drawable.hangman7)
              guessWord.text = "You Lose\nThe word was:\n${wordToGuess}"
              letters.forEach { letter ->
                  if (letter is Button)
                  {
                      letter.visibility = View.GONE
                  }
              }
              ReplayGame()
          }
        }
        //Displays win screen
        fun GameWon()
        {
            if (terminal.dashedWord.concatToString() == wordToGuess) {
                guessWord.text = "Congratulations!\nThe word was:\n${wordToGuess}"
                letters.forEach { letter ->
                    if (letter is Button)
                    {
                        letter.visibility = View.GONE
                    }
                }
                ReplayGame()
            }

        }
//
//        //Checks to see if the word still contains underscores
        fun Isplaying() {
            replay.visibility = View.GONE
            guessWord.text = terminal.dashedWord.concatToString()
            letters.children.forEach { letter ->
                if (letter is Button) {
                    letter.setOnClickListener {
                        terminal.playerGuess = letter.text[0]
                        terminal.checkUserGuess(guessWord, wordToGuess)
                        if (!terminal.status)
                            if (playerLives >= 1)
                                playerLives -= 1
                        hangman.checkPlayerLives(playerLives, hangmanImage)
                        guessWord.text = terminal.dashedWord.concatToString()
                        letter.visibility = View.GONE
                        GameWon()
                        GameLost()
                    }
                }
            }
        }
        Isplaying()
    }
}