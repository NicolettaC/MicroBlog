package src;
import java.sql.Timestamp;
import java.util.LinkedList;

public interface Post_interface {

    //!!!!!!!!!!!!!!!AGGIUNGERE IL FORMALMENTE NELLE CLAUSOLE EFFECT!!!!!!!!!!!!!!!!

    //overview: Tipo modificabile che rappresenta un post di un social network

    //Typical Element: <Id,Auhor,Text,Timestamp,[Like1,....Liken]> una quintupla 


    // EFFECTS: restituisce l'attributo id dell'oggetto
    public int getId();
    // EFFECTS: restituisce l'attributo Author dell'oggetto
    public String getAuthor();
    // EFFECTS: restituisce l'attributo Text dell'oggetto
    public String getText();
    // EFFECTS: restituisce l'attributo TimeStamp dell'oggetto
    public Timestamp getTimestamp();
    // EFFECTS: restituisce l'attributo Likes dell'oggetto
    public LinkedList<String> getLikes();

    // REQUIRES: user != null && user ∉ this.likes && user != this.author
    // THROWS: NullPointerException se user==null (unchecked exception)        
    // MODIFIES: this
    // EFFECTS: se user ∉ this.likes && user != this.author si aggiunge user in coda alla lista likes
    public void addLikes(String user);

  
 

}



