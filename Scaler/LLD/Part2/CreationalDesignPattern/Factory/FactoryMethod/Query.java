package LLD.Part2.CreationalDesignPattern.Factory.FactoryMethod;

public interface Query {
    void createQuery();
}

class MySqlQuery implements Query {
    public void createQuery() {
        System.out.println("MySql query has been created");
    }
}

class PostgreSql implements Query {
    public void createQuery() {
        System.out.println("PostgreSql query has been created");
    }
}