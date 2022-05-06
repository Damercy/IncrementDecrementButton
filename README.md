# IncrementDecrementButton

Zomato/Swiggy like increment decrement button for android. Available as a composable as well as XML based view library!  
![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white) ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white) [![GitHub license](https://badgen.net/github/license/Naereen/Strapdown.js)](https://github.com/Naereen/StrapDown.js/blob/master/LICENSE) 

https://user-images.githubusercontent.com/24220261/167204923-4cd365d2-b2c7-4d49-86e0-395085591a27.mp4


### Screenshots

<img src="https://github.com/Damercy/IncrementDecrementButton/blob/2174c417b685e79ae87d74196df0c7cfaaa57e33/screenshots/btn_vertical.gif" height="500px" align="left"/>
<img src="https://github.com/Damercy/IncrementDecrementButton/blob/2174c417b685e79ae87d74196df0c7cfaaa57e33/screenshots/btn_horizontal.gif" height="500px" align="left"/>
<img src="https://github.com/Damercy/IncrementDecrementButton/blob/2174c417b685e79ae87d74196df0c7cfaaa57e33/screenshots/btn_basic.gif" height="500px"/>  


### Requirements
- API level 21+


### Installation
Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency in app's buid.gradle:
```groovy
dependencies { implementation 'com.github.Damercy:IncrementDecrementButton:1.1.0' }
```  
 
 
For gradle versions **7.X.X+**, you may face issues related to unable to resolve library on syncing projects. In that case, please update project's `settings.gradle` as follows:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url "https://jitpack.io" } // Add jitpack dependency here instead
    }
}
rootProject.name = "Your awesome app"
include ':app'
```
### Usage
#### XML
For a basic use case, you can simply plugin the library in your layout as follows:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
  
  <!-- Other views -->

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Sample text view"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <dev.dayaonweb.incrementdecrementbutton.IncrementDecrementButton
        android:id="@+id/inc_dec_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="@+id/textView"
        app:layout_constraintStart_toStartOf="@+id/textView"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintVertical_bias="0.25" />
  
  <!-- Other views -->

</androidx.constraintlayout.widget.ConstraintLayout>
```

The following attributes are implemented as of now:
| Attribute          | Format      | Description |
| ------------------ | ----------- | ----------- |
|`app:decrementText` |   string          | The text to set for decrement button. Default is `-` |
| `app:incrementText` |   string          | The text to set for increment button. Default is `+` |
| `app:middleText` |   string          | The text to set for the middle view. Default is `ADD` |
| `app:fontFamilyRes` |   reference          | The font family to set for the texts |
| `app:fontSizeRes` |   integer          | The font size to set for the texts. Defaults to 16sp |
| `app:cornerRadius` |   dimension          | The corner radius of the button. Defaults to 8dp |
| `app:animationType` |   enum          | The type of animation to set on text change. Either of `FADE`,`HORIZONTAL` or `VERTICAL`. Defaults to `FADE` |
| `app:animationDuration` |   integer          | The duration for animation. Defauls to `500ms` |
| `app:textColor` |   color          | The color to set for texts |
| `app:buttonBackground` |   reference          | The color to set as background for button |
| `app:borderStrokeColor` |   color          | The border stroke color to set for button. Defaults to `white` |
| `app:borderStrokeWidth` |   integer          | The border width to set as background for button. Defaults to `0` hence no border is shown |

Programatically:
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: IncrementDecrementButton = findViewById(R.id.inc_dec_btn)
        
        // available functions
        button.setIncrementButtonText()
        button.setDecrementButtonText()
        button.getCurrentValue() // Get current value  (amount)
        button.setFontFamily()
    }
}
```
#### Composable
Simply use the composable as shown below.
```kotlin
   MaterialTheme {
		// Some other composables
                IncrementDecrementButton()
            }
```
The IncrementDecrementButton is highly customizable. You can pass in your `Modifier` with appropriate styling as required or use the available methods provided. 

You can even pass in your own composables for the decrement (left), middle (center) & increment (right) places of the button. The default values of IncrementDecrementButton is as follows:
```kotlin
@Composable
fun IncrementDecrementButton(
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = FontFamily(typeface = Typeface.DEFAULT),
    fontSize: TextUnit = 16.0.sp,
    cornerRadius: Dp = 8.dp,
    animationType: AnimationType = AnimationType.FADE,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.contentColorFor(backgroundColor),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.White),
    value: Int = 0,
    onDecrementClick: (Int) -> Unit = {},
    onIncrementClick: (Int) -> Unit = {},
    onMiddleClick: (Int) -> Unit = {},
    decrementComposable: @Composable (cb: (Int) -> Unit) -> Unit = { cb ->
        DefaultDecrementComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            borderStroke = borderStroke,
            onDecrementClick = { cb(-1) }
        )
    },
    incrementComposable: @Composable (cb: (Int) -> Unit) -> Unit = { cb ->
        DefaultIncrementComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            borderStroke = borderStroke,
            onIncrementClick = { cb(-1) }
        )
    },
    middleComposable: @Composable (Int, cb: (Int) -> Unit) -> Unit = { buttonValue, cb ->
        DefaultMiddleComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            borderStroke = borderStroke,
            onMiddleClick = { cb(-1) },
            value = buttonValue
        )
    },
)
```

Please read the [release notes](https://github.com/Damercy/IncrementDecrementButton/releases/tag/2.0.0) for details on available methods & changes.

### Try sample app
You can download the [sample apk](https://github.com/Damercy/IncrementDecrementButton/releases/download/2.0.0/sample-app.apk) to see this library in action.

### Hit the ⭐ if this library helped you in your projects 😄
