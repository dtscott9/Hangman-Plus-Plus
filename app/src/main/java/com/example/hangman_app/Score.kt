package com.example.hangman_app

class Score {
    private var timeScore : String = "score"
    private var endlessScore: String = "score"

    init {
        //empty constructor needed
    }

    constructor(timeScore:String, endlessScore:String) {
        this.timeScore = timeScore
        this.endlessScore = endlessScore
    }


}