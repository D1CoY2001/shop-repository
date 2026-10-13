import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();

        Product first = new Product(1, "Phone", 50_000);
        Product second = new Product(2, "Book", 1_000);

        repo.add(first);
        repo.add(second);

        repo.removeById(1);

        Product[] expected = {second};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingMissingProduct() {
        ShopRepository repo = new ShopRepository();

        Product first = new Product(1, "Phone", 50_000);
        repo.add(first);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repo.removeById(10)
        );

        assertEquals("Element with id: 10 not found", exception.getMessage());
    }
}