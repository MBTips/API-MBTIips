package com.mbtips.virtualfriend.interfaces;

import com.mbtips.domain.virtualfriend.Interest;
import com.mbtips.domain.virtualfriend.VirtualFriend;

import java.util.Collection;
import java.util.List;

public interface InterestRepository {

    void saveAll(Collection<Interest> interests);

    void deleteInterestByVirtualFriend(VirtualFriend virtualFriend);

    List<String> findTopicsByVirtualFriendId(Long virtualFriendId);
}
