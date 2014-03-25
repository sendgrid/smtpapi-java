package com.github.sendgrid.smtpapi;

import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;

public class SMTPAPITest {

  SMTPAPI test;

  @Before public void initialize() {
    test = new SMTPAPI();
  }

  @Test public void addToTest() throws JSONException {
    test.addTo("john@doe.com");
    String[] expected = new String[] {"john@doe.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }

  @Test public void addTosTest() throws JSONException {
    String[] testTo = new String[] {"john@doe.com"};
    test.addTo(testTo);
    String[] expected = new String[] {"john@doe.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }

  @Test public void setTosTest() throws JSONException {
    String[] testTo = new String[] {"john@doe.com", "doe@john.com"};
    test.addTo(testTo);
    String[] expected = new String[] {"john@doe.com", "doe@john.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }


}