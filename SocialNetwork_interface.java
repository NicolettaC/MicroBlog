package src;
import java.util.Map;
import java.util.Set;
import java.util.List;

import src.Myexceptions.*;


public interface SocialNetwork_interface {
    
    //REQUIRES: ps!=null && for all ( posts.getID ∈ List<Post> ps ) ∈ PostDellaRete
    //THROWS: NullPointerException se ps == null 
    //        NoPostException se for all ( posts.getID ∈ List<Post> ps ) ∉ PostDellaRete
    //EFFECTS: restituisce una map che associa a ogni utente riferito dalla lista ps gli utenti che lo seguono
    public	Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException, NoPostException;

    //REQUIRES: followers!=null 
    //THROWS: NullPointerException se followers == null 
    //EFFECTS: restituisce una map che associa a ogni utente riferito dalla lista ps gli utenti che lo seguono
    public	List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException;
    
    //EFFECTS: restituisce un insieme di stringhe che identificano tutti gli utenti registrati alla rete
    public	Set<String>	getMentionedUsers();
   
    //REQUIRES: ps != null && for all ( posts.getID ∈ List<Post> ps ) ∈ PostDellaRete
    //THROWS: NullPointerException se ps == null 
    //        NoPostException se for all ( posts.getID ∈ List<Post> ps ) ∉ PostDellaRete
    //EFFECTS: restituisce un insieme di stringhe che identificano tutti gli utenti registrati alla rete sociale  e che hanno almeno un post a essi associato
    public	Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException, NoPostException;
    
    //REQUIRES: username != null && username ∈ utenti registrati
    //THROWS: NullPointerException se ps == null
    //        NoUserFoundException se username ∉ utenti registrati
    //EFFECTS: restituisce una lista di post che identifica un sottoinsieme dei post presenti nella rete che hanno come autore username
    public	List<Post> writtenBy(String username) throws NullPointerException, NoUserFoundException;

    //REQUIRES: ps!= null && username ∈ utenti registrati && for all ( posts.getID ∈ List<Post> ps ) ∈ PostDellaRete
    //THROWS: NullPointerException se ps == null 
    //        NoUserFoundException se username ∉ utenti registrati
    //        NoPostException se for all ( posts.getID ∈ List<Post> ps ) ∉ PostDellaRete
    //EFFECTS: restituisce una lista di post che identifica un sottoinsieme dei post presenti nella lista ps che hanno come autore username 
    public	List<Post> writtenBy(List<Post> ps, String	username) throws NullPointerException, NoUserFoundException, NoPostException;
    
    //REQUIRES: words != null && searchedWord.length > 0
    //THROWS: NullPointerException se words == null
    //        IllegalCallerException se searchedWord.length <=0
    //EFFECTS: restituisce una lista di post che identifica un sottoinsieme dei post che contengono le parole nella lista words
    public	List<Post> containing(List<String> words) throws NullPointerException;
}
