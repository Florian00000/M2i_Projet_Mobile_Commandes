package com.example.dto.authdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<RoleDTO> roles;
}
