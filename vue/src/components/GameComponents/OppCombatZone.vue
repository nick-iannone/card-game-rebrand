<template>
    <div :class="determineTargetingStylingOpp">
        <tile :class="determineOppCardTileClass(card)" @contextmenu.prevent.native="determineContextBehaviorOpp(card)"
            :card="card" :key="card.id" v-for="card in game.oppGame.combatZone.cards" />
    </div>
</template>

<script>
import tile from "@/components/Tile.vue";

export default {
    name: "opp-combat-zone",
    props: ["game"],
    components: {
      tile
    },
    computed: {
        determineTargetingStylingOpp() {
            return {
                "opp-zone": !this.game.choosingOppTargets,
                "zones-targeting-spotlight-one":
                    this.game.oppGame.combatZone.cards.length === 1 &&
                    this.game.choosingOppTargets,
                "zones-targeting-spotlight-two":
                    this.game.oppGame.combatZone.cards.length === 2 &&
                    this.game.choosingOppTargets,
                "zones-targeting-spotlight-three":
                    this.game.oppGame.combatZone.cards.length === 3 &&
                    this.game.choosingOppTargets,
                "zones-targeting-spotlight-four":
                    this.game.oppGame.combatZone.cards.length === 4 &&
                    this.game.choosingOppTargets,
                "zones-targeting-spotlight-five":
                    this.game.oppGame.combatZone.cards.length === 5 &&
                    this.game.choosingOppTargets,
                "zones-targeting-spotlight-sixplus":
                    this.game.oppGame.combatZone.cards.length >= 6 &&
                    this.game.choosingOppTargets,
            };
        },
    },
    methods: {
        determineOppCardTileClass(card) {
            return {
                heroic: card.isHeroic && !card.isTarget && !card.isAttacking,
                fabled: card.isFabled && !card.isTarget && !card.isAttacking,
                hallowed: card.isHallowed && !card.isTarget && !card.isAttacking,
                attacking: card.isAttacking,
                targeted: card.isTarget,
                        canattack:
          card.canAttack && !this.game.myGame.turn.isTurn && !card.isAttacking,
            };
        },
        determineContextBehaviorOpp(card) {
            this.$emit("determineContextBehaviorOpp", card);
        }
    }
}
</script>

<style>
</style>