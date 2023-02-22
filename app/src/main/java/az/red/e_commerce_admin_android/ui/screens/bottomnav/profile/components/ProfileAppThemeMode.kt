package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.AccentCarrot
import az.red.e_commerce_admin_android.ui.themes.BtnTextLight
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import kotlinx.coroutines.launch

@Composable
fun ProfileAppThemeMode(profileViewModel: ProfileViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var appThemeMode by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            profileViewModel.isDarkMode.collect { themeMode ->
                appThemeMode = themeMode
            }
        }
    }

    Box(
        modifier = Modifier
            .noRippleClickable {
                appThemeMode = !appThemeMode
                //Save App Theme Mode
                profileViewModel.saveAppThemeMode(appThemeMode)
            }
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
            //Light Mode
            if (!appThemeMode) {
                Box(
                    modifier = Modifier
                        .size(width = 25.dp, height = 25.dp)
                        .clip(CircleShape)
                        .background(AccentCarrot)
                        .border(
                            border = BorderStroke(
                                1.5.dp,
                                AccentCarrot
                            )
                        )

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sun),
                        contentDescription = "Icon light mode sun",
                        modifier = Modifier.align(Alignment.Center),
                        tint = BtnTextLight
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_moon),
                    contentDescription = "Icon light mode moon",
                    tint = AccentCarrot
                )
            }
            //Dark Mode
            else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sun),
                    contentDescription = "Icon dark mode sun",
                    tint = AccentCarrot
                )
                Box(
                    modifier = Modifier
                        .size(width = 25.dp, height = 25.dp)
                        .clip(CircleShape)
                        .background(AccentCarrot)
                        .border(
                            border = BorderStroke(
                                1.5.dp,
                                AccentCarrot
                            )
                        )

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_moon),
                        contentDescription = "Icon dark mode moon",
                        modifier = Modifier.align(Alignment.Center),
                        tint = BtnTextLight
                    )
                }
            }

        }

    }
}