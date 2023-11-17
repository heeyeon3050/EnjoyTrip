<script setup>
import HeadingNavbar from "@/components/layout/HeadingNavbar.vue";
</script>

<template>
  <HeadingNavbar />
  <div class="col-start-3 col-span-8">
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
