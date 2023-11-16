<script setup>
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";
import LoginModal from "@/components/common/LoginModal.vue";
import { ref } from "vue";

const menuStore = useMenuStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const logout = () => {
  console.log("로그아웃!!!!");
  changeMenuState();
};

const loginModalVisible = ref(false);
</script>
<template>
  <nav class="w-full bg-blue-200 h-28 flex justify-between col-span-12">
    <div class="flex">
      <router-link :to="{ name: 'main' }" class="mx-5 flex items-center">
        <div class="flex items-center">
          <img src="@/assets/logo.png" alt="..." class="h-20" />
          <h1 class="font-bold text-4xl flex items-center">EnjoyTrip</h1>
        </div>
      </router-link>
      <ul class="flex justify-between space-x-10 items-center ml-10">
        <router-link :to="{ name: 'browser' }">
          <li class="cursor-pointer w-16 h-10 flex justify-center items-center">
            둘러보기
          </li>
        </router-link>
        <router-link :to="{ name: 'map' }">
          <li class="cursor-pointer w-16 h-10 flex justify-center items-center">
            지도
          </li>
        </router-link>
        <router-link :to="{ name: 'board' }">
          <li class="cursor-pointer w-16 h-10 flex justify-center items-center">
            게시판
          </li>
        </router-link>
      </ul>
    </div>
    <div class="flex space-x-5 h-full px-6 w-52">
      <div class="flex justify-center items-center cursor-pointer">
        회원가입
      </div>
      <div
        class="flex justify-center items-center cursor-pointer"
        @click="loginModalVisible = !loginModalVisible"
      >
        로그인
      </div>
    </div>
  </nav>
  <div
    v-if="loginModalVisible"
    @click.stop="loginModalVisible = !loginModalVisible"
    class="absolute w-full h-full bg-black/40 top-0 left-0"
  ></div>
  <LoginModal v-if="loginModalVisible" />
</template>

<style scoped></style>
