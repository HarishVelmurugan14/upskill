package LLD.Part2.CreationalDesignPattern.PrototypeRegistry;

class Pizza implements Cloneable {
    String name;
    String size;

    public Pizza clone() {
        try {
            return (Pizza) super.clone(); // SHALLOW COPY : SUB OBJECTS WONT BE COPIED
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}