<template>
    <div class="item-row-cont">
        <div class="shop-row-header">
            BOONS
        </div>
        <div class="shop-item-row">
            <boon />
            <boon />
            <boon />
            <boon />
        </div>
    </div>
</template>

<script>
import boon from "@/components/GameComponents/Boon.vue";

export default {
    name: "item-row",
    components: {
        boon
    }
}
</script>

<style>
</style>