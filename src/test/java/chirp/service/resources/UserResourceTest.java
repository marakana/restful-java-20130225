package chirp.service.resources;

import static com.sun.jersey.api.client.ClientResponse.Status.CREATED;
import static com.sun.jersey.api.client.ClientResponse.Status.FORBIDDEN;
import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import chirp.model.User;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserResourceTest extends ResourceTest {

	@Test
	public void createUserMustCreateUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("realname", "Test User");
		ClientResponse response = resource().path("user").path("testuser").put(ClientResponse.class, form);
		assertEquals(CREATED.getStatusCode(), response.getStatus());

		User user = getUserRepository().getUser("testuser");
		assertEquals("Test User", user.getRealname());
	}

	@Test
	public void createDuplicateUserMustBeForbidden() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("realname", "Test User");
		resource().path("user").path("testuser").put(form);
		ClientResponse response = resource().path("user").path("testuser").put(ClientResponse.class, form);
		assertEquals(FORBIDDEN.getStatusCode(), response.getStatus());
	}
}
