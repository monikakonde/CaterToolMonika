package com.example.catertool.network;

import java.util.HashMap;
import java.util.Map;

public class RetrofitRequest {

    /**
     * Static variables which is being used as APIs params
     */
    public static final String ACTION_PASSWORD = "password";
    public static final String ACTION_PASSWORD_CONDIRM = "passwordConfirmation";
    private static final String ACTION_IDENTIFIER="identifier";
// User Register
    private static final String ACTION_USERNAME="username";
    private static final String ACTION_EMAIL="email";
    private static final String ACTION_USERREGISTERPASSWORD="password";
    private static final String ACTION_CONFIRMED="confirmed";
    private static final String ACTION_BLOCKED="blocked";
    private static final String ACTION_FIRSTNAME="firstName";
    private static final String ACTION_LASTNAME="lastName";
    private static final String ACTION_MOBILE="mobile";
//
    private static final String ACTION_USERS="users";
    private static final String ACTION_COMPANYNAME="companyName";
    private static final String ACTION_TRADINGNAME="tradingName";
    private static final String ACTION_POSTED="postcode";
    private static final String ACTION_ADDRESSLINE="addressLine1";
    private static final String ACTION_COUNTRY="country";
    private static final String ACTION_BUSINESSTYPE="businessType";
    private static final String ACTION_BUSINESSEMAIL="businessEmail";
    private static final String ACTION_TYPEOFBUINESS="typeOfBusiness";
    private static final String MOBILE="mobile";
    private static final String OTP="otp";

    private static final String FILTER="filters[users][id][$eq]";
    private static final String FILTER_GETUSER="filters[brand_details]";
    private static final String FILTER_GETAllCHECK="filters[brand_detail][id][$eq]";
    private static final String FILTER_HnsUnit="filters[brand_detail][id][$eq]";
    private static final String FILTER_EVENTLIST="filters[hns_unit]";
    private static final String IS_OPNING_CHECKS="filters[isOpeningCheck]";
    private static final String IS_CLOSING_CHECKS="filters[isClosingCheck]";

    private static final String POPULATE="populate";
    private static final String TODO_ASSIGNED_TO="filters[assigned_to][id][$eq]";
    private static final String TODO_DEADLINE_TIME="filters[deadlineDateTime][$gte]";
    private static final String TODO_DEADLINE_DATE="filters[deadlineDateTime][$lt]";
    private static final String TODO_DEADLINE_DATE_COMPLITED="filters[deadlineDateTime][$gte]";
    private static final String TODO_STATUS="filters[status][$ne]";
    private static final String TODO_STATUS_COMPLITED="filters[status][$eq]";
    private static final String TEXT="Text";
    private static final String CONTAINER="Container";
    private static final String ID="Id";
    private static final String KEYS="Key";
    private static final String POPULATE_USER="populate";
    private static final String ACTION_COMPANY_DETAILS="allowCompanyDetails";
    private static final String ACTION_ALLOW_HN_UNITS="allowHNSUnits";
    private static final String ACTION_ALLOW_TEMPERATURE="allowTemperature";
    private static final String ACTION_ALLOW_CHECKS="allowChecks";
    private static final String ACTION_ALLOW_SALES_TRACKER="allowSalesTracker";
    private static final String ACTION_ALLOWTODO="allowToDo";
    private static final String ACTION_BRAND_DETAILS="brand_details";


    public static Map<String, String> loginRequest(final String identifierNo, final String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(ACTION_IDENTIFIER, identifierNo);
        params.put(ACTION_PASSWORD, password);
        return params;
    }

    public static Map<String, String> userRegister(final String users,final String companyName,final String password,final String confirmed,final String blocked,final String firstName,final String lastName,final String mobile) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(ACTION_USERNAME, users);
        params.put(ACTION_EMAIL, companyName);
        params.put(ACTION_USERREGISTERPASSWORD, password);
        params.put(ACTION_CONFIRMED, confirmed);
        params.put(ACTION_BLOCKED, blocked);
        params.put(ACTION_FIRSTNAME, firstName);
        params.put(ACTION_LASTNAME, lastName);
        params.put(ACTION_MOBILE, mobile);
        return params;
    }

    public static Map<String, String> userRegisterInside(final String users,final String companyName,final String password,final String confirmed,final String blocked,final String firstName,final String lastName,final String mobile,final String allowCompanyDetails,
                                                         final String allowHNSUnits,final String allowTemperature,final String allowChecks,final String allowSalesTracker, final String allowToDo, final String brand_details) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(ACTION_USERNAME, users);
        params.put(ACTION_EMAIL, companyName);
        params.put(ACTION_USERREGISTERPASSWORD, password);
        params.put(ACTION_CONFIRMED, confirmed);
        params.put(ACTION_BLOCKED, blocked);
        params.put(ACTION_FIRSTNAME, firstName);
        params.put(ACTION_LASTNAME, lastName);
        params.put(ACTION_MOBILE, mobile);
        params.put(ACTION_COMPANY_DETAILS, allowCompanyDetails);
        params.put(ACTION_ALLOW_HN_UNITS, allowHNSUnits);
        params.put(ACTION_ALLOW_TEMPERATURE, allowTemperature);
        params.put(ACTION_ALLOW_CHECKS, allowChecks);
        params.put(ACTION_ALLOW_SALES_TRACKER, allowSalesTracker);
        params.put(ACTION_ALLOWTODO, allowToDo);
        params.put(ACTION_BRAND_DETAILS, brand_details);
        return params;
    }
    public static Map<String, String> registrationBusiness(final String username,final String companyName,final String tradingName,final String postcode,final String addressLine1,final String country,final String businessType,final String businessEmail,final String typeOfBusiness) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(ACTION_USERS, username);
        params.put(ACTION_COMPANYNAME, companyName);
        params.put(ACTION_TRADINGNAME, tradingName);
        params.put(ACTION_POSTED, postcode);
        params.put(ACTION_ADDRESSLINE, addressLine1);
        params.put(ACTION_COUNTRY, country);
        params.put(ACTION_BUSINESSTYPE, businessType);
        params.put(ACTION_BUSINESSEMAIL, businessEmail);
        params.put(ACTION_TYPEOFBUINESS, typeOfBusiness);
        return params;
    }

    public static Map<String, String> registverifyOtp(final String mobilenumber, final String otp) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MOBILE, mobilenumber);
        params.put(OTP, otp);
        return params;
    }

    public static Map<String, String> resendOtp(final String mobilenumber) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MOBILE, mobilenumber);
        return params;
    }

    public static Map<String, String> forgotPasswordMobile(final String mobilenumber) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MOBILE, mobilenumber);
        return params;
    }

    public static Map<String, String> resetPasswordMobileReqest(final String mobile, final String otp, final String password, final String passwordConfirmation) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MOBILE, mobile);
        params.put(OTP, otp);
        params.put(ACTION_PASSWORD, password);
        params.put(ACTION_PASSWORD_CONDIRM, passwordConfirmation);
        return params;
    }

    public static Map<String, String> getBrandDetailReqest(final String filter, final String populate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(FILTER, filter);
        params.put(POPULATE, populate);
        return params;
    }
    public static Map<String, String> getApiUserReqest(final String populate, final String filter) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(POPULATE_USER, populate);
        params.put(FILTER_GETUSER, filter);
        return params;
    }

    public static Map<String, String> getApiAllOpningCheckReqest(final String populate, final String filter,final String isOpningCheck) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(POPULATE_USER, populate);
        params.put(FILTER_GETAllCHECK, filter);
        params.put(IS_OPNING_CHECKS, isOpningCheck);
        return params;
    }

    public static Map<String, String> getApiAllClosingCheckReqest(final String populate, final String filter,final String isClosingCheck) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(POPULATE_USER, populate);
        params.put(FILTER_GETAllCHECK, filter);
        params.put(IS_CLOSING_CHECKS, isClosingCheck);
        return params;
    }

    public static Map<String, String> getApiHnsUnitReqest(final String filter, final String populate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(FILTER_HnsUnit, filter);
        params.put(POPULATE, populate);
        return params;
    }

    public static Map<String, String> getApiEventListReqest(final String populate,final String filter) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(POPULATE, populate);
        params.put(FILTER_EVENTLIST, filter);
        return params;
    }


    public static Map<String, String> getApiTodoListTodayReqest(final String assigned_id,final String deadline_time,final String deadline_date,final String ststus,final String populate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(TODO_ASSIGNED_TO, assigned_id);
        params.put(TODO_DEADLINE_TIME, deadline_time);
        params.put(TODO_DEADLINE_DATE, deadline_date);
        params.put(TODO_STATUS, ststus);
        params.put(POPULATE, populate);
        return params;
    }
    public static Map<String, String> getApiTodoListScheduledReqest(final String assigned_id,final String deadline_time,final String ststus,final String populate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(TODO_ASSIGNED_TO, assigned_id);
        params.put(TODO_DEADLINE_TIME, deadline_time);
        params.put(TODO_STATUS, ststus);
        params.put(POPULATE, populate);
        return params;
    }

    public static Map<String, String> getApiTodoListCompliedReqest(final String assigned_id,final String deadline_time,final String ststus,final String populate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(TODO_ASSIGNED_TO, assigned_id);
        params.put(TODO_DEADLINE_TIME, deadline_time);
        params.put(TODO_STATUS_COMPLITED, ststus);
        params.put(POPULATE, populate);
        return params;
    }


    public static Map<String, String> getAddressListReqest(final String Key, final String Text) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEYS, Key);
        params.put(TEXT, Text);
        return params;
    }

    public static Map<String, String> getMainAddressListReqest(final String Key, final String Container) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEYS, Key);
        params.put(CONTAINER, Container);
        return params;
    }

    public static Map<String, String> getAddressReqest(final String Key, final String Id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEYS, Key);
        params.put(ID, Id);
        return params;
    }
}