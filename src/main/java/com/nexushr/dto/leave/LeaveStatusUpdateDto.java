package com.nexushr.dto.leave;

import com.nexushr.entity.enums.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveStatusUpdateDto {
    @NotNull
    private LeaveStatus status;
}