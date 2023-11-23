<script setup>
import noImg from "@/assets/no_image.jpg";
import noProfile from "@/assets/no_profile.png";
import { formatDate } from "@/util/utils";

const params = defineProps({ board: Object });

const replaceNoImg = (event) => {
  event.target.src = noImg;
};

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};
</script>

<template>
  <div
    class="w-full h-28 p-2 flex relative first:border-t-0 border-t-[1px] border-[#bbb] text-slate-200"
  >
    <img
      class="h-full aspect-square bg-cover mr-3"
      :src="params.board.imageUrl"
      @error="replaceNoImg"
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
          <h1 class="text-xl font-semibold">
            {{ params.board.title }} [{{ params.board.commentCount }}]
          </h1>
        </router-link>
        <h4 class="text-sm text-slate-400">{{ params.board.category }}</h4>
      </div>
      <div class="w-2/5 h-full flex justify-around items-center">
        <div class="flex items-center space-x-2">
          <img
            class="w-8 h-8 shrink-0 rounded-full bg-slate-500 bg-cover bg-center"
            :src="params.board.writerImageUrl"
            @error="replaceNoProfile"
          />
          <h3>{{ params.board.writerName }}</h3>
        </div>
        <div class="font-medium">
          {{ params.board?.createdAt && formatDate(params.board?.createdAt) }}
        </div>
        <div class="flex space-x-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M6.633 10.5c.806 0 1.533-.446 2.031-1.08a9.041 9.041 0 012.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 00.322-1.672V3a.75.75 0 01.75-.75A2.25 2.25 0 0116.5 4.5c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 01-2.649 7.521c-.388.482-.987.729-1.605.729H13.48c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 00-1.423-.23H5.904M14.25 9h2.25M5.904 18.75c.083.205.173.405.27.602.197.4-.078.898-.523.898h-.908c-.889 0-1.713-.518-1.972-1.368a12 12 0 01-.521-3.507c0-1.553.295-3.036.831-4.398C3.387 10.203 4.167 9.75 5 9.75h1.053c.472 0 .745.556.5.96a8.958 8.958 0 00-1.302 4.665c0 1.194.232 2.333.654 3.375z"
            />
          </svg>
          <div class="font-semibold">{{ params.board.likeCount }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
