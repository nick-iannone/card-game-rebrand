<template>
  <div :class="isOppTurnToggleClass">
    <!-- USERNAME -->
    <div class="status-div-opp-username">
      {{ this.$store.state.game.oppGame.username }}
    </div>

    <!-- RESOURCES -->
    <div class="status-div">
      <font-awesome-icon :icon="['fa', 'coins']" class="icon-arena" color="yellow" />
      {{ this.game.oppGame.cache }}
    </div>

    <!-- FORTRESS -->
    <div :class="this.game.oppFortAttack ? 'status-damage' : 'status-fortress'" id="fort-attack-opp">
      <font-awesome-icon :icon="['fas', 'city']" class="icon-arena" color="grey" />
      {{ this.game.oppGame.fort }}
    </div>

    <!-- REPUTATION -->
    <div :class="this.game.oppRepAttack ? 'status-damage' : 'status-div'" id="rep-attack-opp">
      <font-awesome-icon :icon="['fal', 'chart-network']" class="icon-arena-rep" />
      {{ this.game.oppGame.rep }}
    </div>

    <!-- ACTIVE CONTRACTS -->
    <div class="status-div">
      <font-awesome-icon :icon="['fal', 'file-chart-line']" class="icon-arena-cont" />
      {{ this.game.oppGame.contracts.active.length }}
      Active
    </div>

    <!-- HAND / DECK COUNT -->
    <div class="status-div">
      <font-awesome-icon :icon="['fa', 'align-justify']" class="icon-arena-deck" />
      {{ this.game.oppGame.deckCount }}
      <div class="status-div">
        <font-awesome-icon :icon="['fa', 'hand-paper']" class="icon-arena-hand" />
        {{ this.game.oppGame.handCount }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "opp-status-tile",
  props: ["game"],
  computed: {
    isOppTurnToggleClass() {
      return {
        opparea:
          this.game.myGame.turn.isTurn ||
          this.game.myGame.mulliganPhase ||
          this.game.myGame.rollPhase,
        oppareaturn:
          !this.game.myGame.turn.isTurn &&
          !this.game.myGame.mulliganPhase &&
          !this.game.myGame.rollPhase,
      };
    },
  }
}
</script>

<style>
</style>