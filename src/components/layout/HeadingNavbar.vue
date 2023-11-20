<script setup>
import LoginModal from "@/components/common/LoginModal.vue";
import JoinModal from "../common/JoinModal.vue";
import FindPasswordModal from "@/components/common/FindPasswordModal.vue";
import { ModalStatus } from "@/util/constants";
import { ref } from "vue";

const modalStatus = ref(ModalStatus.CLOSED);

const modalFindPassword = () => {
  modalStatus.value = ModalStatus.FIND_PASSWORD;
};

const modalJoin = () => {
  modalStatus.value = ModalStatus.JOIN;
};

const modalLogin = () => {
  modalStatus.value = ModalStatus.LOGIN;
};
</script>
<template>
  <nav class="w-full h-24 flex justify-between col-span-12">
    <div class="flex">
      <router-link :to="{ name: 'main' }" class="mx-5 flex items-center">
        <div class="flex items-center">
          <img src="@/assets/logo.png" alt="..." class="h-14" />
          <h1 class="font-bold text-2xl flex items-center">EnjoyTrip</h1>
        </div>
      </router-link>
      <ul class="flex justify-between space-x-10 items-center ml-10">
        <router-link :to="{ name: 'browser' }">
          <li
            class="text-sm font-medium cursor-pointer w-16 h-10 flex justify-center items-center"
          >
            둘러보기
          </li>
        </router-link>
        <router-link :to="{ name: 'map' }">
          <li
            class="text-sm font-medium cursor-pointer w-16 h-10 flex justify-center items-center"
          >
            지도
          </li>
        </router-link>
        <router-link :to="{ name: 'board' }">
          <li
            class="text-sm font-medium cursor-pointer w-16 h-10 flex justify-center items-center"
          >
            게시판
          </li>
        </router-link>
      </ul>
    </div>
    <template v-if="1 === 1">
      <div class="flex space-x-5 h-full px-6 w-52">
        <div
          class="flex justify-center items-center cursor-pointer"
          @click="modalStatus = ModalStatus.JOIN"
        >
          회원가입
        </div>
        <div
          class="flex justify-center items-center cursor-pointer"
          @click="modalStatus = ModalStatus.LOGIN"
        >
          로그인
        </div>
      </div>
    </template>
    <template v-else>
      <router-link
        :to="{
          name: 'user-profile',
          params: { userId: 'ssafy' },
        }"
      >
        <div class="flex space-x-5 h-full px-6 w-52 items-center">
          <div class="w-10 h-10 rounded-full bg-slate-500"></div>
          <h2 class="font-medium text-lg">이유로</h2>
        </div>
      </router-link>
    </template>
  </nav>
  <div
    v-if="modalStatus !== ModalStatus.CLOSED"
    @click.stop="modalStatus = 0"
    class="absolute w-full h-full bg-black/40 top-0 left-0 z-30"
  ></div>
  <LoginModal
    v-if="modalStatus === ModalStatus.LOGIN"
    :join="modalJoin"
    :findPassword="modalFindPassword"
  />
  <JoinModal v-if="modalStatus === ModalStatus.JOIN" :login="modalLogin" />
  <FindPasswordModal v-if="modalStatus === ModalStatus.FIND_PASSWORD" />
</template>

<style scoped></style>
