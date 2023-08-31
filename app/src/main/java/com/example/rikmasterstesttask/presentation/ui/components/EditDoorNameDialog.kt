package com.example.rikmasterstesttask.presentation.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.rikmasterstesttask.R
import com.example.rikmasterstesttask.presentation.ui.theme.BluePrimary

@Composable
fun EditDoorNameDialog(
    enteredDoorNameInitialValue: String,
    onConfirmButtonClick: (String) -> Unit,
    onDismissButtonClick: () -> Unit
) {
    var enteredDoorName by rememberSaveable {
        mutableStateOf(enteredDoorNameInitialValue)
    }
    AlertDialog(
        onDismissRequest = onDismissButtonClick,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmButtonClick(enteredDoorName)
                }
            ) {
                Text(stringResource(id = R.string.edit_door_name_dialog_confirm_button))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissButtonClick
            ) {
                Text(stringResource(id = R.string.edit_door_name_dialog_dismiss_button))
            }
        },
        title = { Text(text = stringResource(id = R.string.edit_door_name_dialog_title)) },
        text = {
            OutlinedTextField(
                value = enteredDoorName,
                onValueChange = {
                    enteredDoorName = it
                },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.edit_door_name_dialog_shape_radius)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BluePrimary
                )
            )
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.edit_door_name_dialog_shape_radius))
    )
}