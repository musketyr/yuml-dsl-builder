package cz.orany.yuml

class AggregationOrCompositionBuilder {

    private final Diagram diagram
    private final Type destination
    private final RelationshipType relationshipType
    private final String cardinality

    AggregationOrCompositionBuilder(Diagram diagram, Type destination, RelationshipType relationshipType, String cardinality) {
        this.diagram = diagram
        this.destination = destination
        this.relationshipType = relationshipType
        this.cardinality = cardinality
    }

    AggregationOrCompositionBuilder to(Object upperBound) {
        return new AggregationOrCompositionBuilder(diagram, destination, relationshipType, "$cardinality..$upperBound")
    }

    Relationship type(String aSource) {
        return diagram.relationship(aSource, relationshipType, destination.name) {
            source cardinality
        }
    }
}
