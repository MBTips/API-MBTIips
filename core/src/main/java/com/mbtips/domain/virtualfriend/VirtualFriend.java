package com.mbtips.domain.virtualfriend;

import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VirtualFriend {

    private Long virtualFriendId;

    private User user;

    private String name;

    private String mbti;

    private int age;

    private Gender gender;

    private String job;

    private String friendType;

    private String freeSetting;

}
