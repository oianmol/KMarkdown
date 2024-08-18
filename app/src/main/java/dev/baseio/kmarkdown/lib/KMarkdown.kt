package dev.baseio.kmarkdown.lib

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import dev.baseio.kmarkdown.sample.TEST_MD
import dev.baseio.kmarkdown.sample.ui.theme.KMarkdownTheme
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ASTNode.MDNodeASComposable(
    content: String,
    styles: KMarkdownStyles
) {
    val node = this@MDNodeASComposable
    val nodeContent = node.getTextInNode(content).toString()
    when (node.type.toMarkdownElement()) {
        KMarkdownElement.MarkdownFile -> node.children.forEach { astNode ->
            astNode.MDNodeASComposable(content = content, styles = styles)
        }

        KMarkdownElement.Paragraph -> FlowRow {
            node.children.forEach { paragraphChild ->
                paragraphChild.MDNodeASComposable(content, styles)
            }
        }

        KMarkdownElement.HtmlBlock,  KMarkdownElement.HtmlTag,  KMarkdownElement.HtmlBlockContent -> {
            Box(
                modifier = Modifier
                    .background(
                        color = styles.blockQuoteBG,
                        shape = styles.blockQuoteShape
                    )
                    .padding(4.dp)
            ) {
                val flavour = CommonMarkFlavourDescriptor()
                val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(nodeContent)
                val html = HtmlGenerator(nodeContent, parsedTree, flavour).generateHtml()
                BasicText(
                    text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
                        .toComposableText()
                )
            }
        }
        KMarkdownElement.UnorderedList -> {
            node.children.forEach { paragraphChild ->
                BasicText(text = paragraphChild.getTextInNode(content).toString())
            }
        }

        KMarkdownElement.LinkDestination -> {
            KMarkdownLinkDestination(content, styles)
        }

        KMarkdownElement.Image -> {
            KMarkDownImage(
                content = content,
                styles = styles
            )
        }

        KMarkdownElement.FullReferenceLink -> {
            BasicText(text = buildAnnotatedString {
                append(node.getTextInNode(content))
            })
        }

        KMarkdownElement.CodeSpan -> Box(
            modifier = Modifier
                .background(
                    color = styles.blockQuoteBG,
                    shape = styles.blockQuoteShape
                )
                .padding(4.dp)
        ) {
            BasicText(text = node.getTextInNode(content).toString())
        }

        KMarkdownElement.OrderedList -> {
            node.children.forEach { paragraphChild ->
                BasicText(text = paragraphChild.getTextInNode(content).toString())
            }
        }

        KMarkdownElement.BlockQuote -> Box(
            modifier = Modifier
                .background(
                    color = styles.blockQuoteBG,
                    shape = styles.blockQuoteShape
                )
                .padding(4.dp)
        ) {
            BasicText(text = node.getTextInNode(content).toString())
        }

        KMarkdownElement.Atx1 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading1
        )

        KMarkdownElement.Atx2 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading2
        )

        KMarkdownElement.Atx3 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading3
        )

        KMarkdownElement.Atx4 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading4
        )

        KMarkdownElement.Atx5 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading5
        )

        KMarkdownElement.Atx6 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.heading6
        )

        KMarkdownElement.Text -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.textStyle,
        )

        KMarkdownElement.HorizontalRule -> BasicText(text = "---")
        KMarkdownElement.Setext1 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.setext1Style,
        )

        KMarkdownElement.Setext2 -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.setext2Style,
        )

        KMarkdownElement.Strong -> BasicText(
            text = node.getTextInNode(content).toString(),
            style = styles.kMarkdownTypography.textStyle.copy(fontWeight = FontWeight.Bold),
        )

        KMarkdownElement.Eol -> BasicText(
            text = "\n",
        )

        else -> {
            BasicText(text = "not implemented ${this.type.name}")
        }
    }

}

@Composable
private fun ASTNode.KMarkDownImage(
    content: String,
    styles: KMarkdownStyles
) {
    val linkDestination = this.children.find {
        it.type.toMarkdownElement() is KMarkdownElement.InlineLink
    }?.children?.find {
        it.type.name == MarkdownElementTypes.LINK_DESTINATION.name
    }
    linkDestination?.MDNodeASComposable(content, styles)
}

@Composable
private fun ASTNode.KMarkdownLinkDestination(
    content: String,
    styles: KMarkdownStyles
) {
    val imageUrl = this.getTextInNode(content)
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    when (painter.state) {
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                modifier = Modifier.size(styles.imageSize),
                contentDescription = null
            )
        }

        AsyncImagePainter.State.Empty -> {
            BasicText(text = "nothing to load")
        }

        is AsyncImagePainter.State.Error -> {
            BasicText(text = "error ${(painter.state as AsyncImagePainter.State.Error).result.throwable.stackTraceToString()}")
        }

        is AsyncImagePainter.State.Loading -> BasicText(text = "loading..")
    }
}


@Composable
fun KMarkdown(
    markdownContent: String,
    modifier: Modifier = Modifier,
    styles: KMarkdownStyles,
) {
    val markdown by remember(markdownContent) {
        derivedStateOf {
            val flavour = CommonMarkFlavourDescriptor()
            MarkdownParser(flavour = flavour)
                .buildMarkdownTreeFromString(text = markdownContent)
        }
    }

    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        markdown.MDNodeASComposable(
            content = markdownContent,
            styles = styles
        )
    }
}

@Preview
@Composable
private fun Preview() {
    KMarkdownTheme {
        KMarkdown(
            markdownContent = TEST_MD,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                ),
            styles = KMarkdownStyles()
        )
    }
}

@Composable
fun Spanned.toComposableText(): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        val text = this@toComposableText.toString()

        val spans = this@toComposableText.getSpans(0, text.length, Any::class.java)

        var lastSpanEnd = 0
        for (span in spans) {
            val spanStart = this@toComposableText.getSpanStart(span)
            val spanEnd = this@toComposableText.getSpanEnd(span)

            // Add the text before the span (if any)
            if (spanStart > lastSpanEnd) {
                append(text.substring(lastSpanEnd, spanStart))
            }

            // Apply the styles or spans
            pushStyle(SpanStyle())
            when (span) {
                is StyleSpan -> {
                    when (span.style) {
                        android.graphics.Typeface.BOLD -> {
                            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        }

                        android.graphics.Typeface.ITALIC -> {
                            pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                        }

                        android.graphics.Typeface.BOLD_ITALIC -> {
                            pushStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic
                                )
                            )
                        }
                    }
                }

                is UnderlineSpan -> {
                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                }

                is StrikethroughSpan -> {
                    pushStyle(SpanStyle(textDecoration = TextDecoration.LineThrough))
                }

                is ForegroundColorSpan -> {
                    pushStyle(SpanStyle(color = Color(span.foregroundColor)))
                }

                is BackgroundColorSpan -> {
                    pushStyle(SpanStyle(background = Color(span.backgroundColor)))
                }

                is URLSpan -> {
                    pushStringAnnotation(tag = "URL", annotation = span.url)
                    pushStyle(
                        SpanStyle(
                            color = Color(0xfff86689),
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }

                is AbsoluteSizeSpan -> {
                    pushStyle(SpanStyle(fontSize = span.size.sp))
                }

                is RelativeSizeSpan -> {
                    pushStyle(SpanStyle(fontSize = span.sizeChange.em))
                }

                is TypefaceSpan -> {
                    span.family?.let { _ ->
                        pushStyle(SpanStyle(fontFamily = FontFamily(Typeface.DEFAULT)))
                    }
                }
                // Add other spans as necessary
            }
            append(text.substring(spanStart, spanEnd))
            pop()

            // Update the last span end
            lastSpanEnd = spanEnd
        }

        // Add the remaining text (if any)
        if (lastSpanEnd < text.length) {
            append(text.substring(lastSpanEnd))
        }
    }

    return annotatedString
}