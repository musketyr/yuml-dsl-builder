package cz.orany.yuml.model.impl

import cz.orany.yuml.model.dsl.DiagramDefinition
import cz.orany.yuml.model.dsl.RelationshipDefinition
import cz.orany.yuml.model.dsl.TypeDefinition
import cz.orany.yuml.model.Diagram
import cz.orany.yuml.model.Note
import cz.orany.yuml.model.Relationship
import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.Type
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope

@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultDiagram implements Diagram, DiagramDefinition {

    final Collection<DefaultNote> notes = new LinkedHashSet<>()
    final Collection<DefaultRelationship> relationships = new LinkedHashSet<>()

    private final Map<String, DefaultType> typesMap = [:].withDefault { key -> new DefaultType(this, key.toString()) }

    @Override
    Collection<? extends Type> getTypes() {
        return typesMap.values()
    }

    @Override
    String toYuml() {
        return toString()
    }

    @Override
    DefaultNote note(String text, String color) {
        Note note = new DefaultNote(text, color)
        this.notes.add(note)
        return note
    }

    @Override
    DefaultType type(String name, @DelegatesTo(value = TypeDefinition, strategy = Closure.DELEGATE_FIRST) Closure builder) {
        DefaultType type = typesMap[name]
        type.with builder
        return type
    }

    @Override
    DefaultRelationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        DefaultRelationship relationship = new DefaultRelationship(type(source, Closure.IDENTITY), relationshipType, type(destination, Closure.IDENTITY))
        relationship.with additionalProperties
        this.relationships.add(relationship)
        return relationship
    }

    @Override
    String toString() {
        assert typesMap

        StringWriter stringWriter = new StringWriter()
        PrintWriter printWriter = new PrintWriter(stringWriter)

        for (Note note in notes) {
            stringWriter.println(note)
        }

        Set<String> orphanTypes = new LinkedHashSet<>(typesMap.keySet())

        for (Relationship relationship in relationships) {
            orphanTypes.remove(relationship.source.name)
            orphanTypes.remove(relationship.destination.name)

            printWriter.println(relationship)
        }

        for (String name in orphanTypes) {
            printWriter.println(typesMap[name])
        }

        return stringWriter.toString()
    }

}
