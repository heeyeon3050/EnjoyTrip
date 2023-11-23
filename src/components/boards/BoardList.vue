<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import BoardListItem from "@/components/boards/BoardListItem.vue";
import VPageNavigation from "../common/VPageNavigation.vue";
import VSelect from "../common/VSelect.vue";
import { listBoard } from "@/api/board";
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);

const route = useRoute();
const router = useRouter();

const searchOption = ref("TITLE");
const keyword = ref("");
const category = ref("");

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);

const searchOptions = [
  { value: "TITLE", text: "제목" },
  { value: "WRITER", text: "작성자" },
];

onMounted(() => {
  if (route.params.page) currentPage.value = route.params.page;
  if (route.params.category) category.value = route.params.category;
  if (route.params.keyword) keyword.value = route.params.keyword;
  if (route.params.searchOption) searchOption.value = route.params.searchOption;
  getBoardList();
});

watch(
  () => category.value,
  () => {
    currentPage.value = 1;
    keyword.value = "";
    searchOption.value = "TITLE";
    getBoardList();
  },
  { deep: true }
);

const onChangeKey = (val) => {
  console.log("BoarList에서 선택한 조건 : " + val);
  searchOption.value = val;
};

const getBoardList = () => {
  const query = {
    page: currentPage.value,
    category: category.value,
    keyword: keyword.value,
    searchOption: searchOption.value,
  };
  listBoard(
    query,
    ({ data: data }) => {
      console.log(data.data);
      boards.value = data.data.content;
      totalPage.value = data.data.totalPages;
      router.replace({ name: "board-list", query: query });
    },
    (error) => {
      console.error(error);
    }
  );
};

const search = () => {
  const query = {
    page: currentPage.value,
    category: category.value,
    keyword: keyword.value,
    searchOption: searchOption.value,
  };
  listBoard(
    query,
    ({ data: data }) => {
      console.log(data.data);
      boards.value = data.data.content;
      totalPage.value = data.data.totalPages;
      router.replace({ name: "board-list", query: query });
    },
    (error) => {
      console.error(error);
    }
  );
};

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  getBoardList();
};
</script>

<template>
  <div
    class="w-full px-3 flex space-x-2 py-10 mb-4 border-b-2 border-b-slate-400"
  >
    <CommonBtn text="전체" :disabled="category === ''" @click="category = ''" />
    <CommonBtn
      text="인기"
      :disabled="category === 'POPULAR'"
      @click="category = 'POPULAR'"
    />
    <CommonBtn
      text="여행 팁"
      :disabled="category === 'TIP'"
      @click="category = 'TIP'"
    />
    <CommonBtn
      text="후기"
      :disabled="category === 'REVIEW'"
      @click="category = 'REVIEW'"
    />
  </div>
  <div class="w-full h-fit p-2 flex flex-col overflow-y-scroll scrollbar-hide">
    <div>
      <BoardListItem v-for="board in boards" :key="board.id" :board="board" />
    </div>
  </div>
  <div class="w-full h-10 my-10 flex justify-between">
    <div>
      <VSelect
        :selectOption="searchOptions"
        @onKeySelect="onChangeKey"
        v-model="searchOption"
        class="w-24 h-10 border-2 mr-3 rounded-md px-3 border-slate-500 bg-blue-950/30 text-slate-200 focus:outline-none"
      />
      <input
        type="text"
        class="w-48 h-10 border-2 rounded-md p-2 border-slate-500 bg-blue-950/30 text-slate-200 focus:outline-none"
        v-model="keyword"
        @keyup.enter="search"
      />
    </div>
    <router-link v-if="isLogin" :to="{ name: 'board-write' }">
      <CommonBtn text="글쓰기" />
    </router-link>
  </div>
  <div class="w-full h-10 my-10">
    <VPageNavigation
      :currentPage="currentPage"
      :totalPage="totalPage"
      @pageChange="onPageChange"
    />
  </div>
</template>

<style scoped></style>
