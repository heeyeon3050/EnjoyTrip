import { localAxios } from "@/util/http-commons";

const local = localAxios();

function createBoard(board, success, fail) {
  console.log("boardjs article", board);
  local.post(`/board/create`, JSON.stringify(board)).then(success).catch(fail);
}

function searchBoard(param, success, fail) {
  local.get(`/board/search`, { params: param }).then(success).catch(fail);
}

function getBoardById(boardId, success, fail) {
  local.get(`/board/${boardId}`).then(success).catch(fail);
}

export { createBoard, searchBoard };
