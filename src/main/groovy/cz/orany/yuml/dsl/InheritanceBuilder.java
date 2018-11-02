package cz.orany.yuml.dsl;

import cz.orany.yuml.Type;

public class InheritanceBuilder {
    public InheritanceBuilder(DiagramDefinition diagram, Type destination) {
        this.source = destination;
        this.diagram = diagram;
    }

    public RelationshipDefinition type(String destination) {
        return diagram.inheritance(source.getName(), destination);
    }

    private final Type source;
    private final DiagramDefinition diagram;
}
