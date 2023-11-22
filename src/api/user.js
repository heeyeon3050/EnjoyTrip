import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userJoin(param, success, fail) {
  console.log("param", param);
  await local.post(`/user/join`, param).then(success).catch(fail);
  console.log("userJoin ok");
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.get(`/user/${userid}`).then(success).catch(fail);
}

async function userUpdate(userid, param, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.patch(`/user/${userid}`, param).then(success).catch(fail);
}

async function idCheck(userId, success, fail) {
  await local.get(`/user/${userId}/IdCheck`).then(success).catch(fail);
}

async function nameCheck(name, success, fail) {
  await local.get(`/user/${name}/NameCheck`).then(success).catch(fail);
  console.log("userJoin ok");
}

async function emailCheck(email, success, fail) {
  await local.get(`/user/${email}/EmailCheck`).then(success).catch(fail);
  console.log("userJoin ok");
}

export { userJoin, findById, userUpdate, idCheck, nameCheck, emailCheck };
