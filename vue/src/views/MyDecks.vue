<template>
  <div class="my-decks">
    <div class="decks-container">
      <deckoverview
        :deck="deck"
        v-for="deck in pageOfItems"
        :key="deck.deckID"
      />
      <div
        class="flexy"
        v-if="$store.state.token != '' && this.decks.length === 0"
      >
        <div class="no-decks-prompt">
          <img class="announcement-image" src="../assets/cards/analyst.jpg" />
          <div class="flexy-no-backy">
            Don't have any decks yet?
            <div class="no-decks-prompt">
              Head on over to the Deck Builder to get started!
            </div>
            <button class="nav-button" @click="goToDeckBuilder">
              DECK BUILDER
            </button>
          </div>
        </div>
      </div>
    </div>

    <jw-pagination
      :disableDefaultStyles="true"
      :items="decks"
      @changePage="onChangePage"
      :pageSize="3"
      :labels="{
        first: '<<',
        last: '>>',
        previous: '<',
        next: '>',
      }"
    ></jw-pagination>
  </div>
</template>

<script>
import deckoverview from "@/components/DeckOverview.vue";
import CardService from "@/services/CardService.js";

export default {
  data() {
    return {
      decks: [],
      pageOfItems: [],
    };
  },
  components: {
    deckoverview,
  },
  methods: {
    backToMain() {
      this.$router.push({ name: "home" });
    },
    goToDeckBuilder() {
      this.$router.push({ name: "deck-builder" });
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
  },
  created() {
    CardService.getDecksByUsername(this.$store.state.user.username).then(
      (response) => {
        this.decks = response.data;
        this.$store.commit("SET_MY_DECKS", response.data);
      }
    );
  },
};
</script>

<style scoped>
.my-decks {
  background-image: url("../assets/backgrounds/ships.jpg");
  background-size: 1920px 1070px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 1003px;
}
.decks-container {
  width: 98%;
  height: 90%;
  border: 1px solid black;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.712);
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}
.pagination {
  margin: 0px;
  padding: 20px;
  gap: 20px;
}
.pagination >>> li {
  font-size: 27px;
  font-weight: 700;
  font-family: "Bai Jamjuree";
  color: rgb(255, 255, 255);
  text-shadow: 2px 2px black;
}
.pagination >>> li:hover {
  color: rgb(184, 212, 22);
  text-shadow: 2px 2px black;
}
.flexy {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  gap: 30px;
  background-color: rgba(0, 0, 0, 0.612);
  padding-right: 20px;
  border: 2px solid rgba(201, 201, 201, 0.645);
  border-radius: 4px;
}
.flexy-no-backy {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  gap: 30px;
}
.no-decks-prompt {
  font-family: 'Turret Road', cursive;
  color: rgb(209, 198, 79);
  font-weight: 400;
  font-size: 30px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 30px;
}
</style>
