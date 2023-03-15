public class Toy {
    private String id = "";
    private String name;
    private String count;
    private String vin_weight;

    public Toy(String name, String count, String vin_weight) {
        this.name = name;
        this.count = count;
        this.vin_weight = vin_weight;
    }

    public Toy(String id, String name, String count, String vin_weight) {
        this(name, count, vin_weight);
        this.id = id;
    }
    
    
    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getCount() {
        return count;
    }



    public void setCount(String count) {
        this.count = count;
    }



    public String getVin_weight() {
        return vin_weight;
    }



    public void setVin_weight(String vin_weight) {
        this.vin_weight = vin_weight;
    }



    @Override
    public String toString() {
        return "Toy " + id + ", name: " + name + ", count: " + count + ", vin_weight: " + vin_weight;
    }

    
}