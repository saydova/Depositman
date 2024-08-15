package com.bank.depositman.utils.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDTO {
    private String username;
    private String password;
}
