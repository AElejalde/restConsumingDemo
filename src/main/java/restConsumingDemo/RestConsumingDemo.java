/**
 * 
 */
package restConsumingDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import restConsumingDemo.service.RestService;
import restConsumingDemo.service.RestServiceBis;
import restConsumingDemo.service.impl.RestServiceImpl;
import restConsumingDemo.service.jerseyImpl.RestServiceJerseyImpl;

/**
 * Petita app que permet crear i/o eliminar un repository
 * (sense opcions) a GitHub.
 * 
 * @author AEF
 *
 */
public class RestConsumingDemo {
	
	/**
	 * Demana usuari y pwd, llista repositoris, ofereix crear i/o eliminar
	 * un repositori torna a llistar els repositoris.
	 * 
	 * @param args -
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Starting!");
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter GitHub user:");
	    String user = bufferRead.readLine();
	    
	    System.out.println("Enter GitHub password:");
	    String password = bufferRead.readLine();
				
		RestServiceBis rsJersey = new RestServiceJerseyImpl(user, password);
		RestService rs = new RestServiceImpl(user, password);
		
		List<String> l = rs.getRepoNames();
		printRepos(l);
		
		System.out.println();
		System.out.println("Create new repo? (y/n)");
		String option = bufferRead.readLine();
		if (option.equalsIgnoreCase("y")) {
			System.out.println("Enter new repository name:");
			String repoName = bufferRead.readLine();
			
			System.out.println("Creating new repository: '" + repoName + "'");
			rs.createRepo(repoName);
		}
		
		System.out.println("Delete a repo? (y/n)");
		option = bufferRead.readLine();
		if (option.equalsIgnoreCase("y")) {
			System.out.println("Enter name of repository to delete:");
			String repoName = bufferRead.readLine();
			
			System.out.println("Deleting repository: '" + repoName + "'");
			rsJersey.deleteRepository(repoName);
		}
		
		l = rsJersey.getRepoNames();
		printRepos(l);
		
		System.out.println("Done!");
		
	}
	
	
	/**
	 * Printa el llistat
	 * 
	 * @param l llista de noms de repositoris
	 */
	protected static void printRepos(List<String> l) {
		System.out.println("List of repositories:");
		for (String repoName : l) {
			System.out.println(repoName);
		}
	}

}
