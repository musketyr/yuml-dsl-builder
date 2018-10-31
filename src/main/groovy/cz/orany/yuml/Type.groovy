package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Type {

    final String name

    Type(String name) {
        this.name = name
    }

    @Override
    String toString() {
        return "[$name]"
    }
}
