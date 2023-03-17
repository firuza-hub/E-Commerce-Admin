package az.red.e_commerce_admin_android.ui.screens.fill_profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun FillProfileImage(selectedImage: String?, onImageSelected: (Uri?) -> Unit) {
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            onImageSelected(uri)
        }

    Box() {
        Box(
            modifier = Modifier
                .size(width = 130.dp, height = 130.dp)
                .clip(CircleShape)
                .border(1.dp, CustomTheme.colors.cardBorder, CircleShape)
                .background(CustomTheme.colors.cardBackground)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = if (selectedImage.isNullOrBlank()) painterResource(id = R.drawable.ic_user)
                else {
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = selectedImage)
                            .build()
                    )
                } ?: painterResource(id = R.drawable.ic_user),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop
            )
        }

        Icon(painter = painterResource(id = R.drawable.ic_edit_profile_36px),
            contentDescription = "Fill Profile Edit Icon",
            modifier = Modifier
                .noRippleClickable {
                    galleryLauncher.launch("image/*")
                }
                .align(Alignment.BottomEnd),
            tint = CustomTheme.colors.text)

    }
}