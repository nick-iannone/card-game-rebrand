<template>
  <div class="db-row-container-cont">
    <div class="contract-active-db">
      <div id="dropdown-contract">
        <div class="db-contract-row-header">
          <div class="row-me">
            <div class="cont-name">{{ contract.name }}</div>

            <div class="cont-cost">
              {{ contract.cost }}
              <font-awesome-icon
                :icon="['fa', 'coins']"
                class="icon"
                color="yellow"
              />
            </div>

            <div class="row-me">
              <div class="cont-desc-dura">
                Duration:
                <div class="cont-full-status">
                  <font-awesome-icon
                    :icon="['fa', 'circle']"
                    class="icon-contract"
                    v-for="n in contract.dura"
                    :key="n"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="dropdown-content-active">
          <div class="db-cont-desc">{{ contract.desc }}</div>
        </div>
      </div>

      <div class="row-control">
        <font-awesome-icon
          :icon="['fal', 'minus-square']"
          class="icon-db-row"
          color="white"
          @click="reduceCount(contract)"
        />
        <div class="card-row-counter">{{ contract.count }}</div>
        <font-awesome-icon
          :icon="['fal', 'plus-square']"
          class="icon-db-row"
          color="white"
          @click="increaseCount(contract)"
        />
        <font-awesome-icon
          :icon="['fal', 'trash-alt']"
          class="icon-db-row-trash"
          color="white"
          @click="deleteRow(contract)"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dbcontracttile",
  props: ["contract"],
  methods: {
    reduceCount(contract) {
      if (contract.count > 1) {
        contract.count--;
      }
    },
    increaseCount(contract) {
      if (this.$store.state.newDeckContractCount < 15) {
        if (contract.count === 1) {
          contract.count++;
        }
      }
    },
    deleteRow(contract) {
      this.$emit("deleteRowContract", contract);
    },
  },
};
</script>

<style>
.db-row-container-cont {
  display: flex;
  flex-direction: row;
}
.db-cont-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 8px;
  height: auto;
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 15 px;
  width: 390px;
  background-color: rgb(0, 0, 0, 0.6);
  border-color: white;
}
.row-me {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.db-contract-row-header {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 2px;
  width: 455px;
}
.contract-active-db {
  background-image: linear-gradient(
    60deg,
    rgb(0, 0, 0),
    rgb(8, 95, 121),
    rgb(0, 0, 0)
  );
  border-radius: 5px;
  border: 1px solid rgb(111, 220, 250);
  width: 450px;
  height: auto;
  display: flex;
  flex-direction: row;
  margin: 5px 5px 5px 10px;
}
.contract-active-db:hover {
  cursor: pointer;
    background-image: linear-gradient(
    60deg,
    rgb(35, 68, 85),
    rgb(51, 202, 248),
    rgb(1, 27, 40)
  );
}
#dropdown-contract:hover .dropdown-content-active {
  display: flex;
  flex-direction: column;
  margin-top: 30px;
  margin-right: 3px;
}
#dropdown-contract {
  display: flex;
}
</style>
