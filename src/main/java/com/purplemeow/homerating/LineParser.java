package com.purplemeow.homerating;

import java.util.ArrayList;
import java.util.List;


/**
 * Line parser that parses lines like <br/>
 * "name" "address" rvalue <br/>
 * or <br/>
 * "name" "address" <br/>
 * <br/>
 * into an array of tokens with quotes removed.
 * <p>
 * This code handles cases with embedded quotes if the quote is escaped with a backslash (typical way to escape)
 */
class LineParser {

    static List<String> parse(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inToken = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            switch (c) {
                case '\\': {
                    // peek at the next character, if it's a quote then skip ahead
                    if (i < line.length() - 2 && '"' == line.charAt(i + 1)) {
                        sb.append("\"");
                        i += 1;
                        break;
                    }
                }
                case '\"': {
                    if (inToken) {
                        tokens.add(sb.toString().trim());
                        sb = new StringBuilder();
                    }

                    inToken = !inToken;
                    break;
                }
                case '\t':
                case ' ': {
                    if (!inToken) {
                        break;
                    }
                }
                default: {
                    sb.append(c);
                }
            }
        }

        if (sb.length() != 0) {
            String token = sb.toString().trim();
            if (!"".equals(token)) {
                tokens.add(token);
            }
        }

        return tokens;
    }
}
