import { localAxios } from "@/util/http-commons";

const local = localAxios();

function createImage(image, success, fail) {
  local
    .post(`/s3/image/new`, image, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

export { createImage };
