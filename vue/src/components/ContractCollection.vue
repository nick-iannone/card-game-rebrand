<template>
  <div class="cont-collect">

    <div class="cont-collect-header">
      <div class="cont-collect-name">{{ contract.name }}</div>
      <div class="cont-collect-cost">
        {{ contract.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon-contract"
            color="yellow"
          />
      </div>
    </div>

    <div class="cont-collect-desc">{{ contract.desc }}</div>

    <div class="cont-collect-dura">
      Duration:
      <div class="cont-collect-status">
        <font-awesome-icon :icon="['fa', 'circle']" class="icon-contract" v-for="n in contract.dura" :key="n" />
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "contract-collection",
  props: ["contract"],
};
</script>

<style>
.cont-collect {
  border: 1px solid #46a3b8;
  border-radius: 5px;
  background-image: url('../assets/textures/assault.png'), linear-gradient(rgb(0, 17, 80), rgb(86, 14, 114));
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;

  margin: 5px;
  width: 296px;
  height: 300px;
  padding: 5px;
  transition: all 0.2s ease-in-out;
}
.cont-collect:hover {
  transform: scale(1.05);
  cursor: pointer;
}
.cont-collect-status {
  display: flex;
  flex-direction: row;
  gap: 4px;
  padding: 4px 10px 4px 4px;
}
.cont-collect-status > img {
  width: 15px;
  height: 15px;
  border: 5px solid rgb(82, 192, 55);
  border-radius: 50%;
  box-shadow: 1px 1px rgb(85, 85, 85);
}
.cont-collect-header {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  font-size: 1rem;
}
.cont-collect-name {
  color: rgb(95, 176, 242);
  font-weight: 400;
  font-family: 'Orbitron', sans-serif;
  padding: 3px 13px 3px 4px;
  text-align: left;
}
.cont-collect-cost {
  color: white;
  padding: 3px;
  font-family: "Bai Jamjuree";
  font-weight: 500;
  display: flex;
  flex-direction: row;
}
.cont-collect-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 1rem;
}
.cont-collect-dura {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 1rem;
  align-items: center;
}
</style>
