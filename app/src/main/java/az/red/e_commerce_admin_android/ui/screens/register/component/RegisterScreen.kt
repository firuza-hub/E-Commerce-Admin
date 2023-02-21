package az.red.e_commerce_admin_android.ui.screens.register.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.EmailTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.PasswordTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.StringTextField
import az.red.e_commerce_admin_android.ui.screens.login.AuthButtonColors
import az.red.e_commerce_admin_android.ui.screens.register.RegisterState
import az.red.e_commerce_admin_android.ui.screens.register.RegisterViewModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavController, viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.registerState.collectAsState()

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

        InputSection(state, {}, {}, {}, {}, {}, {})

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(28.dp)),
            colors = AuthButtonColors(),
            onClick = { },
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
    onTelephoneChange: (newValue: String) -> Unit,
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
            isError = state.errorState.emailOrMobileErrorState.hasError,
            errorText = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = { onPasswordChange(it) },
            label = stringResource(id = R.string.password),
            isError = state.errorState.passwordErrorState.hasError,
            errorText = "",
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = "", onValueChange = { onFirstNameChange(it) }, label = "First Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = "", onValueChange = { onLastNameChange(it) }, label = "Last Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = "", onValueChange = { onLoginNameChange(it) }, label = "Login Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        StringTextField(
            value = "", onValueChange = { onTelephoneChange(it) }, label = "Telephone"
        )
    }

}
