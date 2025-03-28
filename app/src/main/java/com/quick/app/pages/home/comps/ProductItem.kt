package com.quick.app.pages.home.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.models.ProductModel

@Composable
fun ProductItem(data: ProductModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(6.dp)
    ) {
        AsyncImage(
            model = data.icon,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(135.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Text(
                text = data.title,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "￥${data.price}",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "销量：${data.salesCount}",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "会员价：￥${data.memberPrice}",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun ProductGridItem(data: ProductModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = data.icon,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                // 通过下面两行在自身范围内居中
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .size(135.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.LightGray)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.title,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "￥${data.price}",
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "销量：${data.salesCount}",
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "会员价：￥${data.memberPrice}",
            color = MaterialTheme.colorScheme.primary
        )
    }
}


val MOCK_DATA = ProductModel(
    id = "1",
    title = "傻厨电煎锅多功能家用不粘锅电饼铛一体锅烙饼锅电烤锅烤肉电烤锅傻厨电煎锅多功能家用不粘锅电饼铛一体锅烙饼锅电烤锅烤肉电烤锅",
    price = 100,
    originPrice = 200,
    icon = "https://www.baidu.com/img/bd_logo1.png",
    icons = listOf("https://www.baidu.com/img/bd_logo1.png"),
    video = "https://www.baidu.com/img/bd_logo1.png",
    keyword = "测试",
    category1Id = "1",
    category2Id = "2",
    category3Id = "3",
    memberPrice = 100,
    highlight = "粘不粘锅",
    detail = "<img src=\"https://img.alicdn.com/imgextra/i2/37617748/TB25A4MwMxlpuFjSszbXXcSVpXa_!!37617748.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i1/37617748/TB2NlvkwMxlpuFjy0FoXXa.lXXa_!!37617748.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i2/37617748/TB23UhVwHXlpuFjy1zbXXb_qpXa_!!37617748.jpg\"/>",
    sale = 100.toByte(),
    deleted = 0.toByte(),
    hot = 10.toByte(),
    news = 20.toByte(),
    recommend = 0.toByte(),
    commentsRate = 99.99f,
    commentsCount = 10086,
    salesCount = 10010,
    stockCount = 100001,
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductItemPreview() {
    PreviewContent(null) {
        Column {
            ProductItem(MOCK_DATA)
            Spacer(modifier = Modifier.size(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ProductGridItem(MOCK_DATA, modifier = Modifier.weight(1f))
                ProductGridItem(MOCK_DATA, modifier = Modifier.weight(1f))
            }
        }
    }
}