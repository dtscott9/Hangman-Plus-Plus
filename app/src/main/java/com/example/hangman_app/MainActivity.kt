package com.example.hangman_app

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    // Database Instance
    private var db = Firebase.firestore
    val player: MutableMap<String, Any> = HashMap()


    //Character Animations
    public lateinit var idleAnimation: AnimationDrawable
    public lateinit var dyingAnimation: AnimationDrawable
    private lateinit var hangmanImage: ImageView
    public lateinit var dyingImage: ImageView
    private lateinit var attackImage: ImageView
    private lateinit var trollImage: ImageView
    private lateinit var trollIdleAnimation: AnimationDrawable
    private lateinit var trollDeathImage: ImageView
    private lateinit var trollDeathAnimation: AnimationDrawable
    private lateinit var heroAttackAnimation: AnimationDrawable
    private lateinit var heroAttackImage: ImageView

    // View Variables

    private lateinit var guessWord: TextView
    private lateinit var letters: ConstraintLayout
    private lateinit var replay: TextView
    private lateinit var timer: ImageView
    private lateinit var time: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var mainMenu: TextView
    private lateinit var nameInput: EditText
    private lateinit var submit: TextView
    private lateinit var healthBox: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Database Business
        var db_collection:String = "name"
        var dbPath1:String = "Time Attack"
        var dbPath2:String = "Endless"

        //Animations
        hangmanImage = findViewById(R.id.hangmanFigure)
        hangmanImage.setBackgroundResource(R.drawable.hero_animation_idle)
        idleAnimation = hangmanImage.background as AnimationDrawable
        dyingImage = findViewById(R.id.dying)
        dyingImage.setBackgroundResource(R.drawable.dying_animation)
        dyingAnimation = dyingImage.background as AnimationDrawable
        dyingImage.visibility = View.GONE
        trollImage = findViewById(R.id.trollImage)
        trollImage.setBackgroundResource(R.drawable.troll_idle)
        trollIdleAnimation = trollImage.background as AnimationDrawable
        trollDeathImage = findViewById(R.id.trollDie)
        trollDeathImage.setBackgroundResource(R.drawable.troll_die)
        trollDeathAnimation = trollDeathImage.background as AnimationDrawable
        trollDeathImage.visibility = View.GONE
        heroAttackImage = findViewById(R.id.heroWin)
        heroAttackImage.setBackgroundResource(R.drawable.hero_animation_attacking)
        heroAttackAnimation = heroAttackImage.background as AnimationDrawable
        heroAttackImage.visibility = View.GONE


        // View Variables linked to Kotlin
        guessWord = findViewById(R.id.wordToGuess)
        letters = findViewById(R.id.letters)
        replay = findViewById(R.id.replay)
        timer = findViewById(R.id.timer)
        time = findViewById(R.id.time)
        timer.visibility = View.GONE
        time.visibility = View.GONE
        mainMenu = findViewById(R.id.menu)
        mainMenu.visibility = View.GONE
        nameInput = findViewById(R.id.name)
        submit = findViewById(R.id.submit)
        nameInput.visibility = View.GONE
        submit.visibility = View.GONE
        healthBox = findViewById(R.id.health)

        // Game Variables
        val intent:Intent = getIntent()
        val timeAttack = intent.getBooleanExtra("gameMode", false)
        val intent1:Intent = getIntent()
        val endless = intent1.getBooleanExtra("gameMode2", false)
        var seconds : Long = 60000
        var interval : Long = 1000
        var playerLives: Int = 6
        var word = Word()
        word.GenRandomWord()
        var wordToGuess = word._wordToGuess
        var terminal = Terminal()
        terminal.createWordToGuess(wordToGuess)
        var hangman = HangmanFigure()
        var score:Int = 0
        var calculateScore:Int = 0
        var gameStatus: Boolean = true
        healthBox.text = "${playerLives}/6"

        //Constants
        var timeText:String = "Time is up"
        var endText:String = "Game Over"

        fun getPlayerid() {

        }

        fun goToMenu() {
            mainMenu.visibility = View.VISIBLE
            mainMenu.setOnClickListener {
                val mainIntent = Intent(this, MainMenu::class.java)
                startActivity(mainIntent)
                finish();
            }
        }

        fun submitScore(score:String) {
            submit.setOnClickListener {
                db_collection = nameInput.text.toString()
                val playerPath:String = "${db_collection+score}"
                player["name"] = db_collection;
                player["mode"] = score
                player["score"] = "0"
                db.collection("names").document(playerPath).set(player)
                    .addOnSuccessListener {
                        Toast.makeText(this@MainActivity, "added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Failed to add", Toast.LENGTH_SHORT)
                            .show()
                    }
                db.collection("names").document(playerPath)
                    .update("score", calculateScore.toString())
                //player[score] = calculateScore.toString()


                val mainIntent = Intent(this, MainMenu::class.java)
                startActivity(mainIntent)
                finish()
            }
        }


        fun finalScore(text:String, scoreType:String) {
            calculateScore = score * 100
            replay.visibility = View.GONE
            nameInput.visibility = View.VISIBLE
            submit.visibility = View.VISIBLE
            hangmanImage.visibility = View.GONE
            trollImage.visibility = View.GONE
            guessWord.text = "${text}. Final score is ${calculateScore}"
            letters.forEach { letter ->
                if (letter is Button)
                {
                    letter.visibility = View.GONE
                }
            }

            goToMenu()
            submitScore(scoreType)


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
                    finalScore(timeText, dbPath1)



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
                healthBox.text = "${playerLives}/6"

                letters.forEach { letter ->
                    if (letter is Button)
                    {
                        letter.visibility = View.VISIBLE
                        guessWord.text = terminal.dashedWord.concatToString()
                        hangmanImage.visibility = View.VISIBLE
                        dyingImage.visibility = View.GONE
                        trollDeathImage.visibility = View.GONE
                        heroAttackImage.visibility = View.GONE
                        trollImage.visibility = View.VISIBLE
//                        hangmanImage.setImageResource(R.drawable.hangman1)

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
              healthBox.text = "0/6"
              hangmanImage.visibility = View.GONE
              trollImage.visibility = View.GONE
              dyingImage.visibility = View.VISIBLE
              dyingAnimation.start()
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
                finalScore(endText, dbPath2)
              }
          }
        }


        //Displays win screen
        fun GameWon()
        {
            if (terminal.dashedWord.concatToString() == wordToGuess) {
                score += 1
                hangmanImage.visibility = View.GONE
                trollImage.visibility = View.GONE
                trollDeathImage.visibility = View.VISIBLE
                heroAttackImage.visibility = View.VISIBLE
                heroAttackAnimation.start()
                trollDeathAnimation.start()
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
                        hangman.checkPlayerLives(playerLives, hangmanImage, idleAnimation, healthBox)
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
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        idleAnimation.start()
        dyingAnimation.stop()
        trollIdleAnimation.start()
        trollDeathAnimation.stop()
        heroAttackAnimation.stop()
    }
}