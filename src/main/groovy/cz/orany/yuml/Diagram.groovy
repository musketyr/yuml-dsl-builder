package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Diagram {

    Collection<Note> notes = new LinkedHashSet<>()
    Collection<Type> types = new LinkedHashSet<>()
    Collection<Relationship> relationships = new LinkedHashSet<>()

    @Override
    String toString() {
        assert types

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        for (Note note in notes) {
            stringWriter.println(note)
        }

        Collection<Type> orphanTypes = new LinkedHashSet<>(types)

        for (Relationship relationship in relationships) {
            orphanTypes.remove(relationship.source)
            orphanTypes.remove(relationship.destination)

            printWriter.println(relationship)
        }

        for (Type type in orphanTypes) {
            printWriter.println(type)
        }

        return stringWriter.toString()
    }

}
