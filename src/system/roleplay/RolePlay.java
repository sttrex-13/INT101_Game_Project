package system.roleplay;

import system.player.Player;
import system.player.RoleRandom;

import java.util.Random;
import java.util.Scanner;

public class RolePlay {

    public boolean isDead = false;
    private int dayCount = 1;

    RoleRandom roleRandom = new RoleRandom();
    RoleSystem system = new RoleSystem();
    Random random = new Random();
    Scanner sc = new Scanner(System.in);

    public RolePlay() {
        System.out.print("Enter your name : ");
        Player player = new Player(sc.nextLine(),);
        System.out.println(player);
        day();

        System.out.println(roleRandom.toString());
    }
    private boolean day(){
        System.out.printf("---Day %d---\n",dayCount);
        system.vote();
        return isDead;
    }
    private boolean night(){


        return isDead;
    }


}
