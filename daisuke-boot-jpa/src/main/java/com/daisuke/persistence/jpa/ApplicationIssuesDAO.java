package com.daisuke.persistence.jpa;

import java.util.List;

import com.daisuke.persistence.jpa.repositories.ApplicationRepository;

public class ApplicationIssuesDAO extends GenericJpaDAO<ApplicationIssuesEntity> {

    private ApplicationRepository repository;
    
    public ApplicationIssuesDAO() {
	super();
	setClazz(ApplicationIssuesEntity.class);
    }
    
    public ApplicationIssuesDAO(ApplicationRepository repository) {
	this();
	this.repository = repository;
    }

    public ApplicationIssuesEntity findByKey(String key) {
	ApplicationIssuesEntity entity = new ApplicationIssuesEntity().setKey(key);
	List<ApplicationIssuesEntity> result = repository.findByEntity(entity);
	return result.get(0);
    }
}
