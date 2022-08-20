<template>
  <div>
    <chat v-show="showChat" />
    <div id="nav-bar">
      <div
        class="chat-icon-container"
        v-show="this.$store.state.token != '' && this.$route.name != 'arena'"
      >
      <font-awesome-icon class='chat-icon' :icon="['fas', 'comment-alt-lines']" v-if="!this.showChat" color="seagreen" @click.prevent="toggleShowChat" />
      <font-awesome-icon class='chat-icon' :icon="['fas', 'comment-alt-slash']" v-if="this.showChat" color="crimson" @click.prevent="toggleShowChat" />
      <font-awesome-icon class='chat-icon' :icon="['fas', 'comment-alt-exclamation']" v-if="this.unread" colo="yellow" /> 
      </div>
      <button
        class="nav-button"
        @click="goToLogin"
        v-if="this.$store.state.token === ''"
      >
        LOGIN
      </button>
      <button
        class="nav-button"
        @click="goToCollection"
        v-if="this.$store.state.token != '' && this.$route.name != 'browse'"
      >
        BROWSE
      </button>
      <button
        class="nav-button"
        @click="goToMyDecks"
        v-if="$store.state.token != '' && this.$route.name != 'my-decks'"
      >
        MY DECKS
      </button>
      <button
        class="nav-button"
        @click="goToDeckBuilder"
        v-if="$store.state.token != '' && this.$route.name != 'deck-builder'"
      >
        DECK BUILDER
      </button>
      <button
        class="nav-button"
        @click.prevent="sendToHome"
        v-if="this.$route.name != 'home'"
      >
        MAIN MENU
      </button>
      <font-awesome-icon
        :icon="['fas', 'sign-out-alt']"
        class="sign-out-icon"
        @click="logout"
        v-if="$store.state.token != ''"
      />
    </div>
  </div>
</template>

<script>
import Chat from "@/components/Chat.vue";

export default {
  name: "the-header",
  components: {
    Chat,
  },
  data() {
    return {
      showChat: false,
      unread: false
    };
  },
  methods: {
    unreadMessage() {
      this.unread = true;
    },
    toggleShowChat() {
      if (!this.showChat) {
        this.showChat = true;
      } else {
        this.showChat = false;
      }
    },
    goToArena() {
      this.$router.push({ name: "arena" });
    },
    goToLogin() {
      this.$router.push({ name: "login" });
    },
    goToCollection() {
      this.$router.push({ name: "browse" });
    },
    goToMyDecks() {
      this.$router.push({ name: "my-decks" });
    },
    goToDeckBuilder() {
      this.$router.push({ name: "deck-builder" });
    },
    sendToHome() {
      this.$router.push({ name: "home" });
    },
    logout() {
      this.$router.push({ name: "logout" });
    },
  },
};
</script>

<style>
#nav-bar {
  background-color: black;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 10px;
  height: 40px;
}
.nav-button {
  margin: 0px 15px 0px 5px;
  background-color: black;
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  width: 180px;
  height: 40px;
  font-family: 'Orbitron', sans-serif;
  font-size: 18px;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
  text-shadow: 1px 2px rgb(51, 51, 51);
}
.nav-button:hover {
  transform: scale(1.03);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../assets/textures/egg-shell.png"),
    linear-gradient(180deg, rgba(37, 122, 226, 0.8), rgba(0, 0, 105, 0.8));
}
.chat-icon-container {
  margin: 0px 15px 0px 0px;
  width: 140px;
  height: 40px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.chat-icon {
  font-size: 35px;
  transition: all 0.2s ease-in-out;
}
.chat-icon:hover {
  cursor: pointer;
  transform: scale(1.2);
}
.sign-out-icon {
  font-size: 40px;
  color: white;
  transition: all 0.2s ease-in-out;
}
.sign-out-icon:hover {
  cursor: pointer;
  color: rgb(240, 52, 52);
  transform: scale(1.2);
}
</style>
