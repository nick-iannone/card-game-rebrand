<template>
    <draggable
      :class="determineTargetingStylingFriendly"
      v-model="game.myGame.combatZone.cards"
      :options="{ group: 'cards' }"
      :move="checkDestination"
      v-if="
        game.myGame.keepHand &&
          game.myGame.keepContracts &&
          game.oppGame.keepHand &&
          game.oppGame.keepContracts
      "
      :disabled="
        !game.myGame.turn.isTurn ||
          game.choosingOppTargets ||
          game.choosingMyTargets
      "
      @change="checkCardPurchase"
    >
      <!-- --------- TURN ON/OFF DRAGGABLE FEATURES ---------
            :disabled="!game.myGame.turn.isTurn"
            @change="checkCardPurchase"
            :move="checkDestination" -->

      <tile
        @contextmenu.prevent.native="determineContextBehaviorFriendly(card)"
        :class="determineMyCardTileClass(card)"
        :card="card"
        :key="card.id"
        v-for="card in game.myGame.combatZone.cards"
      />
    </draggable>
</template>

<script>
import draggable from "vuedraggable";
import tile from "@/components/Tile.vue";

export default {
  name: "my-combat-zone",
  props: ["game"],
  components: {
      draggable,
      tile
  },
  computed: {
    determineTargetingStylingFriendly() {
      return {
        "my-zone": !this.game.choosingMyTargets,
        "zones-targeting-spotlight-one-friendly":
          this.game.myGame.combatZone.cards.length === 1 &&
          this.game.choosingMyTargets,
        "zones-targeting-spotlight-two-friendly":
          this.game.myGame.combatZone.cards.length === 2 &&
          this.game.choosingMyTargets,
        "zones-targeting-spotlight-three-friendly":
          this.game.myGame.combatZone.cards.length === 3 &&
          this.game.choosingMyTargets,
        "zones-targeting-spotlight-four-friendly":
          this.game.myGame.combatZone.cards.length === 4 &&
          this.game.choosingMyTargets,
        "zones-targeting-spotlight-five-friendly":
          this.game.myGame.combatZone.cards.length === 5 &&
          this.game.choosingMyTargets,
        "zones-targeting-spotlight-sixplus-friendly":
          this.game.myGame.combatZone.cards.length >= 6 &&
          this.game.choosingMyTargets,
      };
    },
  },

  methods: {
    checkCardPurchase({ added }) {
      this.$emit("checkCardPurchase", { added });
    },

    determineContextBehaviorFriendly(card) {
      this.$emit('determineContextBehaviorFriendly', card);
    },

    checkDestination(evt) {
      if (evt.relatedContext.component.$attrs.name === "my-hand-component") {
        return false;
      }
    },

    determineMyCardTileClass(card) {
      return {
        heroic:
          card.isHeroic &&
          !card.isTarget &&
          !card.isAttacking &&
          !card.canAttack,
        fabled:
          card.isFabled &&
          !card.isTarget &&
          !card.isAttacking &&
          !card.canAttack,
        hallowed:
          card.isHallowed &&
          !card.isTarget &&
          !card.isAttacking &&
          !card.canAttack,
        attacking: card.isAttacking,
        targeted: card.isTarget,
        canattack:
          card.canAttack && this.game.myGame.turn.isTurn && !card.isAttacking,
      };
    },
  },
};
</script>

<style>
</style>
