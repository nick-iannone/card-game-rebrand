
# <b>CARD GAME</b>

## Welcome to <b>Card Game</b>, a strategy card game made in the ilk of <i>Magic: the Gathering</i> and <i>Hearthstone</i>

This project uses Vue.js, Vuex, Vue Router, Java, and PostgreSQL

TODO project installation / environment setup

---

## Card Game (formal title pending) is a player-vs-player strategy card game that plays out in real time
![alt text](screens/Sci-Fi%20Rebrand/home_small.png)

---

## Players can browse the entire collection of cards and contracts that are avialable to them by selecting the **BROWSE** option

* A variety of filters are available to the player so that they can look for specific cards, or cards that meet certain criteria

![alt text](screens/Sci-Fi%20Rebrand/browse_small.png)

*Viewing Troop cards*

![alt text](screens/Sci-Fi%20Rebrand/browse_contracts_small.png)

*Viewing contracts*

---


## Players construct decks out of many different cards using the **DECK BUILDER** view

<b>A deck consists of the following:</b>

* 30 <b>"Troop"</b> cards:
    * These cards have varying power levels depending on faction, indicated by coloring/styling: 
        * Hallowed / Dreaded (maximum of 1 copy of these cards per deck)
        * Fabled / Feared (maximum of 2 copies of these cards per deck)
        * Heroic / Vile (maximum of 3 copies of these cards per deck)
        * All other cards are considered <i>basic</i> (maximum of 4 copies of these cards per deck)
    
    * A deck cannot exceed 30 Troop cards
    * These are kept in a separate deck from the Contract cards

* 15 <b>"Contract"</b> cards:
    * A deck can include up to two copies of a Contract
    * These are kept in a separate deck from the Troop cards

![alt text](screens/Sci-Fi%20Rebrand/deck_builder_in_progress_cards_small.png)


![alt text](screens/Sci-Fi%20Rebrand/deck_builder_in_progress_contracts_small.png)

---

## Players can see / favorite their decks in the **MY DECKS** view

<b>Favorited decks will have a green background</b>  
* The deck's contents are listed, separated by cards and contracts
* By clicking **'EDIT'**, the user can load their deck into the deck editor to modify its contents

![alt text](screens/Sci-Fi%20Rebrand/my_decks_small.png)

---

## Using the green speech bubble icon in the top-left corner of the screen, players can access the **CHAT** feature

* Clicking the red speech bubble icon again at any time will hide the Chat feature
* In the future, 
* Currently, all online users are displayed
* Start a chat with someone by clicking on their username
* Your username is shown as highlighted in green
* Users can disconnect from the Chat feature and appear as offline by clicking the disconnect button
* Some features I would like to implement in the future are:
    * Unread message notifications / markers
    * The ability to add other users as friends
    * Ability to block users
    
  
![alt text](screens/Sci-Fi%20Rebrand/main_menu_chat_msg_hidden_small.png)


![alt text](screens/Sci-Fi%20Rebrand/main_menu_chat_overlay_small.png)

---

## By hitting the **"PLAY"** button, players can ready-up for a match, and see other players who are waiting for an opponent

* Hitting **'READY-UP'** will send users to the pre-match screen until an opponent is found
* If there is another user waiting for an opponent, you can match with them by clicking **'JOIN'** next to their username

![alt text](screens/Sci-Fi%20Rebrand/matchmaking_game_selector_small.png)

---


## **Once matched, players select their decks to take into battle**

* Only one deck can be selected for a match
* Until you match with another player, the scrolling text will say 'WAITING FOR OPPONENT'
* After you match, the scrolling text will read "PREPARING FOR BATTLE" until they lock in their selection
* When your opponent is ready, the scrolling text will say 'READY FOR BATTLE'
* You will not be able to Ready for battle until a deck is selected
* Factions (as seen in the screens) have yet to be implemented, but will change your fundamental strategy in a given game

![alt text](screens/Sci-Fi%20Rebrand/mm_no_selections_small.png)


![alt text](screens/Sci-Fi%20Rebrand/matchmaking_no_opponent_small.png)


![alt text](screens/Sci-Fi%20Rebrand/matchmaking_matched_small.png)

---


# ***THE BATTLE ARENA***

## **This is where the game is played** - with game state management and updates happening via an open Websocket connection with the server

* Players "roll" to see who goes first, and then get the opportunity to either mulligan or keep their hand of cards as well as the available contracts
* Once both players have made decisions about their hands and contracts, play can begin

![alt text](screens/Sci-Fi%20Rebrand/arena_roll_phase_small.png)


![alt text](screens/Sci-Fi%20Rebrand/arena_mulligan_phase_small.png)

---

## **Status tiles**

* The two status bars on the left side of the screen display some crucial information about each player
    * The player's username is seen at the top
    * The yellow coins icon indicates how many **Credits** the player has to spend
    * The grey building icon indicates the health of the player's **Headquarters** or **HQ**
    * The green web icon indicates the player's **Stability**
    * The blue chip icon indicates how many subroutines (contracts) the player currently has active
    * The red stack/burger icon indicates how many cards remain in the player's deck
    * The teal hand icon indicates how many cards that player currently has in their hand  

* The active player's status tile will be outlined in a yellow glow when it is currently their turn
* Troop cards that are able to be played (the player has enough credits) are outlined in a yellow glow  
* Contracts/Subroutines that are able to be played are outlined in a blue glow  

![alt text](screens/Sci-Fi%20Rebrand/arena_my_turn_small.png)

---

## **Playing Troop cards and making Attacks**

* To play a card, drag it into the **Combat Zone**, the empty space just above the player's hand  
* Troops that are able to make an attack will be outlined with a green glow
* Players can use Troops to attack the enemy player's **HQ** or to attack opposing Troop cards
* **TO MAKE AN ATTACK:**
    * First, ***right-click*** on the Troop you wish to attack with
        * The Troop card will begin the attack animation, outlined in a red glow with a hover effect
    * If there are no enemy Troops, the Troop will attack the opposing **HQ** automatically
    * If there are enemy Troops in the opposing **Combat Zone**, a target must be selected
        * To attack the enemy **HQ** directly, right click on the **HQ** icon in your opponent's status tile
            * The **HQ** row will become briefly outlined in red and shake, indicating an attack was made
        * To attack an enemy Troop, right click on the Troop you wish to attack
            * The Troop that was attacked will become outlined in a blue glow, and the Troops will do Combat
                * Each troop assigns its **Attack** value (left-most number) to the opposing Troop's **Resilience** value (middle number)
                * Troops with 0 Resilience leave the Combat Zone

![alt text](screens/Sci-Fi%20Rebrand/arena_attacking_troop_small.png)

*A troop readying for an attack.*

![alt text](screens/Sci-Fi%20Rebrand/arena_combat_small.png)

*An attack was made. The damage has been assigned to both troops.*

![alt text](screens/Sci-Fi%20Rebrand/arena_hq_attack_small.png)

*A direct attack made to the opponent's HQ.*

---

## **Contracts / Subroutines**

<p>In the first version of **Card Game**, players did battle in a medieval setting with Knights, Soldiers, Ballistas, etc. Since then, Card Game has been rebranded to embrace a futuristic, Sci-Fi aesthetic. What were once called <b>Contracts</b> are now called <b>Subroutines</b>. There are some holdovers of the previous nomenclature that will be gradually phased out.</p>  

* Subroutines that have been purchased are seen in the Active Subroutines section
* Active Subroutines trigger at the beginning of the player's turn and produce some sort of effect
* There are many types of Subroutines:
    * Subroutines that produce additional resources
    * Subroutines that create Troops
    * Subroutines that interact with enemy troops
    * Subroutines that interact with friendly troops
    * Subroutines that generate card advantage (draw additional cards/subroutines)


---

## **Cards and Subroutines that ask the player to select a target**

<p>Some Troop cards and some Subroutines will ask the player to select a target. The targeting system enables a different styling, highlighting available targets to the player. Other functionality is disabled at this time.</p>

* Once the targeting mode has been activated, players must **right-click** on the target to select it
* The targeting mode styling will vary, depending on whether a friendly or enemy troop is being targeted


![alt text](screens/Sci-Fi%20Rebrand/arena_selecting_friendly_target_small.png)

*Selecting a friendly target.*


![alt text](screens/Sci-Fi%20Rebrand/arena_selecting_enemy_target_alt_small.png)

*Selecting an enemy target.*

---

## Players battle until either **HQ** reaches 0, or either **Stability** reaches 0

* When a player is victorious, a victory modal will appear
* The same is true for defeat
* Exiting the game will return the player to the Home screen

![alt text](screens/Sci-Fi%20Rebrand/victory_modal_small.png)

*Congrats! You are victorious!*

![alt text](screens/Sci-Fi%20Rebrand/defeat_modal_small.png)

*Better luck next time!*

---

## The best is yet to come

**As Card Game continues to grow, there are a few things I want to see happen:**

* The game logic is currently living in Vue, on the frontend. This needs to be moved to the server and be shared by the clients so that there is always continuity
* Animations and sounds will give the game the level of immersion that gets me excited to play a game
* The game itself is in need of refinement, particularly Factions and deck building restrictions

<p>That is all I have for you now. A more technical ReadMe is in the works and will be forthcoming soon!</p>

---


# **Thanks for stopping by and reading about CARD GAME!**