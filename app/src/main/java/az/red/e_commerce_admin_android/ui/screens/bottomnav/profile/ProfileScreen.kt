package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import az.red.e_commerce_admin_android.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components.*
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    popUpToRoot: () -> Unit,
    navigateUp: () -> Unit,
    navigateCreateNewProduct: () -> Unit,
    navigationMainGraph: () -> Unit,
    profileViewModel: ProfileViewModel = koinViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val userProfileState by profileViewModel.userProfileState.collectAsState()

    val onAddressClick: () -> Unit = { }
    val onNotificationClick: () -> Unit = { }
    val onPaymentClick: () -> Unit = { }
    val onSecurityClick: () -> Unit = { }
    val onPrivacyPolicyClick: () -> Unit = { }
    val onHelpCenterClick: () -> Unit = { }

    val onLogOutClick: () -> Unit = {
        profileViewModel.logOut()
        coroutineScope.launch {
            profileViewModel.uiEventFlow.collect { event ->
                when (event) {
                    is UIEvent.Navigate -> {
                        popUpToRoot()
                    }
                    else -> {}
                }
            }
        }
    }

    //Profile Buttons clicks
    val onAddNewProductClick: () -> Unit = { navigateCreateNewProduct() }
    val onMyProductsClick: () -> Unit = { navigationMainGraph() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {

        ProfileTopAppBar(navigateUp)

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = CustomTheme.spaces.large)
        ) {
            //Profile Theme & Image & Language Container
            ProfileThemeImageLanguageContainer(profileViewModel,userProfileState.avatarUrl)
            Spacer(modifier = Modifier.height(CustomTheme.spaces.extraLarge))

            //Navigate Other Screens
            ProfileNavigateOtherScreensItems(
                onAddressClick,
                onNotificationClick,
                onPaymentClick,
                onSecurityClick,
                onPrivacyPolicyClick,
                onHelpCenterClick,
                onLogOutClick
            )

            //Profile Buttons
            Spacer(modifier = Modifier.height(30.dp))
            ProfileButtons(
                onAddNewProductClick = onAddNewProductClick,
                onMyProductsClick = onMyProductsClick
            )
        }
    }
}

@Composable
fun ProfileNavigateOtherScreensItems(
    onAddressClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onPaymentClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onLogOutClick: () -> Unit,
) {
    ProfileNavigateOtherScreen(
        R.drawable.ic_address,
        stringResource(R.string.address),
        onAddressClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_notification,
        stringResource(R.string.notification),
        onNotificationClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_payment,
        stringResource(R.string.payment),
        onPaymentClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_security,
        stringResource(R.string.security),
        onSecurityClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_privacy_policy,
        stringResource(R.string.privacy_policy),
        onPrivacyPolicyClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_help_center,
        stringResource(R.string.help_center),
        onHelpCenterClick
    )
    Spacer(modifier = Modifier.height(26.dp))

    ProfileNavigateOtherScreen(
        R.drawable.ic_log_out,
        stringResource(R.string.log_out),
        onLogOutClick, true
    )
}

@Composable
fun ProfileThemeImageLanguageContainer(profileViewModel: ProfileViewModel, imgUrl:String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ProfileAppThemeMode(profileViewModel)

        ProfileImage(imgUrl)

        ProfileMultiLanguage(profileViewModel, context)
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


