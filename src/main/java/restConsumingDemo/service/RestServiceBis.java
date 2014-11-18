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
public interface RestServiceBis {
	/**
	 * Llista els noms dels repositoris
	 * 
	 * @return llistat de noms
	 */
	public List<String> getRepoNames();
	
	/**
	 * Elimina un repositori
	 * 
	 * @param repoName nom del repositori a elminar
	 * @return <code>true</code> si s'ha eliminat satisfactoriament
	 */
	public boolean deleteRepository(String repoName);
}
