package com.quick.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle


@Immutable
data class RuTypography(
    val titleH1: TextStyle = TypographyTokens.titleH1,
    val titleH2: TextStyle = TypographyTokens.titleH2,
    val titleH3: TextStyle = TypographyTokens.titleH3,
    val titleH4: TextStyle = TypographyTokens.titleH4,
    val titleH5: TextStyle = TypographyTokens.titleH5,
    val titleH6: TextStyle = TypographyTokens.titleH6,
    val labelXL: TextStyle = TypographyTokens.labelXL,
    val labelL: TextStyle = TypographyTokens.labelL,
    val labelM: TextStyle = TypographyTokens.labelM,
    val labelS: TextStyle = TypographyTokens.labelS,
    val labelXS: TextStyle = TypographyTokens.labelXS,
    val paragraphXL: TextStyle = TypographyTokens.paragraphXL,
    val paragraphL: TextStyle = TypographyTokens.paragraphL,
    val paragraphM: TextStyle = TypographyTokens.paragraphM,
    val paragraphS: TextStyle = TypographyTokens.paragraphS,
    val paragraphXS: TextStyle = TypographyTokens.paragraphXS,
    val subheadingM: TextStyle = TypographyTokens.subheadingM,
    val subheadingS: TextStyle = TypographyTokens.subheadingS,
    val subheadingXS: TextStyle = TypographyTokens.subheadingXS,
    val subheading2XS: TextStyle = TypographyTokens.subheading2XS,
    val docsLabel: TextStyle = TypographyTokens.docsLabel,
    val docsParagraph: TextStyle = TypographyTokens.docsParagraph,
)
