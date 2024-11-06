import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solver {
    private final Scanner scanner;
    private HashMap<Integer,Adventurer> adventurerHashMap = new HashMap<>();

    public Solver(Scanner scanner) {
        this.scanner = scanner;
    }

    public void solve() {
        int t = scanner.nextInt();
        while (t-- != 0) {
            int op = scanner.nextInt();
            if (op == 1) {
                f1();
            } else if (op == 2) {
                f2();
            } else if (op == 3) {
                f3();
            } else if (op == 4) {
                f4();
            } else if (op == 5) {
                f5();
            } else if (op == 6) {
                f6();
            } else if (op == 7) {
                f7();
            } else if (op == 8) {
                f8();
            } else if (op == 9) {
                f9();
            } else if (op == 10) {
                f10();
            } else if (op == 11) {
                f11();
            } else if (op == 12) {
                f12();
            }
        }
    }

    private void f1() {
        int adv = scanner.nextInt();
        String name1 = scanner.next();
        Adventurer adventurer = new Adventurer(adv, name1);
        adventurerHashMap.put(adv, adventurer);
    }

    private void f2() {
        int adv = scanner.nextInt();
        int botId2 = scanner.nextInt();
        String name2 = scanner.next();
        int cap2 = scanner.nextInt();
        String type2 = scanner.next();
        int ce2 = scanner.nextInt();
        adventurerHashMap.get(adv).AddBottle(botId2, name2, cap2, type2, ce2);
    }

    private void f3() {
        int adv = scanner.nextInt();
        int equId2 = scanner.nextInt();
        String name2 = scanner.next();
        int dur2 = scanner.nextInt();
        String type2 = scanner.next();
        int ce2 = scanner.nextInt();
        adventurerHashMap.get(adv).AddEquipment(equId2, name2, dur2, type2, ce2);
    }

    private void f4() {
        int adv = scanner.nextInt();
        int equ4 = scanner.nextInt();
        adventurerHashMap.get(adv).AddDurability(equ4);
    }

    private void f5() {
        int adv = scanner.nextInt();
        int id5 = scanner.nextInt();
        adventurerHashMap.get(adv).removeId(id5);
    }

    private void f6() {
        int adv = scanner.nextInt();
        int id6 = scanner.nextInt();
        adventurerHashMap.get(adv).carryId(id6);
    }

    private void f7() {
        int adv = scanner.nextInt();
        int botId7 = scanner.nextInt();
        adventurerHashMap.get(adv).useBottle(botId7);
    }

    private void f8() {
        int adv = scanner.nextInt();
        int id = scanner.nextInt();
        String name = scanner.next();
        adventurerHashMap.get(adv).AddFragment(id, name);
    }

    private void f9() {
        int adv = scanner.nextInt();
        String name = scanner.next();
        int id = scanner.nextInt();
        adventurerHashMap.get(adv).exchange(name, id);
    }

    private void f10() {
        int adv = scanner.nextInt();
        String name = scanner.next();
        String type = scanner.next();
        int k = scanner.nextInt();
        Adventurer a = adventurerHashMap.get(adv);
        int gong = a.getBackpack().getCe(name) + a.getAtk();
        if (type.equals("normal")) {
            int[] q = new int[10];
            for (int i = 0; i < k; i++) {
                q[i] = scanner.nextInt();
                Adventurer a1 = adventurerHashMap.get(q[i]);
                a1.preAttack();
                a1.setPpa(a1.getHitPoint());
            }
            int max = 0;
            for (int i = 0; i < k; i++) {
                if (adventurerHashMap.get(q[i]).getDef() > max) {
                    max = adventurerHashMap.get(q[i]).getDef();
                }
            }
            String str = a.str(name);
            if (gong > max) { //进攻成功
                a.useEquipment(a.getBackpack().getEid(name));
                for (int i = 0; i < k; i++) {
                    Adventurer a1 = adventurerHashMap.get(q[i]);
                    System.out.print(a1.getName() + " ");
                    int qq = 0;

                    if (str.equals("Axe")) {
                        qq = a1.getHitPoint() / 10;
                        a1.setHitPoint(qq);
                    } else if (str.equals("Sword")) {
                        int temp = a1.getHitPoint() - a.getBackpack().getCe(name);
                        qq = temp - a.getAtk() + a1.getDef();
                        a1.setHitPoint(qq);
                    } else if (str.equals("Blade")) {
                        qq = a1.getHitPoint() - a.getBackpack().getCe(name) - a.getAtk();
                        a1.setHitPoint(qq);
                    }
                    System.out.println(qq);
                }
                for (int i = 0; i < k; i++) {
                    Adventurer a1 = adventurerHashMap.get(q[i]);
                    if (a1.getHitPoint() <= a1.getPpa() / 2) {
                        a1.yuanzhu();
                    }
                }
            } else {
                System.out.println("Adventurer " + adv + " defeated");
            }
        } else if (type.equals("chain")) {
            HashSet<Adventurer> bb = new HashSet<>();
            for (int i = 0; i < k; i++) {
                int advId = scanner.nextInt();
                Adventurer b = adventurerHashMap.get(advId);
                bb.add(b);
                for (int j = 0; j < 5; j++) {
                    ArrayList<Adventurer> bbb = b.getEmployees();
                    for (int l = 0; l < bbb.size(); l++) {
                        bb.add(bbb.get(l));
                    }
                }
            }
            int max = 0;
            for (Adventurer adventurer : bb) {
                if (adventurer.getDef() > max) {
                    max = adventurer.getDef();
                }
            }
            String str = a.str(name);
            if (gong > max) { //进攻成功
                int sum = 0;
                a.useEquipment(a.getBackpack().getEid(name));
                for (Adventurer a1 : bb) {
                    int qq = 0;
                    if (str.equals("Axe")) {
                        qq = a1.getHitPoint() / 10;
                        sum += a1.getHitPoint() - qq;
                        a1.setHitPoint(qq);
                    } else if (str.equals("Sword")) {
                        int temp = a1.getHitPoint() - a.getBackpack().getCe(name);
                        qq = temp - a.getAtk() + a1.getDef();
                        sum += a1.getHitPoint() - qq;
                        a1.setHitPoint(qq);
                    } else if (str.equals("Blade")) {
                        qq = a1.getHitPoint() - a.getBackpack().getCe(name) - a.getAtk();
                        sum += a1.getHitPoint() - qq;
                        a1.setHitPoint(qq);
                    }
                }
                System.out.println(sum);
            } else {
                System.out.println("Adventurer " + adv + " defeated");
            }
        }
    }

    private void f11() {
        int adv1 = scanner.nextInt();
        int adv2 = scanner.nextInt();
        adventurerHashMap.get(adv1).hire(adventurerHashMap.get(adv2));
    }

    private void f12() {
        int adv = scanner.nextInt();
        adventurerHashMap.get(adv).maoxian();
    }
}
