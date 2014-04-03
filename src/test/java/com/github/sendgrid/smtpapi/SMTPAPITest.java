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

  @Test public void testAddTo() throws JSONException {
    test.addTo("john@doe.com");
    String[] expected = new String[] {"john@doe.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }

  @Test public void testAddTos() throws JSONException {
    String[] testTo = new String[] {"john@doe.com"};
    test.addTo(testTo);
    String[] expected = new String[] {"john@doe.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }

  @Test public void testSetTos() throws JSONException {
    String[] testTo = new String[] {"john@doe.com", "doe@john.com"};
    test.addTo(testTo);
    String[] expected = new String[] {"john@doe.com", "doe@john.com"};
    JSONAssert.assertEquals(new JSONArray(expected), test.getTos(), false);
  }

  @Test public void testAddSubstitution() throws JSONException {
    test.addSubstitution("-name-", "John");
    JSONObject testObject = test.getSubstitutions();
    JSONObject expected = new JSONObject().append("-name-", "John");
    JSONAssert.assertEquals(expected, testObject, false);
  }

  @Test public void testAddSubstitutions() throws JSONException {
    test.addSubstitution("-name-", new String[]{"John", "Doe"});
    JSONObject testObject = test.getSubstitutions();
    JSONObject expected = new JSONObject().put("-name-", new String[]{"John", "Doe"});
    JSONAssert.assertEquals(expected.toString(), testObject.toString(), false);
  }

  @Test public void testAddUniqueArg() throws JSONException {
    test.addUniqueArg("key", "value");
    JSONObject expected = new JSONObject().put("key", "value");
    JSONAssert.assertEquals(expected, test.getUniqueArgs(), false);
  }

  @Test public void testAddCategory() throws JSONException {
    test.addCategory("test");
    JSONArray expected = new JSONArray(new String[]{"test"});
    JSONAssert.assertEquals(expected, test.getCategories(), false);
  }

  @Test public void testAddCategories() throws JSONException {
    test.addCategory(new String[]{"test", "test2"});
    JSONArray expected = new JSONArray(new String[]{"test", "test2"});
    JSONAssert.assertEquals(expected, test.getCategories(), false);
  }

  @Test public void testAddSection() throws JSONException {
    test.addSection("test", "value");
    JSONObject testObject = test.getSections();
    JSONObject expected = new JSONObject().put("test", "value");
    JSONAssert.assertEquals(expected, testObject, false);
  }

  @Test public void testAddFilter() throws JSONException {
    test.addFilter("test", "setting", "value");
    String testObject = test.getFilters().toString();
    JSONObject expected = new JSONObject();
    expected.put("test", new JSONObject().put("settings", new JSONObject().put("setting", "value")));
    JSONAssert.assertEquals(expected.toString(), testObject, false);
  }

}
