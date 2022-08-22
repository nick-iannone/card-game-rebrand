<template>
  <div class="game-setup">
    <div class="player-panel">
      <div class="left-side-panel">
        <playerpanel />
        <!-- <div class="leader-tile"></div> -->
        <div class="mm-button-cont">
          <button class="ready-button" :class="{ ready: this.readyForBattleMessage.readyForBattle }"
            @click.prevent="toggleReadyForBattle" :disabled="disableButton">
            READY FOR BATTLE
          </button>
          <button class="leave-mm-button" @click.prevent="retreat">
            LEAVE GAME
          </button>
        </div>
      </div>
      <!-- && this.selectedDeck != null -->
      <div class="decks-container">
        <div class='deck-flex-bb'>
          <decktile v-for="deck in pageOfItems" :key="deck.deckID" :deck="deck"
            @toggleSelectedDeck="toggleSelectedDeck" />
        </div>
        <jw-pagination :disableDefaultStyles="true" :items="myDecks" @changePage="onChangePage" :pageSize="3" :labels="{
          previous: '<',
          next: '>',
        }"></jw-pagination>
      </div>
    </div>

    <div class="vs" v-if="
      this.$store.state.game.myGame.readyForBattle === false ||
      this.$store.state.game.oppGame.readyForBattle === false
    ">
      VS
    </div>

    <div class="vs-cd" v-if="launchGame">
      THE GAME IS STARTING IN...
      <countdown v-on:startGame="sendToGame" />
    </div>

    <div class="player-panel">
      <div class="waiting-cont" v-if="this.$store.state.game.oppGame.username === ''">
        <p class="waiting">WAITING FOR OPPONENT...</p>
      </div>

      <div class="opp-panel" v-if="this.$store.state.game.oppGame.username != ''">
        <div class="left-side-panel">
          <div class="opp-player-banner">
            <div class="banner-top-row">
              <img class="opp-player-avatar" src="../assets/cards/vance.jpg" />
              <div class="opp-banner-username">
                {{ this.$store.state.game.oppGame.username }}
              </div>
            </div>

            <div class="stats-top-row">
              <div class="user-stats-level">Lvl. </div>
              <div class="user-stats-level-num">27</div>
              <div class="user-stats-wins">Wins: </div>
              <div class="user-stats-level-num">1,622</div>
              <div class="user-stats-wins">Feat Score: </div>
              <div class="user-stats-level-num">39,500</div>
            </div>
          </div>
          <div class="opp-ready-status">
            <div class="waiting-cont" v-if="this.$store.state.game.oppGame.readyForBattle === false">
              <p class="waiting">preparing for battle...</p>
            </div>
            <div class="waiting-cont" v-if="this.$store.state.game.oppGame.readyForBattle === true">
              <p class="waiting">READY TO FIGHT!</p>
            </div>
          </div>
          <!-- <div class="leader-tile"></div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Countdown from "@/components/Countdown.vue";
import CardService from "@/services/CardService.js";
import decktile from "@/components/DeckTile.vue";
import playerpanel from "@/components/PlayerBanner.vue";

export default {
  components: {
    Countdown,
    decktile,
    playerpanel
  },
  data() {
    return {
      selectedDeck: {},
      myDecks: [],
      pageOfItems: [],
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: this.$store.state.sessionId,
        action: "",
      },
      readyForBattleMessage: {
        username: this.$store.state.user.username,
        readyForBattle: false,
      },
    };
  },
  computed: {
    renderButton() {
      if (this.$store.state.game.oppGame.username != '') {
        if (this.selectedDeck.cards && this.selectedDeck.contracts) {
          return true;
        }
      }
      return false;
    },
    launchGame() {
      if (
        this.$store.state.game.myGame.readyForBattle === true &&
        this.$store.state.game.oppGame.readyForBattle === true
      ) {
        this.sendToGame();
        return true;
      }
      return false;
    },
    disableButton() {
      if (
        !this.selectedDeck.cards && !this.selectedDeck.contracts
      ) {
        return true;
      }
      return false;
    },
  },
  methods: {
    toggleSelectedDeck(deck) {
      deck.isSelected = !deck.isSelected;
      if (deck.isSelected) {
        for (var i = 0; i < this.myDecks.length; i++) {
          if (deck.deckID != this.myDecks[i].deckID) {
            this.myDecks[i].isSelected = false;
          }
        }
        this.selectedDeck = deck;
      }
      if (!deck.isSelected) {
        this.selectedDeck = {};
      }
    },
    retreat() {
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify("")
      );
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_OPPGAME_RFB", false);
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.commit("SET_GAME_DECK", {});
      this.$store.state.matchmakingSubscription.unsubscribe();
      this.$router.push({ name: "find-match" });
    },
    leaving() {
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify("")
      );
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.commit("SET_OPPGAME_RFB", false);
      this.$store.commit("SET_GAME_DECK", {});
    },
    toggleReadyForBattle() {
      if (this.readyForBattleMessage.readyForBattle === true) {
        this.readyForBattleMessage.readyForBattle = false;
        this.$store.commit(
          "SET_MYGAME_RFB",
          this.readyForBattleMessage.readyForBattle
        );
      } else if (this.readyForBattleMessage.readyForBattle === false) {
        this.readyForBattleMessage.readyForBattle = true;
        this.$store.commit(
          "SET_MYGAME_RFB",
          this.readyForBattleMessage.readyForBattle
        );
      }
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify(this.readyForBattleMessage)
      );
    },
    sendToGame() {
      this.$store.state.matchmakingSubscription.unsubscribe();
      this.$store.commit('SET_GAME_DECK', this.selectedDeck);
      this.$router.push({ name: "arena" });
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
  },
  mounted() {
    window.addEventListener("beforeunload", this.leaving);

    CardService.getDecksByUsername(this.$store.state.user.username).then(
      (response) => {
        this.myDecks = response.data;
      }
    );
  },
};
</script>

<style>
.game-setup {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/backgrounds/pinned.jpg");
  background-repeat: no-repeat;
}

.player-panel {
  border: 1px solid rgb(116, 116, 116);
  border-radius: 10px;
  width: 1500px;
  height: 450px;
  margin: 15px 0px 15px 0px;
  background-image: linear-gradient(180deg,
      rgba(0, 0, 0, 0.7),
      rgba(0, 0, 0, 0.9));
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}

.left-side-panel {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}

.mm-button-cont {
  display: flex;
  flex-direction: row;
  margin-top: 10px;
}

.ready-button {
  width: 280px;
  height: 55px;
  background-color: black;
  border: 1px solid white;
  border-radius: 10px;
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
  font-size: 20px;
  font-weight: 600;
  margin: 10px 10px 20px 10px;
  outline: none;
}

.ready-button:hover {
  background-image:
    linear-gradient(rgb(43, 100, 255), rgb(1, 29, 107));
  border: 2px solid rgb(255, 255, 255);
  cursor: pointer;
  outline: none;
}

.ready {
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(rgb(43, 100, 255), rgb(1, 29, 107));
  border: 2px solid rgb(255, 255, 255);
  box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(43, 100, 255);
}

.ready:focus {
  outline: none;
}

.leave-mm-button {
  width: 45%;
  height: 55px;
  background-color: black;
  border: 1px solid white;
  border-radius: 10px;
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
  font-size: 20px;
  font-weight: 600;
  margin: 10px 10px 20px 10px;
  outline: none;
}

.leave-mm-button:hover {
  background-color: rgb(255, 15, 15);
  cursor: pointer;
  outline: none;
}

.deck-flex-bb {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 40px;
  width: 900px;
}

.vs {
  font-size: 100px;
  margin: -20px 0px -10px 0px;
  color: white;
  font-family: 'Orbitron', sans-serif;
  text-shadow: 8px 8px rgba(0, 0, 0, 0.575);
  z-index: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 20%;
}

.vs-cd {
  font-size: 80px;
  margin: 0px 0px -0px 0px;
  color: white;
  font-family: 'Orbitron', sans-serif;
  text-shadow: 8px 8px rgba(0, 0, 0, 0.575);
  z-index: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.waiting-cont {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.waiting {
  position: relative;
  text-transform: uppercase;
  letter-spacing: 4px;
  overflow: hidden;
  background: linear-gradient(90deg, #000, rgb(255, 255, 255), #000);
  background-repeat: no-repeat;
  background-size: 80%;
  animation: animate 3s linear infinite;
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: rgba(255, 255, 255, 0);
  font-family: 'Orbitron', sans-serif;
  font-size: 60px;
}

@keyframes animate {
  0% {
    background-position: -500%;
  }

  100% {
    background-position: 500%;
  }
}

.opp-banner-username {
  width: 400px;
  font-size: 40px;
  color: white;
  font-weight: 800;
  font-family: 'Orbitron', sans-serif;
  padding: 10px 10px 10px 20px;
  align-self: center;
  border-right: 1px solid white;
  border-top: 1px solid white;
  border-bottom: 1px solid white;
  border-bottom-right-radius: 8px;
  border-top-right-radius: 8px;
  ;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(black, rgba(206, 30, 30, 0.7));
}
.opp-player-avatar {
  height: 70px;
  width: 70px;
  border: 1px solid white;
  border-bottom-left-radius: 8px;
  border-top-left-radius: 8px;
}
</style>
