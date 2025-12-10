import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline feline;

    @Test
    void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(1);

        String sex = "Самка";
        Lion lion = new Lion(sex, feline);

        int result = lion.getKittens();

        assertEquals(1, result);
        verify(feline).getKittens();
    }

    @Test
    void testDoesHaveManeReturnsCorrectValue() throws Exception {

        Lion maleLion = new Lion("Самец", feline);
        assertTrue(maleLion.doesHaveMane());

        Lion femaleLion = new Lion("Самка", feline);
        assertFalse(femaleLion.doesHaveMane());
    }
}