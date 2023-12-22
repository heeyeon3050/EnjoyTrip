<script setup>
import LoginModal from "@/components/common/LoginModal.vue";
import JoinModal from "../common/JoinModal.vue";
import FindPasswordModal from "@/components/common/FindPasswordModal.vue";
import { ModalStatus } from "@/util/constants";
import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import noProfile from "@/assets/no_profile.png";
import { addMessage } from "@/util/message";

const router = useRouter();

const memberStore = useMemberStore();
const { userLogout } = memberStore;

const { isLogin, userInfo } = storeToRefs(memberStore);

const modalStatus = ref(ModalStatus.CLOSED);

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};

const logout = () => {
  userLogout();
  router.push("/");
  addMessage("성공적으로 로그아웃되었습니다", "bg-green-400");
};

const modalFindPassword = () => {
  modalStatus.value = ModalStatus.FIND_PASSWORD;
};

const modalJoin = () => {
  modalStatus.value = ModalStatus.JOIN;
};

const modalClose = () => {
  modalStatus.value = ModalStatus.CLOSED;
};

const modalLogin = () => {
  modalStatus.value = ModalStatus.LOGIN;
};
</script>
<template>
  <nav class="glow w-full h-24 flex justify-between col-span-12">
    <div class="flex">
      <router-link :to="{ name: 'main' }" class="mx-3 flex items-center">
        <div class="flex items-center">
          <img src="@/assets/logo.png" alt="..." class="h-20 mx-7 px-2" />
          <!-- <h1 class="eng glow font-bold text-xl flex items-center">
            ENJOY TRIP
          </h1> -->
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
    <template v-if="!isLogin">
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
      <div class="flex space-x-5 h-full px-6 w-96">
        <router-link
          :to="{
            name: 'user-profile',
            params: { userId: userInfo.userId },
          }"
        >
          <div class="flex space-x-5 h-full px-6 w-44 items-center">
            <img
              class="w-10 h-10 shrink-0 rounded-full bg-slate-500 bg-cover bg-center shadow-lg"
              :src="userInfo.image_url || ''"
              @error="replaceNoProfile"
            />
            <h2
              class="font-medium text-sm text-overflow-clip overflow-ellipsis break-words line-clamp-1"
            >
              {{ userInfo.name }}
            </h2>
          </div>
        </router-link>
        <div
          class="flex text-sm justify-center items-center cursor-pointer"
          @click="logout"
        >
          로그아웃
        </div>
      </div>
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
    :close="modalClose"
  />
  <JoinModal v-if="modalStatus === ModalStatus.JOIN" :login="modalLogin" />
  <FindPasswordModal v-if="modalStatus === ModalStatus.FIND_PASSWORD" />
</template>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/nanummyeongjo.css);

.glow {
  color: rgb(235, 243, 252);
  text-shadow: 0 0 0.1rem #def;
  font-family: "Nanum Myeongjo", serif;
}

.eng {
  font-family: "SF_HambakSnow";
}
</style>
