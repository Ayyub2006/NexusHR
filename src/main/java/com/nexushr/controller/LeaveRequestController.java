package com.nexushr.controller;

import com.nexushr.dto.leave.LeaveRequestDto;
import com.nexushr.dto.leave.LeaveResponseDto;
import com.nexushr.dto.leave.LeaveStatusUpdateDto;
import com.nexushr.service.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("/request")
    public LeaveResponseDto applyLeave(@Valid @RequestBody LeaveRequestDto dto) {
        return leaveRequestService.applyLeave(dto);
    }
    
    @PutMapping("/{id}/status")
    public LeaveResponseDto updateLeaveStatus(@PathVariable Long id, @Valid @RequestBody LeaveStatusUpdateDto dto) {
        return leaveRequestService.updateLeaveStatus(id, dto);
    }
}