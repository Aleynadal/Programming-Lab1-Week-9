/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members = new ArrayList<>();

    public boolean addCharacter(GameCharacter character) {
        if (findByName(character.getName()) != null) {
            System.out.println("Cannot add: a character named '" + character.getName() + "' is already in the team.");
            return false;
        }
        members.add(character);
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter c = findByName(name);
        if (c == null) {
            System.out.println("Cannot remove: '" + name + "' not found.");
            return false;
        }
        members.remove(c);
        return true;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public ArrayList<GameCharacter> getMembers() {
        return members;
    }

    public int attackAll(GameCharacter target) {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                int dmg = ((Attacker) c).attack(target);
                System.out.println("  " + c.getName() + " attacks " + target.getName() + " -> " + dmg + " damage");
                total += dmg;
            }
        }
        return total;
    }

    public void healTeam() {
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter weakest = null;
                double lowest = Double.MAX_VALUE;
                for (GameCharacter m : members) {
                    if (m.isAlive()) {
                        double ratio = (double) m.getHealth() / m.getMaxHealth();
                        if (ratio < lowest) {
                            lowest = ratio;
                            weakest = m;
                        }
                    }
                }
                if (weakest != null) {
                    int restored = ((Supporter) c).heal(weakest);
                    System.out.println("  " + c.getName() + " heals " + weakest.getName() + " -> " + restored + " HP");
                }
            }
        }
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) total += c.calculatePower();
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("---- TEAM ----");
        for (GameCharacter c : members) {
            System.out.println(c.getCharacterInfo());
        }
        System.out.println("Total power (alive): " + getTotalPower());
        System.out.println("--------------");
    }
}

