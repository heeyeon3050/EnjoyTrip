<script setup>
import HeadingNavbar from "@/components/layout/HeadingNavbar.vue";
import Footer from "@/components/layout/Footer.vue";
</script>

<template>
  <HeadingNavbar />
  <div class="col-start-3 col-span-8">
    <router-view></router-view>
  </div>
  <div class="col-span-12">
    <Footer />
  </div>
</template>

<style scoped></style>
