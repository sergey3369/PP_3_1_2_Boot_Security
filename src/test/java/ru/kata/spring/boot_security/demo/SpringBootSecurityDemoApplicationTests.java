package ru.kata.spring.boot_security.demo;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dto.UserDto;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Log4j2
@SpringBootTest
class SpringBootSecurityDemoApplicationTests {
    @Mock
    UserDao userDao;

    @Test()
    @DisplayName("метод 1")
    void aa() {
        log.log(Level.WARN,"1");
        int odin = 1;
        int dva = 2;
        int tri = odin + dva;
        assertEquals(3 , tri, "Все ПЛОХО");

    }
    @Test()
    @DisplayName("метод 2")
    void ab() {
        given(userDao.getUsers()).willReturn(new ArrayList<>());
        log.log(Level.WARN,"2");
        int odin = 1;
        int dva = 2;
        int tri = odin + dva;
        verify(userDao.getUsers());
    }
    @Test()
    @DisplayName("метод 3")
    void ac() {
        log.log(Level.WARN,"3");
        int odin = 1;
        int dva = 2;
        int tri = odin + dva;
        assumeTrue(tri==3,":(");
    }
    @Test()
    @DisplayName("метод 4")
    void ad() {
        log.log(Level.WARN,"4");
        int odin = 1;
        int dva = 2;
        int tri = odin + dva;
        assertEquals(3 , tri, "Все хорошо");
    }


}
