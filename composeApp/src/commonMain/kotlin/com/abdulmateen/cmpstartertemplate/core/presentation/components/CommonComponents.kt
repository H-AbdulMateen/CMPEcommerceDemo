package com.abdulmateen.cmpstartertemplate.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.calendar
import cmpecommercedemo.composeapp.generated.resources.password_hide
import cmpecommercedemo.composeapp.generated.resources.password_visibility
import org.jetbrains.compose.resources.painterResource

@Composable
fun SimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    shape: Shape = MaterialTheme.shapes.medium
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            unfocusedBorderColor = LightGray,
            focusedBorderColor = Gray,
            unfocusedPlaceholderColor = LightGray
        ),
        shape = shape,
        placeholder = { Text(text = placeholder) }
    )
}
@Composable
fun DatePickerOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {  },
        modifier = modifier.clickable(
            onClick = onClick
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            unfocusedBorderColor = LightGray,
            focusedBorderColor = Gray,
            unfocusedPlaceholderColor = LightGray,
            disabledContainerColor = White,
            disabledBorderColor = LightGray,
            disabledTextColor = Black,
            disabledTrailingIconColor = Black
        ),
        shape = shape,
        placeholder = { Text(text = placeholder) },
        enabled = false,
        trailingIcon = {
            Icon(painter = painterResource(Res.drawable.calendar), contentDescription = "Calendar",
                modifier = Modifier
            )
        }
    )
}
@Composable
fun PasswordOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    shape: Shape = MaterialTheme.shapes.medium,
    onVisibilityClick: () -> Unit,
    isVisible: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            unfocusedBorderColor = LightGray,
            focusedBorderColor = Gray,
            unfocusedPlaceholderColor = LightGray
        ),
        shape = shape,
        placeholder = { Text(text = placeholder) },
        trailingIcon = {
            Icon(painter = painterResource(if (isVisible) Res.drawable.password_hide else Res.drawable.password_visibility), contentDescription = "",
                modifier = Modifier.clickable(
                    onClick = onVisibilityClick
                ))
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonSimple(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ){
        Text(text = label)
    }

}