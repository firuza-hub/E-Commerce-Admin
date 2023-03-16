package az.red.e_commerce_admin_android.ui.screens.register.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.*
import az.red.e_commerce_admin_android.ui.screens.login.AuthButtonColors
import az.red.e_commerce_admin_android.ui.screens.register.RegisterState
import az.red.e_commerce_admin_android.ui.screens.register.RegisterUIEvent
import az.red.e_commerce_admin_android.ui.screens.register.RegisterViewModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.registerState.collectAsState()
    LaunchedEffect(key1 = true) {
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
                        navController.popBackStack()
                        navController.navigate(route = event.route)
                    }
                }
            }
        }
    }
    TopAppBar(
        elevation = 0.dp,
        title = {},
        backgroundColor = CustomTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
        }, modifier = Modifier.fillMaxWidth()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.create_your_account),
            style = CustomTheme.typography.h1,
            color = CustomTheme.colors.text
        )
        Spacer(modifier = Modifier.height(24.dp))

        InputSection(state, {
            viewModel.onUiEvent(
                registerUiEvent = RegisterUIEvent.EmailChanged(
                    it
                )
            )
        }, {
            viewModel.onUiEvent(
                registerUiEvent = RegisterUIEvent.PasswordChanged(
                    it
                )
            )
        }, {
            viewModel.onUiEvent(
                registerUiEvent = RegisterUIEvent.FirstNameChanged(
                    it
                )
            )
        }, {
            viewModel.onUiEvent(
                registerUiEvent = RegisterUIEvent.LastNameChanged(
                    it
                )
            )
        }, {
            viewModel.onUiEvent(
                registerUiEvent = RegisterUIEvent.LoginChanged(
                    it
                )
            )
        })

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(28.dp)),
            colors = AuthButtonColors(),
            onClick = { viewModel.onUiEvent(RegisterUIEvent.Submit) },
            enabled = true
        ) {
            Text(
                text = stringResource(id = R.string._continue),
                style = CustomTheme.typography.nunitoBold18
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(
                text = stringResource(id = R.string.already_have_an_account),
                color = CustomTheme.colors.text,
                style = CustomTheme.typography.body3
            )
            Text(
                text = stringResource(id = R.string.sign_in),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(start = 10.dp),
                color = CustomTheme.colors.accent,
                style = CustomTheme.typography.body3
            )
        }
    }
}


@Composable
fun InputSection(
    state: RegisterState,
    onEmailChange: (newValue: String) -> Unit,
    onPasswordChange: (newValue: String) -> Unit,
    onFirstNameChange: (newValue: String) -> Unit,
    onLastNameChange: (newValue: String) -> Unit,
    onLoginNameChange: (newValue: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            onValueChange = { onEmailChange(it) },
            label = stringResource(id = R.string.email),
            isError = state.errorState.emailErrorState.hasError,
            errorText = state.errorState.emailErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: state.errorState.emailErrorState.errorMessage
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = { onPasswordChange(it) },
            label = stringResource(id = R.string.password),
            isError = state.errorState.passwordErrorState.hasError,
            errorText = state.errorState.passwordErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: state.errorState.passwordErrorState.errorMessage,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = state.login,
            onValueChange = { onLoginNameChange(it) },
            label = stringResource(id = R.string.login),
            isError = state.errorState.loginErrorState.hasError,
            errorText = state.errorState.loginErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: state.errorState.loginErrorState.errorMessage,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = state.firstName,
            onValueChange = { onFirstNameChange(it) },
            label = stringResource(id = R.string.first_name),
            isError = state.errorState.firstNameErrorState.hasError,
            errorText = state.errorState.firstNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: state.errorState.firstNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = state.lastName, onValueChange = { onLastNameChange(it) }, label = stringResource(
                id = R.string.last_name
            ),
            isError = state.errorState.lastNameErrorState.hasError,
            errorText = state.errorState.lastNameErrorState.errorMessageStringResource?.let {
                stringResource(
                    id = it
                )
            }
                ?: state.errorState.lastNameErrorState.errorMessage,
            keyboardType = KeyboardType.Text
        )


    }

}
