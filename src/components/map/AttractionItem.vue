<script setup>
import { likeAttraction } from "@/api/attraction";
import noImg from "@/assets/no_image.jpg";

const params = defineProps({ attraction: Object });
const emit = defineEmits(["like"]);

const replaceNoImg = (event) => {
  event.target.src = noImg;
};

const onLikeAttraction = () => {
  likeAttraction(
    params.attraction.id,
    ({ data: data }) => {
      console.log(data.data);
      emit("like", data.data);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div
    class="w-full h-44 p-2 bg-gray-600 shadow-md flex relative text-slate-200"
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      stroke-width="1.5"
      stroke="currentColor"
      :class="{
        'absolute w-6 h-6 right-3 top-3 cursor-pointer': true,
        'fill-red-400 text-red-400': attraction.isLiked,
      }"
      @click.stop="onLikeAttraction"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
      />
    </svg>

    <img
      class="h-full aspect-square mr-3"
      :src="params.attraction.image_url"
      @error="replaceNoImg"
      alt=""
    />
    <div class="flex flex-col justify-between p-2 overflow-hidden">
      <h3 class="text-slate-300 font-bold text-sm ml-1">
        {{ params.attraction.category }}
      </h3>
      <h1
        class="text-overflow-clip overflow-ellipsis break-words line-clamp-1 text-slate-200 font-semibold text-3xl"
      >
        {{ params.attraction.title }}
      </h1>
      <h4
        class="text-overflow-clip overflow-ellipsis break-words line-clamp-1 text-sm text-slate-400"
      >
        {{ params.attraction.address1 }}
      </h4>
      <p
        class="text-overflow-clip overflow-ellipsis break-words line-clamp-1 text-sm text-slate-300"
      >
        {{ params.attraction.description }}
      </p>
    </div>
  </div>
</template>

<style scoped></style>
