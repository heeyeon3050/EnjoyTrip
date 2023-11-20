<script setup>
defineProps({ type: String, label: String, value: String, error: Boolean });
</script>

<template>
  <div
    :class="{ error: error }"
    class="w-full h-9 border-[1px] border-slate-950 rounded-2xl flex"
  >
    <span
      :class="{ error: error }"
      class="font-semibold w-24 border-r-[1px] border-slate-950 flex items-center justify-center text-sm"
      >{{ label }}</span
    >
    <input
      :type="type"
      :value="value"
      @change="$emit('input', this.value)"
      class="ml-2 w-44 bg-transparent outline-none"
    />
  </div>
</template>

<style scoped>
.error {
  border-color: red !important;
}
</style>
