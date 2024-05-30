
// 회원가입 입력 검증 처리
// 계정 입력 검증
const $idInput = document.getElementById('user_id');
const $passwordInput = document.getElementById('password');
const $pwCheckInput = document.getElementById('password_check');
const $nameInput = document.getElementById('user_name');
const $emailInput = document.getElementById('user_email');

let keywordFlag = false;


function button () {
    const $successInput = document.querySelectorAll('.success');
    const $button = document.getElementById('signup-btn');
    $button.disabled = [...$successInput].length !== 5;
    $button.style.background = ([...$successInput].length !== 5) ? 'gray' : 'orange';
}

// 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(type ,value) {

    const res = await fetch(`http://localhost:8383/members/check?type=${type}&keyword=${value}`);

    const flag =  await res.json();

    keywordFlag = flag;

}

// 이메일 중복검사 비동기 요청 보내기



$idInput.addEventListener('keyup',  async (e)=> {

    // 아이디 검사 정규표현식
    const accountPattern = /^[a-zA-Z0-9]{4,14}$/;

    // 입력값 읽어오기
    const idValue = $idInput.value;



    // 검증 메시지를 입력할 span
    const $idChk = document.getElementById('idChk');

    if(idValue.trim() === '') {
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">[아이디는 필수값입니다.]</b>';
    } else if(!accountPattern.test(idValue)) {
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">[아이디는 4~14글자 사이 영문, 숫자로 입력하세요.]</b>';
    } else {

        // 아이디 중복검사
        await fetchDuplicateCheck('account' ,idValue);

        if(keywordFlag) {
            $idInput.style.borderColor = 'red';
            $idChk.innerHTML = '<b class="warning">[아이디가 중복되었습니다. 다른 아이디를 사용하세요.]</b>';
        } else {
            $idInput.style.borderColor = 'skyblue';
            $idChk.innerHTML = '<b class="success">[사용가능한 아이디입니다.]</b>';

        }

    }
    button();
})

// 비밀번호 검사
$passwordInput.addEventListener('keyup',  (e) => {
    // 패스워드 검사 정규표현식
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*?_~])[A-Za-z\d!@#$%^&*?_~]{8,}$/;;

    // 입력값 읽어오기
    const passwordValue = $passwordInput.value;

    // 검증 메시지 입력할 span
    const $pwChk = document.getElementById('pwChk');

    if(passwordValue.trim() === '') {
        $passwordInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">[비밀번호는 필수값입니다.]</b>';
    } else if(!passwordPattern.test(passwordValue)) {
        $passwordInput.style.borderColor = 'red';
        $pwChk.innerHTML = '<b class="warning">[비밀번호는 영문,숫자,특수기호를 사용해야 합니다.]</b>';
    } else {
        $passwordInput.style.borderColor = 'skyblue';
        $pwChk.innerHTML = '<b class="success">[사용가능한 비밀번호 입니다.]</b>';

    }

    button();
})

// 비밀번호 확인 검사
$pwCheckInput.addEventListener('keyup', (e) => {

    const pw1 = $passwordInput.value;
    const pw2 = $pwCheckInput.value;

    const $pwChk2 = document.getElementById('pwChk2');

    if(pw1 !== pw2) {
        $pwCheckInput.style.borderColor = 'red';
        $pwChk2.innerHTML = '<b class="warning">[비밀번호가 일치하지 않습니다.]</b>';
    } else {
        $pwCheckInput.style.borderColor = 'skyblue';
        $pwChk2.innerHTML = '<b class="success">[비밀번호가 일치합니다.]</b>';

    }
    button();
})

$nameInput.addEventListener('keyup', e => {

    // 이름 검사 정규표현식
    const namePattern = /^[가-힣]{2,6}$/;

    const nameValue = $nameInput.value;

    const $nameChk = document.getElementById('nameChk');

    if(nameValue.trim() === '') {
        $nameInput.style.borderColor = 'red';
        $nameChk.innerHTML = '<b class="warning">[이름은 필수값입니다.]</b>';
    } else if(!namePattern.test(nameValue)) {
        $nameInput.style.borderColor = 'red';
        $nameChk.innerHTML = '<b class="warning">[이름은 2~6자 사이의 한글로 입력해야 합니다..]</b>';
    } else {
        $nameInput.style.borderColor = 'skyblue';
        $nameChk.innerHTML = '<b class="success">[이름을 정상 입력 하였습니다.]</b>';

    }
    button();
})

$emailInput.addEventListener('keyup', async (e) => {
    // 이메일 검사 정규표현식
    const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

    const emailValue = $emailInput.value;

    const $emailChk = document.getElementById('emailChk');

    if(emailValue.trim() === '') {
        $emailInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">[이메일은 필수 입력값입니다.]</b>';
    } else if (!emailPattern.test(emailValue)) {
        $emailInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">[이메일 형식을 정확히 입력하세요.]</b>';
    } else {

        // 이메일 중복검사
        await fetchDuplicateCheck('email' , emailValue);

        if(keywordFlag) {
            $emailInput.style.borderColor = 'red';
            $emailChk.innerHTML = '<b class="warning">[이메일이 중복되었습니다. 다른 이메일을 사용해 주세요.]</b>';
        } else {
            $emailInput.style.borderColor = 'skyblue';
            $emailChk.innerHTML = '<b class="success">[사용 가능한 이메일 입니다.]</b>';

        }
    }
    button();
})