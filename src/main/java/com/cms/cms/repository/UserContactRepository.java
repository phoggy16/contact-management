package com.cms.cms.repository;

import com.cms.cms.entity.UserContactEntity;
import com.cms.cms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserContactRepository extends JpaRepository<UserContactEntity, Long> {

    @Query(value = "SELECT e FROM UserContactEntity e WHERE e.user = :user and " +
            "(e.firstName Like %:search% or e.lastName Like %:search% or e.email Like %:search%)")
    List<UserContactEntity> findAllByUserId(UserEntity user,String search);
}
