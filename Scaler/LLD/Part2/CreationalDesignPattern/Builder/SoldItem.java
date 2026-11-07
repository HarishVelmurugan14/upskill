package LLD.Part2.CreationalDesignPattern.Builder;

public class SoldItem {
    private final String name;
    private final int id;
    private final double price;

    private SoldItem(Builder b) {
        this.name = b.name;
        this.id = b.id;
        this.price = b.price;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void printAll() {
        System.out.println(this.name + " " + this.id);
    }

    public static class Builder {
        private String name;
        private int id;
        private double price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public SoldItem build() {
            return new SoldItem(this);
        }
    }
}