package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300},
                {"", 0}
        };
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0);
    }
}

