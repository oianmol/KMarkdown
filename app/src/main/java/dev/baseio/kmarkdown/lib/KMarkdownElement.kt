package dev.baseio.kmarkdown.lib

import org.intellij.markdown.IElementType
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes

sealed class KMarkdownElement {
    object MarkdownFile : KMarkdownElement()
    object UnorderedList : KMarkdownElement()
    object OrderedList : KMarkdownElement()
    object ListItem : KMarkdownElement()
    object BlockQuote : KMarkdownElement()
    object CodeFence : KMarkdownElement()
    object CodeBlock : KMarkdownElement()
    object CodeSpan : KMarkdownElement()
    object HtmlBlock : KMarkdownElement()
    object Paragraph : KMarkdownElement()
    object Emph : KMarkdownElement()
    object Strong : KMarkdownElement()
    object LinkDefinition : KMarkdownElement()
    object LinkLabel : KMarkdownElement()
    object LinkDestination : KMarkdownElement()
    object LinkTitle : KMarkdownElement()
    object LinkText : KMarkdownElement()
    object InlineLink : KMarkdownElement()
    object FullReferenceLink : KMarkdownElement()
    object ShortReferenceLink : KMarkdownElement()
    object Image : KMarkdownElement()
    object Autolink : KMarkdownElement()
    object Setext1 : KMarkdownElement()
    object Setext2 : KMarkdownElement()
    object Atx1 : KMarkdownElement()
    object Atx2 : KMarkdownElement()
    object Atx3 : KMarkdownElement()
    object Atx4 : KMarkdownElement()
    object Atx5 : KMarkdownElement()
    object Atx6 : KMarkdownElement()

    // From MarkdownTokenTypes
    object Text : KMarkdownElement()
    object CodeLine : KMarkdownElement()
    object BlockQuoteToken : KMarkdownElement() // Renamed to avoid conflict
    object HtmlBlockContent : KMarkdownElement()
    object SingleQuote : KMarkdownElement()
    object DoubleQuote : KMarkdownElement()
    object LParen : KMarkdownElement()
    object RParen : KMarkdownElement()
    object LBracket : KMarkdownElement()
    object RBracket : KMarkdownElement()
    object Lt : KMarkdownElement()
    object Gt : KMarkdownElement()
    object Colon : KMarkdownElement()
    object ExclamationMark : KMarkdownElement()
    object HardLineBreak : KMarkdownElement()
    object Eol : KMarkdownElement()
    object LinkId : KMarkdownElement()
    object AtxHeader : KMarkdownElement()
    object AtxContent : KMarkdownElement()
    object SetextContent : KMarkdownElement()
    object Backtick : KMarkdownElement()
    object EscapedBackticks : KMarkdownElement()
    object ListBullet : KMarkdownElement()
    object Url : KMarkdownElement()
    object HorizontalRule : KMarkdownElement()
    object ListNumber : KMarkdownElement()
    object FenceLang : KMarkdownElement()
    object CodeFenceStart : KMarkdownElement()
    object CodeFenceContent : KMarkdownElement()
    object CodeFenceEnd : KMarkdownElement()
    object EmailAutolink : KMarkdownElement()
    object HtmlTag : KMarkdownElement()
    object BadCharacter : KMarkdownElement()
    object WhiteSpace : KMarkdownElement()
}

fun IElementType.toMarkdownElement(): KMarkdownElement {
    return when (this) {
        MarkdownElementTypes.MARKDOWN_FILE -> KMarkdownElement.MarkdownFile
        MarkdownElementTypes.UNORDERED_LIST -> KMarkdownElement.UnorderedList
        MarkdownElementTypes.ORDERED_LIST -> KMarkdownElement.OrderedList
        MarkdownElementTypes.LIST_ITEM -> KMarkdownElement.ListItem
        MarkdownElementTypes.BLOCK_QUOTE -> KMarkdownElement.BlockQuote
        MarkdownElementTypes.CODE_FENCE -> KMarkdownElement.CodeFence
        MarkdownElementTypes.CODE_BLOCK -> KMarkdownElement.CodeBlock
        MarkdownElementTypes.CODE_SPAN -> KMarkdownElement.CodeSpan
        MarkdownElementTypes.HTML_BLOCK -> KMarkdownElement.HtmlBlock
        MarkdownElementTypes.PARAGRAPH -> KMarkdownElement.Paragraph
        MarkdownElementTypes.EMPH -> KMarkdownElement.Emph
        MarkdownElementTypes.STRONG -> KMarkdownElement.Strong
        MarkdownElementTypes.LINK_DEFINITION -> KMarkdownElement.LinkDefinition
        MarkdownElementTypes.LINK_LABEL -> KMarkdownElement.LinkLabel
        MarkdownElementTypes.LINK_DESTINATION -> KMarkdownElement.LinkDestination
        MarkdownElementTypes.LINK_TITLE -> KMarkdownElement.LinkTitle
        MarkdownElementTypes.LINK_TEXT -> KMarkdownElement.LinkText
        MarkdownElementTypes.INLINE_LINK -> KMarkdownElement.InlineLink
        MarkdownElementTypes.FULL_REFERENCE_LINK -> KMarkdownElement.FullReferenceLink
        MarkdownElementTypes.SHORT_REFERENCE_LINK -> KMarkdownElement.ShortReferenceLink
        MarkdownElementTypes.IMAGE -> KMarkdownElement.Image
        MarkdownElementTypes.AUTOLINK -> KMarkdownElement.Autolink
        MarkdownElementTypes.SETEXT_1 -> KMarkdownElement.Setext1
        MarkdownElementTypes.SETEXT_2 -> KMarkdownElement.Setext2
        MarkdownElementTypes.ATX_1 -> KMarkdownElement.Atx1
        MarkdownElementTypes.ATX_2 -> KMarkdownElement.Atx2
        MarkdownElementTypes.ATX_3 -> KMarkdownElement.Atx3
        MarkdownElementTypes.ATX_4 -> KMarkdownElement.Atx4
        MarkdownElementTypes.ATX_5 -> KMarkdownElement.Atx5
        MarkdownElementTypes.ATX_6 -> KMarkdownElement.Atx6

        // From MarkdownTokenTypes
        MarkdownTokenTypes.TEXT -> KMarkdownElement.Text
        MarkdownTokenTypes.CODE_LINE -> KMarkdownElement.CodeLine
        MarkdownTokenTypes.BLOCK_QUOTE -> KMarkdownElement.BlockQuoteToken
        MarkdownTokenTypes.HTML_BLOCK_CONTENT -> KMarkdownElement.HtmlBlockContent
        MarkdownTokenTypes.SINGLE_QUOTE -> KMarkdownElement.SingleQuote
        MarkdownTokenTypes.DOUBLE_QUOTE -> KMarkdownElement.DoubleQuote
        MarkdownTokenTypes.LPAREN -> KMarkdownElement.LParen
        MarkdownTokenTypes.RPAREN -> KMarkdownElement.RParen
        MarkdownTokenTypes.LBRACKET -> KMarkdownElement.LBracket
        MarkdownTokenTypes.RBRACKET -> KMarkdownElement.RBracket
        MarkdownTokenTypes.LT -> KMarkdownElement.Lt
        MarkdownTokenTypes.GT -> KMarkdownElement.Gt
        MarkdownTokenTypes.COLON -> KMarkdownElement.Colon
        MarkdownTokenTypes.EXCLAMATION_MARK -> KMarkdownElement.ExclamationMark
        MarkdownTokenTypes.HARD_LINE_BREAK -> KMarkdownElement.HardLineBreak
        MarkdownTokenTypes.EOL -> KMarkdownElement.Eol
        MarkdownTokenTypes.LINK_ID -> KMarkdownElement.LinkId
        MarkdownTokenTypes.ATX_HEADER -> KMarkdownElement.AtxHeader
        MarkdownTokenTypes.ATX_CONTENT -> KMarkdownElement.AtxContent
        MarkdownTokenTypes.SETEXT_1 -> KMarkdownElement.Setext1
        MarkdownTokenTypes.SETEXT_2 -> KMarkdownElement.Setext2
        MarkdownTokenTypes.SETEXT_CONTENT -> KMarkdownElement.SetextContent
        MarkdownTokenTypes.EMPH -> KMarkdownElement.Emph
        MarkdownTokenTypes.BACKTICK -> KMarkdownElement.Backtick
        MarkdownTokenTypes.ESCAPED_BACKTICKS -> KMarkdownElement.EscapedBackticks
        MarkdownTokenTypes.LIST_BULLET -> KMarkdownElement.ListBullet
        MarkdownTokenTypes.URL -> KMarkdownElement.Url
        MarkdownTokenTypes.HORIZONTAL_RULE -> KMarkdownElement.HorizontalRule
        MarkdownTokenTypes.LIST_NUMBER -> KMarkdownElement.ListNumber
        MarkdownTokenTypes.FENCE_LANG -> KMarkdownElement.FenceLang
        MarkdownTokenTypes.CODE_FENCE_START -> KMarkdownElement.CodeFenceStart
        MarkdownTokenTypes.CODE_FENCE_CONTENT -> KMarkdownElement.CodeFenceContent
        MarkdownTokenTypes.CODE_FENCE_END -> KMarkdownElement.CodeFenceEnd
        MarkdownTokenTypes.AUTOLINK -> KMarkdownElement.Autolink
        MarkdownTokenTypes.EMAIL_AUTOLINK -> KMarkdownElement.EmailAutolink
        MarkdownTokenTypes.HTML_TAG -> KMarkdownElement.HtmlTag
        MarkdownTokenTypes.BAD_CHARACTER -> KMarkdownElement.BadCharacter
        MarkdownTokenTypes.WHITE_SPACE -> KMarkdownElement.WhiteSpace
        else -> throw IllegalArgumentException("Unknown IElementType")
    }
}