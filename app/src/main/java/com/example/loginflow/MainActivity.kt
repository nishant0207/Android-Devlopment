package com.example.loginflow

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}



@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginScreen",
    ) {
        composable("LoginScreen"){
            MyAppContent(navController = navController)
        }

        composable("Screen2"){
            Screen2( navController = navController)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppContent(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Image(painter = painterResource(id = R.drawable.img99), contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds,
            modifier =  Modifier
                .height(1200.dp)
                .width(500.dp)
        )
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            text = "Login",
            color = Color.White,
            style = TextStyle(fontSize = 60.sp, fontWeight = FontWeight.Bold,),
            modifier = Modifier.padding(bottom = 16.dp)
        )


        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showNotification by remember { mutableStateOf(false)}
        val context = LocalContext.current

        TextField(
            value = username,
            onValueChange = { newUsername -> username = newUsername
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.baseline_email_24),
                    contentDescription = "Email Icon"
                )
            },
            label = {
                Text(text = "Username", fontWeight = FontWeight.ExtraBold, color = Color.LightGray)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.Transparent
            ),
            )


        TextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.baseline_lock_24),
                    contentDescription = "Lock Icon"
                )
            },
            label={
                Text(text = "Password", fontWeight = FontWeight.ExtraBold, color=Color.LightGray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done

            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.Transparent
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        if(showNotification){
            Text(
                text = "Fill username and password!",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
             onClick = {
                 if(username.isNotEmpty() && password.isNotEmpty()) {
                     navController.navigate("Screen2")
                 }
                 else{
                     showNotification = true
                 }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors
                (
                    containerColor = Color(255,153,0),
                    contentColor = Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                        Text(text = "Sign In")
                }

        Text(
            text = "Login with other options",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier
                .padding(top = 15.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))



            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.google.com")
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(252, 181, 74),
                    contentColor = Color.White
                ),
            )
            {
                Text(
                    text = "Sign in with Google", fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.github.com")
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(252, 181, 74),
                    contentColor = Color.White
                ),
            )
            {
                Text(
                    text = "Sign in with Github", fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://twitter.com/i/flow/signup")
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(252, 181, 74),
                    contentColor = Color.White
                ),
            )
            {
                Text(
                    text = "Sign in with Twitter", fontWeight = FontWeight.Bold
                )

            }
    }
}

// ------------------------------------------------Screen2 ------------------------------------------------------------


@Composable
public fun Screen2(navController: NavController) {

    data class RadioButtonOption(
        val url:String,
        val label:String,
        val textColor: Color
    )

    var selectedUrl by remember{ mutableStateOf("") }
    val context= LocalContext.current
    val radioOptions=listOf(
        RadioButtonOption("https://www.netflix.com","Netflix", Color.White),
        RadioButtonOption("https://www.youtube.com","YouTube", Color.White),
        RadioButtonOption("https://www.apple.com","Apple", Color.White),
        RadioButtonOption("https://www.amazon.com","Amazon", Color.White),
    )

    Box(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.img3),
                contentDescription = "Screen 2 Background Image",
                contentScale = ContentScale.FillBounds
                )
        }
        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center
        ){
            Text(
                text="Choose any option to visit the website",
                modifier= Modifier
                    .padding(start = 30.dp)
                    .padding(end = 30.dp)
                    .padding(bottom = 20.dp)
                    .padding(top = 10.dp),
                style= TextStyle(
                    fontSize=40.sp,
                    fontWeight= FontWeight.Bold,
                    fontStyle= FontStyle.Italic,
                    color= Color.White
                ),
            )

            Column(
                modifier= Modifier
                    .width(350.dp)
                    .height(250.dp)
                    .background(
                        Color(255, 255, 255, (0.3f * 255).toInt()),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(10.dp)
            ){

                radioOptions.forEach{option->
                    Row(
                        modifier= Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (selectedUrl == option.url),
                                onClick = {
                                    selectedUrl = option.url
                                }
                            )
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment= Alignment.CenterVertically
                    ){
                        RadioButton(
                            selected=(selectedUrl==option.url),
                            onClick={
                                selectedUrl=option.url
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Green,
                                unselectedColor = Color.White
                            )
                        )
                        Spacer(modifier= Modifier.width(10.dp))
                        Text(text=option.label,fontSize=20.sp,fontWeight= FontWeight.Bold,color=option.textColor)
                    }
                }
            }
        }
        Button(
            onClick={
                if(selectedUrl.isNotEmpty()){
                    val intent= Intent(Intent.ACTION_VIEW, Uri.parse(selectedUrl))
                    context.startActivity(intent)
                }else{

                }
            },
            modifier= Modifier
                .padding(top = 600.dp)
                .align(Alignment.TopCenter)
                .height(50.dp)
                .width(270.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(25, 255, 255, (0.5f * 255).toInt()),
                contentColor = Color.Black
            )
        ){
            Text("Visit Website", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
        }

        Button(
            onClick = {
                    navController.navigate("LoginScreen")
            },

            modifier= Modifier
                .padding(top = 670.dp)
                .align(Alignment.TopCenter)
                .height(50.dp)
                .width(270.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(25, 255, 255, (0.5f * 255).toInt()),
                contentColor = Color.Black
            )

        ) {
                Text(text = "Login Screen", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
        }
    }
}