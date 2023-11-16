<script setup>
defineProps({ type: String, label: String });
</script>

<template>
  <div class="w-72 h-8 border-[1px] border-slate-950 rounded-2xl flex">
    <span
      class="font-semibold px-3 w-24 border-r-[1px] border-slate-950 flex items-center justify-center text-sm"
      >{{ label }}</span
    >
    <input :type="type" class="ml-2 w-44 bg-transparent outline-none" />
  </div>
</template>

<style scoped></style>
