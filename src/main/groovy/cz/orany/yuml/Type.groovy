package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Type {

    String name

    boolean stereotype = false
    boolean standard = false

    Collection<Type> parents = new LinkedHashSet<>()
    Collection<Property> properties = new LinkedHashSet<>()
    Collection<Method> methods = new LinkedHashSet<>()

}
