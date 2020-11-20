package werewolfgame;

import java.util.Random;

public class GamePlay {

    public final int[] rolePlayer;
    public final int[] votePlayer;
    public int[] diePlayer;
    public int countDead;
    public boolean gameStatus = true;
    public final int maxPlayer = 5;
    private int dayCount = 1;

    Random random = new Random();
    RoleAction action = new RoleAction();

    public GamePlay() {
        rolePlayer = new int[maxPlayer];
        votePlayer = new int[maxPlayer];
        diePlayer = new int[maxPlayer];
    }

    public void randomRole(){
        int [] shuffleArray = new int[maxPlayer];
        for (int i = 1; i <= maxPlayer; i++) {
            shuffleArray[i-1] = i;
        }
        for (int i = 0; i < maxPlayer; i++) {
            int j = random.nextInt(maxPlayer);
            int tmp = shuffleArray[i];
            shuffleArray[i] = shuffleArray[j];
            shuffleArray[j] = tmp;
        }
        for (int i = 0; i < rolePlayer.length; i++) {
            rolePlayer[i] = shuffleArray[i];
        }
    }

    public void day(){
        System.out.printf("---Day %d---\n",dayCount);
        action.vote();
    }
    public void night(){
        System.out.printf("---Night %d---\n",dayCount);
        switch (rolePlayer[0]){
            case 1:
            case 2:
            case 3:
                System.out.println("Sleeping...");
                break;
            case 4:
                action.seer();
                break;
            case 5:
                //if (seerCheck!=true){
                    action.werewolf();
                    break;
                //}
        }
        if (rolePlayer[0]!=5){
            action.kill(random.nextInt(maxPlayer));
        }
        //System.out.println(deadCount);
        dayCount++;
    }

    public boolean deadCheck(){
        if(countDead>=maxPlayer){
            if (rolePlayer[0]!=5){
                gameStatus = false;
                System.out.println("You Lose... Werewolf kill all");
            }
        }
        return gameStatus = true;
    }


}
