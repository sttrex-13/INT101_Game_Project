package system.player;

import system.roleplay.Role;

public class Player {
    private String name;
    private Role role;

    public Player(String name,int roleNumber) {
        this.name = name;
        setRole(roleNumber);
    }
    public String getName(){
        return this.name;
    }
    private Role setRole(int number){
        switch (number){
            case 1:
            case 2:
            case 3:
                return this.role = Role.Villager;
            case 4:
                return this.role = Role.Seer;
            case 5:
                return this.role = Role.Werewolf;
            default:
                return this.role = Role.die;
        }
    }
    public Role getRole(){
        return this.role;
    }

    @Override
    public String toString() {
        return "Your name : " + getName()
                + "\n" + "Your Role : " + role;
    }
}
