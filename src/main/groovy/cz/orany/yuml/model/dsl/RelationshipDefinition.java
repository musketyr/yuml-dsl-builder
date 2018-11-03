package cz.orany.yuml.model.dsl;

public interface RelationshipDefinition extends DiagramContentDefinition {
    RelationshipDefinition bidirectional(boolean bidirectional);

    default RelationshipDefinition source(String cardinality) {
        return source(cardinality, null);
    }

    RelationshipDefinition source(String cardinality, String title);

    default RelationshipDefinition destination(String cardinality) {
        return destination(cardinality, null);
    }

    RelationshipDefinition destination(String cardinality, String title);

    RelationshipDefinition called(String sourceTitle);
}
