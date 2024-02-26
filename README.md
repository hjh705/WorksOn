    
# WorksOn

## 📍 프로젝트 소개
	효율적인 협업을 위한 소통 및 업무 창구의 기능을 갖춘 그룹웨어 시스템을 목표로 한 시스템입니다.

## 📍 주요 기능
* 일정
    * 신규 일정 등록 및 카테고리 지정 기능
    * 캘린더 페이지에 선택한 카테고리 일정 조회
* 채팅
    * 채팅방 신규 생성 기능
    * Stomp를 활용한 실시간 채팅 기능
    * 비동기(ajax) 채팅기록 저장 및 로그 출력 기능
* 프로젝트
    * 신규 프로젝트 생성 및 참여 사원 선택 기능
    * 프로젝트 생성 시 첨부파일 업로드 및 다운로드
    * 참여 사원 대상으로 업무 및 이슈 생성
    * 업무 및 이슈 생성 시 별도의 담당자 지정 기능
* 전자결재
    * 신규 전자결재 요청 기능
    * 결재 진행상태별 내역 조회
    * 요청자의 결재라인에 따른 결재 순서 업데이트
* 근태관리
    * 일일 출근하기, 퇴근하기 기록 기능
    * 근무내역 기간별 조회
    * 연차 사용내역 연도별 조회 및 연차현황 조회
* 게시판
    * 게시판 카테고리 별 조회
    * Spring Security 권한에 따른 공지글 작성 제한
* 예약
    * 자원별 정보 상세조회
    * 예약 자원별 예약 내역 조회
    * 비동기(ajax)를 활용한 예약 희망시간대의 예약 가능여부 확인
* 회원등록
    * 비동기(ajax) 이메일 중복검사
    * Mail Send를 통한 외부 가입안내 메일 발송
    * 임시 비밀번호 난수 기반 생성

## 📍 Team 소개
* 팀장
    * 이민정
        * 프로젝트 진행 관리
        * [프로젝트] 메뉴 구현
        * [대시보드] 메뉴 구현
* 부팀장
    * 오우진
        * [전자결재] 메뉴 구현
            * 웹 에디터 적용
            * 문서 업로드 다운로드
* 팀원
    * 성민준
        * [근태관리] 메뉴 구현
            * 일일 업무일지 작성
            * 기능 구현
    * 이재준
        * [채팅] 메뉴 구현
            * Stomp를 이용한 WebSocket 구현
        * [예약] 메뉴 구현
    * 천무진
        * [게시판] 메뉴 구현
        * [관리자] 메뉴 구현
            * 사원 CRUD 기능
    * 한준희
        * [일정] 메뉴 구현
            * FullCalendar 라이브러리 적용

## 📍 기술 스택
<!-- 
정적아이콘 및 뱃지생성 사이트
https://simpleicons.org/?q=intellij
https://shields.io/badges/static-badge

simpleicons의 로고명, 컬러를 참조해서 shields.io문법에 따라 뱃지를 만든다.

shields.io 기본문법
https://img.shields.io/badge/<텍스트>-<배경색>?logo=<로고>&logoColor=<로그컬러> 
-->

###### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white)
![Javascript](https://img.shields.io/badge/Javascript-F7DF1E?style=flat&logo=JavaScript&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-white?style=flat&logo=jquery&logoColor=0769AD)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-white?style=flat&logo=tailwindcss&logoColor=0769AD)
![Bootstrap](https://img.shields.io/badge/Bootstrap-white?style=flat&logo=bootstrap&logoColor=7952B3)


###### Backend
![Spring](https://img.shields.io/badge/Spring-f7f7f7?style=flat&logo=spring&logoColor=6DB33F)
![Spring Boot](https://img.shields.io/badge/Springboot-f7f7f7?style=flat&logo=springboot&logoColor=6DB33F)
![Spring Security](https://img.shields.io/badge/Spring_Security-f7f7f7?style=flat&logo=springsecurity&logoColor=6DB33F)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-6DB33F?style=flat&logo=data:image/png;base64,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)
![MyBatis](https://img.shields.io/badge/MyBatis-ffffff?style=flat&logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB3aWR0aD0iMzBweCIgaGVpZ2h0PSIzMHB4IiB2aWV3Qm94PSIwIDAgMzAgMzAiIHZlcnNpb249IjEuMSI+CjxkZWZzPgo8aW1hZ2UgaWQ9ImltYWdlOSIgd2lkdGg9IjMwIiBoZWlnaHQ9IjMwIiB4bGluazpocmVmPSJkYXRhOmltYWdlL3BuZztiYXNlNjQsaVZCT1J3MEtHZ29BQUFBTlNVaEVVZ0FBQUI0QUFBQWVDQVlBQUFBN01LNmlBQUFBQm1KTFIwUUEvd0QvQVArZ3ZhZVRBQUFFdTBsRVFWUklpZTJWVzRoVlZSakhmMnZ0Njlreng3azJuY1FiU3BNS0RVTkd6a09XRXRudG9ZZWVFaUtJQ3JRSG9TaXF4NFNJbm53cDU2R0xTUXFOcU1SZ0lLV0VraVExVFRrcWFVck5TYzh3ell6anpMbk0yWmUxVnc5enpuUThzODFCb2lmL3NQbSsvUzMyOTEvZmRjTnRKT0FENkQ0Q2cvK2xUelBKK0NGMGJZUFQxWGNKWTNmQVBZZmhlQUhPbFNGYmdzKzJ3cFZiSlpiMWhqNUk5Y0NwWG5pOGFuTmhZaENlRzRmK01seFlETTh1Z1QyM1Nnb2drb3dIb1M4REcvNkFQUWEwbXBBV0lCcGdaUWZjbTRWdlRFZy9CUTlYdjFtellrV1BiVmxQV29heFJrb1phSzEvSlFoMi8zRHAwcDhMSnU2RFZBbTJ6Y0RBVnZpMjltdy9mTndHR3pYNGo4RGFWY3VXcld1dzdWN1BkZGNaVWdvcEpZYVVHSWFCRUNJcWxzdDdwNVY2K2V6WnM4Rk5pV3RnQXczQVpOWHdNVHh6UCt6VG9IY3VXdlRlY0Z2YjZ3WFg5VVNWVUVwa25mU0Q0UHVDbEE4TkRBeUVDeVhPMkthWmRSem5uRzJhaDZWdDd4NGJHL3Z0Q1B6Y0FXdE5zQVFRQ3NHRVpYSE5zcGkwYmZLV0JVSVFDY0dNWmVFS3dVUVlmdjMreU1qbWhSSUwyelNuVTY3Ym1ISWNVbzZqYmRQTU5zZnhtWjBqSTAra2xaclhuTFg0YS9WcWRoc0duYmtjbS9ONS9VbFQwOUxlaVlrckFFWkNhdCt5RGFPOTBYRjhQNG9tcFJDYkxNdGFaWmttbG1rSzI3S2F5MEowbnZBODBlWDdOQ3VWU0dvdFc4YnlFeWY0Y21BQXU2Y0gvK0pGTVNQbG91OUtwWDZZUDA0QnNGM0JnVmpLUzNlMnRQaVo5dmJ1MXFZbTNaUk9rL1k4cEpTRVNqRW1KVzlrTXV4dmJxWXM1d2V1ZlovUlhJNlRKMDl5K3Z4NU9ydTdTY1h4ZzU3bkxVNktHT0J1cmZXNlNtY2FsbWsyT0pZbFBNZkJjMTFhMG1reXJhMDR0azJoWE9hTTYzSzBxWWtaMDZSTktkS1ZETVRGSWcxRFEyellzb1dWaGtGbmZ6K0RsbVVNU3BrUlFnd2sxYmdkdUdDYlprdktkZkZjbDFTRjFITWNValZTQXhleVdXS2w1anA0U1JUUk5UUERjdDhuRXdRNFdoTUxRZDZ5T09TNnYzeFZMTzRvRkFvSGtsYm1PUEJpcEZSZnBKUVJLWVZTaXFqdVVVcVJjbDJXZG5Ud2V5NEhXaU8wNXJKbE1lSTRpV04xclZoOHJUQTZlalNweGxVY2pMVitWU21sa2dpak9KN1QwNmtVY1J6UFBicEdyN1VwcFVJcm5UNWVKVWlxY1JXbkVDSXlwZHhrU0NsTXc1amJTTFg2WkQ3UHRVSUJJUVFJa1NpRkVQaGhPUGpUMEZCdjFYbmkzd25BTUl5WHBKUnZSMHBKUDRxd0UxSStWU3pPcHBsL0ZrS3RGRUJja1lIdjk5WDZUeUxPQUoraTlXTTZqa1VNaEVIQVZCd1RSaEZoSEJNcXhkWHBhYVpMSlF3aGtGSWl0SjZyczlDYXVDTFJHcTFVWG52ZXJoc1J0d0E3Z09lQnhyak9FVXBSS3BjcEI4RjFPMWxVWnJnK3d0cm95MEh3K2ZuaDRVSTljUmZ3RHZBbzRGVVB0TmF6alpLVVBpR3VpL0M2QzliSldLbHhsYysvV1o5V0V4Z0IxdFNTVmxFZjlWejY0dmlHdGF5enhhVWcyRDU4OWVwMHZXOEpqQUVQQUQvV0h3THpSaU91WENEV0dsMmpKOWxLNWZLdTRWeHVYNUxmNmpqNXdFZUFCYXduWWN6bXhvUGtEcTZORkNDTW9yN3M2T2dMU2FTMXhGVWNBdzRCSzRFVjFDK1ltcmxNbXRXS0hnUmgrRzV1ZlB5Vkc1RW1FY05zNnZkV0x1QXhPMTZOK2lZUm9uV2c0dmhZRklaUGowMU43ZjgzMGxvZk44TjZZS09VOGo0aHhGMVNpSlNVMHBKU2prdklZaGpIVzRyRkx5N0R6QUw5M2NiL2g3OEJPbXBvSDV3RHBDNEFBQUFBU1VWT1JLNUNZSUk9Ii8+CjwvZGVmcz4KPGcgaWQ9InN1cmZhY2UxIj4KPHVzZSB4bGluazpocmVmPSIjaW1hZ2U5Ii8+CjwvZz4KPC9zdmc+Cg==)
![Oracle](https://img.shields.io/badge/Oracle-4479A1?style=flat&logo=oracle&logoColor=red)
![Redis](https://img.shields.io/badge/Redis-f7f7f7?style=flat&logo=redis&logoColor=DC382D)
![Apache Tomcat](https://img.shields.io/badge/Apache_Tomcat-000000?logo=apachetomcat&logoColor=F8DC75)
![JUnit5](https://img.shields.io/badge/JUnit5-dc524a?logo=junit5&logoColor=25A162)


###### Infra
<!-- 동적 아이콘 생성 사이트 https://techstack-generator.vercel.app/ -->

<div style="display:flex; align-items:flext-start;">
  <img src="https://techstack-generator.vercel.app/github-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://img.shields.io/badge/Github_Actions-f7f7f7?logo=githubactions&logoColor=2088FF"/>
</div>
<div style="display:flex; align-items:flext-start;">
  <img src="https://img.shields.io/badge/Oracle_Cloud-ATP-4479A1?style=squre&logo=oracle&logoColor=red"/>
</div>

<div style="display:flex; align-items:flext-start;">
  <img src="https://techstack-generator.vercel.app/docker-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://techstack-generator.vercel.app/aws-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://img.shields.io/badge/Ubuntu-ffffff?logo=ubuntu&logoColor=E95420"/>
</div>
<div style="display:flex; align-items:flext-start;">
  <img src="https://img.shields.io/badge/Amazon-EC2-f7f7f7?logo=amazonec2&logoColor=FF9900"/>&nbsp;
  <img src="https://img.shields.io/badge/Amazon-S3-f7f7f7?logo=amazons3&logoColor=FF9900"/>&nbsp;
  <img src="https://img.shields.io/badge/Amazon-Route53-f7f7f7?logo=amazonroute53&logoColor=8C4FFF"/>&nbsp;
</div>

###### Tools
![Intellij IDEA](https://img.shields.io/badge/Intellij_IDEA-red?logo=intellijidea)
![VS Code](https://img.shields.io/badge/VS_Code-blue?logo=visualstudiocode)
![Source Tree](https://img.shields.io/badge/Source_Tree-ffffff?style=flat&logo=sourcetree&logoColor=0052CC)
![Discord](https://img.shields.io/badge/discord-5865F2?style=flat&logo=discord&logoColor=white) 
![Notion](https://img.shields.io/badge/notion-000000?style=flat&logo=notion&logoColor=white)