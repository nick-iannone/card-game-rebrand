<template>
  <div :class="isTurnToggleClass">
    <!-- USERNAME -->
    <div class="status-div-my-username">
      {{ this.game.myGame.username }}
    </div>

    <!-- RESOURCES -->
    <div class="status-div">
      <font-awesome-icon :icon="['fa', 'coins']" class="icon-arena" color="yellow" />
      {{ this.game.myGame.cache }}
    </div>

    <!-- HEADQUARTERS -->
    <div :class="this.game.myFortAttack ? 'status-damage' : 'status-fortress'" id="fort-attack-me">
      <font-awesome-icon :icon="['fas', 'city']" class="icon-arena" color="grey" />
      {{ this.game.myGame.fort }}
    </div>

    <!-- REPUTATION -->
    <div :class="this.game.myRepAttack ? 'status-damage' : 'status-div'" id='rep-attack-me'>
      <font-awesome-icon :icon="['fal', 'chart-network']" class="icon-arena-rep" />
      {{ this.game.myGame.rep }}
    </div>

    <!-- ACTIVE CONTRACTS -->
    <div class="status-div">
      <font-awesome-icon :icon="['fal', 'file-chart-line']" class="icon-arena-cont" />
      {{ this.game.myGame.contracts.active.length }} Active
    </div>

    <!-- HAND / DECK COUNT -->
    <div class="status-div">
      <font-awesome-icon :icon="['fa', 'align-justify']" class="icon-arena-deck" />
      {{ this.game.deck.length }}
      <div class="status-div">
        <font-awesome-icon :icon="['fa', 'hand-paper']" class="icon-arena-hand" />
        {{ this.game.hand.length }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "my-status-tile",
  props: ["game"],
  computed: {
    isTurnToggleClass() {
      return {
        myarea: !this.game.myGame.turn.isTurn,
        myareaturn: this.game.myGame.turn.isTurn,
      };
    },
  },
  methods: {},
};
</script>

<style>
</style>
