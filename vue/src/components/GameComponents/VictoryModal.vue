<template>
      <modal name="victory-modal" v-if="show" height="355px" width="485px" :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;">
      <div class="vic-modal-content">
        <div class="vic-modal-header">VICTORY!</div>
        <p class="vic-modal-prompt">The enemy keep is yours!</p>
        <button class="modal-btn-quit" @click="exitGame">EXIT GAME</button>
      </div>
    </modal>
</template>

<script>
export default {
    name: 'victory-modal',
    components: {

    },
    computed: {

    },
    methods: {
        exitGame() {
            this.$emit('exitGame');
        }
    }
}
</script>

<style>

</style>