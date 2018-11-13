package cz.orany.yuml.model.dsl;

import groovy.transform.NamedParam;

public interface RelationshipDefinition extends DiagramContentDefinition, HasDiagramDefinition {
    RelationshipDefinition bidirectional(boolean bidirectional);

    default RelationshipDefinition source(String cardinality) {
        return source(cardinality, null);
    }

    RelationshipDefinition source(String cardinality, String title);

    default RelationshipDefinition destination(String cardinality) {
        return destination(cardinality, null);
    }

    RelationshipDefinition destination(@NamedParam("cardinality") String cardinality, @NamedParam("title") String title);

    RelationshipDefinition called(String sourceTitle);
}
