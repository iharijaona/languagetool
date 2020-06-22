/* LanguageTool, a natural language style checker
 * Copyright (C) 2014 Daniel Naber (http://www.danielnaber.de)
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

import org.languagetool.rules.AbstractDateCheckFilter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Malagasy localization of {@link AbstractDateCheckFilter}.
 * @since 2.7
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.forLanguageTag("mg"));
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("alah")) return Calendar.SUNDAY;
    if (day.startsWith("alat")) return Calendar.MONDAY;
    if (day.startsWith("tala")||day.startsWith("tal")) return Calendar.TUESDAY;
    if (day.startsWith("alar")) return Calendar.WEDNESDAY;
    if (day.startsWith("alak")) return Calendar.THURSDAY;
    if (day.startsWith("zoma")||day.startsWith("zom")) return Calendar.FRIDAY;
    if (day.startsWith("sabo")||day.startsWith("sab")) return Calendar.SATURDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @Override
  protected String getDayOfWeek(Calendar date) {
    switch(Calendar.LONG){
      case Calendar.SUNDAY:
        return "Alahady";
      case Calendar.MONDAY:
        return "Alatsinainy";
      case Calendar.TUESDAY:
        return "Talata";
      case Calendar.WEDNESDAY:
        return "Alarobia";
      case Calendar.THURSDAY:
        return "Alakamisy";
      case Calendar.FRIDAY:
        return "Zoma";
      case Calendar.SATURDAY:
        return "Sabotsy";
      default:
        return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("mg"));
    }
    
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if (mon.startsWith("jan")) return 1;
    if (mon.startsWith("feb")) return 2;
    if (mon.startsWith("mar")) return 3;
    if (mon.startsWith("apr")) return 4;
    if (mon.startsWith("mey")) return 5;
    if (mon.startsWith("jon")) return 6;
    if (mon.startsWith("jol")) return 7;
    if (mon.startsWith("aog")) return 8;
    if (mon.startsWith("sep")) return 9;
    if (mon.startsWith("okt")) return 10;
    if (mon.startsWith("nov")) return 11;
    if (mon.startsWith("des")) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }

}
