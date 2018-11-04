package cz.orany.yuml.model.impl

import cz.orany.yuml.model.Diagram
import cz.orany.yuml.model.Note
import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.Type
import cz.orany.yuml.model.dsl.DiagramContentDefinition
import cz.orany.yuml.model.dsl.DiagramDefinition
import cz.orany.yuml.model.dsl.DiagramHelper
import cz.orany.yuml.model.dsl.RelationshipDefinition
import cz.orany.yuml.model.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import groovy.transform.stc.SimpleType
import org.codehaus.groovy.runtime.DefaultGroovyMethods

@ToString
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultDiagram implements Diagram, DiagramDefinition {

    final Collection<DefaultNote> notes = new LinkedHashSet<>()
    final Collection<DefaultRelationship> relationships = new LinkedHashSet<>()
    final Map<String, Object> metadata = new LinkedHashMap<>();

    private final Object owner;
    private final Map<String, DefaultType> typesMap = [:].withDefault { key -> new DefaultType(this, key.toString()) }
    private final Map<Class<? extends DiagramHelper>, DiagramHelper> helperMap = [:]

    DefaultDiagram(Object owner) {
        this.owner = owner
    }

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
        @DelegatesTo(value = TypeDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.TypeDefinition")
        Closure<? extends DiagramContentDefinition> builder
    ) {
        DefaultType type = typesMap[name]
        withSameOwner type, builder
        return type
    }

    @Override
    DefaultRelationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        DefaultRelationship relationship = new DefaultRelationship(this, type(source, Closure.IDENTITY), relationshipType, type(destination, Closure.IDENTITY))
        withSameOwner relationship, additionalProperties
        this.relationships.add(relationship)
        return relationship
    }

    protected <V, T> V withSameOwner(T self, Closure<V> closure) {
        final Closure<V> clonedClosure = closure.rehydrate(self, owner, closure.thisObject)
        clonedClosure.setResolveStrategy(Closure.DELEGATE_FIRST);
        clonedClosure.call(self)
    }

    @Override
    public <H extends DiagramHelper, R> H configure(
        @DelegatesTo.Target("helper")
        Class<H> helper,
        @DelegatesTo(value = DelegatesTo.Target.class, target = "helper", strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0)
        @ClosureParams(FirstParam.FirstGenericType.class)
        Closure<R> configuration
    ) {
        H helperInstance = (H) helperMap.computeIfAbsent(helper, { helper.newInstance()})
        DefaultGroovyMethods.with(helperInstance, configuration)
        return helperInstance
    }

    void postprocess() {
        for (DiagramHelper helper : helperMap.values()) {
            metadata.putAll(helper.metadata)
        }
    }
}
