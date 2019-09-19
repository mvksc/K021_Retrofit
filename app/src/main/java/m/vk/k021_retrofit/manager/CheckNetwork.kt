package m.vk.k021_retrofit.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class CheckNetwork {
    fun isConnectInternet(): Boolean {
        //var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        var isConnect = false
        val cm = Contexttor.getInstance().context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        //result = 2
                        isConnect = true
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        //result = 1
                        isConnect = true
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        //result = 2
                        isConnect = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        //result = 1
                        isConnect = true
                    }
                }
            }
        }
        //return result
        return isConnect
    }
}