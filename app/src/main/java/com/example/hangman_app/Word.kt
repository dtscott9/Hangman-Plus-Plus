package com.example.hangman_app

class Word {
    public var _wordToGuess:String = " "

    val wordList = arrayOf<String>("python", "java", "javascript", "mysql", "function", "class",
        "object", "variable", "loop", "visualstudio", "instance", "csharp", "statement", "script",
        "method", "code", "program", "software", "kotlin", "bug", "jetbrains", "node", "expression",
        "conditional", "data")

    //This method chooses a random word from the array above
    public fun GenRandomWord(): String {
        var newWord = wordList.random()
        _wordToGuess = newWord
        return _wordToGuess
    }
}