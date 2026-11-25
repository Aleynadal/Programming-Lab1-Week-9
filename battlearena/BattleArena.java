/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package battlearena;


public class BattleArena {
    public static void main(String[] args) {
        Team team = new Team();
        team.addCharacter(new Warrior("Arthur", 10, 200, 20));
        team.addCharacter(new Mage("Merlin", 12, 120, 100));
        team.addCharacter(new Healer("Luna", 8, 130, 25));

        GameCharacter golem = new Warrior("Golem", 20, 500, 30);

        System.out.println("=== Initial state ===");
        team.displayTeam();
        System.out.println(golem.getCharacterInfo());

        for (int round = 1; round <= 3; round++) {
            System.out.println("\n=== Round " + round + " ===");

            System.out.println("Team attacks Golem:");
            team.attackAll(golem);

            System.out.println("Golem attacks team members:");
            for (GameCharacter m : team.getMembers()) {
                if (m.isAlive() && golem.isAlive()) {
                    int dmg = ((Attacker) golem).attack(m);
                    System.out.println("  Golem attacks " + m.getName() + " -> " + dmg + " damage");
                }
            }

            System.out.println("Team heals itself:");
            team.healTeam();

            System.out.println(golem.getCharacterInfo());
            team.displayTeam();
        }

        System.out.println("\n=== Remove / Find ===");
        team.removeCharacter("Luna");
        GameCharacter found = team.findByName("Merlin");
        System.out.println("Found: " + (found != null ? found.getCharacterInfo() : "not found"));
        System.out.println("Find 'Ghost': " + (team.findByName("Ghost") != null ? "found" : "not found"));
        team.displayTeam();

        System.out.println("\n=== Invalid input tests ===");
        team.addCharacter(new Warrior("Arthur", 5, 100, 10));   // duplicate name
        team.addCharacter(new Mage("Newbie", 150, 100, 50));    // invalid level -> 1
        team.addCharacter(new Healer("Weak", 5, -10, 10));      // invalid maxHealth -> 100
        team.displayTeam();
    }
}
