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
                    type: "Troop - Human",
                    image: "soldierguy.jpeg",
                    text1: "Recyclable",
                    text2: "When you recycle this, give a friendly troop +1/+1.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 2,
                    resilience: 2,
                },
                {
                    id: 2,
                    name: "Wee Beastie",
                    cost: 1,
                    type: "Troop - Organic",
                    image: "unmanaged_organic.jpg",
                    text1: "Recyclable",
                    text2: "When this attacks, it gets +1 Attack.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 1,
                },
                {
                    id: 3,
                    name: "Thessa, the Helping Hand",
                    cost: 3,
                    type: "Troop - Hybrid",
                    image: "thessa.jpg",
                    text1: "Recyclable",
                    text2: "When this enters, give +3 Attack.",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 2,
                    resilience: 3,
                },
                {
                    id: 4,
                    name: "Scout",
                    cost: 2,
                    type: "Troop - Robot",
                    image: "scout.jpg",
                    text1: "Recyclable",
                    text2: "Readiness",
                    text3: "When this enters the CZ, draw.",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 1,
                },
                {
                    id: 5,
                    name: "Alpha Thrall",
                    cost: 5,
                    type: "Troop - Deepened",
                    image: "alpha_thrall.jpg",
                    text1: "Readiness",
                    text2: "Invulnerable while you control another Thrall.",
                    text3: "When this dies, shuffle it into your deck instead.",
                    text4: "",
                    flavor: "",
                    attack: 3,
                    resilience: 4,
                },
                {
                    id: 6,
                    name: "AXA-99",
                    cost: 4,
                    type: "Troop - Hybrid",
                    image: "axa_99.jpg",
                    text1: "Your upgrades and non-troop cards cost 1 less.",
                    text2: "",
                    text3: "",
                    text4: "",
                    flavor: "",
                    attack: 1,
                    resilience: 6,
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