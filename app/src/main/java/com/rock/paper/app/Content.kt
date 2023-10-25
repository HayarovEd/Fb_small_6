package com.rock.paper.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rock.paper.app.ui.theme.background
import com.rock.paper.app.ui.theme.titleText

@Composable
fun Content(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = background)
            .padding(10.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You score: ${state.value.scoreMen}",
                fontSize = 25.sp,
                color = titleText
            )
            Text(
                text = "Opp score: ${state.value.scoreComp}",
                fontSize = 25.sp,
                color = titleText
            )
        }
        Spacer(modifier = modifier.height(50.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.weight(1f),
                painter = painterResource(id = state.value.imageMen), contentDescription = ""
            )
            Spacer(modifier = modifier.width(5.dp))
            Image(
                modifier = modifier.weight(1f),
                painter = painterResource(id = state.value.imageComp), contentDescription = ""
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = modifier.weight(1f),
                onClick = {
                    viewModel.click(
                        idMen = 1,
                        imageMen = R.drawable.rock
                    )
                }) {
                Image(painter = painterResource(id = R.drawable.rock), contentDescription = "")
            }
            Spacer(modifier = modifier.width(5.dp))
            Button(
                modifier = modifier.weight(1f),
                onClick = {
                    viewModel.click(
                        idMen = 2,
                        imageMen = R.drawable.scissors
                    )
                }) {
                Image(painter = painterResource(id = R.drawable.scissors), contentDescription = "")
            }
            Spacer(modifier = modifier.width(5.dp))
            Button(
                modifier = modifier.weight(1f),
                onClick = {
                    viewModel.click(
                        idMen = 3,
                        imageMen = R.drawable.paper
                    )
                }) {
                Image(painter = painterResource(id = R.drawable.paper), contentDescription = "")
            }
        }
        if (state.value.message != "") {
            Spacer(modifier = modifier.height(50.dp))
            Text(
                text = "Result: ${state.value.message}",
                fontSize = 25.sp,
                color = titleText
            )
        }
    }
}