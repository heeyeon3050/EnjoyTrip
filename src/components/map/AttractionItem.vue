<script setup></script>

<template>
  <div class="w-full h-44 p-2 bg-blue-100 shadow-md flex relative">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      stroke-width="1.5"
      stroke="currentColor"
      class="absolute w-6 h-6 right-3 top-3 cursor-pointer"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
      />
    </svg>

    <img
      class="h-full aspect-square mr-3"
      src="@/assets/attraction.jpg"
      alt=""
    />
    <div class="flex flex-col justify-between p-2 overflow-hidden">
      <h3 class="text-slate-900 font-bold text-sm ml-1">카테고리</h3>
      <h1 class="text-slate-900 font-semibold text-3xl">관광지명</h1>
      <h4 class="text-sm text-slate-500">서울시 강남구 테헤란로 212</h4>
      <p
        class="text-overflow-clip overflow-ellipsis break-words line-clamp-1 text-sm text-slate-600"
      >
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque
        optio cupiditate, atque asperiores laboriosam nisi a aut dicta
        exercitationem pariatur corporis sapiente. Repellendus dolore nihil
        reprehenderit odit praesentium, sequi laudantium?
      </p>
    </div>
  </div>
</template>

<style scoped></style>
