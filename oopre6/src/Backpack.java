import java.util.HashMap;

public class Backpack {
    private HashMap<Integer,Bottle> bottleHashMap = new HashMap<>();
    private HashMap<Integer,Equipment> equipmentHashMap = new HashMap<>();
    private int atk = 0;
    private int def = 0;
    private int hp = 0;

    public Backpack() {

    }

    public int sumatk() {
        int sum = 0;
        for (Integer key : equipmentHashMap.keySet()) {
            Equipment e = equipmentHashMap.get(key);
            sum += e.CE(e.getId());
        }
        for (Integer key : bottleHashMap.keySet()) {
            Bottle b = bottleHashMap.get(key);
            sum += b.CE(b.getId());
        }
        return sum;
    }

    public HashMap<Integer,Equipment> help() {
        return equipmentHashMap;
    }

    public void deleteEquipments() {
        equipmentHashMap.clear();
    }

    public void carryEquipment(Equipment equipment) {
        if (equipmentHashMap.isEmpty()) {
            equipmentHashMap.put(equipment.getId(),equipment);
        } else {
            int aaa = 9;
            for (Integer key : equipmentHashMap.keySet()) {
                if (equipmentHashMap.get(key) == null) {
                    break;
                }
                if (equipmentHashMap.get(key).getclass().equals(equipment.getclass())) {
                    aaa = key;
                }
            }
            if (aaa != 9) {
                equipmentHashMap.remove(aaa);
            }
            equipmentHashMap.put(equipment.getId(),equipment);
        }

    }

    public void removeEquipment(int id) {
        if (this.hasEquipment(id)) {
            equipmentHashMap.remove(id);
        }
    }

    public void carryBottle(Bottle bottle, int maxBottle) {
        if (bottle.getclass().equals("AtkBottle") && atk < maxBottle) {
            bottleHashMap.put(bottle.getId(),bottle);
        } else if (bottle.getclass().equals("DefBottle") && def < maxBottle) {
            bottleHashMap.put(bottle.getId(),bottle);
        } else if (bottle.getclass().equals("HpBottle") && hp < maxBottle) {
            bottleHashMap.put(bottle.getId(),bottle);
        }
    }

    public void removeBottle(int id) {
        if (this.hasBottle(id)) {
            bottleHashMap.remove(id);
        }
    }
    /*
    public boolean hasBottle(int id) {
        for (Integer key : bottleHashMap.keySet()) {
            if (bottleHashMap.get(key).getId() == id) {
                return true;
            }
        }
        return false;
    }
    */

    public boolean hasBottle(int id) {
        if (bottleHashMap.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasEquipment(int id) {
        if (equipmentHashMap.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public int getCe(String name) {
        for (Integer key : equipmentHashMap.keySet()) {
            if (equipmentHashMap.get(key) == null) {
                break;
            }
            if (equipmentHashMap.get(key).getclass().equals(name)) {
                return equipmentHashMap.get(key).CE(equipmentHashMap.get(key).getId());
            }
        }
        return 0;
    }

    public int getEid(String name) {
        for (Integer key : equipmentHashMap.keySet()) {
            if (equipmentHashMap.get(key) == null) {
                break;
            }
            if (equipmentHashMap.get(key).getclass().equals(name)) {
                return equipmentHashMap.get(key).getId();
            }
        }
        return 0;
    }
}
