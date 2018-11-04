package cz.orany.yuml.stereotype;

import cz.orany.yuml.model.dsl.*;

public class StereotypeExtensions {

    public static TypeDefinition stereotype(DiagramDefinition diagram, String name) {
        return diagram.type("<<" + name + ">>");
    }

    public static RelationshipDefinition stereotype(AggregationOrCompositionBuilder builder, String name) {
        return builder.type("<<" + name + ">>");
    }

    public static RelationshipDefinition stereotype(InheritanceBuilder builder, String name) {
        return builder.type("<<" + name + ">>");
    }

}
