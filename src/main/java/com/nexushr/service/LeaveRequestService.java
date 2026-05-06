package com.nexushr.service;

import com.nexushr.dto.leave.LeaveRequestDto;
import com.nexushr.dto.leave.LeaveResponseDto;
import com.nexushr.dto.leave.LeaveStatusUpdateDto;

public interface LeaveRequestService {
    LeaveResponseDto applyLeave(LeaveRequestDto dto);
    LeaveResponseDto updateLeaveStatus(Long id, LeaveStatusUpdateDto dto);
}