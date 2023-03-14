package az.red.e_commerce_admin_android.data.remote.color.dto

import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.FilterOption

class ColorResponse : ArrayList<ColorResponseItem>()

data class ColorResponseItem(
    val cssValue: String,
    val name: String,
){
    fun toFilterOption(selected:String?) = FilterOption(name = name, id = name, isSelected = name == selected)
}