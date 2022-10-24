package com.example.hangman_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    private lateinit var startGame: Button
    private lateinit var timeAttack: Button
    private lateinit var endless: Button
    public var timeAttackStatus : Boolean = false
    public var endlessStatus : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        startGame = findViewById(R.id.gameStart)
        timeAttack = findViewById(R.id.timeAttack)
        endless = findViewById(R.id.Endless)
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
    }
}