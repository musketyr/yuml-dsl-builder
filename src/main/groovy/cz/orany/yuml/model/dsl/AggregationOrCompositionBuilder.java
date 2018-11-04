package cz.orany.yuml.model.dsl;

import cz.orany.yuml.model.RelationshipType;
import cz.orany.yuml.model.Type;

public final class AggregationOrCompositionBuilder implements HasDiagramDefinition {

    public AggregationOrCompositionBuilder(DiagramDefinition diagram, Type destination, RelationshipType relationshipType, String cardinality) {
        this.diagram = diagram;
        this.destination = destination;
        this.relationshipType = relationshipType;
        this.cardinality = cardinality;
    }

    public AggregationOrCompositionBuilder to(Object upperBound) {
        return new AggregationOrCompositionBuilder(diagram, destination, relationshipType, cardinality + ".." + upperBound);
    }

    public RelationshipDefinition type(String aSource) {
        return diagram.relationship(aSource, relationshipType, destination.getName()).source(cardinality);
    }

    @Override
    public DiagramDefinition getDiagramDefinition() {
        return diagram;
    }

    private final DiagramDefinition diagram;
    private final Type destination;
    private final RelationshipType relationshipType;
    private final String cardinality;
}
