package br.senai.sp.jandira.fonteseanima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.fonteseanima.ui.theme.Filosofia
import br.senai.sp.jandira.fonteseanima.ui.theme.FontesEAnimaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FontesEAnimaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var visivel = remember {
        mutableStateOf(false)
    }
    var efeitoEntrada = remember {
        mutableStateOf(fadeIn())
    }
    var efeitoSaida = remember {
        mutableStateOf(fadeOut())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Hello $name!",
            fontFamily = Filosofia,
            fontSize = 24.sp,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                visivel.value = !visivel.value
                efeitoEntrada.value = fadeIn(animationSpec = tween(3000))
                efeitoSaida.value = fadeOut(animationSpec = tween(3000))
            }) {
                Text(text = "fade")
            }
            Button(onClick = {
                visivel.value = !visivel.value
                efeitoEntrada.value = expandHorizontally(animationSpec = tween(3000))
                efeitoSaida.value = shrinkHorizontally()
            }) {
                Text(text = "Expand")
            }
            Button(onClick = {
                visivel.value = !visivel.value
                efeitoEntrada.value = slideInHorizontally()
                efeitoSaida.value = shrinkHorizontally() + fadeOut(tween(3000))
            }) {
                Text(text = "Slide")
            }
            Button(onClick = {
                visivel.value = !visivel.value
                efeitoEntrada.value = scaleIn()
                efeitoSaida.value = scaleOut()
            }) {
                Text(text = "Scale")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        MinhaCaixa(visibilidade = visivel.value, entrada = efeitoEntrada.value, saida = efeitoSaida.value )
    }
}

@Composable
fun MinhaCaixa(visibilidade: Boolean, entrada: EnterTransition, saida: ExitTransition){
    AnimatedVisibility(visible = visibilidade, enter = entrada, exit= saida) {
        Column(
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(horizontal = 60.dp, vertical = 30.dp)
        ) {
            Text(text = "Ye",
                color = Color.White


            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FontesEAnimaTheme {
        Greeting("Android")
    }
}