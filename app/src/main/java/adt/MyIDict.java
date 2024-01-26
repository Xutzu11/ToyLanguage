package adt;

import java.util.Collection;
import java.util.Map;

public interface MyIDict < K, V > {
    void put(K k, V v);
    boolean isDefined(K k);
    V lookUp(K k);
    void clear();
    String fileString();
    Map < K, V > getContent();
    Collection < V > getValues();
    void setContent(Map < K, V > mp);
    void copyContent(Map < K, V > mp);
    MyIDict<K, V> clone();
}
