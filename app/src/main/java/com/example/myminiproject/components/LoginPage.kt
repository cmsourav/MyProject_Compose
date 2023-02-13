package com.example.myminiproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myminiproject.R
import com.example.myminiproject.components.util.EmptyFieldMsg

@Composable
fun LoginPage(
    userEmail: MutableState<String>,
    password: MutableState<String>,
    onClick: () -> Unit,
    isEmptyCred: MutableState<Boolean>,
    isLoginCredMismatch: MutableState<Boolean>
) {
    var passwordVisible = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "logo",
            modifier = Modifier
                .width(300.dp)
                .height(160.dp)
                .align(Alignment.CenterHorizontally)
        )
        LoginTextField(
            text = userEmail.value,
            label = "Email",
            onChange = { userEmail.value = it },
            imeAction = ImeAction.Next,
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Email,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        LoginTextField(
            text = password.value,
            label = "Password",
            onChange = { password.value = it },
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (passwordVisible.value) { VisualTransformation.None } else {
                PasswordVisualTransformation()
            },
            keyBoardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            trailingIcon = {
                val image = if (passwordVisible.value) {
                    R.drawable.ic_baseline_visibility_24
                } else R.drawable.ic_baseline_visibility_off_24

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(painter = painterResource(id = image), contentDescription = "visible")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (isEmptyCred.value) {
            EmptyFieldMsg(text = "Credentials are missing")
        }
        if (isLoginCredMismatch.value) {
            EmptyFieldMsg(text = "Email or password are incorrect")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                onClick()
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Login",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = PasswordVisualTransformation()
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        label = { Text(text = label, style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Gray,
            unfocusedLabelColor = Color.Gray

        ),
        modifier = modifier
    )
}
