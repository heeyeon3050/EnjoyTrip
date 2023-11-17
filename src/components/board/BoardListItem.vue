<script setup></script>

<template>
  <div
    class="w-full h-28 p-2 flex relative first:border-t-0 border-t-[1px] border-[#bbb]"
  >
    <img
      class="h-full aspect-square mr-3"
      src="@/assets/attraction.jpg"
      alt=""
    />
    <div class="w-full flex">
      <div
        class="w-3/5 h-full flex flex-col items-start justify-center space-y-2"
      >
        <h1 class="text-xl font-semibold">인기 게시글 제목 [8]</h1>
        <h4 class="text-sm text-slate-500">카테고리</h4>
      </div>
      <div class="w-2/5 h-full flex justify-around items-center">
        <div>작성자</div>
        <div class="font-semibold">11/15</div>
        <div class="font-semibold">15</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
