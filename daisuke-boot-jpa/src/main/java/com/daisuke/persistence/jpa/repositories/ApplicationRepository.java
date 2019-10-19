package com.daisuke.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daisuke.persistence.jpa.ApplicationIssuesEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationIssuesEntity, Integer> {

    List<ApplicationIssuesEntity> findByEntity(ApplicationIssuesEntity entity);
}
