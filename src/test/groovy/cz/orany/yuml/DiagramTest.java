package cz.orany.yuml;

import cz.orany.yuml.export.DiagramPrinter;
import cz.orany.yuml.export.YumlDiagramPrinter;
import cz.orany.yuml.model.Diagram;
import org.junit.Assert;
import org.junit.Test;

import static cz.orany.yuml.model.dsl.DiagramKeywords.*;

public class DiagramTest {

    private static final String EXPECTED_TEXT = "[note: You can stick notes on diagrams too!{bg:skyblue}]\n[Customer]<>1-orders 0..*>[Order]\n[Order]++*-*>[LineItem]\n[Order]-1>[DeliveryMethod]\n[Order]*-*>[Product]\n[Category]<->[Product]\n[DeliveryMethod]^[National]\n[DeliveryMethod]^[International]\n";

    @Test
    public void testDiagramJava() {
        DiagramPrinter printer = new YumlDiagramPrinter();
        Diagram diagram = buildDiagram();
        Assert.assertEquals(EXPECTED_TEXT, printer.print(diagram));
    }

    private Diagram buildDiagram() {
        return Diagram.create(d -> {
            d.note("You can stick notes on diagrams too!", "skyblue");

            d.aggregation("Customer", "Order", r -> {
                r.source("1");
                r.destination("0..*", "orders");
            });

            d.composition("Order", "LineItem", r -> {
                r.source("*");
                r.destination("*");
            });

            d.association("Order", "DeliveryMethod", r -> {
                r.destination("1");
            });

            d.association("Order", "Product", r -> {
                r.source("*");
                r.destination("*");
            });

            d.association("Category", "Product", r -> {
                r.bidirectional(true);
            });

            d.type("National").inherits(from).type("DeliveryMethod");
            d.type("International").inherits(from).type("DeliveryMethod");
        });
    }

}
