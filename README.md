# WebTextView
A text view that allows you to inject html code and use google fonts.

Example usage:
Use in your xml layout like a normal text view:
```xml
                <com.antoniotari.widgets.WebTextView
                    android:id="@+id/textwebDetail"
                    android:layout_width="match_parent"
                    android:layout_height="0dp"
                    android:layout_weight="1"
                </com.antoniotari.widgets.WebTextView>
```
check https://www.google.com/fonts to pick your font, then set it in the xml or programmatically:
```java
webTextView.setFont("Lato");
```
optional (check google fonts instructions, under css for instructions):
```java
webTextView.setFontFamily("'Lato', sans-serif");
```

