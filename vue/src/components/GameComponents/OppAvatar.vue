<template>
    <div class="opp-avatar-cont">
      <div class="status-div-opp-username">
          OPPONENT
      </div>
      <div class="opp-avatar-abilities-row">
        <div class="opp-image-circle">
          <img class="opp-avatar-image" src="@/assets/cards/unused/marv.jpg" />
        </div>
        
        <div class="opp-abilities-cont">
          <div class="opp-ability" >
  
          </div>
          <div class="opp-ability" >
  
        </div>
      </div>
      </div> 
    </div>
  </template>
  
  <script>
  export default {
  
  }
  </script>
  
  <style>
  .opp-avatar-cont {
      display: flex;
      flex-direction: column;
      align-items: center;
      border: 1px solid rgb(43, 43, 43);
      height: 200px;
      width: 248px;
      border-radius: 10px;
      background-image: url("../../assets/textures/3px-tile.png"), linear-gradient(60deg,
        rgba(249, 32, 32, 0.7),
        rgba(0, 0, 0, 0.7));
  }
  .status-div-opp-username {
    color: rgb(255, 255, 255);
    font-family: "Orbitron";
    font-size: 17px;
    font-weight: 700;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 2px 0px 2px 0px;
    border: 1px solid rgb(151, 151, 151);
    border-radius: 5px;
    margin: 5px 5px 0px 5px;
    background-image: linear-gradient(180deg, rgb(0, 26, 39), rgb(62, 1, 1));
    width: 96%;
  }
  .opp-avatar-abilities-row {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-evenly;
    height: 100%;
    width: 98%;
  }
  .opp-avatar-image {
      width: 150px;
      height: 150px;
      border-radius: 50%;
  }
  .opp-abilities-cont {
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      height: 148px;
      width: 64px;
      background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.637), rgba(0, 0, 0, 0.581));
      border-radius: 5px;
  }
  .opp-ability {
      border: 1px solid rgba(255, 92, 92, 0.574);
      border-radius: 5px;
      height: 50px;
      width: 50px;
      background-color: rgba(0, 20, 59, 0.301);
  }
  .opp-image-circle {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        border-top: 2px ridge #000000e1;
        border-right: 2px ridge #03945daf;
        border-left: 2px solid rgba(255, 0, 0, 0.479);
        border-bottom: 2px ridge #00000088;
        box-shadow: -5px 0 5px rgb(255, 1, 1), 5px 0 5px rgb(0, 0, 0);
        animation: spin 10s infinite linear;
    }
    .opp-image-circle img {
        animation: spin 10s infinite reverse linear;
        width: 100%;
        border-radius: 50%;
    }
  </style>