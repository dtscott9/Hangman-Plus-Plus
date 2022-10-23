# Overview
This is a continuation of the hangman game that I made in Kotlin. 
That version just used the console and user inputs for the game to run,
but this is a version made in Android Studio with a new ui. The game works
the same as it did in the other version, however in this Android Studio version
the player is given 26 different buttons on the screen that represent the alphabet.
Once the player clicks any one of the buttons, that button disappears and the letter
that the button represents is used as the player guess. 

Demonstration Video: https://www.youtube.com/watch?v=XQrwomgglGk

# Development Environment
I used Android Studio to develop this program because of it's integrated use of
Kotlin and XML files. The IDE made it very easy to connect the two and provided
me with a template that had everything I needed to start writing code. This program
also uses the Kotlin standard library. 

# Useful Links
As far as the tutorials found in these links go, I should clarify that I did not
follow them step by step, but instead only integrated certain elements that I liked
from them. For example, from the video of the last link, I really liked the idea of 
making the letters their own constraint layout, so I modeled my own letter choosing
system after that. However, I did not follow the rest of that video and made everything
else my own to the best of my ability. 
[Kotlin Programming Language](https://kotlinlang.org/)

[Stack Overflow](https://stackoverflow.com/)

[Android App Development Tutorial for Beginners - Your First App](https://www.youtube.com/watch?v=FjrKMcnKahY&t=1981s)

[Android - How to make a Hangman Game in Kotlin](https://www.youtube.com/watch?v=kGGpH7ypxAU&t=1003s)


# Future Work
1. Add sound effects and background music.
2. Figure out how to integrate multiple screens.
3. Create more space between the underscores so player so better tell which letters are separate.
