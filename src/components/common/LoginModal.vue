<script setup>
import ModalInput from "@/components/modal/ModalInput.vue";
import ModalBtn from "@/components/modal/ModalBtn.vue";
import ModalDoubleBtn from "@/components/modal/ModalDoubleBtn.vue";

defineProps({ join: Function, findPassword: Function });
</script>

<template>
  <div
    class="z-40 gradient w-[34.5rem] h-[19.44rem] absolute -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2 rounded-lg shadow-2xl"
  >
    <div
      class="absolute flex justify-center items-center w-24 h-8 bg-[#cde5f3] left-1/2 top-0 z-10 -translate-x-1/2 translate-y-0 text-xl font-bold"
    >
      LOGIN
    </div>
    <img
      src="@/assets/barcode.png"
      alt="..."
      class="w-16 h-6 absolute -top-2 right-2 -translate-x-full translate-y-full"
    />
    <div
      class="absolute border-slate-950 border-[1px] w-[32.5rem] h-[17.44rem] -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2 rounded-3xl flex flex-col items-center"
    >
      <h1
        class="w-full text-center font-black text-6xl border-b-[1px] border-slate-950 py-3"
      >
        TRAIN TICKET
      </h1>
      <div class="w-full h-full flex">
        <div
          class="w-3/5 border-r-[1px] border-slate-950 flex flex-col items-center justify-evenly px-2"
        >
          <ModalInput type="text" label="아이디" />
          <ModalInput type="password" label="비밀번호" />
          <ModalBtn text="로그인" />
          <ModalDoubleBtn
            leftText="회원가입"
            rightText="비밀번호 찾기"
            :leftClick="join"
            :rightClick="findPassword"
          />
        </div>
        <div class="w-2/5 flex flex-col itemx-center justify-between">
          <div class="w-full h-30 flex space-x-4 p-3 justify-center">
            <div class="w-10 h-10">
              <img src="@/assets/kakao.png" alt="..." class="w-10 h-10" />
            </div>
            <div class="w-10 h-10">
              <img src="@/assets/naver.png" alt="..." class="w-10 h-10" />
            </div>
            <div class="w-10 h-10">
              <img src="@/assets/google.png" alt="..." class="w-10 h-10" />
            </div>
          </div>
          <div class="h-30 flex justify-center items-center m-2">
            <img src="@/assets/train.png" alt="..." class="w-24 h-24" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.gradient {
  background: linear-gradient(180deg, #cde5f3, #ffffff);
}
.gradientText {
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}
</style>
