package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Type {

    boolean stereotype = false
    boolean standard = false

    Collection<Type> parents = new LinkedHashSet<>()
    Collection<Relationship> relationships = new LinkedHashSet<>()
    Collection<Property> properties = new LinkedHashSet<>()
    Collection<Method> methods = new LinkedHashSet<>()

}
