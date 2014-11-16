/**
 * 
 */
package restConsumingDemo.service;

import java.util.List;

/**
 * @author asier
 *
 */
public interface RestService {
	public List<String> getRepoNames();
	
	public void createRepo(String repoName);
}
