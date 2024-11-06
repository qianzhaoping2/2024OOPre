import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Adventurer implements Commodity {
    private int id;
    private String name;
    private ArrayList<Bottle> bottles = new ArrayList<>();
    private ArrayList<Equipment> equipments = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Backpack backpack;
    private int hitPoint;
    private int atk;
    private int def;
    private int assistance;
    private int preHp;
    private int nowHp;
    private int ppa;
    private ArrayList<Adventurer> employees = new ArrayList<>();

    public void hire(Adventurer adventurer) {
        employees.add(adventurer);
        assistance = 0;
        preHp = adventurer.getHitPoint();
    }

    public void setPreHp(int n) {
        this.preHp = n;
    }

    public void setNowHp(int n) {
        this.nowHp = n;
    }

    public void preAttack() {
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).setPreHp(this.hitPoint);
        }
    }

    public int getPreHp() {
        return this.preHp;
    }

    public int getNowHp() {
        return this.nowHp;
    }

    public void setPpa(int ppa) {
        this.ppa = ppa;
    }

    public int getPpa() {
        return ppa;
    }

    public int getAssistance() {
        return assistance;
    }

    public void setAssistance(int assistance) {
        this.assistance = assistance;
    }

    public ArrayList<Adventurer> getEmployees() {
        return employees;
    }

    public void maoxian() {
        int all = atk + def;
        for (int i = 0; i < employees.size(); i++) {
            all += employees.get(i).CE(employees.get(i).getId());
        }
        all += backpack.sumatk();
        if (all > 1000) {
            System.out.println("Cloak of Shadows");
            this.def += 40;
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).addDef(40);
            }
        }
        all = atk + def;
        for (int i = 0; i < employees.size(); i++) {
            all += employees.get(i).CE(employees.get(i).getId());
        }
        all += backpack.sumatk();
        if (all > 2000) {
            System.out.println("Flamebrand Sword");
            this.atk += 40;
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).addAtk(40);
            }
        }
        all = atk + def;
        for (int i = 0; i < employees.size(); i++) {
            all += employees.get(i).CE(employees.get(i).getId());
        }
        all += backpack.sumatk();
        if (all > 3000) {
            System.out.println("Stoneheart Amulet");
            this.def += 40;
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).addDef(40);
            }
        }
        all = atk + def;
        for (int i = 0; i < employees.size(); i++) {
            all += employees.get(i).CE(employees.get(i).getId());
        }
        all += backpack.sumatk();
        if (all > 4000) {
            System.out.println("Windrunner Boots");
            this.def += 30;
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).addDef(30);
            }
        }
        this.maoxian2();
    }

    public void maoxian2() {
        int all = atk + def;
        for (int i = 0; i < employees.size(); i++) {
            all += employees.get(i).CE(employees.get(i).getId());
        }
        all += backpack.sumatk();
        if (all > 5000) {
            System.out.println("Frostbite Staff");
            this.atk += 50;
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).addAtk(50);
            }
        }
    }

    public void addAtk(int n) {
        this.atk += n;
    }

    public void addDef(int n) {
        this.def += n;
    }

    public void yuanzhu() {
        Iterator<Adventurer> iterator = employees.iterator();
        for (int i = 0; i < employees.size(); i++) {
            HashMap<Integer,Equipment> aa = employees.get(i).backpack.help();
            employees.get(i).backpack.deleteEquipments();
            for (int j = 0; j < aa.size(); j++) {
                Equipment a2 = aa.get(j);
                int equId2 = a2.getId();
                String name2 = a2.getName();
                int dur2 = a2.getDurability();
                String type2 = a2.getclass();
                int ce2 = a2.CE(a2.getId());
                this.AddEquipment(equId2, name2, dur2, type2, ce2);
                employees.get(i).removeId(a2.getId());
            }
            int q = employees.get(i).getAssistance() + 1;
            Adventurer a3 = iterator.next();
            if (q > 3) {
                employees.get(i).setAssistance(0);
                iterator.remove();
            } else {
                employees.get(i).setAssistance(q);
            }
        }

    }

    @Override
    public int CE(int id) {
        return atk + def;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Adventurer(int id,String name) {
        this.name = name;
        this.id = id;
        this.atk = 1;
        this.def = 0;
        this.hitPoint = 500;
        this.backpack = new Backpack();
    }

    public void AddBottle(int id, String name, int capacity, String type, int ce) {
        if (type.equals("HpBottle")) {
            Bottle bottle1 = new HpBottle(id, name, capacity,ce);
            bottles.add(bottle1);
        } else if (type.equals("AtkBottle")) {
            Bottle bottle1 = new AtkBottle(id, name, capacity,ce);
            bottles.add(bottle1);
        } else if (type.equals("DefBottle")) {
            Bottle bottle1 = new DefBottle(id, name, capacity, ce);
            bottles.add(bottle1);
        }
    }

    public void AddEquipment(int id, String name, int durability, String type, int ce) {
        if (type.equals("Axe")) {
            Equipment equipment1 = new Axe(id, name, durability, ce);
            equipments.add(equipment1);
        } else if (type.equals("Sword")) {
            Equipment equipment1 = new Sword(id, name, durability, ce);
            equipments.add(equipment1);
        } else if (type.equals("Blade")) {
            Equipment equipment1 = new Blade(id, name, durability, ce);
            equipments.add(equipment1);
        }
    }

    public void AddFragment(int id, String name) {
        Fragment fragment = new Fragment(id, name);
        fragments.add(fragment);
    }

    public void AddDurability(int id) {
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getId() == id) {
                equipments.get(i).add();
                System.out.print(equipments.get(i).getName());
                System.out.println(" " + equipments.get(i).getDurability());
                break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void carryId(int id) {
        for (int i = 0; i < bottles.size(); i++) {
            if (bottles.get(i).getId() == id) {
                backpack.carryBottle(bottles.get(i), this.maxBottle());
            }
        }
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getId() == id) {
                backpack.carryEquipment(equipments.get(i));
            }
        }
    }

    public void removeId(int id) {
        int aaa = -11;
        for (int i = 0; i < bottles.size(); i++) {
            if (bottles.get(i).getId() == id) {
                aaa = i;
                System.out.print(bottles.get(i).getclass());
                System.out.print(" ");
                System.out.print(bottles.get(i).getName());
                System.out.print(" ");
                System.out.println(bottles.get(i).getCapacity());
                break;
            }
        }
        if (aaa != -11) {
            backpack.removeBottle(bottles.get(aaa).getId());
            bottles.remove(aaa);
        }
        int bbb = -11;
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getId() == id) {
                bbb = i;
                System.out.print(equipments.get(i).getclass());
                System.out.print(" ");
                System.out.print(equipments.get(i).getName());
                System.out.print(" ");
                System.out.println(equipments.get(i).getDurability());
                break;
            }
        }
        if (bbb != -11) {
            backpack.removeEquipment(equipments.get(bbb).getId());
            equipments.remove(bbb);
        }
    }

    public void useBottle(int id) {
        //int aa = -11;
        for (int i = 0; i < bottles.size(); i++) {
            if (bottles.get(i).getId() == id) {
                if (backpack.hasBottle(id)) {
                    //aa = i;
                    if (bottles.get(i).getclass().equals("HpBottle")) {
                        this.hitPoint += bottles.get(i).getCapacity();
                    } else if (bottles.get(i).getclass().equals("AtkBottle")) {
                        this.atk += bottles.get(i).CE(id) + bottles.get(i).getCapacity() / 100;
                    } else if (bottles.get(i).getclass().equals("DefBottle")) {
                        this.def += bottles.get(i).CE(id) + bottles.get(i).getCapacity() / 100;
                    }
                    System.out.println(name + " " + hitPoint + " " + atk + " " + def);
                    bottles.get(i).setNow(0);
                } else {
                    System.out.println(name + " fail to use " + bottles.get(i).getName());
                }
            }
        }
        /*if (aa != -11)  {
            backpack.removeBottle(bottles.get(i).getId());
            bottles.remove(i);
        }*/
    }

    public int maxBottle() {
        return (atk + def) / 5 + 1;
    }

    public void exchange(String name, int id) {
        int num = 0;
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i).getName().equals(name)) {
                num++;
            }
        }
        if (num < 5) {
            System.out.println(num + ": Not enough fragments collected yet");
        } else {
            for (int j = 0; j < 5; j++) {
                int bb = -11;
                for (int i = 0; i < fragments.size(); i++) {
                    if (fragments.get(i).getName().equals(name)) {
                        bb = i;
                    }
                }
                if (bb != -11) {
                    fragments.remove(bb);
                }
            }
            int aa = 0;
            for (int i = 0; i < bottles.size(); i++) {
                if (bottles.get(i).getId() == id) {
                    aa = 1;
                    if (bottles.get(i).getNow() == 0) {
                        bottles.get(i).setNow(bottles.get(i).getCapacity());
                    }
                    System.out.print(bottles.get(i).getName());
                    System.out.print(" ");
                    System.out.println(bottles.get(i).getCapacity());
                    break;
                }
            }
            for (int i = 0; i < equipments.size(); i++) {
                if (equipments.get(i).getId() == id) {
                    aa = 2;
                    equipments.get(i).add();
                    System.out.print(equipments.get(i).getName());
                    System.out.print(" ");
                    System.out.println(equipments.get(i).getDurability());
                    break;
                }
            }
            if (aa == 0) {
                Bottle bottle1 = new HpBottle(id, name, 100, 0);
                bottles.add(bottle1);
                System.out.println("Congratulations! HpBottle " + name + " acquired");
            }
        }
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public void setHitPoint(int q) {
        this.hitPoint = q;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void useEquipment(int id) {
        int aa = -11;
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getId() == id) {
                equipments.get(i).use();
                if (equipments.get(i).getDurability() == 0) {
                    aa = i;
                }
                break;
            }
        }
        if (aa != -11) {
            backpack.removeEquipment(equipments.get(aa).getId());
            equipments.remove(aa);
        }
    }

    public String getName() {
        return name;
    }

    public String str(String name) {
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getName().equals(name)) {
                return equipments.get(i).getclass();
            }
        }
        return "Axe";
    }
}
