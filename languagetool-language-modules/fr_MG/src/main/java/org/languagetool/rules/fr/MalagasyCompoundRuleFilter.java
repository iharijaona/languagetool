/* LanguageTool, a natural language style checker
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.fr;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.patterns.RuleFilter;

/**
 * A rule that matches spaces before ?,:,; and ! (required for correct French
 * punctuation).
 *
 * @see <a href="http://unicode.org/udhr/n/notes_fra.html">http://unicode.org/udhr/n/notes_fra.html</a>
 *
 * @author Marcin Miłkowski
 */
public class MalagasyCompoundRuleFilter extends RuleFilter {
    
    private static final Pattern FIRST_TOKEN = Pattern.compile(".+ka$|.+tra$|.+na$", Pattern.CASE_INSENSITIVE);
    private static final Pattern SECOND_TOKEN = Pattern.compile("^[bdfghjklmnprstvz].+", Pattern.CASE_INSENSITIVE);

    private static final Map<String, String> SECOND_TOKEN_INITIALS = new HashMap<String, String>(){{
        put("f", "p");
        put("h", "k");
        put("l", "d");
        put("r", "dr");
        put("s", "ts");
        put("v", "b");
        put("z", "j");
    }};

  /**
   * @param args a map with values for {@code firstToken}, {@code secondToken}
   */
  @Override
  public RuleMatch acceptRuleMatch(RuleMatch match, Map<String, String> args, int patternTokenPos, AnalyzedTokenReadings[] patternTokens) {
   
    String firstToken = getRequired("firstToken", args);
    String secondToken = getRequired("secondToken", args);
    
    firstToken = firstToken.toLowerCase();
    secondToken = secondToken.toLowerCase();
    
    
    if(FIRST_TOKEN.matcher(firstToken).matches() && SECOND_TOKEN.matcher(secondToken).matches()){
        
        for(Map.Entry<String, String> _rule : SECOND_TOKEN_INITIALS.entrySet()){
            if(secondToken.startsWith(_rule.getKey())){
                secondToken = secondToken.replaceAll("^"+_rule.getKey(), _rule.getValue());
                break;
            }
        }
        
        if(firstToken.endsWith("ka") || firstToken.endsWith("tra")){
            firstToken = firstToken.replaceAll("ka$|tra$", "-");
        }
        else if(firstToken.endsWith("na")){
            if(secondToken.startsWith("b") || secondToken.startsWith("p")){
                firstToken = firstToken.replaceAll("na$", "m-");
            }
            else if(secondToken.startsWith("m") || secondToken.startsWith("n")){
                firstToken = firstToken.replaceAll("na$", "-");
            }
            else{
                firstToken = firstToken.replaceAll("a$", "-");
            }
            
        }
        
        String message = match.getMessage().replace("{compoundWord}", firstToken+secondToken);
        RuleMatch ruleMatch = new RuleMatch(match.getRule(), match.getSentence(), match.getFromPos(), match.getToPos(), message, match.getShortMessage());
        ruleMatch.setType(match.getType());
        return ruleMatch;
    } else {
        return null;
    }
  }
  
  protected String getRequired(String key, Map<String, String> map) {
    String result = map.get(key);
    if (result == null) {
      throw new IllegalArgumentException("Missing key '" + key + "'");
    }
    return result;
  }

}
