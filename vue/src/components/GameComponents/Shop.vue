<template>
  <div class="shop">
    <div class="shop-header"><span class="material-symbols-outlined">
        credit_card_heart
      </span>SH0P<span class="material-symbols-outlined">
        credit_card_heart
      </span></div>
    <availableupgrades />
    <availableboons />
    <shopcards />
  </div>
</template>

<script>
import availableupgrades from "@/components/GameComponents/AvailableUpgrades.vue";
import shopcards from "@/components/GameComponents/ShopCards.vue";
import availableboons from "@/components/GameComponents/AvailableBoons.vue";

export default {
  components: {
    availableboons,
    availableupgrades,
    shopcards
  },
}
</script>

<style>
.shop {
  grid-area: "shop";

  /* display: grid; */
  width: 350px;
  height: 854px;
  background-image: url("../../assets/textures/3px-tile.png"), linear-gradient(90deg, rgb(0, 0, 0), rgb(19, 11, 50), rgb(65, 17, 75), rgb(0, 0, 0));
  border: 1px solid rgba(71, 71, 71, 0.345);
  border-radius: 5px;
  margin: 5px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}

.shop-header {
  color: rgb(255, 255, 255);
  font-size: 62px;
  font-weight: 900;
  font-family: "Sixtyfour";
  padding: 15px 0px 3px 0px;
  text-align: center;
  letter-spacing: 0px;
  text-shadow: 2px 2px rgba(255, 255, 255, 0.34);
  background: -webkit-linear-gradient(rgb(243, 243, 243), rgb(241, 221, 3), rgb(255, 224, 87), rgb(255, 255, 255));
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;

}

.material-symbols-outlined {
  font-size: 50px;
}

.icon-arena-resource-shop {
  height: 60px;
}
</style>