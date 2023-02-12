package az.red.e_commerce_admin_android.utils


sealed class UIEvent(val message: String? = null) {
    class Error(message: String) : UIEvent(message)
    class Navigate(val path: Int):UIEvent() {
    }
}