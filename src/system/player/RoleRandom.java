package system.player;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RoleRandom {

    public final int[] rolePlayer;
    public int maxPlayer=5;

    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    public RoleRandom() {
        rolePlayer = new int[maxPlayer];
    }
    public int randomRole(){
        int [] shuffleArray = new int[5];
        for (int i = 1; i <= 5; i++) {
            shuffleArray[i-1] = i;
        }
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(5);
            int tmp = shuffleArray[i];
            shuffleArray[i] = shuffleArray[j];
            shuffleArray[j] = tmp;
        }
        for (int i = 0; i < rolePlayer.length; i++) {
            rolePlayer[i] = shuffleArray[i];
        }
        System.out.println(rolePlayer[2]);
        return rolePlayer[0];
    }
    public int getRolePlayer(int player){
        int role = rolePlayer[player];
        System.out.println(player);
        System.out.println(rolePlayer[1]);
        return role;
    }

    @Override
    public String toString() {
        return "RolePlay{" +
                "rolePlay=" + Arrays.toString(rolePlayer) +
                '}';
    }

}
