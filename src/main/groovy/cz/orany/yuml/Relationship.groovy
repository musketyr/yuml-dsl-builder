package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Relationship {

    final Type source
    final RelationshipType type
    final Type destination

    Relationship(Type source, RelationshipType type = RelationshipType.ASSOCIATION, Type destination) {
        this.source = source
        this.type = type
        this.destination = destination
    }

    boolean bidirectional

    String sourceCardinality
    String sourceTitle

    String destinationCardinality
    String destinationTitle

    @Override
    String toString() {
        return type.toString(this)
    }
}
