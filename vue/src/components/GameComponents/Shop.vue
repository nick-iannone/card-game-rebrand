<template>
  <div class="shop">
    <div class="shop-header">STOREFRONT</div>
    <itemrow />
    <itemrow />
    <shopcards />
  </div>
</template>

<script>
import itemrow from "@/components/GameComponents/ItemRow.vue";
import shopcards from "@/components/GameComponents/ShopCards.vue";

export default {
    components: {
        itemrow,
        shopcards
    },
}
</script>

<style>
.shop {
    grid-area: "shop";

    /* display: grid; */
    width: 350px;
    height: 854px;
    background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.825), rgba(38, 2, 85, 0.797), rgba(0, 0, 0, 0.808));
    border: 1px solid rgba(71, 71, 71, 0.345);
    border-radius: 5px;
    margin: 5px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
}
.shop-header {
    color: white;
    font-size: 28px;
    font-weight: 700;
    font-family: 'Orbitron', sans-serif;
    padding: 5px 13px 3px 13px;
    text-align: center;
    letter-spacing: 10px;
    text-shadow: 1px 3px rgba(139, 64, 252, 0.813);
}

</style>