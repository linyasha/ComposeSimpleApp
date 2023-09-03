package com.example.composesimpleapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composesimpleapp.R
import com.example.composesimpleapp.ui.theme.Blue900
import com.example.composesimpleapp.ui.theme.Dark
import com.example.composesimpleapp.ui.theme.Green900
import com.example.composesimpleapp.ui.theme.Grey
import com.example.composesimpleapp.ui.theme.ThemeToggleBackgroundDay
import com.example.composesimpleapp.ui.theme.ThemeToggleBackgroundNight
import com.example.composesimpleapp.ui.theme.ThemeVariants

@Composable
fun ThemeToggle(currentTheme: ThemeVariants, systemTheme: ThemeVariants, onThemeChanged: (ThemeVariants) -> Unit) {
    val onSelectionChange = {
        val newThemeState = when (currentTheme) {
            ThemeVariants.Day -> ThemeVariants.System
            ThemeVariants.System -> ThemeVariants.Night
            else -> ThemeVariants.Day
        }
        onThemeChanged(newThemeState)
    }

    Surface(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Transparent),
        shadowElevation = 4.dp,

    ) {
        val stateInfo = when (currentTheme) {
            ThemeVariants.Day -> StateInfo(
                Arrangement.Start,
                ThemeToggleBackgroundDay,
                Color.White,
                R.drawable.ic_day_theme,
                Green900
            )

            ThemeVariants.System -> {
                val systemThemeIsNight = systemTheme is ThemeVariants.Night
                StateInfo(
                    Arrangement.Center,
                    if (systemThemeIsNight) ThemeToggleBackgroundNight else ThemeToggleBackgroundDay,
                    if (systemThemeIsNight) Dark else Color.White,
                    R.drawable.ic_system_theme,
                    if (systemThemeIsNight) Blue900 else Grey
                )
            }

            else -> StateInfo(
                Arrangement.End,
                ThemeToggleBackgroundNight,
                Dark,
                R.drawable.ic_night_theme,
                Blue900
            )
        }
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(stateInfo.shapeBackground)
                .width(68.dp)
                .height(32.dp)
                .padding(4.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onSelectionChange()
                },
            horizontalArrangement = stateInfo.arrangement
        ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(stateInfo.circleColor),
                painter = painterResource(id = stateInfo.iconId),
                tint = stateInfo.iconTint,
                contentDescription = null,
            )
        }
    }
}

private data class StateInfo(
    val arrangement: Arrangement.Horizontal,
    val shapeBackground: Color,
    val circleColor: Color,
    val iconId: Int,
    val iconTint: Color
)