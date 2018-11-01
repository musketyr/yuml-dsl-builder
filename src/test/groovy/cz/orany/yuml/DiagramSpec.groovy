package cz.orany.yuml

import groovy.transform.CompileStatic
import spock.lang.Specification
import spock.lang.Unroll

class DiagramSpec extends Specification {

    private static final String EXPECTED_DIAGRAM = '''
        [note: You can stick notes on diagrams too!{bg:skyblue}]
        [Customer]<>1-orders 0..*>[Order]
        [Order]++*-*>[LineItem]
        [Order]-1>[DeliveryMethod]
        [Order]*-*>[Product]
        [Category]<->[Product]
        [DeliveryMethod]^[National]
        [DeliveryMethod]^[International]
    '''.stripIndent().trim()

    private static final String EXPECTED_DIAGRAM_DIAGRAM = '''
        [note: YUML Diagram Components]
        [Type]<>1..*->[Diagram]
        [Note]<>0..*->[Diagram]
        [Relationship]<>0..*->[Diagram]
        [Type]<>source 1->[Relationship]
        [Type]<>destination 1->[Relationship]
        [RelationshipType]++1->[Relationship]
    '''.stripIndent().trim()

    @Unroll
    void 'create #title diagram'() {
        expect:
            diagram.toString().trim() == expected

            diagram.relationships*.source.every { it in diagram.types }
            diagram.relationships*.destination.every { it in diagram.types }
        where:
            title               | diagram                           | expected
            'orders'            | buildOrderDiagram()               | EXPECTED_DIAGRAM
            'literal diagram'   | buildDiagramDiagramLiteral()      | EXPECTED_DIAGRAM_DIAGRAM
    }

    @CompileStatic
    private static Diagram buildOrderDiagram() {
        Diagram.build {
            note('You can stick notes on diagrams too!', 'skyblue')

            aggregation('Customer', 'Order') {
                source '1'
                destination '0..*', 'orders'
            }

            composition('Order', 'LineItem') {
                source '*'
                destination '*'
            }

            association('Order', 'DeliveryMethod') {
                destination '1'
            }

            association('Order', 'Product') {
                source '*'
                destination '*'
            }

            association('Category', 'Product') {
                bidirectional true
            }

            type 'National' inherits from type 'DeliveryMethod'
            type'International' inherits from type 'DeliveryMethod'
        }
    }

    @CompileStatic
    private static Diagram buildDiagramDiagramLiteral() {
        Diagram.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram' has one to many type 'Type'
            type 'Diagram' has zero to many type 'Note'
            type 'Diagram' has zero to many type 'Relationship'

            type 'Relationship' has one type 'Type' called 'source'
            type 'Relationship' has one type 'Type' called 'destination'
            type 'Relationship' owns one type 'RelationshipType'
        }
    }

}
