import com.example.Feline;
import com.example.Lion;;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionParametrizedTest {

    @Mock
    private Feline feline;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testCreateLionWithValidSex(String sex, boolean hasMane) throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals(hasMane, lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"null", ""})
    void testCreateLionWithInvalidSex(String invalidSex) {
        assertThrows(Exception.class, () -> new Lion(invalidSex, feline));
    }




    @ParameterizedTest
    @MethodSource("provideFoodTestData")
    void testGetFoodReturnsCorrectFood(List<String> expectedFood) throws Exception {
        Feline mockFeline = mock(Feline.class);
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", mockFeline);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
    }

    static Stream<Arguments> provideFoodTestData() {
        return Stream.of(
                Arguments.of(List.of("Животные", "Птицы", "Рыба")),
                Arguments.of(List.of("Животные", "Птицы")),
                Arguments.of(List.of("Рыба"))
        );
    }
}
