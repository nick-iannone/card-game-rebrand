<template>
    <div class="item-row-cont">
        <div class="shop-row-header">
            UPGRADES
        </div>
        <div class="shop-item-row">
            <upgrade />
            <upgrade />
            <upgrade />
            <upgrade />
        </div>
    </div>
</template>

<script>
import upgrade from "@/components/GameComponents/Upgrade.vue";

export default {
    name: "item-row",
    components: {
        upgrade
    }
}
</script>

<style>
.item-row-cont {
    display: flex;
    flex-direction: column;
    width: 100%;
    text-align: center;
    justify-content: center;
    align-items: center;
}
.shop-item-row {
    background-image: linear-gradient(90deg, rgba(206, 206, 206, 0.244), rgba(0, 18, 29, 0.474), rgba(206, 206, 206, 0.244));
    border: 1px solid rgba(255, 255, 255, 0.091);
    border-radius: 5px;
    height: 72px;
    width: 96%;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    padding: 4px;
}
.shop-row-header {
    font-size: 18px;
    font-family: 'Orbitron', sans-serif;
    font-weight: 700;
    color: white;
    margin-bottom: 5px;
    letter-spacing: 15px;
    border: 1px solid rgba(255, 255, 255, 0.17);
    border-radius: 5px;
    padding: 4px 4px 4px 10px;
    width: 92%;
    background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.649), rgba(44, 0, 78, 0.474), rgba(0, 0, 0, 0.649));

}
</style>