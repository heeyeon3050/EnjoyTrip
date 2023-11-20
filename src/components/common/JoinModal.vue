<script setup>
import ModalInput from "@/components/modal/ModalInput.vue";
import ModalBtn from "@/components/modal/ModalBtn.vue";
import { userJoin } from "@/api/user";
import { ref } from "vue";

const params = defineProps({ login: Function });

const userId = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");

const userIdError = ref("");
const passwordError = ref("");
const password2Error = ref("");
const nameError = ref("");
const emailError = ref("");

const onSubmit = () => {
  if (userId.value === "") {
    userIdError.value = "아이디를 입력하세요";
    return;
  }
  if (password.value === "") {
    passwordError.value = "아이디를 입력하세요";
    return;
  }
  if (password2.value === "") {
    password2Error.value = "아이디를 입력하세요";
    return;
  }
  if (name.value === "") {
    nameError.value = "아이디를 입력하세요";
    return;
  }
  if (email.value === "") {
    emailError.value = "아이디를 입력하세요";
    return;
  }

  if (password.value !== password2.value) {
    password2Error.value = "비밀번호가 서로 다릅니다";
    return;
  }
  const newUser = {
    userId: userId.value,
    password: password.value,
    name: name.value,
    email: email.value,
  };

  console.log(newUser);
  userJoin(
    newUser,
    (response) => {
      console.log(response);
      params.login();
    },
    (error) => {
      console.log(error);
    }
  );
};

const onChangeUserId = (event) => {
  userIdError.value = "";
  userId.value = event.target.value;
};
const onChangePassword = (event) => {
  passwordError.value = "";
  password.value = event.target.value;
};
const onChangePassword2 = (event) => {
  password2Error.value = "";
  password2.value = event.target.value;
};
const onChangeName = (event) => {
  nameError.value = "";
  name.value = event.target.value;
};
const onChangeEmail = (event) => {
  emailError.value = "";
  email.value = event.target.value;
};
</script>

<template>
  <form
    method="post"
    @submit.prevent="onSubmit"
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
        <ModalInput
          type="text"
          label="아이디"
          :value="userId"
          :error="userIdError !== ''"
          @change="onChangeUserId"
        />
        <span class="h-3 p-0 -mt-5 text-red-400">{{ userIdError }}</span>
        <ModalInput
          type="password"
          label="비밀번호"
          :value="password"
          :error="passwordError !== ''"
          @change="onChangePassword"
        />
        <span class="h-3 p-0 -mt-5 text-red-400">{{ passwordError }}</span>
        <ModalInput
          type="password"
          label="비밀번호 확인"
          :value="password2"
          :error="password2Error !== ''"
          @change="onChangePassword2"
        />
        <span class="h-3 p-0 -mt-5 text-red-400">{{ password2Error }}</span>
        <ModalInput
          type="text"
          label="닉네임"
          :value="name"
          :error="nameError !== ''"
          @change="onChangeName"
        />
        <span class="h-3 p-0 -mt-5 text-red-400">{{ nameError }}</span>
        <ModalInput
          type="text"
          label="이메일"
          :value="email"
          :error="emailError !== ''"
          @change="onChangeEmail"
        />
        <span class="h-3 p-0 -mt-5 text-red-400">{{ emailError }}</span>
        <ModalBtn text="회원가입" />
      </div>
    </div>
  </form>
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
