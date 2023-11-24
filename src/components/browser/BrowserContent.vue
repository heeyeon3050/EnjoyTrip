<script setup>
import BrowserSingleView from "@/components/browser/BrowserSingleView.vue";
import BrowserMultiView from "@/components/browser/BrowserMultiView.vue";
import { aroundBoard } from "@/api/board";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const isSingleView = ref(false);

const route = useRoute();
const router = useRouter();

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);

onMounted(() => {
  if (route.params.page) currentPage.value = route.params.page;
  getBoardList();
});

const getBoardList = () => {
  const query = {
    page: currentPage.value,
  };
  aroundBoard(
    query,
    ({ data: data }) => {
      boards.value = data.data.content;
      totalPage.value = data.data.totalPages;
      router.replace({ name: "browser", query: query });
    },
    (error) => {
      console.error(error);
    }
  );
};
</script>

<template>
  <div class="col-start-3 col-span-8 pt-20">
    <!-- <div class="w-full flex justify-end mb-3">
      <section title=".slideThree">
        <div class="slideThree">
          <input
            type="checkbox"
            value="None"
            id="slideThree"
            name="check"
            class="m-3"
            v-model="isSingleView"
            @click="isSingleView = !isSingleView"
          />
          <label for="slideThree"></label>
        </div>
      </section>
    </div> -->
    <div
      class="myeongjo text-slate-200 text-4xl my-10 border-b-2 border-slate-400 py-5"
    >
      둘러보기
    </div>
    <template v-if="isSingleView">
      <BrowserSingleView :boards="boards" @onPageChange="onPageChange" />
    </template>
    <template v-else>
      <BrowserMultiView :boards="boards" @onPageChange="onPageChange" />
    </template>
  </div>
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanummyeongjo.css);

.myeongjo {
  font-family: "Nanum Myeongjo", serif;
}

/* .slideThree {
  width: 80px;
  height: 26px;
  background: #333;
  margin: 20px auto;
  position: relative;
  border-radius: 50px;
  box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5),
    0px 1px 0px rgba(255, 255, 255, 0.2);
}
.slideThree:after {
  content: "모두";
  color: #3482e9;
  position: absolute;
  right: 10px;
  z-index: 0;
  font: 12px/26px Arial, sans-serif;
  font-weight: bold;
  text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.15);
}
.slideThree:before {
  content: "개별";
  color: #24aa5c;
  position: absolute;
  left: 10px;
  z-index: 0;
  font: 12px/26px Arial, sans-serif;
  font-weight: bold;
}
.slideThree label {
  display: block;
  width: 34px;
  height: 20px;
  cursor: pointer;
  position: absolute;
  top: 3px;
  left: 3px;
  z-index: 1;
  background: #b7b9b0;
  background: linear-gradient(top, #fcfff4 0%, #dfe5d7 40%, #b3bead 100%);
  border-radius: 50px;
  transition: all 0.4s ease;
  box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.3);
}
.slideThree input[type="checkbox"] {
  visibility: hidden;
}
.slideThree input[type="checkbox"]:checked + label {
  left: 43px;
} */
</style>
