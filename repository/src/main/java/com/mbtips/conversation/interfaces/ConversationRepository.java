package com.mbtips.conversation.interfaces;

import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.virtualfriend.VirtualFriend;

public interface ConversationRepository {

    Conversation save(Conversation conversation);

    void delete(Conversation conversation);

    Conversation findById(long conversationId);

    Conversation findByVirtualFriend(VirtualFriend virtualFriend);
}
