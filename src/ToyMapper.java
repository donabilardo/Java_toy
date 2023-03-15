public class ToyMapper {
    public String map(Toy toy) {
        return String.format("%s; %s; %s; %s", toy.getId(), toy.getName(), toy.getCount(), toy.getVin_weight());
    }

    public Toy map(String line) {
        if (line.contains("; ")) {
            String[] lines = line.split("; ");
            return new Toy(lines[0], lines[1], lines[2], lines[3]);
        }
        else{
            String[] lines = line.split(",");
            return new Toy(lines[0], lines[1], lines[2], lines[3]);
        }
    }

}

