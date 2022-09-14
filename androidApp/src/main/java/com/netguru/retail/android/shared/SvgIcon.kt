package com.netguru.retail.android.shared

import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.caverock.androidsvg.SVG
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.FileResource

@Composable
fun svgResource(fileResource: FileResource): SVG =
    SVG.getFromResource(LocalContext.current, fileResource.rawResId)

@Composable
fun svgResource(assetResource: AssetResource): SVG =
    SVG.getFromAsset(LocalContext.current.assets, assetResource.path)

@Composable
fun svgResourceToDrawable(
    assetResource: AssetResource,
    sizeDp: Int = IconSize.dp
): Drawable {
    val sizePx = with(LocalDensity.current) {
        sizeDp.dp
            .toPx()
            .toInt()
    }
    val svg = svgResource(assetResource)
    return PictureDrawable(svg.renderToPicture(sizePx, sizePx))
}

@Composable
fun SvgIcon(modifier: Modifier, svg: SVG, contentDescription: String?) {
    var bounds by remember {
        mutableStateOf(RectF(0f, 0f, IconSize.coordinate, IconSize.coordinate))
    }
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }
    Canvas(
        modifier = modifier
            .onGloballyPositioned {
                bounds = RectF(
                    0f,
                    0f,
                    it.size.width.toFloat(),
                    it.size.height.toFloat()
                )
            }
            .then(semantics)
    ) {
        drawIntoCanvas {
            svg.documentHeight = bounds.height()
            svg.documentWidth = bounds.width()
            svg.renderToCanvas(it.nativeCanvas, bounds)
        }
    }
}

object IconSize {
    const val dp = 24
    const val coordinate = 48f
}
