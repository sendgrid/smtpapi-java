package com.sendgrid.smtpapi;

import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SMTPAPITest {

  SMTPAPI test;

  @Before public void initialize() {
    test = new SMTPAPI();
  }

  @Test public void testVersion() {
    Assert.assertEquals("1.1.1", test.getVersion());
  }

  @Test public void testBuildGradleVersion() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("./build.gradle"));
      String line = br.readLine();
      String regex = "version\\s*=\\s*'" + test.getVersion() + "'";

      while (line != null) {
        if (line.matches(regex)) {
          br.close();
          return;
        }
        line = br.readLine();
      }
      br.close();
      fail("build.gradle version does not match");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  @Test public void testAddTo() throws JSONException {
    test.addTo("john@doe.com");
    String[] expected = new String[] {"john@doe.com"};
    Assert.assertArrayEquals(expected, test.getTos());
  }

  @Test public void testAddTos() throws JSONException {
    String[] expected = new String[] {"john@doe.com"};
    test.addTos(expected);
    Assert.assertArrayEquals(expected, test.getTos());
  }

  @Test public void testSetTos() throws JSONException {
    String[] expected = new String[] {"john@doe.com", "doe@john.com"};
    test.setTos(expected);
    Assert.assertArrayEquals(expected, test.getTos());
  }

  @Test public void testAddSubstitution() throws JSONException {
    test.addSubstitution("-name-", "John");
    JSONObject testObject = test.getSubstitutions();
    JSONObject expected = new JSONObject().put("-name-", new JSONArray().put("John"));
    JSONAssert.assertEquals(expected, testObject, false);
  }

  @Test public void testAddSubstitutions() throws JSONException {
    test.addSubstitutions("-name-", new String[]{"John", "Doe"});
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
    String[] expected = new String[]{"test"};
    Assert.assertArrayEquals(expected, test.getCategories());
  }

  @Test public void testAddCategoryUnicode() throws JSONException {
    test.addCategory("カテゴリUñicode");
    test.addCategory("カテゴリ2Unicode");
    String[] expected = new String[]{"カテゴリUñicode", "カテゴリ2Unicode"};
    Assert.assertArrayEquals(expected, test.getCategories());
  }

  @Test public void testJsonString() {
    test.addCategory("カテゴリUñicode");
    test.addCategory("カテゴリ2Unicode");
    //test.addCategory("𝄞");
    test.addCategory("鼖");
    String expected = "{\"category\":[\"\\u30ab\\u30c6\\u30b4\\u30eaU\\u00f1icode\",\"\\u30ab\\u30c6\\u30b4\\u30ea2Unicode\",\"\\ud87e\\ude1b\"]}";
    Assert.assertEquals(expected, test.jsonString());
  }

  @Test public void testRawJsonString() {
    test.addCategory("カテゴリUñicode");
    test.addCategory("カテゴリ2Unicode");
    test.addCategory("鼖");
    String expected = "{\"category\":[\"カテゴリUñicode\",\"カテゴリ2Unicode\",\"鼖\"]}";
    Assert.assertEquals(expected, test.rawJsonString());
  }

  @Test public void testAddCategories() throws JSONException {
    String[] expected = new String[]{"test", "test2"};
    test.addCategories(expected);
    Assert.assertArrayEquals(expected, test.getCategories());
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

  @Test public void testSetASMGroupId() throws JSONException {
    test.setASMGroupId(1);
    Assert.assertEquals(new Integer(1), test.getASMGroupId());
  }

  @Test public void testSetSendAt() throws JSONException {
    int expected = 12345;
    test.setSendAt(expected);
    Assert.assertEquals(expected, test.getSendAt());
  }

}
