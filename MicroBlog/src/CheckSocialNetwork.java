package src;


import java.util.Map;
import java.util.Set;

import src.Myexceptions.NoUserFoundException;

import java.util.HashMap;
import java.util.HashSet;

public class CheckSocialNetwork extends SocialNetwork{

    private Map<Post,Set<String>> ReportedPosts;
  

    public CheckSocialNetwork(){
        super();
        this.ReportedPosts = new HashMap<Post,Set<String>>();
    }

    //REQUIRES: user != null && user ∈ utenti registrati  && id ∈ posts
    //THROWS: NullPointerException se user == null || ps == null
    //        NoUserFoundException se user ∉ utenti registrati
    //EFFECTS: Aggiunge il post a quelli segnalati inserendo anche l'utente che l'ha segnalato
    public void ReportPost(String user, Post ps) throws NullPointerException,NoUserFoundException{

        if(user == null || ps == null){
            throw new NullPointerException(" Valori non validi");
        }

        Set<String> UserMenzionati = super.getMentionedUsers();

        if(UserMenzionati.contains(user)==false){
            throw new NoUserFoundException("utente non registrato");
        }

        // se è un post che è già stato "reportato" aggiungo l'utente 
       if(this.ReportedPosts.containsKey(ps)){
        this.ReportedPosts.get(ps).add(user);
       }
       else{ // se no inizializzo la map con quel post
           this.ReportedPosts.put(ps, new HashSet<String>());
           this.ReportedPosts.get(ps).add(user);
       }
       
    }

    //EFFECTS: Restituisce i post segnalati
    public Map<Post, Set<String>> GetReportedPost(){
        return this.ReportedPosts;
    }
    
    //REQUIRES: ps != null
    //THROWS: NullPointerException se ps == null
    //EFFECTS: Rimuove il post dai post segnalati
    public void RemoveReportedPost(Post ps) throws NullPointerException{
        
        if(ps==null){
            throw new NullPointerException("Valori non validi");
        }
        
        this.ReportedPosts.remove(ps);
    }

}
