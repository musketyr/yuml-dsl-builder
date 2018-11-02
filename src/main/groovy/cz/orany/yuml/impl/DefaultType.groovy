package cz.orany.yuml.impl

import cz.orany.yuml.RelationshipType
import cz.orany.yuml.Type
import cz.orany.yuml.dsl.AggregationOrCompositionBuilder
import cz.orany.yuml.dsl.From
import cz.orany.yuml.dsl.InheritanceBuilder
import cz.orany.yuml.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class DefaultType implements Type, TypeDefinition {

    private final DefaultDiagram diagram

    final String name

    DefaultType(DefaultDiagram diagram, String name) {
        this.name = name
        this.diagram = diagram
    }

    @Override
    InheritanceBuilder inherits(From from) {
        return new InheritanceBuilder(diagram, this)
    }

    @Override
    AggregationOrCompositionBuilder has(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.AGGREGATION, sourceCardinality as String)
    }

    @Override
    AggregationOrCompositionBuilder owns(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.COMPOSITION, sourceCardinality as String)
    }

    @Override
    String toString() {
        return "[$name]"
    }
}
