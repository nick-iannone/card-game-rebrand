<template>
  <div class="avatar-cont">
    <div class="status-div-my-username">
        TESTER
    </div>
    <div class="image-circle">
      <img class="avatar-image" src="@/assets/cards/unused/L1NN.jpg" />
    </div>
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
    border: 1px solid rgba(160, 159, 159, 0.603);
    margin: 5px;
    gap: 5px;
    height: 399px;
    width: 250px;
    border-radius: 10px;
    background-image: url('../../assets/textures/3px-tile.png'), linear-gradient(180deg,
      rgba(46, 117, 240, 0.7),
      rgb(0, 0, 0));
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
    width: 230px;
    height: 230px;
    border-radius: 50%;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.63);

}
.abilities-cont {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    height: 100px;
    width: 100%;
}
.ability {
    border: 1px solid rgba(92, 228, 255, 0.574);
    border-radius: 5px;
    height: 70px;
    width: 70px;
    background-color: rgba(0, 20, 59, 0.301);
}

.image-circle {
        width: 230px;
        height: 230px;
        border-radius: 50%;
        border-top: 3px ridge #0051ff8c;
        border-right: 3px ridge #ffffffaf;
        border-left: 3px solid rgba(255, 255, 255, 0.479);
        border-bottom: 3px ridge #4973ff88;
        box-shadow: -5px 0 5px rgb(255, 255, 255), 5px 0 5px rgb(216, 9, 188);
        padding: 0px;
        animation: spin 10s infinite linear;
    }
    .image-circle img {
        animation: spin 10s infinite reverse linear;
        width: 100%;
        border-radius: 50%;
    }

    @keyframes spin {
        100% {
            transform: rotate(1turn);
        }}

</style>