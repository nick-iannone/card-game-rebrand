<template>
    <div class="shop-cards">
        <div class="shop-row-header">
            CARDS
        </div>
        <shopcardpopup :card="card" :key="card.id" v-for="card in this.neutralCards" />
    </div>
</template>

<script>
import shopcardpopup from "../GameComponents/ShopCardPopup.vue";

export default {
    components: {
        shopcardpopup
    },
    data() {
        return {
            neutralCards: [
                {
                    id: 1,
                    name: "Guy Soldier",
                    cost: 2,
                    type: "Troop",
                    faction: "Human",
                    image: "soldierguy.jpeg",
                    text1: "Recyclable",
                    text2: "When you recycle this, give a friendly troop +1/+1.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 2,
                    resilience: 2,
                    mod1: true,
                    mod2: true,
                    mod3: false,
                    mod4: false,
                },
                {
                    id: 2,
                    name: "Wee Beastie",
                    cost: 1,
                    type: "Troop",
                    faction: "Organic",
                    image: "unmanaged_organic.jpg",
                    text1: "Recyclable",
                    text2: "When this attacks, it gets +1 Attack.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 1,
                    mod1: false,
                    mod2: false,
                    mod3: false,
                    mod4: false,
                },
                {
                    id: 3,
                    name: "Thessa, the Helping Hand",
                    cost: 3,
                    type: "Troop",
                    faction: "Hybrid",
                    image: "thessa.jpg",
                    text1: "Recyclable",
                    text2: "When this enters, give +3 Attack.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 2,
                    resilience: 3,
                    mod1: true,
                    mod2: true,
                    mod3: true,
                    mod4: true,
                },
                {
                    id: 4,
                    name: "Scout",
                    cost: 2,
                    type: "Troop",
                    faction: "Robot",
                    image: "scout.jpg",
                    text1: "Recyclable",
                    text2: "Readiness",
                    text3: "When this enters, draw.",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 1,
                    mod1: false,
                    mod2: true,
                    mod3: false,
                    mod4: false,
                },
                {
                    id: 5,
                    name: "Alpha Thrall",
                    cost: 5,
                    type: "Troop",
                    faction: "Deepened",
                    image: "alpha_thrall.jpg",
                    text1: "Readiness",
                    text2: "Invulnerable while you control another Thrall.",
                    text3: "When this leaves, shuffle it into your deck instead.",
                    text4: "",
                    flavor: "",
                    attack: 3,
                    resilience: 4,
                    mod1: true,
                    mod2: true,
                    mod3: true,
                    mod4: true,
                },
                {
                    id: 6,
                    name: "AXA-99",
                    cost: 4,
                    type: "Troop",
                    faction: "Hybrid",
                    image: "axa_99.jpg",
                    text1: "Your upgrades, boons, and non-troop cards cost [1] less.",
                    text2: "",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 6,
                    mod1: true,
                    mod2: true,
                    mod3: true,
                    mod4: true,
                },
                {
                    id: 12,
                    name: "Credit Card",
                    cost: 0,
                    type: "Credit",
                    faction: null,
                    image: "credit_basic.jpg",
                    text1: "Recyclable",
                    text2: "Add [1]",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: null,
                    resilience: null,
                    mod1: true,
                    mod2: true,
                    mod3: false,
                    mod4: false,
                },

            ]
        }
    },
}
</script>

<style>
.shop-cards {
    width: 100%;
    height: 400px;
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
}
</style>