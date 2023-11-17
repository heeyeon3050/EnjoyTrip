<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import BoardListItem from "@/components/boards/BoardListItem.vue";
</script>

<template>
  <div
    class="w-full px-3 flex space-x-2 py-10 mb-4 border-b-2 border-b-slate-400"
  >
    <CommonBtn text="전체" />
    <CommonBtn text="인기" />
    <CommonBtn text="여행 팁" />
    <CommonBtn text="후기" />
  </div>
  <div class="w-full h-fit p-2 flex flex-col overflow-y-scroll scrollbar-hide">
    <div>
      <BoardListItem v-for="i in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]" :key="i" />
    </div>
  </div>
  <div class="w-full h-10 my-10">
    <select
      name="languages"
      id="lang"
      class="w-24 h-10 border-2 mr-3 rounded-md px-3 border-slate-500"
    >
      <option value="title">제목</option>
      <option value="writer">작성자</option>
    </select>
    <input
      type="text"
      class="w-48 h-10 border-2 rounded-md p-2 border-slate-500"
    />
  </div>
  <div class="w-full h-10 my-10 flex space-x-4 justify-center">
    <button>&lt;</button>
    <button>&lt;&lt;</button>
    <button>1</button>
    <button>2</button>
    <button>3</button>
    <button>4</button>
    <button>5</button>
    <button>&gt;</button>
    <button>&gt;&gt;</button>
  </div>
</template>

<style scoped></style>
