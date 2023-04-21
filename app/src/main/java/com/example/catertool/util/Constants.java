package com.example.catertool.util;

import static com.example.catertool.BuildConfig.BASEURL_API1;

import com.example.catertool.BuildConfig;

/**
 * Created by Ganesh on 15/2/2023.
 */
public class Constants {

    public interface API {

       // String BASEURL_API = "http://admin.catertool.mydevsystems.com";
      //  String BASEURL_API1 = "https://api.addressy.com";
        String LOGIN = "/api/auth/local";
        String USEREGISTER = "/api/auth/local/register";
        String TYPEOFBUSINESSES = "/api/type-of-businesses";

        String BUSINESSTYPE = "/api/business-types";

        String COUNTRIES = "/api/countries";

        String BUSINESSREGISTRATION = "api/registration";

        String VERIFYOTP = "/api/auth/verify-otp";
        String RESENDOTP = "/api/auth/send-otp";
        String USER_ME = "/api/users/me";
        String FORGETPSSWORDMOBILE = "/api/auth/forgot-password-mobile";
        String RESET_PASSWORD_MOBOLE = "/api/auth/reset-password-mobile";
        String GET_BRAND_DETAILS = "/api/brand-details";
        String GET_ADDRESS_LIST = BASEURL_API1+"/Capture/Interactive/Find/v1.10/json3.ws";

        String GET_MAIN_ADDRESS_LIST = BASEURL_API1+"/Capture/Interactive/Find/v1.10/json3.ws";
        String GET_ADDRESS = BASEURL_API1+"/Capture/Interactive/Retrieve/v1.2/json3.ws";
        String GET_API_USERS = "/api/users";
        String GET_API_OPENING_CHECKS = "/api/common-checks";
        String GET_API_HNS_UNITS = "/api/hns-units";
        String GET_API_SALSE_RECORD = "/api/sales-records";
        String GET_API_ALL_TO_DO_TODAY = "/api/event-todo-items";

        String PUT_UPDATE_BRAND_DETAILS = "/api/brand-details/{id}";

        String PUT_UPDATE_POSTAL_ADDRESS_DETAILS = "/api/brand-details/{id}";
        String POST_UPDATE_COMMON_CHECKS = "/api/common-checks";
        String POST_UPDATE_EVENT_TODO_ITEM = "/api/event-todo-items";

        String PUT_UPDATE_PHONE_DETAILS = "/api/users/{id}";
        String PUT_DONE_TODO = "/api/event-todo-items/{id}";

        String PUT_UPDATE_EMAIL_DETAILS = "/api/users/{id}";
        String DELETE_COMMON_CHECKS_FOR_BRAND = "/api/common-checks/{id}";
        String DELETE_TODO = "/api/event-todo-items/{id}";

        String DELETE_USER_BRAND = "/api/users/{id}";

    }
}
