<template>
  <div class="contract-active">
    <div id="dropdown-nav">
      <div class="cont-header">
        <div class="cont-name">{{ contract.name }}</div>
        <div class="cont-cost">
          {{ contract.cost
          }}<font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
        </div>
      </div>
      <div class="cont-bottom-row">
        <div class="cont-desc-dura">
          Duration:
          <div class="cont-full-status">
            <font-awesome-icon
              :icon="['fa', 'circle']"
              class="icon-contract"
              v-for="n in contract.dura"
              :key="n"
              color=""
            />
          </div>
        </div>
        <div class="cont-desc-dura" v-if="contract.hasCounters">
          Counters:
          <div class="cont-full-status">
            <font-awesome-icon
              :icon="['fa', 'star-of-life']"
              class="icon-contract"
              v-for="n in contract.counters"
              :key="n"
            />
          </div>
        </div>
      </div>

      <div class="dropdown-content-active">
        <div class="cont-desc">{{ contract.desc }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "enemy-cont",
  props: ["contract"],
};
</script>

<style>
.contract-active {
  background-image: linear-gradient(
    60deg,
    rgb(0, 42, 49),
    rgb(25, 167, 177),
    rgb(3, 38, 54)
  );
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 93%;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  margin: 5px 5px 5px 10px;
}
.contract-active:hover {
  cursor: pointer;
  background-image: linear-gradient(60deg, rgb(0, 141, 59), rgb(0, 131, 59));
}
.dropdown-content-active {
  display: none;
  position: absolute;
  background-image: linear-gradient(60deg, rgb(23, 27, 49), rgb(23, 27, 49));
  min-width: 265px;
  z-index: 1;
  margin-top: 5px;
  margin-left: 2px;
  border: 1px solid rgb(179, 179, 179);
  border-radius: 5px;
  transform: scale(1.15);
}
#dropdown-nav:hover .dropdown-content-active {
  display: flex;
  flex-direction: column;
}
.cont-bottom-row {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.fa-circle {
  color: rgb(224, 223, 154);
}
.fa-star-of-life {
  color:rgb(204, 172, 32);
}
</style>
