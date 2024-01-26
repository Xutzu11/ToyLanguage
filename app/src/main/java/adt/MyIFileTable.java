package adt;

import java.util.Map;

public interface MyIFileTable < K, V > {
    void put(K k, V v);
    void remove(K k);
    boolean isDefined(K k);
    V lookUp(K k);
    void clear();
    String fileString();
    Map < K, V > getContent();
}
