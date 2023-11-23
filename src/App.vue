<script setup>
import { onMounted } from "vue";
import { RouterView } from "vue-router";
import { addMessage } from "@/util/message";
onMounted(() => {
  addMessage("123");
});
</script>

<template>
  <div class="grid grid-cols-12">
    <router-view></router-view>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "NEXON Lv2 Gothic";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

* {
  font-family: "NEXON Lv2 Gothic";
}
</style>
