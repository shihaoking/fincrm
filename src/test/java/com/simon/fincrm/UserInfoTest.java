package com.simon.fincrm;

import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.IUserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;


/**
 * Created by jinshihao on 16/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/service-impl.xml", "classpath:/spring/spring-mybatis.xml"})
public class UserInfoTest {
    @Autowired
    private IUserInfo userInfo;

    @Test
    public void testGetUserInfo(){
        List<UserInfoDo> userInfoDoList =  userInfo.selectAll(true);
        assertNotNull(userInfoDoList);
    }
}
