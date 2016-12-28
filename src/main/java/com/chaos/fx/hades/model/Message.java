package com.chaos.fx.hades.model;

import lombok.Data;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
@Data
public class Message<T> {
    private int code;
    private String message;
    private T body;
}
