Android-IcoButton
=================

An Android Button widget that sensibly aligns your icon and text.

## Features

1. Positioning the icon to the immediate "left of your text", "right of your text" or the more standard "left" and "right" extremes of the button .
2. Auto resizing of the icon to match the size of your text (the text is really the more important part of this button, so it made sense to size the icon accordingly).
3. Automatic  clicked/pressed states for your button (you only supply the color to the button, and it auto-figures out a darker color for the pressed state).
4. If you find the default attributes limiting, you can go ahead and add a nested `TextView` or `ImageView` in your xml, and you get precise control of how the individual parts should look like.
5. It's a [Custom Composite View](http://lucasr.org/2014/05/12/custom-layouts-on-android/) (so much more efficient and performant than say a LinearLayout/RelativeLayout).
6. Tested code. This was more of a fun project for me so I went all in and wrote pretty comprehensive tests for the positioning. Look at the `IcoButtonViewHelperTest.java` for the juicy details (with ascii diagrams and all).
7. Super readable code!

![Android IcoButtons](http://nerdsdev.weddingpartyapp.com/images/posts/android_icobuttons.png =500x)

## Custom attributes

Drawable resource for your icon:

    <attr name="drawable" format="integer"/>

Color of the button (the color you provide here is automatically taken to calculate the pressed state). Defaults to Holo Blue ("`#ff0099cc`"):

        <attr name="color" format="color"/>

Padding for your button. Defaults to `10dp` all around:

        <attr name="padding" format="dimension"/>

Probably the most important part of this widget. Indicates where you want the icon in our button to be positioned:

        <attr name="iconAlign" format="enum">
            <enum name="leftOfText" value="0"/>
            <enum name="rightOfText" value="1"/>
            <enum name="left" value="2"/>
            <enum name="right" value="3"/>
        </attr>

The title or text for your button:

        <attr name="txt" format="string"/>

Standard stuff with the following defaults:  (txtSize - 16dp, txtColor - white, txtAllCaps - false):

        <attr name="txtSize" format="dimension"/>
        <attr name="txtColor" format="color"/>
        <attr name="txtAllCaps" format="boolean"/>

