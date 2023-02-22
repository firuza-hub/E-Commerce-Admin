package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable

@Composable
fun ProfileImage() {
    Box(modifier = Modifier.size(width = 100.dp, height = 100.dp)){
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Product List Item Image",
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit_profile),
                contentDescription = "Edit Profile Image",
                modifier = Modifier.noRippleClickable {
                    //Edit Profile Image
                }
            )
        }
    }
}