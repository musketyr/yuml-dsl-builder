package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Diagram {

    Collection<Note> notes = new LinkedHashSet<>()
    Map<String, Type> types = [:].withDefault { key -> new Type(key.toString()) }
    Collection<Relationship> relationships = new LinkedHashSet<>()

    Note note(String text, String color = null) {
        Note note = new Note(text, color)
        this.notes.add(note)
        return note
    }

    Type type(String name) {
        types[name]
    }

    Relationship relationship(Type source, RelationshipType type = RelationshipType.ASSOCIATION, Type destination) {
        Relationship relationship = new Relationship(source, type, destination)
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
