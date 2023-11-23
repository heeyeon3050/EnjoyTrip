<script setup>
import { aroundBoard } from "@/api/board";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(1);
const loading = ref(false);

onMounted(() => {
  aroundBoard(
    { page: 1 },
    ({ data }) => {
      console.log(data.data);
      boards.value = data.data.content;
      totalPage.value = data.data.totalPages;
    },
    (error) => {
      console.error(error);
    }
  );
});

const moveBoardDetail = (boardId) => {
  router.push({ name: "board-view", params: { boardId: boardId } });
};

window.addEventListener("scroll", () => {
  if (loading.value) return;

  const isScrollEnded =
    window.innerHeight + window.scrollY + 10 >= document.body.offsetHeight;

  if (isScrollEnded && currentPage.value + 1 <= totalPage.value) {
    loading.value = true;
    setTimeout(() => {
      aroundBoard(
        { page: currentPage.value + 1 },
        ({ data }) => {
          console.log(data.data);
          boards.value = [...boards.value, ...data.data.content];
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
</script>

<template>
  <div class="w-full grid grid-cols-3 gap-2">
    <div
      class="relative overflow-hidden bg-cover bg-no-repeat"
      v-for="board in boards"
      :key="board.id"
    >
      <img
        :src="board.imageUrl"
        @click="moveBoardDetail(board.id)"
        class="h-full aspect-square transition duration-300 ease-in-out hover:scale-110 bg-cover bg-center cursor-pointer"
      />
    </div>
  </div>
</template>

<style scoped></style>
