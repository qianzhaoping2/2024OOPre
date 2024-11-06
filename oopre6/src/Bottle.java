public class Bottle implements Commodity {
    private final int id;
    private final String name;
    private int capacity;
    private int ce;
    private int now;

    public Bottle(int id,String name,int capacity,int ce) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.ce = ce;
        this.now = capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int CE(int id) {
        return ce;
    }

    public String getclass() {
        return "Bottle";
    }

    public void setNow(int now) {
        this.now = now;
    }

    public int getNow() {
        return now;
    }
}
