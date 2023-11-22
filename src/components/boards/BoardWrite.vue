<script setup>
import VSelect from "@/components/common/VSelect.vue";
import CommonBtn from "@/components/common/CommonBtn.vue";
import { createBoard } from "@/api/board";
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const boardCategory = [
  { value: "NORMAL", text: "일반" },
  { value: "TIP", text: "팁" },
  { value: "REVIEW", text: "후기" },
];

const title = ref("");
const content = ref("");
const category = ref("NORMAL");
const latitude = ref(0);
const longitude = ref(0);
const locationKey = ref("KEYWORD");
const locationKeyword = ref("");

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initSearch();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initSearch());
    document.head.appendChild(script);
  }
});

watch(() => locationKeyword.value, locationSearch, { deep: true });

const initSearch = () => {
  var ps = new kakao.maps.services.Places();

  ps.keywordSearch("이태원 맛집", placesSearchCB);

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < data.length; i++) {
        displayMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    }
  }
};

const onChangeBoardKey = (value) => {
  category.value = value;
};

const onChangeLocationKey = () => {
  locationKey.value = value;
};

const onSubmit = () => {
  if (title.value === "" || content.value === "") return;

  createBoard(
    {
      title: title.value,
      category: category.value,
      content: content.value,
      latitude: latitude.value,
      longitude: longitude.value,
    },
    (response) => {
      console.log(response);
      router.replace(`/board/${response.data.data.id}`);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="w-full">
    <div class="w-full h-20 flex justify-between my-10">
      <VSelect
        :selectOption="boardCategory"
        @onKeySelect="onChangeBoardKey"
        v-model="category"
        class="w-32 border-2 bg-gray-50 text-xl text-slate-700 text-center font-semibold border-slate-200 rounded-l-xl"
      />
      <input
        type="text"
        v-model="title"
        class="w-full p-4 text-2xl placeholder:text-slate-950 placeholder:text-2xl border-2 border-slate-200 rounded-r-xl focus:outline-none"
        placeholder="게시글 제목..."
      />
    </div>
    <div class="w-full">
      <div class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50">
        <div class="flex items-center justify-between px-3 py-2 border-b">
          <div class="flex flex-wrap items-center divide-gray-200">
            <div class="flex items-center space-x-1">
              <button
                type="button"
                class="p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100"
              >
                <svg
                  class="w-4 h-4"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 12 20"
                >
                  <path
                    stroke="currentColor"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M1 6v8a5 5 0 1 0 10 0V4.5a3.5 3.5 0 1 0-7 0V13a2 2 0 0 0 4 0V6"
                  />
                </svg>
                <span class="sr-only">Attach file</span>
              </button>
              <button
                type="button"
                class="p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600"
              >
                <svg
                  class="w-4 h-4"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 16 20"
                >
                  <path
                    d="M8 0a7.992 7.992 0 0 0-6.583 12.535 1 1 0 0 0 .12.183l.12.146c.112.145.227.285.326.4l5.245 6.374a1 1 0 0 0 1.545-.003l5.092-6.205c.206-.222.4-.455.578-.7l.127-.155a.934.934 0 0 0 .122-.192A8.001 8.001 0 0 0 8 0Zm0 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6Z"
                  />
                </svg>
                <span class="sr-only">Embed map</span>
              </button>
            </div>
          </div>
        </div>
        <div class="w-full h-60">
          <div class="h-14 flex justify-between">
            <VSelect
              :selectOption="boardCategory"
              @onKeySelect="onChangeLocationKey"
              v-model="category"
              class="w-32 border-2 bg-gray-50 text-xl text-slate-700 text-center font-semibold border-slate-200"
            />
            <input
              type="text"
              v-model="locationKeyword"
              class="w-full p-4 text-2xl placeholder:text-slate-950 placeholder:text-2xl border-2 border-slate-200 focus:outline-none"
              placeholder="검색어 입력"
            />
          </div>
        </div>
        <div class="px-4 py-2 bg-white rounded-b-lg dark:bg-gray-800">
          <textarea
            id="editor"
            rows="20"
            v-model="content"
            class="resize-none block w-full p-10 text-xl text-gray-800 bg-white border-0 focus:outline-none"
            placeholder="내용을 입력하세요.."
            required
          ></textarea>
        </div>
      </div>
      <div class="flex justify-end space-x-4">
        <router-link :to="{ name: 'board-list' }">
          <CommonBtn text="취소" />
        </router-link>
        <CommonBtn
          text="등록"
          @click="onSubmit"
          :disabled="title === '' || content === ''"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
