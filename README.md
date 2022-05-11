# Team Project Wordle

## Problem Statement
Recreate the online word game Wordle. 

## Developer Documentation
This program contains 4 files controller.java, Database.java, Driver.java and
notWordle.fxml. The controller class contains the functionality of the game,
including all word calculations and input from wordlist/database. The Database
class contains the functionality of the database which stores each user’s
information. It contains the ability for unique user’s identities and stores each
user’s data separately and comparably. The Driver class starts and launches the
JavaFX GUI for the game; notWordle.fxml stores the fxml code for the GUI created
in scene builder. The program is run by running the driver class. 

## User Documentation
When the client starts the Worddle app (driver.java), the user is prompted for a name. This name is used to track the client information in the database. This includes updating the running score of current tournament, retrieving standing results, last time played, and last word played. The player is awarded points based off of how many correct letter selections they make in the correct spot. No points are awarded for letter guesses contained in the word of the day, but in the wrong spot. The player selects letters using the letter buttons on the bottom side of the screen until the row is filled. Once filled, the player must press enter to determine their result. The correct letter guesses in the correct spot will become static, border colored green, and results will be shown on the results section under word inputs. Repeat.

HINT: Word of Day shows up in terminal above where user is prompted for input. 

