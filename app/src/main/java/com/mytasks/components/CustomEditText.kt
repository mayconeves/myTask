package com.mytasks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mytasks.ui.theme.Black
import com.mytasks.ui.theme.LightBlue
import com.mytasks.ui.theme.ShapeCustomEditText
import com.mytasks.ui.theme.White

@Composable
fun CustomEditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,

        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            cursorColor = Black,
            focusedTextColor = Black,
            focusedBorderColor = LightBlue,
            focusedLabelColor = LightBlue
        ),
        shape = ShapeCustomEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )

}

@Composable
@Preview
private fun CustomEditTextPreview() {
    CustomEditText(
        "Teste",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = "Descrição",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )
}