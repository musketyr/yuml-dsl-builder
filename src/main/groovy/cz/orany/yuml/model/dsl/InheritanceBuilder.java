package cz.orany.yuml.model.dsl;

import cz.orany.yuml.model.Type;

public final class InheritanceBuilder implements HasDiagramDefinition {
    public InheritanceBuilder(DiagramDefinition diagram, Type destination) {
        this.source = destination;
        this.diagram = diagram;
    }

    public RelationshipDefinition type(String destination) {
        return diagram.inheritance(source.getName(), destination);
    }

    @Override
    public DiagramDefinition getDiagramDefinition() {
        return diagram;
    }

    private final Type source;
    private final DiagramDefinition diagram;
}
