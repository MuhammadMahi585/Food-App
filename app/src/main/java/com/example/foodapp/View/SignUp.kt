package com.example.foodapp.View

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodapp.R
import com.example.foodapp.data.Credentials
import com.example.foodapp.data.credentialsList
import com.example.foodapp.ui.theme.FoodAppTheme

@Composable
fun SignUp(
    navController: NavController
){
          var name by rememberSaveable{
              mutableStateOf("")
          }
           var email by rememberSaveable{
               mutableStateOf("")
            }
           var password by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column (
        modifier = Modifier
    ){
        FoodTopAppBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.medium_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextUsage(
                value = stringResource(id = R.string.sign_up),
                fontSize = 55,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.background),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.large_padding))
            )
            TextFiled(
                value = name,
                onValueChange = { newName -> name = newName },
                label = R.string.namesign,
                icon = R.drawable.baseline_drive_file_rename_outline_24,
                contentDescription = R.string.name_icon,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
                    .fillMaxWidth()
            )
            TextFiled(
                value = email,
                onValueChange = { newEmail -> email = newEmail },
                label = R.string.email,
                icon = R.drawable.baseline_email_24,
                contentDescription = R.string.email_icon,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
                    .fillMaxWidth()
            )
            PasswordTextFiled(
                value = password,
                onValueChange = { newPass -> password = newPass },
                label = R.string.password,
                Icon = R.drawable.baseline_key_24,
                contentDescription = R.string.password_icon,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
                    .fillMaxWidth()
            )
            TextFiled(
                value = phone,
                onValueChange = { newPhone -> phone = newPhone },
                label = R.string.phone,
                icon = R.drawable.baseline_phone_24,
                contentDescription = R.string.phone,
                imeAction = ImeAction.Done,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding))
                    .fillMaxWidth()
            )
            OutlinedButton(onClick = {
                credentialsList
                    .add(
                        Credentials(email,password))
                       Toast.makeText(context,"Account Created",Toast.LENGTH_SHORT).show()
                        navController.navigate("Login")             },
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .padding(dimensionResource(id = R.dimen.medium_padding))
            ) {
        Text(text = stringResource(id = R.string.sign_up))
     }
        }
    }
}

@Composable
fun TextUsage(
    value:String,
    fontSize:Int,
    fontWeight: FontWeight,
    color: Color,
    modifier: Modifier
){
    Text(text = value,
         style = TextStyle(
             fontSize = fontSize.sp,
             fontWeight = fontWeight
         ),
        color = color,
        modifier = modifier
    )
}
@Composable
@Preview
fun SignUpView(){
    FoodAppTheme {
       // SignUp()
    }
}