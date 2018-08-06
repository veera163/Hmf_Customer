package hmf.com.project.hmfcustomer.utility;

import hmf.com.project.hmfcustomer.AppController;

/**
 * Created by sai on 20-05-2017.
 */

public class AppConstant {

    public static final String HEADER = "Basic Y2xpZW50YXBwOjEyMzQ1Ng==";
    public static final String BASEURL = AppController.BaseUrl;
  //  public static final String BASEURL = "http://34.195.106.0/";
    public static final String LOGINPATH = "oauth/token?grant_type=password&scope=read write&client_secret=123456&client_id=clientapp&";
    public static final String USERPATH = "/userOperationsV3/";

}
