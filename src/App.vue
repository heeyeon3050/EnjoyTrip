<script setup>
import { onMounted } from "vue";
import { RouterView } from "vue-router";

onMounted(() => {
  var canvas = document.getElementById("container");
  var clone = document.getElementById("blurCanvasBottom");

  var cloneCtx = clone.getContext("2d");
  var ctx = canvas.getContext("2d");

  var w = document.getElementById("blurCanvasTop").width;
  var h = document.getElementById("blurCanvasTop").height;

  var ww = window.innerWidth;
  var wh = window.innerHeight;

  console.log(ww, wh);
  canvas.width = ww;
  canvas.height = wh;
  var partCount = 100;
  var particles = [];

  function particle() {
    this.color = "rgba(255,255,255," + Math.random() / 3 + ")";
    console.log(this.color);
    this.x = randomInt(0, ww);
    this.y = randomInt(0, wh);
    this.direction = {
      x: -1 + Math.random() * 2,
      y: -1 + Math.random() * 2,
    };
    this.vx = 0.3 * Math.random();
    this.vy = 0.3 * Math.random();
    this.radius = randomInt(2, 3);
    this.float = function () {
      this.x += this.vx * this.direction.x;
      this.y += this.vy * this.direction.y;
    };
    this.changeDirection = function (axis) {
      this.direction[axis] *= -1;
    };
    this.boundaryCheck = function () {
      if (this.x >= ww) {
        this.x = ww;
        this.changeDirection("x");
      } else if (this.x <= 0) {
        this.x = 0;
        this.changeDirection("x");
      }
      if (this.y >= wh) {
        this.y = wh;
        this.changeDirection("y");
      } else if (this.y <= 0) {
        this.y = 0;
        this.changeDirection("y");
      }
    };
    this.draw = function () {
      ctx.beginPath();
      ctx.fillStyle = this.color;
      ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
      ctx.fill();
    };
  }
  function clearCanvas() {
    cloneCtx.clearRect(0, 0, ww, wh);
    ctx.clearRect(0, 0, ww, wh);
  }
  function createParticles() {
    for (let i = 0; i < partCount; i++) {
      var p = new particle();
      particles.push(p);
    }
  }
  function drawParticles() {
    for (let i = 0; i < particles.length; i++) {
      let p = particles[i];
      p.draw();
    }
  }
  function updateParticles() {
    for (var i = particles.length - 1; i >= 0; i--) {
      let p = particles[i];
      p.float();
      p.boundaryCheck();
    }
  }
  createParticles();
  drawParticles();
  function animateParticles() {
    clearCanvas();
    drawParticles();
    updateParticles();
    cloneCtx.drawImage(canvas, 0, 0);
    requestAnimationFrame(animateParticles);
  }
  requestAnimationFrame(animateParticles);
  cloneCtx.drawImage(canvas, 0, 0);

  window.addEventListener("resize", function () {
    const ww = window.innerWidth;
    const wh = window.innerHeight;
    canvas.width = ww;
    canvas.height = wh;
    clearCanvas();
    particles = [];
    createParticles();
    drawParticles();
  });
  function randomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }
  function velocityInt(min, max) {
    return Math.random() * (max - min + 1) + min;
  }
});
</script>

<template>
  <div class="grid grid-cols-12">
    <router-view></router-view>
  </div>
  <!-- <div
    class="bg-slate-700 w-screen h-screen fixed -z-50 top-0 left-0"
  ></div> -->
  <canvas
    class="container w-screen h-screen -z-30 top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 fixed"
    id="container"
    role="main"
  ></canvas>
  <div class="bg w-screen h-screen fixed top-0 left-0 bg-slate-700 -z-40">
    <div class="blur blurTop">
      <canvas class="canvas" id="blurCanvasTop"></canvas>
    </div>
    <div class="blur blurBottom">
      <canvas class="canvas w-screen h-screen" id="blurCanvasBottom"></canvas>
    </div>
  </div>
</template>

<style scoped>
@import url("https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css");

* {
  font-family: "NanumSquare";
}

.eng {
  font-family: "SF_HambakSnow";
}

.title {
  font-family: "NEXON Lv2 Gothic";
}

.bg {
  background: linear-gradient(25deg, #3b3b3b, #252525);
}

.blur {
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
}
.blurTop {
  left: 40%;
  top: -110%;
  transform: rotate(20deg);
  transform-origin: 0 100%;
}
.blurBottom {
  left: -60%;
  top: 100%;
  transform: rotate(20deg);
  transform-origin: 100% 0%;
}
</style>
