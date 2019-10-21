package com.daisuke.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daisuke.persistence.jpa.ApplicationIssuesEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationIssuesEntity, Integer> {

    @Query("SELECT ai FROM ApplicationIssuesEntity ai WHERE ai.key = (:pKey)")
    List<ApplicationIssuesEntity> findByKey(@Param("pKey") String key);
    @Query("SELECT ai FROM ApplicationIssuesEntity ai WHERE ai.name = (:pName)")
    List<ApplicationIssuesEntity> findByName(@Param("pName") String name);
}
