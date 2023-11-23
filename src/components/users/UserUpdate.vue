<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";

import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { userUpdate } from "@/api/user";
import noProfile from "@/assets/no_profile.png";
import { createImage } from "@/api/image";

const memberStore = useMemberStore();
const router = useRouter();

const { userInfo, loginUserId } = storeToRefs(memberStore);
const { getUserInfo } = memberStore;

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};

const name = ref(userInfo.value.name);
const email = ref(userInfo.value.email);
const password = ref("");
const newPassword = ref("");
const newPassword2 = ref("");
const newImageUrl = ref("");

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
    imageUrl: newImageUrl.value,
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
      router.replace({ name: "user-profile" });
    },
    (error) => {
      console.log(error);
    }
  );
};

const onChangeImage = () => {
  let frm = new FormData();
  let photoFile = document.getElementById("file");
  frm.append("image", photoFile.files[0]);

  createImage(
    frm,
    (response) => {
      console.log(response);
      newImageUrl.value = response.data;
    },
    (error) => {
      console.log(error);
    }
  );
};

watch(
  () => newImageUrl.value,
  () => {
    let profileImg = document.getElementById("profile");
    profileImg.src = newImageUrl.value;
  },
  { deep: true }
);
</script>

<template>
  <div class="w-full space-y-20">
    <div class="w-full flex justify-between p-4 my-10">
      <div class="w-auto flex space-x-12">
        <label for="file">
          <div class="w-32 h-32 relative flex items-center justify-center">
            <img
              id="profile"
              :src="userInfo.image_url || ''"
              @error="replaceNoProfile"
              class="absolute left-0 top-0 cursor-pointer w-32 h-32 bg-slate-700 rounded-full flex justify-center items-center bg-cover bg-center"
            />
            <div
              class="cursor-pointer z-10 opacity-0 hover:opacity-100 bg-black/60 w-32 h-32 flex-col rounded-full flex justify-center items-center"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-8 h-8 text-slate-200"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M6.827 6.175A2.31 2.31 0 015.186 7.23c-.38.054-.757.112-1.134.175C2.999 7.58 2.25 8.507 2.25 9.574V18a2.25 2.25 0 002.25 2.25h15A2.25 2.25 0 0021.75 18V9.574c0-1.067-.75-1.994-1.802-2.169a47.865 47.865 0 00-1.134-.175 2.31 2.31 0 01-1.64-1.055l-.822-1.316a2.192 2.192 0 00-1.736-1.039 48.774 48.774 0 00-5.232 0 2.192 2.192 0 00-1.736 1.039l-.821 1.316z"
                />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M16.5 12.75a4.5 4.5 0 11-9 0 4.5 4.5 0 019 0zM18.75 10.5h.008v.008h-.008V10.5z"
                />
              </svg>
              <p class="text-slate-200 font-semibold text-sm">사진 변경</p>
            </div>
          </div>
        </label>
        <input
          class="hidden"
          type="file"
          name="file"
          id="file"
          @change="onChangeImage"
        />
        <div class="flex flex-col space-y-4 justify-center">
          <h3 class="text-5xl text-slate-300">@{{ userInfo.userId }}</h3>
        </div>
      </div>
    </div>
    <form class="w-full flex flex-col space-y-5">
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-slate-300 text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            닉네임
          </div>
          <span class="inline-block w-5 text-lg text-slate-300">:</span>
          <input
            type="text"
            v-model="name"
            class="text-2xl h-full px-4 border-2 bg-slate-400/30 text-slate-200 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-gray-100"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          nameError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-slate-300 text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            이메일
          </div>
          <span class="inline-block w-5 text-lg text-slate-300">:</span>
          <input
            type="text"
            v-model="email"
            class="text-2xl h-full px-4 border-2 bg-slate-400/30 text-slate-200 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-gray-100"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          emailError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-slate-300 text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            현재 비밀번호
          </div>
          <span class="inline-block w-5 text-lg text-slate-300">:</span>
          <input
            type="password"
            v-model="password"
            autoComplete="off"
            class="text-2xl h-full px-4 border-2 bg-slate-400/30 text-slate-200 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-gray-100"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          passwordError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-slate-300 text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            새 비밀번호
          </div>
          <span class="inline-block w-5 text-lg text-slate-300">:</span>
          <input
            type="password"
            autoComplete="off"
            v-model="newPassword"
            class="text-2xl h-full px-4 border-2 bg-slate-400/30 text-slate-200 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-gray-100"
          />
        </div>
        <span class="flex w-full justify-center h-3 p-0 text-red-400">{{
          newPasswordError
        }}</span>
      </div>
      <div class="w-full flex flex-col h-24">
        <div class="w-full h-20 flex items-center space-x-2">
          <div
            class="text-slate-300 text-2xl w-52 text-center h-full shrink-0 flex items-center"
          >
            새 비밀번호 확인
          </div>
          <span class="inline-block w-5 text-lg text-slate-300">:</span>
          <input
            type="password"
            autoComplete="off"
            v-model="newPassword2"
            class="text-2xl h-full px-4 border-2 bg-slate-400/30 text-slate-200 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-gray-100"
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
