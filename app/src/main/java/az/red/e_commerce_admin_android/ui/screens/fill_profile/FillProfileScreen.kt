package az.red.e_commerce_admin_android.ui.screens.fill_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextFieldPhoneNumber
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextFieldWithTrailingIcon
import az.red.e_commerce_admin_android.ui.navigation.main.BottomNavScreen
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.fill_profile.components.*
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FillProfile(
    navController: NavController,
    viewModel: FillProfileViewModel = koinViewModel()
) {
    val state by viewModel.fillProfileState.collectAsState()
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        FillProfileTopAppBar(navController = navController)

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = CustomTheme.spaces.large)
        ) {
            FillProfileImageContainer()
            FillProfileCustomerInputContainer(
                state,
                {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.FullNameChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.NickNameChanged(it)
                    )
                },
                {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.EmailChanged(it)
                    )
                },
                {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.DateOfBirthChanged(it)
                    )
                },
                {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.PhoneNumberChanged(
                            if (it.length < 10) it else return@FillProfileCustomerInputContainer
                        )
                    )
                },
                {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.GenderChanged(it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            FillProfileButtons(
                onContinueClick = {
                    viewModel.onUiEvent(fillProfileUIEvent = FillProfileUIEvent.Continue)
                    if(state.isFillProfileSuccessful){
                        navController.navigate(BottomNavScreen.Home.screen_route)
                    }
                },
                onSkipClick = {
                    navController.navigate(BottomNavScreen.Home.screen_route)
                }
            )
        }

    }
}

@Composable
fun FillProfileImageContainer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(0.dp, CustomTheme.spaces.large, 0.dp, 0.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        FillProfileImage()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FillProfileCustomerInputContainer(
    fillProfileState: FillProfileState,
    onFullNameChange: (newValue: String) -> Unit,
    onNickNameChange: (newValue: String) -> Unit,
    onEmailChange: (newValue: String) -> Unit,
    onDateOfBirthChange: (newValue: String) -> Unit,
    onPhoneNumberChange: (newValue: String) -> Unit,
    onGenderChange: (newValue: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, CustomTheme.spaces.extraLarge, 0.dp, 0.dp)
    ) {
        StringTextField(
            value = fillProfileState.fullName,
            onValueChange = onFullNameChange,
            label = "Full name",
            isError = fillProfileState.errorState.fullNameErrorState.hasError,
            errorText = fillProfileState.errorState.fullNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: fillProfileState.errorState.fullNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text,
            leadingIcon = R.drawable.ic_user
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(
            value = fillProfileState.nickName,
            onValueChange = onNickNameChange,
            label = "Nickname",
            isError = fillProfileState.errorState.nickNameErrorState.hasError,
            errorText = fillProfileState.errorState.nickNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: fillProfileState.errorState.nickNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text,
            leadingIcon = R.drawable.ic_user
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(
            value = fillProfileState.email,
            onValueChange = onEmailChange,
            label = "Email",
            isError = fillProfileState.errorState.emailErrorState.hasError,
            errorText = fillProfileState.errorState.emailErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.emailErrorState.errorMessage,
            keyboardType = KeyboardType.Text,
            leadingIcon = R.drawable.ic_user
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextFieldWithTrailingIcon(
            onValueChange = onDateOfBirthChange,
            value = fillProfileState.dateOfBirth,
            trailingIcon = R.drawable.ic_date
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextFieldPhoneNumber(
            onValueChange = onPhoneNumberChange,
            value = fillProfileState.phoneNumber,
            label = "Phone number",
            leadingIcon = R.drawable.ic_ukraine_flag,
            isError = fillProfileState.errorState.phoneNumberErrorState.hasError,
            errorText = fillProfileState.errorState.phoneNumberErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.phoneNumberErrorState.errorMessage,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(
            value = fillProfileState.gender,
            onValueChange = onGenderChange,
            label = "Gender",
            isError = fillProfileState.errorState.genderErrorState.hasError,
            errorText = fillProfileState.errorState.genderErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.genderErrorState.errorMessage,
            keyboardType = KeyboardType.Text,
            leadingIcon = R.drawable.ic_user
        )
    }
}
