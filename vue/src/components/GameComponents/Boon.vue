<template>
    <div class="boon" >
      <img class="upgrade-img" src="../../assets/icons/2/PNG/19.png" />
  
    </div>
  </template>
  
  <script>
  export default {
    name: "boon",
    props: ["boon"],
  }
  </script>
  
  <style>
  .boon {
    border: 1px solid rgba(255, 255, 255, 0.386);
      border-radius: 5px;
      height: 50px;
      width: 50px;
      background-image: url("../../assets/textures/egg-shell.png"), linear-gradient(65deg, rgb(0, 0, 0),rgb(18, 17, 17), rgba(19, 42, 65, 0.836), rgb(22, 21, 21), rgb(0, 0, 0));
      padding: 1px;
      transition: all 0.2s ease-in-out;
  }
  .boon:hover {
      transform: scale(1.2);
      border: 1px solid rgb(73, 117, 220);
      box-shadow: -5px 0 5px rgb(116, 142, 255), 5px 0 5px rgb(116, 142, 255);
  }
  .upgrade-img {
      height: 50px;
      width: 50px;
  }
  </style>