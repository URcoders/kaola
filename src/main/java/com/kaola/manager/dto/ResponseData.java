package com.kaola.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private int status;
    private String msg;
    private T data;
}
