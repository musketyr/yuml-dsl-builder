package cz.orany.yuml.properties;

import cz.orany.yuml.model.Diagram;
import cz.orany.yuml.model.Type;
import cz.orany.yuml.model.dsl.DiagramHelper;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesDiagramHelper implements DiagramHelper {

    private static final String METADATA_KEY = "cz.orany.yuml.properties.PropertiesDiagramHelper.typeToProperties";

    private final Map<String, Map<String, String>> typeToProperties = new LinkedHashMap<>();

    public static Map<String, String> getProperties(Diagram diagram, Type type) {
        Map<String, Map<String, String>> typeToProperties =
            (Map<String, Map<String, String>>) diagram.getMetadata().computeIfAbsent(METADATA_KEY, (key) -> new LinkedHashMap<>());
        return typeToProperties.computeIfAbsent(type.getName(), (key) -> new LinkedHashMap<>());
    }

    public PropertiesDiagramHelper addProperty(String owner, String type, String name) {
        Map<String, String> properties = typeToProperties.computeIfAbsent(owner, (key) -> new LinkedHashMap<>());
        properties.put(name, type);
        return this;
    }

    public PropertiesDiagramHelper addProperties(String owner, Map<String, String> newProperties) {
        Map<String, String> properties = typeToProperties.computeIfAbsent(owner, (key) -> new LinkedHashMap<>());
        properties.putAll(newProperties);
        return this;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return Collections.singletonMap(METADATA_KEY, typeToProperties);
    }
}
