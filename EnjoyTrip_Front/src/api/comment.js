import { localAxios } from "@/util/http-commons";

const local = localAxios();

function createComment(comment, success, fail) {
  local.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("accessToken");
  local
    .post(`/comment/create`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function commentListByBoardId(boardId, param, success, fail) {
  local
    .get(`/comment/board/${boardId}`, { params: param })
    .then(success)
    .catch(fail);
}

function updateComment(commentId, param, success, fail) {
  local.patch(`/comment/${commentId}/update`, param).then(success).catch(fail);
}

function deleteComment(commentId, success, fail) {
  local.patch(`/comment/${commentId}/delete`).then(success).catch(fail);
}

export { createComment, commentListByBoardId, updateComment, deleteComment };
