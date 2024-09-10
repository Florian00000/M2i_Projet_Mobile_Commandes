package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<RoleDTO> roles;
}
