# CropView


This repository hosts the `cropView` module, a customizable view for croppingimages in Android applications.

## Features

* Flexible cropping for users
* Double tap focus
* Pinch Zoom
* Free Mode
* Size Displayer
* Auto Centered
* Animations
* Customizable corner styles and colors
* Adjustable gridlines
* Drag-to-crop functionality
* Easy integration with JitPack
* Aspect Ratio Mode
* Rotations

![screenshot](https://github.com/user-attachments/assets/54bb8422-7361-40d9-ba27-3025d9a96cb0)


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

In your app-level **build.gradle** file, add the library dependency. Use the latest version: 
```
implementation 'dev.pegasus.crop:x.x.x'
```

## Implementation

### Add CropView to Your Layout
To integrate the `CropView` into your XML layout, use the following code snippet:
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
### Set Bitmap to CropView
You can set the bitmap to the CropView using the following line of code:
```
cropView.setBitmap(it)
```
### Initialize CropView
To initialize the `CropView`, implement the `onInitialized` callback and observe live updates of the crop view resolution (width, height):
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
### Get Width and Height of Cropped Area
To retrieve and display the width and height of the cropped area, use the following method:
```
private fun updatePixels(rectF: RectF) {
    val cropWidth = "W: ${rectF.width().toInt()}"
    val cropHeight = "H: ${rectF.height().toInt()}"
    binding.mtvWidthCrop.text = cropWidth
    binding.mtvHeightCrop.text = cropHeight
}
```

## Customizing CropView
  You can customize the CropView settings as follows:

### Set Corner Length (in Pixels):
    cropView.setCropCornerLength(value.toPx())

### Set Corner Width (in Pixels):
    cropView.setCropCornerWidth(value.toPx())

### Set Corner Color:
    cropView.setCropCornerColor(Color.BLUE)

### Set Gridline Width (in Pixels):
    cropView.onGridLineWidthSliderChange(value.toPx())

### Set Gridline Color:
    cropView.setGridlineColor(Color.WHITE)
        
### Control Drag State:
You can manage dragging by setting conditions such as "All", "Corners only", "Edge only", "Image only", or "Corners & Edge only":
```
cropView.setDragState(position)
```
## Retrieving the Cropped Image
To obtain the cropped image as a Bitmap, use the following method:
```
val croppedImage = cropView.getCroppedImage()
```

https://github.com/user-attachments/assets/a1ccbe44-c8b7-44a0-b1b1-0f3fe9a5f9f2

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
