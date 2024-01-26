package adt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDict < K, V > implements MyIDict < K, V > {
    private Map < K, V > map;

    public MyDict() {
        map = new HashMap < K, V >();
    }

    @Override
    public void put(K k, V v) {
        map.put(k,v);
    }

    @Override
    public boolean isDefined(K k) {
        return map.get(k) != null;
    }

    @Override
    public V lookUp(K k) {
        return map.get(k);
    }

    @Override
    public String toString() {
        return "MyDictionary{" +
                "map = " + map +
                "}\n";
    }

    @Override
    public String fileString() {
        String rez = "";
        for (Map.Entry<K, V> t:this.map.entrySet()) {
            rez += t.getKey() + " -> " + t.getValue() + "\n";
        }
        return rez;
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

    @Override
    public void setContent(Map < K, V > mp) {
        map = mp;
    } 

    @Override
    public void copyContent(Map < K, V > mp) {
        map.clear();
        map.putAll(mp);
    } 

    @Override
    public Collection < V > getValues() {
        return map.values();
    }

    @Override
    public MyIDict <K, V> clone() {
        MyDict < K, V > copydct = new MyDict<K, V>(); 
        copydct.copyContent(map);
        return copydct;
    }
}
