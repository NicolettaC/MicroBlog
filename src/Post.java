package src;
import java.sql.Timestamp;
import java.util.LinkedList;
//import Myexceptions.*;


public class Post implements Post_interface  {
 
    //Elemento Tipico
    //<id, author, text, timestamp, [UserThatliked1, UserThatLiked2,....UserThatLikedN]>

    //Funzione di astrazione
    //  α(c) = {c.id, c.author,c.text,System.currentTimeMillis(), < c.likes.get(index) | 0 ≤ index < c.likes.length() >}

    //Invariante di rappresentazione
    // this.id >=0 && this.author.length > 0 && text.length()<=140 && text.length()>0
    // && for all i. 0 <= i < this.likes.lenght() ==> this.likes.get(i) != null && this.likes.get(i) != this.author
    // && for all j. 0 <= j < this.likes.lenght() ==> this.likes.get(i) != this.likes.get(j)

    private final int id;               //id univoco post 
    private final String author;        //autore del post 
    private final String text;          //testo del post
    private final Timestamp timestamp;  //data e ora "pubblicazione" post
    private LinkedList<String> likes;   //lista di utenti che hanno messo mi piace al post
    
    //Costruttore
    Post(int id,String author,String text) throws NullPointerException, IllegalArgumentException
     {
        
        //se id è minore di 0 lancio eccezione
        if(id<0){
            throw new  IllegalArgumentException("Id negativo");
        }
        //se author o text hanno valori null lancio eccezione 
        if(author==null || text==null){
            throw new NullPointerException("Valori non validi");
        }
        //se il testo del post non rientra nel range 0-140 lancio eccezione 
        if(text.length()>140 || text.length()<=0){
            throw new IllegalArgumentException("Post non nel range tra 0 e 140 caratteri");
        }
        

        this.id=id;

        this.author=author;
        
        this.text=text;

        //per inizializzare data e ora pubblicazione post uso System.currentTimeMillis e timestamp
        //che restituiscono formato di data e ora del tipo anno-mese-giorno ora:minuti:secondi

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

        //se user ha valore null lancio eccezione
        if(user==null){
            throw new NullPointerException("Valore non valido");
        }
        
        //se user ha già messo like allora la lista likes contiene user 
        //per controllare che un utente non metta due volte like prendo l'indice associato ad user nella lista likes 
        //se indice==-1 allora vuol dire che user non ha inserito like a questo post
        int index = this.likes.indexOf(user);

        //se user non ha messo like e user != autore il like può essere inserito alla lista dei like
        //nel caso opposto non si utilizza un eccezione in quanto l'azione non porta ad errori quindi viene semplicemente ignorata
        if(index==-1 && this.getAuthor()!=user){
        this.likes.add(user);
        }
        
    }
    
    // EFFECTS: restituisce una rappresentazione di this come stringa
    public String toString() {
        //Stringa restituita del tipo autore :: "testo" data
        return this.author + " :: " + "\"" +  this.text  + "\"  " + this.timestamp.toString();
    }

    // EFFECTS:Restituisce true se l'oggetto p e' lo stesso post di this
    public boolean equals(Post p){
        return this.toString().equals(p.toString());
    }

   
}
