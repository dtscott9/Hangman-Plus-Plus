package com.example.hangman_app

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    // Database Instance
    private var db = FirebaseFirestore.getInstance()
    val player: MutableMap<String, Any> = HashMap()



    // View Variables
    private lateinit var guessWord: TextView
    private lateinit var hangmanImage: ImageView
    private lateinit var letters: ConstraintLayout
    private lateinit var replay: TextView
    private lateinit var timer: ImageView
    private lateinit var time: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var mainMenu: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Database Business
//

        // View Variables linked to Kotlin

        guessWord = findViewById(R.id.wordToGuess)
        hangmanImage = findViewById(R.id.hangmanFigure)
        letters = findViewById(R.id.letters)
        replay = findViewById(R.id.replay)
        timer = findViewById(R.id.timer)
        time = findViewById(R.id.time)
        timer.visibility = View.GONE
        time.visibility = View.GONE
        mainMenu = findViewById(R.id.menu)
        mainMenu.visibility = View.GONE
        // Game Variables
        val intent:Intent = getIntent()
        val timeAttack = intent.getBooleanExtra("gameMode", false)
        val intent1:Intent = getIntent()
        val endless = intent1.getBooleanExtra("gameMode2", false)
        var seconds : Long = 30000
        var interval : Long = 1000
        var playerLives: Int = 6
        var word = Word()
        word.GenRandomWord()
        var wordToGuess = word._wordToGuess
        var terminal = Terminal()
        terminal.createWordToGuess(wordToGuess)
        var hangman = HangmanFigure()
        var score:Int = 0
        var gameStatus: Boolean = true

        //Constants
        var timeText:String = "Time is up"
        var endText:String = "Game Over"

        fun goToMenu() {
            mainMenu.visibility = View.VISIBLE
            mainMenu.setOnClickListener {
                val mainIntent = Intent(this, MainMenu::class.java)
                startActivity(mainIntent)
                finish();
            }
        }

        fun finalScore(text:String) {
            var calculateScore : Int = score * 100
            replay.visibility = View.GONE
            guessWord.text = "${text}. Final score is ${calculateScore}"
            letters.forEach { letter ->
                if (letter is Button)
                {
                    letter.visibility = View.GONE
                }
            }
            goToMenu()

            }




        fun TimeAttack () {
            timer.visibility = View.VISIBLE
            time.visibility = View.VISIBLE

            countDownTimer = object : CountDownTimer(seconds, interval) {
                override fun onTick(remaining: Long) {
                    var seconds :Int = remaining.toInt()/1000
                    time.text = seconds.toString()
//                    if (terminal.dashedWord.concatToString() == wordToGuess) {
//                        //countDownTimer.cancel()
//                    }
//                    else if (playerLives == 0) {
//                       //countDownTimer.cancel()
//                    }
                }

                override fun onFinish() {
                    time.text = "0"
                    finalScore(timeText)



                }
            }.start()

        }

        fun ReplayGame() {
            replay.visibility = View.VISIBLE
            goToMenu()
            replay.setOnClickListener{
                word.GenRandomWord()
                wordToGuess = word._wordToGuess
                terminal.createWordToGuess(wordToGuess)
                playerLives = 6
                player["firstName"] = "John"
                player["lastName"] = "Doe"
                db.collection("players").add(player)
                    .addOnSuccessListener {
                        Toast.makeText(this@MainActivity, "added successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Failed to add", Toast.LENGTH_SHORT).show()
                    }

                letters.forEach { letter ->
                    if (letter is Button)
                    {
                        letter.visibility = View.VISIBLE
                        guessWord.text = terminal.dashedWord.concatToString()

                        hangmanImage.setImageResource(R.drawable.hangman1)

                    }
                }
                replay.visibility = View.GONE
                mainMenu.visibility = View.GONE
//                if (playerLives == 0) {
//                    countDownTimer.start()
//                }

            }
        }


        //Displays Game Over screen
        fun GameLost()
        {
          if (playerLives == 0){
              if (!endless) {
                  if (score > 0) {
                      score -= 1
                  }
              }
              hangmanImage.setImageResource(R.drawable.hangman7)
              guessWord.text = "You Lose\nThe word was:\n${wordToGuess}"
              letters.forEach { letter ->
                  if (letter is Button)
                  {
                      letter.visibility = View.GONE
                  }
              }
              if (!endless) {
              ReplayGame()
          }
              else {
                finalScore(endText)
              }
          }
        }


        //Displays win screen
        fun GameWon()
        {
            if (terminal.dashedWord.concatToString() == wordToGuess) {
                score += 1
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
            if (timeAttack)
            {
                TimeAttack()
            }
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