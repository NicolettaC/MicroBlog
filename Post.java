package src;
import java.sql.Timestamp;
import java.util.LinkedList;
//import Myexceptions.*;


public class Post implements Post_interface  {
 
    //Elemento Tipico
    //<id, author, Text, timestamp, [UserThatliked1, UserThatLiked2,....UserThatLikedN]>

    //Funzione di astrazione
    //  α(c) = {c.id, c.author,c.text,System.currentTimeMillis(), < c.likes.get(index) | 0 ≤ i < c.likes.length() >}

    //Invariante di rappresentazione
    //this.id >=0 && this.author.length > 0 && text.length()<140 && text.length()>0
    // && for all i. 0 <= i < this.likes.lenght() ==> this.likes.get(i) != null && this.likes.get(i) != this.author
    // && for all j. 0 <= j < this.likes.lenght() ==> this.likes.get(i) != this.likes.get(j)

    private int id;                     //id univoco post 
    private final String author;        //autore del post, si suppone essere univoco per ogni utente in quanto 
                                        //'Una	persona	è rappresentata dal	nome sulla rete	sociale.'
    private final String text;          //testo del post, text.length()<=140
    private final Timestamp timestamp;  //data e ora "pubblicazione" post
    private LinkedList<String> likes;   //lista di utenti che hanno messo mi piace al post
    
    
    Post(int id,String author,String text) throws NullPointerException, IllegalArgumentException
     {
        
        if(id<0 || author.length()<=0 ){
            throw new  IllegalArgumentException("Id deve essere positivo e l'autore di lunghezza maggiore di 0");
        }
        if(author==null || text==null){
            throw new NullPointerException();
        }
        //Il post ha un limite di 140 caratteri
        if(text.length()>140 || text.length()<=0){
            //VEDI SE E' MEGLIO PROPRIA ECCEZIONE O UNA NUOVA
            throw new IllegalArgumentException("Post non nel range tra 0 e 140 caratteri");
        }
        
        
        
        this.id=id;

        this.author=author;
        
        this.text=text;

        this.timestamp= new Timestamp(System.currentTimeMillis());

        this.likes=new LinkedList<String>();

     }


     public int getId(){
         return this.id;
     }
     
    public String getAuthor(){
        return this.author;
    }

    public String getText(){
        return this.text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public LinkedList<String> getLikes(){
        
        return this.likes;

    }

    public void addLikes(String user) throws NullPointerException{
        
        if(user==null){
            throw new NullPointerException();
        }
        
        int index = this.likes.indexOf(user);

        //se user non ha messo mi piace e user != autore, in questo caso non si utilizza un eccezione in quanto l'azione non porta ad errori
        if(index==-1 && this.getAuthor()!=user){
        this.likes.add(user);
        }
        
    }
    
    // EFFECTS: restituisce una rappresentazione di this come stringa
    public String toString() {
        return this.author + " \"" + this.text + "\"  " + this.timestamp.toString();
    }

    // EFFECTS:Restituisce true se l'oggetto p e' lo stesso post di this
    public boolean equals(Post p){
        return this.toString().equals(p.toString());
    }

   
}
