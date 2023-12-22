<script setup>
import VSelect from "../common/VSelect.vue";
import CommonBtn from "../common/CommonBtn.vue";
import { getBoardById, updateBoard } from "@/api/board";
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import { addMessage } from "@/util/message";

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
      addMessage("성공적으로 게시글을 업데이트했습니다", "bg-green-400");
      router.replace(`/board/${route.params.boardId}`);
    },
    (error) => {
      console.log(error);
      addMessage("게시글을 업데이트하는 중 에러가 발생했습니다", "bg-red-400");
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
        class="w-32 border-[2px] text-xl text-slate-200 text-center font-semibold border-slate-400 rounded-l-xl bg-slate-400/30"
      />
      <input
        type="text"
        v-model="title"
        class="w-full p-4 text-2xl placeholder:text-slate-300 placeholder:text-2xl border-[2px] border-slate-400 rounded-r-xl focus:outline-none bg-slate-400/30 text-slate-200"
        placeholder="게시글 제목..."
      />
    </div>
    <div class="w-full">
      <div class="w-full mb-4 border border-gray-200 rounded-lg">
        <div class="px-4 py-2 bg-slate-600/30 rounded-lg">
          <label for="editor" class="sr-only">Publish post</label>
          <textarea
            id="editor"
            rows="20"
            v-model="content"
            class="resize-none block w-full p-10 text-xl text-slate-200 bg-transparent border-0 focus:outline-none"
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
