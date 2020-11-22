package system.roleplay;

import java.util.Random;
import java.util.Scanner;

public class RoleSystem {

    Scanner sc = new Scanner(System.in);
    RoleRandom system = new RoleRandom();
    Random random = new Random();

    private final int[] votePlayer;
    private final int[] deadPlayer;

    private int updateMaxPlayer = system.maxPlayer;

    public RoleSystem() {
        votePlayer = new int[system.maxPlayer];
        deadPlayer = new int[system.maxPlayer];
        deadPlayer[3] = 3;
        deadPlayer[4] = 4;
    }

    public void villager(){
        System.out.println("Not thing to do...");
        System.out.println("Sleeping...");
    }
    public void seer(){

    }
    public void werewolf(){

    }

    public void vote(){
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
                    //votePlayer[i]=;
                }
            }while (votePlayer[i]==i);
        }
        checkVote();

    }
    private int checkVote(){
        int counter = 0, temp = 0 ;
        for (int i = 1; i < system.maxPlayer; i++) { //แสดงรายชื่อคนโหวต
            temp = votePlayer[i];
            counter = 0;
            for (int j = 0; j < updateMaxPlayer; j++) {
                if (temp == votePlayer[j]) {
                    counter++;
                }
            }
            //if (system.rolePlayer[i]==deadPlayer[i]){
                System.out.println("Player"+i+" Vote:" + votePlayer[i]+" "+counter);
            //}
        }
    return 0;
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
