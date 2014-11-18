/**
 * 
 */
package restConsumingDemo.service;

import java.util.List;

/**
 * @author asier
 *
 */
public interface RestServiceBis {
	public List<String> getRepoNames();
	
	public boolean deleteRepository(String repoName);
}
