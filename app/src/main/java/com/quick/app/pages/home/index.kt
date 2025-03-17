package com.quick.app.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    val vm = viewModel<HomeViewModel>()
    val datum by vm.datum.collectAsState()
    Scaffold(topBar = { MyAppBar() }) {
        LazyColumn(
            // 也可以利用 contentWindowInsets 进行排除
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(datum.size) { idx ->
                ProductItem(data = datum[idx], modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.Detail.routeParam(datum[idx].id))
                })
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyAppBar() {
    val count = viewModel<HomeViewModel>().count
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        },
        title = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                Text(text = "Search")
                Text(
                    text = "计数: ${count.intValue}",
                    modifier = Modifier.clickable { count.intValue++ })
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Outlined.Sms,
                contentDescription = null
            )
        }
    )


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeRoutePreview() {
    PreviewContent("index")
}