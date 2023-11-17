<script setup>
import BrowserSingleView from "@/components/browser/BrowserSingleView.vue";
import BrowserMultiView from "@/components/browser/BrowserMultiView.vue";
import { ref } from "vue";

const isSingleView = ref(true);
</script>

<template>
  <div class="col-start-3 col-span-8">
    <div class="w-full flex justify-end mb-3">
      <input
        type="checkbox"
        class="m-3"
        v-model="isSingleView"
        @click="isSingleView = !isSingleView"
      />
    </div>
    <template v-if="isSingleView">
      <BrowserSingleView />
    </template>
    <template v-else>
      <BrowserMultiView />
    </template>
  </div>
</template>

<style scoped></style>
