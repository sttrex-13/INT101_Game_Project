package system.roleplay;

import system.player.Player;

import java.util.Scanner;

public class RolePlay {

    private boolean isDead = false;
    private int dayCount;

    RoleRandom roleRandom = new RoleRandom();
    RoleSystem system = new RoleSystem();
    Scanner sc = new Scanner(System.in);

    public RolePlay() {

        System.out.print("Enter your name : ");
        Player player = new Player(sc.nextLine(),roleRandom.randomRole());
        System.out.println(player);
        day();

        //System.out.println(roleRandom.toString());
    }
    private boolean day(){
        system.vote();
        return isDead;
    }
    private boolean night(){
        dayCount++;
        return isDead;
    }

}
