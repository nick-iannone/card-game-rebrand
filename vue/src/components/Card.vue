<template>
  <div :class="determineRarity(card)">
    <div class="card-header">
      <div id="card-title" :style="this.card.name.length > 15 ? 'fontSize: 11px' : 'fontSize: 15px'" :class="cardNameClass(card)">{{ card.name }}</div>
      
    </div>
    <div
      :class="determineImageBorder(card)"
      :style="{
        backgroundImage: `url(${require('@/assets/cards/' + card.imageName)})`,
      }"
    >
      <div :class="determineDecriptionClass(card)" v-if="this.card.abilities != null">
        <div id="abilities">
          {{ card.abilities }}
        </div>
        <div id="flavor">
          {{ card.flavor }}
        </div>
      </div>
      
      <div class="card-attr">
        <div class="card-cost" v-if="card.cost > 0">
        {{ card.cost }}
        <font-awesome-icon
          :icon="['fa', 'coins']"
          class="icon"
          color="yellow"
        />
      </div>
        <div class="attribute-attack">
          {{ card.attack }}
          
        </div>
        <div class="attribute-resilience">
          {{ card.resilience }}
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "card",
  props: ["card"],
  methods: {
    getImageURL(pic) {
      return require("../assets/cards/" + pic);
    },
    determineRarity(card) {
      return {
        card: !card.isHallowed && !card.isHeroic && !card.isFabled,
        cardhallowed: card.isHallowed,
        cardfabled: card.isFabled,
        cardheroic: card.isHeroic,
        carddreaded: card.isDreaded,
        cardfeared: card.isFeared,
        cardvile: card.isVile
      };
    },
    determineImageBorder(card) {
      return {
        imagecont: !card.isHallowed && !card.isHeroic && !card.isFabled,
        imageconthallowed: card.isHallowed,
        imagecontfabled: card.isFabled,
        imagecontheroic: card.isHeroic,
        imagecontdreaded: card.isDreaded,
        imagecontfeared: card.isFeared,
        imagecontvile: card.isVile
      }
    },
    determineDecriptionClass(card) {
      return {
        'card-desc': !card.isHallowed && !card.isFabled,
        'card-desc-hallowed': card.isHallowed,
        'card-desc-fabled': card.isFabled,
        'card-desc-dreaded': card.isDreaded,
        'card-desc-feared': card.isFeared
      }
    },
    cardNameClass(card) {
      return {
        cardname: !card.isHallowed,
        cardnamehallowed: card.isHallowed,
        cardnamedreaded: card.isDreaded,
        cardnamefeared: card.isFeared,
        cardnamefabled: card.isFabled,
        cardnameheroic: card.isHeroic,
        cardnamevile: card.isVile,
      }
    }
  },
};
</script>

<style>
.card {
  display: flex;
  flex-direction: column;
  border: 10px solid black;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(2, 0, 27), rgb(2, 0, 27));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.carddreaded {
  display: flex;
  flex-direction: column;
  border-width: 10px;
  border-style: solid;
  border-image: linear-gradient(to bottom right, rgb(24, 24, 24), rgb(250, 74, 74), rgb(26, 26, 26)) 1;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/assault.png"),
    linear-gradient(rgb(0, 0, 0), rgb(0, 0, 0));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  
}
.carddreaded:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.cardhallowed {
  display: flex;
  flex-direction: column;
  border: 10px solid rgb(0, 0, 0);
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/subtle-white-feathers.png"),
    linear-gradient(rgb(7, 60, 95), rgb(59, 117, 148));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
   border-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='75' height='75'%3E%3Cg fill='none' stroke='%23B88846' stroke-width='2'%3E%3Cpath d='M1 1h73v73H1z'/%3E%3Cpath d='M8 8h59v59H8z'/%3E%3Cpath d='M8 8h16v16H8zM51 8h16v16H51zM51 51h16v16H51zM8 51h16v16H8z'/%3E%3C/g%3E%3Cg fill='%23B88846'%3E%3Ccircle cx='16' cy='16' r='2'/%3E%3Ccircle cx='59' cy='16' r='2'/%3E%3Ccircle cx='59' cy='59' r='2'/%3E%3Ccircle cx='16' cy='59' r='2'/%3E%3C/g%3E%3C/svg%3E")
    2;
}
.cardhallowed:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.05);
  cursor: pointer;
}
.cardfeared {
  display: flex;
  flex-direction: column;
  border-width: 10px;
  border-style: solid;
  border-image: linear-gradient(to right, rgb(0, 0, 0), rgb(0, 122, 61), rgb(0, 0, 0)) 1;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(to left, rgb(0, 0, 0), rgb(0, 198, 99), rgb(7, 7, 7));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.cardfeared:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.cardfabled {
  display: flex;
  flex-direction: column;
  border-width: 10px;
  border-style: solid;
  border-image: linear-gradient(to right, rgb(31, 228, 139), rgb(255, 255, 255), rgb(31, 228, 139)) 1;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/subtle-white-feathers.png"),
    linear-gradient(to left, rgb(11, 86, 45), rgb(5, 163, 81), rgb(5, 74, 32));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.cardfabled:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.cardheroic {
  display: flex;
  flex-direction: column;
  border-width: 10px;
  border-style: solid;
  border-image: linear-gradient(to bottom right, rgb(255, 255, 255), rgb(94, 34, 101), rgb(255, 255, 255)) 1;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/soft-wallpaper.png"),
    linear-gradient(rgb(58, 1, 63), rgb(188, 9, 211));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.cardheroic:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.cardvile {
  display: flex;
  flex-direction: column;
  border-width: 10px;
  border-style: solid;
  border-image: linear-gradient(to right, rgb(0, 0, 0), rgb(30, 85, 117), rgb(0, 0, 0)) 1;
  height: 300px;
  width: 200px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url("../assets/textures/3px-tile.png"),
    linear-gradient(to right,  rgb(30, 85, 117), rgb(0, 0, 0), rgb(30, 85, 117));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.cardvile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.card:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
/* .card-header {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 30px;

} */
#card-title {
  font-size: 70%;
  text-wrap: nowrap;
  text-align: center;
  height: 18px;
}
.cardname {
  color: white;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 5px 13px 3px 13px;
  border: 1px solid rgba(255, 255, 255, 0.356);
  border-radius: 5px;
  background-image: linear-gradient(60deg, rgb(0, 0, 0), rgb(54, 54, 54));
  text-align: left;
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);
  height: 20px;
  width: 100%;
  align-items: center;
}
.cardnamedreaded {
  color: rgb(255, 255, 255);
  font-size: 80%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid #ff000096;
  background-image: linear-gradient(60deg, rgba(0, 0, 0, 0.918), rgb(116, 54, 54), rgba(0, 0, 0, 0.986));
  text-align: left;
  height: 20px;
    box-shadow: 0 0 1px 1px #ff000096, 0 0 2px 2px rgb(255, 255, 255),
    0 0 2px 2px rgb(70, 3, 1);
}
.cardnamehallowed {
  color: white;
  font-size: 66%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid #00c1d6;
  background-image: linear-gradient(60deg, rgba(2, 25, 48, 0.618), rgb(0, 108, 167), rgba(2, 17, 53, 0.857));
  text-align: left;
  height: 20px;
    box-shadow: 0 0 2px 2px rgb(20, 63, 76), 0 0 2px 2px rgb(2, 80, 154),
    0 0 2px 2px rgb(1, 33, 70);
}
.cardnamefeared {
  color: rgb(255, 255, 255);
  font-size: 80%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid #00d65996;
  background-image: linear-gradient(80deg, rgba(0, 0, 0, 0.918), rgb(42, 42, 42), rgba(2, 2, 2, 0.986));
  text-align: left;
  height: 20px;
    box-shadow: 0 0 1px 1px #00000096,
    0 0 2px 2px rgb(0, 0, 0);
}
.cardnamefabled {
  color: rgb(255, 255, 255);
  font-size: 80%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid #00af6396;
  background-image: linear-gradient(60deg, rgba(0, 48, 27, 0.918), rgb(18, 126, 79), rgba(0, 48, 27, 0.918));
  text-align: left;
  height: 20px;
    box-shadow: 0 0 1px 1px #00ff8096, 0 0 2px 2px rgb(255, 255, 255),
    0 0 2px 2px rgb(1, 70, 42);
}
.cardnamevile {
  color: rgb(255, 255, 255);
  font-size: 80%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid rgb(30, 85, 117);
  background-image: linear-gradient(60deg, rgba(0, 0, 0, 0.918), rgb(30, 85, 117), rgba(0, 0, 0, 0.986));
  text-align: left;
  height: 20px;
    box-shadow:
    0 0 2px 2px rgb(18, 31, 45);
}
.cardnameheroic {
  color: rgb(255, 255, 255);
  font-size: 80%;
  font-weight: 550;
  font-family: 'Orbitron', sans-serif;
  padding: 3px;
  border: 1px solid #ea00ff96;
  background-image: linear-gradient(60deg, rgba(22, 3, 38, 0.918), rgb(166, 57, 187), rgba(0, 0, 0, 0.986));
  text-align: left;
  height: 20px;
    box-shadow: 0 0 1px 1px #ffffff96, 0 0 1px 1px rgb(255, 255, 255),  
    0 0 2px 2px rgb(44, 1, 70);
}

.imagecont {
  border: 1px solid rgba(255, 255, 255, 0.344);
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}
.imagecontfeared {
  border: 1px solid #00d65996;
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  box-shadow: 0 0 2px 2px rgb(0, 0, 0), 0 0 2px 2px #00d65996,
    0 0 2px 2px #00562496;
}
.imagecontfabled {
  animation-name: fabledimageborder;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;

  border: 1px solid rgb(3, 133, 100);
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  box-shadow: 0 0 2px 2px rgb(72, 255, 133), 0 0 2px 2px rgb(196, 235, 220),
    0 0 2px 2px rgb(1, 67, 70);
}
@keyframes fabledimageborder {
  20% {
    box-shadow: 0 0 2px 2px rgb(125, 255, 227), 0 0 2px 2px rgb(111, 172, 144),
    0 0 2px 2px rgb(1, 54, 43);
  }
  50% {
    box-shadow: 0 0 2px 2px rgb(72, 255, 194), 0 0 2px 2px rgb(93, 223, 201),
    0 0 2px 2px rgb(5, 92, 63);
  }
  80% {
    box-shadow: 0 0 2px 2px rgb(72, 255, 209), 0 0 2px 2px rgb(196, 235, 232),
    0 0 2px 2px rgb(5, 92, 66);
  }
  100% {
    box-shadow: 0 0 2px 2px rgb(72, 255, 148), 0 0 2px 2px rgb(196, 235, 225),
    0 0 2px 2px rgb(2, 61, 46);
  }
}
.imagecontdreaded {
  animation-name: dreadedimageborder;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  box-shadow: 0 0 2px 2px rgb(218, 161, 161), 0 0 2px 2px rgb(145, 29, 29),
    0 0 2px 2px rgb(70, 3, 1);
}
@keyframes dreadedimageborder {
  20% {
    box-shadow: 0 0 2px 2px rgb(243, 117, 117), 0 0 2px 2px rgb(202, 78, 78),
    0 0 2px 2px rgba(255, 8, 0, 0.681);
  }
  50% {
    box-shadow: 0 0 2px 2px rgb(252, 91, 91), 0 0 2px 2px rgb(201, 85, 85),
    0 0 2px 2px rgb(245, 136, 136);
  }
  80% {
    box-shadow: 0 0 2px 2px rgb(255, 160, 160), 0 0 2px 2px rgb(190, 28, 28),
    0 0 2px 2px rgb(105, 2, 2);
  }
  100% {
   box-shadow: 0 0 2px 2px rgb(252, 221, 221), 0 0 2px 2px rgb(250, 27, 27),
    0 0 2px 2px rgb(95, 4, 1);
  }
}
.imageconthallowed {
  animation-name: hallowedimageborder;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  box-shadow: 0 0 2px 2px rgb(218, 209, 161), 0 0 2px 2px rgb(216, 182, 70),
    0 0 2px 2px rgb(70, 3, 1);
}
@keyframes hallowedimageborder {
  20% {
    box-shadow: 0 0 2px 2px rgb(243, 230, 117), 0 0 2px 2px rgb(202, 173, 78),
    0 0 2px 2px rgb(110, 5, 1);
  }
  50% {
    box-shadow: 0 0 2px 2px rgb(252, 195, 91), 0 0 2px 2px rgb(201, 174, 85),
    0 0 2px 2px rgb(245, 179, 136);
  }
  80% {
    box-shadow: 0 0 2px 2px rgb(255, 160, 160), 0 0 2px 2px rgb(190, 153, 28),
    0 0 2px 2px rgb(105, 5, 2);
  }
  100% {
   box-shadow: 0 0 2px 2px rgb(252, 221, 221), 0 0 2px 2px rgb(250, 198, 27),
    0 0 2px 2px rgb(95, 4, 1);
  }
}
.imagecontheroic {
  border: 1px solid white;
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}
.card-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgb(160, 160, 160);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(82, 82, 82, 0.568),
    rgba(8, 17, 44, 0.61)
  );
  transition: all 0.2s ease-in-out;
  font-family: 'Source Code Pro';
}
.card-desc:hover {
  transform: scale(1.3);
  background-image: linear-gradient(
    60deg,
    rgba(4, 7, 32, 0.938),
    rgba(9, 13, 44, 0.918)
  );
}
.card-desc-fabled {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgb(160, 160, 160);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(7, 46, 23, 0.568),
    rgba(8, 44, 27, 0.61)
  );
  transition: all 0.2s ease-in-out;
}
.card-desc-fabled:hover {
  transform: scale(1.3);
  background-image: linear-gradient(
    60deg,
    rgba(4, 32, 21, 0.974),
    rgba(5, 53, 31, 0.946)
  );
}
.card-desc-feared {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid #00d65996;
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.568),
    rgba(0, 0, 0, 0.61)
  );
  transition: all 0.2s ease-in-out;
}
.card-desc-feared:hover {
  transform: scale(1.3);
  border: 1px solid rgb(125, 125, 125);
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.974),
  #004d2096);
}
.card-desc-hallowed {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgba(142, 153, 16, 0.767);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(3, 57, 75, 0.631),
    rgba(90, 174, 189, 0.562),
    rgba(0, 40, 75, 0.568)
  );
  transition: all 0.2s ease-in-out;
}
.card-desc-hallowed:hover {
  transform: scale(1.3);
  background-image: linear-gradient(
    60deg,
    rgba(4, 24, 32, 0.938),
    rgba(9, 39, 44, 0.918)
  );
}
.card-desc-dreaded {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgba(150, 88, 56, 0.767);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(66, 66, 66, 0.631),
    rgba(0, 0, 0, 0.562),
    rgba(90, 88, 88, 0.568)
  );
  transition: all 0.2s ease-in-out;
}
.card-desc-dreaded:hover {
  transform: scale(1.3);
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.938),
    rgba(2, 2, 2, 0.918)
  );
}
#abilities {
  color: white;
  font-size: 13px;
  font-family: 'Bai Jamjuree', sans-serif;
  font-weight: 500;
}
#flavor {
  color: white;
  font-family: 'Source Code Pro';
  font-style: italic;
  font-size: 12px;
  margin-top: 10px;
}
.card-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
  background-image: linear-gradient(60deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.479));
  padding: 1px;
}
.card-cost {
  color: white;
  margin-bottom: 2px;
  font-family: 'Orbitron', sans-serif;
  font-size: 14px;
  font-weight: 600;
  /* border: 1px solid rgba(255, 255, 255, 0.5); */
  border-radius: 5px;
  background-image: linear-gradient(60deg, rgb(0, 0, 0), rgb(54, 54, 54));
  display: flex;
  justify-content: center;
  align-items: center;
  width: 23%;
  text-wrap: nowrap;
  letter-spacing: 2px;
  transition: all 0.2s ease-in-out;
}
.card-cost:hover {
  transform: scale(1.4);
}
.coin {
  height: 12px;
  margin-top: 3px;
  margin-left: 5px;
}
.attribute-attack {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 28px;
  padding: 1px 5px 1px 5px;
  width: 23%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(217, 0, 0, 0.888),
    rgba(101, 0, 0, 0.881)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attribute-attack:hover {
  transform: scale(1.4);
}
.attribute-resilience {
  color: white;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  font-size: 28px;
  padding: 1px 10px 1px 10px;
  width: 23%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(0, 148, 67, 0.873),
    rgba(0, 74, 33, 0.889)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attribute-resilience:hover {
  transform: scale(1.4);
}
#attr-desc {
  font-size: 10px;
  font-weight: 500;
}
p {
  margin: 2px;
}
</style>
