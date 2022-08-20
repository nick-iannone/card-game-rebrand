<template>
    <div class="'mid-banner">
        <div class="options">
            <exit-modal v-on:exitGame="runAway" />
        </div>
        <div class="game-info-hub">
            <gamehub ref="gameHub" />
        </div>
        <div class="turn-button">
            <button class="exit-btn" @click="endTurn">END TURN</button>
        </div>
    </div>
</template>

<script>
import gamehub from "@/components/GameComponents/GameHub.vue";
import ExitModal from "@/components/GameComponents/ExitModal.vue";

export default {
    name: 'mid-banner',
    props: ['game'],
    components: {
        gamehub,
        ExitModal
    },
    computed: {},
    methods: {}
}
</script>

<style>

</style>