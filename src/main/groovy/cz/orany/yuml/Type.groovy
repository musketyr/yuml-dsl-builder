package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Type {

    private final Diagram diagram

    final String name

    Type(Diagram diagram, String name) {
        this.name = name
        this.diagram = diagram
    }

    InheritanceBuilder inherits(From from) {
        return new InheritanceBuilder(diagram, this)
    }

    @Override
    String toString() {
        return "[$name]"
    }
}
