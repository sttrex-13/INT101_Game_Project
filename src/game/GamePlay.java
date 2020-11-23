package game;

import player.Player;
import role.RoleCard;
import role.RoleList;

import java.util.Random;
import java.util.Scanner;

public class GamePlay {

    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    RoleCard card = new RoleCard();

    private boolean dead = false;
    private int dayCount = 1;
    private int updatePlayer = 5;


    public int[] voteCount = new int[5];
    Player player0 = new Player();
    Player player1 = new Player("Zin"); //ใส่ชื่อ Player1
    Player player2 = new Player("Beam"); //ใส่ชื่อ Player2
    Player player3 = new Player("Dose"); //ใส่ชื่อ Player3
    Player player4 = new Player("Job"); //ใส่ชื่อ Player4

    public GamePlay() {

        card.randomCard(); //จั่วไพ่

        player0.setCard(card.getRole(0));
        player1.setCard(card.getRole(1));
        player2.setCard(card.getRole(2));
        player3.setCard(card.getRole(3));
        player4.setCard(card.getRole(4));


        while (dieCheck(0)==false){
            day();
            if (dieCheck(0)==false){
                night();
            }
        }

    }

    private void villager(){
        System.out.println("No action...");
        System.out.println("Sleeping...");
    }
    private boolean seer(){
        int choose;
        do {
            do{
            System.out.print("Choose one player[1-4] to see role : ");
            choose = getNumber();
            }while (dieCheck(choose)); //checkไม่ให้โหวตคนตาย
        }while (choose<=0||choose>=5); //checkว่าโหวตนอกเหนือ1-4

        switch (choose){ //check บทบาท
            case 1:
                System.out.println(player1.getName() + " is " + player1.getCard());
                break;
            case 2:
                System.out.println(player2.getName() + " is " + player2.getCard());
                break;
            case 3:
                System.out.println(player3.getName() + " is " + player3.getCard());
                break;
            case 4:
                System.out.println(player4.getName() + " is " + player4.getCard());
                break;
        }
        iFindWerewolf(choose);//หาwerewolf
        return dead = false;
    }
    private void werewolf(){
        int choose = 0;
        do {
            do{
            System.out.print("Choose one player[1-4] to kill : ");
            choose = getNumber();
            }while (dieCheck(choose));
        }while (choose<=0||choose>=5);
        kill(choose);
        if (updatePlayer<=2){
            System.out.println("You kill all !!! [You win!!!]");
            player0.setDead();
        }

    }

    private void kill(int choosen){
//        do {
//            choosen = random.nextInt(5);
//        }while (dieCheck(choosen));
        
        switch (choosen){
            case 0:
                if (player0.getCard() == RoleList.Werewolf) {
                    System.out.println("You die... : Vote out [You lose]");
                    player0.setDead();
                }
                else {
                    System.out.println("You die... : Werewolf kill [You lose]");
                    player0.setDead();
                }
                break;
            case 1:
                if (player1.getCard()==RoleList.Werewolf){
                    System.out.println("Werewolf doesn't kill anyone...");
                }
                else {
                    player1.setDead();
                    System.out.println("Player1 : "+player1.getName()+" is Dead");
                    updatePlayer--;
                }
                break;
            case 2:
                if (player2.getCard()==RoleList.Werewolf){
                    System.out.println("Werewolf doesn't kill anyone...");
                }
                else {
                    player2.setDead();
                    System.out.println("Player2 : "+player2.getName()+" is Dead");
                    updatePlayer--;
                }
                break;
            case 3:
                if (player3.getCard()==RoleList.Werewolf){
                    System.out.println("Werewolf doesn't kill anyone...");
                }
                else {
                    player3.setDead();
                    System.out.println("Player3 : "+player3.getName()+" is Dead");
                    updatePlayer--;
                }
                break;
            case 4:
                if (player4.getCard()==RoleList.Werewolf){
                    System.out.println("Werewolf doesn't kill anyone...");
                }
                else {
                    player4.setDead();
                    System.out.println("Player4 : "+player4.getName()+" is Dead");
                    updatePlayer--;
                }
                break;
            default:
                break;
        }
    }
    private void vote(){
        //int choose = 0;
        do {
            do{
                System.out.print("Choose one player[1-4] to Vote : ");
                voteCount[0] = getNumber();
            }while (dieCheck(voteCount[0]));
        }while (voteCount[0]<=0||voteCount[0]>=5);
        ramdomVote();
        whoDead();
    }

    private void ramdomVote(){
        for (int i=1;i<5;i++){
            do {
                do {
                    voteCount[i] = random.nextInt(5);
                }while (i==voteCount[i]);
            }while (dieCheck(voteCount[i]));
            if (dieCheck(i)){
                voteCount[i] = random.nextInt(5) + 10;
            }
            if (dieCheck(i)==false){
                System.out.printf("Player%d vote : %d\n",i,voteCount[i]);
            }
        }
        //System.out.println(Arrays.toString(voteCount));
    }
    private int whoDead(){
        int counter = 0;
        int max = 0;

        int index_max = 0; //ตัวเก็บว่าใครจะตาย

        int i,j;
        for(i=0;i<voteCount.length;i++)
        {
            counter = 0;
            for(j=0;j<voteCount.length;j++)
            {
                //check array ที่ซ้ำกัน
                if(voteCount[i] == voteCount[j])
                    counter++; //นับว่าช่องนั้นซ้ำกี่รอบ
            }
            //update ว่าจำนวนvoteไหนเยอะสุด
            if(counter > max)
            {
                max = counter;
                index_max = i;
            }
        }
        if (max>updatePlayer/2){
            switch (voteCount[index_max]){
                case 0:
                    System.out.println("You die... : Vote out [You lose~]");
                    player0.setDead();
                    break;
                case 1:
                    kill(1);
                    iFindWerewolf(1);
                    break;
                case 2:
                    kill(2);
                    iFindWerewolf(2);
                    break;
                case 3:
                    kill(3);
                    iFindWerewolf(3);
                    break;
                case 4:
                    kill(4);
                    iFindWerewolf(4);
                    break;
            }
            return voteCount[index_max];
        }
        else {
            System.out.println("No one die...");
            return -1;
        }

    }

    private boolean dieCheck(int number){
        switch (number){
            case 0:
                return player0.isDead();
            case 1:
                return player1.isDead();
            case 2:
                return player2.isDead();
            case 3:
                return player3.isDead();
            case 4:
                return player4.isDead();
            default:
                return true;
        }
    }
    private boolean iFindWerewolf(int number){
        switch (number){
            case 1:
                if (player1.getCard()== RoleList.Werewolf){
                    System.out.println("You find Werewolf!!! [You win]");
                    player0.setDead();

                }
                return player0.isDead();
            case 2:
                if (player2.getCard()== RoleList.Werewolf){
                    System.out.println("You find Werewolf!!! [You win]");
                    player0.setDead();

                }
                return player0.isDead();
            case 3:
                if (player3.getCard()== RoleList.Werewolf){
                    System.out.println("You find Werewolf!!! [You win]");
                    player0.setDead();

                }
                return player0.isDead();
            case 4:
                if (player4.getCard()== RoleList.Werewolf){
                    System.out.println("You find Werewolf!!! [You win]");
                    player0.setDead();

                }
                return player0.isDead();
            default:
                return player0.isDead();
        }
    }

    private void day(){
        System.out.printf("-----Day%d-----\n",dayCount);
        if (dayCount==1){
            System.out.print("Enter your name : ");
            player0.setName(sc.nextLine());
            System.out.println(player0);
        }
        else {
            vote();
        }
    }
    private void night(){
        System.out.printf("-----Night%d-----\n",dayCount);
        if (dayCount==1){
            if (player0.getCard()==RoleList.Seer){
                seer();
            }
            else {
                System.out.println("Not thing to do..");
                System.out.println("Sleeping...");
            }
            dayCount++;
        }
        else {
            switch (card.getRole(0)){
                case 1:
                case 2:
                case 3:
                    villager();
                    break;
                case 4:
                    seer();
                    break;
                case 5:
                    werewolf();
                    break;
            }
            if (!player0.isDead()){
                if (player0.getCard()!=RoleList.Werewolf){
                    System.out.println("Werewolf Choose to kill...");
                    kill(random.nextInt(5));
                }
            }

            dayCount++;
        }


    }

    public int getNumber() {
        int getNumber = 0;
        while (!sc.hasNextInt()) {
            System.out.print("please enter an integer: ");
            sc.next();
        }
        getNumber = sc.nextInt();
        return getNumber;
    }

    @Override
    public String toString() {
        return "-------------------------The END-------------------------------------";
    }
}
