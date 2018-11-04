package cz.orany.yuml.model.impl

import cz.orany.yuml.model.Relationship
import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.Type
import cz.orany.yuml.model.dsl.DiagramDefinition
import cz.orany.yuml.model.dsl.RelationshipDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultRelationship implements Relationship, RelationshipDefinition {

    private final DefaultDiagram diagram

    final Type source
    final RelationshipType type
    final Type destination

    DefaultRelationship(DefaultDiagram diagram, Type source, RelationshipType type = RelationshipType.ASSOCIATION, Type destination) {
        this.diagram = diagram
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
    DiagramDefinition getDiagramDefinition() {
        return diagram
    }
}
