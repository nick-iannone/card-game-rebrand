<template>
    <div class="pregame-container"
        v-if="
        this.game.myGame.rollPhase || this.game.myGame.mulliganPhase || !this.game.oppGame.keepContracts || !this.game.oppGame.keepHand">
        <div class="my-zone-start" v-if="isLoading">
            <div class="loader">
                <semipolar-spinner :animation-duration="2000" :size="85" color="#e7eea4" v-if="isLoading" />
            </div>
        </div>

        <div class="my-zone-start" v-if="
            this.game.myGame.rollPhase === true &&
            this.game.oppGame.rollPhase === true &&
            !isLoading &&
            !this.checkRolls
        ">
            <div class="mulligan-prompt" v-if="this.game.myGame.rollForFirst === 0">
                HIT THE BUTTON TO ROLL FOR FIRST!
            </div>
            <button class="nav-button" v-if="this.game.myGame.rollForFirst === 0" @click.prevent="roll">
                ROLL
            </button>
            <div class="mulligan-prompt" v-if="this.game.myGame.rollForFirst != 0">
                You rolled a {{ this.game.myGame.rollForFirst }}
            </div>
            <div class="mulligan-prompt" v-if="this.game.oppGame.rollForFirst != 0">
                Your opponent rolled a {{ this.game.oppGame.rollForFirst }}
            </div>
        </div>

        <div class="my-zone-start" v-if="
            this.game.myGame.keepHand === false && this.game.myGame.mulliganPhase
        ">
            <div class="mulligan-prompt">{{ getFirstPlayer }} first!</div>
            <div class="mulligan-prompt">
                WOULD YOU LIKE TO KEEP THIS HAND OR MULLIGAN FOR A NEW ONE?
            </div>
            <button class="nav-button" @click.prevent="keepHand">KEEP</button>
            <button class="nav-button" @click.prevent="mulliganHand">
                MULLIGAN
            </button>
        </div>

        <div class="my-zone-start" v-if="
            this.game.myGame.keepHand === true &&
            this.game.myGame.keepContracts === false
        ">
            <div class="mulligan-prompt">
                WOULD YOU LIKE TO KEEP THESE CONTRACTS OR MULLIGAN FOR A NEW SET?
            </div>
            <button class="nav-button" @click.prevent="keepContracts">KEEP</button>
            <button class="nav-button" @click.prevent="mulliganContracts">
                MULLIGAN
            </button>
        </div>

        <div class="my-zone-start" v-if="
            this.game.myGame.keepHand &&
            this.game.myGame.keepContracts &&
            (!this.game.oppGame.keepContracts || !this.game.oppGame.keepHand)
        ">
            <div class="mulligan-prompt">
                YOUR OPPONENT IS STILL 'MULLING' IT OVER...
            </div>
        </div>
    </div>
</template>

<script>
import { SemipolarSpinner } from "epic-spinners";

export default {
    name: "pre-game",
    props: ["game", "isLoading"],
    components: [
        SemipolarSpinner
    ],
    computed: {
        checkRolls() {
            if (
                this.game.myGame.rollForFirst > 0 &&
                this.game.oppGame.rollForFirst > 0
            ) {
                this.$emit("determineOutcome");
                return true;
            }
            return false;
        },

        getFirstPlayer() {
            if (!this.game.myGame.goesFirst) {
                return this.game.oppGame.username + " goes";
            } else {
                return "You go";
            }
        },
    },
    methods: {
        roll() {
            this.$emit("roll");
        },
        mulliganContracts() {
            this.$emit("mulliganContracts");
        },
        keepContracts() {
            this.$emit("keepContracts");
        },
        mulliganHand() {
            this.$emit("mulliganHand");
        },
        keepHand() {
            this.$emit("keepHand");
        }
    },
}
</script>

<style>
</style>