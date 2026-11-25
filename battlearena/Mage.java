/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public String getCharacterInfo() {
        return "Mage    " + getName() + " | Lvl: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Mana: " + mana + " | Power: " + calculatePower() + (isAlive() ? "" : " [DEFEATED]");
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
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack.");
            return 0;
        }
        int power = calculatePower(); // calculated BEFORE mana is spent
        mana -= 20;
        return target.takeDamage(power / 3);
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot heal.");
            return 0;
        }
        if (!target.isAlive()) {
            System.out.println(getName() + " cannot heal " + target.getName() + " (already defeated).");
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal.");
            return 0;
        }
        mana -= 15;
        return target.restoreHealth(getLevel() * 2);
    }
}

