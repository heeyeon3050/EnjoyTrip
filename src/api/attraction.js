import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listAttraction(param, success, fail) {
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

export { listAttraction };
