import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listAttraction(param, success, fail) {
  local.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("accessToken");
  local
    .get(`/attraction/list`, {
      params: param,
      paramsSerializer: {
        indexes: null, // no brackets at all
      },
    })
    .then(success)
    .catch(fail);
}

function likeAttraction(attractionId, success, fail) {
  local.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("accessToken");
  local.post(`/attraction/${attractionId}/like`).then(success).catch(fail);
}

export { listAttraction, likeAttraction };
