package com.mbtips.domain.virtualfriend.response;

import com.mbtips.virtualfriend.entity.VirtualFriend;

public record VirtualFriendResponse (
        Long virtualFriendId,
        Long conversationId,
        String mbti,
        String virtualFriendName,
        int virtualFriendAge,
        String virtualFriendSex,
        String virtualFriendRelationship
){
    public VirtualFriendResponse VirtualFriendResponse(VirtualFriend friend, Long conversationId) {
        return from(friend, conversationId);
    }
    public static VirtualFriendResponse from(VirtualFriend friend ,Long conversationId) {
        return new VirtualFriendResponse(
                friend.getVirtualFriendId(),
                conversationId,
                findMBTI(friend),
                friend.getVirtualFriendName(),
                friend.getVirtualFriendAge(),
                friend.getVirtualFriendSex(),
                friend.getVirtualFriendRelationship()
        );
    }

    private static String findMBTI(VirtualFriend friend) {
        StringBuilder sb = new StringBuilder();
        sb.append(friend.getEorI());
        sb.append(friend.getNorS());
        sb.append(friend.getTorF());
        sb.append(friend.getJorP());
        return sb.toString();
    }
}
