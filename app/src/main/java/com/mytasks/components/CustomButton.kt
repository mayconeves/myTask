package com.mytasks.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mytasks.ui.theme.LightBlue
import com.mytasks.ui.theme.White

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    value: String
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue,
            contentColor = White
        )
    ) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

    }
}