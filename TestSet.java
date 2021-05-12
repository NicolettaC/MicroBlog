package src;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestSet {
    public static void main(String[] args) throws Exception{
       


        /*test1 prova il metodo writtenby("Sophia Loren")  e il metodo containing({"oscar", "testa", "rumore"})
        OUTPUT DESIDERATO:
        Post Di Sophia Loren:
        Sophia Loren "La vita davanti a sè"  DATA
        Sophia Loren "Che bella l'italia"  DATA
        Sophia Loren "molto felice, serata oscar !!"  DATA
        Post contenenti 'oscar' 'testa' 'rumore' :
        Sophia Loren "molto felice, serata oscar !!"  DATA
        Lino Banfi "c'è molto rumore sul set" DATA
        */
        Test1();
        /* test2 prova i metodi getMentionedUsers(), GetallPosts(), writtenBy({AllPosts},Cristiana Capotondi) getMentionedUsers({Allposts}) e getMentionedUsers()
        OUTPUT DESIDERATO:
        Tutti gli utenti menzionati:
        Monica Bellucci
        Elena Sofia Ricci
        Sophia Loren
        Nino Frassica
        Cristiana Capotondi
        Alessandra Mastronardi
        Stefano accorsi
        Lino Banfi




        */
        Test2();





  
    }
    // test1 prova il metodo writtenby("Sophia Loren")  e il metodo containing({"oscar", "testa", "rumore"})
    public static void Test1() throws Exception{

                
        SocialNetwork sc = new SocialNetwork();

        sc.createUser("Sophia Loren");
        sc.createUser("Nino Frassica");
        sc.createUser("Stefano accorsi");
        sc.createUser("Alessandro Borghi");
        sc.createUser("Monica Bellucci");
        sc.createUser("Lino Banfi");
        sc.createUser("Cristiana Capotondi");
        sc.createUser("Alessandra Mastronardi");
        sc.createUser("Elena Sofia Ricci");

        Post Post1Sophia = sc.CreateNewPost("Sophia Loren", "molto felice, serata oscar !!");
        Post Post2Sophia = sc.CreateNewPost("Sophia Loren", "Che bella l'italia");
        Post Post3Sophia = sc.CreateNewPost("Sophia Loren", "La vita davanti a sè");
        Post Post1Monica = sc.CreateNewPost("Monica Bellucci", "Ciao sono Monica");
        Post Post1Lino = sc.CreateNewPost("Lino Banfi", "c'è molto rumore sul set");
        
        sc.Like(Post1Sophia, "Nino Frassica");
        sc.Like(Post1Sophia, "Stefano accorsi");
        sc.Like(Post1Sophia, "Monica Bellucci");
        sc.Like(Post1Monica, "Alessandro Borghi");
        sc.Like(Post1Sophia, "Lino Banfi");
        sc.Like(Post1Monica, "Alessandra Mastronardi");
        sc.Like(Post2Sophia, "Alessandro Borghi");
        sc.Like(Post2Sophia, "Lino Banfi");
        sc.Like(Post2Sophia, "Alessandra Mastronardi");
        sc.Like(Post2Sophia, "Elena Sofia Ricci");
        sc.Like(Post3Sophia, "Elena Sofia Ricci");

        sc.Like(Post1Lino, "Nino Frassica");
        sc.Like(Post1Lino, "Alessandro Borghi");
        sc.Like(Post1Lino, "Alessandra Mastronardi");

        List<Post> PostdiSophia = sc.writtenBy("Sophia Loren");

        System.out.println("Post di Sophia Loren:");
        for(Post ps: PostdiSophia){
            System.out.println(ps.toString());
        }

        List<String> words= new LinkedList<String>();

        words.add("oscar");
        words.add("testa");
        words.add("rumore");

        List<Post> PostConWords = sc.containing(words);

        System.out.println("Post contenenti 'oscar' 'testa' 'rumore' :");
        for(Post ps: PostConWords){
            System.out.println(ps.toString());
        }

    }

    // test2 prova i metodi getMentionedUsers(), GetallPosts(), writtenBy({AllPosts}) getMentionedUsers({Allposts}, "username") e getMentionedUsers()
    public static void Test2() throws Exception{

        SocialNetwork sc = new SocialNetwork();

        sc.createUser("Sophia Loren");
        sc.createUser("Nino Frassica");
        sc.createUser("Stefano accorsi");
        sc.createUser("Monica Bellucci");
        sc.createUser("Lino Banfi");
        sc.createUser("Cristiana Capotondi");
        sc.createUser("Alessandra Mastronardi");
        sc.createUser("Elena Sofia Ricci");

        Post Post1Sophia = sc.CreateNewPost("Sophia Loren", "molto felice, serata oscar !!");
        Post Post2Sophia = sc.CreateNewPost("Sophia Loren", "Che bella l'italia");
        Post Post3Sophia = sc.CreateNewPost("Sophia Loren", "La vita davanti a sè");
        Post Post1Monica = sc.CreateNewPost("Monica Bellucci", "Ciao sono Monica");
        Post Post1Lino = sc.CreateNewPost("Lino Banfi", "c'è molto rumore sul set");
        Post Post2Lino = sc.CreateNewPost("Lino Banfi", "stasera fa freddo");
        Post Post1Cristiana = sc.CreateNewPost("Cristiana Capotondi", "Una strana giornata");
        Post Post1Elena = sc.CreateNewPost("Elena Sofia Ricci", "Sono molto felice");
        Post Post2Cristiana = sc.CreateNewPost("Cristiana Capotondi", "Tanto affetto");


        Set<String> UserMenzionati=sc.getMentionedUsers();
        System.out.println("Tutti gli utenti Menzionati:");
        for(String str : UserMenzionati){
            System.out.println(str);
        }

        List<Post> AllPosts=sc.GetallPosts();
        System.out.println("Tutti i post");
        for(Post ps: AllPosts){
            System.out.println(ps.toString());
        }

        List<Post> CristianaPosts=sc.writtenBy(AllPosts,"Cristiana Capotondi");

        

        Set<String> UserMenzionatiPost=sc.getMentionedUsers(CristianaPosts);



    }
}
