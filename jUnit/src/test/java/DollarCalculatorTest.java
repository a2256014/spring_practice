import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void dollarTest(){
        Calculator calculator = new Calculator();

        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        calculator.init(dollarCalculator);
        System.out.println(calculator.sum(10,20));
        Assertions.assertEquals(90000,calculator.sum(10,20));
    }
}
