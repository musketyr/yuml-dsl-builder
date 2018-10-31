package cz.orany.yuml

import groovy.transform.CompileStatic
import spock.lang.Specification

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

    void 'create orders diagram'() {
        given:
            Diagram diagram = buildDiagram()

        expect:
            diagram.toString().trim() == EXPECTED_DIAGRAM

            diagram.relationships*.source.every { it in diagram.types }
            diagram.relationships*.destination.every { it in diagram.types }
    }

    @CompileStatic
    private static Diagram buildDiagram() {
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

}
