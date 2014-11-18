package restConsumingDemo.service.jerseyImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import restConsumingDemo.log.RcdLogging;
import restConsumingDemo.service.RestServiceBis;
import restConsumingDemo.service.RestServiceConstants;

/**
 * Implementació fent servir Jersey
 * 
 * @author asier
 *
 */
public class RestServiceJerseyImpl implements RestServiceBis {
	
	private Client client;
	private WebResource service;
	private RcdLogging log = new RcdLogging();
	private String user;
	
	/**
	 * Constructor. Requereix user i password
	 * 
	 * @param user -
	 * @param password -
	 */
	public RestServiceJerseyImpl(String user, String password) {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new HTTPBasicAuthFilter(user, password));
		service = client.resource(getUri());
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see restConsumingDemo.service.RestServiceBis#getRepoNames()
	 */
	public List<String> getRepoNames() {
		List<String> l = new ArrayList<String>();
		String jSon = service.path(RestServiceConstants.SEGMENT_USER).
				path(RestServiceConstants.SEGMENT_REPOS).
				accept(MediaType.APPLICATION_JSON).get(String.class);
		//log.log(jSon);
		JSONArray jArray = new JSONArray(jSon);
		if (jArray == null || jArray.length() <= 0) {
			return l;
		}
		JSONObject jObject  = null;
		for (int i = 0; i < jArray.length(); i++) {
			jObject = jArray.getJSONObject(i);
			l.add(jObject.getString(RestServiceConstants.JSON_PROPERTY_REPO_NAME));
		}
		return l;
	}
	
	/* (non-Javadoc)
	 * @see restConsumingDemo.service.RestServiceBis#deleteRepository(java.lang.String)
	 */
	public boolean deleteRepository(String repoName) {
		log.log("URI for delete: " + service.path(RestServiceConstants.SEGMENT_REPOS).
				path("/" + user).path("/" + repoName).getURI().toString());
		ClientResponse response = service.path(RestServiceConstants.SEGMENT_REPOS).
				path("/" + user).path("/" + repoName).delete(ClientResponse.class);
		
		if (response != null) { 
			log.log("Delete repository response status: " +
					String.valueOf(response.getStatus()));
			if (response.getStatus() == 204) return true;
		}
		return false;
	}
	
	/**
	 * Obte un Repositori
	 * 
	 * @param repoName nom del respositori
	 * @return les dades del repositori
	 */
	public JSONObject getRepo(String repoName) {
		String jSon = service.path(RestServiceConstants.SEGMENT_USER).
				path(RestServiceConstants.SEGMENT_REPOS).path("/" + repoName).
				accept(MediaType.APPLICATION_JSON).get(String.class);
		if (jSon == null) return null;
		return new JSONObject(jSon);
	}
	
	/**
	 * Obte la URI base de la api de GitHub
	 * 
	 * @return la URI
	 */
	public URI getUri() {
		return UriBuilder.fromUri(RestServiceConstants.GITHUB_BASE_URI).build();
	}

}
