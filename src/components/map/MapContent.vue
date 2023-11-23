<script setup>
import VKakaoMap from "@/components/map/VKakaoMap.vue";
import CommonBtn from "../common/CommonBtn.vue";
import AttractionItem from "@/components/map/AttractionItem.vue";
import { listAttraction } from "@/api/attraction";
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();

const categories = ref([]);
const keyword = ref("");

const normal = ref(true);
const heritage = ref(false);
const media = ref(false);
const train = ref(false);

const attractionList = ref([]);
const attraction = ref({});
const currentPage = ref(1);
const totalPage = ref(1);
const loading = ref(false);

const latitude = ref(37.5012739267);
const longitude = ref(127.0396131393);

const replaceRoute = () => {
  const query = {
    keyword: keyword.value,
    categories: categories.value,
    page: currentPage.value,
    latitude: latitude.value,
    longitude: longitude.value,
  };
  router.push({ name: "map", query: query });
  getAttractionList();
};

const changeCategory = () => {
  let c = [];
  if (normal.value) c.push("NORMAL");
  if (heritage.value) c.push("HERITAGE");
  if (media.value) c.push("MEDIA");
  if (train.value) c.push("TRAIN");
  categories.value = c;
};

watch(() => categories.value, replaceRoute, { deep: true });
watch(() => normal.value, changeCategory, { deep: true });
watch(() => heritage.value, changeCategory, { deep: true });
watch(() => media.value, changeCategory, { deep: true });
watch(() => train.value, changeCategory, { deep: true });

onMounted(() => {
  latitude.value = route.query.latitude;
  longitude.value = route.query.longitude;
  getAttractionList();
  const list = document.getElementById("list");
  if (list) {
    list.addEventListener("scroll", (e) => {
      console.log(e);
      console.log(e.target.scrollTop + " / " + e.target.scrollHeight);

      if (loading.value) return;

      const isScrollEnded =
        window.innerHeight + e.target.scrollTop + 10 >= e.target.scrollHeight;
      console.log(isScrollEnded);
      if (isScrollEnded && currentPage.value + 1 <= totalPage.value) {
        loading.value = true;
        setTimeout(() => {
          listAttraction(
            {
              keyword: keyword.value,
              categories: categories.value,
              page: currentPage.value + 1,
            },
            ({ data }) => {
              console.log(data.data);
              attractionList.value = [
                ...attractionList.value,
                ...data.data.content,
              ];
              totalPage.value = data.data.totalPages;
              currentPage.value = data.data.pageable.pageNumber + 1;
              loading.value = false;
            },
            (error) => {
              console.error(error);
              loading.value = false;
            }
          );
        }, 1000);
      }
    });
  }
});

const getAttractionList = () => {
  loading.value = true;
  listAttraction(
    {
      keyword: keyword.value,
      categories: categories.value,
      page: currentPage.value,
      latitude: latitude.value,
      longitude: longitude.value,
    },
    ({ data: { data } }) => {
      attractionList.value = data.content;
      currentPage.value = 1;
      totalPage.value = data.totalPages;
      loading.value = false;
    },
    (error) => {
      console.log(error);
    }
  );
};

const viewLocation = (location) => {
  attraction.value = location;
};

const toggleMenu = () => {
  const menu = document.getElementById("menu");
  const menuOpenBtn = document.getElementById("menuOpenBtn");
  if (menu.classList.contains("close")) {
    menu.classList.remove("close");
    menuOpenBtn.classList.remove("closeBtn");
  } else {
    menu.classList.add("close");
    menuOpenBtn.classList.add("closeBtn");
  }
};

const likeAttraction = (attraction) => {
  attractionList.value = attractionList.value.map((a) => {
    if (attraction.id === a.id) {
      return attraction;
    }
    return a;
  });
};
</script>

<template>
  <div
    id="menu"
    class="col-span-4 h-screen bg-slate-700 flex flex-col relative z-10 transition-transform"
  >
    <div
      @click="toggleMenu"
      class="absolute text-slate-200 cursor-pointer top-1/2 right-0 w-5 h-20 translate-x-[100%] bg-slate-700 -translate-y-1/2 shadow-sm flex justify-center items-center rounded-r-md"
    >
      <svg
        id="menuOpenBtn"
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
      <router-link :to="{ name: 'main' }" class="mx-3 flex items-center h-24">
        <div class="flex items-center">
          <img src="@/assets/logo.png" alt="..." class="h-20 mx-7 px-2" />
        </div>
      </router-link>
      <div class="w-full px-10 py-2">
        <div class="w-full h-8 relative">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            @click="replaceRoute"
            class="w-6 h-6 absolute right-2 top-1/2 -translate-y-1/2 cursor-pointer text-slate-200"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
            />
          </svg>
          <input
            type="text"
            name="keyword"
            v-model="keyword"
            @keyup.enter="replaceRoute"
            class="w-full h-full rounded-sm p-2 focus:outline-none focus:ring-1 bg-slate-400/30 text-slate-200 focus:ring-slate-500"
          />
        </div>
        <div class="w-full h-10 flex justify-around py-3">
          <CommonBtn
            text="기차역"
            @click="train = !train"
            :class="{ 'border-b-[1px] box-border border-slate-200': train }"
          />
          <CommonBtn
            text="관광지"
            @click="normal = !normal"
            :class="{ 'border-b-[1px] box-border border-slate-200': normal }"
          />
          <CommonBtn
            text="촬영지"
            @click="media = !media"
            :class="{ 'border-b-[1px] box-border border-slate-200': media }"
          />
          <CommonBtn
            text="문화재"
            @click="heritage = !heritage"
            :class="{ 'border-b-[1px] box-border border-slate-200': heritage }"
          />
        </div>
      </div>
    </div>

    <div id="list" class="w-full h-full p-2 flex flex-col overflow-y-scroll">
      <div class="space-y-2">
        <AttractionItem
          v-for="attraction in attractionList"
          :key="attraction.id"
          :attraction="attraction"
          @click="viewLocation(attraction)"
          @like="likeAttraction"
        />
      </div>
    </div>
  </div>
  <VKakaoMap
    :list="attractionList"
    :selected="attraction"
    :latitude="latitude"
    :longitude="longitude"
  />
</template>

<style scoped>
.close {
  transform: translateX(-100%);
}

.closeBtn {
  transform: rotateY(180deg);
}

div::-webkit-scrollbar {
  width: 5px;
}
div::-webkit-scrollbar-thumb {
  background-color: rgb(109, 121, 138);
}
div::-webkit-scrollbar-track {
  background-color: rgba(99, 91, 91, 0.3);
}
</style>
