package com.daisuke.persistence.jpa;

public class ApplicationIssuesDAO extends GenericJpaDAO<ApplicationIssuesEntity>{

    public ApplicationIssuesDAO() {
	super();
	setClazz(ApplicationIssuesEntity.class);
    }

}
