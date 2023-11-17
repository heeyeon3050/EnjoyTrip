<script setup>
import CommonBtn from "@/components/common/CommonBtn.vue";
import CommentListItem from "@/components/comments/CommentListItem.vue";
import { ref, watch, onMounted } from "vue";

var map;
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});
// watch(
//   () => props.stations.value,
//   () => {
//     positions.value = [];
//     props.stations.forEach((station) => {
//       let obj = {};
//       obj.latlng = new kakao.maps.LatLng(station.lat, station.lng);
//       obj.title = station.statNm;

//       positions.value.push(obj);
//     });
//     loadMarkers();
//   },
//   { deep: true }
// );

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  map.setDraggable(false);
  map.setZoomable(false);

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });
    markers.value.push(marker);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};
</script>

<template>
  <div class="w-full h-fit flex flex-col mt-10 border-y-2 border-slate-300">
    <div class="h-20 bg-slate-200 flex items-center p-4 justify-between">
      <h1 class="font-bold text-2xl px-5">게시글 제목</h1>
      <h3 class="px-5 font-medium text-slate-500">2023.11.14 16:19</h3>
    </div>
    <div
      class="h-12 border-y-[1px] border-slate-300 flex items-center p-4 justify-between"
    >
      <div class="flex px-2">
        <h3 class="text-lg">작성자</h3>
      </div>
      <div class="flex h-full w-32 justify-between items-center">
        <div class="flex space-x-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M6.633 10.5c.806 0 1.533-.446 2.031-1.08a9.041 9.041 0 012.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 00.322-1.672V3a.75.75 0 01.75-.75A2.25 2.25 0 0116.5 4.5c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 01-2.649 7.521c-.388.482-.987.729-1.605.729H13.48c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 00-1.423-.23H5.904M14.25 9h2.25M5.904 18.75c.083.205.173.405.27.602.197.4-.078.898-.523.898h-.908c-.889 0-1.713-.518-1.972-1.368a12 12 0 01-.521-3.507c0-1.553.295-3.036.831-4.398C3.387 10.203 4.167 9.75 5 9.75h1.053c.472 0 .745.556.5.96a8.958 8.958 0 00-1.302 4.665c0 1.194.232 2.333.654 3.375z"
            />
          </svg>
          <span>15</span>
        </div>
        <div class="flex space-x-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M7.5 8.25h9m-9 3H12m-9.75 1.51c0 1.6 1.123 2.994 2.707 3.227 1.129.166 2.27.293 3.423.379.35.026.67.21.865.501L12 21l2.755-4.133a1.14 1.14 0 01.865-.501 48.172 48.172 0 003.423-.379c1.584-.233 2.707-1.626 2.707-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"
            />
          </svg>
          <span>20</span>
        </div>
      </div>
    </div>
    <div class="w-full min-h-[50vh]">
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus eius
        ratione minus distinctio voluptates harum possimus placeat corporis qui
        quis quas nostrum aliquid quod quibusdam explicabo, velit cupiditate
        optio omnis?
      </p>
    </div>
    <div id="map" class="w-full h-80 my-4 border-4"></div>
    <div class="w-full flex justify-end my-4 space-x-3">
      <CommonBtn text="수정" />
      <CommonBtn text="삭제" />
    </div>
  </div>

  <div class="w-full flex flex-col my-4">
    <div class="w-full flex flex-col p-4 mb-12">
      <h1 class="my-6 font-semibold text-xl">댓글 15개</h1>
      <div class="flex items-center space-x-6">
        <div class="w-16 h-16 rounded-full shrink-0 bg-slate-800"></div>
        <input
          type="text"
          name=""
          id=""
          placeholder="댓글 작성..."
          class="w-full h-16 bg-blue-50/30 rounded-t-md focus:outline-none px-4 focus:border-b-2 focus:border-blue-600"
        />
      </div>
      <div class="flex justify-end p-4"><CommonBtn text="등록" /></div>
      <div class="w-full"></div>
    </div>

    <div
      class="w-full h-fit p-2 flex flex-col overflow-y-scroll scrollbar-hide"
    >
      <div class="space-y-5">
        <CommentListItem
          v-for="i in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
          :key="i"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
