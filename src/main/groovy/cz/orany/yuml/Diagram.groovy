package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Diagram {

    static Diagram build(@DelegatesTo(value = Diagram, strategy = Closure.DELEGATE_FIRST) Closure definition = Closure.IDENTITY) {
        Diagram diagram = new Diagram()
        diagram.with definition
        diagram
    }

    static From getFrom() {
        return From.FROM
    }

    Collection<Note> notes = new LinkedHashSet<>()
    Map<String, Type> types = [:].withDefault { key -> new Type(this, key.toString()) }
    Collection<Relationship> relationships = new LinkedHashSet<>()

    Note note(String text, String color = null) {
        Note note = new Note(text, color)
        this.notes.add(note)
        return note
    }

    Type type(String name) {
        types[name]
    }

    Relationship aggregation(
        String source,
        String destination,
        @DelegatesTo(value = Relationship, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties = Closure.IDENTITY
    ) {
        return relationship(source, RelationshipType.AGGREGATION, destination, additionalProperties)
    }

    Relationship composition(
        String source,
        String destination,
        @DelegatesTo(value = Relationship, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties = Closure.IDENTITY
    ) {
        return relationship(source, RelationshipType.COMPOSITION, destination, additionalProperties)
    }

    Relationship inheritance(
        String source,
        String destination,
        @DelegatesTo(value = Relationship, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties = Closure.IDENTITY
    ) {
        return relationship(source, RelationshipType.INHERITANCE, destination, additionalProperties)
    }

    Relationship association(
        String source,
        String destination,
        @DelegatesTo(value = Relationship, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties = Closure.IDENTITY
    ) {
        return relationship(source, RelationshipType.ASSOCIATION, destination, additionalProperties)
    }

    Relationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = Relationship, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties = Closure.IDENTITY
    ) {
        Relationship relationship = new Relationship(type(source), relationshipType, type(destination))
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
