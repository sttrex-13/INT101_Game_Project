package system.roleplay;

import system.player.RoleRandom;

import java.util.Random;
import java.util.Scanner;

public class RoleSystem {

    Scanner sc = new Scanner(System.in);
    RoleRandom system = new RoleRandom();
    //RolePlay systemplay = new RolePlay();
    Random random = new Random();

    private final int[] votePlayer;
    private final int[] deadPlayer;
    private final int[] playerRole;
    private Role role;
    private RoleRandom rolePlayer;

    private int updateMaxPlayer = system.maxPlayer;

    public RoleSystem() {
        votePlayer = new int[system.maxPlayer];
        playerRole = new int[system.maxPlayer];
        deadPlayer = new int[system.maxPlayer];

        deadPlayer[3] = 3;
//        deadPlayer[4] = 4;
    }

    public void villager(){
        System.out.println("Not thing to do...");
        System.out.println("Sleeping...");
    }
    public void seer(){
        int choose;
        do {
            System.out.printf("choose one player[1-4] to see role : ");
            choose = getNumber();
        }while (choose<=0||choose>=system.maxPlayer);
        seerCheckRole(choose);
        System.out.printf("Player %d is "+role+"\n",choose);
        if (role==Role.Werewolf){
            System.out.println("You find Werewof!!!");
            System.out.println("End Game...[You Win!!!]");
        }

    }
    public void werewolf(){
        int choose;
        do {
            System.out.printf("Kill one player[1-4] : ");
            choose = getNumber();
        }while (choose<=0||choose>=system.maxPlayer);
        kill(choose);
        if (updateMaxPlayer<3){
            System.out.println("You kill all! [You Win!!!]");
        }
    }

    public Role vote(){//roleplayer[] ไม่มาจากอีกclass
        do {
            System.out.print("Vote one player[1-4] : ");
            votePlayer[0] = getNumber();
            while (votePlayer[0]==deadPlayer[votePlayer[0]]){
                System.out.printf("Player%d is dead vote again : ",deadPlayer[votePlayer[0]]);
                votePlayer[0] = getNumber();
            }
        }while (votePlayer[0]<=0||votePlayer[0]>=system.maxPlayer);

        for (int i=1;i<system.maxPlayer;i++){
            do {
                if (system.rolePlayer[i]==deadPlayer[i]){
                    votePlayer[i] = random.nextInt(system.maxPlayer);
                    while (votePlayer[i]==deadPlayer[votePlayer[i]]){
                        votePlayer[i] = random.nextInt(system.maxPlayer);
                    }
                }
                else {
                    votePlayer[i]=random.nextInt(system.maxPlayer) + 10;
                }
            }while (votePlayer[i]==i);
        }
        checkVote();
        return null;

    }
    private Role checkVote(){
        int counter = 0,deadman = -1;
        for (int i = 1; i < system.maxPlayer; i++) { //แสดงรายชื่อคนโหวต
            counter = 0;
            for (int j = 0; j < updateMaxPlayer; j++) {
                if (votePlayer[i] == votePlayer[j]) {
                    counter++;
                    //if (counter>=updateMaxPlayer){
                        deadman = votePlayer[i];
                    //}
                }
            }
            if (system.rolePlayer[i]==deadPlayer[i]){
                System.out.println("Player"+i+" Vote:" + votePlayer[i]+" "+counter);
            }
        }
        deadman = 2;
        System.out.println(playerRole[deadman]);
        switch (deadman){
            case 0:
                System.out.println("You die...[You Lose~]");
                //systemplay.isDead = true;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println(system.getRolePlayer(deadman));
//                if (deadman == 2){
//                    System.out.println("We kill Werewof!!!");
//                    System.out.println("End Game...[You Win!!!]");
//                }
//                else if (system.rolePlayer[deadman]==4){
//                    System.out.println("test seer");
//                }


                break;
            default:
                System.out.println("No one die...");
                break;
        }
    return null;
    }
    private void kill(int choosen){
        //if (systemplay.isDead==false){
            switch (choosen){
                case 0:
                    System.out.println("Werewolf is kill you...[You Lose~]");
                    //systemplay.isDead = false;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    if (system.rolePlayer[choosen]==5){

                    }

                default:
                    System.out.println("Werewolf didn't kill anyone...");
                    break;
            }
        //}
    }
    private Role seerCheckRole(int choose){
        switch (choose){
            case 1:
            case 2:
            case 3:
                return role = Role.Villager;
            case 4:
                return role = Role.Seer;
            case 5:
                return role = Role.Werewolf;
            default:
                return role;
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
        return "RoleSystem{}";
    }
}
