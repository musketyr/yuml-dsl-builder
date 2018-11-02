package cz.orany.yuml

import cz.orany.yuml.dsl.AggregationOrCompositionBuilder
import cz.orany.yuml.dsl.From
import cz.orany.yuml.dsl.InheritanceBuilder
import cz.orany.yuml.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Type implements TypeDefinition {

    private final Diagram diagram

    final String name

    Type(Diagram diagram, String name) {
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
