package com.exam.idcard.builder;

import com.exam.idcard.model.Profile;
import com.exam.idcard.model.ProfileType;

import java.util.UUID;

public class ProfileBuilder {

    public static Profile buildDefaultStudent() {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setType(ProfileType.STUDENT);

        return profile;
    }
}