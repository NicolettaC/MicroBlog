package src;

import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


import java.util.List;

import src.Myexceptions.*;

public class SocialNetwork implements SocialNetwork_interface {

   //Elemento tipico
   // <<Utente1, {Post1Utente1,Post2Utente1, ...}>,<Utente2,{Post1Utente2,Post2Utente2, ...}>  ...> 
   // <<Utente1, {UtenteSeguito1,UtenteSeguito2, ...}>,<Utente2,{UtenteSeguito1,UtenteSeguito2, ...}>,  ...> 
   // <<Id_Post, Post>,<Id_Post, Post>...>
   //Id
   //soglia

   //Funzione di astrazione
   // α(c) = {c.posts.get(i) | 0 <= i < c.posts.size()}

   //Invariante di rappresentazione
   // ( for all i. 0 <= i < this.UserPosts.lenght() && for all j. 0 <= j < this.UserPosts.lenght() )
   // => this.UserPosts.getKey(i) != this.UserPosts.getKey(j) && this.UserPosts.getKey(i) != null
   // &&  ( for all i. 0 <= i < this.Posts.lenght() && for all j. 0 <= j < this.Posts.lenght() )
   // => this.Posts.getKey(i) != this.Posts.getKey(j) && this.Posts.getKey(i) != null
   // && ( for all i. 0 <= i < this.Usersfollowing.lenght() && for all j. 0 <= j < this.Usersfollowing.lenght() )
   // => this.Usersfollowing.getKey(i) != this.Usersfollowing.getKey(j) && this.Usersfollowing.getKey(i) != null
   // && this.UserFollowing.size() == this.UserPosts.size()
   // && this.IDCount>=0 && this.soglia>=1

   //AGGIUNGERE SOGLIA


   private Map<String, Set<Post>> UsersPosts;        //Struttura dati con utenti associati ai loro post
   private Map<String, Set<String>> Usersfollowing;  //Struttura dati con utenti associati ad utenti da essi seguiti  
   private Map<Integer, Post> Posts;                 //Struttura dati con tutti i post

   private int Soglia;                               //soglia usata per metodo influencer, scala automaticamente all'aumentare del numero di utenti
   private int IDCount;                              //contatore per dare id ai post


   public SocialNetwork (){
     
      this.UsersPosts = new HashMap<String, Set<Post>>();
      this.Usersfollowing = new HashMap<String, Set<String>>();
      this.Posts = new HashMap<Integer, Post>();
      this.IDCount=0;
      this.Soglia=1;

   }

   public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException, NoPostException{


      if(ps==null){
         throw new NullPointerException();
      }

      for(Post iPost: ps){
         int IdPost=iPost.getId();
         if(this.Posts.containsKey(IdPost)==false){
            throw new NoPostException("Il post con id  " + IdPost + " non esiste");
         }
      }

      Map<String, Set<String>> copy = new HashMap<String, Set<String>>();
      

      for(Post iPost: ps){


         String author=iPost.getAuthor();

         //inserisco author nella copy solo se non è già presente
         if(copy.containsKey(author)==false){
         
         copy.put(author, new HashSet<String>());
         
         }

         for(int i=0;i < iPost.getLikes().size();i++){
            
            copy.get(author).add(iPost.getLikes().get(i));

         }

      }

      return copy;

   }

  public List<String> influencers(Map<String,Set<String>> followers) throws NullPointerException{
      
      if(followers==null){
         throw new NullPointerException("Valori non validi");
      }

      //inizializzo una lista e una soglia
      List<String> InfluencersOut = new LinkedList<String>();

      //int soglia=4;

      // un utente per essere influencer deve avere un numero di follower >soglia e > dei following 

      //per tutti gli utenti passati in input
      for(String str : followers.keySet()){

         // controllo numero di followers
        int  numFollowers = followers.get(str).size();

        //controllo numero di following
        int  numFollowing = this.Usersfollowing.get(str).size();

        if(numFollowers > numFollowing && numFollowers > this.Soglia){
           InfluencersOut.add(str);
        }


      }

      return InfluencersOut;

   }

   public Set<String> getMentionedUsers() {

      Set<String> users = new HashSet<String>();

      for(String key : UsersPosts.keySet()){
         users.add(key);
      }
      
      return users;

   }

   public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException,NoPostException {
      
      if(ps==null){
         throw new NullPointerException();
      }

      for(Post iPost: ps){
         int IdPost=iPost.getId();
         if(this.Posts.containsKey(IdPost)==false){
            throw new NoPostException("Il post con id  " + IdPost + " non esiste");
         }
      }

      Set<String> users=new HashSet<String>();

      for(Post ipost: ps){

         users.add(ipost.getAuthor());
      }

      return users;

   }

   public List<Post> writtenBy(String username) throws NullPointerException, NoUserFoundException{

      if(username==null){
         throw new NullPointerException();
      }

      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente " +username+ " non è stato registrato");
      }


      LinkedList<Post> PostUsername = new LinkedList<Post>();

      Set<Post> temp = this.UsersPosts.get(username);

      for(Post ps : temp){
         PostUsername.add(ps);
      }

      return PostUsername;

   }
   
   public List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException, NoUserFoundException, NoPostException {
     
      if(ps == null){
         throw new NullPointerException();
      }
      if(username == null) {
         throw new NullPointerException();
      }
     if(this.UsersPosts.containsKey(username)==false){
      throw new NoUserFoundException("l'utente " +username+" non è stato registrato");
      }

      for(Post iPost: ps){
         int IdPost=iPost.getId();
         if(this.Posts.containsKey(IdPost)==false){
            throw new NoPostException("Il post con id  " + IdPost + " non esiste");
         }
      }

      LinkedList<Post> PostUsername = new LinkedList<Post>();

      for(Post ipost: ps){

         if(ipost == null) {
            throw new NullPointerException();
         }

         if(ipost.getAuthor().equals(username)){
            PostUsername.add(ipost);
         }
      }
     
     return PostUsername;

   }

   public List<Post> containing(List<String> words) throws NullPointerException, IllegalArgumentException{


      if(words==null){
         throw new NullPointerException();         
      }
      List<Post> PostWithWords = new LinkedList<Post>();
      
      //il metodo HashMap.values() ritorna una vista collezione dei valori della map.
      //in questo caso i Post
      for(Post Ipost: Posts.values()){

         //per ogni parola all'interno della list<String> in input

         for(String searchedWord: words){

            if(searchedWord.length()<=0){
               throw new IllegalArgumentException();
            }

            
            //se il testo del post contiene una delle parole si può uscire dal ciclo in quanto già si è trovata almeno una parola
            if(Ipost.getText().contains(searchedWord)){

               PostWithWords.add(Ipost);
               break;
            }

         }
      }

      return PostWithWords;

   }

  //Seguono le implementazioni di metodi aggiunti da me

   //REQUIRES: username != null && username ∉ utenti registrati
   //THROWS: NullPointerException se username == null
   //        DuplicateUserException se username ∈ utenti registrati
   //EFFECTS: Registra un nuovo user nella rete sociale
   public void createUser(String username) throws NullPointerException, DuplicateUserException{

      if(username==null){
         throw new NullPointerException("il campo username deve essere diverso da null");
      }

      if (this.UsersPosts.containsKey(username)) {
         throw new DuplicateUserException(" username " + username + " esiste già");
     }
     
    //aggiungo il suo nome alla lista utenti delle due strutture interne
     this.UsersPosts.put(username, new HashSet<Post>());
     this.Usersfollowing.put(username, new HashSet<String>());
    

     //aggiorno soglia
     int numeroutenti= this.UsersPosts.size();

     if(numeroutenti>7){
     this.Soglia=(numeroutenti/2)-3;
     }

   
   }

   //REQUIRES: author!=null && text!=null && author ∈ utenti registrati
   //THROWS: NullPointerException se author == null || text == null
   //        NoUserFoundException se author ∉ utenti registrati
   //EFFECTS: Crea un nuovo post nella rete sociale e restituisce il nuovo post
   public Post CreateNewPost(String author, String text) throws NullPointerException, NoUserFoundException{

      if(author==null || text == null){
         throw new NullPointerException("valori non corretti");
      }

      if(this.UsersPosts.containsKey(author)==false){
         throw new NoUserFoundException("l'utente " + author +" non è stato registrato");
      }

      
      int IdPost=this.GetUnId();
      
      Post newPost = new Post(IdPost,author,text);

      this.Posts.put(newPost.getId(),newPost);

      this.UsersPosts.get(author).add(newPost);
      
      return newPost;

   }

   //REQUIRES: id>0 && username != null && username ∈ utenti registrati && id ∈ posts
   //THROWS: NoUserFoundException se username ∉ utenti registrati
   //        NullPointerException se username == null
   //        NoPostException se  id ∉ posts
   //        IllegalArgumentException se id<0
   //EFFECTS: Aggiunge un like ad un post e aggiorna le relazioni di following
   public void Like(int id,String username) throws NoUserFoundException, NullPointerException, NoPostException, IllegalArgumentException{
      
      if( username == null ){
         throw new NullPointerException("valori non corretti");
      } 
      if( id < 0 ){
         throw new IllegalArgumentException("valori non corretti");
      } 
      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente " +username+ " non è stato registrato");
      }
      if(this.Posts.containsKey(id)==false){
         throw new NoPostException("Il post con id  " +id+ " non esiste");
      }

      Post ps = this.Posts.get(id);
      
      ps.addLikes(username);

      if(username!=ps.getAuthor()){
        this.Usersfollowing.get(username).add(ps.getAuthor());
      }


   }

   //EFFECTS: Restituisce tutti i post presenti nella rete sociale
   public List<Post> GetallPosts (){
      //questo metodo ritorna la lista con tutti i post

      List<Post> Allposts = new LinkedList<Post>();

      for(Post Ipost : Posts.values()){
         Allposts.add(Ipost);
      }

      return Allposts;

   }

   //EFFECTS: Restituisce tutti gli utenti e gli utenti da esso seguiti
   public Map<String, Set<String>> getFollowing() throws NullPointerException {
     
      return this.Usersfollowing;

   }
   
   //EFFECTS: Restituisce id univoco 
   private int GetUnId(){
      return(this.IDCount)++;
   }

   //EFFECTS: Restituisce soglia 
   public int GetSoglia(){
      return this.Soglia;
   }

}
