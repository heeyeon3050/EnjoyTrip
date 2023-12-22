<script setup>
defineProps({ text: String });
</script>

<template>
  <button
    class="myeongjo text-sm font-semibold w-16 h-8 p-2 flex justify-center items-center text-slate-200 disabled:text-slate-400"
  >
    {{ text }}
  </button>
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanummyeongjo.css);

.myeongjo {
  position: relative;
  font-family: "Nanum Myeongjo", serif;
}

.myeongjo::after {
  content: "";
  display: block;
  position: absolute;
  height: 1px;
  width: 0;
  left: 10%;
  background-color: #eee;
  transition: width 0.3s ease-in-out;
  bottom: 0;
}

.myeongjo:disabled::after {
  width: 80%;
}
.myeongjo:hover::after {
  width: 80%;
}
</style>
