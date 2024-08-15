package com.bank.depositman.utils.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WebResponse<T> {
    private String message;
    private String status;
    private T data;
}
