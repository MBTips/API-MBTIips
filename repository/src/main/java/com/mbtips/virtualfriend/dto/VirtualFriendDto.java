package com.mbtips.virtualfriend.dto;

import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VirtualFriendDto {

    private Long virtualFriendId;
    private String eandi;
    private String nands;
    private String tandf;
    private String jandp;
    private String virtualFriendName;
    private int virtualFriendAge;
    private String virtualFriendSex;
    private String virtualFriendRelationship;

    public static VirtualFriendDto convertToDto(VirtualFriend virtualFriend) {
        return new VirtualFriendDto(
                virtualFriend.getVirtualFriendId(),
                virtualFriend.getEandi(),
                virtualFriend.getNands(),
                virtualFriend.getTandf(),
                virtualFriend.getJandp(),
                virtualFriend.getVirtualFriendName(),
                virtualFriend.getVirtualFriendAge(),
                virtualFriend.getVirtualFriendSex(),
                virtualFriend.getVirtualFriendRelationship()
        );
    }
}
