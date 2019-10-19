import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.daisuke.persistence.jpa.ApplicationIssuesDAO;
import com.daisuke.persistence.jpa.repositories.ApplicationRepository;

public class DaisukeTestConfiguration {

    @Autowired
    ApplicationRepository repository;
    
    @Bean
    ApplicationIssuesDAO getAppDAO() {
	ApplicationIssuesDAO dao = new ApplicationIssuesDAO(repository);
	return dao;
    }
    

}
