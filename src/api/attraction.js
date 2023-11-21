import { localAxios } from "@/util/http-commons";

const local = localAxios();

function searchAttraction(param, success, fail) {
  local
    .get(`/attraction/search`, {
      params: param,
      paramsSerializer: {
        indexes: null, // no brackets at all
      },
    })
    .then(success)
    .catch(fail);
}

export { searchAttraction };
