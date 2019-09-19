package m.vk.k021_retrofit.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import m.vk.k021_retrofit.R
import m.vk.k021_retrofit.databinding.ActivityLoginBinding
import m.vk.k021_retrofit.model.LoginModel
import m.vk.k021_retrofit.presenter.ILoginPresenter
import m.vk.k021_retrofit.presenter.LoginPresenterCompI

class LoginActivity : AppCompatActivity() , ILoginView, View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin -> {
                var user = binding.edUsername.text.toString()
                var pass = binding.edPassword.text.toString()
                if (user.isNotEmpty() && pass.isNotEmpty()){
                    loginPresenter.setEnableButtonLogin(false)
                    loginPresenter.setStatusLoading(View.VISIBLE)
                    loginPresenter.checkLogin(false,user,pass)
                }
            }
            R.id.btnClear -> {
                loginPresenter.clearText()
                loginPresenter.setEnableButtonLogin(true)
            }
        }
    }

    override fun onClearText() {
        loginPresenter.setStatusLoading(View.INVISIBLE)
        loginPresenter.setEnableButtonLogin(true)
        binding.edUsername.setText("")
        binding.edPassword.setText("")
        binding.tvStatusLogin.text = ""
    }

    override fun onLoginSuccess(loginModel: LoginModel,msg: String) {
        loginPresenter.setEnableButtonLogin(true)
        loginPresenter.setStatusLoading(View.INVISIBLE)
        binding.tvStatusLogin.text = msg
        binding.tvStatusLogin.setTextColor(Color.parseColor("#00574B"))
    }

    override fun onLoginFail(msg: String) {
        loginPresenter.setEnableButtonLogin(true)
        loginPresenter.setStatusLoading(View.INVISIBLE)
        binding.tvStatusLogin.text = msg
        binding.tvStatusLogin.setTextColor(Color.parseColor("#FF0000"))
    }

    override fun onStatusLoading(visibility: Int) {
        binding.pbLoading.visibility = visibility
    }

    override fun onEnableButtonLogin(enable: Boolean) {
        binding.btnLogin.isEnabled = enable
        binding.btnClear.isEnabled = enable
    }

    override fun onNoConnectInternet() {
        binding.tvStatusLogin.text = "กรุณาเชื่อมต่ออินเดอร์เน็ต"
    }

    companion object{
        lateinit var binding: ActivityLoginBinding
        lateinit var loginPresenter: ILoginPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        loginPresenter = LoginPresenterCompI(this)
        loginPresenter.setStatusLoading(View.INVISIBLE)

        binding.btnLogin.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
     }
}
