package com.nexushr.service.impl;

import com.nexushr.dto.leave.LeaveRequestDto;
import com.nexushr.dto.leave.LeaveResponseDto;
import com.nexushr.dto.leave.LeaveStatusUpdateDto;
import com.nexushr.entity.Employee;
import com.nexushr.entity.LeaveRequest;
import com.nexushr.entity.enums.LeaveStatus;
import com.nexushr.entity.enums.LeaveType;
import com.nexushr.exception.BadRequestException;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.LeaveRequestRepository;
import com.nexushr.service.LeaveRequestService;
import com.nexushr.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveResponseDto applyLeave(LeaveRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));
        LeaveRequest leaveRequest = LeaveRequest.builder()
                .employee(employee)
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status(LeaveStatus.PENDING)
                .build();

        return MapperUtil.mapLeave(leaveRequestRepository.save(leaveRequest));
    }
    
    @Override
    @Transactional
    public LeaveResponseDto updateLeaveStatus(Long id, LeaveStatusUpdateDto dto) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));

        if (dto.getStatus() == LeaveStatus.APPROVED && leaveRequest.getStatus() != LeaveStatus.APPROVED) {
            Employee employee = leaveRequest.getEmployee();
            if (leaveRequest.getLeaveType() == LeaveType.CL) {
                if (employee.getCasualLeaveBalance() <= 0) {
                    throw new BadRequestException("No casual leave balance remaining.");
                }
                employee.setCasualLeaveBalance(employee.getCasualLeaveBalance() - 1);
            } else {
                if (employee.getPaidLeaveBalance() <= 0) {
                    throw new BadRequestException("No paid leave balance remaining.");
                }
                employee.setPaidLeaveBalance(employee.getPaidLeaveBalance() - 1);
            }
        }
        
        leaveRequest.setStatus(dto.getStatus());
        return MapperUtil.mapLeave(leaveRequestRepository.save(leaveRequest));
    }
}