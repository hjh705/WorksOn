const colorChange = (article) => {
    // 랜덤색상 만들기
    const r = Math.floor(Math.random() * 16 + 240);
    const g = Math.floor(Math.random() * 16 + 240);
    const b = Math.floor(Math.random() * 16 + 240);
    article.style.backgroundColor = `rgb(${r},${g},${b})`;
}

window.addEventListener('DOMContentLoaded', () => {
    const articles = document.querySelectorAll("article");
    articles.forEach((article) => {
        colorChange(article);
    })
});

document.querySelectorAll("article[data-project-id]").forEach((article) => {
   article.addEventListener('click', (e) => {
      const project = e.target;
      const { projectId : id } = article.dataset;
      location.href = `${contextPath}project/projectDetail.do?id=${id}`;
   });
});

/**
 * page1에 대한 변수
 */
document.querySelectorAll(".ownerPageNumber").forEach((btn) => {
    btn.addEventListener('click', (e) => {
        const pageNumber2 = document.querySelector("#selectBtnEmpPage span").textContent - 1;
        console.log(pageNumber2);

        const button = e.target;
        const { pageNumber} = button.dataset;
        console.log(pageNumber);

        let size = 8;
        let url = '/WorksOn/project/projectList.do?continue&page1=' + pageNumber + '&size1=' + size + '&page2=' + pageNumber2 + '&size2=' + size;
        // 페이지 이동
         window.location.href = url;
    });
});


/**
 * page2에 대한 변수
 */
document.querySelectorAll(".empPageNumber").forEach((btn) => {
   btn.addEventListener('click', (e) => {
       const pageNumber1 = document.querySelector("#selectBtnOwnerPage span").textContent - 1;
       console.log(pageNumber1);

       const button = e.target;
      const {pageId, pageNumber} = button.dataset;
       console.log(pageNumber);
       console.log(pageId);

        let size = 8;
       // 페이지 URL 생성
       // page2 => emp

       let url = '/WorksOn/project/projectList.do?continue&page1=' + pageNumber1 + '&size1=' + size + '&page2=' + pageNumber + '&size2=' + size;

       console.log(url);
       // 페이지 이동
        window.location.href = url;
   });
});

