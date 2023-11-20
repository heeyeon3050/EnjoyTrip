<script setup>
import VKakaoMap from "@/components/map/VKakaoMap.vue";
import CommonBtn from "../common/CommonBtn.vue";
import AttractionItem from "@/components/map/AttractionItem.vue";
import { searchAttraction } from "@/api/attraction";
import { ref, onMounted } from "vue";

const attractionList = ref([]);
const attraction = ref({});

const params = ref({
  pageNo: 1,
  numOfRows: 20,
  category: "NORMAL",
});

onMounted(() => {
  getAttractionLsit();
});

const getAttractionLsit = () => {
  searchAttraction(
    params.value,
    ({ data: { data } }) => {
      attractionList.value = data;
    },
    (error) => {
      console.log(err);
    }
  );
};

const viewLocation = (location) => {
  attraction.value = location;
};
</script>

<template>
  <div class="col-span-4 h-screen bg-slate-200 flex flex-col relative z-10">
    <div
      class="absolute cursor-pointer top-1/2 right-0 w-5 h-20 translate-x-[100%] bg-slate-200 -translate-y-1/2 shadow-sm flex justify-center items-center"
    >
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
          d="M15.75 19.5L8.25 12l7.5-7.5"
        />
      </svg>
    </div>

    <div class="flex h-24 items-center">
      <router-link :to="{ name: 'main' }" class="mx-5 flex items-center h-24">
        <div class="flex items-center">
          <img src="@/assets/logo.png" alt="..." class="h-14" />
          <h1 class="font-bold text-2xl flex items-center">EnjoyTrip</h1>
        </div>
      </router-link>
    </div>
    <form class="w-full px-10 py-3">
      <div class="w-full h-10 relative">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6 absolute right-2 top-1/2 -translate-y-1/2 cursor-pointer"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
          />
        </svg>
        <input
          type="text"
          name=""
          id=""
          @keyup.enter="login"
          class="w-full h-full rounded-sm p-2 focus:outline-none focus:ring-1 focus:ring-slate-500"
        />
      </div>
      <div class="w-full h-10 flex justify-around py-3">
        <input type="checkbox" name="category" value="TRAIN" />
        <CommonBtn text="기차역" />
        <input type="checkbox" name="category" value="NORMAL" />
        <CommonBtn text="관광지" />
        <input type="checkbox" name="category" value="MEDIA" />
        <CommonBtn text="촬영지" />
        <input type="checkbox" name="category" value="HERITAGE" />
        <CommonBtn text="문화재" />
      </div>
    </form>
    <div
      class="w-full h-full p-2 flex flex-col overflow-y-scroll scrollbar-hide"
    >
      <div class="space-y-2">
        <AttractionItem
          v-for="attraction in attractionList"
          :key="attraction.id"
          :attraction="attraction"
          @click="viewLocation(attraction)"
        />
      </div>
    </div>
  </div>
  <VKakaoMap :list="attractionList" :selected="attraction" />
</template>

<style scoped></style>
