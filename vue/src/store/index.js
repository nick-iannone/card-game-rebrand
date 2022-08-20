import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  // STATE  ////////////////////////////////////////////////////////////////////////////////////////
  state: {
    // GAME STATE  //
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
        username: "",
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
        username: "",
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

    // DECK BUILDING  ////////////////////////////////////////////////////////////////////////////////////////
    newDeckCardCount: 0,
    newDeckContractCount: 0,
    myDecks: [],
    deckToEdit: null,
    loadDeckToEdit: false,

    // USER INFO  ////////////////////////////////////////////////////////////////////////////////////////////
    token: currentToken || "",
    user: currentUser || {},
    sessionId: '',
    allUsers: [],
    users: [],

    // CHAT & WEBSOCKET  /////////////////////////////////////////////////////////////////////////////////////
    chatLog: {},
    socket: null,
    stompClient: null,

    // SUBSCRIPTIONS  ////////////////////////////////////////////////////////////////////////////////////////
    matchmakingSubscription: null,
    gameSessionSubscription: null,
  },
  // getters: {
  //   deckToEdit: state => {
  //     return state.deckToEdit;
  //   }
  // },
// MUTATIONS  ////////////////////////////////////////////////////////////////////////////////////////////////
  mutations: {

   // USER INFO  /////////////////////////////////////////////////////////////////////////////////////////////
   SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
    },

    // MATCHMAKING  ////////////////////////////////////////////////////////////////////////////////////////
    SET_GAME_USERNAME(state, username) {
      state.game.myGame.username = username;
    },
    SET_MYGAME_RFB(state, boolean) {
      state.game.myGame.readyForBattle = boolean;
    },
    SET_OPPGAME_RFB(state, boolean) {
      state.game.oppGame.readyForBattle = boolean;
    },
    SET_OPPONENT(state, username) {
      state.game.oppGame.username = username;
    },

    // CHAT & WEBSOCKET  ////////////////////////////////////////////////////////////////////////////////////
    SET_SESSION_ID (state, sessionId) {
      state.sessionId = sessionId;
    },
    CLEAR_SESSION_ID(state) {
      state.sessionId = '';
    },
    SET_ALL_USERS(state, allUsers) {
      state.allUsers = allUsers;
    },
    USER_ONLINE_EVENT(state, users) {
      state.users = users;
    },
    SET_CHAT_LOG(state, chatLog) {
      let threads = chatLog.threads
      for (var i = 0; i < threads.length; i ++) {
        let messages = threads[i].messages;
        for (var n = 0; n < messages.length; n++) {
          if ((messages[n].fromUsername !== threads[i].chatWith) && (messages[n].toUsername !== threads[i].chatWith)) {
            messages.splice(n, 1);
            n--;
          }
        }
      }
      state.chatLog = chatLog;
    },

    // SUBSCRIPTIONS  ////////////////////////////////////////////////////////////////////////////////////////
    SET_MATCHMAKING_SUBSCRIPTION(state, stompSub) {
      state.matchmakingSubscription = stompSub;
    },
    SET_GAME_SESSION_SUBSCRIPTION(state, stompSub) {
      state.gameSessionSubscription = stompSub;
    },

    // DECK MANAGEMENT  ////////////////////////////////////////////////////////////////////////////////////////
    UPDATE_NEW_DECK_CARD_COUNT(state, newCount) {
      state.newDeckCardCount = newCount;
    },
    UPDATE_NEW_DECK_CONTRACT_COUNT(state, newCount) {
      state.newDeckContractCount = newCount;
    },
    SET_MY_DECKS(state, myDecks) {
      state.myDecks = myDecks;
    },
    SET_DECK_TO_EDIT(state, deck) {
      state.loadDeckToEdit = false;
      state.deckToEdit = deck;
      if (deck) {
        state.loadDeckToEdit = true;
      }
    },
    SET_GAME_DECK(state, deck) {
      state.selectedDeck = deck;
    },
    // GAMEPLAY  //////////////////////////////////////////////////////////////////////////////////////////////
    UPDATE_OPP_GAME_STATE(state, oppGameState) {
      state.game.oppGame = oppGameState;
    },
    UPDATE_MY_GAME_STATE(state, myGameState) {
      state.game.myGame = myGameState;
    },
    RESET_GAME_OBJECT(state) {
      state.game = {
        hand: [],
        deck: [],
        deadZone: [],
        contracts: {
          deck: [],
          available: [],
        },
        hasAttacked: [],
        choosingOppTargets: false,
        choosingMyTargets:  false,
        oppFortAttack: false,
        myFortAttack: false,
        oppRepAttack: false,
        myRepAttack: false,
        myGame: {
          username: "",
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
          username: "",
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
      }
    }
  },
});
