<template>
  <div>
    <button class="exit-btn" @click="show">
      CONCEDE
    </button>
    <modal name="exit-modal" v-if="show" height="475px" width="500px"
      styles="background-color: rgb(255, 255, 255, 0.5); border-radius: 10px; border: 2px solid black;">
      <div class=modal-content>
        <div class="modal-header">
          THE BATTLE WILL BE LOST!
        </div>
        <p class="modal-prompt">
          Are you sure you want to exit the current game?
        </p>
        <button class="modal-btn-quit" @click="exitGame">
          RUN AWAY!
        </button>
        <button class="modal-btn-stay" @click="hide">
          STAY AND FIGHT!
        </button>
      </div>
    </modal>
  </div>
</template>

<script>
export default {
  name: "exit-modal",
  methods: {
    show() {
      this.$modal.show("exit-modal");
    },
    hide() {
      this.$modal.hide("exit-modal");
    },
    exitGame() {
      this.$emit('exitGame');
    },
  },
  mount() {
    this.show();
  },
};
</script>

<style>
.modal-content {
  background-color: rgba(0, 0, 0, 0.824);

  height: 100%;
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.modal-header {
  font-family: 'Orbitron';
  font-size: 27px;
  font-weight: 800;
  color: rgb(243, 242, 242);
  text-shadow: 2px 2px black;
}

.modal-prompt {
  color: white;
  font-family: 'Bai Jamjuree', sans-serif;
  font-weight: 200;
  font-size: 15px;
  margin-top: 20px;
}

.modal-btn-quit {
  background-image: url("../../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(90, 0, 0, 0.8), rgba(170, 0, 0, 0.8));
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  width: 250px;
  height: 60px;
  margin: 20px;
  font-family: "Orbitron";
  font-weight: 800;
  font-size: 20px;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
}

.modal-btn-quit:hover {
  transform: scale(1.08);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(255, 0, 0, 0.8), rgba(170, 0, 0, 0.8));
}

.modal-btn-stay {
  background-image: url("../../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 29, 83, 0.8), rgba(2, 32, 168, 0.8));
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  width: 250px;
  height: 60px;
  margin: 20px;
  font-family: "Orbitron";
  font-size: 20px;
  font-weight: 800;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
}

.modal-btn-stay:hover {
  transform: scale(1.08);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 89, 255, 0.8), rgba(2, 32, 168, 0.8));
}

.exit-btn {
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 18px;
  height: 47px;
  width: auto;
  border-radius: 5px;
  border: 1px solid white;
  background-color: rgb(0, 0, 0);
  transition: all 0.2s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 10px 0px 10px;
  margin: 2px 10px 2px 0px;
}

.exit-btn:hover {
  cursor: pointer;
  background-color: rgba(190, 0, 0, 0.9);
}
</style>
