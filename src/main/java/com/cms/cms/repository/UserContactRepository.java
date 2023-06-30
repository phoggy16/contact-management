package com.cms.cms.repository;

import com.cms.cms.dto.UserContactResponseDto;
import com.cms.cms.entity.UserContactEntity;
import com.cms.cms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserContactRepository extends JpaRepository<UserContactEntity, Long> {

    @Query(value = "SELECT new com.cms.cms.dto.UserContactResponseDto(e.id,e.firstName,e.lastName,e.email,e.phoneNumber)" +
            " FROM UserContactEntity e WHERE e.user = :user and " +
            "(e.firstName Like %:search% or e.lastName Like %:search% or e.email Like %:search%)")
    List<UserContactResponseDto> findAllByUserIdBasedOnSearch(UserEntity user, String search);

    @Query(value = "SELECT new com.cms.cms.dto.UserContactResponseDto(e.id,e.firstName,e.lastName,e.email,e.phoneNumber)" +
            " FROM UserContactEntity e WHERE e.user = :user")
    List<UserContactResponseDto> findAllByUserId(UserEntity user);

    Optional<UserContactEntity> findByIdAndUser(Long id, UserEntity user);
}
