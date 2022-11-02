<template>
  <draggable class="zone-hand-cont" id="my-hand-component" name="my-hand-component" v-model="game.hand"
    :options="{ group: 'cards', put: false }">
    <transition-group class="zone-hand">
      <tile :class="{ castable: isCastable(card) }" :card="card" :key="card.id" v-for="card in this.game.hand" />
    </transition-group>
  </draggable>
</template>

<script>
import draggable from "vuedraggable";
import tile from "@/components/Tile.vue";

export default {
  name: "my-hand",
  props: ["game"],
  components: {
    tile,
    draggable
  },
  computed: {},
  methods: {
    isCastable(card) {
      if (
        this.game.myGame.turn.isTurn ||
        this.game.myGame.rollPhase ||
        this.game.myGame.mulliganPhase
      ) {
        return card.cost <= this.game.myGame.cache;
      }
      return false;
    },
  }
}
</script>

<style>

</style>