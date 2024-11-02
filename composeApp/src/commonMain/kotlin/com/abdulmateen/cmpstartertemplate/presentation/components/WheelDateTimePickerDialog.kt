package com.abdulmateen.cmpstartertemplate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdulmateen.cmpstartertemplate.utils.formatDatePlatform
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.TimeFormat
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults

@Composable
fun WheelDateTimePickerDialog(
    showDatePicker: Boolean,
    toggleDatePicker: (Boolean) -> Unit,
    onDateSelection: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }

        WheelDatePickerView(
            modifier = Modifier.padding(top = 18.dp, bottom = 10.dp).fillMaxWidth(),
            showDatePicker = showDatePicker,
            titleStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
            ),
            doneLabelStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF007AFF),
            ),
            selectorProperties = WheelPickerDefaults.selectorProperties(
                borderColor = Color.LightGray,
            ),
//            timeFormat = TimeFormat.AM_PM,
            dateTextColor = Color(0xff007AFF),
            rowCount = 5,
            height = 170.dp,
            dateTextStyle = TextStyle(
                fontWeight = FontWeight(600),
            ),
            onDoneClick = {
                selectedDate = formatDatePlatform(it.atStartOfDayIn(TimeZone.currentSystemDefault()))
                onDateSelection(selectedDate)
                toggleDatePicker(false)
            },
            dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
            onDismiss = {
                toggleDatePicker(false)
            }
        )
}