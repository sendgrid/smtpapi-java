package com.sendgrid.smtpapi;

import java.util.Map;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class SMTPAPI {

  private static final String VERSION = "1.2.0";

  private JSONObject header = new JSONObject();

  public SMTPAPI()  {

  }

  /**
   * Initialization through the parameterized constructor.
   * @param header: The parameter received.
   */
  public SMTPAPI(JSONObject header) {
    this.header = header;
  }

  /**
   * Returns version.
   * @return VERSION field
   */
  public String getVersion() {
    return VERSION;
  }

  /**
   * Converts JSONArray into an array of strings.
   * @param json: the JSON array received
   * @return an array of strings
   */
  private static String[] toArray(JSONArray json) {
    ArrayList<String> parse = new ArrayList<String>();
    for (int i = 0; i < json.length(); i++) {
      parse.add(json.getString(i));
    }
    return parse.toArray(new String[parse.size()]);
  }

  /**
   * This function is used in the addTos function
   * @param to: the String received
   * @return type SMTPAPI
   * @throws JSONException 
   */
  public SMTPAPI addTo(String to) throws JSONException {
    if (!this.header.has("to")) {
			this.header.put("to", new JSONArray());
		}
		this.header.accumulate("to", to);
    return this;
  }

  /**
   * This function receives a string of arrays and calls its helper 
   * function addTo
   * @param to
   * @return
   * @throws JSONException
   */
  public SMTPAPI addTos(String[] to) throws JSONException {
    for(int i = 0; i < to.length; i ++) {
      addTo(to[i]);
    }
    return this;
  }

  /**
   * This function puts the String to associated with the JSONArray.
   * @param to
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setTos(String[] to) throws JSONException {
    this.header.put("to", new JSONArray(to));
    return this;
  }

  /**
   * Returns the array version of JSONArray.
   * @return toArray of header's JSONArray of "to"
   * @throws JSONException
   */
  public String[] getTos() throws JSONException {
    return SMTPAPI.toArray(this.header.getJSONArray("to"));
  }

  /**
   * Checks if header is null when "sub" passed as a parameter.
   * Puts key value pairs into a new JSON object.
   * @param key: the key that will be added
   * @param val: the value that will be added
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addSubstitution(String key, String val) throws JSONException {
    if (this.header.isNull("sub")) {
      this.header.put("sub", new JSONObject());
    }
		JSONObject subs = this.header.getJSONObject("sub");
		if (!subs.has(key)) {
			subs.put(key, new JSONArray());
		}
    subs.accumulate(key, val);
    return this;
  }

  /**
   * 
   * @param key: the key that will be used in addSubstitution
   * @param val: the value that will be used in addSubstitution
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addSubstitutions(String key, String[] val) throws JSONException {
    for (int i = 0; i < val.length; i++) {
      addSubstitution(key, val[i]);
    }
    return this;
  }

  /**
   * Sets the substitutions by receiving a JSONObject
   * @param subs: the value that is added
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setSubstitutions(JSONObject subs) throws JSONException {
    this.header.put("sub", subs);
    return this;
  }

  /**
   * This function returns the substitutions.
   * @return header's value from key "sub"
   * @throws JSONException
   */
  public JSONObject getSubstitutions() throws JSONException {
    return this.header.getJSONObject("sub");
  }

  /**
   * This function allows unique argument to be added.
   * @param key:The key to insert into map
   * @param val: The value to insert into map
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addUniqueArg(String key, String val) throws JSONException {
    if (this.header.isNull("unique_args")) {
      this.header.put("unique_args", new JSONObject());
    }
    this.header.getJSONObject("unique_args").put(key, val);
    return this;
  }

  /**
   * This functions receives a map consisting of String keys and String values.
   * Then this is put into the header.
   * @param args: the map of type <String, String>
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setUniqueArgs(Map<String, String> args) throws JSONException {
    this.header.put("unique_args", args);
    return this;
  }

  /**
   * This functions allows unique arguments to be set.
   * @param args: the JSONObject to be manipulated
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setUniqueArgs(JSONObject args) throws JSONException {
    this.header.put("unique_args", args);
    return this;
  }

  /**
   * Get the unique arguments from the JSON.
   * @return unique args from JSON
   * @throws JSONException
   */
  public JSONObject getUniqueArgs() throws JSONException {
    return this.header.getJSONObject("unique_args");
  }

  /**
   * Adds a category if it doesn't already have one.
   * @param val: the value to add a category to
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addCategory(String val) throws JSONException {
    if (!this.header.has("category")) {
			this.header.put("category", new JSONArray());
		}
		this.header.accumulate("category", val);
    return this;
  }

  /**
   * Add categories by iterating through the vals array.
   * @param vals: the array that contains entities that categories will be added to
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addCategories(String[] vals) throws JSONException {
    for (int i = 0; i < vals.length; i++) {
      addCategory(vals[i]);
    }
    return this;
  }

  /**
   * This function sets categories into the JSON.
   * @param cat: the array of categories consisting of type String
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setCategories(String[] cat) throws JSONException {
    this.header.put("category", cat);
    return this;
  }

  /**
   * This function returns the categories
   * @return array of strings 
   * @throws JSONException
   */
  public String[] getCategories() throws JSONException {
    return SMTPAPI.toArray(this.header.getJSONArray("category"));
  }

  /**
   * Adds section into the object.
   * @param key: the key to insert into the JSONObject
   * @param val: the val to insert into the JSONObject
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI addSection(String key, String val) throws JSONException {
    if (this.header.isNull("section")) {
      this.header.put("section", new JSONObject());
    }
    this.header.getJSONObject("section").put(key, val);
    return this;
  }

  /**
   * Returns setSections of the new JSONObject
   * @param sec: the map of String keys and String values
   * @return setSections of the new JSONObject
   * @throws JSONException
   */
  public SMTPAPI setSections(Map<String, String> sec) throws JSONException {
    return this.setSections(new JSONObject(sec));
  }


  /**
   * Sets sections with the parameter of a JSONObject called sec
   * @param sec: JSONObject received
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setSections(JSONObject sec) throws JSONException {
    this.header.put("section", sec);
    return this;
  }

  /**
   * Returns the JSONObject for "section"
   * @return JSONObject
   * @throws JSONException
   */
  public JSONObject getSections() throws JSONException {
    return this.header.getJSONObject("section");
  }

  /**
   * This function enables one to add filters depending on the parameters such as the filter,
   * setting, and value.
   * @param filter: type String, used to check if JSONObject filter is null
   * @param setting: type String, acts as the key when adding to map
   * @param val: type String, acts as the value when adding to map
   * @return current object instance 
   * @throws JSONException
   */
  public SMTPAPI addFilter(String filter, String setting, String val) throws JSONException {
    if (this.header.isNull("filters")) {
      this.header.put("filters", new JSONObject());
    }
    if (this.header.getJSONObject("filters").isNull(filter)) {
      this.header.getJSONObject("filters").put(filter, new JSONObject());
      this.header.getJSONObject("filters").getJSONObject(filter).put("settings", new JSONObject());
    }
    this.header.getJSONObject("filters").getJSONObject(filter).getJSONObject("settings").put(setting, val);
    return this;
  }

  /**
   * Checks if there is a filter that exists in JSONObject, if not, add filter.
   * @param filter: the filter to add
   * @param setting: type String, acts as the key when adding to map
   * @param val: type String, acts as the value when adding to map
   * @return current object instance of type SMTPAPI
   * @throws JSONException
   */
  public SMTPAPI addFilter(String filter, String setting, int val) throws JSONException {
    if (this.header.isNull("filters")) {
      this.header.put("filters", new JSONObject());
    }
    if (this.header.getJSONObject("filters").isNull(filter)) {
      this.header.getJSONObject("filters").put(filter, new JSONObject());
      this.header.getJSONObject("filters").getJSONObject(filter).put("settings", new JSONObject());
    }
    this.header.getJSONObject("filters").getJSONObject(filter).getJSONObject("settings").put(setting, val);
    return this;
  }

  /**
   * Allows filters to be set based on JSONObject received.
   * @param filters: paramter received that acts as the value to insert
   * @return current object instance of type SMTPAPI
   * @throws JSONException
   */
  public SMTPAPI setFilters(JSONObject filters) throws JSONException {
    this.header.put("filters", filters);
    return this;
  }

  /**
   * Returns the filters by calling getJSONObject method.
   * @return filters with type JSONObject
   * @throws JSONException
   */
  public JSONObject getFilters() throws JSONException {
    return this.header.getJSONObject("filters");
  }

  /**
   * Sets the ASMGroupID to the val received
   * @param val: the ASMGroupID is set to this
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setASMGroupId(int val) throws JSONException{
    this.header.put("asm_group_id", val);
    return this;
  }

  /**
   * Returns the ASMGroupID by first checking if header has the "asm_group_id".
   * If it doesn't have it, return null.
   * @return the ASMGroupID
   * @throws JSONException
   */
  public Integer getASMGroupId() throws JSONException{
    return this.header.has("asm_group_id") ? this.header.optInt("asm_group_id") : null;
  }

  /**
   * Sets the sendAt to the integer received.
   * @param sendAt: the sendAt is set to this
   * @return current object instance
   * @throws JSONException
   */
  public SMTPAPI setSendAt(int sendAt) throws JSONException {
    this.header.put("send_at", sendAt);
    return this;
  }

  /**
   * Returns the sendAt.
   * @return sendAt of type int
   * @throws JSONException
   */
  public int getSendAt() throws JSONException {
    return this.header.getInt("send_at");

  }

  /**
   * Converts from string to code point array.
   * @param input: the string to convert
   * @return code point array of type int
   */
  private int[] toCodePointArray(String input) {
    int len = input.length();
    int[] codePointArray = new int[input.codePointCount(0, len)];
    for (int i = 0, j = 0; i < len; i = input.offsetByCodePoints(i, 1)) {
      codePointArray[j++] = input.codePointAt(i);
    }
    return codePointArray;
  }

  /**
   * Creates the string based on the StringBuilder class
   * @param input: the String that is converted to a code point array
   * @return String version of input
   */
  private String escapeUnicode(String input) {
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

  /**
   * Calls escapeUnicode method with header in String form.
   * @return the return of escapeUnicode of type String
   */
  public String jsonString() {
    return escapeUnicode(this.header.toString());
  }

  /**
   * Returns the raw JSON String by directly calling toString()
   * @return JSON String
   */
  public String rawJsonString() {
    return this.header.toString();
  }
}
