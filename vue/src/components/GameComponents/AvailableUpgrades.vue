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
import upgrade from "@/components/GameComponents/Upgrade.vue";

export default {
    name: "item-row",
    components: {
        upgrade
    }
}
</script>

<style>
.row-reroll {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    align-content: center;
    padding: 2px;
}

.reroll-cost {
    color: rgb(255, 255, 255);
    font-weight: 700;
    font-family: 'Orbitron', sans-serif;
    font-size: 18px;
    background-image: linear-gradient(rgba(2, 2, 2, 0.678), rgba(0, 0, 0, 0.664));
    border-radius: 5px;
    border: 1px solid rgba(176, 199, 255, 0.514);
    background-size: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    letter-spacing: 4px;
    height: 30px;
    width: 120px;
    transition: all 0.2s ease-in-out;
}

.reroll-cost:hover {
    background-color: rgb(8, 84, 224);
    transform: scale(1.16);
}

.icon-reroll {
    color: white;
    font-size: 20px;
}

.icon-reroll:hover {
    color: rgb(0, 115, 223);
    animation: spin 1s infinite linear;
}

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