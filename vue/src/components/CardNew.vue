<template>
  <div id="card-sprite" :class="determineFrame(card)">

    <div id="card-header" :class="determineHeader(card)" >
      <div class="card-name-new" :style="this.card.name.length > 15 ? 'fontSize: 11px' : 'fontSize: 15px'" >{{ card.name }}</div>
    </div>

    <div :class="determineType(card)">
      <div class="row-me" >
      {{ card.type }}  <div v-if="card.type != 'Credit'" > &nbsp; &nbsp;- &nbsp;&nbsp;</div>  {{ card.faction }}
      </div>
    </div>

    <div class="image-cont-new">
      <img class="image-new" v-bind:src="getImageURL(card.image)" />
      <div id="equip-slot" class="equip-slot-1" v-if="card.mod1"></div>
      <div id="equip-slot" class="equip-slot-2" v-if="card.mod2"></div>
      <div id="equip-slot" class="equip-slot-3" v-if="card.mod3"></div>
      <div id="equip-slot" class="equip-slot-4" v-if="card.mod4"></div>
      <div class="card-desc-new"  >
        <div class="text" :style="this.card.text1 == 'Recyclable' || this.card.text1 == 'Readiness' ? 'fontWeight: 700':'fontWeight: 500'">
        {{ card.text1 }}
        </div>
        <div class="text" :style="this.card.text2 == 'Recyclable' || this.card.text2 == 'Readiness' ? 'fontWeight: 700':'fontWeight: 500'">
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
        <div id="attr" class="attr-attack-new" v-if="this.card.type != 'Credit'">
          {{ card.attack }}
        </div>
        <div id="attr" class="attr-resilience-new" v-if="this.card.type != 'Credit'">
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
    determineFrame(card) {
      return {
        'card-robot': card.faction == 'Robot',
        'card-credit': card.type == 'Credit',
        'card-hybrid': card.faction == 'Hybrid',
        'card-human': card.faction == 'Human',
        'card-deepened': card.faction == 'Deepened',
        'card-organic': card.faction == 'Organic',
      };
    },
    determineHeader(card) {
      return {
        'card-header-robot': card.faction == 'Robot',
        'card-header-credit': card.type == 'Credit',
        'card-header-hybrid': card.faction == 'Hybrid',
        'card-header-human': card.faction == 'Human',
        'card-header-deepened': card.faction == 'Deepened',
        'card-header-organic': card.faction == 'Organic',

      };
    },
    determineType(card) {
      return {
        'type-row-new': card.id > 0,
      };
    },
  },
};
</script>

<style>
#card-sprite {
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: space-between;
  padding: 8px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  border-width: 10px;
  border-style: solid;
}
#card-sprite:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
}

.card-robot {
  border-image: linear-gradient(to bottom right, rgb(255, 255, 255), rgb(166, 56, 209), rgb(255, 255, 255)) 1;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(58, 1, 63), rgb(135, 3, 153));
}
.card-organic {
  border-image: linear-gradient(to bottom right, rgb(230, 228, 145),rgb(63, 63, 63), rgb(230, 228, 145), rgb(61, 61, 61)) 1;
  background-image: url("../assets/textures/handmade-paper.png"),
    linear-gradient(rgb(129, 43, 32), rgb(37, 11, 11));
}
.card-human {
  border-image: linear-gradient(to bottom right, rgb(255, 187, 86), rgb(0, 0, 0), rgb(255, 187, 86)) 1;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(rgb(212, 120, 0), rgb(0, 0, 0));
}
.card-credit {
  border-image: linear-gradient(to right, rgb(12, 255, 141), rgb(255, 255, 255), rgb(12, 255, 141)) 1;
  background-image: url("../assets/textures/diagonal-striped-brick.png"),
    linear-gradient(rgb(6, 102, 57), rgb(6, 102, 57));
}
.card-hybrid {
  border-image: linear-gradient(to bottom right, rgb(0, 0, 0), rgb(0, 0, 0), rgb(110, 190, 255), rgb(0, 0, 0), rgb(0, 0, 0)) 1;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(rgb(5, 16, 75), rgb(7, 68, 148));
}
.card-deepened {
  border-image: linear-gradient(to bottom right, rgb(0, 0, 0), rgb(0, 0, 0), rgb(146, 5, 5), rgb(0, 0, 0), rgb(0, 0, 0)) 1;
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(rgb(77, 1, 1), rgb(0, 0, 0));
}

#card-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 95%;
  padding: 5px;
  border-radius: 5px;
  text-align: center;
  height: 17px;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease-in-out;
}
#card-header:hover {
  transform: scale(1.2);
}
.card-header-organic {
  border: 1px solid rgb(34, 175, 116);
  background-image: linear-gradient(45deg, rgb(39, 10, 6), rgb(168, 63, 63), rgb(61, 167, 102), rgb(168, 63, 63), rgb(39, 10, 6));
}
.card-header-human {
  border: 1px solid rgb(0, 0, 0);
  background-image: linear-gradient(45deg, rgb(0, 0, 0), rgb(196, 110, 40), rgb(0, 0, 0));
}
.card-header-robot {
  border: 1px solid rgb(255, 255, 255);
  background-image: linear-gradient(45deg, rgb(28, 28, 28), rgb(115, 44, 173), rgb(0, 0, 0));
}
.card-header-credit {
  border: 1px solid rgb(0, 0, 0);
  background-image: linear-gradient(45deg, rgb(0, 0, 0), rgb(5, 129, 71), rgb(0, 0, 0));
}
.card-header-hybrid {
  border: 1px solid rgb(68, 186, 255);
  background-image: linear-gradient(45deg, rgb(0, 0, 0), rgb(56, 117, 248), rgb(0, 0, 0));
}
.card-header-deepened {
  border: 1px solid rgb(0, 0, 0);
  background-image: linear-gradient(45deg, rgb(0, 0, 0), rgb(201, 2, 2), rgb(0, 0, 0));
}

.card-name-new {
  color: rgb(255, 255, 255);
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
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
  background-image: linear-gradient(rgb(0, 0, 0), rgb(0, 0, 0));
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
  border: 1px solid rgba(255, 255, 255, 0.678);
  border-radius: 5px;
}


#equip-slot {
  position: absolute;
  border: 1.5px solid rgba(183, 183, 183, 0.61);
  height: 50px;
  width: 50px;
  background-image: linear-gradient(rgba(0, 0, 0, .400), rgb(0, 0, 0, .400))
}
.equip-slot-1 {
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  top: 0%;
}
.equip-slot-2 {
  border-top-right-radius: 5px;
  border-bottom-left-radius: 5px;
  top: 0%;
  left: 74%;
}
.equip-slot-3 {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  top: 26%;
  right: 74%;
}
.equip-slot-4 {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  top: 26%;
  left: 74%;
}


.card-desc-new {
  position: absolute;
  bottom: 0%;
  display: flex;
  width: 96%;
  flex-direction: column;
  text-align: center;
  border-top: 1px solid rgb(255, 255, 255);
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.603),
    rgba(0, 0, 0, 0.6)
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
#attr {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 25px;
  width: 40px;
  height: 40px;
  justify-content: center;
  text-align: center;
  align-content: center;
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr-attack-new {
  background-image: linear-gradient(
    180deg,
    rgb(180, 4, 4),
    rgb(101, 0, 0)
  );
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
  background-image: linear-gradient(
    180deg,
    rgba(0, 148, 67, 1),
    rgba(0, 74, 33, 1)
  );
}
.attr-resilience-new:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(0, 148, 67, 1),
    rgba(0, 74, 33, 1)
  );
}

</style>
