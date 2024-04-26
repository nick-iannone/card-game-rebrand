<template>
  <div class="card">

    <div class="card-header">
      <div class="card-name">{{ card.name }}</div>
      <div class="card-cost">
        {{ card.cost }}
      </div>
    </div>

    <div class="type-row">
      <div>
      {{ card.type }}
      </div>
    </div>

    <div class="image-cont">
      <img class="image" v-bind:src="getImageURL(card.image)" />
      <div class="equip-slot-1" ></div>
      <div class="equip-slot-2" ></div>
      <div class="card-desc"  >
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
    <div class="card-attr">
        <div class="attr-attack">
          {{ card.attack }}
        </div>
        <div class="attr-resilience">
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
.card {
  display: flex;
  flex-direction: column;
  border: 10px solid rgb(167, 165, 174);
  height: 350px;
  width: 250px;
  align-items: center;
  justify-content: space-between;
  padding: 8px;
  border-radius: 10px;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(74, 74, 74), rgb(0, 0, 0));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.card:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.card-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 96%;
  color: white;
  font-size: 100%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 5px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 5px;
  background-image: linear-gradient(45deg, rgb(28, 28, 28), rgb(28, 28, 28));
  text-align: left;
  height: 20px;
  align-items: center;
  transition: all 0.2s ease-in-out;
}
.card-header:hover {
  transform: scale(1.2);
  cursor: pointer;
}
.card-cost {
  color: rgb(255, 255, 255);
  font-weight: 700;
  font-family: 'Orbitron', sans-serif;
  font-size: 22px;
  background-image: url("../assets/cost.png"), linear-gradient(rgb(23, 139, 255), rgb(0, 0, 0));
  border-radius: 2px;
  border: 1.5px solid rgb(0, 47, 155);
  background-size: 100%;
  text-align: center;
  justify-content: center;
  align-content: center;
  height: 20px;
  width: 20px;
  transition: all 0.2s ease-in-out;
}
.card-cost:hover {
  transform: scale(1.5);
  cursor: pointer;
}
.type-row {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  align-items: center;
  width: 98%;
  color: white;
  font-weight: 500;
  font-family: 'Orbitron', sans-serif;
  font-size: 12px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 4px;
  padding: 0px 2px 0px 2px;
  background-image: linear-gradient(rgb(55, 0, 111), rgb(0, 0, 0));
  transition: all 0.2s ease-in-out;
}
.image {
  border-radius: 5px;
}
.image-cont {
  display: flex;
  flex-direction: column;
  width:100%;
  position: relative;
  border: 1px solid white;
  border-radius: 5px;
}
.equip-slot-1 {
  position: absolute;
  background-image: url('../assets/cost.png'), linear-gradient(rgba(45, 45, 45, 0.532), rgba(0, 0, 0, 0.537));
  background-size: 100%;
  background-repeat: no-repeat;
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border: 1.5px solid rgb(183, 183, 183);
  height: 60px;
  width: 60px;
  top: 0%;
}
.equip-slot-2 {
  position: absolute;
  background-image: url('../assets/cost.png'), linear-gradient(rgba(45, 45, 45, 0.532), rgba(0, 0, 0, 0.537));
  background-size: 100%;
  background-repeat: no-repeat;
  border-top-right-radius: 5px;
  border-bottom-left-radius: 5px;
  border: 1.5px solid rgb(183, 183, 183);
  height: 60px;
  width: 60px;
  top: 0%;
  left: 75%;
}
.card-desc {
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
.card-desc:hover {
  transform: scale(1.2);
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.938),
    rgba(0, 0, 0, 0.918)
  );
  border:1px solid white;
  border-radius: 5px;
}
.card-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
  margin: 2px 0px 1px 0px;
}
.attr-attack {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 30px;
  padding: 1px 5px 1px 5px;
  width: 15%;
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
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(166, 0, 0, 0.95),
    rgba(110, 0, 0, 0.95)
  );
}
.attr-resilience {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 30px;
  padding: 1px 5px 1px 5px;
  width: 15%;
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
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgb(0, 41, 24, 0.95),
    rgb(0, 110, 73, 0.95)
  );
}
</style>
