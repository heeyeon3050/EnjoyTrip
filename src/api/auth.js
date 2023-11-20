import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function login(param, success, fail) {
  console.log("param", param);
  await local.post(`/api/auth/login`, param).then(success).catch(fail);
  console.log("userConfirm ok");
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/api/auth/reissue`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { login, tokenRegeneration, logout };
