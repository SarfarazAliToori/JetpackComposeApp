package com.wordpress.safbk.jetpackcomposeapp.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wordpress.safbk.jetpackcomposeapp.ui.theme.ui.theme.JetpackComposeAppTheme
import org.intellij.lang.annotations.JdkConstants

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    MyApp()
//                }
                MyApp()
                //Greetingsss()
            }
        }
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("One", "Two")) {

    //val shouldShowOnboarding = remember { mutableStateOf(true) }
    val shouldShowOnboarding = rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding.value) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding.value = false })
    } else {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                for (name in names) {
                    //Greeting(name = name)
                    Greetingsss()
                }
            }
        }
    }
}


@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}




@Composable
fun Greeting(name: String) {

    val isExpended = remember { mutableStateOf(false) }

    //val extraExpended = if (isExpended.value) 48.dp else 0.dp
    // .... !!!!!!!vvvvv
    val extraPadding = animateDpAsState(
        if (isExpended.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(8.dp, 8.dp)
    ) {
        //Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                //.padding(botom = extraPadding)
                .padding(bottom = extraPadding.value.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                //Text(text = name, style = MaterialTheme.typography.h4)
                Text(text = name, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold))
                if (isExpended.value) {
                    Text(text = "Hi Iam Expended")
                } else {
                    Text("Heheheheh")
                }
            }

            OutlinedButton(onClick = {isExpended.value = !isExpended.value}) {
                Text(if (isExpended.value) "Show Less" else "Show More")
            }
        }
    }
}

//val names: List<String> = List(100) {"ttt"}
@Composable
fun Greetingsss(names: List<String> = List(100) {"$it"}) {
    LazyColumn(modifier =  Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}



@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeAppTheme {
        //MyApp()
        Greetingsss()
    }
}