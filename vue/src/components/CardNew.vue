<template>
  <div class="card-new">

    <div class="card-header-new" >
      <div class="card-name-new" :style="this.card.name.length > 15 ? 'fontSize: 11px' : 'fontSize: 15px'" >{{ card.name }}</div>
    </div>

    <div class="type-row-new">
      <div>
      {{ card.type }}
      </div>
    </div>

    <div class="image-cont-new">
      <img class="image-new" v-bind:src="getImageURL(card.image)" />
      <div class="equip-slot-1" ></div>
      <div class="equip-slot-2" ></div>
      <div class="card-desc-new"  >
        <div class="text">
        {{ card.text1 }}
        </div>
        <div class="text">
        {{ card.text2 }}
        </div>
        <div class="text">
        {{ card.text3 }}
        </div>
        <div class="text">
        {{ card.text4 }}
        </div>
        <div class="flavor">
        {{ card.flavor }}
        </div>
      </div>      
    </div>

    <div class="card-attr-new">
        <div class="card-cost-new">
        {{ card.cost }}<font-awesome-icon
          :icon="['fa', 'coins']"
          class="icon"
          color="yellow"
        />
        </div>
        <div class="attr-attack-new">
          {{ card.attack }}
        </div>
        <div class="attr-resilience-new">
          {{ card.resilience }}
        </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "card",
  props: ["card"],
  methods: {
    getImageURL(image) {
      return require("../assets/cards/" + image);
    },
    determineType(card) {
      return {
        'card': card.id > 0
      };
    },
  },
};
</script>

<style>
.card-new {
  display: flex;
  flex-direction: column;
  border: 10px solid rgb(167, 165, 174);
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: space-between;
  padding: 8px;
  border-radius: 10px;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(74, 74, 74), rgb(0, 0, 0));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.card-new:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.card-header-new {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 95%;
  padding: 5px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 5px;
  background-image: linear-gradient(45deg, rgb(28, 28, 28), rgb(28, 28, 28));
  text-align: center;
  height: 17px;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease-in-out;
}
.card-header-new:hover {
  transform: scale(1.2);
  cursor: pointer;
}
.card-name-new {
  color: rgb(255, 255, 255);
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  font-size: "";
}
.card-cost-new {
  color: rgb(255, 255, 255);
  font-weight: 700;
  font-family: 'Orbitron', sans-serif;
  font-size: 18px;
  background-image: linear-gradient(rgb(23, 139, 255), rgb(0, 0, 0));
  border-radius: 5px;
  border: 1.5px solid rgb(0, 47, 155);
  background-size: 100%;
  display: flex;
  flex-direction: row;
  text-align: center;
  align-items: center;
  justify-content: center;
  letter-spacing: 4px;
  height: 30px;
  width: 50px;
  transition: all 0.2s ease-in-out;
  padding: 3px;   
}
.card-cost-new:hover {
  transform: scale(1.4);
  cursor: pointer;
}
.type-row-new {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  align-items: center;
  width: 98%;
  color: white;
  font-weight: 500;
  font-family: 'Orbitron', sans-serif;
  font-size: 10px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 4px;
  padding: 0px 2px 0px 2px;
  background-image: linear-gradient(rgb(55, 0, 111), rgb(0, 0, 0));
  transition: all 0.2s ease-in-out;
  align-self: flex-start;

}
.image-new {
  border-radius: 5px;
  overflow: hidden;
}
.image-cont-new {
  display: flex;
  flex-direction: column;
  height: 70%;
  width:100%;
  position: relative;
  border: 1px solid white;
  border-radius: 5px;
  
}
.equip-slot-1 {
  position: absolute;
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border: 1.5px solid rgb(183, 183, 183);
  height: 60px;
  width: 60px;
  top: 0%;
}
.equip-slot-2 {
  position: absolute;
  border-top-right-radius: 5px;
  border-bottom-left-radius: 5px;
  border: 1.5px solid rgb(183, 183, 183);
  height: 60px;
  width: 60px;
  top: 0%;
  left: 69%;
}
.card-desc-new {
  position: absolute;
  bottom: 0%;
  display: flex;
  flex-direction: column;
  text-align: center;
  border-top: 1px solid rgb(255, 255, 255);
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.503),
    rgba(0, 0, 0, 0.491)
  );
  padding: 4px;
  color: rgb(255, 255, 255);
  font-size: 13px;
  font-family: 'Bai Jamjuree', sans-serif;
  font-weight: 500;
  transition: all 0.2s ease-in-out;
}
.card-desc-new:hover {
  transform: scale(1.2);
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.938),
    rgba(0, 0, 0, 0.918)
  );
  border:1px solid white;
  border-radius: 5px;
}
.card-attr-new {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;
  width: 100%;
  margin: 4px 0px -3px 0px;
}
.attr-attack-new {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 25px;
  width: 40px;
  height: 40px;
  justify-content: center;
  text-align: center;
  align-content: center;
  background-image: linear-gradient(
    180deg,
    rgb(180, 4, 4),
    rgb(101, 0, 0)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr-attack-new:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(166, 0, 0, 0.95),
    rgba(110, 0, 0, 0.95)
  );
}
.attr-resilience-new {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 25px;
  height: 40px;
  width: 40px;
  text-align: center;
  align-content: center;
  background-image: linear-gradient(
    180deg,
    rgba(0, 148, 67, 0.873),
    rgba(0, 74, 33, 0.889)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr-resilience-new:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(0, 148, 67, 0.873),
    rgba(0, 74, 33, 0.889)
  );
}
</style>
