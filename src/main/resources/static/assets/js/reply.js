
import {fetchReplies, replyDelete} from "./getReply.js";
import {fetchPostReplies} from "./postReply.js";
import {replyPageClickEvent} from "./getReply.js";

// ===== 전역 변수 =====
export const BASE_URL = `http://localhost:8383/api/v1/replies`;

// ===== 함수 정의 =====

// ===== 실행 코드 =====
document.getElementById('replyAddBtn').addEventListener('click',  e => {
    fetchPostReplies();
});

// 댓글 삭제 클릭이벤트
replyDelete();

// 댓글 페이지 클릭이벤트 등록
replyPageClickEvent();
// 댓글 목록 서버에서 불러오기
fetchReplies();