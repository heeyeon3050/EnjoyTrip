<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";

import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { userUpdate } from "@/api/user";

const memberStore = useMemberStore();
const router = useRouter();

const { userInfo, loginUserId } = storeToRefs(memberStore);
const { getUserInfo } = memberStore;

const name = ref(userInfo.value.name);
const email = ref(userInfo.value.email);
const password = ref("");
const newPassword = ref("");
const newPassword2 = ref("");

const nameError = ref("");
const emailError = ref("");
const passwordError = ref("");
const newPasswordError = ref("");
const newPassword2Error = ref("");

const onSubmit = () => {
  if (name.value === "") {
    nameError.value = "닉네임을 입력하세요";
    return;
  }
  if (email.value === "") {
    emailError.value = "이메일을 입력하세요";
    return;
  }

  const updateUser = {
    name: name.value,
    email: email.value,
  };

  if (password.value !== "") {
    if (newPassword.value === "") {
      newPasswordError.value = "새 비밀번호 입력하세요";
      return;
    }
    if (newPassword2.value === "") {
      newPassword2Error.value = "새 비밀번호 확인 입력하세요";
      return;
    }
    if (newPassword.value !== newPassword2.value) {
      newPasswordError.value = "비밀번호가 서로 다릅니다";
      return;
    }
    updateUser.password = newPassword.value;
  }

  console.log(updateUser);
  userUpdate(
    loginUserId.value,
    updateUser,
    async (response) => {
      console.log(response);
      await getUserInfo();
      router.go(0);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="w-full space-y-20">
    <div class="w-full flex justify-between p-4 my-10">
      <div class="w-auto flex space-x-12">
        <div class="w-32 h-32 bg-slate-700 rounded-full"></div>
        <div class="flex flex-col space-y-4 justify-center">
          <h3 class="text-5xl text-slate-400">@{{ userInfo.userId }}</h3>
        </div>
      </div>
    </div>
    <form class="w-full flex flex-col space-y-5">
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            닉네임
          </div>
          <span class="inline-block w-5 text-lg">:</span>
          <input
            type="text"
            v-model="name"
            class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          nameError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            이메일
          </div>
          <span class="inline-block w-5 text-lg">:</span>
          <input
            type="text"
            v-model="email"
            class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          emailError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            현재 비밀번호
          </div>
          <span class="inline-block w-5 text-lg">:</span>
          <input
            type="password"
            v-model="password"
            autoComplete="off"
            class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          passwordError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            새 비밀번호
          </div>
          <span class="inline-block w-5 text-lg">:</span>
          <input
            type="password"
            autoComplete="off"
            v-model="newPassword"
            class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          newPasswordError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            새 비밀번호 확인
          </div>
          <span class="inline-block w-5 text-lg">:</span>
          <input
            type="password"
            autoComplete="off"
            v-model="newPassword2"
            class="text-2xl h-full px-4 border-2 border-slate-200 rounded-lg w-full"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          newPassword2Error
        }}</span>
      </div>
    </form>
    <div class="w-full flex justify-end space-x-5">
      <router-link
        :to="{
          name: 'user-profile',
          params: { userId: 'ssafy' },
        }"
      >
        <CommonBtn text="취소" />
      </router-link>
      <CommonBtn text="수정" @click="onSubmit" />
    </div>
  </div>
</template>

<style scoped></style>
