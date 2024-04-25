<template>
    <div class="shop-cards" >
        <div class="shop-cards-neutral"></div>
        <div class="shop-cards-faction"></div>
    </div>
</template>

<script>
export default {

}
</script>

<style>
.shop-cards {
    width: 100%;
}
.shop-cards-neutral {
    background-image: linear-gradient(45deg, rgba(0, 0, 0, 0.491), rgba(0, 18, 29, 0.474));
    border: 1px solid rgba(255, 255, 255, 0.091);
    border-radius: 5px;
    padding: 5px;
    margin: 5px;
    height: 320px;
    width: 94%;
}
.shop-cards-faction {
    background-image: linear-gradient(45deg, rgba(0, 0, 0, 0.491), rgba(0, 18, 29, 0.474));
    border: 1px solid rgba(255, 255, 255, 0.091);
    border-radius: 5px;
    padding: 5px;
    margin: 5px;
    height: 120px;
    width: 94%;
}
</style>