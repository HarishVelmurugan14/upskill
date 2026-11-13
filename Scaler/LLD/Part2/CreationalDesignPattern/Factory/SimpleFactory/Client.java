package LLD.Part2.CreationalDesignPattern.Factory.SimpleFactory;

public class Client {
 public static void main(String[] args) {
        String[] dbs = {"mysql", "postgresql", "mongodb"};

        for (String name : dbs) {
            Database db = DBFactory.create(name);
            db.connect();

            Query query = db.createQuery();
            System.out.println(query.build("users"));
            System.out.println();
        }
    }
}