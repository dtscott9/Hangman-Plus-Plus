package com.example.hangman_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    public lateinit var startGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        startGame = findViewById(R.id.gameStart)
        startGame.setOnClickListener {
            val intent = Intent(this, com.example.hangman_app.MainActivity::class.java)
            startActivity(intent)
            finish();
        }
    }
}