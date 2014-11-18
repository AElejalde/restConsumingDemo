/**
 * 
 */
package restConsumingDemo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import restConsumingDemo.log.RcdLogging;
import restConsumingDemo.service.RestService;
import restConsumingDemo.service.RestServiceConstants;

/**
 * Implementació fent
 * 
 * @author asier
 *
 */
public class RestServiceImpl implements RestService {
	
	private RcdLogging log = RcdLogging.getInstance();
	
	private RepositoryService repoService = null;
		
	public RestServiceImpl(String user, String password) {
		
		GitHubClient client = getClient();
		client.setCredentials(user, password);
		RepositoryService repoService = new RepositoryService(client);
		this.repoService = repoService;
	}

	public List<String> getRepoNames() {
		
		List<String> names = getRepositoriesNames(repoService);
		return names;
	}
	
	public void createRepo(String repoName) {
		
		createRepository(repoName, repoService);
	}

	protected List<Repository> getRepositories(RepositoryService repoService) {
		List<Repository> l = null;
		try {
			l = repoService.getRepositories();
		} catch (IOException e) {
			log.log(e);
		}
		
		return l;
	}
	
	protected List<String> getRepositoriesNames(RepositoryService repoService) {
		List<Repository> l = getRepositories(repoService);
		List<String> names = new ArrayList<String>();
		for (Repository repo : l) {
			names.add(repo.getName());
		}
		return names;
	}

	private void createRepository(String repoName, RepositoryService repoService) {
		try {
			repoService.createRepository(getRepository(repoName));
		} catch (IOException e) {
			log.log(e);
		}
		
	}

	private Repository getRepository(String repoName) {
		Repository repo = new Repository();
		repo.setName(repoName);
		repo.setDescription(RestServiceConstants.REPO_DESCRIPTION);
		return repo;
	}

	protected GitHubClient getClient() {
		return new GitHubClient();
	}
}
