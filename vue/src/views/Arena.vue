<template>
  <div>
    <div class="arena">
      <oppstatustile :game='game' />
      <oppcombatzone :game="game" @determineContextBehaviorOpp="determineContextBehaviorOpp" />

      <div class="options">
        <exit-modal v-on:exitGame="runAway" />
      </div>
      <div class="game-info-hub">
        <gamehub ref="gameHub" />
      </div>
      <div class="turn-button">
        <button class="exit-btn" @click="endTurn">END TURN</button>
      </div>

      <mystatustile :game='game' />
      <mycombatzone :game='game' @determineContextBehaviorFriendly="determineContextBehaviorFriendly"
        @checkCardPurchase='checkCardPurchase' />
      <pregame :isLoading='isLoading' :game='game' @roll='roll' @keepHand='keepHand' @mulliganHand='mulliganHand'
        @keepContracts='keepContracts' @mulliganContracts='mulliganContracts' @determineOutcome="determineOutcome" />
      <myhand :game="game" />

      <div class="deck">
        <img v-if="this.game.deck.length > 0" class="deck-img" src="../assets/icons/card_back.png" />
      </div>

      <oppactivecontracts :game='game' />
      <myactivecontracts :game="game" @checkContPurchase="checkContPurchase" />
      <myavailablecontracts :game="game" />
    </div>

    <modal name="choose-one-modal" v-if="showOptions" height="325px" width="500px"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;">
      <div class=modal-content>
        <div class="modal-header">
          CHOOSE ONE:
        </div>     
        <div class="option-cont" v-if="getOption1" v-on:click="option1" >{{ getOption1 }}</div>
        <div class="option-cont" v-if="getOption2" v-on:click="option2">{{ getOption2 }}</div>
        <div class="option-cont" v-if="getOption3" v-on:click="option3">{{ getOption3 }}</div>
      </div>
    </modal>

    <modal name="victory-modal" v-if="show" height="355px" width="485px" :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.8); border-radius: 10px; border: 2px solid black;">
      <div class="vic-modal-content">
        <div class="vic-modal-header">VICTORY!</div>
        <p class="vic-modal-prompt">The enemy keep is yours!</p>
        <button class="modal-btn-quit" @click="exitGame">EXIT GAME</button>
      </div>
    </modal>

    <modal name="defeat-modal" v-if="showDefeat" height="355px" width="485px" :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.8); border-radius: 10px; border: 2px solid black;">
      <div class="vic-modal-content">
        <div class="vic-modal-header">DEFEAT!</div>
        <p class="vic-modal-prompt">All is lost!</p>
        <button class="modal-btn-quit" @click="exitGame">EXIT GAME</button>
      </div>
    </modal>
  </div>
</template>


<script>
//  IMPORTS ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import ExitModal from "@/components/GameComponents/ExitModal.vue";
import gamehub from "@/components/GameHub.vue";
import mycombatzone from "@/components/GameComponents/MyCombatZone.vue";
import oppstatustile from "@/components/GameComponents/OppStatusTile.vue";
import mystatustile from "@/components/GameComponents/MyStatusTile.vue";
import pregame from "@/components/GameComponents/PreGame.vue";
import oppcombatzone from "@/components/GameComponents/OppCombatZone.vue";
import myhand from "@/components/GameComponents/MyHand.vue";
import oppactivecontracts from "@/components/GameComponents/OppActiveContracts.vue";
import myactivecontracts from "@/components/GameComponents/MyActiveContracts.vue";
import myavailablecontracts from "@/components/GameComponents/MyAvailableContracts.vue";

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
          username: this.$store.state.user.username,
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
      selectedDeck: {},
      // LOADING / ERROR ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      isLoading: true,
    };
  },

  // COMPONENTS  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  components: {
    ExitModal,
    gamehub,
    mycombatzone,
    oppstatustile,
    mystatustile,
    pregame,
    oppcombatzone,
    myhand,
    oppactivecontracts,
    myactivecontracts,
    myavailablecontracts,
  },

  // COMPUTED  CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
  computed: {

    getFirstPlayer() {
      if (!this.game.myGame.goesFirst) {
        return this.game.oppGame.username + " goes";
      } else {
        return "You go";
      }
    },

    swapFeedByTurn() {
      if (
        this.game.myGame.turn.isTurn === true ||
        this.game.myGame.rollPhase === true ||
        this.game.myGame.mulliganPhase === true
      ) {
        return true;
      } else return false;
    },

    getOption1() {
      return this.game.myGame.options[0].text;
    },
    getOption2() {
      return this.game.myGame.options[1].text;
    },
    getOption3() {
      return this.game.myGame.options[2].text;
    },
  },
  // METHODS  /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
  methods: {
    // PRE-GAME  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    roll() {
      let roll = this.getRandomIntInclusive(1, 100);
      this.game.myGame.rollForFirst = roll;
      this.sendGameState();
    },

    getRandomIntInclusive(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1) + min);
    },

    wonRoll() {
      this.$refs.gameHub.rotate(
        this.game.myGame.username +
        " goes first (" +
        this.game.myGame.rollForFirst +
        " vs " +
        this.game.oppGame.rollForFirst +
        ")"
      );
      this.game.myGame.goesFirst = true;
      this.game.myGame.rollPhase = false;
      this.game.myGame.rollForFirst = 0;
      this.game.myGame.mulliganPhase = true;
      this.sendGameState();
    },

    lostRoll() {
      this.$refs.gameHub.rotate(
        this.game.oppGame.username +
        " goes first (" +
        this.game.oppGame.rollForFirst +
        " vs " +
        this.game.myGame.rollForFirst +
        ")"
      );
      this.game.myGame.goesFirst = false;
      this.game.myGame.rollPhase = false;
      this.game.myGame.rollForFirst = 0;
      this.game.myGame.mulliganPhase = true;
      this.sendGameState();
    },

    tiedRoll() {
      this.$refs.gameHub.rotate(
        "Roll tied! (" +
        this.game.oppGame.rollForFirst +
        " vs " +
        this.game.myGame.rollForFirst +
        ")"
      );
      this.game.myGame.rollForFirst = 0;
      this.game.oppGame.rollForFirst = 0;
      this.sendGameState();
    },

    determineOutcome() {
      if (this.game.myGame.rollForFirst === this.game.oppGame.rollForFirst) {
        this.tiedRoll();
      } else if (
        this.game.myGame.rollForFirst > this.game.oppGame.rollForFirst
      ) {
        this.wonRoll();
      } else {
        this.lostRoll();
      }
    },

    keepHand() {
      this.game.myGame.keepHand = true;
      this.sendGameState();
    },

    mulliganHand() {
      for (var i = 0; i < this.game.hand.length; i++) {
        this.game.deck.push(this.game.hand[i]);
      }
      this.game.hand = [];
      this.shuffle(this.game.deck);
      this.drawCard();
      this.drawCard();
      this.drawCard();
      this.drawCard();
      this.game.myGame.keepHand = true;
      this.sendGameState();
    },

    keepContracts() {
      this.game.myGame.keepContracts = true;
      this.game.myGame.mulliganPhase = false;
      if (this.game.myGame.goesFirst) {
        this.beginTurn();
      } else {
        this.sendGameState();
      }
    },

    mulliganContracts() {
      for (var i = 0; i < this.game.contracts.available.length; i++) {
        this.game.contracts.deck.push(this.game.contracts.available[i]);
      }
      this.game.contracts.available = [];
      this.shuffle(this.game.contracts.deck);
      this.drawContract();
      this.drawContract();
      this.drawContract();
      this.drawContract();
      this.game.myGame.keepContracts = true;
      this.game.myGame.mulliganPhase = false;
      if (this.game.myGame.goesFirst === true) {
        this.beginTurn();
      } else {
        this.sendGameState();
      }
    },

    // DECK MANIPULATION  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    drawCard() {
      if (this.game.hand.length <= 6) {
        let card = this.game.deck.shift();
        this.game.hand.push(card);
      }
    },

    drawContract() {
      if (this.game.contracts.deck.length === 0) {
        return;
      } else if (this.game.contracts.available.length <= 4) {
        let contract = this.game.contracts.deck.shift();
        this.game.contracts.available.push(contract);
      }
    },

    shuffle(array) {
      for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    },

    // GAME-KEEPING  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    beginTurn() {
      this.game.myGame.turn.isTurn = true;
      this.game.myGame.turn.begin = true;
      this.game.myGame.turn.count += 1;
      this.$refs.gameHub.rotate(
        this.game.myGame.username +
        "'s turn (" +
        this.game.myGame.turn.count +
        ")"
      );
      this.game.myGame.lastEvent =
        this.game.myGame.username +
        "'s turn (" +
        this.game.myGame.turn.count +
        ")";
      this.resolveStartOfTurnTriggers();
      this.sendGameState();
      this.resetAttackObject();
      this.game.myGame.directive = {};
    },

    resolveStartOfTurnTriggers() {
      if (this.game.myGame.turn.count > 1) {
        this.game.myGame.cache += 5 * (this.game.myGame.turn.count + 1);
        this.refreshAttackers();
        this.drawCard();
        this.drawContract();
        this.onTurnStartCheck();
      }
      if (this.game.myGame.turn.count === 1 && !this.game.myGame.goesFirst) {
        this.drawCard();
      }
    },

    refreshAttackers() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].canAttack = true;
      }
    },

    resetAttackObject() {
      this.game.myGame.attack = {
        attacker: {},
        target: {},
        value: 0,
        type: "",
        isModded: false,
        modValue: 0,
      };
    },

    endTurn() {
      if (this.game.myGame.turn.isTurn) {
        this.game.myGame.turn.isTurn = false;
        this.game.myGame.turn.begin = false;
        this.game.myGame.turn.plan = false;
        this.game.myGame.turn.combat = false;
        this.game.myGame.turn.plan2 = false;
        this.game.myGame.turn.end = false;
        for (var t = 0; t < this.game.myGame.combatZone.cards.length; t++) {
          this.game.myGame.combatZone.cards[t].isAttacking = false;
        }
        this.game.hasAttacked = [];
        this.resetAttackObject();
        this.game.myGame.turnSwitch = true;
        this.sendGameState();
        this.game.myGame.turnSwitch = false;
      }
    },

    switchTurns() {
      if (!this.game.myGame.turn.isTurn) {
        this.beginTurn();
      }
    },

    updateMyGameState() {
      this.$store.commit("UPDATE_MY_GAME_STATE", this.game.myGame);
    },

    sendGameState() {
      this.game.myGame.deckCount = this.game.deck.length;
      this.game.myGame.handCount = this.game.hand.length;
      this.checkForInformant();
      this.updateMyGameState();
      this.$store.state.stompClient.send(
        "/app/game/match/" + this.$store.state.game.oppGame.username,
        JSON.stringify(this.game.myGame)
      );
    },

    // ECONOMY  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    checkContPurchase({ added }) {
      if (added) {
        if (
          added.element.cost > this.game.myGame.cache ||
          this.game.myGame.contracts.active.length == 6
        ) {
          this.game.myGame.contracts.active.splice(added.newIndex, 1);
          this.game.contracts.available.push(added.element);
        } else {
          const charge = added.element.cost;
          this.game.myGame.cache -= charge;
          this.$refs.gameHub.rotate(
            this.game.myGame.username + " started " + added.element.name
          );
          this.game.myGame.lastEvent =
            this.game.myGame.username + " started " + added.element.name;
          this.sendGameState();
        }
      }
    },

    checkCardPurchase({ added }) {
      if (added) {
        if (
          added.element.cost > this.game.myGame.cache ||
          this.game.myGame.combatZone.cards.length === 11
        ) {
          this.game.myGame.combatZone.cards.splice(added.newIndex, 1);
          this.game.hand.push(added.element);
        } else {
          this.game.myGame.cache -= added.element.cost;
          this.$refs.gameHub.rotate(
            this.game.myGame.username + " played " + added.element.name
          );
          this.game.myGame.lastEvent =
            this.game.myGame.username + " played " + added.element.name;
          if (added.element.onEnter) {
            try {
              this[this.camelize(added.element.name)](added.element);
            } catch (error) {
              console.warn(error);
            }
          }
          this.checkForCombatZoneMods(added.element, "enter");
          this.sendGameState();
          this.game.myGame.directive = {};
          this.resetAttackObject();
          if (added.element.onEnter) {
            try {
              this[this.camelize(added.element.name) + "Target"](added.element);
            } catch (error) {
              console.warn(error);
            }
          }
        }
      }
    },

    // COMBAT  #############################################################################################################################################
    // COMBAT  #############################################################################################################################################
    // COMBAT  #############################################################################################################################################
    // COMBAT  #############################################################################################################################################

    toggleAttacking(card) {
      if (this.game.myGame.turn.isTurn) {
        if (this.game.hasAttacked != []) {
          for (var i = 0; i < this.game.hasAttacked.length; i++) {
            if (this.game.hasAttacked[i].id === card.id) {
              card.canAttack = false;
            }
          }
        }
        if (
          card.name === "Thunderous Cavalry" &&
          this.game.oppGame.combatZone.cards.length < 1
        ) {
          card.canAttack = false;
        }
        if (card.canAttack) {
          card.isAttacking = !card.isAttacking;
          if (card.isAttacking) {
            this.sendGameState();
            this.game.myGame.attack.attacker = card;
            // rep only check
            if (this.game.myGame.attack.attacker.name === "Informant" || this.game.myGame.attack.attacker.name === "x3m0, the Entwined") {
              this.game.oppRepAttack = true;
              this.game.myGame.attack.type = "rep";
              if (this.game.myGame.attack.attacker.name === "Informant") {
                this.game.myGame.attack.value = 1;
              } else {
                this.game.myGame.attack.value = card.attack
              }
              this.game.myGame.attack.attacker = {};
              this.game.oppGame.rep -= (1 + this.game.oppGame.repLossMod);
              card.isAttacking = false;
              this.sendGameState();
              card.canAttack = false;
              this.game.hasAttacked.push(card);
              this.resetAttackObject();
              setTimeout(() => {
                this.game.oppRepAttack = false;
              }, 500)
            }
          }
          if (!card.isAttacking) {
            this.game.myGame.attack.attacker = {};
          }
        }
        // turn off other attackers
        for (var n = 0; n < this.game.myGame.combatZone.cards.length; n++) {
          if (this.game.myGame.combatZone.cards[n].id != card.id) {
            this.game.myGame.combatZone.cards[n].isAttacking = false;
          }
        }
        if (card.canAttack && card.isAttacking && this.game.oppGame.combatZone.cards.length === 0) {
          setTimeout(() => {
            this.fortAttack();
          }, 500)

        } else {
          this.sendGameState();
        }
      }
    },

    fortAttack() {
      if (this.game.myGame.attack.attacker != {}) {
        this.game.oppFortAttack = true;
        this.game.myGame.attack.value = this.game.myGame.attack.attacker.attack;
        this.onAttackTriggerCheck();
        this.checkForDamageMods({}, true);

        // non-modded attack
        if (!this.game.myGame.attack.isModded) {
          this.game.oppGame.fort -= this.game.myGame.attack.value;
          this.game.myGame.attack.type = "fort";
          this.setLastEventAndRotate(
            this.game.myGame.username +
            " attacked " +
            this.game.oppGame.username +
            "'s fortress for " +
            this.game.myGame.attack.value +
            " damage"
          );
          this.sendGameState();
        } else {
          // modded attack
          this.sendGameState();
        }
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          if (this.game.myGame.combatZone.cards[i].isAttacking) {
            this.game.hasAttacked.push(this.game.myGame.combatZone.cards[i]);
            this.game.myGame.combatZone.cards[i].isAttacking = false;
            this.game.myGame.combatZone.cards[i].canAttack = false;
          }
        }
        setTimeout(() => {
          this.fortAttackReset();
        }, 500);
      }
    },

    fortAttackReset() {
      this.game.oppFortAttack = false;
      this.game.myGame.lastEvent = "";
      this.game.myGame.directive = {};
      this.resetAttackObject();
      this.sendGameState();
    },

    troopAttack(card) {
      card.isTarget = !card.isTarget;
      if (card.isTarget) {
        this.game.myGame.attack.value = this.game.myGame.attack.attacker.attack;
        this.game.myGame.attack.target = card;
        this.onAttackTriggerCheck();
        this.checkForDamageMods(card, false);
        this.sendGameState();

        // deal damage / rotate event / resets
        if (this.game.myGame.attack.isModded) {
          this.game.myGame.attack.attacker.resilience -= this.game.myGame.attack.modValue;
          this.game.myGame.attack.isModded = false;
        } else {
          this.game.myGame.attack.attacker.resilience -= card.attack;
        }
        card.resilience -= this.game.myGame.attack.value;
        this.setLastEventAndRotate(
          this.game.myGame.username +
          " attacks " +
          card.name +
          " with " +
          this.game.myGame.attack.attacker.name
        );
        this.game.myGame.directive = {};
        this.resetAttackObject();
        this.sendGameState();
        for (var j = 0; j < this.game.myGame.combatZone.cards.length; j++) {
          if (this.game.myGame.combatZone.cards[j].isAttacking) {
            this.game.hasAttacked.push(this.game.myGame.combatZone.cards[j]);
            this.game.myGame.combatZone.cards[j].isAttacking = false;
            this.game.myGame.combatZone.cards[j].canAttack = false;
          }
        }

        // switch off attacker / log attacker / check deaths
        setTimeout(() => {
          card.isTarget = !card.isTarget;
          this.troopAttackReset();
        }, 500);
      }
    },

    troopAttackReset() {
      this.game.myGame.lastEvent = "";
      // check deaths
      this.checkDeath();
      this.checkOppDeath();
      this.sendGameState();
    },

    checkForDamageMods(card, isFortAttack) {
      if (this.game.myGame.combatZone.mods.length > 0) {
        for (var i = 0; i < this.game.myGame.combatZone.mods.length; i++) {
          try {
            this[this.game.myGame.combatZone.mods[i] + "MyDamage"](
              card,
              isFortAttack
            );
          } catch (error) {
            console.log(error);
          }
        }
      }
      if (this.game.oppGame.combatZone.mods.length > 0) {
        for (var p = 0; p < this.game.oppGame.combatZone.mods.length; p++) {
          try {
            this[this.game.oppGame.combatZone.mods[p] + "OppDamage"](
              card,
              isFortAttack
            );
          } catch (error) {
            console.log(error);
          }
        }
      }
    },

    onAttackTriggerCheck() {
      if (this.game.myGame.attack.attacker != {}) {
        if (this.game.myGame.attack.attacker.onAttack) {
          console.log(this.game.myGame.attack.attacker.name);
          try {
            this[this.camelize(this.game.myGame.attack.attacker.name)](
              this.game.myGame.attack.attacker
            );
          } catch (error) {
            console.warn(error);
          }
        }
      }
    },

    resolveDamage() {
      var index = 666;
      for (var w = 0; w < this.game.myGame.combatZone.cards.length; w++) {
        if (
          this.game.oppGame.attack.target.id ===
          this.game.myGame.combatZone.cards[w].id
        ) {
          index = w;
          this.game.myGame.combatZone.cards[
            w
          ].isTarget = true;
          this.game.myGame.combatZone.cards[
            w
          ].resilience -= this.game.oppGame.attack.value;
          try {
            this[
              this.camelize(this.game.myGame.combatZone.cards[w].name) +
              "AttackedByOpp"
            ]();
          } catch (error) {
            console.log(error);
          }
          for (var m = 0; m < this.game.oppGame.combatZone.cards.length; m++) {
            if (
              this.game.oppGame.combatZone.cards[m].id ===
              this.game.oppGame.attack.attacker.id
            ) {
              this.game.oppGame.combatZone.cards[m].isAttacking = false;
            }
          }
        }
      }
      if (index < 666) {
        this.game.myGame.combatZone.cards[index].isTarget = false;
        this.checkDeath(index);
      }
    },

    resolveFortressAttack() {
      this.game.myFortAttack = true;
      this.game.myGame.fort -= this.game.oppGame.attack.value;
      for (var m = 0; m < this.game.oppGame.combatZone.cards.length; m++) {
        if (
          this.game.oppGame.combatZone.cards[m].id ===
          this.game.oppGame.attack.attacker.id
        ) {
          this.game.oppGame.combatZone.cards[m].isAttacking = false;
        }
      }
      setTimeout(() => {
        if (this.game.myGame.fort <= 0) {
          this.showDefeat();
          this.sendGameState();
        }
        this.game.myFortAttack = false;
      }, 500);
    },

    resolveRepAttack() {
      this.game.myRepAttack = true;
      this.game.myGame.rep -=
        this.game.oppGame.attack.value + this.game.myGame.repLossMod;
      setTimeout(() => {
        if (this.game.myGame.rep <= 0) {
          this.showDefeat();
        }
        this.game.myRepAttack = false;
      }, 500);
    },

    resolveContractAttack() {
      for (var p = 0; p < this.game.myGame.combatZone.cards.length; p++) {
        if (
          this.game.oppGame.attack.target.id ==
          this.game.myGame.combatZone.cards[p].id
        ) {
          this.game.myGame.combatZone.cards[
            p
          ].resilience -= this.game.oppGame.attack.value;
        }
        this.checkDeath(p);
      }
    },

    checkIncomingAttack() {
      if (this.game.oppGame.attack.type != null) {
        if (this.game.oppGame.attack.type === "fort") {
          this.resolveFortressAttack();
        }
        if (this.game.oppGame.attack.type === "rep") {
          this.resolveRepAttack();
        }
      }
      if (this.game.oppGame.attack.target != null) {
        if (this.game.oppGame.attack.type === "contract") {
          this.resolveContractAttack();
        }
        if (
          this.game.oppGame.attack.attacker != null &&
          this.game.oppGame.attack.type != "contract"
        ) {
          this.resolveDamage();
        }
      }
    },

    checkDeath() {
      for (var g = this.game.myGame.combatZone.cards.length - 1; g >= 0; g--) {
        if (this.game.myGame.combatZone.cards[g].resilience <= 0) {
          this.game.deadZone.push(this.game.myGame.combatZone.cards[g]);
          this.checkForCombatZoneMods(
            this.game.myGame.combatZone.cards[g],
            "leave"
          );
          this.game.myGame.combatZone.cards.splice(g, 1);
          g--;
        }
      }
    },

    checkOppDeath() {
      for (var g = this.game.oppGame.combatZone.cards.length - 1; g >= 0; g--) {
        if (this.game.oppGame.combatZone.cards[g].resilience <= 0) {
          this.checkForCombatZoneMods(
            this.game.oppGame.combatZone.cards[g],
            "opp"
          );
          this.game.oppGame.combatZone.cards.splice(g, 1);
        }
      }
    },

    kill() {
      for (let z = 0; z < this.game.myGame.combatZone.cards.length; z++) {
        if (
          this.game.myGame.combatZone.cards[z].id === this.game.oppGame.kill
        ) {
          this.game.deadZone.push(this.game.myGame.combatZone.cards[z]);
          this.checkForCombatZoneMods(
            this.game.myGame.combatZone.cards[z],
            "leave"
          );
          this.game.myGame.combatZone.cards.splice(z, 1);
        }
      }
    },

    killOppCard(card) {
      for (var z = this.game.oppGame.combatZone.cards.length - 1; z >= 0; z--) {
        if (this.game.oppGame.combatZone.cards[z].id === card.id) {
          this.checkForCombatZoneMods(
            this.game.oppGame.combatZone.cards[z],
            "opp"
          );
          this.game.oppGame.combatZone.cards.splice(z, 1);
        }
      }
    },

    resolveChosenOppTarget(card) {
      if (this.game.choosingOppTargets) {
        card.isTarget = true;
        this[this.game.myGame.targetInitiator + "Me"](card);
      }
    },

    resolveChosenFriendlyTarget(card) {
      if (this.game.choosingMyTargets) {
        this[this.game.myGame.targetInitiator + "Me"](card);
      }
    },

    // MODAL  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    show() {
      this.$modal.show("victory-modal");
    },

    hide() {
      this.$modal.hide("victory-modal");
    },

    showOptions() {
      this.$modal.show("choose-one-modal");
    },

    option1() {
      this.$modal.hide("choose-one-modal");
      try {
        this[this.game.myGame.options[0].source + "Option1"]();
      } catch (error) {
        console.warn(error);
      }
    },

    option2() {
      this.$modal.hide("choose-one-modal");
      try {
        this[this.game.myGame.options[0].source + "Option2"]();
      } catch (error) {
        console.warn(error);
      }
    },

    option3() {
      this.$modal.hide("choose-one-modal");
      try {
        this[this.game.myGame.options[0].source + "Option3"]();
      } catch (error) {
        console.warn(error);
      }
    },

    showDefeat() {
      this.game.myGame.concede = true;
      this.sendGameState();
      this.$modal.show("defeat-modal");
    },

    runAway() {
      this.game.myGame.concede = true;
      this.sendGameState();
      this.exitGame();
    },

    exitGame() {
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.commit("SET_OPPGAME_RFB", false);
      this.$store.state.gameSessionSubscription.unsubscribe();
      this.$store.commit("RESET_GAME_OBJECT");
      this.$store.commit("SET_GAME_DECK", {});
      this.$router.push({ name: "home" });
    },

    // UTILITIES  ./././././././././././././././././././././././././././././././././././././././././././././././././././././././././././././././././././././.

    camelize(str) {
      let string = str.replace(/[^a-zA-Z ]/g, "");
      return string.replace(/(?:^\w|[A-Z]|\b\w)/g, function (word, index) {
        return index === 0 ? word.toLowerCase() : word.toUpperCase();
      }).replace(/\s+/g, "");
    },

    getRandom() {
      let index = this.getRandomIntInclusive(
        8,
        54
      );
      return index;
    },

    determineContextBehaviorFriendly(card) {
      if (this.game.myGame.turn.isTurn) {
        if (this.game.choosingMyTargets) {
          this.resolveChosenFriendlyTarget(card);
        } else {
          this.toggleAttacking(card);
        }
      }
    },

    determineContextBehaviorOpp(card) {
      if (this.game.myGame.turn.isTurn) {
        if (this.game.choosingOppTargets) {
          this.resolveChosenOppTarget(card);
        }
        if (!this.game.choosingOppTargets && this.game.myGame.attack.attacker.name) {
          this.troopAttack(card);
        }
      }
    },

    leaving() {
      this.game.myGame.concede = true;
      this.sendGameState();
      this.$store.state.gameSessionSubscription.unsubscribe();
      this.$store.commit("RESET_GAME_OBJECT");
      this.$store.commit("SET_GAME_DECK", {});
      window.localStorage.clear();
    },

    resolveInitiator() {
      if (this.game.oppGame.targetInitiator != "") {
        let methodToCall = this.game.oppGame.targetInitiator + "Opp";
        try {
          this[methodToCall]();
        } catch (error) {
          console.warn(error);
        }
      }
    },

    rotato() {
      this.$refs.gameHub.rotate(this.game.oppGame.lastEvent);
    },

    setLastEventAndRotate(message) {
      this.$refs.gameHub.rotate(message);
      this.game.myGame.lastEvent = message;
    },

    toggleTargetingModeFriendly() {
      this.game.choosingMyTargets = !this.game.choosingMyTargets;
    },

    toggleTargetingModeOpp() {
      this.game.choosingOppTargets = !this.game.choosingOppTargets;
    },

    // CHECK METHODS  ?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?

    checkForInformant() {
      if (this.game.myGame.combatZone.cards.length > 0) {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          if (this.game.myGame.combatZone.cards[i].name === "Informant") {
            this.game.myGame.combatZone.cards[i].attack = 1;
          }
        }
      }
    },

    onTurnStartCheck() {
      let modsToTrigger = [];
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        modsToTrigger.push(
          this.camelize(this.game.myGame.contracts.active[i].name)
        );
      }
      for (var f = 0; f < this.game.myGame.combatZone.cards.length; f++) {
        if (this.game.myGame.combatZone.cards[f].onTurnStart) {
          modsToTrigger.push(
            this.camelize(this.game.myGame.combatZone.cards[f].name) + "TurnStart"
          );
        }
      }
      if (modsToTrigger.length > 0) {
        for (var m = 0; m < modsToTrigger.length; m++) {
          try {
            this[modsToTrigger[m]]();
          } catch (error) {
            console.warn(error);
          }
        }
        for (var p = 0; p < modsToTrigger.length; p++) {
          try {
            this[modsToTrigger[p] + "Target"]();
          } catch (error) {
            console.warn(error);
          }
        }
      }
    },

    checkForRepGainMods(value) {
      let modValue = 0;
      for (var i = 0; i < this.game.myGame.combatZone.mods.length; i++) {
        modValue = this[this.game.myGame.combatZone.mods[i] + "RepGain"]();
        if (modValue > 0) {
          value += modValue;
        }
      }
      return value;
    },

    checkForRepLossMods(value) {
      let modValue = 0;
      for (var i = 0; i < this.game.myGame.combatZone.mods.length; i++) {
        modValue = this[this.game.myGame.combatZone.mods[i] + "RepLoss"]();
        if (modValue > 0) {
          value += modValue;
        }
      }
      return value;
    },

    checkForCombatZoneMods(card, action) {
      // OPP CARD
      if (action === "opp") {
        if (this.game.oppGame.combatZone.mods.length > 0) {
          for (var i = 0; i < this.game.oppGame.combatZone.mods.length; i++) {
            if (
              this.game.oppGame.combatZone.mods[i] ===
              this.camelize(card.name) + "Mod"
            ) {
              let newAction = action + this.camelize(card.name);
              this[this.game.oppGame.combatZone.mods[i]](card, newAction);
            } else {
              this[this.game.oppGame.combatZone.mods[i]](card, "opp");
            }
          }
        }
      } else {
        // NOT OPP CARD
        if (this.game.myGame.combatZone.mods.length > 0) {
          for (var x = 0; x < this.game.myGame.combatZone.mods.length; x++) {
            if (
              this.game.myGame.combatZone.mods[x] ===
              this.camelize(card.name) + "Mod"
            ) {
              let newAction = action + this.camelize(card.name);
              this[this.game.myGame.combatZone.mods[x]](card, newAction);
            } else {
              this[this.game.myGame.combatZone.mods[x]](card, action);
            }
          }
        }
      }
    },

    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // CARD METHODS  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    //  ALLY TARGETERS

    thessaTheHelpingHandTarget() {
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.myGame.combatZone.cards[0].attack += 4;
        this.sendGameState();
      } else {
        this.game.myGame.targetInitiator = "thessaTheHelpingHandResolver";
        this.game.choosingMyTargets = true;
      }
    },

    thessaTheHelpingHandResolverMe(card) {
      card.attack += 4;
      this.game.choosingMyTargets = false;
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
    },

    fieldSurgeonTarget() {
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.myGame.combatZone.cards[0].resilience += 3;
        this.sendGameState();
      } else {
        this.game.myGame.targetInitiator = "fieldSurgeonResolver";
        this.game.choosingMyTargets = true;
      }
    },

    fieldSurgeonResolverMe(card) {
      card.resilience += 3;
      this.game.choosingMyTargets = false;
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
    },

    hypeMan(card) {
      if (this.game.myGame.combatZone.cards.length === 1) {
        card.canAttack = true;
      } else {
        this.game.myGame.targetInitiator = "hypeManResolver";
        this.game.choosingMyTargets = true;
      }
    },

    hypeManResolverMe(card) {
      for (var y = 0; y < this.game.hasAttacked.length; y++) {
        if (this.game.hasAttacked[y].id === card.id) {
          this.game.hasAttacked.splice(y, 1);
        }
      }
      card.canAttack = true;
      this.game.choosingMyTargets = false;
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
    },

    provisionerBotTarget() {
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.myGame.combatZone.cards[0].attack += 2;
        this.game.myGame.combatZone.cards[0].resilience += 2;
        this.sendGameState();
      } else {
        this.game.myGame.targetInitiator = "provisionerBotResolver";
        this.game.choosingMyTargets = true;
      }
    },

    provisionerBotResolverMe(card) {
      card.attack += 2;
      card.resilience += 2;
      this.game.choosingMyTargets = false;
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
    },

    reinforcements() {
      this.game.myGame.targetInitiator = "reinforcementsResolver";
      this.game.choosingMyTargets = true;
    },

    reinforcementsResolverMe(card) {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].id === card.id) {
          this.checkForCombatZoneMods(
            this.game.myGame.combatZone.cards[i],
            "leave"
          );
          card.canAttack = true;
          this.game.myGame.combatZone.cards.splice(i, 1);
          for (var y = 0; y < this.game.hasAttacked.length; y++) {
            if (this.game.hasAttacked[y].id === card.id) {
              this.game.hasAttacked.splice(y, 1);
            }
          }
          this.game.hand.push(card);
          this.game.choosingMyTargets = false;
          this.game.myGame.targetInitiator = "";
          this.sendGameState();
          break;
        }
      }
    },

    squireTarget() {
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.myGame.combatZone.cards[0].attack += 2;
        this.sendGameState();
      } else {
        this.game.myGame.targetInitiator = "squireResolver";
        this.game.choosingMyTargets = true;
      }
    },

    squireResolverMe(card) {
      card.attack += 2;
      this.game.choosingMyTargets = false;
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
    },

    //  OPP TARGETERS

    assimilatorHybridTarget(card) {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.attack.attacker = card;
        this.game.myGame.targetInitiator = "assimilatorHybridResolver";
        this.game.choosingOppTargets = true;
      }
    },

    assimilatorHybridResolverMe(card) {
      this.game.choosingOppTargets = false;
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.myGame.combatZone.cards[i].id === this.game.myGame.attack.attacker.id) {
          this.game.myGame.combatZone.cards[i].name = card.name;
          this.game.myGame.combatZone.cards[i].cost = card.cost;
          this.game.myGame.combatZone.cards[i].image_name = card.image_name;
          this.game.myGame.combatZone.cards[i].abilities = card.abilities;
          this.game.myGame.combatZone.cards[i].attack = card.attack;
          this.game.myGame.combatZone.cards[i].resilience = card.resilience;
          this.game.myGame.combatZone.cards[i].loyalty = card.loyalty;
          this.game.myGame.combatZone.cards[i].on_enter = card.on_enter;
          this.game.myGame.combatZone.cards[i].on_leave = card.on_leave;
          this.game.myGame.combatZone.cards[i].on_attack = card.on_attack;
          this.game.myGame.combatZone.cards[i].on_damage = card.on_damage;
          this.game.myGame.combatZone.cards[i].on_turn_start = card.on_turn_start;
          this.game.myGame.combatZone.cards[i].on_turn_end = card.on_turn_end;
          this.game.myGame.combatZone.cards[i].is_attacking = card.is_attacking;
          this.game.myGame.combatZone.cards[i].is_target = card.is_target;
          this.game.myGame.combatZone.cards[i].is_hallowed = card.is_hallowed;
          this.game.myGame.combatZone.cards[i].is_fabled = card.is_fabled;
          this.game.myGame.combatZone.cards[i].is_heroic = card.is_heroic;
          this.game.myGame.combatZone.cards[i].is_dreaded = card.is_dreaded;
          this.game.myGame.combatZone.cards[i].is_feared = card.is_feared;
          this.game.myGame.combatZone.cards[i].is_vile = card.is_vile;
          break;
        }
        this.resetAttackObject();
        this.sendGameState();
      }

    },

    singleTargetSpecialistTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "singleTargetSpecialistResolver";
        this.game.choosingOppTargets = true;
      }
    },

    singleTargetSpecialistResolverMe(card) {
      this.game.choosingOppTargets = false;
      this.game.myGame.kill = card.id;
      this.killOppCard(card);
      this.sendGameState();
      this.game.myGame.kill = 0;
      this.game.myGame.targetInitiator = "";
    },

    singleTargetSpecialistResolverOpp() {
      this.kill();
    },

    enforcerTarget(card) {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "enforcerResolver";
        this.game.myGame.attack.attacker = card;
        this.game.choosingOppTargets = true;
      }
    },

    enforcerResolverMe(card) {
      this.game.choosingOppTargets = false;
      this.troopAttack(card);
      this.game.myGame.targetInitiator = "";
    },

    fireArcherTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "fireArcherResolver";
        this.game.choosingOppTargets = true;
      }
    },

    fireArcherResolverMe(card) {
      this.game.choosingOppTargets = false;
      card.resilience -= 2;
      this.game.myGame.attack.target = card;
      this.game.myGame.attack.attacker.attack = 2;
      this.game.myGame.directive.name = "fireArcher";
      this.game.myGame.targetInitiator = "";
      this.checkForDamageMods(card, false);
      for (var i = this.game.oppGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.oppGame.combatZone.cards[i].id === card.id) {
          this.checkOppDeath(i);
        }
      }
      this.sendGameState();
      this.resetAttackObject();
      this.game.myGame.directive = {};
    },

    fireArcherDirective() {
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.myGame.combatZone.cards[i].id === this.game.oppGame.attack.target.id) {
          this.game.myGame.combatZone.cards[i].isTarget = false;
          this.game.myGame.combatZone.cards[i].resilience -= 2;
          this.checkForDamageMods(this.game.myGame.combatZone.cards[i], false);
          this.checkDeath(i);
        }
      }
    },

    deltaTrooperTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        for (var i = 0; i < this.game.oppGame.combatZone.cards.length; i++) {
          if (this.game.oppGame.combatZone.cards[i].resilience <= 3) {
            this.game.myGame.targetInitiator = "deltaTrooperResolver";
            this.game.choosingOppTargets = true;
            break;
          }
        }
      }
    },

    deltaTrooperResolverMe(card) {
      if (card.resilience <= 3) {
        this.game.choosingOppTargets = false;
        this.game.myGame.kill = card.id;
        this.killOppCard(card);
        this.sendGameState();
        this.game.myGame.kill = 0;
        this.game.myGame.targetInitiator = "";
      }
    },

    deltaTrooperResolverOpp() {
      this.kill();
    },

    nikitaOfTheOldWaysTarget(card) {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "nikitaOfTheOldWaysResolver";
        this.game.myGame.attack.attacker.attack = card.attack;
        this.game.choosingOppTargets = true;
      }
    },

    nikitaOfTheOldWaysResolverMe(card) {
      this.game.choosingOppTargets = false;
      card.resilience -= this.game.myGame.attack.attacker.attack;
      this.game.myGame.attack.target = card;
      this.game.myGame.directive.name = "nikitaOfTheOldWays";
      this.game.myGame.targetInitiator = "";
      this.checkForDamageMods(card, false);
      for (var i = this.game.oppGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.oppGame.combatZone.cards[i].id === card.id) {
          this.checkOppDeath(i);
        }
      }
      this.sendGameState();
      this.resetAttackObject();
      this.game.myGame.directive = {};
    },

    nikitaOfTheOldWaysDirective() {
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.myGame.combatZone.cards[i].id === this.game.oppGame.attack.target.id) {
          this.game.myGame.combatZone.cards[i].isTarget = false;
          this.game.myGame.combatZone.cards[i].resilience -= this.game.oppGame.attack.attacker.attack;
          this.checkForDamageMods(this.game.myGame.combatZone.cards[i], false);
          this.checkDeath(i);
        }
      }
    },

    longRangeAssassinTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        for (var i = 0; i < this.game.oppGame.combatZone.cards.length; i++) {
          if (this.game.oppGame.combatZone.cards[i].attack >= 6) {
            this.game.myGame.targetInitiator = "longRangeAssassinResolver";
            this.game.choosingOppTargets = true;
            break;
          }
        }
      }
    },

    longRangeAssassinResolverMe(card) {
      if (card.attack >= 6) {
        this.game.choosingOppTargets = false;
        this.game.myGame.kill = card.id;
        this.killOppCard(card);
        this.sendGameState();
        this.game.myGame.kill = 0;
        this.game.myGame.targetInitiator = "";
      }
    },

    longRangeAssassinResolverOpp() {
      this.kill();
    },

    bruteBotTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.choosingOppTargets = true;
        this.game.myGame.targetInitiator = "bruteBotResolver";
      }
    },

    bruteBotResolverMe(card) {
      card.isTarget = false;
      this.game.choosingOppTargets = false;
      card.attack -= 1;
      card.resilience -= 1;
      card.loyalty -= 1;
      for (var i = this.game.oppGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.oppGame.combatZone.cards[i].id === card.id) {
          this.checkOppDeath(i);
        }
      }
      this.game.myGame.directive = card;
      this.game.myGame.directive.name = "bruteBot";
      this.game.myGame.targetInitiator = "";
      this.sendGameState();
      this.game.myGame.directive = {};
    },

    bruteBotDirective(target) {
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        if (this.game.myGame.combatZone.cards[i].id === target.id) {
          this.game.myGame.combatZone.cards[i].isTarget = false;
          this.game.myGame.combatZone.cards[i].attack -= 1;
          this.game.myGame.combatZone.cards[i].resilience -= 1;
          this.game.myGame.combatZone.cards[i].loyalty -= 1;
          this.checkDeath(i);
        }
      }
    },

    readerOfDecreesTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        let eligible = false;
        for (var i = 0; i < this.game.oppGame.combatZone.cards.length; i++) {
          if (this.game.oppGame.combatZone.cards[i].attack <= 1) {
            eligible = true;
          }
        }
        if (eligible) {
          this.game.choosingOppTargets = true;
          this.game.myGame.targetInitiator = "readerOfDecreesResolver";
        }
      }
    },

    readerOfDecreesResolverMe(card) {
      this.game.myGame.targetInitiator = "";
      card.isTarget = false;
      this.game.choosingOppTargets = false;
      if (card.attack <= 1) {
        this.game.myGame.combatZone.cards.push(card);
        this.game.myGame.combatZone.mods.push(this.camelize(card.name) + "Mod");
        for (
          var i = this.game.myGame.combatZone.cards.length - 1;
          i >= 0;
          i--
        ) {
          if (
            this.game.myGame.combatZone.cards[i].name === "Reader of Decrees"
          ) {
            this.game.myGame.directive = this.game.myGame.combatZone.cards[i];
            this.game.myGame.combatZone.cards.splice(i, 1);
            break;
          }
        }
        this.game.myGame.attack.target = card;
        this.sendGameState();
        this.resetAttackObject();
        this.game.myGame.directive = {};
      }
    },

    readerOfDecreesDirective(card) {
      for (var w = this.game.myGame.combatZone.cards.length - 1; w >= 0; w--) {
        if (
          this.game.myGame.combatZone.cards[w].id ===
          this.game.oppGame.attack.target.id
        ) {
          let modToSplice =
            this.camelize(this.game.myGame.combatZone.cards[w].name) + "Mod";
          let index = this.game.myGame.combatZone.mods.indexOf(modToSplice);
          this.game.myGame.combatZone.mods.splice(index, 1);
          this.game.myGame.combatZone.cards.splice(w, 1);
        }
      }
      this.game.myGame.combatZone.cards.push(card);
      this.game.oppGame.targetInitiator = "";
      this.game.oppGame.directive = {};
      this.sendGameState();
    },

    vikenFearedAssassin() {
      this.game.myGame.attack.type = "rep";
      this.game.myGame.attack.value = 3;
      this.game.oppGame.rep -= (3 + this.game.oppGame.repLossMod);
    },

    vikenFearedAssassinTarget() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "vikenFearedAssassinResolver";
        this.game.choosingOppTargets = true;
      }
    },

    vikenFearedAssassinResolverMe(card) {
      this.game.choosingOppTargets = false;
      this.game.myGame.kill = card.id;
      this.killOppCard(card);
      this.sendGameState();
      this.game.myGame.kill = 0;
      this.game.myGame.targetInitiator = "";
    },

    vikenFearedAssassinResolverOpp() {
      this.kill();
    },

    //  ON ENTER (PRIMARILY)

    brothersAtArms() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newBrothersAtArms = {
          id: 365 + this.game.myGame.turn.count * this.getRandom(),
          name: "Brothers at Arms",
          cost: 10,
          imageName: "baa_bro.jpg",
          abilities: "",
          flavor: "",
          attack: 2,
          resilience: 2,
          loyalty: 2,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newBrothersAtArms);
        this.checkForCombatZoneMods(newBrothersAtArms, "enter");
      }
    },

    analyst() {
      this.drawCard();
      this.drawContract();
      this.game.myGame.cache += 5;
      this.game.myGame.fort += 5;
      this.game.myGame.rep += 1 + this.game.myGame.repGainMod;
    },

    strikeSquad() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newEnlistedCivilian = {
          id: 899 + this.game.myGame.turn.count * this.getRandom(),
          name: "Enlisted Civilian",
          cost: 5,
          imageName: "enlisted_civilian.jpg",
          abilities:
            "When this or another Enlisted Civilian enters, if there is another troop in the combat zone, this gains +1 Resilience.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 4,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newEnlistedCivilian);
        this.checkForCombatZoneMods(newEnlistedCivilian, "enter");
        this.enlistedCivilian();
      }
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newEnlistedCivilian = {
          id: 666 + this.game.myGame.turn.count + this.getRandom(),
          name: "Enlisted Civilian",
          cost: 5,
          imageName: "enlisted_civilian.jpg",
          abilities:
            "When this or another Enlisted Civilian enters, if there is another troop in the combat zone, this gains +1 Resilience.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 4,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newEnlistedCivilian);
        this.checkForCombatZoneMods(newEnlistedCivilian, "enter");
        this.enlistedCivilian();
      }
    },

    alphaThrall() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].name === 'Thrall') {
          this.game.myGame.combatZone.cards[i].attack += 1;
          this.game.myGame.combatZone.cards[i].resilience += 1;
          this.game.myGame.combatZone.cards[i].loyalty += 1;
        }
      }
    },

    thrall() {
      let thrallCount = 0;
      let summonLeader = true;
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].name === 'Thrall') {
          thrallCount += 1;
        }
      }
      for (var g = 0; g < this.game.myGame.combatZone.cards.length; g++) {
        if (this.game.myGame.combatZone.cards[g].name === 'Cult Leader') {
          summonLeader = false;
        }
      }
      if (thrallCount >= 4 && summonLeader) {
        if (this.game.myGame.combatZone.cards.length <= 9) {
          let newAlphaThrall = {
            id: 7799 + this.game.myGame.turn.count * this.getRandom(),
            name: "Cult Leader",
            cost: 5,
            imageName: "alpha_thrall.jpg",
            abilities:
              "When this enters, give all friendly Thralls +1 to all stats.",
            flavor: "",
            attack: 3,
            resilience: 3,
            loyalty: 6,
            isAttacking: false,
            isTarget: false,
            canAttack: true,
          };
          this.game.myGame.combatZone.cards.push(newAlphaThrall);
          this.checkForCombatZoneMods(newAlphaThrall, "enter");
          this.alphaThrall();
        }
      }
      this.sendGameState();
    },

    doubleAgent(card) {
      // kill opp thing
      if (this.game.oppGame.combatZone.cards.length === 1) {
        this.game.myGame.kill = this.game.oppGame.combatZone.cards[0].id;
        this.checkForCombatZoneMods(
          this.game.oppGame.combatZone.cards[0],
          "opp"
        );
        this.game.oppGame.combatZone.cards.shift();
      } else if (this.game.oppGame.combatZone.cards.length > 1) {
        let index = this.getRandomIntInclusive(
          0,
          this.game.oppGame.combatZone.cards.length - 1
        );
        this.game.myGame.kill = this.game.oppGame.combatZone.cards[index].id;
        this.checkForCombatZoneMods(
          this.game.oppGame.combatZone.cards[index],
          "opp"
        );
        this.game.oppGame.combatZone.cards.splice(index, 1);
      }
      // kill my thing
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.deadZone.push(this.game.myGame.combatZone.cards[0]);
        this.checkForCombatZoneMods(
          this.game.myGame.combatZone.cards[0],
          "leave"
        );
        this.game.myGame.combatZone.cards.shift();
      } else if (this.game.myGame.combatZone.cards.length > 1) {
        let index = this.getRandomIntInclusive(
          0,
          this.game.myGame.combatZone.cards.length - 1
        );
        this.game.deadZone.push(this.game.myGame.combatZone.cards[index]);
        this.checkForCombatZoneMods(
          this.game.myGame.combatZone.cards[index],
          "leave"
        );
        this.game.myGame.combatZone.cards.splice(index, 1);
      }
      // trigger my contract
      if (this.game.myGame.contracts.active.length > 0) {
        try {
          this[
            this.camelize(
              this.game.myGame.contracts.active[
                this.getRandomIntInclusive(
                  0,
                  this.game.myGame.contracts.active.length - 1
                )
              ].name
            )
          ]();
        } catch (error) {
          console.log(error);
        }
      }
      // directive and draw
      this.game.myGame.directive = card;
      this.drawCard();
    },

    doubleAgentDirective() {
      this.drawCard();
      if (this.game.myGame.contracts.active.length > 0) {
        var randomIndex = this.getRandomIntInclusive(0, (this.game.myGame.contracts.active.length - 1));
        this[this.camelize(this.game.myGame.contracts.active[randomIndex].name)]();
      }
      this.game.oppGame.attack.value = 0;
      setTimeout(() => {
        this.sendGameState();
      }, 500);
    },

    embeddedAssaultUnit() {
      let index = this.getRandomIntInclusive(
        0,
        this.game.contracts.deck.length - 1
      );
      this.game.myGame.contracts.active.push(this.game.contracts.deck[index]);
      this.game.contracts.deck.splice(index, 1);
    },

    enlistedCarpenter() {
      this.game.myGame.fort += 5;
    },

    erraticBombardment() {
      if (this.game.oppGame.combatZone.cards.length === 1) {
        this.game.myGame.kill = this.game.oppGame.combatZone.cards[0].id;
        this.game.oppGame.combatZone.cards.shift();
      } else if (this.game.oppGame.combatZone.cards.length > 1) {
        let index = this.getRandomIntInclusive(
          0,
          this.game.oppGame.combatZone.cards.length - 1
        );
        this.game.myGame.kill = this.game.oppGame.combatZone.cards[index].id;
        this.game.oppGame.combatZone.cards.splice(index, 1);
      }
      if (this.game.myGame.combatZone.cards.length === 1) {
        this.game.deadZone.push(this.game.myGame.combatZone.cards[0]);
        this.game.myGame.combatZone.cards.shift();
      } else if (this.game.myGame.combatZone.cards.length > 1) {
        let index = this.getRandomIntInclusive(
          0,
          this.game.myGame.combatZone.cards.length - 1
        );
        this.game.deadZone.push(this.game.myGame.combatZone.cards[index]);
        this.game.myGame.combatZone.cards.splice(index, 1);
      }
    },

    taanAllSeeingSplicer() {
      this.game.myGame.options = [
        {
          source: 'taanAllSeeingSplicer',
          isChosen: false,
          text: "Destroy an enemy troop"
        },
        {
          source: 'taanAllSeeingSplicer',
          isChosen: false,
          text: "Create 2 Thralls"
        },
        {
          source: 'taanAllSeeingSplicer',
          isChosen: false,
          text: "Reduce opposing Reputation by 3"
        }
      ]
      this.showOptions();
    },

    taanAllSeeingSplicerOption1() {
      if (this.game.oppGame.combatZone.cards.length > 0) {
        this.game.myGame.targetInitiator = "taanAllSeeingSplicerResolver";
        this.game.choosingOppTargets = true;
      }
    },

    taanAllSeeingSplicerOption2() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newThrall = {
          id: 1221 + this.game.myGame.turn.count * this.getRandom(),
          name: "Thrall",
          cost: 5,
          imageName: "thrall.jpg",
          abilities:
            "If you control 4 Thralls, create an Alpha Thrall.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 5,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newThrall);
        this.checkForCombatZoneMods(newThrall, "enter");
        this.thrall();
      }

      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newThrall2 = {
          id: 6558 + this.game.myGame.turn.count * this.getRandom(),
          name: "Thrall",
          cost: 5,
          imageName: "thrall.jpg",
          abilities:
            "If you control 4 Thralls, create an Alpha Thrall.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 5,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newThrall2);
        this.checkForCombatZoneMods(newThrall2, "enter");
        this.thrall();
      }
      this.sendGameState();
    },

    taanAllSeeingSplicerOption3() {
      this.game.myGame.attack.type = "rep";
      this.game.myGame.attack.value = 3;
      this.game.oppGame.rep -= (3 + this.game.oppGame.repLossMod);
      this.sendGameState();
      this.resetAttackObject();
    },

    taanAllSeeingSplicerResolverMe(card) {
      this.game.choosingOppTargets = false;
      this.game.myGame.kill = card.id;
      this.killOppCard(card);
      this.sendGameState();
      this.game.myGame.kill = 0;
      this.game.myGame.targetInitiator = "";
    },

    taanAllSeeingSplicerResolverOpp() {
      this.kill();
    },

    xuunDreadFleetCommander() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newFieldCommander = {
          id: 592 + this.game.myGame.turn.count + this.getRandom(),
          name: "Field Commander",
          cost: 15,
          imageName: "commander.jpg",
          abilities: "All troops in your combat zone have +1 Attack.",
          flavor: "",
          attack: 3,
          resilience: 4,
          loyalty: 6,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newFieldCommander);
        this.checkForCombatZoneMods(newFieldCommander, "enter");
        this.fieldCommander();
      }
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newEnlistedCivilian = {
          id: 812 + this.game.myGame.turn.count + this.getRandom(),
          name: "Enlisted Civilian",
          cost: 5,
          imageName: "enlisted_civilian.jpg",
          abilities:
            "When this or another Enlisted Civilian enters, if there is another troop in the combat zone, this gains +1 Resilience.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 4,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newEnlistedCivilian);
        this.checkForCombatZoneMods(newEnlistedCivilian, "enter");
        this.enlistedCivilian();
      }
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newBruteBot = {
          id: 421 + this.game.myGame.turn.count + this.getRandom(),
          name: "Brute Bot",
          cost: 15,
          imageName: "dread_leader.jpg",
          abilities: "When this enters, reduce an enemy troop's Attack, Resilience, and Loyalty by 1.",
          flavor: "",
          attack: 4,
          resilience: 4,
          loyalty: 2,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newBruteBot);
        this.checkForCombatZoneMods(newBruteBot, "enter");
        this.bruteBotTarget();
      }
      
    },

    influencer() {
      this.game.myGame.cache += 5 * this.game.myGame.contracts.active.length;
    },

    kingsCouncil() {
      this.game.myGame.rep +=
        this.game.myGame.combatZone.cards.length +
        this.game.myGame.contracts.active.length +
        this.game.myGame.repGainMod;
    },

    enlistedCivilian() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].name === "Enlisted Civilian") {
          if (this.game.myGame.combatZone.cards.length >= 2) {
            this.game.myGame.combatZone.cards[i].resilience += 1;
          } else {
            this.game.myGame.combatZone.cards[i].resilience = 2;
          }
        }
      }
    },

    lukasFabledArmorer() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].resilience += 2;
      }
      this.game.myGame.fort += 5;
    },

    ladyPriFabledRider() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let lucianTrustedSteed = {
          id: 249 + this.getRandom() * this.game.myGame.turn.count,
          name: "Lucian, Trusted Steed",
          cost: 5,
          imageName: "lucian.jpg",
          abilities: "If Lady Pri dies, so does Lucian.",
          flavor: "",
          attack: 1,
          resilience: 3,
          loyalty: 8,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(lucianTrustedSteed);
        this.checkForCombatZoneMods(lucianTrustedSteed, "enter");
      }
      this.game.myGame.combatZone.mods.push("ladyPriFabledRiderMod");
    },

    ladyPriFabledRiderMod(card, action) {
      if (action === "leave" && card.name === "Lucian, Trusted Steed") {
        if (this.game.oppGame.combatZone.cards.length === 1) {
          this.game.myGame.kill = this.game.oppGame.combatZone.cards[0].id;
          this.game.oppGame.combatZone.cards.shift();
        } else if (this.game.oppGame.combatZone.cards.length > 1) {
          let index = this.getRandomIntInclusive(
            0,
            this.game.oppGame.combatZone.cards.length - 1
          );
          this.game.myGame.kill = this.game.oppGame.combatZone.cards[index].id;
          this.game.oppGame.combatZone.cards.splice(index, 1);
        }
        this.sendGameState();
      }
      if (action === "leaveladyPriFabledRider") {
        for (
          var i = this.game.myGame.combatZone.cards.length - 1;
          i >= 0;
          i--
        ) {
          if (
            this.game.myGame.combatZone.cards[i].name ===
            "Lucian, Trusted Steed"
          ) {
            this.game.myGame.combatZone.cards.splice(i, 1);
            break;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("ladyPriFabledRiderMod"),
          1
        );
      }
      if (action === "oppladyPriFabledRider") {
        for (
          var g = this.game.oppGame.combatZone.cards.length - 1;
          g >= 0;
          g--
        ) {
          if (
            this.game.oppGame.combatZone.cards[g].name ===
            "Lucian, Trusted Steed"
          ) {
            this.game.oppGame.combatZone.cards.splice(g, 1);
            break;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("ladyPriFabledRiderMod"),
          1
        );
      }
    },

    xmTheEntwined() {
      this.game.myGame.rep += 2;
    },

    hacker() {
      this.game.myGame.attack.type = "rep";
      this.game.myGame.attack.value = 2;
      this.game.oppGame.rep -= (2 + this.game.oppGame.repLossMod);
    },

    scout() {
      this.drawCard();
    },

    mainframeAccessorBot() {
      this.drawCard();
      this.drawCard();
    },

    titusBattleEnder(card) {
      this.game.myGame.directive = card;
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].id != card.id) {
          this.game.deadZone.push(this.game.myGame.combatZone.cards[i]);
          this.checkForCombatZoneMods(
            this.game.myGame.combatZone.cards[i],
            "leave"
          );
          this.game.myGame.combatZone.cards.splice(i, 1);
          i--;
        }
      }
      for (var g = this.game.oppGame.combatZone.cards.length - 1; g >= 0; g--) {
        this.checkForCombatZoneMods(
          this.game.oppGame.combatZone.cards[g],
          "opp"
        );
        this.game.oppGame.combatZone.cards.splice(g, 1);
      }
    },

    titusBattleEnderDirective() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.deadZone.push(this.game.myGame.combatZone.cards[i]);
        this.checkForCombatZoneMods(
          this.game.myGame.combatZone.cards[i],
          "leave"
        );
        this.game.myGame.combatZone.cards.splice(i, 1);
        i--;
      }
    },

    dataExtractors() {
      this.game.myGame.options = [
        {
          source: 'dataExtractors',
          isChosen: false,
          text: "Create an Informant"
        },
        {
          source: 'dataExtractors',
          isChosen: false,
          text: "Gain 15 Coins"
        },
        {
          source: 'dataExtractors',
          isChosen: false,
          text: "Deal 1 damage to all enemy troops"
        }
      ]
      this.showOptions();
    },

    dataExtractorsOption1() {
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newInformant = {
          id: 365 + this.game.myGame.turn.count * this.getRandom(),
          name: "Informant",
          cost: 5,
          imageName: "spotted.jpg",
          abilities: "Readiness. This troop's Attack can't be greater than 1 and it can only attack Stability.",
          flavor: "",
          attack: 1,
          resilience: 3,
          loyalty: 1,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newInformant);
        this.checkForCombatZoneMods(newInformant, "enter");
      }
    },

    dataExtractorsOption2() {
      this.game.myGame.cache += 15;
      this.sendGameState();
    },

    dataExtractorsOption3() {
      this.game.myGame.directive.name = "Trusted Advisor";
      for (
          var i = this.game.oppGame.combatZone.cards.length - 1;
          i >= 0;
          i--
        ) {
          this.game.oppGame.combatZone.cards[i].resilience -= 1;
          this.checkOppDeath(i);
        }
        this.sendGameState();
        this.game.myGame.directive = {
          name: ""
        }
    },

    trustedAdvisorDirective() {
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        this.game.myGame.combatZone.cards[i].resilience -= 1;
        this.checkDeath(i);
      }
    },

    veeraHallowedDiplomat(card) {
      this.game.myGame.rep = 10;
      this.game.oppGame.rep = 10;
      this.game.myGame.directive = card;
    },

    veeraHallowedDiplomatDirective() {
      this.game.myGame.rep = 10;
    },

    // ATTACK RELATED

    bBBunkerBuster(card) {
      this.game.myGame.directive = card;
      this.game.oppGame.fort -= card.attack;
    },

    bBBunkerBusterDirective(card) {
      this.game.myGame.fort -= card.attack;
      this.game.oppGame.directive = {};
    },

    disguisedSaboteur(card) {
      this.game.myGame.directive = card;
      this.game.oppGame.rep -= (2 + this.game.oppGame.repLossMod);
    },

    disguisedSaboteurDirective() {
      this.game.myGame.rep -= 2 + this.game.myGame.repLossMod;
    },

    vanceInfiltratorSupreme(card) {
      if (!card.isAttacking) {
        this.game.myGame.attack.type = "rep";
        this.game.myGame.attack.value = 2;
      }
      if (card.isAttacking) {
        this.game.myGame.directive = card;
      }
      this.game.oppGame.rep -= (2 + this.game.oppGame.repLossMod);
    },

    vanceInfiltratorSupremeDirective() {
      this.game.myGame.rep -= 2 + this.game.myGame.repLossMod;
    },

    axa99NetworkDefense(card) {
      if (!card.isAttacking) {
        this.game.myGame.combatZone.mods.push("axa99NetworkDefenseMod");
      }
      this.game.myGame.rep += 1 + this.game.myGame.repGainMod;
    },

    axa99NetworkDefenseMod(card, action) {
      if (action === "leaveaxa99NetworkDefense") {
        this.game.myGame.rep += 1 + this.game.myGame.repGainMod;
        this.game.oppGame.rep -= (1 + this.game.oppGame.repLossMod);
        this.game.myGame.attack.type = "rep";
        this.game.myGame.attack.value = 1;
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("axa99NetworkDefenseMod"),
          1
        );
        this.sendGameState();
        this.resetAttackObject();
      }
      if (action === "oppaxa99NetworkDefense") {
        this.game.oppGame.rep += 1 + this.game.oppGame.repGainMod;
        this.game.myGame.rep -= 1 + this.game.myGame.repLossMod;
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("axa99NetworkDefenseMod"),
          1
        );
      }
    },

    frenziedChargers(card) {
      if (card.isAttacking) {
        this.game.myGame.directive = card;
        this.game.oppGame.fort -= this.game.myGame.combatZone.cards.length;
        this.game.myGame.directive.loyalty = this.game.myGame.combatZone.cards.length;
      }
    },

    frenziedChargersDirective() {
      this.game.myGame.fort -= this.game.oppGame.directive.loyalty;
    },

    wrex(card) {
      if (!card.isAttacking) {
        this.game.myGame.combatZone.mods.push("wrexMod");
      }
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].attack += 1;
        this.game.myGame.combatZone.cards[i].resilience += 1;
        this.game.myGame.combatZone.cards[i].loyalty += 1;
      }
    },

    wrexMod(card, action) {
      if (action === "leavewrex") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          this.game.myGame.combatZone.cards[i].attack += 1;
          this.game.myGame.combatZone.cards[i].resilience += 1;
          this.game.myGame.combatZone.cards[i].loyalty += 1;
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("wrexMod"),
          1
        );
      }
      if (action === "oppwrex") {
        for (var y = 0; y < this.game.oppGame.combatZone.cards.length; y++) {
          this.game.oppGame.combatZone.cards[y].attack += 1;
          this.game.oppGame.combatZone.cards[y].resilience += 1;
          this.game.oppGame.combatZone.cards[y].loyalty += 1;
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("wrexMod"),
          1
        );
      }
    },

    merekRenegadeIcon(card) {
      if (!card.isAttacking) {
        this.game.myGame.combatZone.mods.push("merekRenegadeIconMod");
      }
      this.game.myGame.cache += 5;
    },

    merekRenegadeIconMod(card, action) {
      if (action === "leavemerekRenegadeIcon") {
        this.game.myGame.cache += 5;
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("merekRenegadeIconMod"),
          1
        );
      }
      if (action === "oppmerekRenegadeIcon") {
        this.game.oppGame.cache += 5;
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("merekRenegadeIconMod"),
          1
        );
      }
    },

    hVTETheCrowdPleaser(card) {
      if (card.name === "HVTE, the Crowd Pleaser") {
        this.game.myGame.directive = card;
        for (
          var i = this.game.oppGame.combatZone.cards.length - 1;
          i >= 0;
          i--
        ) {
          this.game.oppGame.combatZone.cards[i].resilience -= 1;
          this.checkForDamageMods(this.game.oppGame.combatZone.cards[i], false);
          this.checkOppDeath(i);
        }
      }
    },

    hVTETheCrowdPleaserDirective() {
      for (var i = this.game.myGame.combatZone.cards.length - 1; i >= 0; i--) {
        this.game.myGame.combatZone.cards[i].resilience -= 1;
        this.checkForDamageMods(this.game.myGame.combatZone.cards[i], false);
        this.checkDeath(i);
      }
    },

    //  DAMAGE RELATED

    antiPersonnelSpecialist() {
      this.game.myGame.combatZone.mods.push("antiPersonnelSpecialistMod");
    },

    antiPersonnelSpecialistMod(card, action) {
      if (action === "leaveantiPersonnelSpecialist") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf(
            "antiPersonnelSpecialistMod"
          ),
          1
        );
      }
    },

    antiPersonnelSpecialistModMyDamage(target, isFortAttack) {
      if (isFortAttack) {
        if (
          this.game.myGame.attack.attacker.name === "Anti-Personnel Specialist"
        ) {
          this.game.myGame.attack.value = 0;
        }
      }
    },

    batteringRam() {
      this.game.myGame.combatZone.mods.push("batteringRamMod");
    },

    batteringRamModMyDamage(target, isFortAttack) {
      if (
        !isFortAttack &&
        this.game.myGame.attack.attacker.name === "Battering Ram"
      ) {
        this.game.myGame.attack.value = 0;
      }
    },

    batteringRamModOppDamage(target, isFortAttack) {
      if (target.name === "Battering Ram" && !isFortAttack) {
        this.game.myGame.attack.isModded = true;
        this.game.myGame.attack.modValue = 0;
      }
    },

    batteringRamMod(card, action) {
      if (action === "leavebatteringRam") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("batteringRamMod"),
          1
        );
      }
    },

    breakspearHallowedDefender() {
      this.game.myGame.combatZone.mods.push("breakspearHallowedDefenderMod");
    },

    breakspearHallowedDefenderModOppDamage(target, isFortAttack) {
      if (isFortAttack) {
        let index = this.game.oppGame.combatZone.cards.indexOf(
          this.game.oppGame.combatZone.cards.find(
            (card) => card.name === "Breakspear, Hallowed Defender"
          )
        );
        this.game.oppGame.combatZone.cards[index].isTarget = true;
        this.game.myGame.attack.target = this.game.oppGame.combatZone.cards[
          index
        ];
        this.game.myGame.attack.type = "contract";
        this.game.myGame.attack.isModded = true;
        this.game.oppGame.combatZone.cards[
          index
        ].resilience -= this.game.myGame.attack.value;
        // rotate game event
        this.setLastEventAndRotate(
          this.game.myGame.username +
          " attacked " +
          this.game.oppGame.username +
          "'s fortress for " +
          this.game.myGame.attack.value +
          " damage. Breakspear takes the blow instead!"
        );

        // revert isTarget
        for (var t = 0; t < this.game.oppGame.combatZone.cards.length; t++) {
          if (
            this.game.oppGame.combatZone.cards[t].name ===
            "Breakspear, Hallowed Defender"
          ) {
            this.game.oppGame.combatZone.cards[t].isTarget = false;
          }
        }
      }
    },

    breakspearHallowedDefenderMod(card, action) {
      if (action === "leavebreakspearHallowedDefender") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf(
            "breakspearHallowedDefenderMod"
          ),
          1
        );
      }
    },

    motherOfThralls() {
      this.game.myGame.combatZone.mods.push("motherOfThrallsMod");

      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newThrall = {
          id: 899 + this.game.myGame.turn.count * this.getRandom(),
          name: "Thrall",
          cost: 5,
          imageName: "thrall.jpg",
          abilities:
            "If you control 4 Thralls, create an Alpha Thrall.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 5,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newThrall);
        this.checkForCombatZoneMods(newThrall, "enter");
        this.thrall();
      }

      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newThrall = {
          id: 899 + this.game.myGame.turn.count * this.getRandom(),
          name: "Thrall",
          cost: 5,
          imageName: "thrall.jpg",
          abilities:
            "If you control 4 Thralls, create an Alpha Thrall.",
          flavor: "",
          attack: 1,
          resilience: 2,
          loyalty: 5,
          isAttacking: false,
          isTarget: false,
          canAttack: true,
        };
        this.game.myGame.combatZone.cards.push(newThrall);
        this.checkForCombatZoneMods(newThrall, "enter");
        this.thrall();
      }
    },

    motherOfThrallsMod(card, action) {
      if (action === "leavemotherOfThralls") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("motherOfThrallsMod"),
          1
        );
      }
    },

    motherOfThrallsModMyDamage(target, isFortAttack) {
      if (
        !isFortAttack &&
        this.game.myGame.attack.attacker.name === "Mother of Thralls"
      ) {
        if (target.attack > 0) {
          for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
            if (this.game.myGame.combatZone.cards[i].name === 'Thrall') {
              this.game.myGame.combatZone.cards[i].attack += 1;
            }
          }
        }
      }
    },

    motherOfThrallsModOppDamage(target, isFortAttack) {
      console.log(this.game.myGame.attack.attacker.attack);
      console.log(isFortAttack);
      console.log(target.name);
      if (
        target.name === "Mother of Thralls" &&
        this.game.myGame.attack.attacker.attack > 0 &&
        !isFortAttack
      ) {
        for (var i = 0; i < this.game.oppGame.combatZone.cards.length; i++) {
          if (this.game.oppGame.combatZone.cards[i].name === 'Thrall') {
            this.game.oppGame.combatZone.cards[i].attack += 1;
          }
        }
      }
    },

    motherOfThrallsAttackedByOpp() {
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].name === 'Thrall') {
          this.game.myGame.combatZone.cards[i].attack += 1;
        }
      }
    },

    minaFabledHealer() {
      this.game.myGame.combatZone.mods.push("minaFabledHealerMod");
    },

    minaFabledHealerModMyDamage(target, isFortAttack) {
      if (!isFortAttack) {
        this.game.myGame.attack.isModded = true;
        this.game.myGame.attack.modValue = target.attack - 1;
        if (this.game.myGame.attack.modValue < 0) {
          this.game.myGame.attack.modValue = 0;
        }
      }
    },

    minaFabledHealerModOppDamage(target, isFortAttack) {
      if (!isFortAttack) {
        this.game.myGame.attack.value -= 1;
        if (this.game.myGame.attack.value < 0) {
          this.game.myGame.attack.value = 0;
        }
      }
    },

    minaFabledHealerMod(card, action) {
      if (action === "leaveminaFabledHealer") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("minaFabledHealerMod"),
          1
        );
      }
      if (action === "oppminaFabledHealer") {
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("minaFabledHealerMod"),
          1
        );
      }
    },

    kineticConversionSystem() {
      this.game.myGame.combatZone.mods.push("kineticConversionSystemMod");
    },

    kineticConversionSystemModMyDamage(target, isFortAttack) {
      if (
        !isFortAttack &&
        this.game.myGame.attack.attacker.name === "Kinetic Conversion System"
      ) {
        if (target.attack > 0) {
          this.game.myGame.fort += 2;
        }
      }
    },

    kineticConversionSystemModOppDamage(target, isFortAttack) {
      if (
        target.name === "Kinetic Conversion System" &&
        this.game.myGame.attack.attacker.attack > 0 &&
        !isFortAttack
      ) {
        this.game.oppGame.fort += 2;
      }
    },

    kineticConversionSystemAttackedByOpp() {
      this.game.myGame.fort += 2;
    },

    kineticConversionSystemMod(card, action) {
      if (action === "leavekineticConversionSystem") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("leavekineticConversionSystemMod"),
          1
        );
      }
      if (action === "oppkineticConversionSystem") {
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("kineticConversionSystemMod"),
          1
        );
      }
    },

    //  STATIC EFFECTS

    bolsteringPresence() {
      this.game.myGame.combatZone.mods.push("bolsteringPresenceMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].resilience += 1;
      }
    },

    bolsteringPresenceMod(card, action) {
      if (action === "enter") {
        card.resilience += 1;
      }
      if (action === "leave") {
        card.resilience -= 1;
      }
      if (action === "opp") {
        card.resilience -= 1;
      }
      if (action === "leavebolsteringPresence") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          if (this.game.myGame.combatZone.cards[i].resilience > 1) {
            this.game.myGame.combatZone.cards[i].resilience -= 1;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("bolsteringPresenceMod"),
          1
        );
      }
      if (action === "oppbolsteringPresence") {
        for (var w = 0; w < this.game.oppGame.combatZone.cards.length; w++) {
          if (this.game.oppGame.combatZone.cards[w].resilience > 1) {
            this.game.oppGame.combatZone.cards[w].resilience -= 1;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("bolsteringPresenceMod"),
          1
        );
      }
    },

    bolsteringPresenceModOppDamage(target, isFortAttack) {
      if (target.name === "Bolstering Presence" && !isFortAttack) {
        if (target.resilience > this.game.myGame.attack.attacker.resilience) {
          target.isTarget = false;
          for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
            if (this.game.myGame.combatZone.cards[i].isAttacking) {
              this.game.myGame.combatZone.cards[i].isAttacking = false;
            }
          }
          this.resetAttackObject();
        }
      }
    },

    crownDevotee() {
      this.game.myGame.combatZone.mods.push("crownDevoteeMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].resilience += 1;
      }
    },

    crownDevoteeMod(card, action) {
      if (action === "enter") {
        card.resilience += 1;
      }
      if (action === "leave") {
        card.resilience -= 1;
      }
      if (action === "opp") {
        card.resilience -= 1;
      }
      if (action === "leavecrownDevotee") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          if (this.game.myGame.combatZone.cards[i].resilience > 1) {
            this.game.myGame.combatZone.cards[i].resilience -= 1;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("crownDevoteeMod"),
          1
        );
      }
      if (action === "oppcrownDevotee") {
        for (var w = 0; w < this.game.oppGame.combatZone.cards.length; w++) {
          if (this.game.oppGame.combatZone.cards[w].resilience > 1) {
            this.game.oppGame.combatZone.cards[w].resilience -= 1;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("crownDevoteeMod"),
          1
        );
      }
    },



    inspiringPresence() {
      this.game.myGame.combatZone.mods.push("inspiringPresenceMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].loyalty += 1;
      }
    },

    inspiringPresenceMod(card, action) {
      if (action === "enter") {
        card.loyalty += 1;
      }
      if (action === "leave") {
        card.loyalty -= 1;
      }
      if (action === "opp") {
        card.loyalty -= 1;
      }
      if (action === "leaveinspiringPresence") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          this.game.myGame.combatZone.cards[i].loyalty -= 1;
          if (this.game.myGame.combatZone.cards[i].loyalty < 1) {
            this.game.myGame.combatZone.cards[i].loyalty = 1;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("inspiringPresenceMod"),
          1
        );
      }
      if (action === "oppinspiringPresence") {
        for (var y = 0; y < this.game.oppGame.combatZone.cards.length; y++) {
          this.game.oppGame.combatZone.cards[y].loyalty -= 1;
          if (this.game.oppGame.combatZone.cards[y].loyalty < 1) {
            this.game.oppGame.combatZone.cards[y].loyalty = 1;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("inspiringPresenceMod"),
          1
        );
      }
    },

    inspiringPresenceModOppDamage(target, isFortAttack) {
      if (target.name === "Inspiring Presence" && !isFortAttack) {
        if (target.loyalty > this.game.myGame.attack.attacker.loyalty) {
          target.isTarget = false;
          for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
            if (this.game.myGame.combatZone.cards[i].isAttacking) {
              this.game.myGame.combatZone.cards[i].isAttacking = false;
            }
          }
          this.resetAttackObject();
        }
      }
    },

    jomariFabledAdvisor() {
      this.game.myGame.combatZone.mods.push("jomariFabledAdvisorMod");
      this.game.myGame.repGainMod += 1;
    },

    jomariFabledAdvisorMod(card, action) {
      if (action === "leavejomariFabledAdvisor") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("jomariFabledAdvisorMod"),
          1
        );
        this.game.myGame.repGainMod -= 1;
      }
      if (action === "oppjomariFabledAdvisor") {
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("jomariFabledAdvisorMod"),
          1
        );
      }
    },

    phalanx(card) {
      this.game.myGame.combatZone.mods.push("phalanxMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length - 1; i++) {
        card.attack += 1;
        card.resilience += 1;
      }
    },

    phalanxMod(card, action) {
      if (action === "enter") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          if (this.game.myGame.combatZone.cards[i].name === "Phalanx") {
            this.game.myGame.combatZone.cards[i].attack += 1;
            this.game.myGame.combatZone.cards[i].resilience += 1;
          }
        }
      }
      if (action === "leave") {
        for (var x = 0; x < this.game.myGame.combatZone.cards.length; x++) {
          if (this.game.myGame.combatZone.cards[x].name === "Phalanx") {
            this.game.myGame.combatZone.cards[x].attack -= 1;
            this.game.myGame.combatZone.cards[x].resilience -= 1;
          }
        }
      }
      if (action === "opp") {
        for (var k = 0; k < this.game.oppGame.combatZone.cards.length; k++) {
          if (this.game.oppGame.combatZone.cards[k].name === "Phalanx") {
            this.game.oppGame.combatZone.cards[k].attack -= 1;
            this.game.oppGame.combatZone.cards[k].resilience -= 1;
          }
        }
      }
      if (action === "leavephalanx") {
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("phalanxMod"),
          1
        );
      }
      if (action === "oppphalanx") {
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("phalanxMod"),
          1
        );
      }
    },

    thrillingPresence() {
      this.game.myGame.combatZone.mods.push("thrillingPresenceMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].attack += 1;
      }
    },

    thrillingPresenceMod(card, action) {
      if (action === "enter") {
        card.attack += 1;
      }
      if (action === "leave") {
        card.attack -= 1;
      }
      if (action === "opp") {
        card.attack -= 1;
      }
      if (action === "leavethrillingPresence") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          this.game.myGame.combatZone.cards[i].attack -= 1;
          if (this.game.myGame.combatZone.cards[i].attack < 1) {
            this.game.myGame.combatZone.cards[i].attack = 1;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("thrillingPresenceMod"),
          1
        );
      }
      if (action === "oppthrillingPresence") {
        for (var y = 0; y < this.game.oppGame.combatZone.cards.length; y++) {
          this.game.oppGame.combatZone.cards[y].attack -= 1;
          if (this.game.oppGame.combatZone.cards[y].attack < 1) {
            this.game.oppGame.combatZone.cards[y].attack = 1;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("thrillingPresenceMod"),
          1
        );
      }
    },

    thrillingPresenceModOppDamage(target, isFortAttack) {
      if (target.name === "Thrilling Presence" && !isFortAttack) {
        if (target.attack > this.game.myGame.attack.attacker.attack) {
          target.isTarget = false;
          for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
            if (this.game.myGame.combatZone.cards[i].isAttacking) {
              this.game.myGame.combatZone.cards[i].isAttacking = false;
            }
          }
          this.resetAttackObject();
        }
      }
    },

    fieldCommander() {
      this.game.myGame.combatZone.mods.push("fieldCommanderMod");
      for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].attack += 1;
      }
    },

    fieldCommanderMod(card, action) {
      if (action === "enter") {
        card.attack += 1;
      }
      if (action === "leave") {
        card.attack -= 1;
      }
      if (action === "opp") {
        card.attack -= 1;
      }
      if (action === "leavefieldCommander") {
        for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
          this.game.myGame.combatZone.cards[i].attack -= 1;
          if (this.game.myGame.combatZone.cards[i].attack < 1) {
            this.game.myGame.combatZone.cards[i].attack = 1;
          }
        }
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("fieldCommanderMod"),
          1
        );
      }
      if (action === "oppfieldCommander") {
        for (var y = 0; y < this.game.oppGame.combatZone.cards.length; y++) {
          this.game.oppGame.combatZone.cards[y].attack -= 1;
          if (this.game.oppGame.combatZone.cards[y].attack < 1) {
            this.game.oppGame.combatZone.cards[y].attack = 1;
          }
        }
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("fieldCommanderMod"),
          1
        );
      }
    },

    // ON DEATH TRIGGER

    martyr() {
      this.game.myGame.combatZone.mods.push("martyrMod");
    },

    martyrMod(card, action) {
      if (action === "leavemartyr") {
        this.game.myGame.attack.type = "rep";
        this.game.myGame.attack.value = 1;
        this.game.oppGame.rep -= (1 + this.game.oppGame.repLossMod);
        this.game.myGame.rep += 1 + this.game.myGame.repGainMod;
        this.game.myGame.combatZone.mods.splice(
          this.game.myGame.combatZone.mods.indexOf("martyrMod"),
          1
        );
      }
      if (action === "oppmartyr") {
        this.game.oppGame.rep += 1 + this.game.oppGame.repGainMod;
        this.game.myGame.rep -= 1 + this.game.myGame.repLossMod;
        this.game.oppGame.combatZone.mods.splice(
          this.game.oppGame.combatZone.mods.indexOf("martyrMod"),
          1
        );
      }
    },

    // ON TURN START TRIGGER

    swarmBotTurnStart() {
      for (var f = 0; f < this.game.myGame.combatZone.cards.length; f++) {
        if (this.game.myGame.combatZone.cards[f].name === "Swarm Bot") {
          this.game.myGame.combatZone.cards[f].attack += 1;
          this.game.myGame.combatZone.cards[f].resilience += 1;
        }
      }
      if (this.game.myGame.combatZone.cards.length <= 9) {
        let newSwarmBot = {
          id: 5215 + this.game.myGame.turn.count * this.getRandom(),
          name: "Swarm Bot",
          cost: 5,
          imageName: "swarm_bot.jpg",
          abilities:
            "At the beginning of your turn, give +1 Attack and +1 Resilience to all friendly troops named \"Swarm Bot\", then create a Swarm Bot.",
          flavor: "",
          attack: 1,
          resilience: 1,
          loyalty: 1,
          isAttacking: false,
          isTarget: false,
          canAttack: false,
        };
        this.game.myGame.combatZone.cards.push(newSwarmBot);
        this.checkForCombatZoneMods(newSwarmBot, "enter");
      }
    },

    xmTheEntwinedTurnStart() {
      this.game.myGame.rep += 1;
      this.game.myGame.cache += 5;
    },

    structuralRepairUnitTurnStart() {
      this.game.myGame.fort += 5;
    },


    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // CONTRACT METHODS  8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888

    //  CONTRACT COMPLETE
    contractComplete(contract, i) {
      if (contract.dura === 0) {
        this.game.myGame.contracts.completed.push(contract);
        this.game.myGame.contracts.active.splice(i, 1);
      }
    },

    //  CONTRACTS
    shortTermLoan() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "shortTermLoan"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          this.game.myGame.cache +=
            5 * this.game.myGame.contracts.active[i].counters;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    callInTheRinger() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "callInTheRinger"
        ) {
          for (var q = 0; q < this.game.deck.length; q++) {
            if (this.game.deck[q].isFabled || this.game.deck[q].isFeared) {
              if (this.game.hand.length <= 6) {
                this.game.hand.push(this.game.deck[q]);
                this.game.deck.splice(q, 1);
              }
              break;
            }
          }
          this.game.myGame.contracts.active[i].dura -= 1;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    iKnowAGuy() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "iKnowAGuy"
        ) {
          for (var q = 0; q < this.game.deck.length; q++) {
            if (this.game.deck[q].isHeroic || this.game.deck[q].isVile) {
              if (this.game.hand.length <= 6) {
                this.game.hand.push(this.game.deck[q]);
                this.game.deck.splice(q, 1);
              }
              break;
            }
          }
          this.game.myGame.contracts.active[i].dura -= 1;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    stimulusPackage() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "stimulusPackage"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          if (this.game.myGame.contracts.active[i].counters === 1) {
            if (this.game.myGame.combatZone.cards.length <= 9) {
              let newSoldier = {
                id: 231 + this.getRandom() * this.game.myGame.turn.count,
                name: "Soldier",
                cost: 5,
                imageName: "captain.jpg",
                abilities: "",
                flavor: "",
                attack: 2,
                resilience: 2,
                loyalty: 2,
                isAttacking: false,
                isTarget: false,
                canAttack: true,
              };
              this.game.myGame.combatZone.cards.push(newSoldier);
              this.checkForCombatZoneMods(newSoldier, "enter");
            }
          }
          if (this.game.myGame.contracts.active[i].counters === 2) {
            this.game.myGame.cache += 10;
          }
          if (this.game.myGame.contracts.active[i].counters === 3) {
            this.game.myGame.rep += 2;
            this.game.myGame.fort += 5;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    bountyTarget() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "bounty"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          if (this.game.oppGame.combatZone.cards.length > 0) {
            this.game.myGame.targetInitiator = "bountyResolver";
            this.game.choosingOppTargets = true;
          } else {
            this.contractComplete(this.game.myGame.contracts.active[i], i);
          }
        }
      }
    },

    bountyResolverMe(card) {
      this.game.choosingOppTargets = false;
      this.game.myGame.kill = card.id;
      this.killOppCard(card);
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "bounty"
        ) {
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
      this.sendGameState();
      this.game.myGame.kill = 0;
      this.game.myGame.targetInitiator = "";
    },

    bountyResolverOpp() {
      this.kill();
    },

    ceasefire() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "ceasefire"
        ) {
          for (var w = 0; w < this.game.myGame.combatZone.cards.length; w++) {
            if (this.game.hand.length <= 6) {
              this.game.hand.push(this.game.myGame.combatZone.cards[w]);
            }
          }
          this.game.myGame.combatZone.cards = [];
          this.game.myGame.contracts.active[i].dura -= 1;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
          this.game.myGame.directive = {
            name: "ceasefire",
          };
          this.sendGameState();
          this.game.myGame.directive = {};
        }
      }
    },

    ceasefireDirective() {
      for (var w = 0; w < this.game.myGame.combatZone.cards.length; w++) {
        if (this.game.hand.length <= 6) {
          this.game.hand.push(this.game.myGame.combatZone.cards[w]);
        }
      }
      this.game.myGame.combatZone.cards = [];
      this.sendGameState();
    },

    misinformationCampaign() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "misinformationCampaign"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.attack.value = 2;
          this.game.myGame.attack.type = "rep";
          this.game.oppGame.rep -= (2 + this.game.oppGame.repLossMod);
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    firewallUpdate() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "firewallUpdate"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.rep += 1 + this.game.myGame.repGainMod;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    dataMine() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "dataMine"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.drawCard();
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    majorBreakthrough() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "majorBreakthrough"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          if (this.game.myGame.contracts.active[i].counters === 1) {
            this.drawCard();
            this.game.myGame.cache += 5;
          } else {
            this.drawCard();
            this.drawCard();
            this.game.myGame.cache += 10;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    combatInstruction() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "combatInstruction"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          if (this.game.myGame.combatZone.cards.length >= 1) {
            let index = this.getRandomIntInclusive(
              0,
              this.game.myGame.combatZone.cards.length - 1
            );
            this.game.myGame.combatZone.cards[index].attack += 1;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    enlistTheMasses() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "enlistTheMasses"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          if (this.game.myGame.combatZone.cards.length <= 9) {
            let newEnlistedCivilian = {
              id: 564 + this.getRandom() * this.game.myGame.turn.count,
              name: "Enlisted Civilian",
              cost: 5,
              imageName: "enlisted_civilian.jpg",
              abilities:
                "When this or another Enlisted Civilian enters, if there is another troop in the combat zone, this gains +1 Resilience.",
              flavor: "",
              attack: 1,
              resilience: 2,
              loyalty: 4,
              isAttacking: false,
              isTarget: false,
              canAttack: true,
            };
            this.game.myGame.combatZone.cards.push(newEnlistedCivilian);
            this.checkForCombatZoneMods(newEnlistedCivilian, "enter");
            this.enlistedCivilian();
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    stimpakRequisition() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "stimpakRequisition"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          if (this.game.myGame.combatZone.cards.length >= 1) {
            let index = this.getRandomIntInclusive(
              0,
              this.game.myGame.combatZone.cards.length - 1
            );
            this.game.myGame.combatZone.cards[index].resilience += 1;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    repairServices() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "repairServices"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.fort += 5;
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    netRecruitmentProgram() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "netRecruitmentProgram"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          let index = this.getRandomIntInclusive(0, this.game.hand.length - 1);
          this.game.myGame.combatZone.cards.push(this.game.hand[index]);
          this.checkForCombatZoneMods(this.game.hand[index], "enter");
          try {
            this[this.camelize(this.game.hand[index].name)](
              this.game.hand[index]
            );
          } catch (error) {
            console.warn(error);
          }
          this.game.hand.splice(index, 1);
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    moraleBoost() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "moraleBoost"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          for (var k = 0; k < this.game.myGame.combatZone.cards.length; k++) {
            this.game.myGame.combatZone.cards[k].attack +=
              1 * this.game.myGame.contracts.active[i].counters;
            this.game.myGame.combatZone.cards[k].resilience +=
              1 * this.game.myGame.contracts.active[i].counters;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    spreadMissileAttack() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "spreadMissileAttack"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          if (this.game.myGame.contracts.active[i].counters === 1) {
            this.game.myGame.directive = {
              name: "spreadMissileAttackOne",
            };
          }
          if (this.game.myGame.contracts.active[i].counters === 2) {
            this.game.myGame.directive = {
              name: "spreadMissileAttackTwo",
            };
          }
          for (var w = 0; w < this.game.oppGame.combatZone.cards.length; w++) {
            this.game.oppGame.combatZone.cards[w].resilience -=
              2 * this.game.myGame.contracts.active[i].counters;
            this.checkForDamageMods(this.game.oppGame.combatZone.cards[w], false);  
          }
          for (
            var d = this.game.oppGame.combatZone.cards.length - 1;
            d >= 0;
            d--
          ) {
            this.checkOppDeath(d);
          }
          this.sendGameState();
          this.game.myGame.directive = {};
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    spreadMissileAttackOneDirective() {
      for (var w = 0; w < this.game.myGame.combatZone.cards.length; w++) {
        this.game.myGame.combatZone.cards[w].resilience -= 2;
        this.checkForDamageMods(this.game.oppGame.combatZone.cards[w], false);  
      }
      for (var d = this.game.myGame.combatZone.cards.length - 1; d >= 0; d--) {
        this.checkDeath(d);
      }
    },

    spreadMissileAttackTwoDirective() {
      for (var w = 0; w < this.game.myGame.combatZone.cards.length; w++) {
        this.game.myGame.combatZone.cards[w].resilience -= 4;
        this.checkForDamageMods(this.game.oppGame.combatZone.cards[w], false);
      }
      for (var d = this.game.myGame.combatZone.cards.length - 1; d >= 0; d--) {
        this.checkDeath(d);
      }
    },

    microViruses() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "microViruses"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          let options = ["troop", "fort", "rep"];
          let choice = options[this.getRandomIntInclusive(0, 2)];
          if (choice == "troop") {
            if (this.game.oppGame.combatZone.cards.length === 1) {
              this.game.oppGame.combatZone.cards[0].resilience -= 1;
              this.checkForDamageMods(this.game.oppGame.combatZone.cards[0], false);
              if (this.game.oppGame.combatZone.cards[0].resilience === 0) {
                this.game.oppGame.combatZone.cards.shift();
              }
              this.game.myGame.attack.target = this.game.oppGame.combatZone.cards[0];
              this.game.myGame.attack.type = "contract";
              this.game.myGame.attack.value = 1;
            } else if (this.game.oppGame.combatZone.cards.length > 1) {
              let index = this.getRandomIntInclusive(
                0,
                this.game.oppGame.combatZone.cards.length - 1
              );
              this.game.oppGame.combatZone.cards[index].resilience -= 1;
              if (this.game.oppGame.combatZone.cards[index].resilience === 0) {
                this.game.oppGame.combatZone.cards.splice(index, 1);
              }
              this.game.myGame.attack.target = this.game.oppGame.combatZone.cards[
                index
              ];
              this.game.myGame.attack.type = "contract";
              this.game.myGame.attack.value = 1;
            } else {
              choice = options[this.getRandomIntInclusive(1, 2)];
            }
          }
          if (choice === "fortress") {
            this.game.myGame.attack.value = 1;
            this.game.myGame.attack.type = "fort";
            this.game.oppGame.fort -= 1;
          }
          if (choice === "reputation") {
            this.game.myGame.attack.value = 1;
            this.game.myGame.attack.type = "rep";
            this.game.oppGame.rep -= (1 + this.game.oppGame.repLossMod);
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },

    randomizedDroneStrikes() {
      for (var i = 0; i < this.game.myGame.contracts.active.length; i++) {
        if (
          this.camelize(this.game.myGame.contracts.active[i].name) ===
          "randomizedDroneStrikes"
        ) {
          this.game.myGame.contracts.active[i].dura -= 1;
          this.game.myGame.contracts.active[i].counters += 1;
          if (this.game.myGame.contracts.active[i].counters === 2) {
            if (this.game.oppGame.combatZone.cards.length === 1) {
              this.game.myGame.kill = this.game.oppGame.combatZone.cards[0].id;
              this.game.oppGame.combatZone.cards.shift();
            } else if (this.game.oppGame.combatZone.cards.length > 1) {
              let index = this.getRandomIntInclusive(
                0,
                this.game.oppGame.combatZone.cards.length - 1
              );
              this.game.myGame.kill = this.game.oppGame.combatZone.cards[
                index
              ].id;
              this.game.oppGame.combatZone.cards.splice(index, 1);
            }
            this.game.myGame.contracts.active[i].counters = 0;
            this.sendGameState();
            this.game.myGame.kill = 0;
          }
          this.contractComplete(this.game.myGame.contracts.active[i], i);
        }
      }
    },
  },

  // CREATED  4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
  created() {
    //  GAME SESSION SUBSCRIPTION  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    let gameSessionSub = this.$store.state.stompClient.subscribe(
      "/topic/match/" + this.$store.state.user.username,
      (tick) => {
        this.isLoading = false;
        let message = JSON.parse(tick.body);
        this.$store.commit("UPDATE_OPP_GAME_STATE", message);
        this.game.oppGame = message;

        //concede
        if (message.concede === true) {
          this.show();
        }

        // turn switch
        if (message.turnSwitch) {
          this.switchTurns();
        }

        // kill
        if (message.kill > 0 && message.targetInitiator === "") {
          this.kill();
        }

        // target initiator
        if (message.targetInitiator != "") {
          this.resolveInitiator();
        }

        // directive
        if (message.directive) {
          try {
            this[this.camelize(message.directive.name) + "Directive"](
              message.directive
            );
          } catch (error) {
            console.warn(error);
          }
        }

        // attack
        if (
          message.attack !=
          {
            attacker: {},
            target: {},
            value: 0,
            type: "",
            isModded: false,
            modValue: 0,
          }
        ) {
          this.checkIncomingAttack();
        }

        //update feed
        if (
          !this.game.myGame.turn.isTurn &&
          !this.game.myGame.mulliganPhase &&
          !this.game.myGame.rollPhase
        ) {
          this.rotato();
        }
      }
    );
    this.$store.commit("SET_GAME_SESSION_SUBSCRIPTION", gameSessionSub);
  },

  // MOUNTED  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  mounted() {
    window.addEventListener("beforeunload", this.leaving);

    const noContext = document.getElementById("fort-attack-opp");
    noContext.addEventListener("contextmenu", (e) => {
      e.preventDefault();
      if (this.game.myGame.turn.isTurn && this.game.myGame.attack.attacker.name) {
        this.fortAttack();
      }
    });

    this.game = this.$store.state.game;

    this.game.deck = this.$store.state.selectedDeck.cards;
    this.shuffle(this.game.deck);
    console.log("drawing hands...");
    this.drawCard();
    this.drawCard();
    this.drawCard();
    this.drawCard();

    this.game.contracts.deck = this.$store.state.selectedDeck.contracts;
    this.shuffle(this.game.contracts.deck);
    this.drawContract();
    this.drawContract();
    this.drawContract();
    this.drawContract();

    this.game.myGame.rollPhase = true;
    this.sendGameState();
  }
};
</script>

<style>
.arena {
  display: grid;
  grid-template-rows: 400px 49px 400px 200px;
  grid-template-columns: 250px 200px 1080px 360px;
  row-gap: 3px;
  column-gap: 5px;
  grid-template-areas:
    "opp-status opp-zone opp-zone enemy-contracts"
    "options game-info game-info turn-button"
    "avatar-panel my-zone my-zone contracts-active"
    "my-status deck hand contracts";

  background-image: url("../assets/backgrounds/ships.jpg");
}

.options {
  display: flex;
  flex-direction: row;
  justify-content: center;
  grid-area: options;
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 5px;
}

.game-info-hub {
  grid-area: game-info;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin-left: 10px;
  margin-top: 10px;
}

.game-info-contracts {
  width: 30%;
}

.turn-button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  grid-area: turn-button;
  margin: 0px 10px 0px 10px;
}

.status-div-my-username {
  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 0px 2px 0px;
  border: 1px solid rgb(151, 151, 151);
  border-radius: 5px;
  margin: 0px 5px 0px 5px;
  background-image: linear-gradient(180deg, rgb(0, 26, 39), rgb(20, 164, 220));
}

.status-div-opp-username {
  color: rgb(255, 255, 255);
  font-family: "Orbitron";
  font-size: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px 0px 2px 0px;
  border: 1px solid rgb(151, 151, 151);
  border-radius: 15px;
  margin: 0px 5px 0px 5px;
  background-image: linear-gradient(180deg, rgb(39, 0, 0), rgb(160, 0, 0));
}

.opp-zone {
  grid-area: opp-zone;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  background-image: linear-gradient(60deg,
      rgba(0, 0, 0, 0.7),
      rgba(0, 0, 0, 0.7));
  margin: 5px;
  gap: 5px;
  border-radius: 10px;
  border: 1px solid rgb(43, 43, 43);
  height: 100%;
  width: 100%;
}

.loader {
  min-height: 90px;
}

.my-zone {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  gap: 5px;
  background-image: linear-gradient(60deg,
      rgba(0, 0, 0, 0.7),
      rgba(0, 0, 0, 0.7));
  margin: 5px;
  border: 1px solid rgb(43, 43, 43);
  border-radius: 10px;
  height: 100%;
  width: 100%;
  grid-area: my-zone;
}

.pregame-container {
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  height: 100%;
  width: 100%;
  grid-area: my-zone;
}

.my-zone-start {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  gap: 5px;
  background-image: linear-gradient(60deg,
      rgba(0, 0, 0, 0.7),
      rgba(0, 0, 0, 0.7));
  margin: 5px;
  border: 1px solid rgb(43, 43, 43);
  border-radius: 10px;
  height: 100%;
  width: 100%;
  grid-area: my-zone;
}

.zones-targeting-spotlight-one {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 11%;
  left: 41%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-two {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 11%;
  left: 34.4%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-three {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 11%;
  left: 27.8%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-four {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 11%;
  left: 21.1%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-five {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 11%;
  left: 14.5%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-sixplus {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 1.8%;
  left: 13.5%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;

  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-one-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 53.1%;
  left: 41%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-two-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 53.1%;
  left: 34.4%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-three-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 53.1%;
  left: 27.8%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-four-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 53.1%;
  left: 21.1%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-five-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 53.1%;
  left: 14.5%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;
  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zones-targeting-spotlight-sixplus-friendly {
  background-image: linear-gradient(60deg,
      rgba(2, 2, 2, 0.5),
      rgba(0, 0, 0, 0.5));
  position: fixed;
  top: 44%;
  left: 13.5%;
  max-width: 68%;
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 4px;

  /* for IE */
  box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
  /* for real browsers */
  box-shadow: 0 0 0 100vmax rgba(0, 0, 0, 0.5);
}

.zone-hand {
  display: grid;
  grid-template-columns: 10% 10% 10% 10% 10% 10% 30%;
  justify-content: center;
  align-items: center;

}

.zone-hand-cont {
  grid-area: hand;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  background-image: linear-gradient(60deg,
      rgba(0, 0, 0, 0.7),
      rgba(0, 0, 0, 0.7));
  border-radius: 10px;
  border: 1px solid black;
  margin: 5px;
}

.deck {
  margin: 2px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  grid-area: deck;
  border: 1px solid rgb(102, 102, 102);
  border-radius: 10px;
  width: 80%;
  background-color: rgba(57, 57, 57, 0.5);
}

.deck-img {
  width: 155px;
  height: 190px;
  transition: all 0.2s ease-in-out;
}

.deck-img:hover {
  transform: scale(1.01);
  cursor: pointer;
}

/* .dead-zone {
  min-height: 200px;
  min-width: 180px;
  padding: 10px 10px 10px 10px;
  display: grid;
  grid-template-rows: 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px;
  justify-items: center;
} */

.cont-cont-av {
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 0, 0, 0.8), rgba(0, 44, 56, 0.8));
  background-position: right;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 188px;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: contracts;
}

.cont-cont-ac {
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 0, 0, 0.8), rgba(0, 44, 56, 0.8));
  background-position: right;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 100%;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: contracts-active;
}

.enemy-cont-ac {
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(0, 0, 0, 0.8), rgba(1, 17, 56, 0.8));
  background-position: right;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 100%;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: enemy-contracts;
}

.cont-av {
  min-width: 300px;
  min-height: 300px;
}

.cont-ac {
  min-width: 300px;
  min-height: 300px;
}

.contract-head {
  padding: 3px;
  color: rgb(233, 236, 222);
  font-family: 'Orbitron', sans-serif;
  font-weight: 800;
  font-size: 22px;
  text-shadow: 1px 2px rgb(77, 77, 77);
  text-align: center;
}

.castable {
  box-shadow: 0 0 10px #fff, -3px 0 4px rgb(255, 240, 35),
    3px 0 4px rgb(255, 240, 35);
}

.castable:hover {
  box-shadow: 0 0 10px #fff, -3px 0 4px rgb(255, 240, 35),
    3px 0 4px rgb(255, 240, 35);
}

.contractable {
  box-shadow: -5px 0 5px rgb(18, 128, 218), 5px 0 5px rgb(116, 142, 255);
}

.contractable:hover {
  box-shadow: -5px 0 5px rgb(18, 128, 218), 5px 0 5px rgb(116, 142, 255);
}

.mulligan-prompt {
  font-family: "Orbitron";
  font-size: 20px;
  color: white;
}

.attacking {
  animation-name: attacking;
  animation-duration: 1s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;

  box-shadow: 0 0 5px 5px rgb(255, 43, 43), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(255, 0, 0);
}

.attacking:hover {
  box-shadow: 0 0 5px 5px rgb(255, 43, 43), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(255, 0, 0);
}

@keyframes attacking {
  0% {
    transform: translate(0, 0px);
  }

  50% {
    transform: translate(0, 7px);
    transform: scale(1.03);
    box-shadow: 0 0 5px 5px rgb(158, 0, 0), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(248, 97, 97);
  }

  100% {
    transform: translate(0, -0px);
  }
}

.targeted {
  animation-name: target;
  animation-duration: 1s;
  animation-iteration-count: 1;
  animation-timing-function: ease-in-out;
}

@keyframes target {
  0% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(0, 0px);
  }

  10% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(2px, -2px);
    transform: rotate(5deg);
  }

  20% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(-2px, 2px);
    transform: rotate(5deg);
  }

  40% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(-1px, 1px);
    transform: rotate(-2deg);
  }

  70% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(1px, -1px);
    transform: rotate(2deg);
  }

  100% {
    box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
      0 0 5px 5px rgb(43, 100, 255);
    transform: translate(0, -0px);
  }
}

.canattack {
  box-shadow: 0 0 5px 5px rgb(0, 143, 76), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(0, 73, 39);
}

.canattack:hover {
  box-shadow: 0 0 5px 5px rgb(0, 143, 76), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(0, 73, 39);
}

.vic-modal-content {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.vic-modal-header {
  font-family: "Orbitron";
  font-size: 70px;
  color: white;
  text-shadow: 2px 2px rgb(88, 88, 88);
}

.vic-modal-prompt {
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 25px;
  margin-top: 20px;
}

.exit-btn {
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 18px;
  height: 47px;
  width: auto;
  border-radius: 5px;
  border: 1px solid white;
  background-color: rgb(0, 0, 0);
  transition: all 0.2s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 10px 0px 10px;
  margin: 2px 10px 2px 0px;
}

.exit-btn:hover {
  cursor: pointer;
  background-color: rgba(190, 0, 0, 0.9);
}

.hallowed {
  animation-name: hallowed;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;

  box-shadow: 0 0 3px 3px rgb(255, 225, 1), 0 0 3px 3px rgb(255, 255, 255),
    0 0 3px 3px rgb(255, 246, 120);
}

.hallowed:hover {
  box-shadow: 0 0 3px 3px rgb(255, 225, 1), 0 0 3px 3px rgb(255, 255, 255),
    0 0 3px 3px rgb(255, 246, 120);
}

@keyframes hallowed {
  20% {
    box-shadow: 0 0 3px 3px rgb(192, 175, 45), 0 0 3px 3px rgb(255, 233, 66),
      0 0 3px 3px rgb(255, 245, 104);
  }

  50% {
    box-shadow: 0 0 3px 3px rgb(255, 243, 153), 0 0 3px 3px rgb(255, 238, 143),
      0 0 3px 3px rgb(250, 237, 60);
  }

  80% {
    box-shadow: 0 0 3px 3px rgb(255, 248, 193), 0 0 3px 3px rgb(177, 197, 44),
      0 0 3px 3px rgb(255, 238, 0);
  }

  100% {
    box-shadow: 0 0 3px 3px rgb(255, 238, 111), 0 0 3px 3px rgb(255, 238, 107),
      0 0 3px 3px rgb(248, 241, 140);
  }
}

.fabled {
  animation-name: fabled;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;

  box-shadow: 0 0 2px 2px rgb(1, 81, 157), 0 0 2px 2px rgb(61, 117, 177),
    0 0 2px 2px rgb(5, 18, 92);
}

.fabled:hover {
  box-shadow: 0 0 2px 2px rgb(1, 81, 157), 0 0 2px 2px rgb(61, 117, 177),
    0 0 2px 2px rgb(5, 18, 92);
}

@keyframes fabled {
  20% {
    box-shadow: 0 0 2px 2px rgb(0, 96, 186), 0 0 2px 2px rgb(67, 117, 172),
      0 0 2px 2px rgb(14, 24, 84);
  }

  50% {
    box-shadow: 0 0 2px 2px rgb(0, 78, 151), 0 0 2px 2px rgb(54, 120, 192),
      0 0 2px 2px rgb(28, 37, 87);
  }

  80% {
    box-shadow: 0 0 2px 2px rgb(0, 37, 72), 0 0 2px 2px rgb(14, 106, 204),
      0 0 2px 2px rgb(82, 89, 138);
  }

  100% {
    box-shadow: 0 0 2px 2px rgb(0, 70, 135), 0 0 2px 2px rgb(71, 134, 201),
      0 0 2px 2px rgb(55, 64, 117);
  }
}

.heroic {
  animation-name: heroic;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-in-out;

  box-shadow: 0 0 1px 1px rgb(255, 72, 240), 0 0 1px 1px rgb(196, 214, 235),
    0 0 1px 1px rgb(78, 5, 92);
}

.heroic:hover {
  box-shadow: 0 0 1px 1px rgb(255, 72, 240), 0 0 1px 1px rgb(196, 214, 235),
    0 0 1px 1px rgb(78, 5, 92);
}

@keyframes heroic {
  20% {
    box-shadow: 0 0 1px 1px rgb(201, 42, 188), 0 0 1px 1px rgb(191, 203, 216),
      0 0 1px 1px rgb(103, 6, 123);
  }

  50% {
    box-shadow: 0 0 1px 1px rgb(162, 26, 150), 0 0 1px 1px rgb(232, 233, 233),
      0 0 1px 1px rgb(49, 4, 58);
  }

  80% {
    box-shadow: 0 0 1px 1px rgb(225, 39, 209), 0 0 1px 1px rgb(255, 255, 255),
      0 0 1px 1px rgb(50, 2, 60);
  }

  100% {
    box-shadow: 0 0 1px 1px rgb(255, 72, 240), 0 0 1px 1px rgb(185, 201, 220),
      0 0 1px 1px rgb(75, 5, 89);
  }
}
.option-cont {
  border: 1 px solid white;
  border-radius: 8px;
  background-color: rgba(0, 119, 79, 0.842);
  transition: all 0.2s ease-in-out;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  padding: 10px;
  margin: 15px;
  color: white;
  font-family: 'Share Tech Mono', monospace;
  font-size: 20px;
}
.option-cont:hover {
  transform: scale(1.08);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(60deg, rgba(11, 160, 123, 0.8), rgba(11, 160, 123, 0.8));
}

@keyframes statusDamage {
  0% {
    transform: translate(0, 0px);
  }

  10% {
    box-shadow: 0 0 10px rgb(255, 63, 63), -3px 0 4px rgb(255, 35, 35),
      3px 0 4px rgb(165, 24, 24);
    background-color: red;
    transform: translate(2px, -2px);
    transform: rotate(2deg);
  }

  20% {
    box-shadow: 0 0 10px rgb(255, 63, 63), -3px 0 4px rgb(255, 35, 35),
      3px 0 4px rgb(165, 24, 24);
    background-color: red;
    transform: translate(-2px, 2px);
    transform: rotate(2deg);
  }

  40% {
    box-shadow: 0 0 10px rgb(255, 63, 63), -3px 0 4px rgb(255, 35, 35),
      3px 0 4px rgb(165, 24, 24);
    background-color: red;
    transform: translate(-1px, 1px);
    transform: rotate(-1deg);
  }

  70% {
    box-shadow: 0 0 10px rgb(255, 63, 63), -3px 0 4px rgb(255, 35, 35),
      3px 0 4px rgb(165, 24, 24);
    background-color: red;
    transform: translate(1px, -1px);
    transform: rotate(1deg);
  }

  100% {
    background-color: red;
    transform: translate(0, -0px);
  }
}

</style>
