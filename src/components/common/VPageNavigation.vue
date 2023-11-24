<script setup>
import { computed } from "vue";

const props = defineProps({ currentPage: Number, totalPage: Number });
const emit = defineEmits(["pageChange"]);

const navigationSize = parseInt(import.meta.env.VITE_ARTICLE_NAVIGATION_SIZE);

const startPage = computed(() => {
  return parseInt((props.currentPage - 1) / navigationSize) * navigationSize + 1;
});

const endPage = computed(() => {
  let lastPage =
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize + navigationSize;
  return props.totalPage < lastPage ? props.totalPage : lastPage;
});

const endRange = computed(() => {
  return parseInt((props.totalPage - 1) / navigationSize) * navigationSize < props.currentPage;
});

function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
  // return Array(end - start + 1)
  //   .fill()
  //   .map((val, i) => start + i);
}

function onPageChange(pg) {
  console.log(pg + "로 이동!!!");
  emit("pageChange", pg);
}
</script>

<template>
  <div class="w-full flex items-center justify-center text-slate-200">
    <div class="flex space-x-7 mr-5">
      <div class="flex justify-center items-center">
        <a class="" @click="onPageChange(1)"
          ><svg
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
              d="M18.75 19.5l-7.5-7.5 7.5-7.5m-6 15L5.25 12l7.5-7.5"
            />
          </svg>
        </a>
      </div>
      <div class="flex justify-center items-center">
        <a class="" @click="onPageChange(startPage == 1 ? 1 : startPage - 1)"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5" />
          </svg>
        </a>
      </div>
    </div>
    <div class="flex mx-5">
      <ul class="flex space-x-6 justify-center text-xl items-center">
        <template v-for="pg in range(startPage, endPage)" :key="pg">
          <li
            :class="
              currentPage === pg
                ? 'text-slate-900 bg-gray-200 w-10 h-10 flex justify-center items-center rounded font-semibold'
                : 'w-10 h-10 flex justify-center items-center rounded font-semibold text-slate-200'
            "
          >
            <a class="" @click="onPageChange(pg)">{{ pg }}</a>
          </li>
        </template>
      </ul>
    </div>
    <div class="flex space-x-7 ml-5">
      <div class="flex justify-center items-center">
        <a class="" @click="onPageChange(endRange ? totalPage : endPage + 1)"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5" />
          </svg>
        </a>
      </div>
      <div class="flex justify-center items-center">
        <a class="" @click="onPageChange(totalPage)"
          ><svg
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
              d="M11.25 4.5l7.5 7.5-7.5 7.5m-6-15l7.5 7.5-7.5 7.5"
            />
          </svg>
        </a>
      </div>
    </div>
  </div>
</template>

<style scoped>
a {
  cursor: pointer;
}
</style>
