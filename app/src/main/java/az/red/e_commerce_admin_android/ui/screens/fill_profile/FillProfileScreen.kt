package az.red.e_commerce_admin_android.ui.screens.fill_profile

import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextFieldPhoneNumber
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextFieldWithTrailingIcon
import az.red.e_commerce_admin_android.ui.screens.fill_profile.components.*
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FillProfile(
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    navigateTo: (String) -> Unit,
    viewModel: FillProfileViewModel = koinViewModel()
) {
    val state by viewModel.fillProfileState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        launch {
            viewModel.uiEventFlow.collect { event ->
                when (event) {
                    is UIEvent.Error -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Message -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Navigate -> {
                        navigateTo(event.route)
                    }
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        FillProfileTopAppBar(navigateUp)

        if (isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        else {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = CustomTheme.spaces.large)
            ) {
                FillProfileImageContainer(selectedImage = state.avatarUrl, onImageSelected = {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.AvatarChanged(it.toString())
                    )
                })
                FillProfileCustomerInputContainer(state, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.FullNameChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.NickNameChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.EmailChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.DateOfBirthChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.PhoneNumberChanged(it)
                    )
                }, {
                    viewModel.onUiEvent(
                        fillProfileUIEvent = FillProfileUIEvent.GenderChanged(it)
                    )
                })
                Spacer(modifier = Modifier.height(40.dp))
                FillProfileButtons(onContinueClick = {
                    viewModel.onUiEvent(fillProfileUIEvent = FillProfileUIEvent.Continue)
                }, onSkipClick = {
                    navigateToHome()
                })
            }
        }
    }
}

@Composable
fun FillProfileImageContainer(selectedImage: String?, onImageSelected: (Uri?) -> Unit) {
    Spacer(modifier = Modifier.height(CustomTheme.spaces.large))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        FillProfileImage(selectedImage = selectedImage, onImageSelected = onImageSelected)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FillProfileCustomerInputContainer(
    fillProfileState: UserProfileState,
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
        StringTextField(value = fillProfileState.fullName,
            onValueChange = onFullNameChange,
            label = stringResource(id = R.string.full_name),
            isError = fillProfileState.errorState.fullNameErrorState.hasError,
            errorText = fillProfileState.errorState.fullNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.fullNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(value = fillProfileState.nickName,
            onValueChange = onNickNameChange,
            label = stringResource(id = R.string.nickname),
            isError = fillProfileState.errorState.nickNameErrorState.hasError,
            errorText = fillProfileState.errorState.nickNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.nickNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(value = fillProfileState.email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email),
            isError = fillProfileState.errorState.emailErrorState.hasError,
            errorText = fillProfileState.errorState.emailErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.emailErrorState.errorMessage,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(10.dp))

        StringTextFieldWithTrailingIcon(
            onValueChange = {
                println("DATE_CHANGED " + it)
                onDateOfBirthChange(it)
            },
            value = fillProfileState.dateOfBirth,
            trailingIcon = R.drawable.ic_date
        )

        Spacer(modifier = Modifier.height(10.dp))

        StringTextFieldPhoneNumber(onValueChange = onPhoneNumberChange,
            value = fillProfileState.phoneNumber,
            label = stringResource(id = R.string.phone_number),
            leadingIcon = R.drawable.ic_ukraine_flag,
            isError = fillProfileState.errorState.phoneNumberErrorState.hasError,
            errorText = fillProfileState.errorState.phoneNumberErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.phoneNumberErrorState.errorMessage,
            keyboardType = KeyboardType.Phone)

        Spacer(modifier = Modifier.height(10.dp))

        StringTextField(value = fillProfileState.gender,
            onValueChange = onGenderChange,
            label = stringResource(id = R.string.gender),
            isError = fillProfileState.errorState.genderErrorState.hasError,
            errorText = fillProfileState.errorState.genderErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            } ?: fillProfileState.errorState.genderErrorState.errorMessage,
            keyboardType = KeyboardType.Text)
    }
}
