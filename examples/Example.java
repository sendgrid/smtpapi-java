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

    // [Per email scheduling](https://docs.sendgrid.com/for-developers/sending-email/scheduling-parameters)
    header.addSendEachAt(1416427645);

    //int sendAt = header.getSendAt();

    // Get Headers
    String headers = header.jsonString();

    // If you need the unescaped JSON string.
    //String rawJson = header.rawJsonString();
    System.out.println(headers);
  }
}