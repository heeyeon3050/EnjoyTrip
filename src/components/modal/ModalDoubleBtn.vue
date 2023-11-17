<script setup>
defineProps({ leftText: String, rightText: String });
</script>

<template>
  <div
    class="w-full h-9 border-[1px] border-slate-950 bg-[#363636] rounded-2xl flex"
  >
    <div
      class="cursor-pointer h-full w-1/2 flex justify-center items-center gradient gradientText font-semibold border-r-[1px] border-slate-950"
    >
      {{ leftText }}
    </div>
    <div
      class="cursor-pointer h-full w-1/2 flex justify-center items-center gradient gradientText font-semibold"
    >
      {{ rightText }}
    </div>
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
