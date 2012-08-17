package com.f1codz.jlisp.core.parser;

import static com.f1codz.jlisp.util.LispExpressionUtils.LISP_END;
import static com.f1codz.jlisp.util.LispExpressionUtils.LISP_START;

class SublispParser implements UnitParser {
        public boolean fitsFor(char currentChar) {
            return currentChar == LISP_START;
        }

        public String parse(Lisp lisp) {
            char currentChar = lisp.currentChar();

            StringBuilder result = new StringBuilder();
            result.append(currentChar);

            int countSubSubLisps = 0;
            while (true) {
                char nextChar = lisp.nextChar();
                result.append(nextChar);

                if (nextChar == LISP_END && countSubSubLisps == 0)
                    break;

                if (nextChar == LISP_START)
                    countSubSubLisps++;
                else if (nextChar == LISP_END)
                    countSubSubLisps--;
            }

            return result.toString();
        }
    }
