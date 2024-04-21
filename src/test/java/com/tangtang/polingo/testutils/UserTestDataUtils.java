package com.tangtang.polingo.testutils;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.constant.LoginType;
import com.tangtang.polingo.user.constant.UserRole;
import com.tangtang.polingo.user.entity.User;

public class UserTestDataUtils {

    public static final User testUser;


    static {
        testUser = User.builder()
                .id(1L)
                .nickname("홍길동")
                .language(Language.ENGLISH)
                .role(UserRole.COMMON)
                .loginType(LoginType.GOOGLE)
                .providerId("123456789")
                .build();

    }


}
