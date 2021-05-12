package src;

import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


import java.util.List;

import src.Myexceptions.*;

public class SocialNetwork implements SocialNetwork_interface {

   private Map<String, Set<String>> Usersfollowing;  //Struttura dati con utenti associati ad utenti da essi seguiti  
   private Map<String, Set<Post>> UsersPosts;        //Struttura dati con utenti associati ai loro post
   private Map<Integer, Post> Posts;                //Struttura dati con tutti i post
   private int ID;


   public SocialNetwork (){
     
      this.Usersfollowing = new HashMap<String, Set<String>>();
      this.UsersPosts = new HashMap<String, Set<Post>>();
      this.Posts = new HashMap<Integer, Post>();
      this.ID=0;

   }

   public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException{


      if(ps==null){
         throw new NullPointerException();
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

  public List<String> influencers(Map<String,Set<String>> followers){
      
      List<String> InfluencersOut = new LinkedList<String>();
   
      for(String str : followers.keySet()){

        int  numFollowers = followers.get(str).size();
        int  numFollowing = this.Usersfollowing.get(str).size();

        if(numFollowers > numFollowing && numFollowers > 4){
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

   public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException {
      
      if(ps==null){
         throw new NullPointerException();
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
         throw new NoUserFoundException("l'utente non è stato registrato");
      }


      LinkedList<Post> PostUsername = new LinkedList<Post>();

      Set<Post> temp = this.UsersPosts.get(username);

      for(Post ps : temp){
         PostUsername.add(ps);
      }

      return PostUsername;

   }
   
   public List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException, NoUserFoundException {
     
      if(ps == null){
         throw new NullPointerException();
      }
      if(username == null) {
         throw new NullPointerException();
      }
     if(this.UsersPosts.containsKey(username)==false){
      throw new NoUserFoundException("l'utente non è stato registrato");
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
   //EFFECTS: Crea un nuovo user nella rete sociale
   public void createUser(String username) throws NullPointerException, DuplicateUserException{

      if(username==null){
         throw new NullPointerException("il campo username deve essere diverso da null");
      }

      if (this.UsersPosts.containsKey(username)) {
         throw new DuplicateUserException("questo username esiste già");
     }
    //aggiungo il suo nome alla lista utenti delle due strutture interne
     this.UsersPosts.put(username, new HashSet<Post>());
     this.Usersfollowing.put(username, new HashSet<String>());
    

   }
   //REQUIRES: username!=null && username ∈ utenti registrati
   //THROWS: NullPointerException se username == null
   //        NoUserFoundException se se username ∉ utenti registrati
   //EFFECTS: Elimina un user dalla rete sociale
   public void removeUser(String username) throws NullPointerException, NoUserFoundException{

      if(username == null){
         throw new NullPointerException();
      }

      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }

      this.UsersPosts.remove(username);
      this.Usersfollowing.remove(username);



   }

   //REQUIRES: author!=null && text!=null && author ∈ utenti registrati
   //THROWS: NullPointerException se author == null || text == null
   //        NoUserFoundException se author ∉ utenti registrati
   //EFFECTS: Crea un nuovo post nella rete sociale e restituisce il nuovo post
   public Post CreateNewPost(String author, String text) throws NullPointerException, NoUserFoundException{

      if(author==null || text == null){
         throw new NullPointerException();
      }

      if(this.UsersPosts.containsKey(author)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }

      
      int IdPost=this.GetUnId();
      Post newPost = new Post(IdPost,author,text);

      this.Posts.put(newPost.getId(),newPost);

      this.UsersPosts.get(author).add(newPost);
      
      return newPost;

   }

   //REQUIRES: username ∈ utenti registrati
   //THROWS: NoUserFoundException se username ∉ utenti registrati
   //EFFECTS: Aggiunge un like ad un post e aggiorna le relazioni di following
   public void Like(Post ps,String username) throws NoUserFoundException, DuplicateUserException{
      
      if(ps==null || username == null){
         throw new NullPointerException();
      } 

      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }
      ps.addLikes(username);

      this.Usersfollowing.get(username).add(ps.getAuthor());



   }

   //REQUIRES: username ∈ utenti registrati
   //THROWS: NoUserFoundException se username ∉ utenti registrati
   //EFFECTS: Elimina un like da un post e aggiorna le relazioni di following
   public void Unlike(Post ps,String username) throws NoUserFoundException, DuplicateUserException{
      
      if(ps==null || username == null){
         throw new NullPointerException();
      } 

      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }
      ps.removeLikes(username);

      this.Usersfollowing.get(username).remove(ps.getAuthor());

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

   //EFFECTS: Restituisce una map che associa a ogni utente gli utenti da esso seguiti
   public Map<String, Set<String>> getFollowing() throws NullPointerException {
     
      return this.Usersfollowing;

   }
   
   //EFFECTS: Restituisce id univoco 
   private int GetUnId(){
      return(this.ID)++;
   }


}
