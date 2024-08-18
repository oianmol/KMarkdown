package dev.baseio.kmarkdown.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.kmarkdown.lib.KMarkdown
import dev.baseio.kmarkdown.lib.KMarkdownStyles
import dev.baseio.kmarkdown.sample.ui.theme.KMarkdownTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KMarkdownTheme {
                KMarkdown(
                    markdownContent = TEST_MD,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 24.dp),
                    styles = KMarkdownStyles()
                )
            }
        }
    }
}