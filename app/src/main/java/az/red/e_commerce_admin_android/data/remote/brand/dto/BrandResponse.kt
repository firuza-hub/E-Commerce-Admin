package az.red.e_commerce_admin_android.data.remote.brand.dto


class BrandResponse : ArrayList<BrandResponseItem>()

data class BrandResponseItem(
    val name: String,
)