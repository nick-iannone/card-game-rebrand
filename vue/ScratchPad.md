<!-- INSPO -->

MONSTER TRAIN - roguelike deck-builder
SLAY THE SPIRE - roguelike deck-builder
GWENT - Witcher card game
DICEY DUNGEONS - dice-rolling card game
INTO THE BREACH - roguelike turn-based puzzle

HEROIC FABLED AND HALLOWED

<!-- GAME ARCHITECTURE -->

card: {
  id: int,
  name: "String",
  cost: int,
  type: "String",
  faction: "String",
  image: "String",
  text1: "String",
  text2: "String",
  text3: "String",
  text4: "String",
  flavor: "String",
  attack: int,
  resilience: int,
  modSlots: int,
  upgrades: [
    {
      upgrade: {
        name: "String",
        slot: int
      }
    }
  ],
  onEnter: boolean,
	onLeave: boolean,
	onAttack: boolean,
	onDamage: boolean,
	onTurnStart: boolean,
	onTurnEnd: boolean,
	isAttacking: boolean,
	isTarget: boolean,
	canAttack: boolean,
	count: int,
}

game: {
  hand: [card],
  deck: [card],
  deadZone: [card],
  contracts: {
    deck: [],
    available: [],
  },
  hasAttacked: [],
  choosingOppTargets: false,
  choosingMyTargets: false,
  myGame: {
    username: "",
    lastEvent: "",
    directive: {},
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
    modded: false,
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
    },
    oppGame: {
      username: "",
      lastEvent: "",
      directive: {},
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
      modded: false,
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
  },
  }
},

TYPES ///////////////////////////

onEnter
onLeave
onAttack
onDamage
onTurnStart
onTurnEnd

/////////////////////////////////
