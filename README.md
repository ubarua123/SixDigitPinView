  # SixDigitPinView
A very lightweight library which serves the purpose of entering a 6 digit pix like OTP from an SMS. Uses latest Material IO and theming is picked up from whatever is set in the app


<img src="https://media.giphy.com/media/Q9vm6ROIAAAZvn6F6I/source.gif" data-canonical-src="https://media.giphy.com/media/Q9vm6ROIAAAZvn6F6I/source.gif" width="200" height="400" />

<img src="https://media.giphy.com/media/SV5hIR24dsCv63AvI4/source.gif" data-canonical-src="https://media.giphy.com/media/SV5hIR24dsCv63AvI4/source.gif" width="200" height="400" />


How to use:
==============
Include it in the layout as follows:
```
<com.wonderquill.sixdigitpinview.SixDigitPinView
android:id=@+id/sixDigitPinView
    android:layout_width=0dp
    android:layout_height=wrap_content/>
```

Reference it the usual way
--------------------------
```
val pinView = findViewById<SixDigitPinView>(R.id.sixDigitPinView)
```

To get the entered pin, call:
-----------------------------
```
val pin = pinView.getEnteredPin()
```

 I purposely didn’t implement fetching data from the clip board service since sometimes you might want to auto detect the sms and paste it.

To paste a 6 digit pin:
------------------------
```
pinView.pastePin("123456")
```

Example of getting from the clipboard
-------------------------------------
```
val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE)  as ClipboardManager
clipboardManager.primaryClip?.let.{
if(it.itemCount> 0)
        pinView.pastePin(it.getItemAt(0).text.toString())
}
```

Feel free to fork it, clone it, edit it!
