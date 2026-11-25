/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;


public class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = armor;
    }

    @Override
    public int takeDamage(int damage) {
        if (!isAlive()) return 0;
        int reduced = Math.max(1, damage - armor / 2); // at least 1 damage
        return super.takeDamage(reduced);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public String getCharacterInfo() {
        return "Warrior " + getName() + " | Lvl: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Armor: " + armor + " | Power: " + calculatePower() + (isAlive() ? "" : " [DEFEATED]");
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot attack.");
            return 0;
        }
        if (!target.isAlive()) {
            System.out.println(getName() + " cannot attack " + target.getName() + " (already defeated).");
            return 0;
        }
        int damage = calculatePower() / 4;
        return target.takeDamage(damage);
    }
}