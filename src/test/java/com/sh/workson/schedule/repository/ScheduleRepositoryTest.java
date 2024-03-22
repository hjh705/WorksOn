package com.sh.workson.schedule.repository;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.schedule.entity.Schedule;
import com.sh.workson.schedule.entity.ScheduleCategory;
import com.sh.workson.schedule.entity.ScheduleJoinMember;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleJoinMemberRepository scheduleJoinMemberRepository;
    @Autowired
    ScheduleCategoryRepository scheduleCategoryRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    @DisplayName("스케쥴 카테고리 한 건 등록")
    @Test
    void test1(){
        //given
        //샘플
        Employee employee = Employee.builder()
                .id(1L)
                .name("테스트사원")
                .build();

        //샘플 일정 카테고리
        ScheduleCategory scheduleCategory = ScheduleCategory.builder()
                .id(1L)
                .color("blue")
                .name("test일정")
                .employee(employee)
                .build();
        //when
        scheduleCategoryRepository.save(scheduleCategory);

        //then
        assertThat(employee).isNotNull();
        assertThat(scheduleCategory).isNotNull();
        assertThat(scheduleCategory.getColor())
                .isNotNull()
                .isEqualTo("blue");
    }

    @DisplayName("empId로 스케쥴 카테고리 조회")
    @Test
    void test2(){
        //given
        // select * from schedule_category where emp_id = 1 and name = 'test일정';
        Employee employee = employeeRepository.findById(1L).orElse(null);
        String name = "test일정";

        //when
        ScheduleCategory scheduleCategory = scheduleCategoryRepository.findByNameAndEmpId(employee, name);
        //then
        assertThat(scheduleCategory).isNotNull();
        assertThat(scheduleCategory.getEmployee().getId()).isEqualTo(1L);
        assertThat(scheduleCategory.getName()).isNotNull();
        assertThat(scheduleCategory.getColor()).isNotNull();
    }

    @DisplayName("스케쥴 색, 이름 변경")
    @Test
    void test3(){
        //given
        // Employee employee = employeeRepository.findById(51L).orElse(null);
        // ScheduleCategory scheduleCategory = scheduleCategoryRepository.findByName();
        // //when
        // scheduleCategory.setColor("red");
        // scheduleCategory.setName("테스트빨강");
        //
        // scheduleCategoryRepository.save(scheduleCategory);
        // //then
        // assertThat(scheduleCategory.getColor()).isEqualTo("red");
        // assertThat(scheduleCategory.getName()).isEqualTo("테스트빨강");

    }

    @DisplayName("테스트 일정 등록")
    @Test
    void test4(){
        //given
        Employee employee = employeeRepository.findById(1L).orElse(null);
        ScheduleCategory scheduleCategory = ScheduleCategory.builder()
                .employee(employee)
                .name("테스트카테고리")
                .color("red")
                .build();
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = Schedule.builder()
                .title("테스트 일정입니다")
                .startTime(now)
                .endTime(now.plusHours(2))
                .employee(employee)
                .scheduleCategory(scheduleCategory)
                .content("schedule content 테스트")
                .build();
        //when
        scheduleRepository.save(schedule);
        //then
        assertThat(schedule).isNotNull();
        assertThat(schedule.getTitle()).isEqualTo("테스트 일정입니다");
        assertThat(schedule.getScheduleCategory()).isEqualTo(scheduleCategory);
        assertThat(schedule.getEmployee()).isEqualTo(employee);
    }

    @DisplayName("일정 참여자 생성")
    @Test
    void test5(){
        //given

        Schedule schedule = Schedule.builder()
                .id(1L)
                .title("테스트 일정입니다")
                .build();
        Employee employee1 = Employee.builder()
                .id(1L)
                .name("테스트사원1")
                .build();
        Employee employee2 = Employee.builder()
                .id(2L)
                .name("테스트사원2")
                .build();
        Employee employee3 = Employee.builder()
                .id(3L)
                .name("테스트사원3")
                .build();

        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee1);
        employeesList.add(employee3);
        employeesList.add(employee2);
        //when
        ScheduleJoinMember scheduleJoinMember = ScheduleJoinMember.builder()
                .scheduleId(schedule)
                .employees(employeesList)
                .build();
        scheduleJoinMemberRepository.save(scheduleJoinMember);
        //then
        assertThat(scheduleJoinMember).isNotNull();
        assertThat(scheduleJoinMember.getEmployees()).size().isEqualTo(3);
    }

    @DisplayName("emp_id로 ScheduleCategory 목록 조회")
    @Test
    void test6(){
        //given
        Employee employee = employeeRepository.findById(1L).orElse(null);
        //when
        List<ScheduleCategory> scheduleCategories = scheduleCategoryRepository.findByEmpId(employee.getId());
        //then
        System.out.println(scheduleCategories);
    }


    // @DisplayName("")
    // @Test
    // void test(){
    //     //given
    //
    //     //when
    //
    //     //then
    // }
}