package m.vk.k021_retrofit.view

import m.vk.k021_retrofit.model.LoginModel

interface ILoginView {
    fun onClearText()
    fun onLoginSuccess(arrLoginModel: LoginModel,msg: String)
    fun onLoginFail(msg: String)
    fun onStatusLoading(visibility: Int)
    fun onEnableButtonLogin(enable: Boolean)
    fun onNoConnectInternet()
}