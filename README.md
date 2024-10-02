# CropView


This repository hosts the `cropView` module, a customizable view for croppingimages in Android applications.

## Features

* Customizable corner styles and colors
* Adjustable gridlines
* Drag-to-crop functionality
* Easy integration with JitPack

## Gradle Integration

### Step A: Add Maven Repository

In your project-level **build.gradle** or **settings.gradle** file, add the JitPack repository:
```
repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```  

### Step B: Add Dependencies

In your app-level **build.gradle** file, add the library dependency. Use the latest version: [![](https://jitpack.io/v/hypersoftdev/inappbilling.svg)](https://jitpack.io/#hypersoftdev/inappbilling)
```
implementation 'dev.pegasus.crop:x.x.x'
```

## Implementation

###. Add the `CropView` to your layout XML
```
    <dev.pegasus.crop.CropView
        android:id="@+id/crop_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cropBackgroundColor="@color/white"
        app:cropCornerColor="@color/black"
        app:cropCornerLength="16dp"
        app:cropCornerWidth="4dp"
        app:cropDraggingState="drag_all"
        app:cropGridlineColor="@color/gray"
        app:cropGridlineWidth="1dp"
        app:cropMargin="16dp" />
```
###. Set Bitmap to cropView
```
    binding.cropView.setBitmap(it)
```
###. Implement callback `onInitialized` and attach observer to listen live updates of cropView resolution (width, height)
```
    fun initCropView() = with(cropView) {
        onInitialized = {
            updatePixels(getCropSizeOriginal())
        }
        observeCropViewResolution = {
            updatePixels(it)
        }
    }
```
###. Get Height/width
```
    private fun updatePixels(rectF: RectF) {
        val cropWidth = "W: ${rectF.width().toInt()}"
        val cropHeight = "H: ${rectF.height().toInt()}"
        binding.mtvWidthCrop.text = cropWidth
        binding.mtvHeightCrop.text = cropHeight
    }
```


### Set CropView (overlay) corner's length (in Pixels)
        cropView.setCropCornerLength(value.toPx())

### Set CropView (overlay) corner's width (in Pixels)
        cropView.setCropCornerWidth(value.toPx())

### Set CropView (overlay) corner's color 
        cropView.setCropCornerColor(Color.BLUE)

### Set CropView (overlay) gridline's width (in Pixels)
        cropView.onGridLineWidthSliderChange(value.toPx())

### Set CropView (overlay) gridline's color 
        cropView.setGridlineColor(Color.WHITE)
        
### We have option of drag, we can block drag by few conditions ("All", "Corners only", "Edge only", "Image only", "Corners & Edge only")
        cropView.setDragState(position)

## Result

* **Get the cropped image:** Use `cropView.getCroppedImage()` to retrieve the cropped image as a Bitmap.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
