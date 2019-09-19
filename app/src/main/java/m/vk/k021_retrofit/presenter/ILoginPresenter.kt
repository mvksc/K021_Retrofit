package m.vk.k021_retrofit.presenter

interface ILoginPresenter {
    fun clearText()
    fun checkLogin(isPost: Boolean,user: String,pass: String)
    fun setStatusLoading(visibility: Int)
    fun setEnableButtonLogin(enable: Boolean)
}