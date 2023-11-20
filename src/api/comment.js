import { localAxios } from "@/util/http-commons";

const local = localAxios();

function createComment(comment, success, fail) {
  console.log("comment ", comment);
  local
    .post(`/comment/create`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function CommentListByBoardId(boardId, param, success, fail) {
  local
    .get(`/comment/board/${boardId}`, { params: param })
    .then(success)
    .catch(fail);
}

export { createComment, CommentListByBoardId };
