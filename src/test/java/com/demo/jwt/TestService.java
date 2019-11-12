package com.demo.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.demo.jwt.token.Payload;
import com.demo.jwt.utils.JWTService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author:yangyang
 * @date:2019/11/8
 * @descriptiom:
 */


@SpringBootTest
//@ContextConfiguration
@RunWith(SpringRunner.class)
public class TestService {

    @Autowired
    private JWTService jWTService;

    @Test
    public void verifyToken() throws UnsupportedEncodingException {
        Payload payload = jWTService.createPayload("USERSERVICE","userLoginToken","APP",new Date(),1);
        String token = jWTService.createToken();
        System.out.println(token);
        Payload verifyToken = jWTService.verifyToken(token);
        System.out.println(verifyToken);
        Assert.assertNotNull(verifyToken);
    }
}
