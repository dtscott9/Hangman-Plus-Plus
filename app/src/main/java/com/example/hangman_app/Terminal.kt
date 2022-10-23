package com.example.hangman_app

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import java.util.*

class Terminal {

    public var playerGuess:Char = 'Q'

    public var status:Boolean = false

    public var dashedWord:CharArray = charArrayOf()

    public fun checkUserGuess(displayWord:TextView, correctWord:String) {

        var correctIndex = correctWord.indexOf(playerGuess)

        if (correctWord.contains(playerGuess)) {
            //This while loop checks to see if the letter guessed is in the word more than once
            while (correctIndex >= 0)
            {
                dashedWord.set(correctIndex, playerGuess)
                correctIndex = correctWord.indexOf(playerGuess, correctIndex + 1)
                status = true
            }
        }
        else
        {
            status = false
        }

        displayWord.text = dashedWord.concatToString()
    }

    public fun createWordToGuess(correctWord: String): CharArray {
        dashedWord = correctWord.toCharArray()
        val wordCount = dashedWord.count()
        var index:Int = 0
        //This while loop goes through the index of each letter in the word to guess and converts it to an underscore
        while (index < wordCount)
        {

            dashedWord.set(index, '_')
            print(' ')

            index += 1
        }
        return dashedWord
    }
}