package src;

import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


import java.util.List;

import src.Myexceptions.*;

public class MySocialNetwork /*implements SocialNetwork*/ {
    

   private Map<String, Set<String>> UserFollowers;  //Struttura dati con utenti associati ai loro followers
   private Map<String, Set<Post>> UsersPosts;        //Struttura dati con utenti associati ai loro post
   private Map<Integer, Post> Posts;                //Struttura dati con tutti i post
   private int ID;


   public MySocialNetwork (){
      this.UserFollowers = new HashMap<String,Set<String>>();
      this.UsersPosts = new HashMap<String, Set<Post>>();
      this.Posts = new HashMap<Integer, Post>();
      this.ID=0;
   }


   public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException{
      
      if(ps==null){
         throw new NullPointerException();
      }

      for(Post iPost: ps){

         String author=iPost.getAuthor();

         for(int i=0;i < iPost.getLikes().size();i++){
            
            this.UserFollowers.get(author).add(iPost.getLikes().get(i));
         }

      }

      return UserFollowers;

   }

   public List<String> influencers(Map<String,Set<String>> followers){
      
      



      




      
     

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

      for(Post Ipost: ps){
         users.add(Ipost.getAuthor());
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
      if(username == null) {
         throw new NullPointerException();
      }
     if(this.UsersPosts.containsKey(username)==false){
      throw new NoUserFoundException("l'utente non è stato registrato");
      }

      LinkedList<Post> PostUsername = new LinkedList<Post>();

      for(Post post: ps){
         if(post.getAuthor().equals(username)){
            PostUsername.add(post);
         }
      }
     
     return PostUsername;

   }

   public List<Post> containing(List<String> words) throws NullPointerException{

      List<Post> PostWithWords = new LinkedList<Post>();
      
      //il metodo HashMap.values() ritorna una vista collezione dei valori della map.
      //in questo caso i Post
      for(Post Ipost: Posts.values()){

         //per ogni parola all'interno della list<String> in input

         for(String searchedWord: words){
            
            //se il testo del post contiene una delle parole si può uscire dal ciclo in quanto già si è trovata almeno una parola
            if(Ipost.getText().contains(searchedWord)){

               PostWithWords.add(Ipost);
               break;
            }

         }
      }

      return PostWithWords;

   }

   public void createUser(String username) throws NullPointerException, DuplicateUserException{

      if(username==null){
         throw new NullPointerException("il campo username deve essere diverso da null");
      }

      if (this.UsersPosts.containsKey(username)) {
         throw new DuplicateUserException("questo username esiste già");
     }
    //aggiungo il suo nome alla lista utenti delle tre strutture interne
     this.UsersPosts.put(username, new HashSet<Post>());
     this.UserFollowers.put(username, new HashSet<String>());

   }

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

   private int GetUnId(){
      return(this.ID)++;
   }

   public void Like(Post ps,String username) throws NoUserFoundException, DuplicateUserException{
      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }
      ps.addLikes(username);

   }

   public void Unlike(Post ps,String username) throws NoUserFoundException, DuplicateUserException{


      if(this.UsersPosts.containsKey(username)==false){
         throw new NoUserFoundException("l'utente non è stato registrato");
      }
      ps.removeLikes(username);

   }

   public Map<String, Set<String>> getUserFollowers(){
      return UserFollowers;
   }

   public int Contafollower(String username){


   }
}
