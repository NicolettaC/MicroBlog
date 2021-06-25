package src;

import java.util.LinkedList;
import java.util.List;
// import java.util.Map;
// import java.util.Set;
import java.util.Map;
import java.util.Set;

public class TestSet {
    public static void main(String[] args) {

        System.out.println("************************ INIZIO CREAZIONE RETE ************************ \n");

        // Creo nuovi utenti e post nella rete infine aggiungo dei like ad alcuni post
        SocialNetwork sc = creaRete();

        System.out.println("\n ************************ FINE CREAZIONE RETE ************************\n");

        System.out.println("************************ INIZIO TEST1 ************************");
        System.out.println("/////Questo test prova tutti i metodi della classe SocialNetwork\n");

        // Provo tutti i metodi
        Test1(sc);
        System.out.println("\n ************************ FINE TEST1 ************************ \n");

        System.out.println("************************ INIZIO TEST2 ************************ ");
        System.out.println("/////Questo test causa delle eccezioni\n");
        Test2(sc);
        System.out.println("\n ************************ FINE TEST2 ************************ \n");

        System.out.println("************************ INIZIO TEST3 ************************");
        System.out.println("//Questo test prova l'estensione gerarchica del tipo di dato SocialNetwork\n");
        Test3();
        System.out.println("\n ************************ FINE TEST3 ************************\n");

    }

    public static SocialNetwork creaRete() {

        SocialNetwork sc = new SocialNetwork();

        // creo automaticamente 15 utenti con username del tipo utentei
        // con i che va da 1 a 15

        System.out.println("-Creo 15 utenti,con username del tipo utente+i con i che va da 1 a 15 ");
        for (int i = 1; i <= 15; i++) {
            try {
                sc.createUser("utente" + i);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // provo ad inserire una seconda volta utente15, in questo caso avrò eccezione
        // DuplicateUserException
        // output: src.Myexceptions.DuplicateUserException: username utente15 esiste già

        System.out.println("-Provo a creare un utente già esistente e un utente con valore null");

        try {
            sc.createUser("utente15");
        } catch (Exception e) {
            System.out.println(e);
        }
        // provo ad inserire utente null, in questo caso avrò eccezione
        // NullPointerExceptions.
        // output: java.lang.NullPointerException: il campo username deve essere diverso
        // da null
        try {
            sc.createUser(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("-Creo nuovi post: \n");

        Post Post1 = null;
        Post Post2 = null;
        Post Post3 = null;
        Post Post4 = null;
        Post Post5 = null;
        Post Post6 = null;
        Post Post7 = null;
        Post Post8 = null;
        Post Post9 = null;
        Post Post10 = null;
        // Creo i post
        try {
            Post1 = sc.CreateNewPost("utente1", "che bella giornata");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Post2 = sc.CreateNewPost("utente1", "quando ci vuole, ci vuole. prova ");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post3 = sc.CreateNewPost("utente1", " l'agenda euristica.");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post4 = sc.CreateNewPost("utente2", " Questo è un post moltomoltomoltomolto lungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungolungo");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post4 = sc.CreateNewPost("utente2", "Indie raccolte prova ");
        } catch (Exception e) {
            System.out.println(e);
        }

        // provo a creare post con un utente non registrato, in questo caso avrò
        // eccezione NoUserFoundException
        // output: src.Myexceptions.NoUserFoundException: l'utente utente24 non è stato
        // registrato
        try {
            Post5 = sc.CreateNewPost("utente24", "Alberto e Enea gareggiano");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post5 = sc.CreateNewPost("utente2", "Alberto e Enea gareggiano");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post6 = sc.CreateNewPost("utente2", " invariabili : è una certezza.");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post7 = sc.CreateNewPost("utente10", "generalmente sembra l'opposto.");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post8 = sc.CreateNewPost("utente8", "notti prova tennistiche");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post9 = sc.CreateNewPost("utente5", " prova non si finiSce mai di imparare.");
        } catch (Exception e) {
            System.out.println(e);
        }

        // provo a creare post con un utente null, in questo caso avrò eccezione
        // NullPointerException
        // output: java.lang.NullPointerException: valori non corretti
        try {
            Post10 = sc.CreateNewPost(null, "eeeeeee");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Post10 = sc.CreateNewPost("utente14", "simpatizzante ma vorrei averne una conferma definitiva.");
        } catch (Exception e) {
            System.out.println(e);
        }

        // metodo GetallPosts in SocialNetwork, restituisce lista con tutti i post nella
        // rete
        List<Post> AllPosts = sc.GetallPosts();

        // per confrontare correttezza stampo la dimensione della lista+1
        // deve essere uguale 10

        System.out.println("   Ci sono " + AllPosts.size() + " post nella rete \n");

        System.out.println("-Inserisco dei like:");

        // inserisco ad post1 i like da utente 2 ad utente 15

        for (int i = 2; i <= 15; i++) {
            try {
                sc.Like(Post1.getId(), "utente" + i);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //inserisco ad utente 1 doppio like
        try {
            sc.Like(Post1.getId(), "utente2");
        } catch (Exception e) {
            System.out.println(e);
        }

        // inserisco a post7 i like di utente3 utente4 utente7 utente8
        try {
            sc.Like(Post7.getId(), "utente3");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post7.getId(), "utente4");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post7.getId(), "utente7");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post7.getId(), "utente8");
        } catch (Exception e) {
            System.out.println(e);
        }

        // inserisco a post4 i like di utente1 utente7 utente8 utente9 utente10 utente14
        // utente 15

        try {
            sc.Like(Post4.getId(), "utente1");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente9");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente10");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente14");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente7");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente8");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            sc.Like(Post4.getId(), "utente15");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            sc.Like(Post10.getId(), "utente14"); // questo like non verrà ignorato
        } catch (Exception e) {
            System.out.println(e);
        }

        // Inserisco a Post9 like di utente25 provocando eccezione
        try {
            sc.Like(Post9.getId(), "utente25");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Inserisco a Post9 like di utente6 utente7 utente8 utente9 utente10 utente 11
        for (int i = 6; i <= 11; i++) {
            try {
                sc.Like(Post9.getId(), "utente" + i);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // inserisco a post2 like di utente1, sarà ignorato in quanto proprietario del
        // post
        try {
            sc.Like(Post2.getId(), "utente1");
        } catch (Exception e) {
            System.out.println(e);
        }

        // inserisco a post5 like di utente1
        try {
            sc.Like(Post5.getId(), "utente1");
        } catch (Exception e) {
            System.out.println(e);
        }

        // inserisco a post3 like di utente12
        try {
            sc.Like(Post3.getId(), "utente12");
        } catch (Exception e) {
            System.out.println(e);
        }

        // inserisco a post6 like di utente12
        try {
            sc.Like(Post6.getId(), "utente12");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Inserisco a Post8 like di utente31 provocando eccezione
        try {
            sc.Like(Post8.getId(), "utente31");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Inserisco a post8 like di utente11
        try {
            sc.Like(Post8.getId(), "utente11");
        } catch (Exception e) {
            System.out.println(e);
        }

        return sc;
    }

    public static void Test1(SocialNetwork sc) {

        Set<String> UserRetePost = null;
        Map<String, Set<String>> followers = null;
        Map<String, Set<String>> following = null;
        List<String> Influencers = null;

        List<String> WordsContains = new LinkedList<String>();

        WordsContains.add("prova");
        WordsContains.add("Enea");
        WordsContains.add("conferma");

        List<Post> PostWithWords = null;

        //GetMentionedUsers()
        Set<String> UserRete = sc.getMentionedUsers();

        System.out.println("\n");
        System.out.println("-Gli utenti registrati nella rete sono " + UserRete.size() + ":");

        System.out.println(UserRete);

        //GetmentionedUsers(List<Post>)
        try {
            UserRetePost = SocialNetwork.getMentionedUsers(sc.GetallPosts());
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n");
        System.out.println("-Gli utenti che hanno Sritto almeno un post nella rete sono " + UserRetePost.size() + ":");

        System.out.println(UserRetePost);

        // Controllo followers di tutti gli utenti che hanno fatto un post e following
        // di tutti gli utenti nella rete
        System.out.println("\n");

        System.out.println("-Gli utenti che hanno fatto almeno un post hanno i seguenti follower:");

        try {
            followers = SocialNetwork.guessFollowers(sc.GetallPosts());
        } catch (Exception e) {
            System.out.println(e);
        }

        for (String str : followers.keySet()) {
            System.out.println("   i follower di " + str + " sono: " + followers.get(str));

        }

        System.out.println("\n");

        System.out.println("-Tutti gli utenti della rete hanno i seguenti following:");

        try {
            following = sc.getFollowing();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (String str : following.keySet()) {
            System.out.println("   i following di " + str + " sono: " + following.get(str));
        }

        System.out.println("\n");
        System.out.println("-Provo il metodo influencers");

        try {
            Influencers = sc.influencers(followers);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("   Gli utenti con followers >" + sc.GetSoglia() +" && followers > following sono  :" + Influencers);

        List<Post> PostUtente1 = null;
        List<Post> PostUtente2 = null;

        System.out.println("\n");
        System.out.println("-Metodo writtenby che mi restituisce tutti i post scritti da utente1:");

        try {
            PostUtente1 = sc.writtenBy("utente1");
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Post Ipost : PostUtente1) {
            System.out.println("   " + Ipost.toString());
        }

        System.out.println(
                "-Metodo writtenby con i post di utente 1 che mi restituisce tutti i post scritti da utente2:");

        try {
            PostUtente2 = sc.writtenBy(PostUtente1, "utente2");
        } catch (Exception e) {
            System.out.println(e);
        }

        if (PostUtente2.isEmpty())
            System.out.println("   []");
        else {
            for (Post Ipost : PostUtente2) {
                System.out.println("   " + Ipost.toString());
            }
        }

        System.out.println("\n");
        
        System.out.println("-Provo metodo contains(prova,Enea,conferma) ");

        try {
            PostWithWords = sc.containing(WordsContains);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (PostWithWords.isEmpty())
            System.out.println("   []");
        else {
            for (Post Ipost : PostWithWords) {
                System.out.println("   " + Ipost.toString());
            }
        }

    }

    public static void Test2(SocialNetwork sc) {

      
        List<Post> AllPosts = sc.GetallPosts();

        System.out.println("\n");
        

        System.out.println("-Metodo writtenby con utente non esistente");
        try {
            sc.writtenBy(AllPosts, "utente38");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("-Metodo writtenby con valori null");
        try {
           sc.writtenBy(null, null);
        } catch (Exception e) {
            System.out.println(e);
        }
     
        System.out.println("-Metodo Like con id di post che non esiste");

        try {
            sc.Like(280, "utente1");
        } catch (Exception e) {
            System.out.println(e);
        }

         System.out.println("-GuessFollowers con null");
         try {
           SocialNetwork.guessFollowers(null);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Test3() {

        CheckSocialNetwork sc2 = new CheckSocialNetwork();
        Post Post1 = null;
        Post Post2 = null;
        Post Post3 = null;
        System.out.println("-Creo 6 utenti e 3 post");

        // creo automaticamente 6 utenti con username del tipo utentei
        // con i che va da 1 a 16
        for (int i = 1; i <= 6; i++) {
            try {
                sc2.createUser("utente" + i);
            } catch (Exception e) {
                System.out.println(" " + e);
            }
        }

        try {
            Post1 = sc2.CreateNewPost("utente1", "Sono molto molto arrabbiato.");
        } catch (Exception e) {
            System.out.println(" " + e);
        }

        try {
            Post2 = sc2.CreateNewPost("utente1", "Buona la prima.");
        } catch (Exception e) {
            System.out.println(" " + e);
        }

        try {
            Post3 = sc2.CreateNewPost("utente3", "Brutte parole");
        } catch (Exception e) {
            System.out.println(" " + e);
        }

     
        System.out.println(" I post sono : \n" + sc2.GetallPosts());

        //Report di post

        System.out.println("-Utente2 report post 'Sono molto arrabbiato' ");
        try{
            sc2.ReportPost("utente2", Post1);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }
        System.out.println("-Utente3 report post 'Sono molto arrabbiato' ");
        try{
            sc2.ReportPost("utente3", Post1);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }
        System.out.println("-Utente5 report post 'Sono molto arrabbiato' ");
        try{
            sc2.ReportPost("utente5", Post1);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }
        System.out.println("-Utente6 report post 'Sono molto arrabbiato' ");
        try{
            sc2.ReportPost("utente6", Post1);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }

        System.out.println("-Utente non registrato report post 'Sono molto arrabbiato' ");
        try{
            sc2.ReportPost("utente34", Post1);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }

        System.out.println("-Utente6 report post 'Brutte parole' ");
        try{
            sc2.ReportPost("utente6", Post3);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }

        System.out.println("-Utente1 report post 'Brutte parole' ");
        try{
            sc2.ReportPost("utente1", Post3);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }

        System.out.println("-Report con valori null ");
        try{
            sc2.ReportPost(null, Post2);
        }
        catch(Exception e){
            System.out.println(" " + e);
        }

        System.out.println("\n-Tutti i post segnalati con gli utenti che li hanno segnalati sono:");

        Map<Post,Set<String>> Reported= sc2.GetReportedPost();

        System.out.println(Reported);

        System.out.println("\n-Rimuovo il post 'Sono molto molto arrabbiato.' dai post segnalati");

        try{
        sc2.RemoveReportedPost(Post1);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("\n-Tutti i post segnalati con gli utenti che li hanno segnalati sono:");

       Reported= sc2.GetReportedPost();

        System.out.println(Reported);


        System.out.print("-Provo a segnalare un post non registrato nella rete \n");
        Post post4 = new Post(250,"ciao","utente1");

        try{
        sc2.ReportPost("Utente1", post4);
        }
        catch(Exception e){
            System.out.println(e);
        }
        





    }

}