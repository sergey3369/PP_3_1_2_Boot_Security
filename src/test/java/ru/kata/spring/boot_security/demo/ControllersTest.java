package ru.kata.spring.boot_security.demo;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kata.spring.boot_security.demo.controller.ControllerBase;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@WebMvcTest(ConstrollerBase.class)
public class ControllersTest {

    private UserDao userDao = mock(UserDao.class);


    User user1 = User.builder().id(1L).name("denis").age(33).lastName("Voronov").password("123qwe").build();
    User user2 = User.builder().id(2L).name("sergey").age(22).lastName("Bolokon").password("rty456").build();
    User user3 = User.builder().id(3L).name("kirill").age(11).lastName("lokonmov").password("326ytr").build();


    @Test
    public void getUsers_success() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));

        given(userDao.getUsers()).willReturn(users);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin"))
                .andExpect(status().isOk());
    }
}
