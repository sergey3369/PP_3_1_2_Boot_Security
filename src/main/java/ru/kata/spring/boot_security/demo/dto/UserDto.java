package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDto {

    private Long dtoId;

    private String dtoName;

    private String dtoLastName;

    private Integer dtoAge;

    private List<Role> dtoRoles;

}