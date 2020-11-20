package werewolfgame;

import java.util.Random;
import java.util.Scanner;

public class RoleAction {

    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    GamePlay gamePlay = new GamePlay();

    public roleList role;
    public boolean gameStatus = true;

    public int countDead;
    private int dayCount = 1;
    private int deadVillager = -1;
    private int survivalVillager = gamePlay.maxPlayer;

    public RoleAction() {

    }

    public void seer(){
        int choose;

        do {
            System.out.println("Choose one player[1,2,3,4] to see role : ");
            choose = getNumber();
        }while (choose<=0||choose>=gamePlay.maxPlayer);

        System.out.printf("Player %d is " + checkRole(choose) +"\n",choose);

        if (checkRole(choose)== roleList.Werewolf){
            System.out.println("You find werewolf!!!");
            System.out.println("[You Win...]");
            gameStatus = false;
        }

    }
    public roleList checkRole(int chosenOne){
        switch (gamePlay.rolePlayer[chosenOne]){
            case 1:
            case 2:
            case 3:
                return role = roleList.Villager;
            case 4:
                return role = roleList.Seer;
            case 5:
                return role = roleList.Werewolf;
            default:
                return role = roleList.Die;
        }
    }

    public void werewolf(){
        int choose;

        do {
            System.out.println("Choose one player[1,2,3,4] to kill : ");
            choose = getNumber();
        }while (choose<=0||choose>=gamePlay.maxPlayer);

        kill(choose);

        if (countDead>=gamePlay.maxPlayer/2){
            System.out.println("You kill all! [You Win!!!]");
            gameStatus=false;
        }

    }
    public void kill(int chosenOne){
        if (gameStatus==true){
            switch (chosenOne){
                case 0:
                    System.out.println("Werewolf did kill you...[You Lose~]");
                    gameStatus = false;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    if (checkRole(chosenOne)== roleList.Werewolf){
                        System.out.println("Werewolf wasn't kill anyone...");
                        break;
                    }
                        else if (checkRole(chosenOne)== roleList.Seer){
                            System.out.println("Player " + chosenOne + " die...");
                            System.out.println("Player " + chosenOne + " is : " + "Seer");
                            gamePlay.diePlayer[chosenOne]=chosenOne;
                            countDead++;
                            survivalVillager--;
                            break;
                        }
                        else if (checkRole(chosenOne)== roleList.Villager){
                            System.out.println("Player " + chosenOne + "die...");
                            System.out.println("Player " + chosenOne + "is : " + "Villager");
                            gamePlay.diePlayer[chosenOne]=chosenOne;
                            countDead++;
                            survivalVillager--;
                            break;
                        }

            }
        }
    }

    public void villager(){
        System.out.println("Sleeping...");
    }

    public void vote(){
        do {
            System.out.printf("Vote one player[1,2,3,4] : ");
            gamePlay.votePlayer[0] = getNumber();
            int i=0;
            while (gamePlay.votePlayer[0]==gamePlay.diePlayer[i]){
                gamePlay.votePlayer[0] = getNumber();
                i++;
            }
        }while (gamePlay.votePlayer[0]>=gamePlay.maxPlayer);

        for (int i=1;i<gamePlay.rolePlayer.length;i++){
            do {
                gamePlay.votePlayer[i] = random.nextInt(gamePlay.maxPlayer);
            }while (gamePlay.votePlayer[i]==gamePlay.diePlayer[i]);

            if (gamePlay.rolePlayer[i]==gamePlay.diePlayer[i]){
                gamePlay.votePlayer[i]=-1;
            }
        }
        checkVote();


    }
    public void checkVote(){
        int counter = 0, temp = 0 ;
        for (int i = 1; i < gamePlay.rolePlayer.length; i++) {
            temp = gamePlay.votePlayer[i];
            counter = 0;
            for (int j = 0; j < survivalVillager; j++) {
                if (temp == gamePlay.votePlayer[j]) {
                    counter++;
                    if (gamePlay.maxPlayer == 4) {
                        if (counter == 4) {
                            System.out.println("No one die");
                        }
                        if (counter >= survivalVillager / 2) {//จำนวนคนตายหารสอง
                            deadVillager = gamePlay.votePlayer[i];
                        }
                    } else if (gamePlay.maxPlayer > 4) {
                        if (counter >= survivalVillager / 2) {
                            deadVillager = gamePlay.votePlayer[i];
                        }
                    }
                }
            }
            if (gamePlay.rolePlayer[i] != -1) {
                System.out.println("Player" + i + " Vote:" + gamePlay.votePlayer[i] + " " + counter);
            }
        }
            //////////
            switch (deadVillager){
                case 0:
                    System.out.println("You die...[You Lose~]");
                    gameStatus = false;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    if (role== roleList.Werewolf){
                        System.out.println("We kill Werewof!!!");
                        System.out.println("End Game...[You Win!!!]");
                        gameStatus = false;
                    }
                    else if (role== roleList.Seer){
                        kill(deadVillager);
                    }
                    else if (role== roleList.Villager){
                        kill(deadVillager);
                    }
                    break;
                default:
                    System.out.println("No one die...");
                    break;
            }
        }

    public int getNumber() {
        int getNumber = 0;
        System.out.print("Please Enter an integer : ");
        while (!sc.hasNextInt()) {
            System.out.print("Please Enter an integer : ");
            sc.next();
        }
        getNumber = sc.nextInt();
        return getNumber;
    }
}
