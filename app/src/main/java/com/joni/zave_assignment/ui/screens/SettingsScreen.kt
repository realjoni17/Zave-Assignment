package com.joni.zave_assignment.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joni.zave_assignment.R

@Composable
fun Container1(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .padding(bottom = 63.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 32.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "ACCOUNT",
                            color = Color(0xff6b7280),
                            style = TextStyle(
                                fontSize = 13.sp,
                                letterSpacing = 0.5.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                                shape = RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(85.39.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = "avatar%2Ffemale%2F25-35%2FHispanic%2F0",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .requiredSize(size = 40.dp)
                                        .clip(shape = RoundedCornerShape(20.dp)))
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Maria Garcia",
                                            color = Color(0xff0f1722),
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium),
                                            modifier = Modifier
                                                .requiredWidth(width = 97.dp)
                                                .requiredHeight(height = 20.dp)
                                                .wrapContentHeight(align = Alignment.CenterVertically))
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "maria.garcia@gmail.com",
                                            color = Color(0xff6b7280),
                                            style = TextStyle(
                                                fontSize = 13.sp),
                                            modifier = Modifier
                                                .requiredWidth(width = 152.dp)
                                                .requiredHeight(height = 16.dp)
                                                .wrapContentHeight(align = Alignment.CenterVertically))
                                    }
                                }
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
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "LOCATION PREFERENCES",
                            color = Color(0xff6b7280),
                            style = TextStyle(
                                fontSize = 13.sp,
                                letterSpacing = 0.5.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                                shape = RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(59.37.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Use location automatically",
                                        color = Color(0xff0f1722),
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium),
                                        modifier = Modifier
                                            .requiredWidth(width = 202.dp)
                                            .requiredHeight(height = 20.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Requires GPS access",
                                        color = Color(0xff6b7280),
                                        style = TextStyle(
                                            fontSize = 13.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 131.dp)
                                            .requiredHeight(height = 16.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .requiredWidth(width = 48.dp)
                                    .requiredHeight(height = 28.dp)
                                    .clip(shape = RoundedCornerShape(14.dp))
                                    .background(color = Color(0xff00b894))
                                    .padding(start = 22.dp,
                                        end = 2.dp,
                                        top = 2.dp,
                                        bottom = 2.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredSize(size = 24.dp)
                                        .clip(shape = RoundedCornerShape(12.dp))
                                        .background(color = Color.White)
                                        .shadow(elevation = 2.dp,
                                            shape = RoundedCornerShape(12.dp)))
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(78.75.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Column() {
                                            Text(
                                                text = "Search radius",
                                                color = Color(0xff0f1722),
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Medium),
                                                modifier = Modifier
                                                    .requiredWidth(width = 105.dp)
                                                    .requiredHeight(height = 20.dp)
                                                    .wrapContentHeight(align = Alignment.CenterVertically))
                                        }
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .clip(shape = MaterialTheme.shapes.small)
                                                    .background(color = Color(0xfff59e0b))
                                                    .padding(horizontal = 6.dp,
                                                        vertical = 2.dp)
                                            ) {
                                                Text(
                                                    text = "OVERRIDDEN",
                                                    color = Color(0xff0f1722),
                                                    style = TextStyle(
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Bold),
                                                    modifier = Modifier
                                                        .requiredWidth(width = 65.dp)
                                                        .requiredHeight(height = 12.dp)
                                                        .wrapContentHeight(align = Alignment.CenterVertically))
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Manual override applied",
                                            color = Color(0xff6b7280),
                                            style = TextStyle(
                                                fontSize = 13.sp),
                                            modifier = Modifier
                                                .requiredWidth(width = 148.dp)
                                                .requiredHeight(height = 16.dp)
                                                .wrapContentHeight(align = Alignment.CenterVertically))
                                    }
                                }
                                Column() {
                                    Text(
                                        text = "10 mi",
                                        color = Color(0xff00b894),
                                        style = TextStyle(
                                            fontSize = 16.sp),
                                        modifier = Modifier
                                            .requiredWidth(width = 40.dp)
                                            .requiredHeight(height = 20.dp)
                                            .wrapContentHeight(align = Alignment.CenterVertically))
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 22.dp)
                                    .padding(vertical = 8.dp)
                            ) {
                                TextField(
                                    value = "",
                                    onValueChange = {},
                                   /* colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xfff1f5f6)),*/
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(height = 6.dp)
                                        .clip(shape = RoundedCornerShape(3.dp)))
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(height = 7.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(255.25.dp, Alignment.Start),
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 0.dp,
                                            y = (-8).dp)
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "1 mi",
                                            color = Color(0xff6b7280),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium),
                                            modifier = Modifier
                                                .requiredWidth(width = 22.dp)
                                                .requiredHeight(height = 15.dp)
                                                .wrapContentHeight(align = Alignment.CenterVertically))
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "50 mi",
                                            color = Color(0xff6b7280),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium),
                                            modifier = Modifier
                                                .requiredWidth(width = 32.dp)
                                                .requiredHeight(height = 15.dp)
                                                .wrapContentHeight(align = Alignment.CenterVertically))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "REMOTE CONFIG (DEBUG)",
                            color = Color(0xff6b7280),
                            style = TextStyle(
                                fontSize = 13.sp,
                                letterSpacing = 0.5.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                                shape = RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(129.58.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp,
                                    vertical = 12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "default_search_radius",
                                    color = Color(0xff6b7280),
                                    style = TextStyle(
                                        fontSize = 13.sp),
                                    modifier = Modifier
                                        .requiredWidth(width = 164.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                            Badge(
                                contentColor = Color(0xfff59e0b)
                            ) {
                                Text(
                                    text = "badgeNumber")
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.78.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp,
                                    vertical = 12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "featured_banner_id",
                                    color = Color(0xff6b7280),
                                    style = TextStyle(
                                        fontSize = 13.sp),
                                    modifier = Modifier
                                        .requiredWidth(width = 140.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "promo_banner_spring",
                                    color = Color(0xff16a34a),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold),
                                    modifier = Modifier
                                        .requiredWidth(width = 164.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(176.39.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp,
                                    vertical = 12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "enable_new_ui",
                                    color = Color(0xff6b7280),
                                    style = TextStyle(
                                        fontSize = 13.sp),
                                    modifier = Modifier
                                        .requiredWidth(width = 101.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "true",
                                    color = Color(0xff00b894),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold),
                                    modifier = Modifier
                                        .requiredWidth(width = 31.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(168.57.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp,
                                    vertical = 12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 1.dp,
                                        bottom = 2.dp)
                            ) {
                                Text(
                                    text = "min_app_version",
                                    color = Color(0xff6b7280),
                                    style = TextStyle(
                                        fontSize = 13.sp),
                                    modifier = Modifier
                                        .requiredWidth(width = 117.dp)
                                        .requiredHeight(height = 13.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically))
                            }
                            Badge(
                                contentColor = Color(0xfff59e0b)
                            ) {
                                Text(
                                    text = "badgeNumber")
                            }
                        }
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White)
                            .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.08f)),
                                shape = RoundedCornerShape(8.dp))
                            .padding(all = 16.dp)
                    ) {
                        Text(
                            text = "Sign Out",
                            color = Color(0xffef4444),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 16.sp),
                            modifier = Modifier
                                .requiredWidth(width = 66.dp)
                                .requiredHeight(height = 20.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 375, heightDp = 764)
@Composable
private fun ContainerPreview() {
    Container1(Modifier)
}