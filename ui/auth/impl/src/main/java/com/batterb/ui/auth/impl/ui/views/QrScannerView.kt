package com.batterb.ui.auth.impl.ui.views

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationAction
import com.batterb.ui.auth.impl.ui.views.components.QrScannerTopBar
import com.batterb.ui.auth.impl.util.QrCodeAnalyzer

@Composable
fun QrScanner(
    dispatchAction: (action: AuthorizationAction) -> Unit = {}
) {
    println("1")
    val context = LocalContext.current
    println("2")
    val lifecycleOwner = LocalLifecycleOwner.current
    println("3")
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    println("4")
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    println("5")
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
        }
    )
    println("6")
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    println("7")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QrScannerTopBar(
                onExitClicked = {
                    cameraProviderFuture.get().unbindAll()
                    dispatchAction(AuthorizationAction.CancelQrScan)
                }
            )
        }
    ) { mainScaffoldPadding ->
        if (hasCameraPermission) {
            AndroidView(
                factory = { viewContext ->
                    val previewView = PreviewView(viewContext)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            cameraProviderFuture.get().unbindAll()
                            dispatchAction(AuthorizationAction.QrCodeLogin(result))
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.padding(mainScaffoldPadding).fillMaxSize()
            )
        }
    }
}