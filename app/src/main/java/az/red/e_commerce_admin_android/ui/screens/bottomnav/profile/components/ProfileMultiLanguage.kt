package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.main.MainActivity
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileViewModel
import az.red.e_commerce_admin_android.ui.themes.AccentCarrot
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.SessionManager

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileMultiLanguage(profileViewModel: ProfileViewModel, context: Context) {
    val sessionManager = SessionManager(context)
    val languageList = arrayOf("US", "AZ")

    var selectedItem by rememberSaveable {
        mutableStateOf(sessionManager.getCurrentLanguage())
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        val downOrUpIcon = if (expanded) R.drawable.ic_right else R.drawable.ic_down
        Box(
            modifier = Modifier
                .size(width = 60.dp, height = 31.dp)
                .border(
                    border = BorderStroke(1.5.dp, AccentCarrot),
                    shape = RoundedCornerShape(CustomTheme.spaces.large)
                )
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = if(selectedItem == "us") "US" else "AZ",
                    style = CustomTheme.typography.montSerratSemiBold,
                    color = AccentCarrot
                )

                Icon(
                    painter = painterResource(id = downOrUpIcon),
                    tint = AccentCarrot,
                    contentDescription = "Icon dark mode moon"
                )

            }

        }

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.background(CustomTheme.colors.accent),
            onDismissRequest = { expanded = false }
        ) {
            languageList.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption.lowercase()
                    //Save Current Language
                    profileViewModel.saveCurrentLanguage(selectedOption)
                    (context as MainActivity).recreate()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}