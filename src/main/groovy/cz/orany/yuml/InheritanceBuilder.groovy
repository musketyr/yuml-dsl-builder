package cz.orany.yuml

import groovy.transform.CompileStatic

@CompileStatic
class InheritanceBuilder {

    private final Type source
    private final Diagram diagram


    InheritanceBuilder(Diagram diagram, Type destination) {
        this.source = destination
        this.diagram = diagram
    }

    Relationship type(String destination) {
        return diagram.inheritance(source.name, destination)
    }
}
