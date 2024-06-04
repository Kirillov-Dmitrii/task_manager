package org.grogu.task_manager.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
