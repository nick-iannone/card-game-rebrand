<template>
  <div class="deck-builder">
    <div class="deck-cards-grid">
      <div class="db-cards-grid-header">CARDS</div>
      <draggable
        class="cards-added"
        v-model="newDeck.cards"
        :group="{ name: 'cards' }"
        ghost-class="ghosties"
        drag-class="ghosties"
        @change="handleAddedCard"
      >
        <dbcardtile
          v-for="card in this.newDeck.cards"
          :key="card.id"
          :card="card"
          @removeRow="onRemoveRow"
        />
      </draggable>
    </div>

    <div class="deck-contracts-grid">
      <div class="db-contracts-grid-header">CONTRACTS</div>
      <draggable
        class="contracts-added"
        v-model="newDeck.contracts"
        :group="{ name: 'contracts' }"
        ghost-class="ghosties"
        drag-class="ghosties"
        @change="handleAddedContract"
      >
        <dbcontracttile
          v-for="contract in this.newDeck.contracts"
          :key="contract.id"
          :contract="contract"
          @deleteRowContract="deleteRowContract"
        />
      </draggable>
    </div>

    <div class="db-stats">
      <div class="db-card-count">
        Cards:
        <div
          :style="{ color: computeCardDeckLength < 30 ? 'red' : 'green' }"
          class="running-card-count"
        >
          {{ computeCardDeckLength }}
        </div>
        /30
      </div>
      <div class="db-card-count">
        Contracts:
        <div
          :style="{ color: computeContractDeckLength < 15 ? 'red' : 'green' }"
          class="running-card-count"
        >
          {{ computeContractDeckLength }}
        </div>
        /15
      </div>
      <div></div>
      <div class="db-search-input">
        <input
          class="db-input-name"
          type="text"
          v-model="newDeck.name"
          placeholder="Deck Name..."
          required
          :maxLength="max"
        />
        <div
          class="input-count"
          :style="{
            backgroundColor: newDeck.name.length < min ? 'crimson' : 'seagreen',
          }"
        >
          {{ max - newDeck.name.length }}
        </div>
      </div>
      <button class="nav-btn" @click="validateDeck()">SAVE DECK</button>
    </div>

    <div class="db-radio-filters">
      <div class="cards-or-conts-filters">
        <div class="align-radio-selectors">
          <input
            type="radio"
            id="cards"
            value="cards"
            v-model="cardsOrContracts"
            name="cardsOrContracts"
          />
          <label for="cards">Cards</label>
        </div>
        <div class="align-radio-selectors">
          <input
            type="radio"
            id="contracts"
            value="contracts"
            v-model="cardsOrContracts"
            name="cardsOrContracts"
          />
          <label for="contracts">Contracts</label>
        </div>
      </div>
      <div>
        <div class="db-radio-filters-filters">
          <input
            type="radio"
            v-model="filter"
            value="name"
            id="name"
            name="filter"
          />
          <label for="name">Name</label>
          <br />
          <input
            type="radio"
            v-model="filter"
            value="cost"
            id="cost"
            name="filter"
          />
          <label for="cost">Cost</label>
          <br />
          <input
            type="radio"
            v-model="filter"
            value="description"
            id="description"
            name="filter"
          />
          <label for="description">Description</label>
          <br />
          <input
            type="radio"
            v-model="filter"
            value="attack"
            id="attack"
            name="filter"
            v-if="cardsOrContracts == 'cards'"
          />
          <label for="attack" v-if="cardsOrContracts == 'cards'">Attack</label>
          <br />
          <input
            type="radio"
            v-model="filter"
            value="resilience"
            id="resilience"
            name="filter"
            v-if="cardsOrContracts == 'cards'"
          />
          <label for="resilience" v-if="cardsOrContracts == 'cards'"
            >Resilience</label
          >
          <br />
          <input
            type="radio"
            v-model="filter"
            value="loyalty"
            id="loyalty"
            name="filter"
            v-if="cardsOrContracts == 'cards'"
          />
          <label for="loyalty" v-if="cardsOrContracts == 'cards'"
            >Loyalty</label
          >
          <br />
          <input
            type="radio"
            v-model="filter"
            value="duration"
            id="duration"
            name="filter"
            v-if="cardsOrContracts == 'contracts'"
          />
          <label for="duration" v-if="cardsOrContracts == 'contracts'"
            >Duration</label
          >
          <br />
        </div>
        <div class="db-search-input">
          <input
            class="db-input"
            type="text"
            v-model="search"
            placeholder="Filter Collection..."
          />
        </div>
      </div>
    </div>

    <div class="selection-container">
      <div class="selection-area">
        <draggable
          class="card-pool"
          v-if="cardsOrContracts == 'cards'"
          v-model="pageOfItems"
          :group="{ name: 'cards', pull: 'clone', put: false }"
        >
          <card v-for="card in pageOfItems" :key="card.id" :card="card" />
        </draggable>
        <draggable
          class="contract-pool"
          v-if="cardsOrContracts == 'contracts'"
          v-model="pageOfItems"
          :group="{ name: 'contracts', pull: 'clone', put: false }"
        >
          <contractcollection
            v-for="contract in pageOfItems"
            :key="contract.id"
            :contract="contract"
          />
        </draggable>
        <div class="deck-builder-pagination">
          <jw-pagination
            :disableDefaultStyles="true"
            :items="filteredList"
            @changePage="onChangePage"
            :pageSize="4"
            :labels="{
              first: '<<',
              last: '>>',
              previous: '<',
              next: '>',
            }"
            class="pagination-station"
          ></jw-pagination>
        </div>
      </div>
    </div>

    <modal
      name="validation-error-modal"
      v-if="showValidationError"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;"
    >
      <div class="error-modal-content">
        <div class="error-modal-header">ERROR!</div>
        <p class="error-prompt">{{ this.deckValidationErrorMsg }}</p>
        <li class="error-modal-li" v-if="this.deckNameLengthError">
          {{ this.deckNameLengthErrorMsg }}
        </li>
        <li class="error-modal-li" v-if="this.deckCardCountError">
          {{ this.deckCardCountErrorMsg }}
        </li>
        <li class="error-modal-li" v-if="this.deckContractCountError">
          {{ this.deckContractCountErrorMsg }}
        </li>
        <button class="error-modal-btn" @click="hideValidationError">
          CLOSE
        </button>
      </div>
    </modal>

    <modal
      name="overwrite-warn-modal"
      v-if="showOverwriteWarningModal"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;"
    >
      <div class="error-modal-content">
        <div class="error-modal-header">WARNING!</div>
        <p class="error-prompt">{{ this.deckOverwritePrompt }}{{this.newDeck.name}}</p>
        <button class="success-modal-btn" @click="continueToSave">CONFIRM</button>
        <button class="success-modal-btn" @click="hideOverwriteWarningModal">CANCEL</button>
      </div>
    </modal>

    <modal
      name="submission-error-modal"
      v-if="showDeckSubmissionError"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;"
    >
      <div class="error-modal-content">
        <div class="error-modal-header">ERROR!</div>
        <p class="error-prompt">{{ this.deckSubmissionErrorMsg }}</p>
        <button class="error-modal-btn" @click="hideDeckSubmissionError">
          CLOSE
        </button>
      </div>
    </modal>

    <modal
      name="success-modal"
      v-if="showSuccess"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.8); border-radius: 10px; border: 2px solid black;"
    >
      <div class="error-modal-content">
        <div class="success-modal-header">SUCCESS!</div>
        <p class="error-prompt">Your new deck has been successfully saved!</p>
        <button class="success-modal-btn" @click="hideSuccess">CLOSE</button>
      </div>
    </modal>

    <modal
      name="import-modal"
      v-if="showImport"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;"
    >
      <div class="error-modal-content">
        <div class="success-modal-header">DECK IMPORT</div>
        <p class="error-prompt">
          Would you like to load your deck into the deck editor?
        </p>
        <button class="success-modal-btn" @click="updateDeckToEdit">YES</button>
        <button class="success-modal-btn" @click="hideImport">NO</button>
      </div>
    </modal>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import CardService from "@/services/CardService.js";
import card from "@/components/Card.vue";
import dbcardtile from "@/components/DeckBuilderCardTile.vue";
import contractcollection from "@/components/ContractCollection.vue";
import dbcontracttile from "@/components/DeckBuilderContractTile.vue";

export default {
  data() {
    return {
      allCards: [],
      allContracts: [],
      pageOfItems: [],
      cardsOrContracts: "cards",
      filter: "",
      search: "",
      newDeck: {
        deckID: null,
        cards: [],
        contracts: [],
        ownerUsername: this.$store.state.user.username,
        name: "",
        imageName: "",
        isFavorite: false,
      },
      max: 30,
      min: 3,
      myDecks: [],
      deckSubmissionError: false,
      deckSubmissionErrorMsg:
        "Something went wrong when submitting your deck. Please try again.",
      deckValidationError: false,
      deckValidationErrorMsg: "Please fix the following errors and try again: ",
      deckValidationErrorArray: [],
      deckNameLengthError: false,
      deckNameLengthErrorMsg:
        "The name of your deck must be at least 3 characters in length.",
      deckCardCountError: false,
      deckCardCountErrorMsg: "Your deck must contain at least 30 cards.",
      deckContractCountError: false,
      deckContractCountErrorMsg:
        "Your deck must contain at least 15 contracts.",
      deckOverwriteWarning: false,  
      deckOverwritePrompt: 'The following deck will be overwritten by this save: ',
    };
  },
  components: {
    draggable,
    card,
    dbcardtile,
    dbcontracttile,
    contractcollection,
  },
  methods: {
    getRandomIntInclusive(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1) + min);
    },
    validateDeck() {
      if (this.newDeck.name < 3) {
        this.deckNameLengthError = true;
      }
      if (this.computeCardDeckLength < 30) {
        this.deckCardCountError = true;
      }
      if (this.computeContractDeckLength < 15) {
        this.deckContractCountError = true;
      }
      if (
        this.deckContractCountError ||
        this.deckCardCountError ||
        this.deckNameLengthError
      ) {
        this.deckValidationError = true;
        this.showValidationError();
      }
      if (this.checkForOverwrite) {
        this.deckOverwriteWarning = true;
        this.showOverwriteWarningModal();
      }
      if (!this.deckValidationError && !this.deckOverwriteWarning) {
        this.saveDeck();
      }
    },
    saveDeck() {
      console.log(this.newDeck.deckID);
      CardService.addNewDeck(this.buildDeck()).then((response) => {
        if (response.status === 201) {
          this.showSuccess();
        } else {
          this.deckSubmissionError = true;
          this.showDeckSubmissionError();
        }
      });
    },
    buildDeck() {
      let newNewDeck = {
        deckID: this.newDeck.deckID,
        cards: [],
        contracts: [],
        ownerUsername: this.$store.state.user.username,
        name: this.newDeck.name,
        imageName: this.newDeck.cards[0].imageName,
        isFavorite: false,
      };
      for (var m = 0; m < this.newDeck.cards.length; m++) {
        for (var x = 0; x < this.newDeck.cards[m].count; x++) {
          newNewDeck.cards.push(this.newDeck.cards[m]);
        }
      }
      for (var d = 0; d < this.newDeck.contracts.length; d++) {
        for (var y = 0; y < this.newDeck.contracts[d].count; y++) {
          newNewDeck.contracts.push(this.newDeck.contracts[d]);
        }
      }
      return newNewDeck;
    },
    onRemoveRow(card) {
      for (var i = 0; i < this.newDeck.cards.length; i++) {
        if (card.id === this.newDeck.cards[i].id) {
          this.newDeck.cards.splice(i, 1);
          this.$store.commit(
            "UPDATE_NEW_DECK_CARD_COUNT",
            this.computeCardDeckLength
          );
        }
      }
    },
    deleteRowContract(contract) {
      for (var i = 0; i < this.newDeck.contracts.length; i++) {
        if (contract.id === this.newDeck.contracts[i].id) {
          this.newDeck.contracts.splice(i, 1);
          this.$store.commit(
            "UPDATE_NEW_DECK_CONTRACT_COUNT",
            this.computeContractDeckLength
          );
        }
      }
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
    handleAddedCard({ added }) {
      if (added) {
        if (this.$store.state.newDeckCardCount < 30) {
          for (let i = 0; i < this.newDeck.cards.length; i++) {
            if (this.newDeck.cards[i].id === added.element.id) {
              if (i != added.newIndex || this.newDeck.cards.length === 21) {
                this.newDeck.cards.splice(added.newIndex, 1);
              } else {
                this.$store.commit(
                  "UPDATE_NEW_DECK_CARD_COUNT",
                  this.computeCardDeckLength
                );
              }
            }
          }
        } else {
          this.newDeck.cards.splice(added.newIndex, 1);
        }
      }
    },
    handleAddedContract({ added }) {
      if (added) {
        if (this.$store.state.newDeckContractCount < 15) {
          for (let i = 0; i < this.newDeck.contracts.length; i++) {
            if (this.newDeck.contracts[i].id === added.element.id) {
              if (i != added.newIndex || this.newDeck.contracts.length === 21) {
                this.newDeck.contracts.splice(added.newIndex, 1);
              } else {
                this.$store.commit(
                  "UPDATE_NEW_DECK_CONTRACT_COUNT",
                  this.computeContractDeckLength
                );
              }
            }
          }
        } else {
          this.newDeck.contracts.splice(added.newIndex, 1);
        }
      }
    },
    showValidationError() {
      this.$modal.show("validation-error-modal");
    },
    hideValidationError() {
      this.$modal.hide("validation-error-modal");
    },
    showDeckSubmissionError() {
      this.$modal.show("submission-error-modal");
    },
    hideDeckSubmissionError() {
      this.$modal.hide("submission-error-modal");
    },
    showOverwriteWarningModal() {
      this.$modal.show("overwrite-warn-modal");
    },
    hideOverwriteWarningModal() {
      this.$modal.hide("overwrite-warn-modal");
    },
    showSuccess() {
      this.$modal.show("success-modal");
    },
    hideSuccess() {
      this.$modal.hide("success-modal");
      this.$router.push({ name: "my-decks" });
    },
    showImport() {
      this.$modal.show("import-modal");
    },
    hideImport() {
      let deckReset = {};
      this.$store.commit("SET_DECK_TO_EDIT", deckReset);
      this.$modal.hide("import-modal");
    },
    updateDeckToEdit() {
      this.loadDeckToEdit();
    },
    loadDeckToEdit() {
      this.newDeck = this.$store.state.deckToEdit;
      let deckReset = {};
      this.$store.commit("SET_DECK_TO_EDIT", deckReset);
      this.$modal.hide("import-modal");
    },
    continueToSave() {
      this.$modal.hide("overwrite-warn-modal");
      this.deckOverwriteWarning = false;
      this.saveDeck();
    }
  },
  computed: {
    computeCardDeckLength() {
      let total = 0;
      for (var i = 0; i < this.newDeck.cards.length; i++) {
        total += this.newDeck.cards[i].count;
      }
      return total;
    },
    computeContractDeckLength() {
      let total = 0;
      for (var i = 0; i < this.newDeck.contracts.length; i++) {
        total += this.newDeck.contracts[i].count;
      }
      return total;
    },
    checkForOverwrite() {
      for (var y = 0; y < this.myDecks.length; y++) {
        if (this.myDecks[y].name === this.newDeck.name) {
          return true;
        }
      }
      return false;
    },
    filteredList() {
      if (this.cardsOrContracts === "cards") {
        return this.allCards.filter((card) => {
          if (this.filter === "") {
            return card.name.toLowerCase().includes(this.search.toLowerCase());
          }
          if (this.filter === "name") {
            return card.name.toLowerCase().includes(this.search.toLowerCase());
          }
          if (this.filter === "cost") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.cost == this.search;
          }
          if (this.filter === "description") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            if (card.abilities != null) {
              return card.abilities
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
          }
          if (this.filter === "attack") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.attack == this.search;
          }
          if (this.filter === "resilience") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.resilience == this.search;
          }
          if (this.filter === "loyalty") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.loyalty == this.search;
          }
        });
      } else if (this.cardsOrContracts === "contracts") {
        return this.allContracts.filter((contract) => {
          if (this.filter === "") {
            return contract.name
              .toLowerCase()
              .includes(this.search.toLowerCase());
          }
          if (this.filter === "name") {
            return contract.name
              .toLowerCase()
              .includes(this.search.toLowerCase());
          }
          if (this.filter === "cost") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return contract.cost == this.search;
          }
          if (this.filter === "description") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            if (contract.desc != null) {
              return contract.desc
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
          }
          if (this.filter === "duration") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return contract.dura == this.search;
          }
        });
      } else return null;
    },
  },
  created() {
    CardService.getAllCards().then((response) => {
      this.allCards = response.data;
    });
    CardService.getAllContracts().then((response) => {
      this.allContracts = response.data;
    });
    CardService.getDecksByUsername(this.$store.state.user.username).then(
      (response) => {
        this.myDecks = response.data;
      }
    );
  },
  mounted() {
    if (this.$store.state.deckToEdit != null) {
      this.showImport();
    }
  },
};
</script>

<style>
.ghosties {
  height: 180px;
  position: relative;
}
.deck-builder {
  background-image: url("../assets/backgrounds/ships.jpg");
  background-size: 1920px 1060px;
  display: grid;
  grid-template-areas:
    "cards-added contracts-added filters"
    "cards-added contracts-added filters"
    "cards-added contracts-added browser"
    "deck-info deck-info browser";
  grid-template-rows: 50px 40px 770px 50px;
  grid-template-columns: 595px 595px 710px;
  column-gap: 10px;
  row-gap: 10px;
  height: 1003px;
}
.deck-cards-grid {
  grid-area: cards-added;
  background-color: rgba(0, 0, 0, 0.733);
  border: 1px solid black;
  border-radius: 10px;
  height: 100%;
  width: 100%;
  margin: 10px;
}
.db-cards-grid-header {
  text-align: center;
  color: white;
  font-size: 30px;
  font-family: "Bai Jamjuree";
  letter-spacing: 20px;
  font-weight: 700;
  padding: 2px;
  border-bottom: 1px solid rgba(170, 170, 170, 0.507);
  background: rgba(25, 124, 111, 0.597);
}
.cards-added {
  padding-top: 5px;
  height: 800px;
  width: 570px;
  display: flex;
  flex-direction: column;
}
.deck-contracts-grid {
  grid-area: contracts-added;
  background-color: rgba(0, 0, 0, 0.733);
  border: 1px solid black;
  border-radius: 10px;
  margin: 10px;
  height: 100%;
  width: 100%;
}
.db-contracts-grid-header {
  text-align: center;
  color: white;
  font-size: 30px;
  font-family: "Bai Jamjuree";
  letter-spacing: 20px;
  font-weight: 700;
  padding: 2px;
  border-bottom: 1px solid rgba(170, 170, 170, 0.507);
  background: rgba(68, 25, 124, 0.597);
}
.contracts-added {
  padding-top: 5px;
  height: 800px;
  width: 400px;
  display: flex;
  flex-direction: column;
}
.selection-area {
  grid-area: browser;
  width: auto;
  height: auto;
  background-color: rgba(0, 0, 0, 0.733);
  border: 1px solid black;
  border-radius: 10px;
  margin: 20px;
}
.card-pool {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-items: center;
  gap: 10px;
  width: 95%;
  height: 100%;
  padding: 20px;
}
.contract-pool {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  gap: 5px;
  width: auto;
  height: auto;
  padding: 10px;
}
.pagination {
  margin: 0px;
  padding: 10px;
  gap: 20px;
}
.pagination li {
  font-size: 17px;
  font-family: "Bai Jamjuree";
}
.db-stats {
  grid-area: deck-info;
  height: 100%;
  width: 98.5%;
  padding: 10px;
  margin: 10px;
  background-color: rgba(0, 0, 0, 0.733);
  border: 1px solid black;
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
}
.deck-name-control {
  margin: 10px;
  height: 25px;
  border-radius: 5px;
  width: 300px;
}
.deck-name-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  padding: 10px;
}
.db-radio-filters {
  grid-area: filters;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 91%;
  height: 100%;
  margin-left: 20px;
  margin-top: 10px;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.733);
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 15px;
  border: 1px solid grey;
  border-radius: 8px;
}
.db-radio-filters-filters {
  display: flex;
  flex-direction: row;
  height: 40px;
  gap: 2px;
}
.db-filters-container {
  display: flex;
  flex-direction: row;
  gap: 5px;
  flex-wrap: wrap;
}
.cards-or-conts-filters {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 22px;
  gap: 20px;
  width: auto;
  margin: 0px 20px 0px 5px;
}
.align-radio-selectors {
  display: flex;
  flex-direction: row;
}
.db-search-input {
  padding-left: 10px;
  display: flex;
}
.db-input {
  border-radius: 5px;
  background: rgba(41, 41, 41, 0.733);
  border: 1px solid white;
  color: white;
  font-family: "Bai Jamjuree";
  height: 30px;
  font-size: 20px;
}
.db-input-name {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  background: rgba(41, 41, 41, 0.733);
  border: 1px solid white;
  color: white;
  font-family: "Bai Jamjuree";
  height: 30px;
  font-size: 20px;
  width: 400px;
}
.db-card-count {
  font-family: "Bai Jamjuree";
  font-weight: 700;
  color: white;
  font-size: 28px;
  display: flex;
}
.running-card-count {
  margin-left: 20px;
}
.input-count {
  font-family: "Bai Jamjuree";
  color: white;
  font-weight: 700;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  font-size: 20px;
  background-color: rgb(12, 8, 61);
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  border-top: 1px solid white;
  border-right: 1px solid white;
  border-bottom: 1px solid white;
  width: 40px;
  height: 32px;
}

.success-modal-header {
  font-family: "Orbitron";
  font-size: 50px;
  font-weight: 700;
  color: rgb(2, 141, 83);
  text-shadow: 1px 1px rgb(184, 255, 219);
}
.success-modal-btn {
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
  margin: 10px 10px 2px 0px;
}
.success-modal-btn:hover {
  cursor: pointer;
  background-color: rgb(2, 141, 83);
}
.error-modal-content {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  text-align: center;
}
.error-modal-header {
  font-family: "Orbitron";
  font-size: 50px;
  font-weight: 700;
  color: rgb(219, 39, 39);
  text-shadow: 1px 2px rgb(245, 175, 175);
}
.error-prompt {
  color: white;
  font-family: "Bai Jamjuree";
  font-size: 20px;
  margin-top: 20px;
}
.error-modal-li {
  font-style: italic;
  font-family: "Bai Jamjuree";
  font-size: 15px;
  color: rgb(228, 88, 88);
}
.error-modal-btn {
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
  margin: 10px 10px 2px 0px;
}
.error-modal-btn:hover {
  cursor: pointer;
  background-color: rgba(190, 0, 0, 0.9);
}
</style>
