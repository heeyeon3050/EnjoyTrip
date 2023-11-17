<script setup>
import ModalInput from "@/components/modal/ModalInput.vue";
import ModalBtn from "../modal/ModalBtn.vue";
</script>

<template>
  <div
    class="z-40 gradient w-[26rem] h-[34.5rem] absolute -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2 rounded-lg shadow-2xl"
  >
    <div
      class="absolute flex justify-center items-center w-36 h-8 bg-[#cde5f3] left-1/2 top-0 z-10 -translate-x-1/2 translate-y-0 text-xl font-bold"
    >
      ENJOY TRIP
    </div>
    <img
      src="@/assets/barcode.png"
      alt="..."
      class="w-16 h-6 absolute -top-2 right-2 -translate-x-full translate-y-full"
    />
    <div
      class="absolute border-slate-950 border-[1px] w-[24rem] h-[32.5rem] -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2 rounded-3xl flex flex-col items-center"
    >
      <h1
        class="w-full text-center font-black text-6xl border-b-[1px] border-slate-950 py-5"
      >
        SIGN UP
      </h1>
      <div class="w-full h-full flex flex-col items-center p-4 justify-around">
        <ModalInput type="text" label="아이디" />
        <ModalInput type="password" label="비밀번호" />
        <ModalInput type="password" label="비밀번호 확인" />
        <ModalInput type="text" label="닉네임" />
        <ModalInput type="text" label="이메일" />
        <ModalBtn text="회원가입" />
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
