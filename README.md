# SMTPAPI for Java

This module will let you build SendGrid's SMTP API headers with simplicity.

[![BuildStatus](https://travis-ci.org/sendgrid/smtpapi-java.png?branch=master)](https://travis-ci.org/sendgrid/smtpapi-java)

## Installing

Choose your installation method - Maven w/ Gradle (recommended) or Jar file.

### Maven w/ Gradle

```groovy
...
dependencies {
  ...
  compile 'com.sendgrid:smtpapi-java:1.0.0'
}

repositories {
  mavenCentral()
}
...
```

Then import the library - in the file appropriate to your Java project.

```java
import com.sendgrid.smtpapi.SMTPAPI;
```

### Jar file

You can just drop the jar file in. It's a fat jar - it has all the dependencies built in.

[smtpapi-java.jar](https://sendgrid-open-source.s3.amazonaws.com/smtpapi-java/smtpapi-java.jar)

## Examples

### Create headers

```java
import com.sendgrid.smtpapi.SMTPAPI;
SMTPAPI header = new SMTPAPI();
```

### [To](http://sendgrid.com/docs/API_Reference/SMTP_API/index.html)
```java
header.addTo("email@email.com");
// or
header.addTo(["email@email.com"]);
// or
header.setTos(["email@email.com"]);

String[] tos = header.getTos();
```

### [Substitutions](http://sendgrid.com/docs/API_Reference/SMTP_API/substitution_tags.html)

```java
header.addSubstitution("key", "value");

JSONObject subs = header.getSubstitutions();
```

### [Unique Arguments](http://sendgrid.com/docs/API_Reference/SMTP_API/unique_arguments.html)

```java
header.addUniqueAarg("key", "value");
// or
Map map = new HashMap<String, String>();
map.put("unique", "value");
header.setUniqueArgs(map);
// or
JSONObject map = new JSONObject();
map.put("unique", "value");
header.setUniqueArgs(map);

JSONObject args = header.getUniqueArgs();
```
### [Categories](http://sendgrid.com/docs/API_Reference/SMTP_API/categories.html)

```java
header.addCategory("category");
// or
header.addCategory(["categories"]);
// or
header.setCategories(["category1", "category2"]);

String[] cats = header.getCategories();
```

### [Sections](http://sendgrid.com/docs/API_Reference/SMTP_API/section_tags.html)

```java
header.addSection("key", "section");
// or
Map newSec = new HashMap();
newSec.put("-section-", "value");
header.setSections(newSec);
// or
JSONObject newSec = new JSONObject();
newSec.put("-section-", "value");
header.setSections(newSec);

JSONObject sections = header.getSections();
```

### [Filters](http://sendgrid.com/docs/API_Reference/SMTP_API/apps.html)

```java
header.addFilter("filter", "setting", "value");
header.addFilter("filter", "setting", 1);

JSONObject filters = header.getFilters();
```

### [ASM Group Id](https://sendgrid.com/docs/User_Guide/advanced_suppression_manager.html)

```java
header.setASMGroupId(1);

Integer groupId = header.getASMGroupId();
```

### [Scheduling](https://sendgrid.com/docs/API_Reference/SMTP_API/scheduling_parameters.html)

```java
header.setSendAt(1416427645)

int sendAt = header.getSendAt();
```

### Get Headers

```java
String headers = header.jsonString();
```

If you need the unescaped JSON string.
```java
String rawJson = header.rawJsonString();
```

## Running Tests

```
./gradlew check
```

## Publishing to Maven

This only works if you have the correct permissions - for admins only basically.

```
cp gradle.properties.example gradle.properties
```

Edit the contents of gradle.properties with your credentials.

```
./gradlew
./gradlew uploadArchives
```

Login to [Sonatype](https://oss.sonatype.org/index.html#stagingRepositories).

Go to [staging repositories page](https://oss.sonatype.org/index.html#stagingRepositories).

Click 'Close' with the archive selected.

![](https://raw.githubusercontent.com/sendgrid/sendgrid-java/master/maven-help.png)

Wait a few minutes, and refresh the staging repositories page.

Check the box for the SendGrid repo again and this time click 'Release'.

You're all done.

[Further help](https://github.com/sendgrid/sendgrid-java/pull/15).


## MIT
