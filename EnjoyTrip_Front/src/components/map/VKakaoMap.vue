<script setup>
import { ref, watch, onMounted } from "vue";
import marker from "@/assets/marker.png";
import selectedMarker from "@/assets/selectedMarker.png";

var map;
const positions = ref([]);
const markers = ref([]);

const props = defineProps({
  list: Array,
  selected: Object,
  latitude: Number,
  longitude: Number,
});

watch(
  () => props.selected.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(
      props.selected.latitude,
      props.selected.longitude - 0.001
    );
    loadMarkers();
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

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

watch(
  () => props.list,
  () => {
    positions.value = [];
    if (props.list.length > 0) {
      props.list.forEach((location) => {
        let obj = {};
        obj.latlng = new kakao.maps.LatLng(
          location.latitude,
          location.longitude
        );
        obj.title = location.title;
        obj.id = location.id;

        positions.value.push(obj);
      });
      loadMarkers(location.category);
    }
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(props.latitude, props.longitude),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지의 이미지 크기 입니다
  const imgSize = new kakao.maps.Size(64, 55);
  const selectedImgSize = new kakao.maps.Size(54, 55);
  const markerImage = new kakao.maps.MarkerImage(marker, imgSize);
  const selectedMarkerImage = new kakao.maps.MarkerImage(
    selectedMarker,
    selectedImgSize
  );

  let selectedLocation = null;

  // 마커를 생성합니다
  markers.value = [];
  positions.value.forEach((position) => {
    const selected = position.id === props.selected.id;
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      image: selected ? selectedMarkerImage : markerImage, // 마커의 이미지
    });

    if (!selected) {
      markers.value.push(marker);
    } else {
      selectedLocation = marker;
    }
  });

  if (selectedLocation) {
    markers.value.push(selectedLocation);
  }
  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100vw;
  height: 100vh;
  position: absolute;
  z-index: 0;
}
</style>
