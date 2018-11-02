package cz.orany.yuml.export;

import cz.orany.yuml.model.Diagram;
import cz.orany.yuml.model.Note;
import cz.orany.yuml.model.Relationship;
import cz.orany.yuml.model.Type;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class YumlDiagramPrinter implements DiagramPrinter {

    public String print(Diagram diagram) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (Note note : diagram.getNotes()) {
            printWriter.println(print(note));
        }

        Map<String, Type> orphanTypes = diagram.getTypes().stream().collect(toMap(Type::getName, identity()));

        for (Relationship relationship : diagram.getRelationships()) {
            orphanTypes.remove(relationship.getSource().getName());
            orphanTypes.remove(relationship.getDestination().getName());

            printWriter.println(print(relationship));
        }

        for (Type type : orphanTypes.values()) {
            printWriter.println(print(type));
        }

        return stringWriter.toString();
    }

    private String print(Type type) {
        return String.format("[%s]", type.getName());
    }

    private String print(Relationship r) {
        switch (r.getType()) {
            case ASSOCIATION:
                return String.format("%s%s%s-%s>%s",
                    print(r.getSource()),
                    r.isBidirectional() ? "<" : "",
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(r.getDestination())
                );
            case AGGREGATION:
                return String.format("%s<>%s-%s>%s",
                    print(r.getSource()),
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(r.getDestination())
                );
            case COMPOSITION:
                return String.format("%s++%s-%s>%s",
                    print(r.getSource()),
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(r.getDestination())
                );
            case INHERITANCE:
                return String.format("%s^%s", print(r.getDestination()), print(r.getSource()));
        }
        throw new IllegalArgumentException("Unknown type of relationship: " + r.getType());
    }

    private String print(Note note) {
        if (note.getColor() != null) {
            return String.format("[note: %s{bg:%s}]", note.getText(), note.getColor());
        }

        return String.format("[note: %s]", note.getText());
    }

    private static String cardinalityAndTitle(String cardinality, String title) {
        if (cardinality != null) {
            return title != null ? (title + " " + cardinality) : cardinality;
        }

        return title != null ? title : "";
    }
}
