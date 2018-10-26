Quick Start:
import com.sendgrid.smtpapi.SMTPAPI;

public class Example {
  public static void main(String[] args) {
    SMTPAPI header = new SMTPAPI();
    header.addTo("test@example.com");
    String headers = header.jsonString();
    System.out.println(headers);
  }
}

Usage:
- SendGrid Docs
- Example Code

-SendGrid Docs:

  Getting started building
      Limitations
  Customizing Your Send
      Scheduling Your Send
      Substitution Tags
      Section Tags
      Suppression Groups
      Categories
      Unique Arguments
      
Getting started building:

SMTP works by passing a JSON string with as many SMTP objects as you want to SendGrid. To do this, add the JSON string to your message under a header named “X-SMTPAPI” like this:

{
  "to": [
    "example@example.com",
    "example@example.com"
  ],
  "sub": {
    "%name%": [
      "Ben",
      "Joe"
    ],
    "%role%": [
      "%sellerSection%",
      "%buyerSection%"
    ]
  },
  "section": {
    "%sellerSection%": "Seller information for: %name%",
    "%buyerSection%": "Buyer information for: %name%"
  },
  "category": [
    "Orders"
  ],
  "unique_args": {
    "orderNumber": "12345",
    "eventID": "6789"
  },
  "filters": {
    "footer": {
      "settings": {
        "enable": 1,
        "text/plain": "Thank you for your business"
      }
    }
  },
  "send_at": 1409348513
}

Limitations:

-There is a hard limit of 10,000 addresses in a multiple recipient e-mail. However, the best practice is to split up large jobs to around 1,000 recipients - this allows better processing load distribution. If you have a large number of additional substitutions or sections in the headers, it is best to split the send into even smaller groups.

-When using the X-SMTPAPI to send to multiple recipients, you cannot use the standard SMTP protocols “TO” field to send to multiple recipients because doing so can generate duplicate messages to the addresses listed in both. For more information, see RFC 5321.

-Ensure that the header is limited to a maximum total line length of 1,000 characters. Failure to do this can cause intermediate MTA’s to split the header on non-space boundaries- this causes inserted spaces in the final e-mail. If your e-mail is going through another MTA before reaching SendGrid, it is likely to have an even lower setting for maximum header length and may truncate the header.

-When using the API, if our system encounters a parsing error, the message will be bounced to the address specified in the MAIL FROM portion of the SMTP session. The MAIL FROM address is re-written when we send the e-mail out for final delivery, so it is safe to set this to an address that can receive the bounces so that you will be alerted to any errors.

-When sending Unicode characters via the SMTP API, you should escape these characters using the \u escape character. When you do this, Unicode characters like á becomes \u00E1.

Customizing your send (filters):

You can customize the emails you send via SMTP by using different settings (also referred to as filters). Change these settings in the X-SMTPAPI header.

For example, to send a blind carbon copy (BCC) of your email to the address example@example.com, include the following in your X-SMTPAPI header:

{
  "filters" : {
    "bcc" : {
      "settings" : {
        "enable" : 1,
        "email" : "example@example.com"
      }
    }
  }
}

The X-SMTPAPI header is a JSON-encoded associative array consisting of several sections, below are examples of JSON strings using each section. Add this header to any SMTP message sent to SendGrid and the instructions in the header will be interpreted and applied to that message’s transaction. You can enable these sections with the X-SMTPAPI header:

  -Scheduling Your Send
  -Substitution Tags
  -Section Tags
  -Suppression Groups
  -Categories
  -Unique Arguments
  
Scheduling Your Send:

Schedule your email send time using the send_at parameter within your X-SMTPAPI header. Set the value of send_at to the UNIX timestamp.

{
  "send_at": 1409348513
}

For more information, see our scheduling parameters documentation.

Substitution Tags:

Substitution tags allow you to dynamically insert specific content relevant to each of your recipients, such as their first and last names.

For example, to use a substitution tag to replace the first name of your recipient, insert the tag {{name}} in the HTML of your message:

<html>
  <head></head>
  <body>
    <p>Hello {{name}},<br>
        The body of your email would go here...
    </p>
  </body>
</html>

To define the value that will replace the {{name}} tag, define the following in your X-SMTPAPI header:

{
  "to": [
    "john.doeexampexample@example.com",
    "example@example.com"
  ],
  "sub": {
    "{{name}}": [
      "John",
      "Jane"
    ]
  }
}

For more information, see our substitution tags documentation.

Section Tags:

Section tags are similar to substitution tags, but rather than replace tags with content for each recipient; section tags allow you to replace a tag with more generic content— like a salutation.

For more information, see our section tags documentation.

Suppression Groups:

You can easily specify an unsubscribe group for an email sent via SMTP by including the asm_group_id parameter in your X-SMTPAPI header. Simply set the value of asm_group_id to the numerical ID of the group you would like to use.

{
  "asm_group_id": 1
}

For more information, see our suppression groups documentation.

Categories:

Categories allow you to track your emails according to broad topics that you define, like “Weekly Newsletter” or “Confirmation Email”. Simply define the category by using the category parameter within your X-SMTPAPI header:

{
  "category": "Example Category"
}

For more information, see our categories documentation

Categories should only be used for broad topics. To attach unique identifiers, please use unique arguments.

Unique Arguments:

Use unique arguments to track your emails based on specific identifiers unique to individual messages. Unique arguments can be retrieved via SendGrid’s Event Webhook or your email activity page.

For more information, see our unique arguments documentation.

- Example Code:

import com.sendgrid.smtpapi.SMTPAPI;

public class Example {
  public static void main(String[] args) {
    SMTPAPI header = new SMTPAPI();

    // [To](http://sendgrid.com/docs/API_Reference/SMTP_API/index.html)
    header.addTo("test@example.com");
    // or
    //header.addTo(["test1@example.com"]);
    // or
    //header.setTos(["test1@example.com", "test2@example.com"]);

    //String[] tos = header.getTos();

    // [Substitutions](http://sendgrid.com/docs/API_Reference/SMTP_API/substitution_tags.html)
    header.addSubstitution("key", "value");

    //JSONObject subs = header.getSubstitutions();

    // [Unique Arguments](http://sendgrid.com/docs/API_Reference/SMTP_API/unique_arguments.html)
    header.addUniqueArg("key", "value");
    // or
    //Map map = new HashMap<String, String>();
    //map.put("unique", "value");
    //header.setUniqueArgs(map);
    // or
    //JSONObject map = new JSONObject();
    //map.put("unique", "value");
    //header.setUniqueArgs(map);

    //JSONObject args = header.getUniqueArgs();

    // [Categories](http://sendgrid.com/docs/API_Reference/SMTP_API/categories.html)

    header.addCategory("category");
    // or
    //header.addCategory(["categories"]);
    // or
    //header.setCategories(["category1", "category2"]);

    //String[] cats = header.getCategories();

    // [Sections](http://sendgrid.com/docs/API_Reference/SMTP_API/section_tags.html)
    header.addSection("key", "section");
    // or
    //Map newSec = new HashMap();
    //newSec.put("-section-", "value");
    //header.setSections(newSec);
    // or
    //JSONObject newSec = new JSONObject();
    //newSec.put("-section-", "value");
    //header.setSections(newSec);

    //JSONObject sections = header.getSections();

    // [Filters](http://sendgrid.com/docs/API_Reference/SMTP_API/apps.html)
    header.addFilter("filter", "setting", "value");
    //header.addFilter("filter", "setting", 1);

    //JSONObject filters = header.getFilters();

    // [ASM Group Id](https://sendgrid.com/docs/User_Guide/advanced_suppression_manager.html)
    header.setASMGroupId(1);

    //Integer groupId = header.getASMGroupId();

    // [Scheduling](https://sendgrid.com/docs/API_Reference/SMTP_API/scheduling_parameters.html)
    header.setSendAt(1416427645);

    //int sendAt = header.getSendAt();

    // Get Headers
    String headers = header.jsonString();

    // If you need the unescaped JSON string.
    //String rawJson = header.rawJsonString();
    System.out.println(headers);
  }
}

-Link to more example codes:
https://github.com/sendgrid/smtpapi-java/tree/master/examples
