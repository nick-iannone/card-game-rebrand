<template>
  <div
    @click="selectDeck(deck)"
    :class="determineTileClass(deck)"
  >
    <div :class="determineNameClass(deck)" >{{ deck.name }}</div>
    <div
      :class="determineImgClass(deck)"
      :style="{
        backgroundImage: `url(${require('@/assets/cards/' +
          deck.cards[29].imageName)}`,
      }"
    />
  </div>
</template>

<script>
export default {
  name: "decktile",
  props: ["deck"],
  methods: {
    determineTileClass(deck) {
      return {
        "deck-tile": !deck.isSelected,
        "deck-tile-selected": deck.isSelected,
      };
    },
    determineNameClass(deck) {
      return {
        "deck-tile-name": !deck.isSelected,
        "deck-tile-name-selected": deck.isSelected,
      };
    },
    determineImgClass(deck) {
      return {
        "deck-tile-img": !deck.isSelected,
        "deck-tile-img-selected": deck.isSelected,
      };
    },
    getRandomIntInclusive(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1) + min);
    },
    selectDeck(deck) {
      console.log("hitting here in deck tile");
      this.$emit("toggleSelectedDeck", deck);
    },
  },
};
</script>

<style>
.deck-tile {
  width: 250px;
  height: 350px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(rgb(0, 0, 0), rgb(0, 0, 0));
  border: 2px solid rgba(71, 71, 71, 0.493);
  box-shadow: 4px 4px rgba(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
}
.deck-tile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.02);
  cursor: pointer;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(180deg, rgb(0, 0, 0), rgb(79, 111, 255), rgb(0, 0, 0));
}
.deck-tile-selected {
  width: 250px;
  height: 350px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(rgb(43, 100, 255), rgb(1, 29, 107));
  border: 2px solid rgb(255, 255, 255);
  box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(43, 100, 255);
  transition: all 0.2s ease-in-out;
}
.deck-tile-selected:hover {
    background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(rgb(43, 100, 255), rgb(1, 29, 107));
  box-shadow: 0 0 6px 6px rgb(43, 100, 255), 0 0 6px 6px rgb(255, 255, 255),
    0 0 6px 6px rgb(43, 100, 255);
  transform: scale(1.02);
  cursor: pointer;
}
.deck-tile-name {
  font-family: 'Orbitron', sans-serif;
  font-weight: 700;
  width: 80%;
  color: white;
  font-size: 20px;
  text-align: center;
  text-shadow: 2px 2px rgba(0, 0, 0, 0.774);
  background-image: linear-gradient(
    60deg,
    rgb(0, 0, 0),
    rgba(152, 155, 154, 0.808),
    rgb(0, 0, 0)
  );
  padding: 5px 15px 5px 15px;
  border: 1px solid black;
  border-radius: 5px;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.938);
}
.deck-tile-name-selected {
  font-family: 'Orbitron', sans-serif;
  font-weight: 700;
  width: 80%;
  color: white;
  font-size: 20px;
  text-align: center;
  text-shadow: 2px 2px rgba(0, 0, 0, 0.774);
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.651),
    rgba(71, 58, 255, 0.973),
    rgba(0, 0, 0, 0.658)
  );
  padding: 5px 15px 5px 15px;
  border: 1px solid rgb(255, 255, 255);
  border-radius: 5px;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.938);
}
.deck-tile-img {
  width: 220px;
  height: 200px;
  border: 2px solid black;
  border-radius: 5px;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.938);
}
.deck-tile-img-selected {
  width: 220px;
  height: 200px;
  border: 2px solid rgb(255, 255, 255);
  border-radius: 5px;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.938);
}
</style>
