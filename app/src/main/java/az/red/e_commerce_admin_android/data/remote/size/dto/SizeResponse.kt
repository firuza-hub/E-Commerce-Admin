package az.red.e_commerce_admin_android.data.remote.size.dto

import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.FilterOption

class SizeResponse : ArrayList<SizeResponseItem>()

data class SizeResponseItem(
    val date: String,
    val name: String,
){
    fun toFilterOption(selected:String?) = FilterOption(name = name, id = name, isSelected = name == selected)
}