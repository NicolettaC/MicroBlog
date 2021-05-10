package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
       
        MySocialNetwork sc = new MySocialNetwork();

        sc.createUser("username");
        sc.createUser("username3");
        sc.createUser("username2");
        sc.createUser("username4");
        sc.createUser("username5");

        Post p = sc.CreateNewPost("username", "diamine che rabbia");
        Post p2 = sc.CreateNewPost("username", "mi sto seccando");
        Post p4 = sc.CreateNewPost("username", "rabbia seccando");
        Post p5 = sc.CreateNewPost("username", "8");
        Post p6 = sc.CreateNewPost("username", "cabvolo");
        Post p102 = sc.CreateNewPost("username", " 6 ");
        Post p8 = sc.CreateNewPost("username", "rabbia");
        Post p9 = sc.CreateNewPost("username", "2");
        Post p10 = sc.CreateNewPost("username", "3");
        Post p11 = sc.CreateNewPost("username2", "5");
        Post p13 = sc.CreateNewPost("username5", "4");

         
        Post p7 = sc.CreateNewPost("username2", "adoawe0fioawp");
      


        sc.Like(p, "username3");
        sc.Like(p, "username2");
        sc.Like(p, "username3");
        sc.Like(p, "username5");
        sc.Like(p2, "username4");
        sc.Like(p7, "username");
        sc.Like(p7, "username4");

        
       
       

        List<Post> AllPosts = new LinkedList<Post>();

        AllPosts.add(p);
        AllPosts.add(p2);
        AllPosts.add(p7);
        AllPosts.add(p4);
        AllPosts.add(p5);
        AllPosts.add(p6);
        AllPosts.add(p11);


        Map<String,Set<String>> followers = new HashMap<String,Set<String>>();

        followers=sc.guessFollowers(AllPosts);

        System.out.println("followers" + followers);

        Map<String, Set<String>> UserFollowers = sc.getUserFollowers();

        sc.influencers(UserFollowers);


      /* List<Post> PostdiUser = sc.writtenBy("username");

       for(int i = 0; i < PostdiUser.size(); i++){
           System.out.println("Writtenby " +PostdiUser.get(i));
       }
       


       List<Post> PostdiUser2=sc.writtenBy(PostdiUser, "username");
       System.out.println("Writtenby con lista: " +PostdiUser2.size()); //11
       
       
       PostdiUser.add(p7);
       PostdiUser.add(p13);

       PostdiUser2=sc.writtenBy(PostdiUser, "username");
       System.out.println(PostdiUser2.size()); //11

       PostdiUser.remove(p7);

       Set<String> UserMenzionati=sc.getMentionedUsers();
       Set<String> UserMenzionatiPost=sc.getMentionedUsers(PostdiUser);

       for(String str : UserMenzionati){
        System.out.println("getMentionedUsers: " +str);
       }

       for(String str : UserMenzionatiPost){
        System.out.println("getMentionedUsers(list): "+str);
       }

       List<String> words = new LinkedList<String>();

       words.add("rabbia");
       words.add("cavolo");
       words.add("6");

       List<Post> PostdiUser3=sc.containing(words);

       for(int i = 0; i < PostdiUser3.size(); i++){
        System.out.println("Containing rabbia cavolo e 6: " +PostdiUser3.get(i));
    }

    }*/

  }
}
