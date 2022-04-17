package com.wordpress.safbk.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.wordpress.safbk.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.launch

class LayoutsInCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyTopAppBar()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard() {
    Row(modifier = Modifier
        .clickable { }
        .padding(20.dp)) {
        androidx.compose.material.Surface(modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape)
            //.background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        ) {
            Image(painter = painterResource(id = R.drawable.pf)
                , contentDescription = null )
        }

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = "Sarfaraz Ali Toori", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }

//            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.primary) {
//                Text(text = "Hello World" , fontWeight = FontWeight.SemiBold)
//            }
        }

    }
}


// Scaffold

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
       // BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
private fun MyTopAppBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "This App Bar")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column {
            MyAppBody(Modifier.padding(innerPadding))
            //MySimpleList(Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun MyLazyList() {

    LazyColumn(state = rememberLazyListState()) {
        item{
            Text(text = "Simple List", fontWeight = FontWeight.Bold)
        }
        items(100) {
            Text(text = "LazyList Items $it")
        }
    }
}

@Composable
private fun MySimpleList(modifier: Modifier = Modifier) {

    val verticalScroll = rememberScrollState()
    Column(modifier.verticalScroll(verticalScroll)) {
        Text(text = "Simple List", fontWeight = FontWeight.Bold)
        repeat(20) {
            Text(text = "List Items $it", modifier.padding(20.dp))
        }
    }
}

@Composable
private fun ImageListItems(index: Int) {
    Row(modifier = Modifier.padding(20.dp)){
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "Hello Android $index", style = MaterialTheme.typography.body2)
    }
}

@Composable
private fun ImageList() {
    LazyColumn(state = rememberLazyListState()) {
        items(100) {
            ImageListItems(it)
        }
    }
}

@Composable
private fun ImageListItemsWithButtons() {
    val listItems = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll To the Top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listItems-1)
                }
            }) {
                Text(text = "Scroll to the Last")
            }
        }
    }

    LazyColumn(state = scrollState) {
        items(listItems) {
            ImageListItems(index = it)
        }
    }
}

@Composable
private fun MyAppBody(modifier: Modifier = Modifier) {
//    Column(modifier = modifier) {
//        Text(text = "Hello world", modifier.padding(20.dp), fontWeight = FontWeight.SemiBold)
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//            Text(text = "20 minutes Ago")
//        }
//    }
//    Row {
////        MySimpleList()
////        MyLazyList()
//        //ImageList()
//
//    }
    ImageListItemsWithButtons()
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview() {
    JetpackComposeAppTheme {

    }
}
