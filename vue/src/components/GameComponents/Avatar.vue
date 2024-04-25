<template>
  <div class="avatar-cont">
    <div class="status-div-my-username">
        TESTER
    </div>
    <img class="avatar-image" src="@/assets/cards/unused/L1NN.jpg" />
    <div class="abilities-cont">
        <div class="ability" >

        </div>
        <div class="ability" >

        </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.avatar-cont {
    grid-row-start: avatar;
    grid-column-start: avatar;
    grid-row-end: avatar;
    grid-column-end: avatar;
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 1px solid rgb(43, 43, 43);
    margin: 5px;
    gap: 5px;
    height: 399px;
    width: 250px;
    border-radius: 10px;
    background-image: url("../../assets/textures/datastream-corner.png"), linear-gradient(60deg,
      rgba(46, 117, 240, 0.7),
      rgba(0, 0, 0, 0.7));
}
.status-div-my-username {
  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 22px;
  font-weight: 700;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px 0px 2px 0px;
  border: 1px solid rgb(151, 151, 151);
  border-radius: 5px;
  margin: 5px 5px 0px 5px;
  background-image: linear-gradient(180deg, rgb(0, 26, 39), rgb(12, 59, 153));
  width: 96%;
}
.avatar-image {
    width: 240px;
    height: 240px;
    border-radius: 50%;
    border: 3px solid rgba(79, 179, 255, 0.517);
}
.abilities-cont {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    height: 100px;
    width: 100%;
    background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.804), rgba(0, 0, 0, 0.581));
}
.ability {
    border: 1px solid rgba(92, 228, 255, 0.574);
    border-radius: 5px;
    height: 80px;
    width: 80px;
    background-color: rgba(0, 20, 59, 0.301);
}
</style>