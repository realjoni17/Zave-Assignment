/*
package com.joni.zave_assignment.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joni.zave_assignment.R
@Composable
fun Container(modifier: Modifier = Modifier, badgeNumber: String) {
    Column(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .padding(bottom = 114.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                        shape = RoundedCornerShape(8.dp))
                    .padding(all = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "27027b22-f29e-49ca-93d4-7938bb1b212b.jpg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(size = 88.dp)
                            .clip(shape = RoundedCornerShape(6.dp)))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(37.47.dp, Alignment.Start),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Daily Grind Coffee",
                                        color = Color(0xff0f1722),
                                        style = TextStyle(
                                            fontSize = 16.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 141.dp)
                                            .requiredHeight(height = 20.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredSize(size = 14.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_launcher_background),
                                            contentDescription = "SVG",
                                            modifier = Modifier
                                                .requiredSize(size = 14.dp))
                                    }
                                    Badge(
                                        contentColor = Color(0xff0f1722)
                                    ) {
                                        Text(
                                            text = badgeNumber)
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Cafe",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 29.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "•",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 10.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 6.dp)
                                            .requiredHeight(height = 12.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "0.3 mi",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 38.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Open until 8:00 PM",
                                color = Color(0xff16a34a),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xffe6fff6))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "View on Map",
                                color = Color(0xff05524a),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 87.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xff00b894))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "Directions",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 68.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                        shape = RoundedCornerShape(8.dp))
                    .padding(all = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "a3b8bedf-b8bd-4563-a0bc-78ad6ca5726f.jpg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(size = 88.dp)
                            .clip(shape = RoundedCornerShape(6.dp)))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(68.58.dp, Alignment.Start),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Hearth Bakery",
                                        color = Color(0xff0f1722),
                                        style = TextStyle(
                                            fontSize = 16.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 111.dp)
                                            .requiredHeight(height = 20.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredSize(size = 14.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_launcher_background),
                                            contentDescription = "SVG",
                                            modifier = Modifier
                                                .requiredSize(size = 14.dp))
                                    }
                                    Badge(
                                        contentColor = Color(0xff0f1722)
                                    ) {
                                        Text(
                                            text = badgeNumber)
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Bakery",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 43.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "•",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 10.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 6.dp)
                                            .requiredHeight(height = 12.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "0.8 mi",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 38.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Open until 6:00 PM",
                                color = Color(0xff16a34a),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xffe6fff6))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "View on Map",
                                color = Color(0xff05524a),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 87.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xff00b894))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "Directions",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 68.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                        shape = RoundedCornerShape(8.dp))
                    .padding(all = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "aea3481d-4eaa-4ce7-8e17-3177e5132fa0.jpg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(size = 88.dp)
                            .clip(shape = RoundedCornerShape(6.dp)))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(60.14.dp, Alignment.Start),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Oasis Espresso",
                                        color = Color(0xff0f1722),
                                        style = TextStyle(
                                            fontSize = 16.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 119.dp)
                                            .requiredHeight(height = 20.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .requiredSize(size = 14.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_launcher_background),
                                            contentDescription = "SVG",
                                            modifier = Modifier
                                                .requiredSize(size = 14.dp))
                                    }
                                    Badge(
                                        contentColor = Color(0xff0f1722)
                                    ) {
                                        Text(
                                            text = badgeNumber)
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
                                    Text(
                                        text = "Coffee",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 41.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "•",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 10.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 6.dp)
                                            .requiredHeight(height = 12.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column() {
                                    Text(
                                        text = "1.2 mi",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 35.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Closed • Opens 7 AM",
                                color = Color(0xffef4444),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xffe6fff6))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "View on Map",
                                color = Color(0xff05524a),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 87.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 0.5f)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xff00b894))
                            .padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "SVG",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))
                        Column() {
                            Text(
                                text = "Directions",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium),
                                modifier = Modifier
                                    .requiredWidth(width = 68.dp)
                                    .requiredHeight(height = 17.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun Header1(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .background(color = Color(0xfff7fbf9))
            .padding(all = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(weight = 1f)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color(0xffe6fff6))
                .padding(horizontal = 16.dp,
                    vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredSize(size = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "SVG",
                    modifier = Modifier
                        .requiredSize(size = 20.dp))
            }
            Column(
                modifier = Modifier
                    .weight(weight = 1f)
            ) {
                Text(
                    text = "Coffee shops",
                    color = Color(0xff0f1722),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredSize(size = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "SVG",
                    modifier = Modifier
                        .requiredSize(size = 20.dp))
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredSize(size = 44.dp)
                .clip(shape = RoundedCornerShape(22.dp))
                .background(color = Color(0xffe6fff6))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "SVG",
                modifier = Modifier
                    .requiredSize(size = 20.dp))
        }
    }
}



@Composable
fun HorizontalBorder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 57.dp)
    ) {
        InputChip(
            label = {
                Text(
                    text = "Nearest",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .requiredWidth(width = 49.dp)
                        .requiredHeight(height = 16.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            shape = RoundedCornerShape(12.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color(0xff00b894)
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 16.dp,
                    y = 0.dp))
        InputChip(
            label = {
                Text(
                    text = "Top Rated",
                    color = Color(0xff05524a),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .requiredWidth(width = 63.dp)
                        .requiredHeight(height = 16.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            shape = RoundedCornerShape(12.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color(0xffe6fff6)
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 104.83.dp,
                    y = 0.dp))
        InputChip(
            label = {
                Text(
                    text = "Open Now",
                    color = Color(0xff05524a),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .requiredWidth(width = 65.dp)
                        .requiredHeight(height = 16.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            shape = RoundedCornerShape(12.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color(0xffe6fff6)
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 207.38.dp,
                    y = 0.dp))
        InputChip(
            label = {
                Text(
                    text = "Offers Delivery",
                    color = Color(0xff05524a),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .requiredWidth(width = 93.dp)
                        .requiredHeight(height = 16.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            shape = RoundedCornerShape(12.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color(0xffe6fff6)
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 312.55.dp,
                    y = 0.dp))
    }
}

@Preview(widthDp = 375, heightDp = 57)
@Composable
private fun HorizontalBorderPreview() {
    HorizontalBorder(Modifier)
}

@Preview(widthDp = 375, heightDp = 77)
@Composable
private fun HeaderPreview() {
    Header(Modifier)
}


@Preview(widthDp = 375, heightDp = 683)
@Composable
private fun ContainerPreview() {
    Container(Modifier, "4.9")
}*/
