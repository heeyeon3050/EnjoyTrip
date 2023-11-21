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
        <div class="flex items-center justify-between px-3 py-2 border-b">
          <div class="flex flex-wrap items-center divide-gray-200">
            <div class="flex items-center space-x-1">
              <button
                type="button"
                class="p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100"
              >
                <svg
                  class="w-4 h-4"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 12 20"
                >
                  <path
                    stroke="currentColor"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M1 6v8a5 5 0 1 0 10 0V4.5a3.5 3.5 0 1 0-7 0V13a2 2 0 0 0 4 0V6"
                  />
                </svg>
                <span class="sr-only">Attach file</span>
              </button>
              <button
                type="button"
                class="p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600"
              >
                <svg
                  class="w-4 h-4"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 16 20"
                >
                  <path
                    d="M8 0a7.992 7.992 0 0 0-6.583 12.535 1 1 0 0 0 .12.183l.12.146c.112.145.227.285.326.4l5.245 6.374a1 1 0 0 0 1.545-.003l5.092-6.205c.206-.222.4-.455.578-.7l.127-.155a.934.934 0 0 0 .122-.192A8.001 8.001 0 0 0 8 0Zm0 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6Z"
                  />
                </svg>
                <span class="sr-only">Embed map</span>
              </button>
            </div>
          </div>

          <div
            id="tooltip-fullscreen"
            role="tooltip"
            class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
          >
            Show full screen
            <div class="tooltip-arrow" data-popper-arrow></div>
          </div>
        </div>
        <div class="px-4 py-2 bg-white rounded-b-lg dark:bg-gray-800">
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
