package com.mbtips.virtualfriend.interfaces;

import com.mbtips.domain.virtualfriend.VirtualFriend;

import java.util.List;

public interface VirtualFriendRepository {

    VirtualFriend save(VirtualFriend virtualFriend);

    void delete(VirtualFriend virtualFriend);

    VirtualFriend findById(long virtualFriendId);

    List<Object[]> findVirtualFriendAndConversation(String userId);
}
