<script setup>
const params = defineProps({ board: Object });
</script>

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
        <router-link
          :to="{
            name: 'board-view',
            params: { boardId: params.board.id },
          }"
        >
          <h1 class="text-xl font-semibold">{{ params.board.title }} [8]</h1>
        </router-link>
        <h4 class="text-sm text-slate-500">{{ params.board.category }}</h4>
      </div>
      <div class="w-2/5 h-full flex justify-around items-center">
        <div>{{ params.board.writer }}</div>
        <div class="font-semibold">{{ params.board.createdAt }}</div>
        <div class="font-semibold">{{ params.board.board_users.length }}</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
