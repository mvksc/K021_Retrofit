package m.vk.k021_retrofit.presenter

import android.util.Log
import android.view.View
import m.vk.k021_retrofit.BuildConfig
import m.vk.k021_retrofit.manager.CheckNetwork
import m.vk.k021_retrofit.manager.retrofit.ApiClient
import m.vk.k021_retrofit.model.LoginModel
import m.vk.k021_retrofit.view.ILoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class LoginPresenterCompI : ILoginPresenter{
    var iLoginView: ILoginView
    private var dataSet: List<LoginModel>
    private var checkNetwork: CheckNetwork

    constructor(iLoginView: ILoginView){
        this.iLoginView = iLoginView
        this.dataSet = ArrayList()
        this.checkNetwork = CheckNetwork()
    }

    override fun clearText() {
        iLoginView.onClearText()
    }

    override fun checkLogin(isPost: Boolean, user: String, pass: String) {
        if (checkNetwork.isConnectInternet()){
            val call: Call<LoginModel> = if (isPost){
                ApiClient.getClient.checklogin_getpost(user,pass)//Call Server POST_METHOD
            }else{
                ApiClient.getClient.checklogin_gethttp(user,pass)//Call Server GET_METHOD
            }

            call.enqueue(object : Callback<LoginModel>{
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    iLoginView.onLoginFail("เกิดข้อผิดพลาด")
                    iLoginView.onEnableButtonLogin(true)
                    iLoginView.onStatusLoading(View.INVISIBLE)
                }

                override fun onResponse(call: Call<LoginModel>,response: Response<LoginModel>) {
                    if (response.isSuccessful && response.errorBody() == null){
                        var loginModel: LoginModel = response.body()!!
                        if (loginModel.status == 1){
                            iLoginView.onLoginSuccess(loginModel,"สวัสดี ${loginModel.member_name}")
                        }else if (loginModel.status == 2){
                            iLoginView.onLoginSuccess(loginModel,"${loginModel.massage}")
                        }
                    }else{
                        iLoginView.onLoginFail("ไม่สามารถติดต่อกับ Server ได้")
                    }
                    iLoginView.onEnableButtonLogin(true)
                    iLoginView.onStatusLoading(View.INVISIBLE)
                }
            })
        }else{
            iLoginView.onNoConnectInternet()
            iLoginView.onEnableButtonLogin(true)
            iLoginView.onStatusLoading(View.INVISIBLE)
        }

    }

    override fun setStatusLoading(visibility: Int) {
        iLoginView.onStatusLoading(visibility)
    }

    override fun setEnableButtonLogin(enable: Boolean) {
        iLoginView.onEnableButtonLogin(enable)
    }
    fun onShowLogCat(tag: String,msg: String){
        if (BuildConfig.DEBUG){
            Log.e("***LoginPresenter***","Tag : $tag ==> Msg : $msg")
        }
    }
}