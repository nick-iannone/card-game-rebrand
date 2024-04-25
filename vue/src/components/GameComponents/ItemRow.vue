<template>
    <div class="item-row-cont">
        <div class="shop-row-header">
            UPGRADES
        </div>
        <div class="shop-item-row">
        </div>
    </div>
</template>

<script>
export default {

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
    background-image: linear-gradient(45deg, rgba(0, 0, 0, 0.491), rgba(0, 18, 29, 0.474));
    border: 1px solid rgba(255, 255, 255, 0.091);
    border-radius: 5px;
    height: 70px;
    width: 96%;
}
.shop-row-header {
    font-size: 18px;
    font-family: 'Orbitron', sans-serif;
    color: white;
    margin-bottom: 5px;
}
</style>