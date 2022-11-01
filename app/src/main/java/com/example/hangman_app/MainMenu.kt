package com.example.hangman_app

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainMenu : AppCompatActivity() {
    public lateinit var idleAnimation: AnimationDrawable
    private lateinit var startGame: Button
    private lateinit var timeAttack: Button
    private lateinit var endless: Button
    private lateinit var highScores: Button
    public var timeAttackStatus : Boolean = false
    public var endlessStatus : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        var imageView:ImageView = findViewById(R.id.imageView)
        imageView.setBackgroundResource(R.drawable.hero_animation_idle)
        idleAnimation = imageView.background as AnimationDrawable
        startGame = findViewById(R.id.gameStart)
        timeAttack = findViewById(R.id.timeAttack)
        endless = findViewById(R.id.Endless)
        highScores = findViewById(R.id.highScore)
        startGame.setOnClickListener {
            val intent = Intent(this, com.example.hangman_app.MainActivity::class.java)
            startActivity(intent)
            finish();
        }
        timeAttack.setOnClickListener {
            timeAttackStatus = true
            val intent1 = Intent(this, MainActivity::class.java)
            intent1.putExtra("gameMode", timeAttackStatus)

            startActivity(intent1)
            finish()
        }
        endless.setOnClickListener{
            endlessStatus = true
            val intent2 = Intent(this, MainActivity::class.java)
            intent2.putExtra("gameMode2", endlessStatus)
            startActivity(intent2)
            finish()
        }
        highScores.setOnClickListener {
           val intent3 = Intent(this, HighScores::class.java)
            startActivity(intent3)
            finish()
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        idleAnimation.start()
    }
}