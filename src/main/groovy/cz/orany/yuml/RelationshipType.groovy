package cz.orany.yuml

import groovy.transform.CompileStatic

@CompileStatic
enum RelationshipType {
    ASSOCIATION {
        @Override
        def toString(Relationship r) {
            return "${r.source}${r.bidirectional ? '<' : ''}${cardinalityAndTitle(r.sourceCardinality, r.sourceTitle)}-${cardinalityAndTitle(r.destinationCardinality, r.destinationTitle)}>${r.destination}"
        }
    },
    AGGREGATION {
        @Override
        def toString(Relationship r) {
            return "${r.source}<>${cardinalityAndTitle(r.sourceCardinality, r.sourceTitle)}-${cardinalityAndTitle(r.destinationCardinality, r.destinationTitle)}>${r.destination}"
        }
    },
    COMPOSITION {
        @Override
        def toString(Relationship r) {
            return "${r.source}++${cardinalityAndTitle(r.sourceCardinality, r.sourceTitle)}-${cardinalityAndTitle(r.destinationCardinality, r.destinationTitle)}>${r.destination}"
        }
    },
    INHERITANCE {
        @Override
        def toString(Relationship r) {
            return "${r.destination}^${r.source}"
        }
    },

    abstract toString(Relationship r)

    private static String cardinalityAndTitle(String cardinality, String title) {
        if (cardinality) {
            return title ? "$title $cardinality" : cardinality
        }
        return title ? title : ''
    }
}
