<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import BoardListItem from "@/components/boards/BoardListItem.vue";
import { searchBoard } from "@/api/board";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "글번호", value: "article_no" },
  { text: "제목", value: "subject" },
  { text: "작성자아이디", value: "user_id" },
]);

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getBoardList();
});

const changeKey = (val) => {
  console.log("BoarList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const getBoardList = () => {
  console.log("서버에서 글목록 얻어오자!!!", param.value);
  searchBoard(
    param.value,
    ({ data }) => {
      console.log(data);
      boards.value = data.data;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.error(error);
    }
  );
};

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getBoardList();
};

const moveWrite = () => {
  router.push({ name: "article-write" });
};
</script>

<template>
  <div
    class="w-full px-3 flex space-x-2 py-10 mb-4 border-b-2 border-b-slate-400"
  >
    <CommonBtn text="전체" />
    <CommonBtn text="인기" />
    <CommonBtn text="여행 팁" />
    <CommonBtn text="후기" />
  </div>
  <div class="w-full h-fit p-2 flex flex-col overflow-y-scroll scrollbar-hide">
    <div>
      <BoardListItem v-for="board in boards" :key="board.id" :board="board" />
    </div>
  </div>
  <div class="w-full h-10 my-10 flex justify-between">
    <div>
      <select
        name="languages"
        id="lang"
        class="w-24 h-10 border-2 mr-3 rounded-md px-3 border-slate-500"
      >
        <option value="title">제목</option>
        <option value="writer">작성자</option>
      </select>
      <input
        type="text"
        class="w-48 h-10 border-2 rounded-md p-2 border-slate-500"
      />
    </div>
    <router-link :to="{ name: 'board-write' }">
      <CommonBtn text="+ 작성" />
    </router-link>
  </div>
  <div class="w-full h-10 my-10 flex space-x-4 justify-center">
    <button>&lt;</button>
    <button>&lt;&lt;</button>
    <button>1</button>
    <button>2</button>
    <button>3</button>
    <button>4</button>
    <button>5</button>
    <button>&gt;</button>
    <button>&gt;&gt;</button>
  </div>
</template>

<style scoped></style>
