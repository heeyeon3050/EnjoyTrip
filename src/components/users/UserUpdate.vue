<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
</script>

<template>
  <div class="w-full space-y-20">
    <div class="w-full flex justify-between p-4 my-10">
      <div class="w-auto flex space-x-12">
        <div class="w-32 h-32 bg-slate-700 rounded-full"></div>
        <div class="flex flex-col space-y-4 justify-center">
          <h3 class="text-5xl text-slate-400">@eurohand</h3>
        </div>
      </div>
    </div>
    <div class="w-full flex flex-col space-y-5">
      <div class="w-full h-20 flex items-center space-x-2">
        <div
          class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
        >
          닉네임
        </div>
        <span class="inline-block w-5 text-lg">:</span>
        <input
          type="text"
          value="이유로"
          class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
        />
      </div>
      <div class="w-full h-20 flex items-center space-x-2">
        <div
          class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
        >
          이메일
        </div>
        <span class="inline-block w-5 text-lg">:</span>
        <input
          type="text"
          value="eurohand@naver.com"
          class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
        />
      </div>
      <div class="w-full h-20 flex items-center space-x-2">
        <div
          class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
        >
          현재 비밀번호
        </div>
        <span class="inline-block w-5 text-lg">:</span>
        <input
          type="text"
          value="eurohand@naver.com"
          class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
        />
      </div>
      <div class="w-full h-20 flex items-center space-x-2">
        <div
          class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
        >
          새 비밀번호
        </div>
        <span class="inline-block w-5 text-lg">:</span>
        <input
          type="text"
          value="eurohand@naver.com"
          class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
        />
      </div>
      <div class="w-full h-20 flex items-center space-x-2">
        <div
          class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
        >
          새 비밀번호 확인
        </div>
        <span class="inline-block w-5 text-lg">:</span>
        <input
          type="text"
          value="eurohand@naver.com"
          class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
        />
      </div>
    </div>
    <div class="w-full flex justify-end space-x-5">
      <router-link
        :to="{
          name: 'user-profile',
          params: { userId: 'ssafy' },
        }"
      >
        <CommonBtn text="취소" />
      </router-link>
      <CommonBtn text="수정" />
    </div>
  </div>
</template>

<style scoped></style>
