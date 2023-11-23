<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { deleteUser, findById } from "@/api/user";
import noProfile from "@/assets/no_profile.png";
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import noImg from "@/assets/no_image.jpg";

const memberStore = useMemberStore();
const router = useRouter();
const route = useRoute();

const { userLogout } = memberStore;
const { loginUserId } = storeToRefs(memberStore);
const profileUser = ref({});

onMounted(() => {
  findById(
    route.params.userId,
    ({ data: data }) => {
      console.log(data);
      profileUser.value = data.data;
    },
    (error) => {
      console.log(error);
    }
  );
});

const replaceNoImg = (event) => {
  event.target.src = noImg;
};

const replaceNoProfile = (event) => {
  event.target.src = noProfile;
};

const onDeleteUser = () => {
  const comfirmDelete = confirm("탈퇴하시겠습니까?");
  if (!comfirmDelete) return;

  deleteUser(
    userInfo.value.userId,
    ({ data }) => {
      userLogout();
      router.push("/");
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
        <img
          class="w-32 h-32 bg-slate-700 rounded-full bg-cover bg-center shadow-lg"
          :src="profileUser.image_url || ''"
          @error="replaceNoProfile"
        />
        <div class="flex flex-col space-y-4 justify-center">
          <h1 class="text-5xl font-semibold text-slate-200">
            {{ profileUser.name }}
          </h1>
          <h3 class="text-lg text-slate-400">@{{ profileUser.userId }}</h3>
        </div>
      </div>
      <div class="flex items-end space-x-2">
        <router-link
          :to="{
            name: 'user-update',
            params: { userId: profileUser.userId },
          }"
        >
          <CommonBtn text="수정" />
        </router-link>
        <CommonBtn text="탈퇴" @click="onDeleteUser" />
      </div>
    </div>
    <div class="w-full space-y-2">
      <div class="w-full h-10 border-b-2 border-slate-400">
        <h1 class="text-2xl text-slate-400 px-2">저장한 관광지</h1>
      </div>
      <div class="w-full grid grid-cols-6 gap-2">
        <router-link
          v-for="attraction in profileUser.attractions?.slice(0, 6)"
          :key="attraction.id"
          :to="{
            name: 'map',
            query: {
              latitude: attraction.latitude,
              longitude: attraction.longitude,
            },
          }"
        >
          <img
            :src="attraction.image_url"
            @error="replaceNoImg"
            class="w-full aspect-square bg-cover bg-center"
          />
        </router-link>
      </div>
    </div>
    <div class="w-full space-y-2">
      <div class="w-full h-10 border-b-2 border-slate-400">
        <h1 class="text-2xl text-slate-400 px-2">좋아요한 게시물</h1>
      </div>
      <div class="w-full grid grid-cols-6 gap-2">
        <router-link
          v-for="board in profileUser.boards?.slice(0, 6)"
          :key="board.id"
          :to="{
            name: 'board-view',
            params: { boardId: board.id },
          }"
        >
          <img
            :src="board.imageUrl"
            @error="replaceNoImg"
            class="w-full aspect-square bg-cover bg-center"
          />
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
