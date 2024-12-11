package com.flybook.mapper;

import com.flybook.model.dto.ProfileDTO;
import com.flybook.model.entity.Profile;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDTO profilEntityToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId( profile.getId() );
        profileDTO.setFirstname( profile.getFirstname() );
        profileDTO.setLastname( profile.getLastname() );
        profileDTO.setBirthday( profile.getBirthday() );
        profileDTO.setNbLuggage( profile.getNbLuggage() );

        return profileDTO;
    }

    @Override
    public Profile profilDTOToProfileEntity(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( profileDTO.getId() );
        profile.setFirstname( profileDTO.getFirstname() );
        profile.setLastname( profileDTO.getLastname() );
        profile.setBirthday( profileDTO.getBirthday() );
        profile.setNbLuggage( profileDTO.getNbLuggage() );

        return profile;
    }
}
