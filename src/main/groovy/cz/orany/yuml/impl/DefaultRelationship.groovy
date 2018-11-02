package cz.orany.yuml.impl

import cz.orany.yuml.Relationship
import cz.orany.yuml.RelationshipType
import cz.orany.yuml.Type
import cz.orany.yuml.dsl.RelationshipDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class DefaultRelationship implements Relationship, RelationshipDefinition {

    final Type source
    final RelationshipType type
    final Type destination

    DefaultRelationship(Type source, RelationshipType type = RelationshipType.ASSOCIATION, Type destination) {
        this.source = source
        this.type = type
        this.destination = destination
    }

    boolean bidirectional

    DefaultRelationship bidirectional(boolean bidirectional) {
        this.bidirectional = bidirectional
        return this
    }


    String sourceCardinality
    String sourceTitle

    DefaultRelationship source(String cardinality, String title = null) {
        this.sourceCardinality = cardinality
        this.sourceTitle = title
        this
    }

    String destinationCardinality
    String destinationTitle

    DefaultRelationship destination(String cardinality, String title = null) {
        this.destinationCardinality = cardinality
        this.destinationTitle = title
        this
    }

    DefaultRelationship called(String sourceTitle) {
        this.sourceTitle = sourceTitle
        this
    }

    @Override
    String toString() {
        return type.toString(this)
    }
}
