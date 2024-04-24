<template>
  <div :class="isOppTurnToggleClass">

    <!-- REPUTATION -->
    <div :class="this.game.oppRepAttack ? 'opp-status-rep-damage' : 'opp-status-rep'" id="rep-attack-opp">
      <font-awesome-icon :icon="['fas', 'users']" class="icon-arena-rep" />
      {{ this.game.oppGame.rep }}
    </div>

    <!-- HAND / DECK COUNT -->
    <div class="opp-status-counts">
      <div class="status-div">
        <font-awesome-icon :icon="['fa', 'align-justify']" class="icon-arena-deck" />
      {{ this.game.oppGame.deckCount }}
      </div>
      <div class="status-div">
        <font-awesome-icon :icon="['fa', 'hand-paper']" class="icon-arena-hand" />
        {{ this.game.oppGame.handCount }}
      </div>
    </div>

    <!-- HEALTH -->
    <div :class="this.game.oppFortAttack ? 'opp-status-damage' : 'opp-status-hp'" id="fort-attack-opp">
      <font-awesome-icon :icon="['fas', 'monitor-heart-rate']" class="icon-arena-hp" />
      {{ this.game.oppGame.fort }}
    </div>

    <!-- RESOURCES -->
    <div class="opp-status-resources">
      <font-awesome-icon :icon="['fa', 'coins']" class="icon-arena-resource" />
      {{ this.game.oppGame.cache }}
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
.opparea {
  grid-area: opp-status;
  gap: 5px;
  
  display: grid;
  grid-template-rows: 78px 118px;
  grid-template-columns: 123px 123px;
  row-gap: 4px;
  column-gap: 4px;
  grid-template-areas:
    "opp-rep opp-counts"
    "opp-avatar-hp opp-resources"
  ;

  height: 200px;
  width: 250px;
  
  margin: 5px;
}
.oppareaturn {
  grid-area: opp-status;
  gap: 5px;
  
  display: grid;
  grid-template-rows: 78px 118px;
  grid-template-columns: 123px 123px;
  row-gap: 4px;
  column-gap: 4px;
  grid-template-areas:
    "opp-rep opp-counts"
    "opp-avatar-hp opp-resources"
  ;

  height: 200px;
  width: 250px;
  
  margin: 5px;

  border: 2px solid yellow;
  border-radius: 10px;
  box-shadow: 0 0 10px rgb(226, 255, 62), -3px 0 4px rgb(0, 150, 32),
    3px 0 4px rgb(62, 255, 104);
}

.opp-status-hp {

  grid-area: "opp-avatar-hp";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 50px;
  font-weight: 700;

  background-image: linear-gradient(45deg,
    rgba(0, 66, 21, 0.775),
    rgba(0, 167, 95, 0.8), rgba(0, 68, 25, 0.771));

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;

  border: 1px solid white;
  border-bottom-left-radius: 5px;
  padding: 5px;
}
.opp-status-damage {
  animation-name: statusDamage;
  animation-duration: 1s;
  animation-iteration-count: 1;
  animation-timing-function: ease-in-out;
  grid-area: "avatar-hp";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 50px;
  font-weight: 700;

  background-image: linear-gradient(45deg,
      rgba(0, 66, 21, 0.836),
      rgba(0, 167, 95, 0.946), rgba(0, 68, 25, 0.946));

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;

  border: 1px solid white;
  border-bottom-left-radius: 5px;
  padding: 5px;
}

.opp-status-resources {

  grid-area: "opp-resources";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 50px;
  font-weight: 700;

  background-image: linear-gradient(135deg,
    rgba(52, 52, 52, 0.836),
    rgba(110, 106, 106, 0.946), rgba(22, 22, 22, 0.946));

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;

  border: 1px solid white;
  border-bottom-right-radius: 5px;
  padding: 5px;
}

.opp-status-rep {

  grid-area: "opp-rep";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 35px;
  font-weight: 700;

  background-image: linear-gradient(135deg,
    rgba(0, 0, 0, 0.836),
    rgba(48, 1, 84, 0.666),  rgba(144, 57, 206, 0.836));

  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;

  border: 1px solid white;
  border-top-left-radius: 5px;

  padding: 5px;
}
.opp-status-rep-damage {
  animation-name: statusDamage;
  animation-duration: 1s;
  animation-iteration-count: 1;
  animation-timing-function: ease-in-out;
  grid-area: "rep";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 35px;
  font-weight: 700;

  background-image: linear-gradient(135deg,
    rgba(0, 0, 0, 0.836),
    rgba(48, 1, 84, 0.666),  rgba(144, 57, 206, 0.836));

  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;

  border: 1px solid white;
  border-top-left-radius: 5px;

  padding: 5px;
}

.opp-status-counts {

  grid-area: "opp-counts";

  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 22px;
  font-weight: 500;

  background-image: linear-gradient(45deg,
      rgba(144, 57, 206, 0.836),
      rgba(2, 7, 64, 0.816), rgba(0, 0, 0, 0.946));

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;

  border: 1px solid white;
  border-top-right-radius: 5px;

  padding: 5px;
}
</style>