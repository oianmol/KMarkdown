package dev.baseio.kmarkdown.lib

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class KMarkdownStyles(
    val kMarkdownTypography: KMarkdownTypography = KMarkdownTypography(),
    val blockQuoteBG: Color = Color(0x34242424),
    val blockQuoteShape: Shape = RoundedCornerShape(12.dp),
    val imageSize: DpSize = DpSize(200.dp, 200.dp)
)

data class KMarkdownTypography(
    val textStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    val setext1Style: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    val setext2Style: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading1: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading2: TextStyle = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading3: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading4: TextStyle = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading5: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
    ),
    val heading6: TextStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
)
