
const commentReplyCreate = (parentId) => {
    const frm = document.getElementById(`replyFrm${parentId}`);
    const parentFrm = document.commentCreateFrm;

    const content = frm.content;
    const commentLevel = frm.commentLevel;
    const empId = parentFrm.empId;
    const empName = parentFrm.empName;
    const empProfileUrl = parentFrm.empProfileUrl;
    const empPositionName = parentFrm.empPositionName;
    const typeId = parentFrm.typeId;
    const type = parentFrm.type;

    // frm의 이전 요소 앞에 끼워넣기
    const area = frm.previousElementSibling;

    $.ajax({
        url: `${contextPath}project/projectCommentCreate.do`,
        method: 'post',
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: {
            content: content.value.trim(),
            parentCommentId: parentId,
            commentLevel: commentLevel.value,
            empId: empId.value,
            typeId: typeId.value,
            type: type.value
        },
        success(response) {
            console.log(response);
            let profileUrl = '';
            if (empProfileUrl.value === null) {
                profileUrl = 'https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/profile.png';
            } else {
                profileUrl = empProfileUrl.value;
            }
            const date = new Date();
            const comment = `
            <div class="flex mb-1" id="comment${response.id}">
                <div class="flex-shrink-0 mr-3">
                    <img class="mt-3 rounded-full w-6 h-6 sm:w-8 sm:h-8" 
                    src="${profileUrl}" alt="">
                </div>
                <div class="flex-1 bg-gray-100 rounded-lg px-4 py-2 sm:px-6 sm:py-4 leading-relaxed">
                    <div class="flex justify-between w-full">
                        <div>
                            <strong>${empName.value}${empPositionName.value}</strong> 
                            <span class="text-xs text-gray-400">${date.getFullYear()}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getDate().toString().padStart(2, '0')} ${date.getHours()}:${date.getMinutes()}</span>    
                        </div>
                        <div onclick="javascript:deleteTask(${response.id})">
                            <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 mt-1 mr-0">
                                <line x1="18" y1="6" x2="6" y2="18"></line>
                                <line x1="6" y1="6" x2="18" y2="18"></line>
                            </svg>
                        </div>
                    </div>
                    <p class="text-xs sm:text-sm">
                        ${content.value.trim()}
                    </p>
                </div>
            </div>`;
            area.insertAdjacentHTML('beforebegin', comment);
            frm.reset();
        }
    })
}

const replyEvent = () => {
    document.querySelectorAll(".reply").forEach((btn, i) => {
       btn.addEventListener('click', (e) => {
           const {parentId} = e.target.dataset;

           const frm = `
            <form name="commentReplyCreateFrm" id="replyFrm${parentId}">
                <div class="flex items-center mt-2 rounded-lg">
                    <textarea rows="1" name="content"
                    class="block p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
                    placeholder="답글을 작성해주세요."></textarea>
                        <input type="hidden" name="commentLevel" value="2">
                    <button type="button" onclick="javascript:commentReplyCreate(${parentId})"
                            class="inline-flex justify-center ml-1 p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600">
                        <svg class="w-5 h-5 rotate-90 rtl:-rotate-90" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                            <path d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"/>
                        </svg>
                    </button>
                </div>
            </form>`;
           const area = e.target.parentElement;
           console.log(area.nextElementSibling);
           if(area.nextElementSibling === null){
               area.insertAdjacentHTML('afterend', frm);
           }
           else {
               area.nextElementSibling.outerHTML = '';
           }
       });
    })

  // const createReplyFrm
};

const deleteTask = (id) => {
    const area = document.getElementById(`comment${id}`);
    if(confirm("댓글을 삭제하시겠습니까?")){
        $.ajax({
            url: `${contextPath}project/projectCommentDelete.do`,
            method: 'post',
            headers: {
                [csrfHeaderName] : csrfToken
            },
            data: {
                id: id,
            },
            success(response){
                alert(response);
                area.outerHTML = '';
            }
        })
    }
};
document.commentCreateFrm.addEventListener('submit', (e) => {
    e.preventDefault();
    const frm = e.target;
    const content = frm.content;
    const commentLevel = frm.commentLevel;
    const empId = frm.empId;
    const empName = frm.empName;
    const empProfileUrl = frm.empProfileUrl;
    const empPositionName = frm.empPositionName;
    const typeId = frm.typeId;
    const type = frm.type;
    // comment 영역의 마지막 자식요소
    let area = document.querySelector("#commentWrap").lastElementChild;


    
    $.ajax({
        url: `${contextPath}project/projectCommentCreate.do`,
        method: 'post',
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: {
            content: content.value.trim(),
            commentLevel: commentLevel.value,
            empId: empId.value,
            typeId: typeId.value,
            type: type.value
        },
        success(response){
            console.log(response);
            let profileUrl = '';
            if(empProfileUrl.value === null){
                profileUrl = 'https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/profile.png';
            }
            else {
                profileUrl = empProfileUrl.value;
            }


            const date = new Date();
            const comment = `
            <div class="comment flex" id="comment${response.id}">
                <div class="flex-shrink-0 mr-3">
                    <img class="mt-2 rounded-full w-8 h-8 sm:w-10 sm:h-10"
                    src="${profileUrl}" alt="">
                </div>
                <div class="flex-1 border rounded-lg px-4 py-2 sm:px-6 sm:py-4 leading-relaxed">
                    <div class="flex justify-between w-full">
                        <div>
                            <strong>${empName.value}${empPositionName.value}</strong> 
                            <span class="text-xs text-gray-400">${date.getFullYear()}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getDate().toString().padStart(2, '0')} ${date.getHours()}:${date.getMinutes()}</span>    
                        </div>
                        <div>
                            <button id="editBtn" type="button" data-comment-id="${response.id}"
                                    class="text-gray-700 bg-yellow-50 focus:ring-4 hover:focus:bg-yellow-400 hover:focus:text-white focus:outline-none font-medium rounded-lg text-sm w-[50px] py-1 border border-yellow-600 me-2">편집</button>
                            <button id="deleteBtn" type="button" onclick="javascript:deleteTask(${response.id})"
                                    class="text-gray-700 bg-rose-50 focus:ring-4 hover:focus:bg-rose-400 hover:focus:text-white focus:outline-none focus:ring-rose-200 rounded-lg w-[50px] py-1 border border-rose-600 text-sm font-medium">삭제</button>
                        </div>
                    </div>
                    <textarea style="outline: none;" readonly class="text-sm border-none focus:outline-none resize-none w-full h-fit" cols="3">${content.value}</textarea>
                    <div class="mt-4 flex items-center">
                        <div class="reply cursor-pointer text-sm text-gray-500 font-semibold">
                            답글달기
                        </div>
                    </div>
                </div>
            </div>
            `;
            if(area === null){
                document.querySelector("#commentWrap").innerHTML = comment;
            }
            else {
                area.insertAdjacentHTML('afterend', comment);
            }
            content.value = '';
            replyEvent();
        }
    })




});

document.querySelector("#deleteBtn").addEventListener('click', () => {
    if(confirm("업무를 삭제하시겠습니까?")){
        document.taskDeleteFrm.submit();
    }
})

window.addEventListener('DOMContentLoaded', () => {
    lisColorChange();
    pageEvent();
    empPageEvent();
    editBtnEvent();
    updateBtnEvent();
    replyEvent();
});
const dropDownEvent = (e) => {
    const dropDown = document.querySelector("#dropdown-states");
    dropDown.classList.toggle("hidden");

}
/**
 * 내용 수정 이벤트
 */
const updateBtnEvent = () => {
    // input태그들 편집 불가하게 하기
    const newEmpSearch = document.querySelector("#newEmpSearch");
    const input = document.querySelector("#create-search-input");
    const selectEmpArea = document.querySelector("#createSelectArea");
    const stars = document.querySelectorAll(".star");
    const startAt = document.querySelector("#startAt");
    const endAt = document.querySelector("#endAt");
    const taskContent = document.querySelector("#taskContent");
    const btns = document.querySelectorAll("#taskFrmBtns button");
    const banner = document.querySelector("#sticky-banner");
    const statesBtn = document.querySelector("#states-button");

    document.querySelector("#updateBtn").addEventListener('click', (e) => {
        const btn = e.target;
        banner.classList.add("hidden");
        statesBtn.classList.remove("cursor-pointer");

        btns.forEach((b) => {
            if(!b.classList.contains("hidden")){
                b.classList.add("hidden");
            }
            else {
                b.classList.remove("hidden");
            }
        })

        // 사원 검색시
        newEmpSearch.classList.add("hidden");

        // 날짜 input 태그 변경
        startAt.classList.add("hidden");
        endAt.classList.add("hidden");
        taskContent.classList.add("hidden");

        // ajax 등록을 위한 데이터 확인
        const frm = document.taskDetailFrm;
        const newEmpId = frm.taskEmp;
        const newPriority = frm.priority;
        const newStatus = frm.status;
        const newStartAt = frm.startAt;
        const newEndAt = frm.endAt;
        const newContent = frm.taskContent;
        const id = frm.id;

        switch (newStatus.value){
            case "To do": newStatus.value = "TODO"; break;
            case "In progress": newStatus.value = "INPROGRESS"; break;
            case "Done": newStatus.value = "DONE"; break;
        }

        console.log(newEmpId.value, newPriority.value, newStatus.value, newStartAt.value, newEndAt.value, newContent.value);

        $.ajax({
            url: `${contextPath}project/updateTaskDetail.do`,
            data: {
                id: id.value,
                empId: newEmpId.value,
                priority: newPriority.value,
                status: newStatus.value,
                startAt: newStartAt.value,
                endAt: newEndAt.value,
                content: newContent.value,
            },
            headers: {
                [csrfHeaderName] : csrfToken
            },
            method: 'post',
            success(response) {
                // html 변경
                selectEmpArea.innerHTML = input.value;
                document.querySelector("#startAtHtml").innerHTML = `
                ${newStartAt.value}
                <input type="date" value="${newStartAt.value}"
                       id="startAt" name="startAt"
                       class="hidden absolute text-sm text-gray-700 focus:bg-gray-50 focus:z-10 top-0 left-0 border-none w-full h-full p-2 m-0" />
                `;
                document.querySelector("#endAtHtml").innerHTML = `
                ${newEndAt.value}
                <input type="date" value="${newEndAt.value}"
                       id="endAt" name="endAt"
                       class="hidden absolute text-sm text-gray-700 focus:bg-gray-50 focus:z-10 top-0 left-0 border-none w-full h-full p-2 m-0" />
                `;
                document.querySelector("#contentHtml").innerHTML = `
                ${newContent.value}
                <textarea name="taskContent" id="taskContent" cols="30" rows="10"
                          class="hidden absolute text-sm text-gray-700 focus:bg-gray-50 bg-gray-50 rounded-lg top-0 left-0 border-none w-full h-full p-2 m-0">${newContent.value}</textarea>
                `;
                
                // 이벤트 삭제하기
                const dropDown = document.querySelector("#dropdown-states");
                statesBtn.removeEventListener('click', dropDownEvent);


                // 별 새로 등록..
                let starY = ``;
                let starG = ``;
                for(let i = 1; i <= newPriority.value; i++ ) {
                    starY += `<svg id="star${i}" class="w-4 h-4 text-yellow-300 ms-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                            <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                        </svg>`;
                }
                for(let i = 1; i <= 5 - newPriority.value; i++ ) {
                    starG += `<svg id="star${6 - i}" class="w-4 h-4 ms-1 text-gray-300 dark:text-gray-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                            <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                        </svg>`;
                }
                document.querySelector("#starArea").innerHTML = `
                <div class="flex items-center">
                     ${starY}
                </div>
                <div class="flex items-center">
                     ${starG}
                </div>
                `;
                alert(response);

            }
        })



    });
};









/**
 * 수정 버튼 클릭 시
 * data-dropdown-toggle="dropdown-states"
 */
const editBtnEvent = () => {
    // input태그들 편집가능하도록 하기
    const taskEmp = document.querySelector("#taskEmp");
    const newEmpSearch = document.querySelector("#newEmpSearch");
    const selectEmpArea = document.querySelector("#createSelectArea");
    const stars = document.querySelectorAll(".star");
    const priority = document.querySelector("#priority");
    const startAt = document.querySelector("#startAt");
    const endAt = document.querySelector("#endAt");
    const taskContent = document.querySelector("#taskContent");
    const btns = document.querySelectorAll("#taskFrmBtns button");
    const banner = document.querySelector("#sticky-banner");
    const statesBtn = document.querySelector("#states-button");
    const dropDown = document.querySelector("#dropdown-states");


    document.querySelector("#editBtn").addEventListener('click', (e) => {
       const btn = e.target;
       banner.classList.remove("hidden");
        statesBtn.classList.add("cursor-pointer");

       btns.forEach((b) => {
           if(b.classList.contains("hidden")){
               b.classList.remove("hidden");
           }
           else {
               b.classList.add("hidden");
           }
       })
        
        // 사원 검색시
        newEmpSearch.classList.remove("hidden");
        selectEmpArea.innerHTML = '';
        
        // 날짜 input 태그 변경
       startAt.classList.remove("hidden");
       endAt.classList.remove("hidden");
       taskContent.classList.remove("hidden");
       // status 편집
        statesBtn.addEventListener('click', dropDownEvent);
        // 상태 버튼 클릭 시
        const statusInput = document.querySelector("#status");
        const statusValueBtns = document.querySelectorAll(".statusValueBtn");
        statusValueBtns.forEach((btn, i) => {
            btn.addEventListener('click', (e) => {
                statusInput.value = document.getElementById(`statusValue${i + 1}`).dataset.status;
                console.log(statusInput.value);

                statesBtn.innerHTML = statusValueBtns[i].innerHTML;
                dropDown.classList.add("hidden");
            });
        });

        // star 편집
        stars.forEach((btn, i) => {
            btn.addEventListener('click', (e) => {
                // 전체 요소 회색 처리
                stars.forEach((s) => {
                    s.classList.remove("text-yellow-300");
                    s.classList.add("text-gray-300");
                    s.classList.add("cursor-pointer")
                    s.classList.add("hover:text-yellow-300");
                })
                // 클릭한 요소까지 노랑색 처리
                for(let j = 0; j <= i; j++){
                    stars[j].classList.remove("text-gray-300");
                    stars[j].classList.add("text-yellow-300");
                }
                // 클릭한 요소의 인덱스 + 1로 input태그 value 설정하기
                priority.value = i + 1;
                console.log(priority.value);
            });
        });
    });
};


/**
 * status 디자인 입히는 이벤트
 *
 */
const setStatus = () => {
    const statesBtn = document.querySelector("#states-button");

    const status = statesBtn.innerHTML.trim();
    console.log(status);
    let defaultStatus = '';

    switch (status) {
        case "TODO": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-yellow-300 mr-2"></span>To do</div>`; break;
        case "INPROGRESS": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-blue-300 mr-2"></span>In progress</div>`; break;
        case "DONE": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-rose-300 mr-2"></span>Done</div>`; break;
    }
    statesBtn.innerHTML = defaultStatus;
};
setStatus();

/**
 * page1에 대한 변수
 */
const pageEvent = () => {
    document.querySelectorAll(".ownerPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const pageNumber2 = document.querySelector("#selectBtnEmpPage").value;
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            let url = `/WorksOn/project/taskDetail.do?id=${document.querySelector("#id").value}&projectId=${document.querySelector("#projectId").value}&continue&page1=${pageNumber}&size1=${size}&page2=${pageNumber2}&size2=${size}`;

            console.log(url);

            // Fetch API를 사용하여 비동기로 데이터 가져오기
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
                    let parser = new DOMParser();
                    let newDocument = parser.parseFromString(html, 'text/html');

                    const newSidebar = newDocument.querySelector("#sidebarProject");
                    const originSidebar = document.querySelector("#sidebarProject");

                    // console.log(newDocument);
                    originSidebar.innerHTML = newSidebar.innerHTML;
                    lisColorChange();
                    pageEvent();
                    empPageEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};
/**
 * page2에 대한 변수
 */
const empPageEvent = () => {
    document.querySelectorAll(".empPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const pageNumber1 = document.querySelector("#selectBtnOwnerPage").value;
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            let url = `/WorksOn/project/projectDetail.do?id=${document.querySelector("#projectId").value}&continue&page1=${pageNumber1}&size1=${size}&page2=${pageNumber}&size2=${size}`;

            console.log(url);

            // Fetch API를 사용하여 비동기로 데이터 가져오기
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
                    let parser = new DOMParser();
                    let newDocument = parser.parseFromString(html, 'text/html');

                    const newSidebar = newDocument.querySelector("#sidebarProject");
                    const originSidebar = document.querySelector("#sidebarProject");

                    console.log(newDocument);
                    originSidebar.innerHTML = newSidebar.innerHTML;
                    lisColorChange();
                    pageEvent();
                    empPageEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};
const lisColorChange = () => {
    document.querySelectorAll(".articleLi").forEach((article) => {
        article.addEventListener('click', (e) => {
            const project = e.target;
            const { projectId : id } = article.dataset;
            location.href = `${contextPath}project/projectDetail.do?id=${id}`;
        });

        // console.log(window.location);
        const url = window.location.search;
        if(url.includes(`projectId=${article.dataset.projectId}`)){
            article.classList.add("bg-gray-100");
        }
        else {
            article.classList.remove("bg-gray-100");
        }
    });
}

/**
 * keyup 이벤트
 */
/**
 * 목록 중 하나 클릭 시 입력처리
 */
const createClickEvent = () => {
    document.querySelectorAll(".searchResult").forEach((emp) => {
        emp.addEventListener('click', (e) => {
            const selectArea = document.querySelector("#createSelectArea");
            // 선택된 사원있으면 삭제 처리
            if(selectArea.innerHTML !== ''){
                selectArea.innerHTML = '';
            }

            const input = document.querySelector("#create-search-input");
            const searchList = document.querySelector("#create-search-list");
            searchList.innerHTML = '';

            const {empId, empName, empPosition, deptName} = e.target.dataset;
            document.querySelector("#taskEmp").value = empId;

            input.value = `[${deptName}] ${empName}${empPosition}`;
            // <div id="create${empId}" class="flex">
            //     [${deptName}] ${empName}${empPosition}
            //     <div onclick="createClickDelete(${empId})">
            //         <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 ml-2">
            //             <line x1="18" y1="6" x2="6" y2="18"></line>
            //             <line x1="6" y1="6" x2="18" y2="18"></line>
            //         </svg>
            //     </div>
            // </div>
            ;
        });
    })}

/**
 * 참여사원 목록 요청
 */
document.querySelector("#create-search-input").addEventListener('keyup', (e) => {
    const input = e.target;
    console.log(input.value);
    const searchList = document.querySelector("#create-search-list");
    const projectId = document.querySelector("#projectId");

    searchList.innerHTML = '';

    if(input.value !== ''){
        $.ajax({
            url: `${contextPath}project/searchProjectEmployee.do`,
            headers: {
                [csrfHeaderName] : csrfToken
            },
            data: {
                name: input.value,
                id: projectId.value
            },
            success(response) {
                console.log(response);
                searchList.innerHTML = '';

                response.forEach((e) => {
                    const {id, name, department: {name : deptName}, position: {name: positionName}, profileUrl} = e;

                    searchList.innerHTML += `
                <div class="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-blue-100 text-sm" onclick="javascript:createClickEvent();">
                    <div class="flex w-full items-center p-2 pl-2 border-transparent border-l-2 relative hover:border-blue-100">
                        <div data-emp-id="${id}" data-emp-name="${name}" data-emp-position="${positionName}" data-dept-name="${deptName}"
                        class="searchResult w-full items-center flex">
                            <div style="width: 40px; height: 40px; overflow: hidden" class="rounded-full">
                                <img width="50px" alt="사원 프로필 사진" class="block rounded-full" 
                                src="${profileUrl != null ? profileUrl : 'https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/profile.png'}">
                            </div>
                            <div>
                                <p class="ml-2 text-sm text-blue-700 font-bold">
                                     ${deptName}
                                </p>
                                <p class="ml-2 text-sm">
                                    ${name} ${positionName}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>`;
                });
            }
        })
    }
});
