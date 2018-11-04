package cz.orany.yuml.model.dsl;

public interface TypeDefinition extends DiagramContentDefinition, HasDiagramDefinition {

    InheritanceBuilder inherits(From from);
    AggregationOrCompositionBuilder has(Object sourceCardinality);
    AggregationOrCompositionBuilder owns(Object sourceCardinality);

}
