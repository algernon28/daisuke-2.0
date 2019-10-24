package com.daisuke.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daisuke.persistence.jpa.entities.ApplicationIssues;

public interface ApplicationRepository extends JpaRepository<ApplicationIssues, Integer> {

    @Query("SELECT ai FROM ApplicationIssues ai WHERE ai.key = (:pKey)")
    List<ApplicationIssues> findByKey(@Param("pKey") String key);

    @Query("SELECT ai FROM ApplicationIssues ai WHERE ai.name = (:pName)")
    List<ApplicationIssues> findByName(@Param("pName") String name);
}
