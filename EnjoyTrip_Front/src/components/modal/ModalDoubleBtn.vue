<script setup>
defineProps({
  leftText: String,
  rightText: String,
  leftClick: Function,
  rightClick: Function,
});
</script>

<template>
  <div
    class="w-full h-9 border-[1px] border-slate-950 bg-[#363636] rounded-2xl flex"
  >
    <button
      class="cursor-pointer h-full w-1/2 flex justify-center items-center gradient gradientText font-semibold border-r-[1px] border-slate-950"
      @click="leftClick"
    >
      {{ leftText }}
    </button>
    <button
      class="cursor-pointer h-full w-1/2 flex justify-center items-center gradient gradientText font-semibold"
      @click="rightClick"
    >
      {{ rightText }}
    </button>
  </div>
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
