<template>
  <div class="db-row-container">
    <div :class="determineRarity(card)">
      <div id="dropdown-card">
        <div class="db-card-row-header">
          <div class="row-me">
            <div class="cont-name">{{ card.name }}</div>
            <div class="cont-cost">
              {{ card.cost
              }}<font-awesome-icon
                :icon="['fa', 'coins']"
                class="icon"
                color="yellow"
              />
            </div>
          </div>
          <div class="row-me">
            <font-awesome-icon
              :icon="['fal', 'sword']"
              class="icon-db-stat"
              color="white"
            />
            <div class="card-row-counter">{{ card.attack }}</div>
            <font-awesome-icon
              :icon="['fal', 'shield-alt']"
              class="icon-db-stat"
              color="white"
            />
            <div class="card-row-counter">{{ card.resilience }}</div>
            
          </div>
        </div>

        <div class="dropdown-content-active" v-if="this.card.abilities != null">
          <!-- <div v-if="this.card.abilities != null">
            <div>
              <div class="db-cont-desc" v-if="this.card.abilities != null">
                {{ card.abilities }}
              </div>
            </div>
          </div> -->
          <tile :card=card />
        </div>
      </div>
    </div>
    <div class="row-control">
      <font-awesome-icon
        :icon="['fal', 'minus-square']"
        class="icon-db-row"
        color="white"
        @click="reduceCount(card)"
      />
      <div class="card-row-counter">{{ card.count }}</div>
      <font-awesome-icon
        :icon="['fal', 'plus-square']"
        class="icon-db-row"
        color="white"
        @click="increaseCount(card)"
      />
      <font-awesome-icon
        :icon="['fal', 'trash-alt']"
        class="icon-db-row-trash"
        color="white"
        @click="onDelete(card)"
      />
    </div>
  </div>
</template>

<script>
import tile from "../components/Tile.vue";

export default {
  name: "dbcardtile",
  props: ["card"],
  components: {
    tile
  },
  methods: {
    getImageURL(pic) {
      return require("../assets/cards/" + pic);
    },
    determineRarity(card) {
      return {
        dbcardtilerow: !card.isHallowed && !card.isHeroic && !card.isFabled,
        dbcardtilerowhallowed: card.isHallowed,
        dbcardtilerowfabled: card.isFabled,
        dbcardtilerowheroic: card.isHeroic,
        dbcardtilerowdreaded: card.isDreaded,
        dbcardtilerowfeared: card.isFeared,
        dbcardtilerowvile: card.isVile,
      };
    },
    determineImageBorder(card) {
      return {
        imagecont: !card.isHallowed && !card.isHeroic && !card.isFabled,
        imageconthallowed: card.isHallowed,
        imagecontfabled: card.isFabled,
        imagecontheroic: card.isHeroic,
      };
    },
    cardNameClass(card) {
      return {
        cardname: !card.isHallowed,
        cardnamehallowed: card.isHallowed,
      };
    },
    reduceCount(card) {
      if (card.count >= 2) {
        card.count--;
      }
    },
    increaseCount(card) {
      if (this.$store.state.newDeckCardCount < 30) {
        if ((card.isHallowed || card.isDreaded) && card.count === 0) {
          card.count++;
        } else if ((card.isFabled || card.isFeared) && card.count <= 1) {
          card.count++;
        } else if ((card.isHeroic || card.isVile) && card.count <= 2) {
          card.count++;
        } else if (
          card.count <= 3 &&
          !card.isHallowed &&
          !card.isFabled &&
          !card.isHeroic &&
          !card.isDreaded &&
          !card.isFeared &&
          !card.isVile
        ) {
          card.count++;
        }
      }
    },
    onDelete(card) {
      this.$emit("removeRow", card);
    },
  },
};
</script>

<style>
.row-me {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}
.db-row-container {
  display: flex;
  flex-direction: row;
}
.db-card-row-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 2px;
  width: 435px;
}
.dbcardtilerow {
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(to right, rgb(5, 2, 50), rgb(14, 12, 49));
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.dbcardtilerow:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowhallowed {
    background-image: url("../assets/textures/assault.png"),
    linear-gradient(to right, rgb(4, 22, 60), rgb(73, 122, 237), rgb(7, 35, 95));
  border-radius: 5px;
  border: 2px solid rgb(196, 189, 96);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
  font-size: 15px;
}
.dbcardtilerowhallowed:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowdreaded {
  background-image: url("../assets/textures/assault.png"), linear-gradient(
    60deg,
    rgb(0, 0, 0),
    rgb(196, 12, 12),
    rgb(43, 1, 1)
  );
  border-radius: 5px;
  border: 2px solid rgb(250, 65, 65);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
  font-size: 15px;
}
.dbcardtilerowdreaded:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowfabled {
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(
    60deg,
    rgb(0, 49, 19),
    rgb(3, 116, 58),
    rgb(3, 54, 26)
  );
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.dbcardtilerowfabled:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowfeared {
  background-image: url("../assets/textures/3px-tile.png"), linear-gradient(to right, rgb(0, 0, 0), rgb(38, 174, 83), rgb(0, 0, 0));
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.dbcardtilerowfeared:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowheroic {
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(
    60deg, rgb(58, 1, 63), rgb(188, 9, 211), rgb(58, 1, 63));
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.dbcardtilerowheroic:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dbcardtilerowvile {
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(to right, rgb(37, 114, 159), rgb(0, 0, 0));
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.dbcardtilerowvile:hover {
  cursor: pointer;
  background-image: url("../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(33, 33, 33), rgb(43, 43, 43));
}
.dropdown-content-active {
  display: none;
  position: absolute;
  min-width: 265px;
  z-index: 1;
  margin-bottom: 200px;
  margin-left: 2px;
  transform: scale(1.01);
  left: 470px;
}

#dropdown-card {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
}
#dropdown-card:hover .dropdown-content-active {
  display: flex;
  flex-direction: column;
}

.row-control {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}
.card-row-counter {
  margin-right: 10px;
  margin-left: 9px;
  width: 9px;
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
}
.icon-db-row {
  font-size: 20px;
  margin-right: 5px;
  margin-left: 5px;
}
.icon-db-row:hover {
  cursor: pointer;
  color: rgb(104, 104, 221);
}
.icon-db-row-trash {
  font-size: 18px;
  margin-right: 5px;
  margin-left: 15px;
}
.icon-db-row-trash:hover {
  cursor: pointer;
  color: rgb(202, 36, 36);
}
.icon-db-row-clicked {
  font-size: 18px;
}
.icon-db-stat {
  font-size: 18px;
}

.card-db-dropdown {
  display: flex;
  flex-direction: column;
  border: 10px solid black;
  height: auto;
  width: 250px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(2, 0, 27), rgb(2, 0, 27));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
#abilities {
  color: white;
  font-size: 13px;
  font-family: 'Bai Jamjuree', sans-serif;
  font-weight: 500;
}
#flavor {
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
  font-style: italic;
  font-size: 12px;
  margin-top: 10px;
}
.card-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  width: 100%;
}
.db-dd-attribute {
  color: white;
  font-weight: 600;
  font-family: 'Bai Jamjuree', sans-serif;
  font-size: 16px;
  padding: 0px 10px 0px 10px;
  width: 30%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0.3),
    rgba(155, 143, 143, 0.3)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.db-dd-attribute:hover {
  transform: scale(1.04);
  background-image: linear-gradient(
    60deg,
    rgb(0, 41, 24, 0.95),
    rgb(0, 110, 73, 0.95)
  );
}
#attr-desc {
  font-size: 10px;
  font-weight: 500;
}
.cont-name {
  color: white;
  font-weight: 550;
  font-family: "Bai Jamjuree";
  padding: 3px 13px 3px 5px;
  text-align: left;
  height: 20px;
}
.cont-cost {
  color: white;
  padding: 3px;
  font-family: "Bai Jamjuree";
  font-weight: 600;
}
</style>
