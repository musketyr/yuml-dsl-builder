package cz.orany.yuml.properties

import cz.orany.yuml.model.dsl.TypeDefinition
import groovy.transform.CompileStatic

@CompileStatic
class PropertiesDiagramHelperExtensions {

    static TypeDefinition property(TypeDefinition typeDefinition, String type, String name) {
        typeDefinition.diagramDefinition.configure(PropertiesDiagramHelper) { PropertiesDiagramHelper helper ->
            helper.addProperty(typeDefinition.name, type, name)
        }
        return typeDefinition
    }

    static TypeDefinition property(TypeDefinition typeDefinition, Map<String, String> properties) {
        typeDefinition.diagramDefinition.configure(PropertiesDiagramHelper) { PropertiesDiagramHelper helper ->
            helper.addProperties(typeDefinition.name, properties)
        }
        return typeDefinition
    }

}
