package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.HorizontalPager

@Composable
fun ProfileImage(imgUrl: String?) {
    Box(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(CircleShape)
                .border(1.dp, CustomTheme.colors.cardBorder, CircleShape)
                .background(CustomTheme.colors.cardBackground)
        ) {
            if (imgUrl.isNullOrBlank()) {

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop
                )

            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imgUrl)
                        .crossfade(true).build(),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}