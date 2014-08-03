Android-IcoButton
=================

I was perennially frustrated with the fact that I couldn't align the icon in a button to the right/left of the text. The typical solution is to use a RelativeLayout or LinearLayout (if your designers are forgiving).

Using a Relativelayout for a button seems like killing a fly with a sword, so I built a Button widget that sensibly aligns your icon with the text provided. Some of the features for this widget:

1. It's a [Custom Composite Wiew](http://lucasr.org/2014/05/12/custom-layouts-on-android/) (so much more efficient and performant than a RelativeLayout)
2. Auto resizing of icon to match the size of your text (the text is the most important part of your button, so it made sense to size the icon accordingly)
3. Adds clicked/pressed states (you only supply the color to the button, and it auto-figures out a darker color for the pressed state)
4. Tested code. This was more of a fun project, so I went all in and wrote pretty comprehensive tests. Look at the IcoButtonViewHelperTest class. I got ascii diagrams and all.
5. Super readable code!

I've covered most of the predominant cases that I required for my project. If you find a case that's not covered, feel free to send in a pull request. This project was built with the intention of allowing people to quickly dive-in to the code, possibly write a test case if things are behavingly wierdly, patch the code and send it up to everyone.