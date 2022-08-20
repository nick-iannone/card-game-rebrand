<template>
  <div class="enemy-cont-ac">
        <div class="contract-head">Enemy Subroutines</div>
        <div class="cont-av">
          <enemycont v-for="contract in this.game.oppGame.contracts.active" :key="contract.id" :contract="contract" />
        </div>
      </div>
</template>

<script>
import enemycont from "@/components/EnemyCont.vue";

export default {
    name: "opp-active-contracts",
    props: ['game'],
    components: {
        enemycont,
    },
    computed: {},
    methods: {}
}
</script>

<style>
</style>