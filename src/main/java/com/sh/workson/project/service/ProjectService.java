package com.sh.workson.project.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.employee.dto.EmployeeProjectOwnerDto;
import com.sh.workson.employee.dto.EmployeeSearchDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.*;
import com.sh.workson.project.entity.*;
import com.sh.workson.project.repository.ProjectEmployeeRepository;
import com.sh.workson.project.repository.ProjectRepository;
import com.sh.workson.project.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private S3FileService s3FileService;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ProjectEmployeeRepository projectEmployeeRepository;
    @Autowired
    private TaskService taskService;

    public Page<ProjectListDto> findAll(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(project -> convertToProjectDto(project));
    }


    public Page<ProjectListDto> findByEmpId(Employee employee, Pageable pageable) {
        Page<Project> projects = projectRepository.findByEmpId(employee.getId(), pageable);
        return projects.map(project -> convertToProjectDto(project));
    }

    private ProjectListDto convertToProjectDto(Project project) {
        ProjectListDto projectListDto = modelMapper.map(project, ProjectListDto.class);

        EmployeeProjectOwnerDto employeeProjectOwnerDto = EmployeeProjectOwnerDto.builder()
                .id(project.getEmployee().getId())
                .name(project.getEmployee().getName())
                .email(project.getEmployee().getEmail())
                .deptName(project.getEmployee().getDepartment().getName())
                .positionName(project.getEmployee().getPosition().getName())
                .profileUrl(project.getEmployee().getProfileUrl())
                .build();
        projectListDto.setEmployee(employeeProjectOwnerDto);

        return projectListDto;
    }

    /**
     * mapper로 projectCreateDto -> project로 변환
     * s3service한테 파일 저장 요청
     * @param projectCreateDto
     */
    public void createProject(ProjectCreateDto projectCreateDto) {
        Project project = modelMapper.map(projectCreateDto, Project.class);
        log.debug("project = {}", project);

        // mapper가 처리하지 못하는 startAt, endAt, status, emplist 처리하기
        // status, startAt, endAt -> 편의메소드로 처리
        project.setStatus(Status.NOT_YET);

        projectRepository.save(project);
        // emplist 처리
        List<ProjectEmployee> createEmployees = new ArrayList<>();
        for(Long empId :projectCreateDto.getCreateEmp()){
            createEmployees.add(ProjectEmployee.builder().projectId(project.getId()).role(ProjectRole.CREATE).employee(Employee.builder().id(empId).build()).build());
        }
        projectEmployeeRepository.saveAll(createEmployees);
        List<ProjectEmployee> readEmployees = new ArrayList<>();
        for(Long empId :projectCreateDto.getReadEmp()){
            readEmployees.add(ProjectEmployee.builder().projectId(project.getId()).role(ProjectRole.READ).employee(Employee.builder().id(empId).build()).build());
        }
        projectEmployeeRepository.saveAll(readEmployees);

        // attach의 projectId(게시판이라면 boardId) 셋팅 및 attachment 타입으로 변환
        if(!projectCreateDto.getAttaches().isEmpty()) {
            for (AttachmentCreateDto attach: projectCreateDto.getAttaches()) {
                attach.setBoardId(project.getId());
                Attachment attachment = modelMapper.map(attach, Attachment.class);
                log.debug("attachment = {}", attachment);
                attachmentRepository.save(attachment);
            }
        }
    }

    public Page<ProjectListDto> findByOwnerId(Employee employee, Pageable pageable) {
        Page<Project> projects = projectRepository.findByOwnerId(employee.getId(), pageable);
        return projects.map(project -> convertToProjectDto(project));
    }

    public ProjectDetailDto findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();

        return convertToProjectDetailDto(project);
    }

    private ProjectDetailDto convertToProjectDetailDto(Project project) {
        ProjectDetailDto projectDetailDto = modelMapper.map(project, ProjectDetailDto.class);

        if(!project.getTasks().isEmpty()){
            List<TaskListDto> taskListDtos = new ArrayList<>();
            for(Task task : project.getTasks()){
                taskListDtos.add(taskService.convertToTaskListDto(task));
            }
            projectDetailDto.setTasks(taskListDtos);
        }

        return projectDetailDto;
    }

    public List<ProjectEmployee> findAllProjectEmployeesByProjectID(Long projectId) {
        return projectEmployeeRepository.findAllProjectEmployeesByProjectID(projectId);
    }

    public List<Attachment> findAllAttachmentByProjectId(Long id) {
        return attachmentRepository.findAllAttachmentByProjectId(id);
    }


    public void updateProject(ProjectUpdateDto projectUpdateDto) {
        projectRepository.save(projectUpdateDtoConvertToProjectDto(projectUpdateDto));
    }

    private Project projectUpdateDtoConvertToProjectDto(ProjectUpdateDto projectUpdateDto) {
        Project project = projectRepository.findById(projectUpdateDto.getId()).orElseThrow();
        project.setTitle(projectUpdateDto.getTitle());
        project.setStatus(projectUpdateDto.getStatus());
        project.setStartAt(projectUpdateDto.getStartAt());
        project.setEndAt(projectUpdateDto.getEndAt());
        return project;
    }

    public Page<ProjectListDto> findAllDoneProject(Long id, Pageable pageable) {
        Page<Project> projects = projectRepository.findByAllDoneProject(id, pageable);
        return projects.map(project -> convertToProjectDto(project));
    }

    public Page<ProjectDashBoardDto> findTop3Project(Long id, Pageable pageable) {
        Page<Project> projects = projectRepository.findTop3Project(id, pageable);
        return projects.map(project -> convertToProjectDashBoardDto(project));
    }

    private ProjectDashBoardDto convertToProjectDashBoardDto(Project project) {
        return new ProjectDashBoardDto(project.getId(), project.getTitle(), String.valueOf(project.getStatus()));
    }

    public List<EmployeeSearchDto> findByNameAndProjectId(String name, Long id) {
        List<Project> projects = projectRepository.findByNameAndProjectId(name, id);
        List<EmployeeSearchDto> employeeDtos = new ArrayList<>();
        for(int i = 0; i < projects.size(); i++){
            for(int j = 0; j < projects.get(i).getProjectEmployees().size(); j++){
                employeeDtos.add(converToEmployeeDtos(projects.get(i).getProjectEmployees().get(j).getEmployee()));
            }
        }
       return employeeDtos;
    }

    private EmployeeSearchDto converToEmployeeDtos(Employee employee) {
        return modelMapper.map(employee, EmployeeSearchDto.class);
    }
}
