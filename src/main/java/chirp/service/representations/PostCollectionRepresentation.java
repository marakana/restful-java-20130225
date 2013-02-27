package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.model.User;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value = @Ref("/post/{username}"), rel = "self"),
	@Link(value = @Ref("/user/{username}"), rel = "related")
})
public class PostCollectionRepresentation {

	@Ref("/post/{username}")
	private URI self;

	private final String username;
	private final Collection<PostRepresentation> posts;

	public PostCollectionRepresentation(User user, Collection<Post> posts) {
		this.username = user.getUsername();
		this.posts = new ArrayList<PostRepresentation>();
		for (Post post : posts) {
			this.posts.add(new PostRepresentation(post, true));
		}
	}

	@JsonCreator
	public PostCollectionRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("posts") Collection<PostRepresentation> posts) {
		this.username = null;
		this.self = self;
		this.posts = posts;
	}

	public URI getSelf() {
		return self;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}

	public Collection<PostRepresentation> getPosts() {
		return posts;
	}

}
