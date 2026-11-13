package LLD.Part2.CreationalDesignPattern.Factory.SimpleFactory;

// ===== Query side =====
interface Query {
    String build(String table);
}

class SqlQuery implements Query {
    @Override
    public String build(String table) {
        return "SELECT * FROM " + table + ";";
    }
}

class NoSqlQuery implements Query {
    @Override
    public String build(String collection) {
        return "db." + collection + ".find({})";
    }
}

// ===== Database side =====
interface Database {
    void connect();
    Query createQuery();
}

class MySql implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL");
    }

    @Override
    public Query createQuery() {
        return new SqlQuery();
    }
}

class PostgreSql implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to PostgreSQL");
    }

    @Override
    public Query createQuery() {
        return new SqlQuery();
    }
}

class MongoDB implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MongoDB");
    }

    @Override
    public Query createQuery() {
        return new NoSqlQuery();
    }
}

// ===== Simple Factory =====
class DBFactory {
    public static Database create(String name) {
        switch (name.toLowerCase()) {
            case "mysql":
                return new MySql();
            case "postgresql":
                return new PostgreSql();
            case "mongodb":
                return new MongoDB();
            default:
                throw new IllegalArgumentException("Unknown DB: " + name);
        }
    }
}