package az.red.e_commerce_admin_android.data.remote.brand.dto

import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.FilterOption


class BrandResponse : ArrayList<BrandResponseItem>()

data class BrandResponseItem(
    val name: String,
){
    fun toFilterOption(selected: String?) = FilterOption(name, name, isSelected = name == selected)
}