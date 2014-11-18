/**
 * 
 */
package restConsumingDemo.service;

import java.util.List;

/**
 * Funcionalitat per proves
 * 
 * @author asier
 *
 */
public interface RestService {
	/**
	 * Llista els noms dels repositoris
	 * 
	 * @return llistat de noms
	 */
	public List<String> getRepoNames();
	
	/**
	 * Crea un repositori. Valors per defecte tret del nom
	 * 
	 * @param repoName nom del nou repositori
	 */
	public void createRepo(String repoName);
}
