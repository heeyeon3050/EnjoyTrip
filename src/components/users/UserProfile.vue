<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import noProfile from "@/assets/no_profile.png";

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};

const memberStore = useMemberStore();

const { userInfo } = storeToRefs(memberStore);
</script>

<template>
  <div class="w-full space-y-20">
    <div class="w-full flex justify-between p-4 my-10">
      <div class="w-auto flex space-x-12">
        <img
          class="w-32 h-32 bg-slate-700 rounded-full bg-cover bg-center"
          :src="userInfo.imageUrl || ''"
          @error="replaceNoProfile"
        />
        <div class="flex flex-col space-y-4 justify-center">
          <h1 class="text-5xl font-semibold">{{ userInfo.name }}</h1>
          <h3 class="text-lg text-slate-400">@{{ userInfo.userId }}</h3>
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
          v-for="attraction in userInfo.attractions"
          :key="attraction.id"
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
          v-for="board in userInfo.boards"
          :key="board.id"
          src="@/assets/attraction.jpg"
          class="w-full aspect-square bg-cover bg-center"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
