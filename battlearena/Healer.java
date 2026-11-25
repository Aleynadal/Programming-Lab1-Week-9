/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;
public class Healer extends GameCharacter implements Supporter  {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public String getCharacterInfo() {
        return "Healer  " + getName() + " | Lvl: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | HealAmount: " + healAmount + " | Power: " + calculatePower() + (isAlive() ? "" : " [DEFEATED]");
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
        return target.restoreHealth(healAmount + getLevel());
    }
}




