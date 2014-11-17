package restConsumingDemo.service.jerseyImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import restConsumingDemo.service.RestService;
import restConsumingDemo.service.RestServiceConstants;

/**
 * Implementació fent servir Jersey
 * 
 * @author asier
 *
 */
public class RestServiceJerseyImpl implements RestService {
	
	private Client client;
	private WebResource service;
	
	public RestServiceJerseyImpl(String user, String password) {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new HTTPBasicAuthFilter(user, password));
		service = client.resource(getUri());
	}

	public List<String> getRepoNames() {
		List<String> l = new ArrayList<String>();
		String jSon = service.path(RestServiceConstants.SEGMENT_USER).
				path(RestServiceConstants.SEGMENT_REPOS).
				accept(MediaType.APPLICATION_JSON).get(String.class);
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

	public void createRepo(String repoName) {
		// TODO Auto-generated method stub

	}
	
	public URI getUri() {
		return UriBuilder.fromUri(RestServiceConstants.GITHUB_BASE_URI).build();
	}

}
