package cz.orany.yuml.dsl;

import cz.orany.yuml.Diagram;
import cz.orany.yuml.Type;

public class InheritanceBuilder {
    public InheritanceBuilder(Diagram diagram, Type destination) {
        this.source = destination;
        this.diagram = diagram;
    }

    public RelationshipDefinition type(String destination) {
        return diagram.inheritance(source.getName(), destination);
    }

    private final Type source;
    private final Diagram diagram;
}
