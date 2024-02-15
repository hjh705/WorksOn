package com.sh.workson.schedule.service;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.schedule.dto.ScheduleCategoryDto;
import com.sh.workson.schedule.entity.ScheduleCategory;
import com.sh.workson.schedule.repository.ScheduleCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ScheduleCategoryService {
    @Autowired
    private ScheduleCategoryRepository scheduleCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ScheduleCategoryDto> findByEmpId(EmployeeDetails employeeDetails) {
        Employee employee = employeeDetails.getEmployee();
        Long id = employee.getId();
        List<ScheduleCategory> scheduleCategories = scheduleCategoryRepository.findByEmpId(employee.getId());
        List<ScheduleCategoryDto> scheduleCategoryDtos = scheduleCategories.stream()
                .map(this::convertToScheduleCategoryDto)
                .collect(Collectors.toList());
        return scheduleCategoryDtos;
    }

    private ScheduleCategoryDto convertToScheduleCategoryDto(ScheduleCategory scheduleCategory) {
        ScheduleCategoryDto scheduleCategoryDto = modelMapper.map(scheduleCategory, ScheduleCategoryDto.class);
        return scheduleCategoryDto;
    }
}