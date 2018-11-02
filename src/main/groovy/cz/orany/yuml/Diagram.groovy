package cz.orany.yuml

import cz.orany.yuml.dsl.DiagramDefinition
import cz.orany.yuml.dsl.RelationshipDefinition
import cz.orany.yuml.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Diagram implements DiagramDefinition {

    static Diagram build(@DelegatesTo(value = DiagramDefinition, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        Diagram diagram = new Diagram()
        diagram.with definition
        diagram
    }

    Collection<Note> notes = new LinkedHashSet<>()
    Map<String, Type> types = [:].withDefault { key -> new Type(this, key.toString()) }
    Collection<Relationship> relationships = new LinkedHashSet<>()

    @Override
    Note note(String text, String color) {
        Note note = new Note(text, color)
        this.notes.add(note)
        return note
    }

    @Override
    Type type(String name, @DelegatesTo(value = TypeDefinition, strategy = Closure.DELEGATE_FIRST) Closure builder) {
        Type type = types[name]
        type.with builder
        return type
    }

    @Override
    Relationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        Relationship relationship = new Relationship(type(source, Closure.IDENTITY), relationshipType, type(destination, Closure.IDENTITY))
        relationship.with additionalProperties
        this.relationships.add(relationship)
        return relationship
    }

    @Override
    String toString() {
        assert types

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        for (Note note in notes) {
            stringWriter.println(note)
        }

        Set<String> orphanTypes = new LinkedHashSet<>(types.keySet())

        for (Relationship relationship in relationships) {
            orphanTypes.remove(relationship.source.name)
            orphanTypes.remove(relationship.destination.name)

            printWriter.println(relationship)
        }

        for (String name in orphanTypes) {
            printWriter.println(types[name])
        }

        return stringWriter.toString()
    }

}
