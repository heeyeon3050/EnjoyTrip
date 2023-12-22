<script setup>
import { deleteComment } from "@/api/comment";
import { ref } from "vue";
import noProfile from "@/assets/no_profile.png";
import { addMessage } from "@/util/message";

const params = defineProps({
  comment: Object,
  modify: Boolean,
  cancelModify: Function,
});
const emit = defineEmits([
  "modifyStart",
  "modifyCancel",
  "onDeleteComment",
  "onModifyComment",
]);

const content = ref(params.comment.content);

const startModify = () => {
  emit("modifyStart", params.comment.id);
};
const onModify = () => {
  emit("onModifyComment", params.comment.id, content.value);
};
const onDelete = () => {
  const comfirmDelete = confirm("삭제하시겠습니까?");

  if (!comfirmDelete) return;
  deleteComment(
    params.comment.id,
    (response) => {
      console.log(response);
      addMessage("성공적으로 삭제되었습니다", "bg-green-400");
      emit("onDeleteComment", params.comment.id);
    },
    (error) => {
      addMessage("삭제하는 중 오류가 발생했습니다", "bg-red-400");
      console.log(error);
    }
  );
};

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};
</script>

<template>
  <div class="w-full h-20 flex items-center space-x-4 relative">
    <div class="absolute top-2 right-4 flex space-x-2 items-center">
      <template v-if="modify">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          @click="cancelModify"
          class="w-5 h-5 text-slate-500 hover:text-red-500 cursor-pointer"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      </template>
      <template v-else>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          @click="startModify"
          class="w-5 h-5 text-slate-500 hover:text-blue-400 cursor-pointer"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
          />
        </svg>
      </template>
      <template v-if="modify">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          @click="onModify"
          class="w-5 h-5 text-slate-500 hover:text-blue-400 cursor-pointer"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M4.5 12.75l6 6 9-13.5"
          />
        </svg>
      </template>
      <template v-else>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          @click="onDelete"
          class="w-5 h-5 text-slate-500 hover:text-red-500 cursor-pointer"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"
          />
        </svg>
      </template>
    </div>

    <img
      class="w-12 h-12 shrink-0 rounded-full bg-slate-500 bg-cover bg-center"
      :src="params.comment.writerImageUrl || ''"
      @error="replaceNoProfile"
    />
    <div class="w-full flex flex-col space-y-2">
      <div class="flex space-x-6 items-center">
        <h3 class="text-slate-400 text-lg font-semibold">
          @{{ params.comment.writerName }}
        </h3>
        <h4 class="text-slate-500 text-xs">10개월 전</h4>
      </div>
      <template v-if="modify">
        <input
          autofocus
          type="text"
          class="focus:outline-none p-1 rounded-md bg-blue-950/30 text-slate-200"
          v-model="content"
        />
      </template>
      <template v-else>
        <p class="text-slate-200">
          {{ params.comment.content }}
        </p>
      </template>
    </div>
  </div>
</template>

<style scoped></style>
