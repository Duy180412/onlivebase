package vn.vplay.vlive.myapplication.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import vn.vplay.vlive.myapplication.presentation.theme.Color1877F2
import vn.vplay.vlive.myapplication.presentation.theme.ColorD6D6D6

@Composable
fun NavigationText(
    @StringRes label: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    Text(
        text = stringResource(label),
        modifier = modifier,
        style = MaterialTheme.typography.labelSmall,
        color = if (isSelected) Color1877F2 else ColorD6D6D6
    )
}
