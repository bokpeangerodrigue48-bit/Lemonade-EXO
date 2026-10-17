package ci.pigier.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ci.pigier.lemonade.ui.theme.LemonadeTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()

                }
            }
        }
    }
//affichage l'Etape courante de la preparation et des pression à effectuer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(){
    var etape by rememberSaveable { mutableIntStateOf(1) }
    var squeezecount by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text(text="Lemonade")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF9E44C)
                )
            )
        }
    ){ innerPadding->
            EtapeCitronnade(
                etape = etape,
                onImageClick = {
                    when (etape) {
                        1 -> {
                            squeezecount = (2..4).random()
                            etape = 2
                        }

                        2 -> {
                            squeezecount--
                            if (squeezecount==0) etape = 3
                        }

                        3 -> etape = 4
                        else -> etape = 1
                    }
                },
                modifier= Modifier.fillMaxSize().padding(innerPadding)
            )
    }
}

@Composable
fun EtapeCitronnade(
    etape: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //  choix du texte, d'image et de description pour chauqe étape
    val texteRes: Int
    val imageRes: Int
    val descriptionRes: Int
    when (etape) {
        1 -> {
            texteRes = R.string.lemon_select
            imageRes = R.drawable.lemon_tree
            descriptionRes = R.string.lemon_tree_content_description
        }
        2 -> {
            texteRes = R.string.lemon_squeeze
            imageRes = R.drawable.lemon_squeeze
            descriptionRes = R.string.lemon_content_description
        }
        3 -> {
            texteRes = R.string.lemon_drink
            imageRes = R.drawable.lemon_drink
            descriptionRes = R.string.lemonade_content_description
        }
        else -> {
            texteRes = R.string.lemon_empty_glass
            imageRes = R.drawable.lemon_restart
            descriptionRes = R.string.empty_glass_content_description
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // ajout de L'image dans un bouton pour qu'on puisse cliquer dessus
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC3ECD2) // vert clair autour de l'image
            )
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = stringResource(descriptionRes),
                modifier = Modifier.padding(16.dp)
            )
        }

        // ajout d'espace pour que le texte ne colle pas à l'image
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(texteRes),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
