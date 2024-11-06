public class Equipment implements Commodity {
    private final int id;
    private final String name;
    private int durability;
    private int ce;

    public  Equipment(int id,String name,int durability,int ce) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.ce = ce;
    }

    public void add() {
        this.durability++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDurability() {
        return this.durability;
    }

    @Override
    public int CE(int id) {
        return ce;
    }

    public String getclass() {
        return "Equipment";
    }

    public void use() {
        this.durability--;
    }
}
