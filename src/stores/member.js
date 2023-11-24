import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";

import { findById } from "@/api/user";
import { login, tokenRegeneration, logout } from "@/api/auth";
import { httpStatusCode } from "@/util/http-status";
import { addMessage } from "@/util/message";

export const useMemberStore = defineStore(
  "memberStore",
  () => {
    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const isValidToken = ref(false);
    const loginUserId = ref(null);
    const loginUserName = ref(null);

    const userLogin = async (loginUser) => {
      await login(
        loginUser,
        (response) => {
          // console.log("login ok!!!!", response.status);
          // console.log("login ok!!!!", httpStatusCode.CREATE);
          if (response.status === httpStatusCode.OK) {
            console.log(response);
            let {
              data: { data },
            } = response;
            console.log("data", data);
            let accessToken = data["accessToken"];
            let refreshToken = data["refreshToken"];
            let userId = data["userId"];
            console.log("accessToken", accessToken);
            console.log("refreshToken", refreshToken);
            console.log("userId", userId);
            isLogin.value = true;
            isLoginError.value = false;
            isValidToken.value = true;
            loginUserId.value = userId;
            sessionStorage.setItem("accessToken", accessToken);
            sessionStorage.setItem("refreshToken", refreshToken);
            sessionStorage.setItem("userId", userId);
            console.log("sessiontStorage에 담았다", isLogin.value);
          } else {
            console.log("로그인 실패했다");
            addMessage("로그인에 실패했습니다", "bg-red-400");
            isLogin.value = false;
            isLoginError.value = true;
            isValidToken.value = false;
            loginUserId.value = null;
          }
        },
        (error) => {
          addMessage("로그인에 실패했습니다", "bg-red-400");
          console.error(error);
        }
      );
    };

    const getUserInfo = async () => {
      await findById(
        loginUserId.value,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            userInfo.value = response.data.data;
            loginUserName.value = userInfo.value.name;
            console.log("3. getUserInfo data >> ", userInfo.value.name);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.error(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          isValidToken.value = false;

          await tokenRegenerate();
        }
      );
    };

    const tokenRegenerate = async () => {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("accessToken")
      );
      await tokenRegeneration(
        {
          accessToken: sessionStorage.getItem("accessToken"),
          refreshToken: sessionStorage.getItem("refreshToken"),
        },
        (response) => {
          if (response.status === httpStatusCode.OK) {
            let accessToken = response.data["accessToken"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("accessToken", accessToken);
            isValidToken.value = true;
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === httpStatusCode.UNAUTHORIZED) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              userInfo.value.userid,
              (response) => {
                if (response.status === httpStatusCode.OK) {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                isLogin.value = false;
                userInfo.value = null;
                isValidToken.value = false;
                router.push({ name: "user-login" });
              },
              (error) => {
                console.error(error);
                isLogin.value = false;
                userInfo.value = null;
              }
            );
          }
        }
      );
    };

    const userLogout = async () => {
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("refreshToken");
      sessionStorage.removeItem("userId");

      isLogin.value = false;
      userInfo.value = null;
      isValidToken.value = false;
      loginUserId.value = null;
      loginUserName.value = null;
      // await logout(
      //   userid,
      //   (response) => {
      //     if (response.status === httpStatusCode.OK) {
      //       isLogin.value = false;
      //       userInfo.value = null;
      //       isValidToken.value = false;
      //     } else {
      //       console.error("유저 정보 없음!!!!");
      //     }
      //   },
      //   (error) => {
      //     console.log(error);
      //   }
      // );
    };

    return {
      loginUserId,
      loginUserName,
      isLogin,
      isLoginError,
      userInfo,
      isValidToken,
      userLogin,
      getUserInfo,
      tokenRegenerate,
      userLogout,
    };
  },
  { persist: true }
);
