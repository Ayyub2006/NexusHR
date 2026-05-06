package com.nexushr.dto.employee;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveBalanceDto {
    private Long employeeId;
    private Integer casualLeaveRemaining;
    private Integer paidLeaveRemaining;
}