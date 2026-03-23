package ci553.happyshop.catalogue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void constructorAndGettersReturnExpectedValues() {
        Product p = new Product("0001", "Test product description", "0001.jpg", 9.99, 25);

        assertEquals("0001", p.getProductId());
        assertEquals("Test product description", p.getProductDescription());
        assertEquals("0001.jpg", p.getProductImageName());
        assertEquals(9.99, p.getUnitPrice(), 0.0001);
        assertEquals(25, p.getStockQuantity());
    }
    @Test
    void setOrderedQuantityUpdatesValue() {
        Product p = new Product("0003", "Desc", "0003.jpg", 2.00, 10);
        p.setOrderedQuantity(7);
        assertEquals(7, p.getOrderedQuantity());
    }
    @Test
    void orderedQuantityDefaultsToOne() {
        Product p = new Product("0002", "Desc", "0002.jpg", 1.50, 5);
        assertEquals(1, p.getOrderedQuantity());
    }
    @Test
    void setOrderedQuantityAllowsNegativeValues() {
        // Not ideal behaviour, but this documents what the current implementation does.
        Product p = new Product("0005", "Desc", "0005.jpg", 2.00, 10);
        p.setOrderedQuantity(-3);
        assertEquals(-3, p.getOrderedQuantity());
    }
    @Test
    void compareToReturnsZeroForSameProductId() {
        Product a = new Product("0010", "A", "a.jpg", 1.00, 1);
        Product b = new Product("0010", "B", "b.jpg", 9.99, 99);

        assertEquals(0, a.compareTo(b));
        assertEquals(0, b.compareTo(a));
    }
    @Test
    void compareToSortsByProductIdAscendingLexicographically() {
        Product a = new Product("0001", "A", "a.jpg", 1.00, 1);
        Product b = new Product("0002", "B", "b.jpg", 1.00, 1);

        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
    }
    @Test
    void compareToIsLexicographicNotNumeric() {
        // "10" comes before "2" lexicographically. This documents current behaviour.
        Product p10 = new Product("10", "P10", "10.jpg", 1.00, 1);
        Product p2  = new Product("2", "P2", "2.jpg", 1.00, 1);

        assertTrue(p10.compareTo(p2) < 0);
    }
    @Test
    void productsCanBeSortedUsingCollectionsSort() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("0003", "C", "c.jpg", 1.00, 1));
        products.add(new Product("0001", "A", "a.jpg", 1.00, 1));
        products.add(new Product("0002", "B", "b.jpg", 1.00, 1));

        Collections.sort(products);

        assertEquals("0001", products.get(0).getProductId());
        assertEquals("0002", products.get(1).getProductId());
        assertEquals("0003", products.get(2).getProductId());
    }
    @Test
    void toStringContainsKeyFieldsAndFormatsPriceToTwoDecimals() {
        Product p = new Product("0007", "Some description", "0007.jpg", 3.5, 12);

        String s = p.toString();

        assertTrue(s.contains("Id: 0007"));
        assertTrue(s.contains("£3.50/uint"));     // checks 2dp formatting + existing literal
        assertTrue(s.contains("stock: 12"));
        assertTrue(s.contains("Some description"));
    }



}
