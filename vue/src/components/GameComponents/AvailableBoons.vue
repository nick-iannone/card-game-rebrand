<template>
    <div class="item-row-cont">
        <div class="shop-row-header">
            BOONS
        </div>
        <draggable class="shop-item-row" :options="{ group: 'boons' }">
            <boon />
            <boon />
            <boon />
            <boon />
        </draggable>
        <div class="row-me">
            <div class="row-reroll">
                <div class="reroll-cost">
                    3 <font-awesome-icon :icon="['fa', 'coins']" class="icon" color="yellow" />&nbsp;:&nbsp;
                    <font-awesome-icon :icon="['fa', 'spinner']" class="icon-reroll" />
                </div>
            </div>
            <div class="row-reroll">
                <div class="reroll-cost">
                    4 <font-awesome-icon :icon="['fa', 'coins']" class="icon" color="yellow" />&nbsp;:&nbsp;
                    <font-awesome-icon :icon="['fa', 'question']" class="icon-reroll" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import boon from "@/components/GameComponents/Boon.vue";
import draggable from "vuedraggable";

export default {
    name: "item-row",
    components: {
        boon,
        draggable
    }
}
</script>

<style>
</style>