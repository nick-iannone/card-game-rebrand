<template>
  <div :class="isTurnToggleClass">

    <!-- HEALTH -->
    <div :class="this.game.myFortAttack ? 'status-damage' : 'status-hp'" id="fort-attack-me">
      <font-awesome-icon :icon="['fas', 'monitor-heart-rate']" class="icon-arena-hp" />
      {{ this.game.myGame.fort }}
    </div>


    <!-- RESOURCES -->
    <div class="status-resources">
      <font-awesome-icon :icon="['fa', 'coins']" class="icon-arena-resource" />
      {{ this.game.myGame.cache }}
    </div>

    <!-- REPUTATION -->
    <div :class="this.game.myRepAttack ? 'status-rep-damage' : 'status-rep'" id='rep-attack-me'>
      <font-awesome-icon :icon="['fas', 'users']" class="icon-arena-rep" />
      {{ this.game.myGame.rep }}
    </div>

    <!-- HAND / DECK COUNT -->
    <div class="status-counts">
      <div class="status-div">
        <font-awesome-icon :icon="['fa', 'align-justify']" class="icon-arena-deck" />
        {{ this.game.deck.length }}
      </div>
      
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
.myarea {

  grid-area: my-status;
  gap: 5px;
  
  display: grid;
  grid-template-rows: 118px 73px;
  grid-template-columns: 123px 123px;
  row-gap: 2px;
  column-gap: 4px;
  grid-template-areas:
    "avatar-hp resources"
    "rep counts"
  ;

  height: 195px;
  width: 250px;
  
  margin: 5px;
}
.myareaturn {

  grid-area: my-status;
  gap: 5px;
  
  display: grid;
  grid-template-rows: 118px 78px;
  grid-template-columns: 123px 123px;
  row-gap: 4px;
  column-gap: 4px;
  grid-template-areas:
    "avatar-hp resources"
    "rep counts"
  ;

  height: 195px;
  width: 250px;
  
  margin: 5px;
  
  border: 2px solid yellow;
  border-radius: 10px;
  box-shadow: 0 0 10px rgb(226, 255, 62), -3px 0 4px rgb(0, 150, 32),
    3px 0 4px rgb(62, 255, 104);
}

.status-hp {

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
  border-top-left-radius: 5px;
  padding: 5px;
}
.icon-arena-hp {
  color: rgb(60, 249, 60);
  font-size: 30px;

  margin: 2px;
}

.status-resources {

  grid-area: "resources";

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
  border-top-right-radius: 5px;
  padding: 5px;
}
.icon-arena-resource {
  color: yellow;
  font-size: 30px;

  margin: 2px;
}

.status-damage {
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
  border-top-left-radius: 5px;
  padding: 5px;
}

.status-rep {

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
border-bottom-left-radius: 5px;

padding: 5px;
}
.icon-arena-rep {
  color: rgb(148, 113, 229);
  font-size: 24px;
}
.status-rep-damage {
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
  border-bottom-left-radius: 5px;

  padding: 5px;
}


.status-counts {

grid-area: "counts";

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
border-bottom-right-radius: 5px;

padding: 5px;
}
.icon-arena-deck {
  margin-right: 10px;
  color: rgb(251, 90, 84);
}
.icon-arena-hand {
  margin-right: 10px;
  color: rgb(103, 167, 236);
}

.status-div {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}

</style>
