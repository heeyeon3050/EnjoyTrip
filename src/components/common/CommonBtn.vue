<script setup>
defineProps({ text: String });
</script>

<template>
  <button
    class="bg-slate-300 text-sm font-semibold w-16 h-8 rounded-md p-2 flex justify-center items-center shadow-md text-slate-800 disabled:text-slate-400 disabled:bg-slate-200"
  >
    {{ text }}
  </button>
</template>

<style scoped></style>
