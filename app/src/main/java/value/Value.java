package value;

import type.*;

public interface Value {
    Type getType();

    @Override
    boolean equals(Object oth);
}
