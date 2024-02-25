function execCmd(command) {
    document.execCommand(command, false, null);
}

function setApprovalInfo(sign, lineId) {
    document.recognize.sign.value = sign;
    document.recognize.lineId.value = lineId;
}


window.addEventListener("DOMContentLoaded", () => {
    approvalEvent();
    // 폼 버튼 눌렀을때 동작 이벤트
    const inputs = document.querySelectorAll(".signs");
    const frm = document.getElementById("recognizeFrm");
    // const frm = document.querySelector("form[name=recognizeFrm]");

    // 반려 관련
    const rejectFrm = document.getElementById("rejectFrm");

    // 승인 관련
    inputs.forEach((input, i) => {
        input.addEventListener('keyup', (e) => {
            const sign = e.target.value;
            const  {lineId, isLast} = e.target.dataset;
            console.log(sign);
            console.log(frm);


            frm.sign.value = sign;
            frm.lineId.value = lineId;
            frm.isLast.value = isLast;

        });
    });

    // 반려 관련
    rejectionFrmBtn.addEventListener('click', (e) => {
        inputs.forEach((input) => {
           if(!input.readOnly){
               const {lineId} = input.dataset;

               rejectFrm.sign.value = '반려';
               rejectFrm.lineId.value = lineId;
               rejectFrm.submit;
           }
        });
    });


})

// 편집 가능여부 컨트롤
const approvalEvent = () => {
    const loginId = document.querySelector("#loginId").value;
    const approvals = document.querySelectorAll(".approver");
    console.log(approvals);
    approvals.forEach((approval, iterStat) => {
        if (iterStat !== 0) {
            const sign = approvals[iterStat - 1].querySelector("input[name=sign]");

            // console.dir(sign);
            // console.log(sign.value);
            const {empId} = approval.dataset;
            // console.log(sign.value === "");
            // console.log(sign.value !== "");
            // console.log(sign.value !== undefined);
            // console.log(sign.value === undefined);
            // console.log(loginId);
            // console.log(empId);
            const thisSign = approval.querySelector("input[name=sign]");
            // console.log("thisSign ", thisSign);

            if((sign.value !== "") && (loginId === empId)) {
                // console.log("여기 들어오나요?");
                // console.dir(thisSign);

                thisSign.readOnly = false;
                console.log(thisSign.readOnly);
            }
            else {
                thisSign.readOnly = true;
            }
        }
    });
}


//
// document.getElementById("recognizeBtn").addEventListener('click', (e) => {
//     console.log(e.target);
//     thisFrm.submit;
//     console.dir(thisFrm);
//
// })

