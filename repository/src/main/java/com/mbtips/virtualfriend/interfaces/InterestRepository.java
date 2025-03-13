package com.mbtips.virtualfriend.interfaces;

import com.mbtips.domain.virtualfriend.Interest;

import java.util.Collection;

public interface InterestRepository {

    void saveAll(Collection<Interest> interests);
}
