package vn.vplay.vlive.myapplication.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun IconPreview() {

}

@Composable
fun NavigationIcon(
    @DrawableRes icon: Int,
    @DrawableRes iconSelected: Int,
    @StringRes contentDescription: Int,
    isSelected: Boolean
) {

    val resId = if (isSelected) iconSelected else icon
    androidx.compose.material3.Icon(
        painter = painterResource(resId),
        contentDescription = stringResource(contentDescription),
        tint = if (isSelected) MaterialTheme.colorScheme.primary else LocalContentColor.current
    )
}
