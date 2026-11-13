package LLD.Part2.CreationalDesignPattern.Factory.FactoryMethod;

public abstract class DatabaseFactory {

    abstract Query getQueryConnection();        // factory method: only creates
}

class MySqlService extends DatabaseFactory {
    Query getQueryConnection() {
        return new MySqlQuery();
    }
}

class PostgreService extends DatabaseFactory {
    Query getQueryConnection() {
        return new PostgreSql();
    }
}