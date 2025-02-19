package com.devopsintegration.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAlreadyExist extends RuntimeException{

    private String resourcename;

    public UserAlreadyExist(String resourcename) {
        super(String.format(resourcename));
        this.resourcename = resourcename;
    }
}
