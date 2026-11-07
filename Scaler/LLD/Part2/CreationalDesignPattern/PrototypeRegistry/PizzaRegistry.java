package LLD.Part2.CreationalDesignPattern.PrototypeRegistry;

import java.util.HashMap;
import java.util.Map;

class PizzaRegistry {
    Map<String, Pizza> menu = new HashMap<>();

    void add(String key, Pizza p) {
        menu.put(key, p);
    }

    Pizza get(String key) {
        return menu.get(key).clone();
    }
}