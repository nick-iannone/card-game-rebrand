<template>
    <div class="shop-card-tile-row">
        <div id="shop-dropdown-card">
            <div class="shop-card-row-header">
                <div class="shop-cont-name">{{ card.name }}</div>
                
            </div>
            <div class="shop-row-me">
                <div class="shop-cont-cost">
                    {{ card.cost
                    }}<font-awesome-icon :icon="['fa', 'coins']" class="new-cost-icon" color="yellow" />
                </div>
            </div>
            <div class="shop-dropdown-content-active" >
                <cardnew :card=card />
            </div>
        </div>
    </div>
</template>
  
<script>
import cardnew from "../CardNew.vue";

export default {
    name: "shopcardtile",
    props: ["card"],
    components: {
        cardnew
    },
    methods: {
        getImageURL(pic) {
            return require("../../assets/cards/" + pic);
        },
        cardNameClass(card) {
            return {
                cardname: !card.isHallowed,
                cardnamehallowed: card.isHallowed,
            };
        },
    },
};
</script>
  
<style>
.shop-row-container {
    display: flex;
    flex-direction: row;
}

.shop-row-me {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    width: auto;
}

.shop-card-row-header {
    display: flex;
    flex-direction: row;
    width: 100%;
    padding: 2px;

}

.shop-card-tile-row {
    background-image: url("../../assets/textures/3px-tile.png"),
        linear-gradient(to right, rgb(0, 0, 0), rgb(4, 59, 77), rgb(0, 0, 0));
        /* background-size: 100%; */
    border-radius: 5px;
    border: 1px solid rgba(255, 255, 255, 0.679);
    width: 96%;
    height: 40px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin: 5px;
}

.shop-card-tile-row:hover {
    cursor: pointer;
    background-image: url("../../assets/textures/soft-wallpaper.png"), linear-gradient(60deg, rgb(14, 172, 53), rgb(2, 78, 36));
}

.shop-dropdown-content-active {
    display: none;
    position: absolute;
    min-width: 265px;
    z-index: 1;
    margin-bottom: 200px;
    margin-left: 2px;
    transform: scale(1.01);
    right: 360px;
    bottom: 43px;
    transform: scale(1.2);
}

#shop-dropdown-card {
    display: flex;
    flex-direction: row;

    width: 100%;
}

#shop-dropdown-card:hover .shop-dropdown-content-active {
    display: flex;
    flex-direction: column;
}

.card-row-counter {
    color: white;
    font-family: 'Bai Jamjuree', sans-serif;

}

.new-cost-icon {
  font-size: 20px;
  margin-left: 4px;
}

.icon-shop-row {
    font-size: 10px;
    margin-right: 5px;
    margin-left: 5px;
}

.icon-shop-row:hover {
    cursor: pointer;
    color: rgb(104, 104, 221);
}

.icon-shop-row-trash {
    font-size: 18px;
    margin-right: 5px;
    margin-left: 15px;
}

.icon-shop-row-trash:hover {
    cursor: pointer;
    color: rgb(202, 36, 36);
}

.icon-shop-row-clicked {
    font-size: 12px;
}

.icon-shop-stat {
    font-size: 15px;
}

.card-shop-dropdown {
    display: flex;
    flex-direction: column;
    border: 10px solid black;
    height: auto;
    width: 250px;
    padding: 8px 12px 8px 12px;
    border-radius: 15px;
    background-image: url("../../assets/textures/soft-wallpaper.png"),
        linear-gradient(rgb(2, 0, 27), rgb(2, 0, 27));
    transition: all 0.2s ease-in-out;
    box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}

.shop-card-attr {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    width: 100%;
}

.shop-dd-attribute {
    color: white;
    font-weight: 600;
    font-family: 'Bai Jamjuree', sans-serif;
    font-size: 16px;
    padding: 0px 10px 0px 10px;
    width: 30%;
    text-align: center;
    background-image: linear-gradient(180deg,
            rgba(0, 0, 0, 0.3),
            rgba(155, 143, 143, 0.3));
    transition: all 0.2s ease-in-out;
    border-radius: 5px;
}

.shop-dd-attribute:hover {
    transform: scale(1.04);
    background-image: linear-gradient(60deg,
            rgb(0, 41, 24, 0.95),
            rgb(0, 110, 73, 0.95));
}
.shop-cont-name {
  color: white;
  font-weight: 550;
  font-family: "Bai Jamjuree";
  padding: 3px 13px 3px 5px;
  text-align: left;
  height: 20px;
}
.shop-cont-cost {
  color: white;
  padding: 3px;
  font-family: "Bai Jamjuree";
  font-weight: 600;
  font-size: 20px;
  text-wrap: nowrap;
}
</style>
  