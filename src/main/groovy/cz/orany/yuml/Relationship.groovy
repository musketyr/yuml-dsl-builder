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

    Relationship bidirectional(boolean bidirectional) {
        this.bidirectional = bidirectional
        return this
    }


    String sourceCardinality
    String sourceTitle

    Relationship source(String cardinality, String title = null) {
        this.sourceCardinality = cardinality
        this.sourceTitle = title
        this
    }

    String destinationCardinality
    String destinationTitle

    Relationship destination(String cardinality, String title = null) {
        this.destinationCardinality = cardinality
        this.destinationTitle = title
        this
    }

    Relationship called(String sourceTitle) {
        this.sourceTitle = sourceTitle
        this
    }

    @Override
    String toString() {
        return type.toString(this)
    }
}
