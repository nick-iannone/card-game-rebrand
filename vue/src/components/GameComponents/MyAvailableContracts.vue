<template>
    <div class="cont-cont-av">
        <draggable class="cont-av" :options="{ group: 'contracts' }" v-model="game.contracts.available">
            <contracttile v-for="contract in game.contracts.available" :key="contract.id" :contract="contract"
                :class="{ contractable: isContractable(contract) }" />
        </draggable>
    </div>
</template>

<script>
import draggable from "vuedraggable";
import contracttile from "@/components/ContractTile.vue";

export default {
    name: "my-available-contracts",
    props: ['game'],
    components: {
        contracttile,
        draggable
    },
    computed: {},
    methods: {
        isContractable(contract) {
            if (
                this.game.myGame.turn.isTurn ||
                this.game.myGame.rollPhase ||
                this.game.myGame.mulliganPhase
            ) {
                return contract.cost <= this.game.myGame.cache;
            }
            return false;
        },
    }
}
</script>

<style>
</style>