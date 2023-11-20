import { localAxios } from "@/util/http-commons";

const local = localAxios();

function searchAttraction(param, success, fail) {
  local.get(`/attraction/search`, { params: param }).then(success).catch(fail);
}

export { searchAttraction };
