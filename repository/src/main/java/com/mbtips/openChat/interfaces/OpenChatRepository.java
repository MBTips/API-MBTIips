package com.mbtips.openChat.interfaces;

import com.mbtips.domain.openChat.OpenChat;

import java.util.List;

public interface OpenChatRepository {

    void save(OpenChat openChat);

    List<OpenChat> findActiveOpenChats();
}
