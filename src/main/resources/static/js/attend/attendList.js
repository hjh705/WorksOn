const modalEvent = () => {
    document.querySelectorAll(".attendOnTime").forEach((e) => {
        e.addEventListener('click', (btn) => {
            const modal = document.querySelector("#crud-modal");

            if(modal.classList.contains("hidden")){
                modal.classList.remove("hidden");
            }
            else {
                modal.classList.add("hidden");
            }
        });
    });
};
document.querySelectorAll(".closeBtn").forEach((e) => {
    e.addEventListener('click', (btn) => {
        const modal = document.querySelector("#crud-modal");
        modal.classList.add("hidden");
    });
});



window.addEventListener('DOMContentLoaded', () =>{
    modalEvent();


    const focusBar = document.querySelector("#focus-bar");
    const attendBtn = document.querySelector("#attendBtn");
    const lis = document.querySelectorAll("#nav-ul li");

    // console.log(projectLi);

    lis.forEach((li, i) => {
        const h = 58;
        for(let i = 0; i < lis.length; i++){
            focusBar.classList.remove(`inset-y-[${h * i}px]`);
            lis[i].classList.remove("bg-blue-500");
        }
    });
    attendBtn.classList.add("bg-blue-500");
    focusBar.classList.add(`inset-y-[${attendBtn.offsetTop}px]`);
});


window.addEventListener('DOMContentLoaded', () => {
    const btnStartWork = document.getElementById("btn-startwork");
    const btnEndWork = document.getElementById("btn-endwork");
    const workStateElement = document.getElementById("work-state");

    btnStartWork.addEventListener('click', function () {
        if (isAttendRegistered) {
            alert('이미 출근이 등록되어 있습니다.');
            return;
        }
        const {empId} =  btnStartWork.dataset;
        console.log(empId);
        // AJAX를 사용하여 출근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/startWork.do`,
            data: {

            },
            success: function (response) {
                alert(response);
                // 출근 등록이 성공하면 상태를 변경
                isAttendRegistered = true;

                console.log('출근 등록 성공');
                workStateElement.textContent = '업무중';
                const currentTime = new Date();
                const formattedTime = currentTime.toLocaleTimeString();
                document.getElementById('startwork-time').textContent = formattedTime;

            },
            error: function (error) {
                // 오류가 발생하면 오류 메시지 출력 또는 다른 처리 수행
                console.error('에러 발생:', error.responseText);
            }

        });
    });

    let isEndWorkRegistered = false;
    btnEndWork.addEventListener('click', function () {
        if (isEndWorkRegistered) {
            alert('이미 퇴근이 등록되어 있습니다.');
            return;
        }
        const {empId} =  btnStartWork.dataset;
        console.log(empId);
        // AJAX를 사용하여 퇴근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/endWork.do`,
            data: {

            },
            success: function (response) {
                alert(response);
                // 퇴근 등록이 성공하면 상태를 변경
                isEndWorkRegistered = true;

                console.log('퇴근 등록 성공');
                workStateElement.textContent = '퇴근';
                const currentTime = new Date();
                const formattedTime = currentTime.toLocaleTimeString();
                document.getElementById('endwork-time').textContent = formattedTime;

            },
            error: function (xhr, status, error) {
                const errorMessage = JSON.parse(xhr.responseText).message;
                if (errorMessage === "퇴근시간이 아닙니다.") {
                    alert(errorMessage); // 예외 메시지를 팝업으로 표시
                } else {
                    // 그 외의 오류 처리
                    console.error('서버 오류:', errorMessage);
                }
            }
        });
    });
});

// 근태관리 실시간 시간 보여주기
var Target = document.getElementById("clock");
var yearNow = document.getElementById("year");
function clock() {
    var time = new Date();

    var year = time.getFullYear();
    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    yearNow.innerHTML = `${year}년 ${month + 1}월 ${date}일 (${week[day]})`;
    Target.innerText =
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}
clock();
setInterval(clock, 1000); // 1초마다 실행
// 근태관리 실시간 시간 보여주기 end


document.getElementById("btnRequest").addEventListener('click', function() {
    // 폼 데이터 수집
    const formData = new FormData(document.forms["contentFrm"]);
    let isAttendRequest = true;
    // AJAX 요청
    $.ajax({
        type: "POST",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}attend/request.do`,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data){
            alert("정정 요청이 완료되었습니다.");
        }
    });
});

// DOM 요소 가져오기
const startDateInput = document.getElementById('start-input');
const endDateInput = document.getElementById('end-input');
const submitBtn = document.getElementById('submitBtn');

// 이벤트 리스너 추가
startDateInput.addEventListener('input', handleDateChange);
endDateInput.addEventListener('input', handleDateChange);

// 날짜가 변경되었을 때 실행되는 함수
function handleDateChange() {
    // 선택된 시작 날짜와 종료 날짜 출력
    console.log(`선택된 시작 날짜: ${startDateInput.value}, 종료 날짜: ${endDateInput.value}`);
}


const pagebarEvent = () => {
    document.querySelectorAll(".attendPageNumber").forEach((e) => {
        e.addEventListener('click', (btn) => {
            const button = btn.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            const startDate = startDateInput.datepicker.dates[0];
            const endDate = endDateInput.datepicker.dates[0];
            const tbody = document.querySelector("#searchDate tbody");

            $.ajax({
                type: "GET",
                headers: {
                    [csrfHeaderName]: csrfToken
                },
                url: `${contextPath}attend/attendListSearchDate.do`,
                data: {
                    startDate : startDate, // millis
                    endDate : endDate,
                    page: pageNumber,
                    size: size
                },
                success(response){
                    console.log(response);
                    tbody.innerHTML = '';
                    const pagebarContainer = document.querySelector("#page-bar-container");
                    const {size, number, totalpages} = response;

                    let previousBtn;
                    if(number <= 0) {
                        previousBtn = `
                <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-gray-50 text-gray-300 focus:shadow-outline-blue cursor-not-allowed"
                        readonly>
                    <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20">
                        <path
                                d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                                clip-rule="evenodd"
                                fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
                    }
                    else if(number > 0){
                        previousBtn = `
                <button class="attendPageNumber px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-blue-100 hover:bg-blue-200 focus:shadow-outline-blue"
                        aria-label="Previous"
                        data-page-number="${number - 1}">
                    <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20" data-page-number="${number - 1}">
                        <path data-page-number="${number - 1}"
                              d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                              clip-rule="evenodd"
                              fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
                    }
                    let pageNumberBtn = '';
                    for(let i = 0; i <= totalpages - 1; i++){
                        if(i === number){
                            pageNumberBtn += `
                    <button id="selectBtn"
                            class="px-3 py-1 text-white transition-colors duration-150 bg-blue-600 border border-r-0 border-blue-600 rounded-md focus:outline-none focus:shadow-outline-blue">
                        <!-- 현재 선택된 페이지는 특별한 스타일을 적용 -->
                        <span>${i + 1}</span>
                    </button>`;
                        }
                        else {
                            pageNumberBtn += `
                    <button class="attendPageNumber px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-blue hover:bg-gray-100"
                            data-page-id="owner" data-page-number="${i}">
                        <!-- 페이지가 선택되지 않은 경우의 스타일 및 이벤트 핸들러 -->
                        <span data-page-number="${i}">${i + 1}</span>
                    </button>
                    `;
                        }
                    }

                    let nextBtn;
                    if(number >= totalpages - 1) {
                        nextBtn = `
                            <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-gray-50 text-gray-300 focus:shadow-outline-blue cursor-not-allowed" readonly>
                                    <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20">
                                    <path
                                            d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                            clip-rule="evenodd"
                                            fill-rule="evenodd"
                                    ></path>
                                </svg>
                            </button>`;
                                }
                                else if(number < totalpages - 1){
                                    nextBtn = `
                            <button class="attendPageNumber px-3 py-1 rounded-md rounded-r-lg focus:outline-none bg-blue-100 hover:bg-blue-200 focus:shadow-outline-blue"
                                    aria-label="Next"
                                    data-page-number="${number + 1}">
                                <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20" data-page-number="${number + 1}">
                                    <path data-page-number="${number + 1}"
                                          d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                          clip-rule="evenodd"
                                          fill-rule="evenodd"
                                    ></path>
                                </svg>
                            </button>`;
                                }

                    pagebarContainer.innerHTML = `
                <div id="page-bar-container" class="px-4 py-3 text-xs font-semibold text-gray-500 uppercase">
                    <span class="flex mt-2 justify-center">
                        <nav aria-label="Table navigation">
                            <ul class="inline-flex items-center">
                                <!-- 이전 버튼 -->
                                <li>
                                    ${previousBtn}
                                </li>
                                <!-- 페이지 번호 반복문 -->
                                    ${pageNumberBtn}
                                <!-- 다음 버튼 -->
                                <li>
                                    ${nextBtn}
                                </li>
                            </ul>
                        </nav>
                    </span>
            </div>
            `;
                    console.log(pagebarContainer.innerHTML);
                    pagebarEvent();

                    response.attendPage.content.forEach((att) => {
                        const {content, employeeId, endAt, id, startAt, state} = att;

                        tbody.innerHTML += `
                    <tr>
                        <td>${new Date(startAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}</td>
                        <td>${new Date(startAt).toLocaleTimeString()}</td>
                        <td>${endAt ? new Date(endAt).toLocaleTimeString() : ''}</td>
                        <td>
                            <button class="attendOnTime times block text-black focus:ring-4 rounded-xl focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                                    type="button">
                                    ${new Date(startAt).toLocaleString()}
                            </button>
                        </td>
                        <td>${att.content}</td>
                    </tr>
                `;


                    });
                    modalEvent();
                    stateEvent();
                },
                error(e){
                    console.log(e);
                }
            });




        });
    });
}



// Submit 버튼 클릭 시 시작 날짜부터 종료 날짜까지 출력
document.querySelector("#submitBtn").addEventListener('click', (e) => {

    console.log(`선택한 날짜 범위: ${startDateInput.value} 부터 ${endDateInput.value}`);
    // const startDateValue = startDateInput.value;
    // const endDateValue = endDateInput.value;

    const startDate = startDateInput.datepicker.dates[0];
    const endDate = endDateInput.datepicker.dates[0];
    const tbody = document.querySelector("#searchDate tbody");
    console.log(startDate," ~ " ,endDate)

    $.ajax({
        type: "GET",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}attend/attendListSearchDate.do`,
        data: {
            startDate : startDate, // millis
            endDate : endDate
        },
        success(response){

            console.log(response);
            tbody.innerHTML = '';
            const pagebarContainer = document.querySelector("#page-bar-container");
            const {size, number, totalpages} = response;


            let previousBtn;
            if(number <= 0) {
                previousBtn = `
                <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-gray-50 text-gray-300 focus:shadow-outline-blue cursor-not-allowed"
                        readonly>
                    <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20">
                        <path
                                d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                                clip-rule="evenodd"
                                fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
            }
            else if(number > 0){
                previousBtn = `
                <button class="attendPageNumber px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-blue-100 hover:bg-blue-200 focus:shadow-outline-blue"
                        aria-label="Previous"
                        data-page-number="${number - 1}">
                    <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20" data-page-number="${number - 1}">
                        <path data-page-number="${number - 1}"
                              d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                              clip-rule="evenodd"
                              fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
            }
            let pageNumberBtn = '';
            for(let i = 0; i <= totalpages - 1; i++){
                if(i === number){
                    pageNumberBtn += `
                    <button id="selectBtn"
                            class="px-3 py-1 text-white transition-colors duration-150 bg-blue-600 border border-r-0 border-blue-600 rounded-md focus:outline-none focus:shadow-outline-blue">
                        <!-- 현재 선택된 페이지는 특별한 스타일을 적용 -->
                        <span>${i + 1}</span>
                    </button>`;
                }
                else {
                    pageNumberBtn += `
                    <button class="attendPageNumber px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-blue hover:bg-gray-100"
                            data-page-id="owner" data-page-number="${i}">
                        <!-- 페이지가 선택되지 않은 경우의 스타일 및 이벤트 핸들러 -->
                        <span data-page-number="${i}">${i + 1}</span>
                    </button>
                    `;
                }
            }

            let nextBtn;
            if(number >= totalpages - 1) {
                nextBtn = `
                <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none bg-gray-50 text-gray-300 focus:shadow-outline-blue cursor-not-allowed" readonly>
                        <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20">
                        <path
                                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                clip-rule="evenodd"
                                fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
            }
            else if(number < totalpages - 1){
                nextBtn = `
                <button class="attendPageNumber px-3 py-1 rounded-md rounded-r-lg focus:outline-none bg-blue-100 hover:bg-blue-200 focus:shadow-outline-blue"
                        aria-label="Next"
                        data-page-number="${number + 1}">
                    <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20" data-page-number="${number + 1}">
                        <path data-page-number="${number + 1}"
                              d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                              clip-rule="evenodd"
                              fill-rule="evenodd"
                        ></path>
                    </svg>
                </button>`;
            }

            pagebarContainer.innerHTML = `
                <div id="page-bar-container" class="px-4 py-3 text-xs font-semibold text-gray-500 uppercase">
                    <span class="flex mt-2 justify-center">
                        <nav aria-label="Table navigation">
                            <ul class="inline-flex items-center">
                                <!-- 이전 버튼 -->
                                <li>
                                    ${previousBtn}
                                </li>
                                <!-- 페이지 번호 반복문 -->
                                    ${pageNumberBtn}
                                <!-- 다음 버튼 -->
                                <li>
                                    ${nextBtn}
                                </li>
                            </ul>
                        </nav>
                    </span>
            </div>
            `;
            console.log(pagebarContainer.innerHTML);
            pagebarEvent();

            response.attendPage.content.forEach((att) => {
                const {content, employeeId, endAt, id, startAt, state} = att;

                tbody.innerHTML += `
                    <tr>
                        <td>${new Date(startAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}</td>
                        <td>${new Date(startAt).toLocaleTimeString()}</td>
                        <td>${endAt ? new Date(endAt).toLocaleTimeString() : ''}</td>
                        <td>
                            <button class="attendOnTime times block text-black focus:ring-4 rounded-xl focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                                    type="button">
                                    ${new Date(startAt).toLocaleString()}
                            </button>
                        </td>
                        <td>${att.content}</td>
                    </tr>
                `;

            });
            modalEvent();
            stateEvent();
            },
        error(e){
            console.log(e);
        }
    })
});

function changePage1(pageNumber) {
    let size = 10;
    // 페이지 URL 생성
    let url = '/WorksOn/attend/attendList.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}