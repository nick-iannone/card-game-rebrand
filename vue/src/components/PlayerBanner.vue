<template>
  <div class="player-banner">
    <div class="banner-top-row">
      <img class="player-avatar" src="../assets/cards/assassin.jpg" />
      <div class="banner-username">
        {{ this.$store.state.user.username }}
      </div>
    </div>
    <div class="stats-top-row">
      <div class="user-stats-level">Lvl. </div><div class="user-stats-level-num">4</div>
      <div class="user-stats-wins">Wins: </div><div class="user-stats-level-num">125</div>
      <div class="user-stats-wins">Feat Score: </div><div class="user-stats-level-num">9,250</div>
    </div>
    <div class="select-prompt">SELECT A FACTION</div>
    <div class="factions-container">
      <div id="dropdown-nav-faction">
        <img class="factions-icon" src="../assets/cards/deepened.jpg" />
        <div class="dropdown-content-faction">
          <div class='dd-faction-name'>
            DEEPENED
          </div>
        </div>
      </div>
      <div id="dropdown-nav-faction">
        <img class="factions-icon" src="../assets/cards/technician.jpg" />
        <div class="dropdown-content-faction">
          <div class='dd-faction-name'>
            HUMAN
          </div>
        </div>
      </div>
      <div id="dropdown-nav-faction">
        <img class="factions-icon" src="../assets/cards/robot.jpg" />
        <div class="dropdown-content-faction">
          <div class='dd-faction-name'>
            ROBOT
          </div>
        </div>
      </div>
      <div id="dropdown-nav-faction">
        <img class="factions-icon" src="../assets/cards/hybrid.jpg" />
        <div class="dropdown-content-faction">
          <div class='dd-faction-name'>
            HYBRID
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.player-banner {
  width: 500px;
  height: 250px;
  margin: 10px 10px 10px 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.banner-top-row {
  display: flex;
  flex-direction: row;
}
.player-avatar {
  height: 66px;
  width: 66px;
  border: 1px solid white;
  border-bottom-left-radius: 8px;
  border-top-left-radius: 8px;
}
.banner-username {
  width: 370px;
  font-size: 37px;
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
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(black, rgba(71, 101, 231, 0.7));
}
.stats-top-row {
  display: flex;
  margin: -5px 0px 15px 0px;
}
.user-stats-level {
  font-family: 'Bai Jamjuree', sans-serif;
  padding: 8px 5px 5px 5px;
  color: white;
  font-size: 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-self: flex-start;
  font-style: italic;
}
.user-stats-level-num {
  font-family: 'Bai Jamjuree', sans-serif;
  padding: 8px 5px 5px 5px;
  color: rgb(253, 238, 101);
  font-size: 17px;
  font-weight: 800;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-self: flex-start;
}
.user-stats-wins {
  font-family: 'Bai Jamjuree', sans-serif;
  padding: 8px 5px 5px 5px;
  color: white;
  font-size: 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-self: flex-start;
  font-style: italic;
}
.user-stats-wins-num {
  font-family: 'Bai Jamjuree', sans-serif;
  padding: 8px 5px 5px 5px;
  color: rgb(253, 238, 101);
  font-size: 17px;
  font-weight: 800;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-self: flex-start;
}
.select-prompt {
  font-family: 'Bai Jamjuree', sans-serif;
  font-weight: 400;
  letter-spacing: 6px;
  align-self: center;
  width: 90%;
  margin-bottom: 17px;
  color: white;
  font-size: 15px;
  border: 1px solid rgba(255, 255, 255, 0.301);
  text-align: center;
  background-image:
    linear-gradient(to right, rgb(0, 0, 0), rgb(63, 86, 187), rgba(0, 0, 0, 0.7));
    border-radius: 4px;
}
.factions-container {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
}
.factions-icon {
  height: 90px;
  width: 90px;
  border: 1px solid rgba(255, 255, 255, 0.377);
  border-radius: 5px;
  box-shadow: 0 0 2px 2px rgb(241, 241, 241), 0 0 3px 3px rgba(112, 112, 112, 0.226);
  transition: all 0.2s ease-in-out;
}
.factions-icon:hover {
  cursor: pointer;
  box-shadow: 0 0 3px 3px rgb(255, 251, 43), 0 0 3px 3px rgb(255, 255, 255),
    0 0 3px 3px rgb(255, 251, 43);
  transform: scale(1.05);

}
#dropdown-nav-faction {
  position: relative;
  display: inline-block;
}
.dropdown-content-faction {
  display: none;
  min-width: 65px;
  z-index: 1;
  margin-top: 5px;
  margin-left: 2px;
  transform: scale(1.1);
}
.dd-faction-name {
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
  font-size: 15px;
  text-align: center;
}
#dropdown-nav-faction:hover .dropdown-content-faction {
  display: flex;
  flex-direction: column;
}

</style>