package br.senai.sp.jandira.bmi_ds2aitb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.bmi_ds2aitb.screens.BMIResultScreen
import br.senai.sp.jandira.bmi_ds2aitb.screens.HomeScreen
import br.senai.sp.jandira.bmi_ds2aitb.screens.UserDataScreen
import br.senai.sp.jandira.bmi_ds2aitb.ui.theme.BMIDS2AITBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIDS2AITBTheme {
                var navegacao = rememberNavController()
                NavHost(
                    navController = navegacao,
                    startDestination = "home"
                ){
                    composable(route = "home"){ HomeScreen(navegacao) }
                    composable(route = "dados"){ UserDataScreen(navegacao) }
                    composable(route = "result"){ BMIResultScreen(navegacao) }
                }
            }
        }
    }
}
