package com.wordpress.safbk.jetpackcomposeapp

import android.content.ClipData
import android.os.Bundle
import android.preference.PreferenceActivity
import android.widget.HeaderViewListAdapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wordpress.safbk.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyRecycler(SampleData.conversationSample)

                }
            }

        }
    }
}

data class MessageData(val author: String, val message: String)

@Composable
fun Message2(msg: MessageData) {
    Row {
        Image(painter = painterResource(id = R.drawable.pf),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clip(shape = CircleShape)
                .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )

        Column {
            Text(text = msg.author,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondaryVariant,
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = msg.message,
            style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 15.sp,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}

@Composable
fun MessageCard(name: String, message: String) {
    Row() {
        Image(painter = painterResource(id = R.drawable.pf)
            , contentDescription = null
            , modifier = Modifier
                .size(20.dp)
                .clip(shape = CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Column {
            Text(text = name,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp
                )
            
            Spacer(modifier = Modifier.size(4.2.dp))
            
            Text(text = message,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.body2,
                fontSize = 12.sp
            )
        }

    }
}

@Composable
fun MyBoxCompose() {
    Box(modifier = Modifier
        .background(MaterialTheme.colors.secondaryVariant)
        .padding(10.dp)
    ) {
        Column {
            Text(text = "First Line",
            color = MaterialTheme.colors.secondary)
            Text(text = "Second Line",
            color = MaterialTheme.colors.secondary)
            Text(text = "Third Line",
            color = MaterialTheme.colors.secondary)

            Box(modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
            ) {
                Column {
                    Text(text = "Second Box First Line")
                    Text(text = "Second Box Second Line")
                    Text(text = "Second Box Third Line")

                    Box(modifier = Modifier
                        .background(MaterialTheme.colors.secondary)
                        .padding(10.dp)
                    ) {
                        Column {
                            Text(text = "third box Line")
                            Text(text = "third box second line")
                            Text(text = "third box third line")
                            Box(modifier = Modifier
                                .background(MaterialTheme.colors.primaryVariant)
                            ) {
                                Image(painter = painterResource(id = R.drawable.pf),
                                    contentDescription = null,
                                    Modifier.clip(shape = CircleShape)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}


@Composable
private fun MyList() {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ,modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)){

        item {
            Text(text = "Hello world")
        }

        item {
            Text(text = "My Name is Khan")
        }

        item {
            Text(text = "This is list")
        }

        items(5) { index ->
            Text(text = "Item: $index")
        }

    }
}


///////////////////

class MyConversation(val authors: String, val messages: String)

////////////////////////////


@Composable
fun MessageUI(myConversation: MyConversation) {
    var isExpended by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .clickable { isExpended = !isExpended }
        .padding(20.dp)
        .background(color = Color.LightGray)
    ) {
        Image(painter = painterResource(id = R.drawable.pf)
            , contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
        )
        Text(text = myConversation.authors,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 25.sp)

        Text(text = myConversation.messages,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle2,
        fontSize = 20.sp,
        maxLines = if (isExpended) Int.MAX_VALUE else 1
        )
    }
    Spacer(modifier = Modifier.width(5.dp))
}

@Composable
fun MyRecycler(messages: List<MyConversation>) {
    LazyColumn {
        items(messages) { nn ->
            MessageUI(nn)
        }
    }
}


@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}


@Preview
@Composable
fun MyBoxPreview() {
    //MyList()
    //Message2(msg = MessageData("faraz", "djlsjfs"))
    //MessageUI(MyConversation("Faraz", "hldjfsdjfs"))
    MyRecycler(SampleData.conversationSample)
}