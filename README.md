# Overview
This is a continuation of my Kotlin hangman project that I'm trying to make more exciting
and action based. The main improvement that I've added is two competitive modes, "Endless"
mode and "Time Attack" mode. In endless mode, the player must guess as many words as they
can without getting one wrong. In time attack, the player is given 60 seconds to guess as
many words as they can. For each word they get right they get 100 points (however in time
attack mode they lose 100 points for each word they get wrong). I connected the program to
a Firestore database to keep track of the player scores, and the program actually pulls from 
the database to display the scores for each player. You can also update and delete the scores
in game. 

Demonstration Video: https://www.youtube.com/watch?v=mbkhECtOf48

# Development Environment
I used Android Studio to develop this program because of it's integrated use of
Kotlin and XML files. The IDE made it very easy to connect the two and provided
me with a template that had everything I needed to start writing code. This program
also uses the Kotlin standard library. 

# Useful Links

[Kotlin Programming Language](https://kotlinlang.org/)

[Stack Overflow](https://stackoverflow.com/)

[Swiping to Delete](https://www.youtube.com/watch?v=xE8z8wiXz18)

[Creating the Recyclerview for the Data](https://www.youtube.com/watch?v=Ly0xwWlUpVM&t=393s)

[How to Create Drawable Animations](https://www.youtube.com/watch?v=scZYIAZrMWk)




# Future Work
1. Add sound effects and background music.
2. Add character animations for when a letter is guessed wrong or right.
3. Allow the score to only be updated for the same player if their new score is higher than their previous.
