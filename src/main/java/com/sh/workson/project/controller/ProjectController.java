package com.sh.workson.project.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.dto.ProjectAttachmentCreateDto;
import com.sh.workson.attachment.dto.ProjectAttachmentDetailDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.dto.EmployeeSearchDto;
import com.sh.workson.project.dto.*;
import com.sh.workson.project.entity.*;
import com.sh.workson.project.service.IssueService;
import com.sh.workson.project.service.ProjectCommentService;
import com.sh.workson.project.service.ProjectService;
import com.sh.workson.project.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/project")
@Validated
public class ProjectController {


    @Autowired
    private ProjectService projectService;
    @Autowired
    private S3FileService s3FileService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectCommentService projectCommentService;
    @Autowired
    private IssueService issueService;

    @GetMapping("/totalProjectList.do")
    public void totalProjectList(
            @PageableDefault(size = 8, page = 0)Pageable pageable,
            Model model
    ){
        Page<ProjectListDto> projects = projectService.findAll(pageable);
        log.debug("project = {}", projects.getContent());
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalCount", projects.getTotalElements());
    }

    @GetMapping("/projectList.do")
    public void projectList(
            @RequestParam(name = "page1",defaultValue = "0") int page1,
            @RequestParam(name = "size1", defaultValue = "8") int size1,
            @RequestParam(name = "page2", defaultValue = "0") int page2,
            @RequestParam(name = "size2", defaultValue = "8") int size2,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), PageRequest.of(page2, size2));
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), PageRequest.of(page1, size1));
        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectEmpSize", projects.getSize());
        model.addAttribute("projectEmpNumber", projects.getNumber());
        model.addAttribute("projectEmpTotalpages", projects.getTotalPages());

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("projectOwnerSize", projects2.getSize());
        model.addAttribute("projectOwnerNumber", projects2.getNumber());
        model.addAttribute("projectOwnerTotalpages", projects2.getTotalPages());
    }

    @GetMapping("/createProject.do")
    public void createProject(){};

    @PostMapping("/createProject.do")
    public String createProject(
            ProjectCreateDto projectCreateDto,
            BindingResult bindingResult,
            @RequestParam(name = "files") List<MultipartFile> files,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        log.debug("files = {}", files);
        log.debug("projectCreateDto = {}", projectCreateDto);

        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                AttachmentCreateDto attachmentCreateDto = s3FileService.upload(file, AttachType.PROJECT);
                attachmentCreateDto.setEmployee(employeeDetails.getEmployee());
                log.debug("attachmentCreateDto = {}", attachmentCreateDto);
                projectCreateDto.addAttachmentCreateDto(attachmentCreateDto);
            }
        }

        projectCreateDto.setEmployee(employeeDetails.getEmployee());
        projectService.createProject(projectCreateDto);

        return "redirect:/project/projectList.do";
    }

    @GetMapping("/projectDetail.do")
    public void projectDetail(
            @RequestParam("id") Long id,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam(name = "page1",defaultValue = "0") int page1,
            @RequestParam(name = "size1", defaultValue = "5") int size1,
            @RequestParam(name = "page2", defaultValue = "0") int page2,
            @RequestParam(name = "size2", defaultValue = "5") int size2,
            @RequestParam(name = "page2", defaultValue = "0") int page3,
            @RequestParam(name = "size2", defaultValue = "3") int size3
    ){
        ProjectDetailDto projectDetailDto = projectService.findById(id);
        model.addAttribute("project", projectDetailDto);

        List<TaskListDto> todos = new ArrayList<>();
        List<TaskListDto> progresses = new ArrayList<>();
        List<TaskListDto> dones = new ArrayList<>();
        // task 분류하기
        if(!projectDetailDto.getTasks().isEmpty()){
            for(TaskListDto task: projectDetailDto.getTasks()){
                switch (task.getStatus()){
                    case "TODO" : todos.add(task); break;
                    case "INPROGRESS" : progresses.add(task); break;
                    case "DONE" : dones.add(task); break;
                    default: throw new RuntimeException("[task 조회 오류 : 존재하지 않는 status]");
                }
            }
        }

        Page<IssueDetailDto> issueDetailDtos = issueService.findTop3Issue(id, PageRequest.of(page3, size3));
        log.debug("issue = {}", issueDetailDtos.toList());
        model.addAttribute("issueSize", issueDetailDtos.getTotalElements());
        model.addAttribute("issues", issueDetailDtos);
        model.addAttribute("issueCount", issueDetailDtos.getTotalElements());

        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), PageRequest.of(page2, size2));
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), PageRequest.of(page1, size1));

        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectEmpSize", projects.getSize());
        model.addAttribute("projectEmpNumber", projects.getNumber());
        model.addAttribute("projectEmpTotalpages", projects.getTotalPages());

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("projectOwnerSize", projects2.getSize());
        model.addAttribute("projectOwnerNumber", projects2.getNumber());
        model.addAttribute("projectOwnerTotalpages", projects2.getTotalPages());

        model.addAttribute("taskSize", projectDetailDto.getTasks().size());
        model.addAttribute("taskTodos", todos);
        model.addAttribute("taskProgresses", progresses);
        model.addAttribute("taskDone", dones);

        log.debug("project = {}", projectDetailDto);
    }


    @GetMapping("/projectEmployeeList.do")
    public ResponseEntity<?> projectEmployeeList(
            @RequestParam("projectId") Long id
    ){
        List<ProjectEmployee> employees = projectService.findAllProjectEmployeesByProjectID(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/projectAttachmentList.do")
    public ResponseEntity<?> projectAttachmentList(
            @RequestParam("projectId") Long id
    ){
        List<Attachment> attachments = projectService.findAllAttachmentByProjectId(id);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }


    @GetMapping("/projectAttachDownload.do")
    public ResponseEntity<?> projectAttachDownload(
            @RequestParam("id") Long id,
            @RequestParam("url") String url
    ) throws UnsupportedEncodingException {

        ProjectAttachmentDetailDto attachmentDetailDto = attachmentService.findByProjectId(id);
        return s3FileService.download(attachmentDetailDto);
    }


    @PostMapping("/uploadAttachment.do")
    public ResponseEntity<?> uploadAttachment(
        @RequestParam("projectId") Long projectId,
        @RequestParam(name = "files") List<MultipartFile> files,
        @AuthenticationPrincipal EmployeeDetails employeeDetails,
        RedirectAttributes redirectAttributes
    ) throws IOException {

        List<ProjectAttachmentDetailDto> attachments = new ArrayList<>();
        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                ProjectAttachmentCreateDto attachmentCreateDto = s3FileService.uploadProjectAttach(file, AttachType.PROJECT);
                attachmentCreateDto.setEmployee(employeeDetails.getEmployee());
                attachmentCreateDto.setBoardId(projectId);

                // DB에 저장하기
                attachments.add(attachmentService.createProjectAttachment(attachmentCreateDto));
                log.debug("attachments = {}", attachments);
            }
        }
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @GetMapping("/dateTest.do")
    public void dateTest(){};

    @GetMapping("/searchDateTest.do")
    public void dateTest(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ){
        log.debug("startDate = {}", startDate);
        log.debug("endDate = {}", endDate);

        LocalDateTime startAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(startDate)), ZoneId.systemDefault());
        LocalDateTime endAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(endDate)), ZoneId.systemDefault());

        log.debug("T startDate = {}", startAt); // T startDate = 2024-02-07T00:00
        log.debug("T endDate = {}", endAt); // T endDate = 2024-02-10T00:00
    };

    @PostMapping("/createTask.do")
    public ResponseEntity<?> createTask(
        TaskCreateDto taskCreateDto,
        @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        log.debug("taskCreateDto = {}", taskCreateDto);
        taskCreateDto.setTaskOwnerId(employeeDetails.getEmployee().getId());
        TaskListDto task = taskService.createTask(taskCreateDto);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/updateTask.do")
    public ResponseEntity<?> updateTask(
            TaskUpdateDto taskUpdateDto
    ){
        log.debug("taskUpdateDto = {}", taskUpdateDto);
        if(taskUpdateDto.getStatus() != null)
            taskService.updateTask(taskUpdateDto);

        return null;
    }

    @GetMapping("/taskDetail.do")
    public void taskDetail(
            @RequestParam("id") Long id,
            @RequestParam("projectId") Long projectId,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam(name = "page1",defaultValue = "0") int page1,
            @RequestParam(name = "size1", defaultValue = "5") int size1,
            @RequestParam(name = "page2", defaultValue = "0") int page2,
            @RequestParam(name = "size2", defaultValue = "5") int size2
    ){

        // task 찾기
        TaskDetailDto taskDto = taskService.findById(id);
        log.debug("taskDto = {}", taskDto);
        model.addAttribute("task", taskDto);

        List<ProjectCommentDetailDto> commentDetailDtos = projectCommentService.findByTypeId(taskDto.getId(), ProjectCommentType.TASK);
        model.addAttribute("comments", commentDetailDtos);

        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), PageRequest.of(page2, size2));
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), PageRequest.of(page1, size1));

        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectEmpSize", projects.getSize());
        model.addAttribute("projectEmpNumber", projects.getNumber());
        model.addAttribute("projectEmpTotalpages", projects.getTotalPages());

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("projectOwnerSize", projects2.getSize());
        model.addAttribute("projectOwnerNumber", projects2.getNumber());
        model.addAttribute("projectOwnerTotalpages", projects2.getTotalPages());
    }


    @PostMapping("/updateTaskDetail.do")
    public ResponseEntity<?> updateTaskDetail(
            TaskDetailUpdateDto taskDetailUpdateDto
    ){
        log.debug("taskUpdateDto = {}", taskDetailUpdateDto);
        taskService.updateTaskDetail(taskDetailUpdateDto);
        return new ResponseEntity<>("업무 내용이 수정되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/deleteTask.do")
    public String deleteTask(
            @RequestParam("id") Long id,
            @RequestParam("projectId") Long projectId
    ){
        log.debug("taskId = {}", id);
        log.debug("projectId = {}", projectId);

        taskService.deleteTask(id);
        return "redirect:/project/projectDetail.do?id=" + projectId;
    }


    @PostMapping("/projectCommentCreate.do")
    public ResponseEntity<?> projectCommentCreate(
            ProjectCommentCreateDto commentCreateDto
    ){
        log.debug("commentCreateDto = {}", commentCreateDto);
        ProjectCommentDetailDto projectCommentDetailDto = projectCommentService.createProjectComment(commentCreateDto);
        log.debug("commentDetailDto = {}", projectCommentDetailDto);
        return new ResponseEntity<>(projectCommentDetailDto, HttpStatus.OK);
    }

    @PostMapping("/projectCommentDelete.do")
    public ResponseEntity<?> projectCommentDelete(
            ProjectCommentDeleteDto commentDeleteDto
    ){
        log.debug("commentCreateDto = {}", commentDeleteDto);
        projectCommentService.deleteProjectComment(commentDeleteDto);
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }



    @PostMapping("/updateProject.do")
    public ResponseEntity<?> updateProject(
            ProjectUpdateDto projectUpdateDto
    ){
        log.debug("taskUpdateDto = {}", projectUpdateDto);
        projectService.updateProject(projectUpdateDto);
        return new ResponseEntity<>("프로젝트 정보가 수정되었습니다.", HttpStatus.OK);
    }


    @GetMapping("/doneProjectList.do")
    public void doneProjectList(
            @PageableDefault(size = 15, page = 0) Pageable pageable,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model
    ){
        Page<ProjectListDto> projectListDtos = projectService.findAllDoneProject(employeeDetails.getEmployee().getId(), pageable);
        model.addAttribute("projects", projectListDtos);
        model.addAttribute("totalCount", projectListDtos.getTotalElements());
        model.addAttribute("size", projectListDtos.getSize());
        model.addAttribute("number", projectListDtos.getNumber());
        model.addAttribute("totalpages", projectListDtos.getTotalPages());
    };


    @GetMapping("/totalTaskList.do")
    public void totalTaskList(
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model
    ){
        Page<TaskDetailDto> taskDetailDtos = taskService.findAllMyTask(employeeDetails.getEmployee().getId(), pageable);

        log.debug("task = {}", taskDetailDtos);

        model.addAttribute("tasks", taskDetailDtos);
        model.addAttribute("totalCount", taskDetailDtos.getTotalElements());
        model.addAttribute("size", taskDetailDtos.getSize());
        model.addAttribute("number", taskDetailDtos.getNumber());
        model.addAttribute("totalpages", taskDetailDtos.getTotalPages());
    }


    @GetMapping("/totalIssueList.do")
    public void totalIssueList(
        @PageableDefault(size = 10, page = 0) Pageable pageable,
        @AuthenticationPrincipal EmployeeDetails employeeDetails,
        Model model
    ){
        Page<IssueDetailDto> issueDetailDtos = issueService.findAllMyIssue(employeeDetails.getEmployee().getId(), pageable);

        log.debug("issue = {}", issueDetailDtos);

        model.addAttribute("issues", issueDetailDtos);
        model.addAttribute("totalCount", issueDetailDtos.getTotalElements());
        model.addAttribute("size", issueDetailDtos.getSize());
        model.addAttribute("number", issueDetailDtos.getNumber());
        model.addAttribute("totalpages", issueDetailDtos.getTotalPages());
    }


    @GetMapping("/searchTask.do")
    public ResponseEntity<?> totalTaskList(
            @RequestParam("name") String name,
            @RequestParam("projectId") Long projectId,
            Model model
    ){
        List<TaskSearchDto> tasks = taskService.findTaskByProjectId(name, projectId);
        log.debug("task = {}", tasks);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @PostMapping("/createIssue.do")
    public ResponseEntity<?> createIssue(
            IssueCreateDto issueCreateDto,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        issueCreateDto.setOwnerId(employeeDetails.getEmployee().getId());
        Issue issue = issueService.createIssue(issueCreateDto);

        return new ResponseEntity<>(issue.getId(), HttpStatus.OK);
    }


    @GetMapping("/projectTotalTaskList.do")
    public void projectTotalTaskList(
            @RequestParam("id") Long id,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam(name = "page1",defaultValue = "0") int page1,
            @RequestParam(name = "size1", defaultValue = "5") int size1,
            @RequestParam(name = "page2", defaultValue = "0") int page2,
            @RequestParam(name = "size2", defaultValue = "5") int size2,
            @RequestParam(name = "page3", defaultValue = "0") int page3,
            @RequestParam(name = "size3", defaultValue = "10") int size3
    ){
        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), PageRequest.of(page2, size2));
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), PageRequest.of(page1, size1));

        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectEmpSize", projects.getSize());
        model.addAttribute("projectEmpNumber", projects.getNumber());
        model.addAttribute("projectEmpTotalpages", projects.getTotalPages());

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("projectOwnerSize", projects2.getSize());
        model.addAttribute("projectOwnerNumber", projects2.getNumber());
        model.addAttribute("projectOwnerTotalpages", projects2.getTotalPages());


        Page<TaskDetailDto> taskDetailDtos = taskService.findAllProjectTask(id, PageRequest.of(page3, size3));
        log.debug("task = {}", taskDetailDtos);
        model.addAttribute("thisProject", taskDetailDtos.getContent().get(0).getProject());
        model.addAttribute("tasks", taskDetailDtos);
        model.addAttribute("totalCount", taskDetailDtos.getTotalElements());
        model.addAttribute("size", taskDetailDtos.getSize());
        model.addAttribute("number", taskDetailDtos.getNumber());
        model.addAttribute("totalpages", taskDetailDtos.getTotalPages());
    }


    @GetMapping("projectTotalIssueList.do")
    public void projectTotalIssueList(
            @RequestParam("id") Long id,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam(name = "page1",defaultValue = "0") int page1,
            @RequestParam(name = "size1", defaultValue = "5") int size1,
            @RequestParam(name = "page2", defaultValue = "0") int page2,
            @RequestParam(name = "size2", defaultValue = "5") int size2,
            @RequestParam(name = "page3", defaultValue = "0") int page3,
            @RequestParam(name = "size3", defaultValue = "10") int size3
    ){
        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), PageRequest.of(page2, size2));
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), PageRequest.of(page1, size1));

        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectEmpSize", projects.getSize());
        model.addAttribute("projectEmpNumber", projects.getNumber());
        model.addAttribute("projectEmpTotalpages", projects.getTotalPages());

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("projectOwnerSize", projects2.getSize());
        model.addAttribute("projectOwnerNumber", projects2.getNumber());
        model.addAttribute("projectOwnerTotalpages", projects2.getTotalPages());


        Page<IssueDetailDto> issueDetailDtos = issueService.findTop3Issue(id, PageRequest.of(page3, size3));
        log.debug("issue = {}", issueDetailDtos.toList());
        model.addAttribute("thisProject", issueDetailDtos.getContent().get(0).getProject());
        model.addAttribute("issues", issueDetailDtos);
        model.addAttribute("totalCount", issueDetailDtos.getTotalElements());
        model.addAttribute("size", issueDetailDtos.getSize());
        model.addAttribute("number", issueDetailDtos.getNumber());
        model.addAttribute("totalpages", issueDetailDtos.getTotalPages());
    }


    @GetMapping("/searchProjectEmployee.do")
    public ResponseEntity<?> searchEmployee(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "id") Long id
    ){
        List<EmployeeSearchDto> employees = projectService.findByNameAndProjectId(name, id);
        log.debug("employees = {}", employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
