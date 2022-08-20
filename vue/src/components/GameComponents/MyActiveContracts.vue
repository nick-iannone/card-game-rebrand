<template>
  <div class="cont-cont-ac">
    <div class="contract-head">Active Subroutines</div>
    <draggable class="cont-ac" :options="{ group: 'contracts' }" v-model="game.myGame.contracts.active"
      @change="checkContPurchase" :disabled="
        !game.myGame.turn.isTurn ||
        game.choosingOppTargets ||
        game.choosingMyTargets
      ">
      <!-- @change="checkContPurchase" -->
      <enemycont :contract="contract" class="cont-tile" v-for="contract in game.myGame.contracts.active"
        :key="contract.id" />
    </draggable>
  </div>
</template>

<script>
import enemycont from "@/components/EnemyCont.vue";
import draggable from "vuedraggable";

export default {
  name: "my-active-contracts",
  props: ['game'],
  components: {
    enemycont,
    draggable
  },
  computed: {},
  methods: {
    checkContPurchase({ added }) {
      this.$emit('checkContPurchase', { added });
    }
  }
}
</script>

<style>
</style>