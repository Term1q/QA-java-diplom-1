package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredientSauce;
    @Mock
    private Ingredient ingredientFilling;

    @Before
    public void setUp() {
        burger = new Burger();
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200F);
        when(ingredientSauce.getName()).thenReturn("hot sauce");
        when(ingredientSauce.getPrice()).thenReturn(100F);
        when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientFilling.getName()).thenReturn("sausage");
        when(ingredientFilling.getPrice()).thenReturn(300F);
        when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientSauce);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientSauce, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientSauce, burger.ingredients.get(1));
        assertEquals(ingredientFilling, burger.ingredients.get(0));
    }

    @Test
    public void testGetPrice() {

        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        float expectedPrice = bun.getPrice() * 2 + ingredientSauce.getPrice() + ingredientFilling.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        String expectedReceipt = String.format(
                        "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bun.getName(),
                ingredientSauce.getType().toString().toLowerCase(), ingredientSauce.getName(),
                ingredientFilling.getType().toString().toLowerCase(), ingredientFilling.getName(),
                bun.getName(),
                burger.getPrice());
        assertEquals(expectedReceipt, burger.getReceipt());

    }
}
