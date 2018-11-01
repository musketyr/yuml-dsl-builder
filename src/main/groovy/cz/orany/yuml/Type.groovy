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

    AggregationOrCompositionBuilder has(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.AGGREGATION, sourceCardinality as String)
    }

    AggregationOrCompositionBuilder owns(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.COMPOSITION, sourceCardinality as String)
    }

    @Override
    String toString() {
        return "[$name]"
    }
}
