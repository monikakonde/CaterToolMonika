package com.example.catertool.model
import iostudio.`in`.matrimonial.model.CommonResponse

data class BusinessRegister( override var status_code: String,
                  override var message: String,
                  val registration_result: RegistrationResult,
                  var error: ErrorData1
) : CommonResponse() {

}
data class RegistrationResult (
    val message: String,
    val success: Int
)

data class ErrorData1 (
    var status:String?,
    var name: String?,
    var message: String?,
)
