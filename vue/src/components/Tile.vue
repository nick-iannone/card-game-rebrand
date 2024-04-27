<template>
  <div class="tile"
  :style="{
        backgroundImage: `url(${require('@/assets/cards/' + card.imageName)})`,
      }"
  >
    <div class="tile-header">
      <div class="tile-name">{{ card.name }}</div>
      
    </div>

    <div class="tile-desc" :style="card.abilities ? {opacity: 1} : {opacity: 0}">

          {{ card.abilities }}

    </div>

    <div class="tile-attr">
      <div class="tile-cost">
        {{ card.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
      </div>
        <div class="attr-attack">
          {{ card.attack }}
          <!-- <p id="attr-desc">Attack</p> -->
        </div>
        <div class="attr-resilience">
          {{ card.resilience }}
          <!-- <p id="attr-desc">Resilience</p> -->
        </div>
        
    </div>    
  </div>
</template>

<script>
export default {
  name: "tile",
  props: ["card"],
  methods: {
    getImageURL(pic) {
      return require("../assets/cards/" + pic);
    },
    determineNameClass(card) {
      return {
        'tile-name': !card.isHallowed,
        'tile-name-hallowed': card.isHallowed,
        'tile-name-dreaded': card.isDreaded
      }
    }
  },
};
</script>

<style>
.tile {
  flex-direction: column;
  /* border: 8px solid rgba(203, 238, 248, 0); */
  width: 210px;
  height: 108px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 4px;  
  border-radius: 10px;
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.tile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.02);
  cursor: pointer;
}
.tile-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 35px;
}
.tile-name {
  color: white;
  font-size: 12px;
  font-weight: 550;
  font-family: "Bai Jamjuree";
  padding: 4px 4px 0px 4px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(3, 24, 49, 0.8), rgb(34, 34, 92, 0.8));
  text-align: center;
  width: 100%;
  padding:3px 3px 3px 3px;
  text-wrap: nowrap;
}
.tile-name-hallowed {
  color: white;
  font-size: 11px;
  font-weight: 550;
  font-family: "Bai Jamjuree";
  padding: 2px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(68, 1, 1, 0.8), rgba(172, 54, 54, 0.8));
  text-align: left;
  height: 25px;
}
.tile-name-dreaded {
  color: white;
  font-size: 11px;
  font-weight: 550;
  font-family: "Bai Jamjuree";
  padding: 2px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(2, 2, 2, 0.8), rgba(97, 97, 97, 0.89), rgba(2, 2, 2, 0.8));
  text-align: left;
  height: 25px;
}
.tile-cost {
  color: white;
  padding: 4px 2px 0px 2px;
  font-family: "Bai Jamjuree";
  font-size: 16px;
  font-weight: 600;
  border-top-left-radius: 90px;
  border-bottom-left-radius: 90px;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 0, 0, 0.925), rgba(0, 0, 0, 0));
  text-align: center;
  width: 30%;
}
.tile-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgb(160, 160, 160);
  border-radius: 5px;
  padding: 2px;
  height: auto;
  font-size: 10px;
  font-family: "Bai Jamjuree";

  color: white;
  background-image: linear-gradient(
    60deg,
    rgba(82, 82, 82, 0.7),
    rgba(8, 17, 44, 0.7)
  );
  transition: all 0.2s ease-in-out;
}
.tile-desc:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(4, 7, 32, 0.938),
    rgba(9, 13, 44, 0.918)
  );
}
.tile-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
}
.attr-attack {
  color: white;
  font-weight: 600;
  font-family: "Bai Jamjuree";
  font-size: 20px;
  width: 30%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(217, 0, 0, 0.888),
    rgba(101, 0, 0, 0.881)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr-attack:hover {
  transform: scale(1.5);
  background-image: linear-gradient(
    180deg,
    rgba(217, 0, 0, 0.888),
    rgba(101, 0, 0, 0.881)
  )
}
.attr-resilience {
  color: white;
  font-weight: 600;
  font-family: "Bai Jamjuree";
  font-size: 20px;
  width: 30%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(0, 148, 67, 0.873),
    rgba(0, 74, 33, 0.889)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr-resilience:hover {
  transform: scale(1.5);
  background-image: linear-gradient(
    180deg,
    rgba(0, 148, 67, 0.873),
    rgba(0, 74, 33, 0.889)
  );
}
</style>
