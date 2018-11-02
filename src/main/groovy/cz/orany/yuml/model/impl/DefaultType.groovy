package cz.orany.yuml.model.impl

import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.Type
import cz.orany.yuml.model.dsl.AggregationOrCompositionBuilder
import cz.orany.yuml.model.dsl.From
import cz.orany.yuml.model.dsl.InheritanceBuilder
import cz.orany.yuml.model.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope

@PackageScope
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
