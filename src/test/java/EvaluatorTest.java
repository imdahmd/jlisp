import com.f1codz.jlisp.core.Evaluator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvaluatorTest {
    private Evaluator evaluator;

    @Test
    public void shouldEvaluateListExpressionAndReturnResult() {
        String lisp = "(+ 1 2)";

        Object o = evaluator.evaluate(lisp);

        assertTrue(o instanceof Integer);
        assertEquals(3, o);
    }
}
