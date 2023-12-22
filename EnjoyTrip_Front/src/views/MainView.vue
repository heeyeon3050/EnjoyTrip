<script setup>
import HeadingNavbar from "@/components/layout/HeadingNavbar.vue";
import { ref, onMounted } from "vue";
import * as THREE from "three";

let gallery;

let fragment = `
uniform float time;
uniform float progress;
uniform sampler2D texture1;
uniform sampler2D texture2;
uniform vec2 pixels;
uniform vec2 uvRate1;
uniform vec2 accel;

varying vec2 vUv;
varying vec2 vUv1;
varying vec4 vPosition;

vec2 mirrored(vec2 v) {
  vec2 m = mod(v, 2.);
  return mix(m, 2.0 - m, step(1.0, m));
}

float tri(float p) {
  return mix(p, 1.0 - p, step(0.5, p))*2.;
}

void main() {
  vec2 uv = gl_FragCoord.xy/pixels.xy;
  float p = fract(progress);

  float delayValue = p*7. - uv.y*2. + uv.x - 2.;

  delayValue = clamp(delayValue, 0., 1.);

  vec2 translateValue = p + delayValue*accel;
  vec2 translateValue1 = vec2(-0.5, 1.)*translateValue;
  vec2 translateValue2 = vec2(-0.5, 1.)*(translateValue - 1. - accel);

  vec2 w = sin( sin(time)*vec2(0, 0.3) + vUv.yx*vec2(0, 4.) )*vec2(0, 0.5);
  vec2 xy = w*(tri(p)*0.5 + tri(delayValue)*0.5);

  vec2 uv1 = vUv1 + translateValue1 + xy;
  vec2 uv2 = vUv1 + translateValue2 + xy;

  vec4 rgba1 = texture2D(texture1, mirrored(uv1));
  vec4 rgba2 = texture2D(texture2, mirrored(uv2));

  vec4 rgba = mix(rgba1, rgba2, delayValue);
  gl_FragColor = rgba;
  // gl_FragColor = vec4(tri(progress));
}
`;
let vertex = `
uniform float time;
varying vec2 vUv;
varying vec2 vUv1;
varying vec4 vPosition;

uniform sampler2D texture1;
uniform sampler2D texture2;
uniform vec2 pixels;
uniform vec2 uvRate1;

void main() {
  vUv = uv;
  vec2 _uv = uv - 0.5;
  vUv1 = _uv;
  vUv *= uvRate1.xy;

  vUv1 += 0.5;

  gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );
}
`;

const target = ref();

let camera,
  // controls,
  scene,
  renderer,
  material,
  plane;

function init() {
  gallery = [
    new THREE.VideoTexture(document.getElementById("video1")),
    new THREE.VideoTexture(document.getElementById("video2")),
    new THREE.VideoTexture(document.getElementById("video3")),
    new THREE.VideoTexture(document.getElementById("video4")),
    new THREE.VideoTexture(document.getElementById("video5")),
  ];
  scene = new THREE.Scene();

  renderer = new THREE.WebGLRenderer();

  renderer.setPixelRatio(window.devicePixelRatio);
  renderer.setSize(window.innerWidth, window.innerHeight);

  camera = new THREE.PerspectiveCamera(
    70,
    window.innerWidth / window.innerHeight,
    0.001,
    100
  );
  camera.position.set(0, 0, 1);

  // controls = new OrbitControls(camera, renderer.domElement);

  material = new THREE.ShaderMaterial({
    side: THREE.DoubleSide,
    uniforms: {
      time: { type: "f", value: 0 },
      pixels: {
        type: "v2",
        value: new THREE.Vector2(window.innerWidth, window.innerHeight),
      },
      accel: {
        type: "v2",
        value: new THREE.Vector2(0.5, 2),
      },
      progress: { type: "f", value: 0 },
      uvRate1: {
        value: new THREE.Vector2(1, 1),
      },
      texture1: {
        value: gallery[0],
      },
      texture2: {
        value: gallery[1],
      },
    },
    // wireframe: true,
    vertexShader: vertex,
    fragmentShader: fragment,
  });

  plane = new THREE.Mesh(new THREE.PlaneGeometry(1, 1, 1, 1), material);
  scene.add(plane);

  resize();
}

window.addEventListener("resize", resize);

function resize() {
  const w = window.innerWidth;
  const h = window.innerHeight;
  renderer.setSize(w, h);
  camera.aspect = w / h;

  material.uniforms.uvRate1.value.y = h / w;

  // calculate scene
  let dist = camera.position.z - plane.position.z;
  let height = 1;
  camera.fov = 2 * (180 / Math.PI) * Math.atan(height / (2 * dist));

  // if (w / h > 1) {
  plane.scale.x = w / h;
  // }

  camera.updateProjectionMatrix();
}

let time = 0;
function animate() {
  time = time + 0.05;
  material.uniforms.time.value = time;

  requestAnimationFrame(animate);
  render();
}

function render() {
  renderer.render(scene, camera);
}

onMounted(() => {
  document.getElementById("video1").play();
  document.getElementById("video1").playbackRate = 0.75;
  document.getElementById("video2").play();
  document.getElementById("video3").play();
  document.getElementById("video4").play();
  document.getElementById("video5").play();
  init();
  target.value.appendChild(renderer.domElement);
  animate();

  raf();
});

let speed = 0;
let position = 0;
document.addEventListener("wheel", function (event) {
  speed += event.deltaY * 0.0003;
});

function raf() {
  position += speed;
  speed *= 0.7;

  let i = Math.round(position);
  let dif = i - position;

  dif = dif < 0 ? Math.max(dif, -0.02) : Math.max(dif, +0.02);

  position += dif * 0.035;
  if (Math.abs(i - position) < 0.001) {
    position = i;
  }

  const dot = document.querySelector(".dot");
  if (dot) {
    dot.style.top = position * 200 + "px";
  }
  material.uniforms.progress.value = position;

  let curslide = Math.floor(position) % gallery.length;
  let nextslide = (Math.floor(position) + 1) % gallery.length;

  material.uniforms.texture1.value = gallery[curslide];
  material.uniforms.texture2.value = gallery[nextslide];

  window.requestAnimationFrame(raf);
}
</script>

<template>
  <HeadingNavbar></HeadingNavbar>
  <div
    class="absolute top-0 left-0 w-screen h-screen -z-10 bg-slate-800/40"
  ></div>
  <div ref="target" @click="onClick" class="absolute top-0 left-0 -z-20"></div>
  <div
    class="eng absolute left-1/2 top-1/2 z-10 -translate-x-1/2 -translate-y-full text-3xl flex space-x-20 text-slate-200"
  >
    <div class="flex title">
      <span class="block">E</span>
      <span class="block">N</span>
      <span class="block">J</span>
      <span class="block">O</span>
      <span class="block">Y</span>
    </div>
    <div class="flex title">
      <span class="block">T</span>
      <span class="block">R</span>
      <span class="block">I</span>
      <span class="block">P</span>
    </div>
  </div>
  <div
    class="scroll opacity-30 absolute left-1/2 bottom-0 z-10 -translate-x-1/2 -translate-y-full"
  >
    <img class="h-20" src="@/assets/scroll.png" />
  </div>

  <video
    id="video1"
    loop
    muted
    crossOrigin="anonymous"
    playsinline
    style="display: none"
  >
    <source src="../assets/video/tram.mp4" />
  </video>
  <video
    id="video2"
    loop
    muted
    crossOrigin="anonymous"
    playsinline
    style="display: none"
  >
    <source src="../assets/video/illuminated.mp4" />
  </video>
  <video
    id="video3"
    loop
    muted
    crossOrigin="anonymous"
    playsinline
    style="display: none"
  >
    <source src="../assets/video/sea.mp4" />
  </video>
  <video
    id="video4"
    loop
    muted
    crossOrigin="anonymous"
    playsinline
    style="display: none"
  >
    <source src="../assets/video/temple.mp4" />
  </video>
  <video
    id="video5"
    loop
    muted
    crossOrigin="anonymous"
    playsinline
    style="display: none"
  >
    <source src="../assets/video/buildings.mp4" />
  </video>
</template>

<style scoped>
.webgl {
  position: fixed;
  top: 0;
  left: 0;
  outline: none;
}

@font-face {
  font-family: "NEXON Lv2 Gothic";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.title > * {
  font-family: "NEXON Lv2 Gothic";
  animation: turn 6s ease-in;
  display: inline-block;
  letter-spacing: 3vw;
  text-shadow: 0 0 0.5rem #def;
  text-transform: uppercase;
  transform: scaleY(4);
}

@keyframes turn {
  0% {
    text-shadow: 0 0 0 #def;
    transform: scaleY(4) rotateY(90deg);
  }
  100% {
    text-shadow: 0 0 0.5rem #def;
    transform: scaleY(4) rotateY(0);
  }
}

.scroll {
  animation: scroll 2s ease-in-out infinite;
}

@keyframes scroll {
  0% {
    transform: translateY(-100%);
  }
  70% {
    transform: translateY(-70%);
  }
  100% {
    transform: translateY(-100%);
  }
}
</style>
