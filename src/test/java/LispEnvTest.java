import com.f1codz.jlisp.environment.LispEnv;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LispEnvTest {
    private LispEnv lispEnv;

    @Before
    public void setUp(){
        lispEnv = new LispEnv();
    }

    @Test
    public void shouldEvaluateListExpressionAndReturnResult_One() {
        String lisp = "(+ 1 2)";

        String result = lispEnv.eval(lisp);

        assertEquals((Double) 3.0, Double.valueOf(result));
    }

    @Test
    public void shouldEvaluateListExpressionAndReturnResult_Two() {
        String lisp = "(+   'hello'    ', '   'world')";

        String result = lispEnv.eval(lisp);

        assertEquals("'hello, world'", result);
    }
}
