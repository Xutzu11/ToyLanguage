package adt;

import java.util.HashMap;
import java.util.Map;

public class MyFileTable < K, V > implements MyIFileTable < K, V > {
    private Map < K, V > map;

    public MyFileTable() {
        map = new HashMap < K, V >();
    }

    @Override
    public void put(K k, V v) {
        map.put(k,v);
    }

    @Override
    public boolean isDefined(K k) {
        for (Map.Entry < K, V > x : map.entrySet()) {
            if (x.getKey().equals(k)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V lookUp(K k) {
        for (Map.Entry < K, V > x : map.entrySet()) {
            if (x.getKey().equals(k)) {
                return x.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "MyFileTable{" +
                "table = " + map +
                "}\n";
    }

    @Override
    public String fileString() {
        String rez = "";
        for (Map.Entry<K, V> t:this.map.entrySet()) {
            rez += t.getKey() + "\n";
        }
        return rez;
    }

    @Override
    public void remove(K k) {
        for (Map.Entry < K, V > x : map.entrySet()) {
            if (x.getKey().equals(k)) {
                map.remove(x.getKey());
                return;
            }
        }
    }

    public Map<K, V> getMap() {
        return map;
    }

    @Override
    public void clear() {
        map.clear();
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public Map < K, V > getContent() {
        return map;
    }
}
