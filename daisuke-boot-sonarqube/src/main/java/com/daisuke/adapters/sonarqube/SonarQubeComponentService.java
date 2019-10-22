package com.daisuke.adapters.sonarqube;

import java.util.List;

import com.daisuke.domain.adapters.ComponentsAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.ComponentDTO;

public class SonarQubeComponentService implements  ComponentsAdapter<SearchComponent>{

    public SonarQubeComponentService() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public List<ComponentDTO> findComponents(SearchComponent search) throws SearchException {
	// TODO Auto-generated method stub
	return null;
    }

    public static class ComponentMapper{
	
    }
}
