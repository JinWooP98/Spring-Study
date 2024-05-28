
// 서버에 댓글 등록을 요청하는 비동기 함수
import {BASE_URL} from "./reply.js";
import {renderReplies} from "./getReply.js";

export async function fetchPostReplies() {

    const textInput = document.getElementById('newReplyText');
    const writerInput = document.getElementById('newReplyWriter');

    const res = await fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            text: textInput.value,
            author: writerInput.value,
            bno: document.getElementById('wrap').dataset.bno
        })
    });

    const replies = await res.json();

    textInput.value = '';
    writerInput.value = '';


    renderReplies(replies);

}

