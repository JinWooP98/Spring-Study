
import {fetchReplies} from "./getReply.js";
import {fetchPostReplies} from "./postReply.js";

// ===== 전역 변수 =====
export const BASE_URL = `http://localhost:8383/api/v1/replies`;

// ===== 함수 정의 =====

// ===== 실행 코드 =====
document.getElementById('replyAddBtn').addEventListener('click',  e => {
    fetchPostReplies();
})
// 댓글 목록 서버에서 불러오기
fetchReplies();