package cz.orany.yuml.dsl;

import cz.orany.yuml.Diagram;
import cz.orany.yuml.RelationshipType;
import cz.orany.yuml.Type;

public class AggregationOrCompositionBuilder {
    public AggregationOrCompositionBuilder(Diagram diagram, Type destination, RelationshipType relationshipType, String cardinality) {
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

    private final Diagram diagram;
    private final Type destination;
    private final RelationshipType relationshipType;
    private final String cardinality;
}
