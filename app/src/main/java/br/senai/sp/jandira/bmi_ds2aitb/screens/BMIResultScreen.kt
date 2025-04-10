package br.senai.sp.jandira.bmi_ds2aitb.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi_ds2aitb.R
import br.senai.sp.jandira.bmi_ds2aitb.calcs.bmiCalculate
import br.senai.sp.jandira.bmi_ds2aitb.model.BmiStatus
import br.senai.sp.jandira.bmi_ds2aitb.screens.components.BmiLevel
import br.senai.sp.jandira.bmi_ds2aitb.utils.convertNumberToLocale
import java.util.Locale

@Composable
fun BMIResultScreen(controleDeNavegacao: NavHostController?) {

    val context = LocalContext.current
    val userFile = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)

    val userWeight = userFile.getInt("user_weight", 0)
    val userHeight = userFile.getFloat("user_height", 0.0f)
    val userAge = userFile.getInt("user_age", 0)

    val bmi = bmiCalculate(
        userWeight,
        userHeight.toDouble().div(100)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFF44336),
                        Color(0xFF9C27B0),
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.your_result),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                ),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(130.dp),
                        colors = CardDefaults
                            .cardColors(containerColor = Color.White),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 4.dp,
                            color = bmi.bmiColor
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = String.format(
                                    Locale.getDefault(),
                                    "%.1f",
                                    bmi.bmi.second
                                ),
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = bmi.bmi.first,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = CardDefaults
                            .cardColors(
                                containerColor = Color(0xFFD9D3D3)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.age)
                                )
                                Text(
                                    text = "$userAge",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.weight)
                                )
                                Text(
                                    text = "$userWeight Kg",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.height)
                                )
                                Text(
                                    text = convertNumberToLocale(userHeight.toDouble()),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_blue),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_green),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.NORMAL) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.yellow),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.OVER_WEIGHT) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_orange),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.OBSESITY_1) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.dark_orange),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.OBSESITY_2) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.red),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.OBSESITY_3) true else false
                        )
                    }
                    HorizontalDivider()
                    Button(
                        onClick = {
                            controleDeNavegacao?.navigate("user_data")
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9C27B0)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.new_calculate)
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun BMIResultScreenPreview() {
    BMIResultScreen(null)
}