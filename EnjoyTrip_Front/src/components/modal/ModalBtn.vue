<script setup>
defineProps({ text: String });
</script>

<template>
  <button
    class="mt-3 w-full h-9 border-[1px] border-slate-950 bg-[#363636] rounded-2xl flex justify-center items-center disabled:bg-[#8e8e8e]"
  >
    <span class="gradient gradientText font-semibold">{{ text }}</span>
  </button>
</template>

<style scoped>
.gradient {
  background: linear-gradient(180deg, #cde5f3, #ffffff);
}
.gradientText {
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}
</style>
