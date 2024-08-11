package com.example.foodapp.View


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.foodapp.R
import com.example.foodapp.data.Credentials
import com.example.foodapp.data.credentialsList
import com.example.foodapp.ui.theme.FoodAppTheme

@Composable
fun LoginPage(
    navController: NavController
){
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
       var email by rememberSaveable {
           mutableStateOf("")
       }
       var password by remember {
           mutableStateOf("")
       }
       FoodTopAppBar()
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxSize()
       ) {
           Image(
               painter = painterResource(id = R.drawable.ai_generated_7925730_1280_removebg_preview),
               contentDescription = "Logo image",
               modifier = Modifier
                   .size(dimensionResource(id = R.dimen.image_szie))
                   .padding(dimensionResource(id = R.dimen.large_padding)),
               contentScale = ContentScale.Crop)
           TextFiled(
               value = email,
               onValueChange = {inputEmail->email=inputEmail},
               label = R.string.email,
               icon = R.drawable.baseline_email_24,
               contentDescription = R.string.email_icon,
               imeAction = ImeAction.Next,
               modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium_padding))
           )
           PasswordTextFiled(
               value = password,
               onValueChange = {inputPass->password=inputPass},
               label = R.string.password,
               Icon = R.drawable.baseline_key_24
               , contentDescription = R.string.password_icon,
               imeAction = ImeAction.Done,
               modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium_padding))
           )
           OutlinedButton(onClick = {
               if(credentialsList.contains(
                       Credentials(email,password))){
                   navController.navigate("HomePage")
               }
               else{
                   Toast.makeText(context,"Wrong credentials input",Toast.LENGTH_LONG).show()
               }
           },
               modifier = Modifier
                   .clip(MaterialTheme.shapes.small)) {
               Text(text = stringResource(R.string.login))
           }
           TextButton(onClick = {
               navController.navigate("SignUp")
           }) {
               Text(text = stringResource(R.string.sign_up))
           }
       }
   }
}
@Composable
fun TextFiled(
    value:String,
    onValueChange:(String)->Unit,
    label: Int,
    icon: Int,
    contentDescription:Int,
    imeAction: ImeAction,
    modifier: Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label ={
            Text(text = stringResource(label))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon)
                , contentDescription = stringResource(contentDescription)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        modifier = modifier)
}
@Composable
fun PasswordTextFiled(
    value:String,
    onValueChange:(String)->Unit,
    label: Int,
    Icon : Int,
    contentDescription:Int,
    imeAction: ImeAction,
    modifier: Modifier
){
    var visibility by remember {
        mutableStateOf(false)
    }
    val icon = if(visibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)   
    }
    else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label ={
            Text(text = stringResource(label))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = Icon)
                , contentDescription = stringResource(contentDescription)
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                visibility = !visibility
            }) {
                 Icon(
                     painter = icon,
                     contentDescription = stringResource(R.string.visibilty_icon)
                 )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        visualTransformation = if(visibility) VisualTransformation.None
        else PasswordVisualTransformation(),
        modifier = modifier)
}
@Preview
@Composable
fun ViewLoginPage(){
    FoodAppTheme {
        //LoginPage()
    }
}