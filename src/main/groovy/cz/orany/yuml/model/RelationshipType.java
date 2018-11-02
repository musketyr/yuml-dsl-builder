package cz.orany.yuml.model;

import org.codehaus.groovy.runtime.StringGroovyMethods;

public enum RelationshipType {

    ASSOCIATION {
        @Override
        public String toString(Relationship r) {
            return String.format("%s%s%s-%s>%s", r.getSource(), r.isBidirectional() ? "<" : "", cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()), cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()), r.getDestination());
        }
    }, AGGREGATION {
        @Override
        public String toString(Relationship r) {
            return String.format("%s<>%s-%s>%s", r.getSource(), cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()), cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()), r.getDestination());
        }
    }, COMPOSITION {
        @Override
        public String toString(Relationship r) {
            return String.format("%s++%s-%s>%s", r.getSource(), cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()), cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()), r.getDestination());
        }
    }, INHERITANCE {
        @Override
        public String toString(Relationship r) {
            return String.format("%s^%s", r.getDestination(), r.getSource());
        }
    };

    public abstract String toString(Relationship r);

    private static String cardinalityAndTitle(String cardinality, String title) {
        if (StringGroovyMethods.asBoolean(cardinality)) {
            return StringGroovyMethods.asBoolean(title) ? (title + " " + cardinality) : cardinality;
        }

        return StringGroovyMethods.asBoolean(title) ? title : "";
    }

}
