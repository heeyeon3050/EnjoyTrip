<script setup>
import VSelect from "@/components/common/VSelect.vue";
import CommonBtn from "@/components/common/CommonBtn.vue";
import { createBoard } from "@/api/board";
import { createImage } from "@/api/image";
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
const locationKeyword = ref("");
const locationData = ref([]);
const selectedLocation = ref(null);
const selectedImage = ref("");
const locationOpen = ref(false);
let ps;

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

const locationSearch = () => {
  if (locationKeyword.value === "") return;
  ps.keywordSearch(locationKeyword.value, (data, status, pagination) => {
    if (status === kakao.maps.services.Status.OK) {
      locationData.value = data;
    }
  });
};

watch(() => locationKeyword.value, locationSearch, { deep: true });

const initSearch = () => {
  ps = new kakao.maps.services.Places();
};

const onChangeBoardKey = (value) => {
  category.value = value;
};

const onSubmit = () => {
  if (title.value === "" || content.value === "") return;
  let frm = new FormData();
  let photoFile = document.getElementById("file");
  frm.append("image", photoFile.files[0]);
  createImage(
    frm,
    (response) => {
      console.log(response);
      createBoard(
        {
          title: title.value,
          category: category.value,
          content: content.value,
          latitude: latitude.value,
          longitude: longitude.value,
          imageUrl: response.data,
        },
        (response) => {
          console.log(response);
          router.replace(`/board/${response.data.data.id}`);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    (error) => {
      console.log(error);
    }
  );
};

const setLocation = (location) => {
  latitude.value = location.y;
  longitude.value = location.x;
  selectedLocation.value = location;
  locationKeyword.value = "";
  locationOpen.value = false;
};

const handleFileChange = (e) => {
  selectedImage.value = e.target.files[0].name;
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
              <div
                class="p-2 text-gray-500 flex items-center space-x-2 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600"
              >
                <label for="file">
                  <div
                    class="p-2 space-x-3 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100"
                  >
                    <svg
                      :class="{
                        'w-4 h-4': true,
                        'text-blue-600': selectedImage !== '',
                      }"
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
                  </div>
                </label>
                <input
                  class="hidden"
                  type="file"
                  name="file"
                  id="file"
                  @change="handleFileChange"
                />
                {{ selectedImage }}
              </div>
              <div
                @click="locationOpen = !locationOpen"
                class="p-2 text-gray-500 flex items-center space-x-2 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600"
              >
                <div>
                  <svg
                    :class="{
                      'w-4 h-4': true,
                      'text-blue-600': selectedLocation !== null,
                    }"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="currentColor"
                    viewBox="0 0 16 20"
                  >
                    <path
                      d="M8 0a7.992 7.992 0 0 0-6.583 12.535 1 1 0 0 0 .12.183l.12.146c.112.145.227.285.326.4l5.245 6.374a1 1 0 0 0 1.545-.003l5.092-6.205c.206-.222.4-.455.578-.7l.127-.155a.934.934 0 0 0 .122-.192A8.001 8.001 0 0 0 8 0Zm0 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6Z"
                    />
                  </svg>
                </div>
                {{ selectedLocation?.place_name }}
                <span class="sr-only">Embed map</span>
              </div>
            </div>
          </div>
        </div>
        <template v-if="locationOpen">
          <div class="w-full h-60">
            <div class="h-14 flex justify-between">
              <input
                type="text"
                v-model="locationKeyword"
                class="w-full p-4 text-2xl placeholder:text-slate-950 placeholder:text-2xl border-2 border-slate-200 focus:outline-none"
                placeholder="검색어 입력"
              />
            </div>
            <div class="w-full h-44 overflow-y-scroll scrollbar-hide">
              <div class="w-full">
                <div
                  v-for="location in locationData"
                  :key="location"
                  class="w-full h-10"
                  @click="setLocation(location)"
                >
                  {{ location.place_name }} ( {{ location.address_name }})
                </div>
              </div>
            </div>
          </div>
        </template>
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
