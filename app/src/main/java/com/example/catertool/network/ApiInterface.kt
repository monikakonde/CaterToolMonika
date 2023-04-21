package com.tobibur.swipequotes.model.service

import com.example.catertool.model.*
import com.example.catertool.util.Constants

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST(Constants.API.LOGIN)
    fun loginRequest(@Body request: Map<String, String>): Call<Login>

    @POST(Constants.API.USEREGISTER)
    fun userRegisterRequest(@Body request: Map<String, String>): Call<RegisterUser>

    @POST(Constants.API.USEREGISTER)
    fun userRegisterInsideRequest(@Body request: Map<String, String>): Call<RegisterUser>

    @GET(Constants.API.TYPEOFBUSINESSES)
    fun typeOfBusinessesRequest(): Call<TypeOfBusinesses>

    @GET(Constants.API.BUSINESSTYPE)
    fun businessTypesRequest(): Call<BusinessTypes>

    @GET(Constants.API.COUNTRIES)
    fun countryListRequest(): Call<BusinessTypes>

    @POST(Constants.API.BUSINESSREGISTRATION)
    fun businessRegisterRequest(@Body request: Map<String, String>): Call<BusinessRegister>

    @POST(Constants.API.VERIFYOTP)
    fun verifyOtpRequest(@Body request: Map<String, String>): Call<VerifyOtp>

    @POST(Constants.API.RESENDOTP)
    fun resendOtpRequest(@Body request: Map<String, String>): Call<ResendOtp>

    @GET(Constants.API.USER_ME)
    fun usersMeReqest(@Header("Authorization") auth: String): Call<UseMe>

    @POST(Constants.API.FORGETPSSWORDMOBILE)
    fun forgotPasswordMobileReqest(@Body request: Map<String, String>): Call<ForgotPasswordMobile>

    @POST(Constants.API.RESET_PASSWORD_MOBOLE)
    fun resetPasswordMobileReqest(@Body request: Map<String, String>): Call<Login>
    @GET(Constants.API.GET_BRAND_DETAILS)
    fun getBrandDetailsReqest(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<GetBrandDetails>
// List Apis
    @GET(Constants.API.GET_API_USERS)
    fun getApiUsersReqest(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<GetAllUserOfBrand>

    @GET(Constants.API.GET_API_OPENING_CHECKS)
    fun getApiGetOpeningChecks(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<AllCommenOpningCheck>

    @GET(Constants.API.GET_API_HNS_UNITS)
    fun GetApiHnsUnitRepository(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<HnsUnits>

    @GET(Constants.API.GET_API_SALSE_RECORD)
    fun GetApiSelaseRecordRepository(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<EventSelaseRecordList>

    @GET(Constants.API.GET_API_ALL_TO_DO_TODAY)
    fun GetApiGetAllToDoToday(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<GetAllToDoListToday>

    @GET(Constants.API.GET_API_ALL_TO_DO_TODAY)
    fun GetApiGetAllToDoScheduled(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<GetAllTodoScheduled>

    @GET(Constants.API.GET_API_ALL_TO_DO_TODAY)
    fun GetApiGetAllToComplited(
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<TodoComplited>

    @PUT(Constants.API.PUT_UPDATE_BRAND_DETAILS)
    fun putUpdateBrandRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body :BrandDetailsRequest
    ): Call<UpdateBrandDetails>

    @PUT(Constants.API.PUT_UPDATE_POSTAL_ADDRESS_DETAILS)
    fun putUpdatePostalAddressRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body :UpdatePostalAddressReqest
    ): Call<UpdatePostalAddress>

    @POST(Constants.API.POST_UPDATE_COMMON_CHECKS)
    fun putUpdateAllChecksRequest(
        @Header("Authorization") auth: String,
        @Body body :AddChecksReqest
    ): Call<UpdateChecks>

    @POST(Constants.API.POST_UPDATE_EVENT_TODO_ITEM)
    fun postAddToDo(
        @Header("Authorization") auth: String,
        @Body body :AddToDoReqest
    ): Call<AddTodo>

    @PUT(Constants.API.PUT_UPDATE_EMAIL_DETAILS)
    fun putEmailRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body :EmailUpdateReqest
    ): Call<UpdateEmail>

    @PUT(Constants.API.PUT_UPDATE_PHONE_DETAILS)
    fun putMobileRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body :MobileNumberUpdateRequest): Call<UpdateEmail>

    @PUT(Constants.API.PUT_DONE_TODO)
    fun putDoneTodoRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body :UpdateDoneTodoReqest): Call<TodoDone>

    @GET
    fun getAddressListReqest(
        @Url url: String,
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<GetAddressList>

    @GET
    fun getMainAddressListInterface(
        @Url url: String,
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>
    ): Call<MainAddressList>

    @GET
    fun getAddressInterface(
        @Url url: String,
        @Header("Authorization") auth: String,
        @QueryMap details: Map<String, String>,
    ): Call<GetAddress>



    @DELETE(Constants.API.DELETE_COMMON_CHECKS_FOR_BRAND)
    fun deleteCommonCheckRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String): Call<DeleteCommonCheck>

    @DELETE(Constants.API.DELETE_TODO)
    fun deleteTodoRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String): Call<DeleteTodo>

    @DELETE(Constants.API.DELETE_USER_BRAND)
    fun deleteUserBrandRequest(
        @Header("Authorization") auth: String,
        @Path("id") id: String): Call<Delete_User_Brand>

}
