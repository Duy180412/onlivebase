package vn.vplay.vlive.myapplication.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import vn.vplay.vlive.myapplication.R
import vn.vplay.vlive.myapplication.presentation.theme.Color131313_90

sealed class BottomNavItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
    val route: String
) {
    data object Home : BottomNavItem(
        R.string.label_nav_home,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        HOME
    )

    data object Category : BottomNavItem(
        R.string.label_nav_category,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        CATEGORY
    )

    data object Schedule : BottomNavItem(
        R.string.label_nav_schedule,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        SCHEDULE
    )

    data object Notification : BottomNavItem(
        R.string.label_nav_notification,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        NOTIFICATION
    )

    data object Profile : BottomNavItem(
        R.string.label_nav_profile,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        PROFILE
    )

    companion object {
        const val HOME = "HOME"
        const val CATEGORY = "CATEGORY"
        const val SCHEDULE = "SCHEDULE"
        const val NOTIFICATION = "NOTIFICATION"
        const val PROFILE = "PROFILE"
        val items = listOf(
            Home,
            Category,
            Schedule,
            Notification,
            Profile
        )
    }

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    NavigationBar(
        containerColor = Color131313_90,
    ) {
        BottomNavItem.items.forEach { item ->
            val selected = currentDestination?.route == item.route

            NavigationBarItem(
                icon = {
                    NavigationIcon(
                        icon = item.icon,
                        iconSelected = item.iconSelected,
                        contentDescription = item.label,
                        isSelected = selected
                    )
                },
                label = { NavigationText(label = item.label, isSelected = selected) },
                selected = selected,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

