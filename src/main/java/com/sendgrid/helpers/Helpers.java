package com.sendgrid.smtpapi;

import java.util.ArrayList;

public static class Helpers {

  public static String[] toArray(JSONArray json) {
    ArrayList<String> parse = new ArrayList<String>();
    for (int i = 0; i < json.length(); i++) {
      parse.add(json.getString(i));
    }
    return parse.toArray(new String[parse.size()]);
  }

  // convert from string to code point array
  public static int[] toCodePointArray(String input) {
    int len = input.length();
    int[] codePointArray = new int[input.codePointCount(0, len)];
    for (int i = 0, j = 0; i < len; i = input.offsetByCodePoints(i, 1)) {
      codePointArray[j++] = input.codePointAt(i);
    }
    return codePointArray;
  }

  public static String escapeUnicode(String input) {
    StringBuilder sb = new StringBuilder();
    int[] codePointArray = toCodePointArray(input);
    int len = codePointArray.length;
    for (int i = 0; i < len; i++) {
      if (codePointArray[i] > 65535) {
        // surrogate pair
        int hi = (codePointArray[i] - 0x10000) / 0x400 + 0xD800;
        int lo = (codePointArray[i] - 0x10000) % 0x400 + 0xDC00;
        sb.append(String.format("\\u%04x\\u%04x", hi, lo));
      } else if (codePointArray[i] > 127) {
        sb.append(String.format("\\u%04x",codePointArray[i]));
      } else {
        sb.append(String.format("%c", codePointArray[i]));
      }
    }
    return sb.toString();
  }
}
