package com.example.myminiproject.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myminiproject.R
import com.example.myminiproject.components.util.*
import com.example.myminiproject.model.OverView
import com.example.myminiproject.model.ReportModel
import com.example.myminiproject.nav_utils.NavStudentPage
import com.example.myminiproject.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun StudentScreen(drawerState: DrawerState, homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    StudentScreenNavigation(navController, drawerState)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun StudentScreenNavigation(navController: NavHostController, drawerState: DrawerState) {
    /* Personal Details */
    val radioOptions = listOf("Male", "Female", "Other")
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(radioOptions[0]) }
    var firstName = rememberSaveable { mutableStateOf("") }
    var lastName = rememberSaveable { mutableStateOf("") }
    var fatherName = rememberSaveable { mutableStateOf("") }
    var dob = rememberSaveable { mutableStateOf("") }
    var studentMobileNumber = rememberSaveable { mutableStateOf("") }
    var fatherMobileNumber = rememberSaveable { mutableStateOf("") }
    var studentEmail = rememberSaveable { mutableStateOf("") }

    /* Course Details */
    val couse_radioOptions = listOf("UG", "PG", "Diploma")
    val (course_selectedOption, course_onOptionSelected) = rememberSaveable { mutableStateOf("UG") }
    var category: MutableState<String> = mutableStateOf("")
    var course: MutableState<String> = mutableStateOf("")
    var otherCourse: MutableState<String> = rememberSaveable { mutableStateOf("") }
    var academicYear: MutableState<String> = mutableStateOf("")
    var dateofAdmsn: MutableState<String> = mutableStateOf("")
    var certificateCollect: MutableState<String> = rememberSaveable { mutableStateOf("") }

    /* College Details */
    val selectedPlace: MutableState<String> = mutableStateOf("")
    val otherPlace: MutableState<String> = mutableStateOf("")
    val college: MutableState<String> = mutableStateOf("")
    val otherCollege: MutableState<String> = mutableStateOf("")

    /* Fees Details */
    val totalAmount: MutableState<String> = mutableStateOf("")
    val feesPer: MutableState<String> = mutableStateOf("")
    val admsnFee: MutableState<String> = mutableStateOf("")
    val serviceCharge: MutableState<String> = mutableStateOf("")
    val associate: MutableState<String> = mutableStateOf("")
    val feesPaid: MutableState<String> = mutableStateOf("")
    val remark: MutableState<String> = mutableStateOf("")

    /* Reference Details */
    val refName: MutableState<String> = mutableStateOf("")
    val serviceChargeOffered: MutableState<String> = mutableStateOf("")
    val contactInfo: MutableState<String> = mutableStateOf("")

    var isFirstNameEmpty: MutableState<Boolean> = mutableStateOf(false)
    var isFatherNameEmpty: MutableState<Boolean> = mutableStateOf(false)
    var isStudentMobileEmpty: MutableState<Boolean> = mutableStateOf(false)
    var isFatherMobileEmpty: MutableState<Boolean> = mutableStateOf(false)
    var isStudentEmailEmpty: MutableState<Boolean> = mutableStateOf(false)

    NavHost(navController = navController, startDestination = NavStudentPage.StudentMainScreen.route) {
        composable(NavStudentPage.StudentMainScreen.route) {
            StudentMainScreen(drawerState = drawerState, onClick = {
                navController.navigate(NavStudentPage.RegistrationPersonalInfo.route)
            })
        }
        composable(NavStudentPage.RegistrationPersonalInfo.route) {
            RegistrationPersonalInfo(
                firstName = firstName,
                lastName = lastName,
                fatherName = fatherName,
                dob = dob,
                studentMobileNumber = studentMobileNumber,
                fatherMobileNumber = fatherMobileNumber,
                studentEmail = studentEmail,
                radioOptions = radioOptions,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
                isFirstNameEmpty = isFirstNameEmpty,
                isFatherNameEmpty = isFatherNameEmpty,
                isStudentMobileEmpty = isStudentMobileEmpty,
                isFatherMobileEmpty = isFatherMobileEmpty,
                isStudentEmailEmpty = isStudentEmailEmpty,
                drawerState = drawerState,
                onNextButtonClick = { navController.navigate(NavStudentPage.RegistrationCourseDetails.route) }
            )
        }
        composable(NavStudentPage.RegistrationCourseDetails.route) {
            RegistrationCourseDetails(
                category = category,
                course = course,
                otherCourse = otherCourse,
                academicYear = academicYear,
                certificateCollect = certificateCollect,
                dateofAdmsn = dateofAdmsn,
                radioOptions = couse_radioOptions,
                selectedOption = course_selectedOption,
                onOptionSelected = course_onOptionSelected,
                drawerState = drawerState,
                onNextButtonClick = { navController.navigate(NavStudentPage.CollegeDetails.route) }
            )
        }
        composable(NavStudentPage.CollegeDetails.route) {
            CollegeDetails(
                selectedPlace = selectedPlace,
                otherPlace = otherPlace,
                college = college,
                otherCollege = otherCollege,
                drawerState = drawerState,
                onNextButtonClick = { navController.navigate(NavStudentPage.FeesDetails.route) }
            )
        }
        composable(NavStudentPage.FeesDetails.route) {
            FeesDetails(
                totalAmount = totalAmount,
                feesPer = feesPer,
                admsnFee = admsnFee,
                serviceCharge = serviceCharge,
                associate = associate,
                feesPaid = feesPaid,
                remark = remark,
                drawerState = drawerState,
                onNextButtonClick = { navController.navigate(NavStudentPage.ReferenceDetails.route) }
            )
        }
        composable(NavStudentPage.ReferenceDetails.route) {
            ReferenceDetails(
                refName = refName,
                ser_chargeOffered = serviceChargeOffered,
                contactInfo = contactInfo,
                drawerState = drawerState,
                onNextButtonClick = { navController.navigate(NavStudentPage.StudentMainScreen.route) }
            )
        }
    }
}

@Composable
fun StudentMainScreen(drawerState: DrawerState, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopBarHeader(
                openDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                screenName = "Student"
            )
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            val datas = listOf<OverView>(
                OverView(state = "Tamilnadu", count = 60),
                OverView(state = "Bangalore", count = 60),
                OverView(state = "Mysore", count = 8),
                OverView(state = "Mangalore", count = 5)
            )

            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
            Spacer(modifier = Modifier.height(12.dp).background(Color.Gray))
            OverViewCardUI(overView = datas)
        }
        RegisterButton(onClick = onClick)
    }
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 10.dp)
        ) {
            IconButton(onClick = onClick) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(50.dp)
                        .shadow(elevation = 6.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "edit",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

/* Registration UI*/
@Composable
fun ReferenceDetails(
    refName: MutableState<String>,
    ser_chargeOffered: MutableState<String>,
    contactInfo: MutableState<String>,
    drawerState: DrawerState,
    onNextButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Student"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Text(
                text = "Reference Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Reference Name")
            CustomTextField(
                text = refName.value,
                placeholder = "Enter reference name",
                onChange = { refName.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Service Charge Offered")
            CustomTextField(
                text = ser_chargeOffered.value,
                placeholder = "Enter service charge offered",
                onChange = { ser_chargeOffered.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Contact Info")
            CustomTextField(
                text = contactInfo.value,
                placeholder = "Enter contact info",
                onChange = { contactInfo.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            SingleButton(
                onSingleButtonClick = {
                    if (
                        refName.value.isNotEmpty() &&
                        ser_chargeOffered.value.isNotEmpty() &&
                        contactInfo.value.isNotEmpty()
                    ) {
                        onNextButtonClick()
                    } else {}
                },
                btnBackground = if (
                    refName.value.isNotEmpty() &&
                    ser_chargeOffered.value.isNotEmpty() &&
                    contactInfo.value.isNotEmpty()
                ) {
                    Color.Black
                } else {
                    Color.LightGray
                },
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Composable
fun FeesDetails(
    totalAmount: MutableState<String>,
    feesPer: MutableState<String>,
    admsnFee: MutableState<String>,
    serviceCharge: MutableState<String>,
    associate: MutableState<String>,
    feesPaid: MutableState<String>,
    remark: MutableState<String>,
    drawerState: DrawerState,
    onNextButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val feesAsPer = listOf(
        "Year",
        "Semester",
        "Package"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Student"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Text(
                text = "Fees Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Total Amount")
            CustomTextField(
                text = totalAmount.value,
                placeholder = "Enter fees...",
                onChange = { totalAmount.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Fees Per")
            AutoCompleteTextField(
                courses = feesPer,
                focusManager = focusManager,
                categories = feesAsPer
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Admission Fee")
            CustomTextField(
                text = admsnFee.value,
                placeholder = "Enter admission fees...",
                onChange = { admsnFee.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Service Charge")
            CustomTextField(
                text = serviceCharge.value,
                placeholder = "Enter admission fees...",
                onChange = { serviceCharge.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Associate")
            CustomTextField(
                text = associate.value,
                placeholder = "Enter admission fees...",
                onChange = { associate.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Fees Paid")
            CustomTextField(
                text = feesPaid.value,
                placeholder = "Enter Amount Paid...",
                onChange = { feesPaid.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Remark")
            CustomTextField(
                text = remark.value,
                placeholder = "Enter Amount Paid...",
                onChange = { remark.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            SingleButton(
                onSingleButtonClick = {
                    if (
                        totalAmount.value.isNotEmpty() &&
                        feesPer.value.isNotEmpty() &&
                        admsnFee.value.isNotEmpty() &&
                        serviceCharge.value.isNotEmpty() &&
                        associate.value.isNotEmpty() &&
                        feesPaid.value.isNotEmpty() &&
                        remark.value.isNotEmpty()
                    ) {
                        onNextButtonClick()
                    } else{}
                },
                btnBackground = if (
                    totalAmount.value.isNotEmpty() &&
                    feesPer.value.isNotEmpty() &&
                    admsnFee.value.isNotEmpty() &&
                    serviceCharge.value.isNotEmpty() &&
                    associate.value.isNotEmpty() &&
                    feesPaid.value.isNotEmpty() &&
                    remark.value.isNotEmpty()
                ) {
                    Color.Black
                } else {
                    Color.LightGray
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CollegeDetails(
    selectedPlace: MutableState<String>,
    otherPlace: MutableState<String>,
    college: MutableState<String>,
    otherCollege: MutableState<String>,
    drawerState: DrawerState,
    onNextButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val tn_college = listOf(
        "Excel Engineering College",
        "KSR College",
        "Mahendra Eng College",
        "Paavai Eng College",
        "JKKM Eng College",
        "Other"
    )

    val bnglr_college = listOf(
        "Garden city college",
        "T.John Eng College",
        "RR college",
        "HKBK College",
        "PES University",
        "Other"
    )

    val manglr_college = listOf(
        "Yenapoya College",
        "PA College",
        "Shree devi College",
        "Alvas",
        "Srinivasa College",
        "Other"
    )

    val mysore_college = listOf(
        "JSS College",
        "Nss College",
        "Something",
        "Other"
    )

    val kerala_college = listOf(
        "KSS college",
        "TKM",
        "NSS Palakkad",
        "CUSAT",
        "Other"
    )

    val buttonTextList =
        remember {
            mutableListOf(
                ReportModel("TamilNadu"),
                ReportModel("Bangalore"),
                ReportModel("Mangalore"),
                ReportModel("Mysore"),
                ReportModel("Kerala"),
                ReportModel("Other")
            )
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Student"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Text(
                text = "College Details  ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "Place")
            ReportList(report = buttonTextList, place = selectedPlace)
            if (selectedPlace.value == "Other") {
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldHeading(text = "Enter Place")
                CustomTextField(
                    text = otherPlace.value,
                    placeholder = "Enter place...",
                    onChange = { otherPlace.value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    keyBoardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldHeading(text = "College Name")
                CustomTextField(
                    text = otherCollege.value,
                    placeholder = "Enter College Name...",
                    onChange = { otherCollege.value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    keyBoardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
            if (selectedPlace.value != "Other") {
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldHeading(text = "College")
                AutoCompleteTextField(
                    college,
                    focusManager,
                    categories = if (selectedPlace.value == "TamilNadu") {
                        tn_college
                    } else if (selectedPlace.value == "Bangalore") {
                        bnglr_college
                    } else if (selectedPlace.value == "Mangalore") {
                        manglr_college
                    } else if (selectedPlace.value == "Mysore") {
                        mysore_college
                    } else if (selectedPlace.value == "Kerala") {
                        kerala_college
                    } else {
                        listOf("No data found")
                    }
                )
            }
            if (college.value == "Other") {
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldHeading(text = "College Name")
                CustomTextField(
                    text = otherCollege.value,
                    placeholder = "Enter College Name...",
                    onChange = { otherCollege.value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    keyBoardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
            SingleButton(
                onSingleButtonClick = {
                    if (
                        selectedPlace.value.isNotEmpty() &&
                        selectedPlace.value != "Other" &&
                        college.value != "Other" &&
                        college.value.isNotEmpty() ||
                        otherCollege.value.isNotEmpty()

                    ) {
                        onNextButtonClick()
                    } else {}
                },
                btnBackground = if (
                    selectedPlace.value.isNotEmpty() &&
                    selectedPlace.value != "Other" &&
                    college.value != "Other" &&
                    college.value.isNotEmpty() ||
                    otherCollege.value.isNotEmpty()

                ) {
                    Color.Black
                } else {
                    Color.LightGray
                },
                modifier = Modifier.padding(top = 30.dp)
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegistrationPersonalInfo(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    fatherName: MutableState<String>,
    dob: MutableState<String>,
    radioOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    studentMobileNumber: MutableState<String>,
    fatherMobileNumber: MutableState<String>,
    studentEmail: MutableState<String>,
    isFirstNameEmpty: MutableState<Boolean>,
    isFatherNameEmpty: MutableState<Boolean>,
    isStudentMobileEmpty: MutableState<Boolean>,
    isFatherMobileEmpty: MutableState<Boolean>,
    isStudentEmailEmpty: MutableState<Boolean>,
    drawerState: DrawerState,
    onNextButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Student"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Personal Details  ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldHeading(text = "First name")
            CustomTextField(
                text = firstName.value,
                placeholder = "First Name",
                onChange = { firstName.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            if (isFirstNameEmpty.value) {
                EmptyFieldMsg(
                    text = "First name can't empty"
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Last name")
            CustomTextField(
                text = lastName.value,
                placeholder = "Last Name",
                onChange = { lastName.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Gender", modifier = Modifier.padding(5.dp))
            SimpleRadioButtonComponent(
                radioOptions = radioOptions,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Date of birth")
            val mDatePickerDialog = datePickerDialog(context, dob)
            if (dob.value.isNotEmpty()) {
                CustomTextField(
                    text = dob.value,
                    placeholder = "Date of birth",
                    onChange = { dob.value = it }
                )
            }
            Button(
                onClick = {
                    mDatePickerDialog.show()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = if (dob.value.isEmpty()) "Choose" else "Change")
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Father name")
            CustomTextField(
                text = fatherName.value,
                placeholder = "Father Name",
                onChange = { fatherName.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            if (isFatherNameEmpty.value) {
                EmptyFieldMsg(
                    text = "Father name can't empty"
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Student contact")
            CustomTextField(
                text = studentMobileNumber.value,
                placeholder = "Student mobile",
                onChange = { studentMobileNumber.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            if (isStudentMobileEmpty.value) {
                EmptyFieldMsg(
                    text = "Student mobile can't empty"
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Father contact")
            CustomTextField(
                text = fatherMobileNumber.value,
                placeholder = "Father mobile",
                onChange = { fatherMobileNumber.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            if (isFatherMobileEmpty.value) {
                EmptyFieldMsg(
                    text = "Father mobile can't empty"
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Student Email")
            CustomTextField(
                text = studentEmail.value,
                placeholder = "Student Email",
                onChange = { studentEmail.value = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            )
            if (isStudentEmailEmpty.value) {
                EmptyFieldMsg(
                    text = "Student Email can't be empty"
                )
            }
            SingleButton(
                onSingleButtonClick = {
                    if (
                        firstName.value.isNotEmpty() &&
                        selectedOption.isNotEmpty() &&
                        dob.value.isNotEmpty() &&
                        fatherName.value.isNotEmpty() &&
                        studentMobileNumber.value.isNotEmpty() &&
                        fatherMobileNumber.value.isNotEmpty() &&
                        studentEmail.value.isNotEmpty()
                    ) {
                        onNextButtonClick()
                    } else {
                    }
                },
                btnBackground = if (
                    firstName.value.isNotEmpty() &&
                    selectedOption.isNotEmpty() &&
                    dob.value.isNotEmpty() &&
                    fatherName.value.isNotEmpty() &&
                    studentMobileNumber.value.isNotEmpty() &&
                    fatherMobileNumber.value.isNotEmpty() &&
                    studentEmail.value.isNotEmpty()
                ) {
                    Color.Black
                } else {
                    Color.LightGray
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegistrationCourseDetails(
    category: MutableState<String>,
    course: MutableState<String>,
    otherCourse: MutableState<String>,
    academicYear: MutableState<String>,
    certificateCollect: MutableState<String>,
    dateofAdmsn: MutableState<String>,
    radioOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    drawerState: DrawerState,
    onNextButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val academicYears = listOf(
        "2021",
        "2022",
        "2023",
        "2024",
        "2025",
        "2026",
        "2027",
        "2028",
        "2029",
        "2030",
        "2031",
        "2032",
        "2033",
        "2034",
        "2035",
        "2036",
        "2037",
        "2038",
        "2039",
        "2040",
        "2041",
        "2042",
        "2043",
        "2044",
        "2045",
        "2046",
        "2047",
        "2048",
        "2049",
        "2050"
    )

    val pgCategory = listOf(
        "ENGINEERING",
        "B.Arch",
        "LAW",
        "Other"
    )

    val ugCategory = listOf(
        "ENGINEERING",
        "B.Arch",
        "ALLIED HEALTH SCIENCE",
        "MEDICAL",
        "ARTS AND SCIENCE/MANAGEMENT COURSES",
        "AGRICULTURAL SCIENCE",
        "LAW",
        "Nursing",
        "Other"
    )

    val ug_nursing = listOf(
        "BSC",
        "POST BSC"
    )

    val ug_law = listOf(
        "LLB",
        "BA LLB",
        "B.Com LLB",
        "BBA LLB"
    )

    val ug_arts_management = listOf(
        "BBA",
        "BCA",
        "B.Com with ACCA",
        "B.Com",
        "BSc Physics",
        "Bsc Chemistry",
        "Bsc Maths",
        "Bsc Botany",
        "Bsc Zoology",
        "Bsc Microbiology",
        "Bsc Biotechnology",
        "Bsc Electronics",
        "Bsc Genetics",
        "Bsc Biochemistry",
        "Bsc Computer Science",
        "Bsc IT",
        "Bsc Statistics",
        "Bsc Hotel Management",
        "Bsc Visual Communication",
        "Bsc Fashion Designing",
        "Bsc Environmental Science",
        "Bsc Aeronautical Science",
        "Bsc Food Technology",
        "Bsc Photography",
        "Bsc Film Tech",
        "Bsc Physical Education",
        "Bsc Sound Engg",
        "Bsc Geology",
        "Bsc Phychology",
        "Bsc Hospital Management",
        "Bsc Criminology",
        "Bsc Nurtrition and Dietics",
        "B.A English",
        "B.A Tamil",
        "B.A Hindi",
        "B.A Journalism",
        "B.A Mass Communication",
        "B.A Tourism",
        "B.A History",
        "B.A Psychology",
        "B.A Economics and Political Science",
        "BSW",
        "Other"
    )

    val ug_medical = listOf(
        "MBBS",
        "BDS",
        "BAMS",
        "BHMS",
        "BNYS",
        "BSMS",
        "Other"
    )

    val ug_alliedHS = listOf(
        "Bsc",
        "Cardiac Care Tech",
        "Perfusion Tech",
        "Dialysis Tech",
        "OT and AT",
        "Radiology",
        "MLT",
        "Respiratory Tech",
        "Accident & Emergency Care Tech",
        "Neuro Electrophysiology",
        "Physician Assistant",
        "Optometry",
        "Medical Record Science",
        "Critical Care Management",
        "Other"
    )

    val ug_engineering = listOf(
        "Artificial Intelligence & Data Science",
        "Computer Science & Business Systems",
        "Cyber Security",
        "Fire and Safety Engg",
        "Petroleum Engg",
        "Petrochemical Engg",
        "Food Technology",
        "Biomedical Engg",
        "Agriculture Engg",
        "Chemical Engg",
        "Mining Engg",
        "Genetic Engg",
        "Pharmaceutical Engg",
        "Nanotechnology",
        "Textile Technology",
        "Medical Electronics",
        "Biotechnology Engg",
        "Mechatronics",
        "Marine Engg",
        "Automobile Engg",
        "Mech and Auto Engg",
        "Electronics and Instrumentation",
        "B.Design",
        "Aerospace Engg",
        "Software Engg",
        "Mechanical Engg",
        "Civil Engg",
        "Information Technology",
        "Computer Science",
        "Electronics & Communication Engg",
        "Electrical & Electronics Engg",
        "Other"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Student"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Course Details  ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Degree")
            SimpleRadioButtonComponent(
                radioOptions = radioOptions,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldHeading(text = "Category")
            AutoCompleteTextField(
                category,
                focusManager,
                categories = if (selectedOption == "UG") {
                    ugCategory
                } else if (selectedOption == "PG") {
                    pgCategory
                } else {
                    listOf()
                }
            )
            if (selectedOption == "UG" &&
                category.value == "ENGINEERING" ||
                category.value == "ALLIED HEALTH SCIENCE" ||
                category.value == "ARTS AND SCIENCE/MANAGEMENT COURSES" ||
                category.value == "LAW" ||
                category.value == "MEDICAL" ||
                category.value == "Nursing"
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldHeading(text = "Course")
                AutoCompleteTextField(
                    courses = course,
                    focusManager = focusManager,
                    categories = if (category.value == "ENGINEERING" && selectedOption == "UG") {
                        ug_engineering
                    } else if (category.value == "ALLIED HEALTH SCIENCE" && selectedOption == "UG") {
                        ug_alliedHS
                    } else if (category.value == "ARTS AND SCIENCE/MANAGEMENT COURSES" && selectedOption == "UG") {
                        ug_arts_management
                    } else if (category.value == "LAW" && selectedOption == "UG") {
                        ug_law
                    } else if (category.value == "MEDICAL" && selectedOption == "UG") {
                        ug_medical
                    } else if (category.value == "Nursing" && selectedOption == "UG") {
                        ug_nursing
                    } else {
                        listOf()
                    }
                )
            }

            // special course
            if (course.value == "Other" || category.value == "Other") {
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldHeading(text = "Course")
                CustomTextField(
                    text = otherCourse.value,
                    placeholder = "Enter course",
                    onChange = { otherCourse.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    keyBoardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
            if (category.value.isNotEmpty() ||
                category.value == "B.Arch" ||
                category.value == "AGRICULTURAL SCIENCE" ||
                category.value == "Other"
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldHeading(text = "Academic Year")
                AutoCompleteTextField(
                    courses = academicYear,
                    focusManager = focusManager,
                    categories = academicYears
                )
            }
            if (category.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldHeading(text = "Date of admission")
                val mDatePickerDialog = datePickerDialog(context, dateofAdmsn)
                if (dateofAdmsn.value.isNotEmpty()) {
                    CustomTextField(
                        text = dateofAdmsn.value,
                        placeholder = "Date of birth",
                        onChange = { dateofAdmsn.value = it }
                    )
                }
                Button(
                    onClick = {
                        mDatePickerDialog.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = if (dateofAdmsn.value.isEmpty()) "Choose" else "Change")
                }
            }
            if (category.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldHeading(text = "Certificate Collect")
                CustomTextField(
                    text = certificateCollect.value,
                    placeholder = "Certificate collect",
                    onChange = { certificateCollect.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    keyBoardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
            SingleButton(
                onSingleButtonClick = {
                    if (
                        category.value.isNotEmpty() &&
                        otherCourse.value.isNotEmpty() &&
                        academicYear.value.isNotEmpty() &&
                        dateofAdmsn.value.isNotEmpty() &&
                        course.value.isNotEmpty() ||
                        certificateCollect.value.isNotEmpty() ||
                        category.value == "B.Arch" ||
                        category.value == "AGRICULTURAL SCIENCE"
                    ) {
                        onNextButtonClick()
                    } else {
                    }
                },
                btnBackground = if (
                    category.value.isNotEmpty() &&
                    otherCourse.value.isNotEmpty() &&
                    academicYear.value.isNotEmpty() &&
                    dateofAdmsn.value.isNotEmpty() &&
                    course.value.isNotEmpty() ||
                    certificateCollect.value.isNotEmpty() ||
                    category.value == "B.Arch" ||
                    category.value == "AGRICULTURAL SCIENCE"
                ) {
                    Color.Black
                } else {
                    Color.LightGray
                }
            )
        }
    }
}

@Composable
private fun AutoCompleteTextField(
    courses: MutableState<String>,
    focusManager: FocusManager,
    categories: List<String>
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    var course by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    courses.value = course
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                placeholder = { Text(text = "Select") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightTextFields)
                    .border(
                        width = 1.8.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                value = course,
                onValueChange = {
                    course = it
                    expanded = true
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "arrow",
                            tint = Color.Black
                        )
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        expanded = false
                    }
                )
            )
        }

        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .width(textFieldSize.width.dp),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp)
                ) {
                    if (course.isNotEmpty()) {
                        items(
                            categories.filter {
                                it.lowercase()
                                    .contains(course.lowercase()) || it.lowercase()
                                    .contains("others")
                            }
                                .sorted()
                        ) {
                            CategoryItems(title = it) { title ->
                                course = title
                                expanded = false
                                focusManager.clearFocus()
                            }
                        }
                    } else {
                        items(
                            categories.sorted()
                        ) {
                            CategoryItems(title = it) { title ->
                                course = title
                                expanded = false
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItems(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}

@Composable
private fun datePickerDialog(
    context: Context,
    dob: MutableState<String>
): DatePickerDialog {
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            dob.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        },
        mYear,
        mMonth,
        mDay
    )
    return mDatePickerDialog
}

@Composable
fun SimpleRadioButtonComponent(
    radioOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    .padding(horizontal = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black,
                        disabledColor = Color.LightGray
                    ),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                Text(
                    text = text
                )
            }
        }
    }
}
