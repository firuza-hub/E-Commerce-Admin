package az.red.e_commerce_admin_android.ui.screens.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.navigation.main.bottom.BottomNavScreen
import az.red.e_commerce_admin_android.ui.navigation.root.Graph

@Composable
fun BottomNavigationContainer(navController: NavController) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Orders,
        BottomNavScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarDestination = items.any { it.screen_route == currentRoute }
    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.accent_carrot),
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)),
            contentColor = Color.Black
        ) {

            items.forEach { item ->
                BottomNavigationItem(
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp,
                            color = colorResource(id = R.color.bottom_navigation_unselected)
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = colorResource(id = R.color.bottom_navigation_unselected).copy(
                        0.5f
                    ),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            popUpTo(Graph.MAIN) {
                                saveState = true
                            }
                            navController.backQueue.forEach { i -> println(i.destination.route) }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}