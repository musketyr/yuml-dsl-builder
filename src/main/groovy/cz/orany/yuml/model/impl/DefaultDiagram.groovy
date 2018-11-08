package cz.orany.yuml.model.impl

import cz.orany.yuml.model.Diagram
import cz.orany.yuml.model.Note
import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.Type
import cz.orany.yuml.model.dsl.DiagramDefinition
import cz.orany.yuml.model.dsl.DiagramHelper
import cz.orany.yuml.model.dsl.RelationshipDefinition
import cz.orany.yuml.model.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

import java.util.function.Consumer

@ToString
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultDiagram implements Diagram, DiagramDefinition {

    final Collection<DefaultNote> notes = new LinkedHashSet<>()
    final Collection<DefaultRelationship> relationships = new LinkedHashSet<>()
    final Map<String, Object> metadata = new LinkedHashMap<>();

    private final Map<String, DefaultType> typesMap = [:].withDefault { key -> new DefaultType(this, key.toString()) }
    private final Map<Class<? extends DiagramHelper>, DiagramHelper> helperMap = [:]

    @Override
    Collection<? extends Type> getTypes() {
        return typesMap.values()
    }

    @Override
    DefaultNote note(String text, String color) {
        Note note = new DefaultNote(text, color)
        this.notes.add(note)
        return note
    }

    @Override
    DefaultType type(
        String name,
        Consumer<TypeDefinition> builder
    ) {
        DefaultType type = typesMap[name]
        builder.accept(type)
        return type
    }

    @Override
    DefaultRelationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        DefaultRelationship relationship = new DefaultRelationship(this, typesMap[source], relationshipType, typesMap[destination])
        additionalProperties.accept(relationship)
        this.relationships.add(relationship)
        return relationship
    }

    @Override
    public <H extends DiagramHelper> H configure(Class<H> helper, Consumer<H> configuration) {
        H helperInstance = (H) helperMap.computeIfAbsent(helper, { helper.newInstance()})
        configuration.accept(helperInstance)
        return helperInstance
    }

    void postprocess() {
        for (DiagramHelper helper : helperMap.values()) {
            metadata.putAll(helper.metadata)
        }
    }
}
