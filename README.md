[![BuildStatus](https://travis-ci.org/sendgrid/smtpapi-java.svg?branch=master)](https://travis-ci.org/sendgrid/smtpapi-java)

**This module allows you to build SendGrid's SMTP API headers with simplicity.**

# Announcements

All updates to this module is documented in our [CHANGELOG](https://github.com/sendgrid/smtpapi-java/blob/master/CHANGELOG.md).

# Installation

## Prerequisites

- Java version Oracle JDK 7, 8 or OpenJDK 7
- The SendGrid service, starting at the [free level](https://sendgrid.com/free?source=smtpapi-java)

## Install Package

Choose your installation method - Maven w/ Gradle (recommended) or Jar file.

### Install Maven w/ Gradle

```groovy
...
dependencies {
  ...
  compile 'com.sendgrid:smtpapi-java:1.2.0'
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

### Install with Jar File

You can just drop the jar file in. It's a fat jar - it has all the dependencies built in.

[smtpapi-java.jar](https://sendgrid-open-source.s3.amazonaws.com/smtpapi-java/smtpapi-java.jar)

## Dependencies

- See [build.gradle](https://github.com/sendgrid/smtpapi-java/blob/master/build.gradle#L47).

# Quick Start

```java
import com.sendgrid.smtpapi.SMTPAPI;

public class Example {
  public static void main(String[] args) {
    SMTPAPI header = new SMTPAPI();
    header.addTo("test@example.com");
    String headers = header.jsonString();
    System.out.println(headers);
  }
}
```

# Usage

- [SendGrid Docs](https://sendgrid.com/docs/API_Reference/SMTP_API/index.html)
- [Example Code](https://github.com/sendgrid/smtpapi-java/tree/master/examples)

## Roadmap

If you are interested in the future direction of this project, please take a look at our [milestones](https://github.com/sendgrid/smtpapi-java/milestones). We would love to hear your feedback.

## How to Contribute

We encourage contribution to our projects, please see our [CONTRIBUTING](https://github.com/sendgrid/smtpapi-java/blob/master/CONTRIBUTING.md) guide for details.

Quick links:

- [Feature Request](https://github.com/sendgrid/smtpapi-java/blob/master/CONTRIBUTING.md#feature_request)
- [Bug Reports](https://github.com/sendgrid/smtpapi-java/blob/master/CONTRIBUTING.md#submit_a_bug_report)
- [Sign the CLA to Create a Pull Request](https://github.com/sendgrid/smtpapi-java/blob/master/CONTRIBUTING.md#cla)
- [Improvements to the Codebase](https://github.com/sendgrid/smtpapi-java/blob/master/CONTRIBUTING.md#improvements_to_the_codebase)

# About

smtpapi-java is guided and supported by the SendGrid [Developer Experience Team](mailto:dx@sendgrid.com).

smtpapi-java is maintained and funded by SendGrid, Inc. The names and logos for smtpapi-java are trademarks of SendGrid, Inc.

![SendGrid Logo](https://uiux.s3.amazonaws.com/2016-logos/email-logo%402x.png)
