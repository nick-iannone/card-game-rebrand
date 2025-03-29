<template>
  <div class="upgrade" >
    <!-- <img class="upgrade-img" v-bind:src="getUpgradeImageURL(upgrade.image)" /> -->

  </div>
</template>

<script>
export default {
  data () {
    return
  },
  methods: {
    // getUpgradeImageURL(image) {
    //   return require("../assets/upgrades/" + image);
    // }
  }
}
</script>

<style>
.upgrade {
    border: 1px solid rgba(255, 255, 255, 0.386);
    border-radius: 5px;
    height: 50px;
    width: 50px;
    background-image: url("../../assets/textures/egg-shell.png"), linear-gradient(65deg, rgb(0, 0, 0),rgb(18, 17, 17), rgba(19, 42, 65, 0.836), rgb(22, 21, 21), rgb(0, 0, 0));
    padding: 1px;
    transition: all 0.2s ease-in-out;
}
.upgrade:hover {
    transform: scale(1.2);
    border: 1px solid rgb(41, 213, 130);
    box-shadow: -5px 0 5px rgb(18, 218, 135), 5px 0 5px rgb(7, 211, 146);
}
.upgrade-img {
    height: 50px;
    width: 50px;
}
</style>