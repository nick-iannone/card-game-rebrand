<template>
  <div :class="setClassByFavorite(deck)">
    <div class="deck-title">{{ deck.name }}</div>
    <div
      class="deck-img-cont"
      :style="{
        backgroundImage: `url(${require('@/assets/cards/' +
          deck.cards[29].imageName)}`,
      }"
    ></div>
    <div class="deck-contents">
      <div class="deck-contents-header">DECK CONTENTS</div>
      <div class="contents-row">
        <div class="deck-cards-column">
          <b>CARDS</b>
          <div v-for="name in generateCardNames(deck)" :key="name">
            {{ name }}
          </div>
        </div>
        <div class="deck-cards-column">
          <b>CONTRACTS</b>
          <div v-for="name in generateContractNames(deck)" :key="name">
            {{ name }}
          </div>
        </div>
      </div>
    </div>
    <div>
      <button class="deck-button" @click="sendToDeckBuilder(deck)">EDIT</button>
      <button
        class="deck-button"
        @click="setFavorite(deck)"
        v-if="!deck.isFavorite"
      >
        SET FAVORITE
      </button>
      <button
        class="deck-button"
        @click="unfavorite(deck)"
        v-if="deck.isFavorite"
      >
        UNFAVORITE
      </button>
    </div>
  </div>
</template>

<script>
import CardService from "@/services/CardService.js";

export default {
  name: "deckoverview",
  props: ["deck"],
  data() {
    return {
      uniqueCards: [],
      uniqueContracts: [],
    };
  },
  computed: {
    grabRandomImage(deck) {
      let index = this.getRandomIntInclusive(0, 29);
      return deck.cards[index].imageName;
    },
  },
  methods: {
    getRandomIntInclusive(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1) + min);
    },
    setFavorite(deck) {
      deck.isFavorite = true;
      CardService.updateFavorite(deck.deckID, deck.isFavorite).then(
        (response) => {
          if (response.status === 200) {
            console.log("deck preference saved");
          } else {
            console.log(response.status);
          }
        }
      );
    },
    unfavorite(deck) {
      deck.isFavorite = false;
      CardService.updateFavorite(deck.deckID, deck.isFavorite).then(
        (response) => {
          if (response.status === 200) {
            console.log("deck preference saved");
          } else {
            console.log(response.status);
          }
        }
      );
    },
    setClassByFavorite(deck) {
      return {
        "deck-overview-tile": !deck.isFavorite,
        "deck-overview-tile-fav": deck.isFavorite,
      };
    },
    generateCardNames(deck) {
      let names = [];
      for (var x = 0; x < deck.cards.length; x++) {
        names.push(deck.cards[x].name);
      }
      let unique = [...new Set(names)];
      return unique;
    },
    generateContractNames(deck) {
      let names = [];
      for (var x = 0; x < deck.contracts.length; x++) {
        names.push(deck.contracts[x].name);
      }
      let unique = [...new Set(names)];
      return unique;
    },
    sendToDeckBuilder(deck) {
      let uniqueCards = [];
      for (var f = 0; f < deck.cards.length; f++) {
        if (deck.cards[f].id < 100) {
          uniqueCards.push(deck.cards[f]);
        }
      }
      for (var t = 0; t < uniqueCards.length; t++) {
        uniqueCards[t].count = 0;
        for (var i = 0; i < deck.cards.length; i++) {
          if (uniqueCards[t].name === deck.cards[i].name) {
            uniqueCards[t].count += 1;
          }
        }
      }
      deck.cards = uniqueCards;

      let uniqueContracts = [];
      for (var q = 0; q < deck.contracts.length; q++) {
        if (deck.contracts[q].id < 100) {
          uniqueContracts.push(deck.contracts[q]);
        }
      }
      for (var s = 0; s < uniqueContracts.length; s++) {
        uniqueContracts[s].count = 0;
        for (var p = 0; p < deck.contracts.length; p++) {
          if (uniqueContracts[s].name === deck.contracts[p].name) {
            uniqueContracts[s].count += 1;
          }
        }
      }
      deck.contracts = uniqueContracts;
      console.log(deck.deckID);
      this.$store.commit("SET_DECK_TO_EDIT", deck);
      this.$router.push({ name: "deck-builder" });
    },
  },
};
</script>

<style>
.deck-overview-tile {
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(0, 0, 0), rgb(36, 36, 36));
  height: 85%;
  width: 25%;
  border-radius: 20px;
  border: 2px solid black;
  box-shadow: 4px 4px rgba(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  gap: 20px;
  padding-top: 15px;
  padding-bottom: 15px;
  color: white;
  font-family: "Orbitron";
}
.deck-overview-tile-fav {
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(2, 32, 18), rgb(2, 32, 18));
  height: 85%;
  width: 25%;
  border-radius: 20px;
  border: 3px solid rgb(13, 105, 62);
  box-shadow: 4px 4px rgba(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  gap: 20px;
  padding-top: 15px;
  padding-bottom: 15px;
  color: white;
  font-family: "Orbitron";
}
.deck-overview-tile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.02);
  cursor: pointer;
}
.deck-overview-tile-fav:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.02);
  cursor: pointer;
}
.deck-title {
  padding: 10px;
  background: rgba(0, 0, 0, 0.637);
  border-radius: 5px;
  border: 1px solid rgb(0, 0, 0);
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
  width: 80%;
  text-align: center;
  font-size: 30px;
}
.deck-img-cont {
  padding: 10px;
  background: black;
  border-radius: 5px;
  border: 1px solid rgb(0, 0, 0);
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
  height: 250px;
  width: 250px;
  background-size: 300px 300px;
}
.deck-contents {
  padding: 10px;
  background: rgba(0, 0, 0, 0.637);
  border-radius: 5px;
  border: 1px solid rgb(0, 0, 0);
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
  height: 400px;
  width: 88%;
  color: rgb(255, 255, 255);
  margin-left: 5px;
  margin-right: 5px;
  font-family: "Bai Jamjuree";
  font-size: 13px;
}
.deck-contents-header {
  text-align: center;
  margin-bottom: 19px;
  font-size: 23px;
  font-weight: 700;
  font-family: "Bai Jamjuree";
  color: rgb(120, 189, 143);
  text-shadow: 1px 1px rgb(78, 78, 78);
}
.contents-row {
  display: flex;
  flex-direction: row;
  gap: 20px;
}
.deck-cards-column {
  display: flex;
  flex-direction: column;
}
.deck-button {
  margin: 0px 15px 0px 5px;
  background: rgba(0, 0, 0, 0.527);
  color: white;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 5px;
  width: 140px;
  height: 40px;
  font-family: "Bai Jamjuree";
  font-size: 18px;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
  text-shadow: 1px 2px rgb(51, 51, 51);
}
.deck-button:hover {
  transform: scale(1.03);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(180deg, rgba(37, 122, 226, 0.8), rgba(0, 0, 105, 0.8));
}
</style>
