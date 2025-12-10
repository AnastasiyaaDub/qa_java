import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline mockFeline;

    @Test
    void testGetSound() {
        Cat cat = new Cat(mockFeline);
        String sound = cat.getSound();
        assertEquals("Мяу", sound);
    }
    @Test
    void constructorSetsPredatorField() {

        Cat cat = new Cat(mockFeline);
        assertDoesNotThrow(() -> cat.getFood());
    }
    @Test
    void getFoodReturnsSameListAsFelineEatMeat() throws Exception {

        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(mockFeline);

        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood,
                "Cat.getFood() должен возвращать тот же список, что и Feline.eatMeat()");
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test
    void getFoodPropagatesExceptionFromFeline() throws Exception {
        Exception expectedException = new Exception("Ошибка получения еды");
        when(mockFeline.eatMeat()).thenThrow(expectedException);

        Cat cat = new Cat(mockFeline);

        Exception actualException = assertThrows(Exception.class, cat::getFood);
        assertEquals(expectedException, actualException,
                "Cat.getFood() должен пробрасывать исключение из Feline.eatMeat()");
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test
    void getFoodWithEmptyList() throws Exception {

        List<String> emptyList = List.of();
        when(mockFeline.eatMeat()).thenReturn(emptyList);

        Cat cat = new Cat(mockFeline);
        List<String> result = cat.getFood();

        assertTrue(result.isEmpty(), "Должен возвращаться пустой список");
        verify(mockFeline, times(1)).eatMeat();
    }
}
