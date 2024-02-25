const dragEvent = () => {
   let dragTemp;

   document.querySelectorAll('.drag').forEach(item => {
      // dargstart 이벤트
      item.addEventListener('dragstart', function(e) {
         dragTemp = e.target;
         console.log('dragStart', dragTemp);

         // 드래그 시작시 디자인 변경
         dragTemp.classList.remove("bg-white");
         dragTemp.classList.add("bg-gray-100");

         // 데이터 셋팅하기 -> task의 id
         e.dataTransfer.setData("text", e.target.dataset.id);
         console.log(e.dataTransfer.getData("text"));
      });

      item.addEventListener('dragend', function(e) {
         dragTemp = e.target;
         // 드래그 종료시 디자인 변경
         dragTemp.classList.add("bg-white");
         dragTemp.classList.remove("bg-gray-100");
      });


      // click 이벤트
      item.addEventListener('click', (e) => {
         e.stopPropagation();
         console.log(e);

         const task = e.target;
         const {id} = task.dataset;
         const projectId = document.querySelector("#projectId");

         location.href = `${contextPath}project/taskDetail.do?id=${id}&projectId=${projectId.value}`;
      });
   });

   document.querySelectorAll('.drop').forEach(dropZone => {
      dropZone.addEventListener('dragover', function(e) {
         e.preventDefault();
      });

      dropZone.addEventListener('drop', function(e) {
         e.preventDefault();

         const lastChild = this.lastElementChild;
         if(dragTemp !== undefined){
            lastChild.before(dragTemp);
            console.log(dragTemp);
         }

         // ajax update 처리하기
         // console.log(e.dataTransfer.getData("text"));
         // console.log(e.target.dataset.zone);

         $.ajax({
            url: `${contextPath}project/updateTask.do`,
            data: {
               id : e.dataTransfer.getData("text"),
               status: e.target.dataset.zone
            },
            headers: {
               [csrfHeaderName] : csrfToken
            },
            method: 'post',
            success(response) {
               console.log(response);
            }
         })


      });
   });
}

window.addEventListener('DOMContentLoaded', () => {
   dragEvent();
})


// task 생성 이벤트
document.taskCreateFrm.addEventListener('submit', (e) => {
   e.preventDefault();

   const frm = e.target;
   const name = frm.querySelector("#taskName");
   const taskEmpId = frm.querySelector("#taskEmpId");
   const priority = frm.querySelector("#taskPriority");
   const status = frm.querySelector("#taskStatus");
   const startAt = frm.querySelector("#startAt");
   const endAt = frm.querySelector("#endAt");
   const content = frm.querySelector("#content");
   const projectId = document.querySelector("#projectId");
   const taskEmpPosition = document.querySelector("#taskEmpPosition");
   const taskEmpName = document.querySelector("#taskEmpName");


   $.ajax({
      url: `${contextPath}project/createTask.do`,
      data: {
         name: name.value,
         taskEmpId: taskEmpId.value,
         priority: priority.value,
         status: status.value,
         startAt: startAt.value,
         endAt: endAt.value,
         content: content.value,
         projectId: projectId.value
      },
      headers: {
         [csrfHeaderName] : csrfToken
      },
      method: 'post',
      success(response) {
         console.log(response);

         setTimeout(() => {
            let area = null;
            // $('#dp1') 분기
            switch (status.value) {
               case "To do": area = document.querySelector("#dp1"); break;
               case "In progress": area = document.querySelector("#dp2"); break;
               case "Done": area = document.querySelector("#dp3"); break;
            }

            let starY = ``;
            let starG = ``;
            for(let i = 1; i <= priority.value; i++ ) {
               starY += `<svg class="w-4 h-4 text-yellow-300 ms-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                            <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                        </svg>`;
            }
            for(let i = 1; i <= 5 - priority.value; i++ ) {
               starG += `<svg class="w-4 h-4 ms-1 text-gray-300 dark:text-gray-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                            <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                        </svg>`;
            }

            const drag = `
            <div draggable="true" data-id="${response.id}" class="drag w-full hover:bg-gray-50 bg-white p-3 rounded-md shadow-sm text-gray-700">
                    <ul>
                        <li class="flex justify-between" data-id="${response.id}">
                            <div class="font-semibold" data-id="${response.id}">${name.value}</div>
                            <div class="flex" data-id="${response.id}">
                                <div class="flex items-center" data-id="${response.id}">
                                    ${starY}
                                </div>
                                <div class="flex items-center" data-id="${response.id}">
                                    ${starG}
                                </div>
                            </div>
                        </li>
                        <li data-id="${response.id}">${taskEmpName.value}${taskEmpPosition.value}</li>
                    </ul>
                </div>
            `;

            const lastChild = area.lastElementChild;
            lastChild.insertAdjacentHTML('beforebegin', drag);
            
            // 폼 리셋
            document.querySelector("#createSelectArea").innerHTML = '';
            frm.reset();
            // 모달창 닫기
            document.querySelector("#closeModal").click();
            dragEvent();

         }, 500);
      }
   })
});

window.addEventListener('DOMContentLoaded', () => {

   // 업무 생성시 별 이벤트
   const stars = document.querySelectorAll(".starBtn");
   const priority = document.querySelector("#taskPriority");

   // text-gray-300
   stars.forEach((btn, i) => {
      btn.addEventListener('click', (e) => {
         // 전체 요소 회색 처리
         stars.forEach((s) => {
            s.classList.remove("text-yellow-300");
            s.classList.add("text-gray-300");
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


   // 업무 생성시 status값 지정
   const btns = document.querySelectorAll(".modalBtn");
   const taskStatusInput = document.querySelector("#taskStatus");
   const taskStatesBtn = document.querySelector("#task-states-button");

   btns.forEach((btn, i) => {
      const {status} = btn.dataset;
      btn.addEventListener('click', (e) => {
         taskStatusInput.value = status;
         let defaultStatus = '';

         switch (status) {
            case "To do": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-yellow-300 mr-2"></span>${status}</div>`; break;
            case "In progress": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-blue-300 mr-2"></span>${status}</div>`; break;
            case "Done": defaultStatus = `<div class="inline-flex items-center"><span class="w-[16px] h-[16px] rounded-full bg-rose-300 mr-2"></span>${status}</div>`; break;
         }
         taskStatesBtn.innerHTML = defaultStatus;
      });
   });

   const taskStatusValueBtns = document.querySelectorAll(".taskStatusValueBtn");
   taskStatusValueBtns.forEach((btn, i) => {
      btn.addEventListener('click', (e) => {
         taskStatusInput.value = document.getElementById(`taskStatusValue${i + 1}`).dataset.status;
         console.log(taskStatusInput.value);
         taskStatesBtn.innerHTML = taskStatusValueBtns[i].innerHTML;
      });
   });
});


/**
 * keyup 이벤트
 */
/**
 * 입력된 사용자 삭제
 */
const createClickDelete = (empId) => {
   console.log(empId);
   const selected = document.getElementById(`create${empId}`);
   selected.outerHTML = '';
};

/**
 * 목록 중 하나 클릭 시 입력처리
 */
const createClickEvent = () => {
   document.querySelectorAll(".searchResult").forEach((emp) => {
      emp.addEventListener('click', (e) => {
         e.stopPropagation();


         const selectArea = document.querySelector("#createSelectArea");
         // 선택된 사원있으면 삭제 처리
         if(selectArea.innerHTML !== ''){
            selectArea.innerHTML = '';
         }

         const input = document.querySelector("#create-search-input");
         input.value = '';
         const searchList = document.querySelector("#create-search-list");
         searchList.innerHTML = '';

         const {empId, empName, empPosition} = e.target.dataset;
         document.querySelector("#taskEmpId").value = empId;
         document.querySelector("#taskEmpPosition").value = empPosition;
         document.querySelector("#taskEmpName").value = empName;

         selectArea.innerHTML += `
        <div id="create${empId}"
        class="selected w-fit h-fit flex justify-center items-center font-medium px-3 py-2 bg-white rounded-full text-blue-700 bg-blue-100 border border-blue-300">
        <div class="text-sm font-normal leading-none max-w-full flex-initial">${empName}${empPosition}</div>
            <div class="flex flex-auto flex-row-reverse">
                <div onclick="createClickDelete(${empId})">
                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 ml-2">
                        <line x1="18" y1="6" x2="6" y2="18"></line>
                        <line x1="6" y1="6" x2="18" y2="18"></line>
                    </svg>
                </div>
            </div>
        </div>
        `;
      });
   })}

/**
 * 참여사원 목록 요청
 */
document.querySelector("#create-search-input").addEventListener('keyup', (e) => {
   const input = e.target;
   console.log(input.value);
   const searchList = document.querySelector("#create-search-list");
   searchList.innerHTML = '';
   const projectId = document.querySelector("#projectId");

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
                        <div data-emp-id="${id}" data-emp-name="${name}" data-emp-position="${positionName}"
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



























