package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

import com.sun.jersey.server.linking.Ref;

public class UserCollectionRepresentation {

	@Ref("/user")
	private URI self;

	private final Collection<UserRepresentation> users;

	public UserCollectionRepresentation(Collection<User> users) {
		this.users = new ArrayList<UserRepresentation>();
		for (User user : users) {
			this.users.add(new UserRepresentation(user, true));
		}
	}

	@JsonCreator
	public UserCollectionRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("users") Collection<UserRepresentation> users) {
		this.self = self;
		this.users = users;
	}

	public URI getSelf() {
		return self;
	}

	public Collection<UserRepresentation> getUsers() {
		return users;
	}

}
