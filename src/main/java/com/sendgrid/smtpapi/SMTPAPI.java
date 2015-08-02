package com.sendgrid.smtpapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SMTPAPI {

	private static final String VERSION = "1.2.0";

	private JSONObject header = new JSONObject();

	private static final String TO_FIELD_NAME = "to";

	private static final String SUB_FILED_NAME = "sub";

	private static final String CATEGORY_FIELD = "category";

	private static final String SECTION_OBJECT = "section";

	private static final String SETTINGS_OBJECT = "settings";

	private static final String UNIQUE_ARGS_OBJECT = "unique_args";

	private static final String FILTERS_OBJECT = "filters";

	private static final String ASM_GROUP_ID_FIELD = "asm_group_id";

	private static final String SEND_AT_FIELD = "send_at";

	public SMTPAPI() {

	}

	public SMTPAPI(JSONObject header) {
		this.header = header;
	}

	public String getVersion() {
		return VERSION;
	}

	private static String[] toArray(JSONArray json) {
		final List<String> parse = new ArrayList<String>();
		for (int i = 0; i < json.length(); i++) {
			parse.add(json.getString(i));
		}
		return parse.toArray(new String[parse.size()]);
	}

	public SMTPAPI addTo(String to) throws SMTPAPIException {
		try {
			if (!this.header.has(TO_FIELD_NAME)) {
				this.header.put(TO_FIELD_NAME, new JSONArray());
			}
			this.header.accumulate(TO_FIELD_NAME, to);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addTos(String[] to) throws SMTPAPIException {
		for (final String toElement : to) {
			addTo(toElement);
		}
		return this;
	}

	public SMTPAPI setTos(String[] to) throws SMTPAPIException {
		try {
			this.header.put(TO_FIELD_NAME, new JSONArray(to));
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public String[] getTos() throws SMTPAPIException {
		try {
			return SMTPAPI.toArray(this.header.getJSONArray(TO_FIELD_NAME));
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addSubstitution(String key, String val) throws SMTPAPIException {
		try {
			if (this.header.isNull(SUB_FILED_NAME)) {
				this.header.put(SUB_FILED_NAME, new JSONObject());
			}
			final JSONObject subs = this.header.getJSONObject(SUB_FILED_NAME);
			if (!subs.has(key)) {
				subs.put(key, new JSONArray());
			}
			subs.accumulate(key, val);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
		return this;
	}

	public SMTPAPI addSubstitutions(String key, String[] val) throws SMTPAPIException {
		for (final String value : val) {
			addSubstitution(key, value);
		}
		return this;
	}

	public SMTPAPI setSubstitutions(JSONObject subs) throws SMTPAPIException {
		try {
			this.header.put(SUB_FILED_NAME, subs);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
		return this;
	}

	public JSONObject getSubstitutions() throws SMTPAPIException {
		try {
			return header.getJSONObject(SUB_FILED_NAME);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addUniqueArg(String key, String val) throws SMTPAPIException {
		try {
			if (this.header.isNull(UNIQUE_ARGS_OBJECT)) {
				this.header.put(UNIQUE_ARGS_OBJECT, new JSONObject());
			}
			this.header.getJSONObject(UNIQUE_ARGS_OBJECT).put(key, val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setUniqueArgs(Map<String, String> args) throws SMTPAPIException {
		try {
			this.header.put(UNIQUE_ARGS_OBJECT, args);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setUniqueArgs(JSONObject args) throws SMTPAPIException {
		try {
			this.header.put(UNIQUE_ARGS_OBJECT, args);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public JSONObject getUniqueArgs() throws SMTPAPIException {
		try {
			return this.header.getJSONObject(UNIQUE_ARGS_OBJECT);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addCategory(String val) throws SMTPAPIException {
		try {
			if (!this.header.has(CATEGORY_FIELD)) {
				this.header.put(CATEGORY_FIELD, new JSONArray());
			}
			this.header.accumulate(CATEGORY_FIELD, val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addCategories(String[] vals) throws SMTPAPIException {
		try {
			for (final String value : vals) {
				addCategory(value);
			}
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setCategories(String[] cat) throws SMTPAPIException {
		try {
			this.header.put(CATEGORY_FIELD, cat);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public String[] getCategories() throws SMTPAPIException {
		try {
			return SMTPAPI.toArray(this.header.getJSONArray(CATEGORY_FIELD));
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addSection(String key, String val) throws SMTPAPIException {
		try {
			if (this.header.isNull(SECTION_OBJECT)) {
				this.header.put(SECTION_OBJECT, new JSONObject());
			}
			this.header.getJSONObject(SECTION_OBJECT).put(key, val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setSections(Map<String, String> sec) throws SMTPAPIException {
		try {
			return this.setSections(new JSONObject(sec));
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setSections(JSONObject sec) throws SMTPAPIException {
		try {
			this.header.put(SECTION_OBJECT, sec);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public JSONObject getSections() throws SMTPAPIException {
		try {
			return this.header.getJSONObject(SECTION_OBJECT);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addFilter(String filter, String setting, String val) throws SMTPAPIException {
		try {
			if (this.header.isNull(FILTERS_OBJECT)) {
				this.header.put(FILTERS_OBJECT, new JSONObject());
			}
			if (this.header.getJSONObject(FILTERS_OBJECT).isNull(filter)) {
				this.header.getJSONObject(FILTERS_OBJECT).put(filter, new JSONObject());
				this.header.getJSONObject(FILTERS_OBJECT).getJSONObject(filter).put(SETTINGS_OBJECT, new JSONObject());
			}
			this.header.getJSONObject(FILTERS_OBJECT).getJSONObject(filter).getJSONObject(SETTINGS_OBJECT).put(setting,
					val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI addFilter(String filter, String setting, int val) throws SMTPAPIException {
		try {
			if (this.header.isNull(FILTERS_OBJECT)) {
				this.header.put(FILTERS_OBJECT, new JSONObject());
			}
			if (this.header.getJSONObject(FILTERS_OBJECT).isNull(filter)) {
				this.header.getJSONObject(FILTERS_OBJECT).put(filter, new JSONObject());
				this.header.getJSONObject(FILTERS_OBJECT).getJSONObject(filter).put(SETTINGS_OBJECT, new JSONObject());
			}
			this.header.getJSONObject(FILTERS_OBJECT).getJSONObject(filter).getJSONObject(SETTINGS_OBJECT).put(setting,
					val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setFilters(JSONObject filters) throws SMTPAPIException {
		try {
			this.header.put(FILTERS_OBJECT, filters);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public JSONObject getFilters() throws SMTPAPIException {
		try {
			return this.header.getJSONObject(FILTERS_OBJECT);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setASMGroupId(int val) throws SMTPAPIException {
		try {
			this.header.put(ASM_GROUP_ID_FIELD, val);
			return this;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public Integer getASMGroupId() throws SMTPAPIException {
		try {
			return header.has(ASM_GROUP_ID_FIELD) ? this.header.optInt(ASM_GROUP_ID_FIELD) : null;
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	public SMTPAPI setSendAt(int sendAt) throws SMTPAPIException {
		try {
			this.header.put(SEND_AT_FIELD, sendAt);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
		return this;
	}

	public int getSendAt() throws SMTPAPIException {
		try {
			return this.header.getInt(SEND_AT_FIELD);
		} catch (final JSONException e) {
			throw new SMTPAPIException(e);
		}
	}

	// convert from string to code point array
	private int[] toCodePointArray(String input) {
		int len = input.length();
		int[] codePointArray = new int[input.codePointCount(0, len)];
		for (int i = 0, j = 0; i < len; i = input.offsetByCodePoints(i, 1)) {
			codePointArray[j++] = input.codePointAt(i);
		}
		return codePointArray;
	}

	private String escapeUnicode(final String input) {
		final StringBuilder sb = new StringBuilder();
		final int[] codePointArray = toCodePointArray(input);
		final int len = codePointArray.length;
		for (int i = 0; i < len; i++) {
			if (codePointArray[i] > 65535) {
				// surrogate pair
				int hi = (codePointArray[i] - 0x10000) / 0x400 + 0xD800;
				int lo = (codePointArray[i] - 0x10000) % 0x400 + 0xDC00;
				sb.append(String.format("\\u%04x\\u%04x", hi, lo));
			} else if (codePointArray[i] > 127) {
				sb.append(String.format("\\u%04x", codePointArray[i]));
			} else {
				sb.append(String.format("%c", codePointArray[i]));
			}
		}
		return sb.toString();
	}

	public String jsonString() {
		return escapeUnicode(this.header.toString());
	}

	public String rawJsonString() {
		return this.header.toString();
	}
}
