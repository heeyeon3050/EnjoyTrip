<script setup>
import VSelect from "../common/VSelect.vue";
import CommonBtn from "../common/CommonBtn.vue";
import { getBoardById, updateBoard } from "@/api/board";
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref } from "vue";

const route = useRoute();
const router = useRouter();
const boardCategory = [
  { value: "NORMAL", text: "일반" },
  { value: "TIP", text: "팁" },
  { value: "REVIEW", text: "후기" },
];

onMounted(() => {
  const boardId = route.params.boardId;
  getBoardById(
    boardId,
    ({ data }) => {
      title.value = data.data.title;
      content.value = data.data.content;
      category.value = data.data.category;
      latitude.value = data.data.latitude;
      longitude.value = data.data.longitude;
    },
    () => {}
  );
});

const title = ref("");
const content = ref("");
const category = ref("NORMAL");
const latitude = ref(0);
const longitude = ref(0);

const onSubmit = () => {
  updateBoard(
    route.params.boardId,
    {
      title: title.value,
      content: content.value,
      category: category.value,
      latitude: latitude.value,
      longitude: longitude.value,
    },
    ({ data }) => {
      console.log(data);
      router.replace(`/board/${route.params.boardId}`);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="w-full">
    <div class="w-full h-20 flex justify-between my-10">
      <VSelect
        :selectOption="boardCategory"
        v-model="category"
        class="w-32 border-2 bg-gray-50 text-xl text-slate-700 text-center font-semibold border-slate-200 rounded-l-xl"
      />
      <input
        type="text"
        v-model="title"
        class="w-full p-4 text-2xl placeholder:text-slate-950 placeholder:text-2xl border-2 border-slate-200 rounded-r-xl focus:outline-none"
        placeholder="게시글 제목..."
      />
    </div>
    <div class="w-full">
      <div class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50">
        <div class="px-4 py-2 bg-white rounded-lg dark:bg-gray-800">
          <label for="editor" class="sr-only">Publish post</label>
          <textarea
            id="editor"
            rows="20"
            v-model="content"
            class="resize-none block w-full p-10 text-xl text-gray-800 bg-white border-0 focus:outline-none"
            placeholder="내용을 입력하세요.."
            required
          ></textarea>
        </div>
      </div>
      <div class="flex justify-end space-x-4">
        <router-link
          :to="{
            name: 'board-view',
            params: { boardId: route.params.boardId },
          }"
        >
          <CommonBtn text="취소" />
        </router-link>
        <CommonBtn text="수정" @click="onSubmit" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
