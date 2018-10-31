package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Relationship {

    RelationshipType type = RelationshipType.ASSOCIATION
    boolean bidirectional

    Type source
    String sourceCardinality
    String sourceTitle

    Type destination
    String destinationCardinality
    String destinationTitle

    @Override
    String toString() {
        return type.toString(this)
    }
}
