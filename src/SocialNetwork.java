package src;
import java.util.Map;
import java.util.Set;
import java.util.List;




public interface SocialNetwork {
    
    public	 Map<String, Set<String>> guessFollowers(List<Post>	 ps);
    public	 List<String> influencers(Map<String, Set<String>>	 followers);
    public	Set<String>	getMentionedUsers();
    public	 Set<String> getMentionedUsers(List<Post> ps);
    public	List<Post_interface> writtenBy(String username) ;
    public	 List<Post_interface> writtenBy(List<Post> ps, String	username);
    public	List<Post_interface> containing(List<String> words);
}
