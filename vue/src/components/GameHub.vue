<template>
  <div class="hub-container">
    <div class="slide-cont">
      <div id="list-complete-demo" class="demo">
        <transition-group name="list-complete" class="datas">
          <div v-for="message in items" v-bind:key="message.charAt(0)" class="list-complete-item">
            <font-awesome-icon :icon="['fal', 'caret-circle-right']" class="feed-icon" />
            <div class="feed-text">
              <div>{{ message.substring(1) }}</div>
            </div>
          </div>
        </transition-group>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "game-hub",
  data() {
    return {
      items: ["9Setting up your game..."],
      id: 0,
    };
  },
  methods: {
    add(newMessage) {
      this.items.unshift(newMessage);
    },
    remove() {
      this.items.pop();
    },
    rotate(newMessage) {
      if (newMessage != "") {
        if (this.items.length < 8) {
          let prependId = this.id.toString();
          newMessage = prependId + newMessage;
          this.id++;
          if (this.id == 4) {
            this.id = 0;
          }
          this.add(newMessage);
        } else {
          this.remove();
          let prependId = this.id.toString();
          newMessage = prependId + newMessage;
          this.id++;
          if (this.id == 4) {
            this.id = 0;
          }
          this.add(newMessage);
        }
      }
    },
  },
};
</script>

<style>
.hub-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 40%;
  height: 100%;
  background-color: rgba(0, 8, 31, 0.534);
  padding-bottom: 3px;
  padding-left: 2px;
  border-radius: 5px;
}

.slide-cont {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.tran-group {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.demo {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.centered {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.feed-icon {
  font-size: 26px;
  padding: 0px 10px 0px 0px;
  margin-top: -2px;
}

.feed-text {
  display: inline;
  vertical-align: middle;
}

.list-complete-item {
  transition: all 0.5s;
  vertical-align: middle;
  display: inline-flex;
  margin-right: 20px;
  margin-top: 3px;
  color: rgb(255, 255, 255);
  font-family: "Bai Jamjuree";
  font-size: 15px;
  background-color: rgba(5, 5, 5, 0.863);
  border: 1px solid rgba(194, 194, 194, 0.616);
  border-radius: 8px;
  padding: 5px 10px 5px 5px;
  width: 22px;
  height: 24px;
  overflow: hidden;
}

.list-complete-item:hover {
  width: auto;
  overflow: visible;
  height: auto;
  width: auto;
  z-index: 100;
  position: relative;
}

.list-complete-enter,
.list-complete-leave-to

/* .list-complete-leave-active below version 2.1.8 */
  {
  opacity: 0;
  transform: translateY(30px);
}

.list-complete-leave-active {
  position: absolute;
}
</style>
