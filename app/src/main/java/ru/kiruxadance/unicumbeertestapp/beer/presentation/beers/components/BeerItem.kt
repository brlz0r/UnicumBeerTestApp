package ru.kiruxadance.unicumbeertestapp.beer.presentation.beers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ru.kiruxadance.unicumbeertestapp.beer.domain.model.Beer

@Composable
fun BeerItem(
    beer: Beer,
    modifier: Modifier = Modifier,
) {
   Box(
       modifier = modifier,
   ) {
       Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
           Image(painter = rememberAsyncImagePainter(beer.image_url),
               contentDescription = "beer image",
               modifier = Modifier.size(70.dp).padding(start = 8.dp))
           Spacer(modifier = Modifier.width(16.dp))
           Column(
               modifier = Modifier
           ) {
               Text(
                   text = beer.name,
                   fontSize = 20.sp,
                   color = Color.Gray,
                   maxLines = 1,
               )
               Spacer(modifier = Modifier.height(2.dp))
               Text(
                   text = beer.description,
                   style = MaterialTheme.typography.body2,
                   color = Color.Gray,
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )
           }
       }
   }
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp
    )
}