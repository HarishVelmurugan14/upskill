package LLD.Part2.CreationalDesignPattern.Factory.AbstractFactory;

public abstract class DatabaseComponentFactory {

    abstract Query getQueryConnection();        // factory method: only creates

    abstract Transactions getTxnConnection();
}

class MySqlFactory extends DatabaseComponentFactory {
    Query getQueryConnection() {
        return new MySqlQuery();
    }

    Transactions getTxnConnection() {
        return new MySqlTransaction();
    }
}

class PostgresFactory extends DatabaseComponentFactory {
    Query getQueryConnection() {
        return new PostgreSql();
    }

    Transactions getTxnConnection() {
        return new PostrgresTransaction();
    }
}