package vn.vplay.vlive.myapplication.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import vn.vplay.vlive.myapplication.R

val MyFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
)


val defaultSize12Height16TextStyleNormal = TextStyle(
    fontFamily = MyFontFamily,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Normal
)
val defaultSize12Height22TextStyleSemi = TextStyle(
    fontFamily = MyFontFamily,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.SemiBold,
)


val textSize12Normal = defaultSize12Height16TextStyleNormal.copy(fontSize = 12.sp)
val textSize14Normal = defaultSize12Height16TextStyleNormal.copy(fontSize = 14.sp)
val textSize16Normal = defaultSize12Height16TextStyleNormal.copy(fontSize = 16.sp)
val textSize18Normal = defaultSize12Height16TextStyleNormal.copy(fontSize = 18.sp)


val textSize16Semi = defaultSize12Height22TextStyleSemi.copy(fontSize = 16.sp)
