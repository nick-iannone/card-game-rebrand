<template>
  <div class="tester">
    <tile :card="card"/>
  </div>
</template>

<script>
import tile from "@/components/Tile.vue"

export default {
    data() {
        return {
      // GAME STATE  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      game: {
        hand: [],
        deck: [],
        deadZone: [],
        contracts: {
          deck: [],
          available: [],
        },
        hasAttacked: [],
        choosingOppTargets: false,
        choosingMyTargets: false,
        oppFortAttack: false,
        myFortAttack: false,
        oppRepAttack: false,
        myRepAttack: false,
        myGame: {
          updateRequest: false,
          username: "TESTER",
          readyForBattle: false,
          lastEvent: "",
          directive: {
            name: ""
          },
          concede: false,
          rollPhase: false,
          rollForFirst: 0,
          goesFirst: false,
          mulliganPhase: false,
          keepHand: false,
          keepContracts: false,
          deckCount: 0,
          handCount: 0,
          turnSwitch: false,
          turn: {
            count: 0,
            isTurn: true,
            begin: false,
            end: false,
          },
          attack: {
            attacker: {},
            target: {},
            value: 0,
            type: "",
            isModded: false,
            modValue: 0,
          },
          targetInitiator: "",
          kill: 0,
          fort: 40,
          rep: 15,
          cache: 10,
          combatZone: {
            cards: [],
            mods: [],
          },
          contracts: {
            active: [],
            completed: [],
            mods: [],
          },
          repGainMod: 0,
          repLossMod: 0,
          showOptions: false,
          options: [
            {
              source: "",
              isChosen: false,
              text: "",
            },
            {
              source: "",
              isChosen: false,
              text: "",
            },
            {
              source: "",
              isChosen: false,
              text: "",
            },
            {
              source: "",
              isChosen: false,
              text: "",
            }
          ]
        },
        oppGame: {
          username: this.$store.state.game.oppGame.username,
          lastEvent: "",
          directive: {name: ""},
          readyForBattle: false,
          concede: false,
          rollPhase: false,
          rollForFirst: 0,
          goesFirst: false,
          mulliganPhase: false,
          keepHand: false,
          keepContracts: false,
          turnSwitch: false,
          turn: {
            count: 0,
            isTurn: false,
            begin: false,
            end: false,
          },
          attack: {
            attacker: {},
            target: {},
            value: 0,
            type: "",
            isModded: false,
            modValue: 0,
          },
          targetInitiator: "",
          kill: 0,
          fort: 40,
          rep: 15,
          cache: 10,
          combatZone: {
            cards: [],
            mods: [],
          },
          contracts: {
            active: [],
            completed: [],
            mods: [],
          },
          repGainMod: 0,
          repLossMod: 0,
        },
      },
      card: {
        id: 1,
        name: "Guy Soldier",
        cost: 2,
        type: "Troop - Human",
        imageName: "soldierguy.jpeg",
        text1: "Recyclable",
        text2: "When you recycle this, give a friendly troop +1/+1.",
        text3: "",
        text4: "",
        flavor: "",
        attack: 2,
        resilience: 2,
      },
      shop: {},
      selectedDeck: {},
      // LOADING / ERROR ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      isLoading: true,
    };
    },
    components: {
      tile
    },
}
</script>

<style>
.tester {
    display: flex;
    flex-direction: column;
    align-content: center;
    align-items: center;
    justify-content: center;
    padding: 100px;
}
</style>