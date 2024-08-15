package com.bank.depositman.utils.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApprovalDTO {
    String approvalStatus;
    Integer idTrans;
}
