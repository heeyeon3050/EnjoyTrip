<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
</script>

<template>
  <div class="w-full space-y-20">
    <div class="w-full flex justify-between p-4 my-10">
      <div class="w-auto flex space-x-12">
        <div class="w-32 h-32 bg-slate-700 rounded-full"></div>
        <div class="flex flex-col space-y-4 justify-center">
          <h1 class="text-5xl font-semibold">이유로</h1>
          <h3 class="text-lg text-slate-400">@eurohand</h3>
        </div>
      </div>
      <div class="flex items-end space-x-2">
        <router-link
          :to="{
            name: 'user-update',
            params: { userId: 'ssafy' },
          }"
        >
          <CommonBtn text="수정" />
        </router-link>
        <CommonBtn text="탈퇴" />
      </div>
    </div>
    <div class="w-full space-y-2">
      <div class="w-full h-10 border-b-2 border-slate-300">
        <h1 class="text-2xl text-slate-500 px-2">저장한 관광지</h1>
      </div>
      <div class="w-full grid grid-cols-6 gap-2">
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
      </div>
    </div>
    <div class="w-full space-y-2">
      <div class="w-full h-10 border-b-2 border-slate-300">
        <h1 class="text-2xl text-slate-500 px-2">좋아요한 게시물</h1>
      </div>
      <div class="w-full grid grid-cols-6 gap-2">
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
        <img
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
